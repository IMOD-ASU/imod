/* Copyright 2010-2012 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.databasemigration

import grails.util.GrailsUtil

import java.text.SimpleDateFormat

import liquibase.Liquibase
import liquibase.changelog.ChangeLogIterator
import liquibase.changelog.DatabaseChangeLog
import liquibase.changelog.filter.ContextChangeSetFilter
import liquibase.changelog.filter.CountChangeSetFilter
import liquibase.changelog.filter.DbmsChangeSetFilter
import liquibase.database.Database
import liquibase.diff.Diff
import liquibase.executor.Executor
import liquibase.executor.ExecutorService
import liquibase.executor.LoggingExecutor
import liquibase.lockservice.LockService
import liquibase.parser.ChangeLogParserFactory
import liquibase.util.StringUtils

import org.apache.log4j.Logger
import org.springframework.context.ApplicationContext

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
class ScriptUtils {

	static final String DAY_DATE_FORMAT = 'yyyy-MM-dd'
	static final String FULL_DATE_FORMAT = DAY_DATE_FORMAT + ' HH:mm:ss'

	private Logger log = Logger.getLogger('grails.plugin.databasemigration.Scripts')

	static void printStackTrace(Throwable e) {
		GrailsUtil.deepSanitize e
		e.printStackTrace()
	}

	static PrintStream calculateDestination(List argsList, Integer argIndex = 0, boolean relativeToMigrationDir = false) {
		if (!argsList[argIndex]) {
			return System.out
		}

		String destination = argsList[argIndex]
		if (relativeToMigrationDir) {
			destination = MigrationUtils.changelogLocation + '/' + destination
		}

		new PrintStream(destination)
	}

	static PrintWriter newPrintWriter(List argsList, Integer argIndex = 0, boolean relativeToMigrationDir = false) {
		new PrintWriter(calculateDestination(argsList, argIndex, relativeToMigrationDir))
	}

	static OutputStreamWriter newOutputStreamWriter(List argsList, Integer argIndex = 0, boolean relativeToMigrationDir = false) {
		new OutputStreamWriter(calculateDestination(argsList, argIndex, relativeToMigrationDir))
	}

	// run a script (called by the closure) which generates changelog XML, and
	// write it to STDOUT if no filename was specified, to an XML file if the
	// extension is .xml, and convert to the Groovy DSL and write to a Groovy
	// file if the extension is .groovy
	static void executeAndWrite(String filename, boolean add, Closure c) {
		PrintStream out
		ByteArrayOutputStream baos
		if (filename) {
			filename = MigrationUtils.changelogLocation + '/' + filename
			if (filename.toLowerCase().endsWith('groovy')) {
				baos = new ByteArrayOutputStream()
				out = new PrintStream(baos)
			}
			else {
				out = new PrintStream(filename)
			}
		}
		else {
			out = System.out
		}

		c(out)

		if (baos) {
			String xml = new String(baos.toString('UTF-8'))
			String groovy = ChangelogXml2Groovy.convert(xml)
			new File(filename).withWriter { it.write groovy }
		}

		if (add) {
			registerInclude filename
		}
	}

	static void registerInclude(String filename) {
		String fullPath = new File(filename).absolutePath
		String fullMigrationFolderPath = new File(MigrationUtils.changelogLocation).absolutePath
		String relativePath = (fullPath - fullMigrationFolderPath).substring(1)
		appendToChangelog new File(filename), relativePath
	}

	static void appendToChangelog(File sourceFile, String includeName) {

		File changelog = new File(MigrationUtils.changelogLocation, MigrationUtils.changelogFileName)
		if (changelog.absolutePath.equals(sourceFile.absolutePath)) {
			return
		}

		boolean xml = changelog.name.toLowerCase().endsWith('.xml')
		String includeStatement = xml ? "\n    <include file='$includeName'/>\n" : "\n\tinclude file: '$includeName'"

		def asLines = changelog.text.readLines()
		int count = asLines.size()
		int index = -1
		for (int i = count - 1; i > -1; i--) {
			if ((xml && asLines[i].trim() == '</databaseChangeLog>') || asLines[i].trim() == '}') {
				index = i
				break
			}
		}

		if (index == -1) {
			// TODO
			return
		}

		// TODO backup
		changelog.withWriter {
			index.times { i -> it.write asLines[i]; it.newLine() }

			it.write includeStatement; it.newLine()

			(count - index).times { i -> it.write asLines[index + i]; it.newLine() }
		}
	}

	static void closeConnection(it) { try { it?.close() } catch (ignored) {} }

	// returns a Map; the rendered date String is under the 'date' key,
	// calculateDateFileNameIndex is under 'calculateDateFileNameIndex',
	// and any exception message is under 'error'
	static Map calculateDate(List argsList) {

		def results = [:]

		String dateFormat
		String dateString

		switch (argsList.size()) {
			case 1:
				dateFormat = DAY_DATE_FORMAT
				dateString = argsList[0].trim()
				break
			case 2:
				dateFormat = FULL_DATE_FORMAT
				dateString = argsList[0] + ' ' + argsList[1]
				try {
					new SimpleDateFormat(dateFormat).parse(dateString)
				}
				catch (e) {
					// assume that 2nd param is filename
					dateFormat = DAY_DATE_FORMAT
					dateString = argsList[0]
					results.calculateDateFileNameIndex = 1
				}
				break
			case 3:
				dateFormat = FULL_DATE_FORMAT
				dateString = argsList[0] + ' ' + argsList[1]
				results.calculateDateFileNameIndex = 2
		}

		if (dateString) {
			try {
				results.date = new SimpleDateFormat(dateFormat).parse(dateString)
			}
			catch (e) {
				results.error = "Problem parsing '$dateString' as a Date: $e.message"
			}
			return results
		}

		results.error = 'Date must be specified as two strings with the format "yyyy-MM-dd HH:mm:ss"' +
		                'or as one strings with the format "yyyy-MM-dd"'

		results
	}

	static GormDatabase createGormDatabase(config, appCtx) {
		def dialect = config.dataSource.dialect
		if (dialect) {
			if (dialect instanceof Class) {
				dialect = dialect.name
			}
		}
		else {
			dialect = appCtx.dialectDetector
		}

		def GrailsAnnotationConfiguration = MigrationUtils.classForName('org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration')

		def configuration = GrailsAnnotationConfiguration.newInstance(
			grailsApplication: appCtx.grailsApplication,
			properties: ['hibernate.dialect': dialect.toString()] as Properties)

		def hibernateCfgXml = Thread.currentThread().contextClassLoader.getResource('hibernate.cfg.xml')
		if (hibernateCfgXml) {
			configuration.configure hibernateCfgXml
		}

		configuration.buildMappings()

		new GormDatabase(configuration)
	}

	static Diff createDiff(Database referenceDatabase, Database targetDatabase,
	                       ApplicationContext appCtx, String diffTypes) {
		Diff diff = new Diff(referenceDatabase, targetDatabase)
		diff.diffTypes = diffTypes
		diff.addStatusListener appCtx.diffStatusListener
		diff
	}

	static void generatePreviousChangesetSql(Database database, Liquibase liquibase, Writer output, int changesetCount, int skip, String contexts) {
		def changeLogFile = liquibase.changeLogFile

		liquibase.changeLogParameters.contexts = StringUtils.splitAndTrim(contexts, ",")

		Executor oldTemplate = ExecutorService.instance.getExecutor(database)
		LoggingExecutor loggingExecutor = new LoggingExecutor(ExecutorService.instance.getExecutor(database), output, database)
		ExecutorService.instance.setExecutor database, loggingExecutor

		LockService lockService = LockService.getInstance(database)
		lockService.waitForLock()

		try {
			DatabaseChangeLog changeLog = ChangeLogParserFactory.instance.getParser(changeLogFile, liquibase.resourceAccessor).parse(
				changeLogFile, liquibase.changeLogParameters, liquibase.resourceAccessor)
			changeLog.changeSets.reverse true
			skip.times { changeLog.changeSets.remove(0) }

			liquibase.checkDatabaseChangeLogTable true, changeLog, contexts
			changeLog.validate liquibase.database, contexts

			ChangeLogIterator logIterator = new ChangeLogIterator(changeLog,
				new ContextChangeSetFilter(contexts),
				new DbmsChangeSetFilter(database),
				new CountChangeSetFilter(changesetCount))

			logIterator.run new NoopVisitor(database), database

			output.flush()
		}
		finally {
			lockService.releaseLock()
			ExecutorService.instance.setExecutor database, oldTemplate
		}
	}
}
