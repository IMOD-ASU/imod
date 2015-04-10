// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ 'classpath:${appName}-config.properties',
//                             'classpath:${appName}-config.groovy',
//                             'file:${userHome}/.grails/${appName}-config.properties',
//                             'file:${userHome}/.grails/${appName}-config.groovy']

// if (System.properties['${appName}.config.location']) {
//    grails.config.locations << 'file:' + System.properties['${appName}.config.location']
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
	all:			'*/*',
	atom:			'application/atom+xml',
	css:			'text/css',
	csv:			'text/csv',
	form:			'application/x-www-form-urlencoded',
	html:			['text/html','application/xhtml+xml'],
	js:				'text/javascript',
	json:			['application/json', 'text/json'],
	multipartForm:	'multipart/form-data',
	rss:			'application/rss+xml',
	text:			'text/plain',
	xml:			['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = 'none' // none, html, base64
grails.views.gsp.encoding = 'UTF-8'
grails.converters.encoding = 'UTF-8'
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// simplify testing for empty strings
grails.databinding.convertEmptyStringsToNull = true

// automatically flush the database
grails.gorm.autoFlush = true

environments {
	development {
		grails.logging.jul.usebridge = true
		grails.gorm.failOnError = true
	}
    test {
        grails.logging.jul.usebridge = true
        grails.gorm.failOnError = true
        grails.serverURL = 'http://imod.poly.asu.edu:8080/imod-test'
    }
	production {
		grails.logging.jul.usebridge = false
		grails.serverURL = 'http://imod.poly.asu.edu:8080/imod-demo'
	}
}

// log4j configuration
log4j = {
	// Example of changing the log pattern for the default console appender:
	//
	//appenders {
	//    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	//}

	error	'org.codehaus.groovy.grails.web.servlet',		// controllers
			'org.codehaus.groovy.grails.web.pages',			// GSP
			'org.codehaus.groovy.grails.web.sitemesh',		// layouts
			'org.codehaus.groovy.grails.web.mapping.filter',// URL mapping
			'org.codehaus.groovy.grails.web.mapping',		// URL mapping
			'org.codehaus.groovy.grails.commons',			// core / classloading
			'org.codehaus.groovy.grails.plugins',			// plugins
			'org.codehaus.groovy.grails.orm.hibernate',		// hibernate integration
			'org.springframework',
			'org.hibernate',
			'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'imod.ImodUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'imod.ImodUserRole'
grails.plugin.springsecurity.authority.className = 'imod.Role'


//InterceptUrlMap to make all links secure
//InterceptUrlMap to make all links secure
grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
	'/':							['permitAll'],
	'/**/css/**':					['permitAll'],
	'/**/favicon.ico':				['permitAll'],
	'/**/images/**':				['permitAll'],
	'/**/js/**':					['permitAll'],
	'/assets/**':					['permitAll'],
	'/error/**':					['permitAll'],
	'/index':						['permitAll'],
	'/index.gsp':					['permitAll'],
	'/j_spring_security_check':		['permitAll'],
	'/login/**':					['permitAll'],
	'/logout/**':					['permitAll'],
	'/register/**':					['permitAll'],
	'/security/**':					['permitAll'],
	'/plugins/**':					['permitAll'],
	'/assessment/**':				['ROLE_USER'],
	'/content/**':					['ROLE_USER'],
	'/courseOverview/**':			['ROLE_USER'],
	'/imod/**':						['ROLE_USER'],
	'/learningObjective/**':		['ROLE_USER'],
	'/pedagogy/**':					['ROLE_USER'],
	'/pedagogyTechnique/**':		['ROLE_USER'],
	'/console':						['ROLE_ADMIN']
]
//to allow user to login, avoid double encryption
grails.plugin.springsecurity.ui.encodePassword = false

//mail configuration
grails {
	mail {
		host = 'smtp.gmail.com'
		port = 465
		username = 'imod.grails@gmail.com'
		password = 'B0bblegum'
		props =	[
					'mail.smtp.auth':'true',
					'mail.smtp.socketFactory.port':'465',
					'mail.smtp.socketFactory.class':'javax.net.ssl.SSLSocketFactory',
					'mail.smtp.socketFactory.fallback':'false'
				]
	}
 }

//makes the imod page default url after user logs in
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/imod'
// FIXME use this variable or delete it
objective.length = 40
