package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'course overview'
		]
	}

    def springSecurityService

    def create() {

    	print params

        def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

        // create a new instructor
        def newInstructor = new Instructor(
            firstName: params.firstName,
            lastName: params.lastName,
            email: params.email,
            role: params.role,
            location: params.location,
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

	// FIXME rename the action to addInstructor
    def add(){
		// FIXME rename GSP file to addInstructor and delete this render statement
    	render(
			view: 'addinstructor',
		)
    }


    def delete() {
    	print params.id
		def instructorInstance = Instructor.get(params.id)
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
		}
	}




}
