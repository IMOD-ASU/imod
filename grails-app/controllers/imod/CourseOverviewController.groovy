package imod

import grails.converters.JSON
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


    def create() {
        final jsonParser = new JsonSlurper()
        final parameters = jsonParser.parseText(params.parameters)

        parameters.each() {
            final firstName = it.firstName
            final lastName = it.lastName
            final email = it.email
            final role = it.role
            final officeHours = it.officeHours
            final webPage = it.webPage
            final location = it.location

            if (it.id == null) {
                def newInstructor = new Instructor(
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    role: role,
                    officeHours: officeHours,
                    webPage: webPage,
                    location: location,
                    createdBy: params.imodId
                )

                // save new instructor and the updated user to database
                newInstructor.save()
            }
            else {
                def newInstructor = Instructor.get(it.id)
                newInstructor.firstName = firstName
                newInstructor.lastName = lastName
                newInstructor.email = email
                newInstructor.role = role
                newInstructor.officeHours = officeHours
                newInstructor.webPage = webPage
                newInstructor.location = location

                // save new instructor and the updated user to database
                newInstructor.save()
            }
        }

        render (
            [
                value: 'success'
            ] as JSON
        )
    }

	// FIXME rename the action to addInstructor
    def add() {
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
    def syllabus(Long id) {
        final currentImod = Imod.get(id)
        final learningObjectives = LearningObjective.findAllByImod(currentImod)
        final contentList = Content.findAllWhere(imod: currentImod, parentContent: null)

        def text = '<ul>'

        contentList.each() {
            text += getSubContent(it)
        }

        text += '</ul>'

        [
            currentImod: currentImod,
            currentPage: 'syllabus',
            learningObjectives: learningObjectives,
            contentList: text
        ]
    }

    def generatedSyllabus(Long id) {
        final currentImod = Imod.get(id)
        final learningObjectives = LearningObjective.findAllByImod(currentImod)
        final contentList = Content.findAllWhere(imod: currentImod, parentContent: null)

        def text = '<ul>'

        contentList.each() {
            text += getSubContent(it)
        }

        text += '</ul>'

        [
            currentImod: currentImod,
            currentPage: 'syllabus',
            learningObjectives: learningObjectives,
            contentList: text
        ]
    }

     def syllabuspdf(Long id) {
        final currentImod = Imod.get(id)
        final learningObjectives = LearningObjective.findAllByImod(currentImod)
        final contentList = Content.findAllWhere(imod: currentImod, parentContent: null)

        def text = '<ul>'

        contentList.each() {
            text += getSubContent(it)
        }

        text += '</ul>'

        renderPdf(
            template: "/courseOverview/syllabus",
            model: [
                currentImod: currentImod,
                currentPage: 'syllabus',
                learningObjectives: learningObjectives,
                contentList: text
            ],
            filename: currentImod?.name.replaceAll(' ', '_') + '.pdf'
        )
    }

    private def getSubContent(Content current) {
        def text = '<li>' + current.topicTitle

        if (current.subContents != null) {
            text += '<ul>'
            current.subContents.each() {
                text += getSubContent(it)
            }
            text += '</ul>'
        }

        text +=  '</li>'

        return text
    }
}
