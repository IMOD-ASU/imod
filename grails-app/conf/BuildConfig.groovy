grails.servlet.version = '2.5' // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = 'target/${appName}-${appVersion}.war'
grails.project.war.file = 'target/${appName}.war'

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]
grails.project.dependency.resolver='maven'
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

		// uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
		//mavenRepo 'http://snapshots.repository.codehaus.org'
		//mavenRepo 'http://repository.codehaus.org'
		//mavenRepo 'http://download.java.net/maven/2/'
		//mavenRepo 'http://repository.jboss.com/maven2/'
		mavenRepo 'http://download.java.net/maven/2/'
	}

	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
		runtime 'org.postgresql:postgresql:9.3-1102-jdbc41'
	}

	plugins {
		build ':tomcat:7.0.54'

		compile ':console:1.4.4'
		compile ':scaffolding:2.1.2'
		compile ':postgresql-extensions:4.1.0'
		compile ':cache:1.1.7'
		compile ':spring-security-core:2.0-RC4'
		compile ':mail:1.0.6'
		compile ':jquery-ui:1.10.3'
		compile ':famfamfam:1.0.1'
		compile ':spring-security-ui:1.0-RC2'
		compile ':google-visualization:0.7'

		runtime ':hibernate4:4.3.5.5'
		runtime ':jquery:1.11.1'
		runtime ':resources:1.2.8'
		runtime ':database-migration:1.4.0'
	}
}
