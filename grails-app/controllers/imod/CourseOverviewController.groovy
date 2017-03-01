package imod

import grails.converters.JSON
import groovy.json.JsonSlurper

class CourseOverviewController {
	def springSecurityService

	static allowedMethods = [
		delete: 'POST',
		create: 'POST',
		updateSyllabusPrefs: 'POST',
	]

	def index(String id) {

		if ( id != null && id == 'new') {
			def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

			def dummyImod = new Imod(
				owner: currentUser,
				name: 'New Imod',
				url: 'example.com',
				subjectArea: 'sample',
				imodNumber: '1',
				//	gradingProcedure: '',
				attendance: 'Regular on-time attendance in this course is expected',
				classParticipation: 'Students are expected to participate in the educational process and not be a disruptive element with regard to the learning of others.',
				professionalConduct: 'All students should be familiar with the Student Code of Conduct, which can be found at http://www.asu.edu/studentlife/judicial/',
				missedExams: 'The only legitimate reasons for missing an exam are business or university related travel or illness for more than half the assignment period with appropriate documentation. Contact your instructor to make appropriate attangements',
				missedAssignments: 'Assignments should be turned in by the specified deadline. Late assignments will not be accepted unless prior arrangements have been made with the instructor.',
				saved: false
			)

			[
				currentImod: dummyImod,
				currentPage: 'course overview',
				id: 'new'
			]
		} else {

			[
				currentImod: Imod.get(Long.parseLong(id)),
				currentPage: 'course overview',
				id: id
			]
		}

	}

	def create() {
		final jsonParser = new JsonSlurper()
		final parameters = jsonParser.parseText(params.parameters)

		parameters.each {
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
		def count = 0

		def text = '<ul>'

		contentList.each {
			count++
			text += getSubContent(it)
		}

		text += '</ul>'

		if (!count) {
			text = ''
		}

		def syllabusPrefs = SyllabusPrefs.findByImod(currentImod)

		if (syllabusPrefs != null) {
			[
				currentImod: currentImod,
				currentPage: 'syllabus',
				learningObjectives: learningObjectives,
				contentList: text,
				hideSectionsList: syllabusPrefs.hideSectionsList == null ? '' : syllabusPrefs.hideSectionsList,
				sortIdList: syllabusPrefs.sortIdList == null ? '' : syllabusPrefs.sortIdList
			]
		} else {
			[
				currentImod: currentImod,
				currentPage: 'syllabus',
				learningObjectives: learningObjectives,
				contentList: text,
				hideSectionsList: '',
				sortIdList: ''
			]
		}
	}

	// Method to update syllabus contentlist
	// to toggle hide show
	def updateSyllabusPrefs() {
		def imod = Imod.get(params.imodId)
		def syllabusPrefs = SyllabusPrefs.findByImod(imod)

		if ( syllabusPrefs != null ) {
			syllabusPrefs.hideSectionsList = params.hideSectionsList
		} else {
			syllabusPrefs = new SyllabusPrefs(
				hideSectionsList: params.hideSectionsList,
				imod: params.imodId
			)
		}

		// save syllabus preferences to DB
		syllabusPrefs.save()

		render (
			[
				value: 'success'
			] as JSON
		)
	}

	// Stores the syllabus sections order
	// in the DB
	def updateSyllabusOrder() {
		def imod = Imod.get(params.imodId)
		def syllabusPrefs = SyllabusPrefs.findByImod(imod)

		syllabusPrefs.sortIdList = params.sortIdList

		// save syllabus preferences to DB
		syllabusPrefs.save()

		render (
			[
				value: 'success'
			] as JSON
		)
	}

	def generatedSyllabus(Long id) {
		final currentImod = Imod.get(id)
		final learningObjectives = LearningObjective.findAllByImod(currentImod)
		final contentList = Content.findAllWhere(imod: currentImod, parentContent: null)
		def count = 0

		def text = '<ul>'

		contentList.each {
			count++
			text += getSubContent(it)
		}

		text += '</ul>'

		if (!count) {
			text = ''
		}

		def syllabusPrefs = SyllabusPrefs.findByImod(currentImod)

		if (syllabusPrefs != null) {
			[
				currentImod: currentImod,
				currentPage: 'syllabus',
				learningObjectives: learningObjectives,
				contentList: text,
				hideSectionsList: syllabusPrefs.hideSectionsList == null ? '' : syllabusPrefs.hideSectionsList,
				sortIdList: syllabusPrefs.sortIdList == null ? '' : syllabusPrefs.sortIdList
			]
		} else {
			[
				currentImod: currentImod,
				currentPage: 'syllabus',
				learningObjectives: learningObjectives,
				contentList: text,
				hideSectionsList: '',
				sortIdList: ''
			]
		}
	}

	 def syllabuspdf(Long id) {
		final currentImod = Imod.get(id)
		final learningObjectives = LearningObjective.findAllByImod(currentImod)
		final contentList = Content.findAllWhere(imod: currentImod, parentContent: null)

		def text = '<ul>'

		contentList.each {
			text += getSubContent(it)
		}

		text += '</ul>'

		renderPdf(
			template: '/courseOverview/syllabus',
			model: [
				currentImod: currentImod,
				currentPage: 'syllabus',
				learningObjectives: learningObjectives,
				contentList: text
			],
			filename: currentImod?.name.replaceAll(' ', '_') + '.pdf'
		)
	}

	private getSubContent(Content current) {
		def text = '<li>' + current.topicTitle

		if (current.subContents != null) {
			text += '<ul>'
			current.subContents.each {
				text += getSubContent(it)
			}
			text += '</ul>'
		}

		text +=  '</li>'

		// returns text
		text
	}
}
