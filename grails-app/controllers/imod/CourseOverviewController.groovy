package imod

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class CourseOverviewController {
	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'course overview'
		]
	}

    def springSecurityService

    def create() {
        
        // create a new instructor
        def newInstructor = new Instructor(
            firstName: params.firstName,
            lastName: params.lastName,
            email: params.email,
            role: params.role,
            officeHours: params.officeHours,
            webPage: params.webPage,
            location: params.location,
            createdBy: params.id
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

	// FIXME rename the action to addInstructor
    def add(){
		
    	render(
			view: 'addinstructor',
            model: [imodid: params.imodid]
		)
    }


    def delete() {
    	render (
            [
                value: params.id
            ] as JSON
        )
        
        def instructorInstance = Instructor.get(params.id)

		/*def instructorInstance = Instructor.get(params.id)
		if (!instructorInstance) {
			flash.message = message(
				code: 'default.not.found.message',
				args: [
					message(
						code: 'instructor.label',
						default: 'Instructor'
					),
					instructorInstance
				]
			)
			redirect(
				controller: 'courseOverview',
				action: 'index',
				id: instructorInstance.id
			)
			return
		}

		try {
			instructorInstance.delete()
			flash.message = message (
				code: 'default.deleted.message',
				args: [
					message(
						code: 'instructor.label',
						default: 'Instructor'
					),
					instructorInstance
				]
			)
			redirect(
				controller: 'courseOverview',
				action: 'index',
				id: instructorInstance.id
			)
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(
				code: 'default.not.deleted.message',
				args: [
					message(
						code: 'instructor.label',
						default: 'Instructor'
					),
					instructorInstance
				]
			)
			redirect(
				action: 'show',
				id: id
			)
		}*/
	}
}
