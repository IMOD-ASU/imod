package imod

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper

class CourseOverviewController {
    def springSecurityService

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

            if(it.id == null){
                
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

            }else{

                def newInstructor = Instructor.get(it.id)
                newInstructor.firstName = firstName;
                newInstructor.lastName = lastName;
                newInstructor.email = email;
                newInstructor.role = role;
                newInstructor.officeHours = officeHours;
                newInstructor.webPage = webPage;
                newInstructor.location = location;

                // save new instructor and the updated user to database
                newInstructor.save()

            }


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
	}

    // syllabus html page
    def syllabus(Long id){

        def currentImod = Imod.get(id);

        def learningObjectives = LearningObjective.findAllByImod(currentImod)

        def contentList = Content.findAllWhere(imod: currentImod, parentContent: null);

        [
            currentImod: currentImod,
            currentPage: 'syllabus',
            learningObjectives: learningObjectives,
            contentList: contentList
        ]
    }
}
