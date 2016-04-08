package imod

import org.springframework.dao.DataIntegrityViolationException

class SettingsController {
	def springSecurityService
	def passwordEncoder

    def index() {
    	def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
    	def roles = ['Assistant Professor', 'Associate Professor', 'Professor', 'Teaching Assistant', 'Course Assistant', 'Grader', 'Course Designer', 'Lecturer', 'Sr. Lecturer', 'Other']

    	[
			currentPage: 'index',
			currentUser: currentUser,
			roles: roles
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
			flash.error = 'Could not update password!'
		}

    	redirect(action: 'index', controller: 'settings')
    }

    def updateDetails() {

    	def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
    	try {
			currentUser.firstName = params.firstName
			currentUser.lastName = params.lastName
			currentUser.location = params.location
			currentUser.officeHours = params.officeHours
			currentUser.webPage = params.webPage
			currentUser.phoneNumber = params.phoneNumber
			currentUser.role = params.role
	    	flash.detailsSuccess = 'Personal Information updated successfully'
		} catch (DataIntegrityViolationException e) {
			flash.detailsError = 'Could not update details!'
		}

    	redirect(action: 'index', controller: 'settings')

    }
}
