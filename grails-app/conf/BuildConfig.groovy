// This controls what level of features the grails server supports
grails.servlet.version = '3.0' // Change depending on target container compliance (2.5 or 3.0)
// This controls where grails builds the class files to
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.war.file = "target/${appName}.war"

// This controls what java version is required to be able to run grails
grails.project.target.level = 1.8
grails.project.source.level = 1.8

// This tells grails to grails to build the pages as part of the build
grails.views.gsp.sitemesh.preprocess = true

// TODO Workaround for https://github.com/chrismair/GrailsCodeNarcPlugin/issues/6
codenarc.excludeBaseline = ''

grails.project.dependency.resolver = 'maven'

codenarc.reports = {
    TextReport('text') {
        outputFile = 'static-analysis.txt'
        title = 'Code Report'
    }
    HtmlReport('html') {
        outputFile = 'static-analysis.html'
        title = 'Code Report'
    }
}


grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits('global') {
		// specify dependency exclusions here; for example, uncomment this to disable ehcache:
		// excludes 'ehcache'
	}
	log 'warn' // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	checksums true // Whether to verify checksums on resolve
	legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

	repositories {
		inherits true // Whether to inherit repository definitions from plugins

		grailsPlugins()
		grailsHome()
		grailsCentral()

		mavenLocal()
		mavenCentral()

		mavenRepo 'http://download.java.net/maven/2/'
	}

	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
		runtime 'org.postgresql:postgresql:9.4-1201-jdbc41'
		compile 'org.jadira.usertype:usertype.core:3.2.0.GA'
		runtime 'org.springframework:spring-test:3.1.0.RELEASE'
	}

	plugins {
		build ':tomcat:7.0.55.3'

		compile ':scaffolding:2.1.2'
		compile ':postgresql-extensions:4.5.0'
		compile ':cache:1.1.8'
		compile ':spring-security-core:2.0-RC5'
		compile ':mail:1.0.7'
		compile ':jquery-ui:1.10.4'
		compile ':famfamfam:1.0.1'
		compile ':spring-security-ui:1.0-RC2'
		compile ':joda-time:1.5'
		compile ':rest-client-builder:2.1.1'
		compile ':rendering:1.0.0'

		provided ':codenarc:0.24'

		runtime ':hibernate4:4.3.10'
		runtime ':jquery:1.11.1'
	}
}
