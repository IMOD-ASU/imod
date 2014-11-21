package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage: 'course overview'
		]
	}

    def springSecurityService

    def create(){

        def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

        // create a new instructor
        def newInstructor = new Instructor(
            firstName:'abc',
            lastName:'xyz',
            email:'abc@asu.edu',
            role:'Professor',
            location:'Peralta',
            createdBy: currentUser
        )

        // save new instructor and the updated user to database
        newInstructor.save()

        // redirect to editing new Instructor
        redirect(
            controller: 'CourseOverview',
            action: 'index',
            id: springSecurityService.currentUser.id
        )

    }
}
