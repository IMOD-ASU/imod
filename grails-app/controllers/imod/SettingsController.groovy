package imod

import org.springframework.dao.DataIntegrityViolationException

class SettingsController {
	def springSecurityService
	def passwordEncoder

    def index() {
    	def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

    	[
			currentPage: 'index',
			currentUser: currentUser
		]

    }

    def updateSettings() {

    	// Validations
    	// null and empty check
    	if (params.currentPassword == null ||
    		params.currentPassword.isEmpty() ||
    		params.newPassword == null ||
    		params.newPassword.isEmpty()) {
    		render(status: 400, text: 'Validation errors: Current and new password cannot be empty')
    		return
    	}

    	// new password and confirm password must match
    	if (!params.newPassword == params.reenterPassword) {
    		render(status: 400, text: 'Validation errors: New password and reentered password must match')
    		return
    	}

    	def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
    	if (!passwordEncoder.isPasswordValid(currentUser.password, params.currentPassword, null)) {
    		render(status: 400, text: 'Validation errors: Current password does not match')
    		return
    	}

    	try {
			currentUser.password = params.newPassword
	    	flash.success = 'Password updated successfully'
		} catch (DataIntegrityViolationException e) {
			flash.error = 'could not delete object'
		}

    	redirect(action: 'index', controller: 'settings')
    }
}
