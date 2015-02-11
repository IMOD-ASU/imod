package imod

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper

class CourseOverviewController {

    static allowedMethods = [
        delete:           'POST',
        create:           'POST',
    ]

	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'course overview'
		]
	}

    def springSecurityService

    def create(String JSONData) {

        def jsonParser = new JsonSlurper()
        def parameters = jsonParser.parseText(params.parameters)

        parameters.each() { 

            def firstName = it.firstName
            def lastName = it.lastName
            def email = it.email
            def role = it.role
            def officeHours = it.officeHours
            def webPage = it.webPage
            def location = it.location
            
            def newInstructor = new Instructor(
                firstName: firstName,
                lastName: lastName,
                email: email,
                role: role,
                officeHours: officeHours,
                webPage: webPage,
                location: location,
                createdBy: params.imod_id
            )

            // save new instructor and the updated user to database
            newInstructor.save()

        }

        render (
            [
                value: 'success'
            ] as JSON
        )
        

        // if no ajax

        /*
        // create a new instructor
        def newInstructor = new Instructor(
            firstName: params.firstName,
            lastName: params.lastName,
            email: params.email,
            role: params.role,
            officeHours: params.officeHours,
            webPage: params.webPage,
            location: params.location,
            createdBy: params.imod_id
        )

        // save new instructor and the updated user to database
        newInstructor.save()

        // redirect to editing new Instructor
        redirect(
            controller: 'CourseOverview',
            action: 'index',
            id: springSecurityService.currentUser.id

        )*/
    }

	// FIXME rename the action to addInstructor
    def add(){
		
    	render(
			view: 'addinstructor',
            model: [imodid: params.imodid]
		)
    }


    def delete() {
    	
        def instructorList = params.list('selected[]')

        instructorList.each { item ->
            
            def instructorInstance = Instructor.get(item)
            instructorInstance.delete()

        }

        render (
            [
                value: 'success'
            ] as JSON
        )

        // todo
        // check if instructor doesn't exist and handle the exception

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
