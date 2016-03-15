package imod

class SettingsController {
	def springSecurityService

    def index() {
    	def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

    	[
			currentPage: 'index',
			currentUser: currentUser
		]

    }
}
