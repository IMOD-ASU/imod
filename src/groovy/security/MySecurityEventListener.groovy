package security

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.codehaus.groovy.grails.web.util.WebUtils

class MySecurityEventListener
      implements ApplicationListener<AuthenticationSuccessEvent> {

  	def springSecurityService

   	void onApplicationEvent(AuthenticationSuccessEvent event) {

   		def currentUser = ((AuthenticationSuccessEvent) event).getAuthentication().principal

   		def session = WebUtils.retrieveGrailsWebRequest().session
      	// def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		def isAdmin = false

		currentUser.getAuthorities().each {
			if (it.authority  == 'ROLE_ADMIN') {
				isAdmin = true
			}
		}

		session['isAdmin'] = isAdmin
   }
}
