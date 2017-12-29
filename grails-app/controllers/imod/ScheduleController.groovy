package imod

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class ScheduleController {
	def learningObjectiveService
	def springSecurityService

	static allowedMethods = [
		index: 'GET',
		findMatchingTechniques: 'POST',
		deleteEvent: 'POST',
		addEvent: 'POST'
	]

	def index(Long id, Long learningObjectiveID) {
		// get the selected imod
		final currentImod = Imod.get(id)
		// finds all the learning objective linked to this imod
		final learningObjectives = learningObjectiveService.getAllByImod(currentImod)
		// If no learning objective is selected
		// select the first one if it exists
		if (params.learningObjectiveID == null && learningObjectives.size > 0) {
			redirect(
				controller: 'schedule',
				action: 'index',
				id:  id,
				params: [learningObjectiveID: learningObjectives[0].id]
			)
		}
		// select current learning objective
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		final pedagogyFocuses = PedagogyActivityFocus.list()

		// get all of the filters used to find Assessment techniques
		final domainCategories = DomainCategory.list()
		final knowledgeDimensions = KnowledgeDimension.list()
		final learningDomains = LearningDomain.list()
		final assessmentFeedback = AssessmentFeedback.list()
		final selectedActionWordCategory = currentLearningObjective?.actionWordCategory
		final selectedDomainCategory = selectedActionWordCategory?.domainCategory
		final selectedDomain = selectedDomainCategory?.learningDomain
		final content = currentImod?.contents
		final activityTypes = ['Pedagogy', 'Assessment']
		final taskEnvironment = ['In-class', 'Out-of-class']

		def knowDimensionList = []
		def dimension = []
		if (content != null) {
			content.each {
				knowDimensionList.push(it.dimensions)
			}
			// merge multiple lists into one
			dimension = knowDimensionList.flatten()
			// remove duplicates
			dimension = dimension.unique { a, b ->
				a <=> b
			}
		}

		def dimensionSize = 0
		if (dimension != null) {
			dimensionSize  = dimension.size() - 1
		}

		//render responseData as JSON
		def startDate1 = []
		def endDate1 = []
		def creditHours1 = -1
		def timeRatio1 = 'no time'
		def currName1 = 'noName'

		def taskID = -1

		startDate1 = Imod.get(id).schedule.startDate
		endDate1 = Imod.get(id).schedule.endDate
		creditHours1 = Imod.get(id).creditHours
		timeRatio1 = Imod.get(id).timeRatio
		currName1 = Imod.get(id).name

		[
			pedagogyFocuses: pedagogyFocuses,

			currentImod: Imod.get(id),
			currentLearningObjective: currentLearningObjective,
			currentPage: 'schedule',
			startDate1: startDate1,
			endDate1: endDate1,
			creditHours1: creditHours1,
			timeRatio1: timeRatio1,
			learningObjectives: learningObjectives,
			currName1: currName1,

			taskID: taskID,

			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
			activityTypes: activityTypes,
			taskEnvironment: taskEnvironment,
			assessmentFeedback: assessmentFeedback,
			selectedDomain: selectedDomain,
			selectedDomainCategory: selectedDomainCategory,
			dimension: dimension,
			dimensionSize: dimensionSize

		]

		/*
			render (
				[
					startDate: startDate1,
					startDate1: startDate1,
					endDate1: endDate1,
					creditHours1: creditHours1,
					timeRatio1: timeRatio1
				]
			)
		*/

	}

	/**
	 * Add an event for a particular learning objective
	 */
	def addEvent() {

		//DateTimeFormatter fmt = DateTimeFormat.forPattern('MM/dd/yyyy HH:mm')
		DateTimeFormatter fmt = DateTimeFormat.forPattern('MM/dd/yyyy')

		def stDate = params.startDate_month + '/' + params.startDate_day + '/' + params.startDate_year
		def enDate = params.endDate_month + '/' + params.endDate_day + '/' + params.endDate_year

		DateTime sDate = fmt.parseDateTime(stDate)
		DateTime eDate = fmt.parseDateTime(enDate)

		//DateTime sDate = fmt.parseDateTime(params.startDate)
		//DateTime eDate = fmt.parseDateTime(params.endDate)

		def title = params.title
		def learningObjectiveID = params.lo.toLong()

		//this 'learnO' is currently the stored value of 'Link to Online Resource' for each new event. Due to time constraints didn't have time to change this and safely test/demo and deploy.
		def learnO = params.learnO
		def knowD = params.knowD

		def enviro = params.enviro
		def workTime = params.workTime
		def notes = params.notes

		def lo = params.lo
		// def activity = params.type_of_activity_field
		def startdate_day = params.startDate_day
		def startdate_month = params.startDate_month
		def startdate_year = params.startDate_year
//		def startdate_day = params.startDate_day
//		def startdate_day = params.startDate_day
//		def startdate_day = params.startDate_day
//		def startdate_day = params.startDate_day

		if ( params.edit == 'yes' ) {
			def id = params.id
			//def event1 = ScheduleEvent.findById(id)
			def event1 = ScheduleEvent.get(id)

			final currentImod = Imod.get(params.imodId)
			final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

			if ( event1 != null ) {

				currentLearningObjective.removeFromScheduleEvents(event1)
				event1.delete()
			}

//			redirect(
//				controller: 'Schedule',
//				action: 'index',
//				id: params.imodId,
//				params: [learningObjectiveID: learningObjectiveID]
//			)
		}

		def event = new ScheduleEvent(
			title: title,
			startDate: sDate,
			endDate: eDate,
			learnO: learnO,
			knowD: knowD,
			enviro: enviro,
			workTime: workTime,
			notes: notes,
			lo: lo,
			startdate_day : startdate_day,
			startdate_month : startdate_month,
			startdate_year : startdate_year,
		)
		event.save()

		final currentImod = Imod.get(params.imodId)
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		currentLearningObjective.addToScheduleEvents(event)

		redirect(
			controller: 'Schedule',
			action: 'index',
			id: params.imodId,
			params: [learningObjectiveID: learningObjectiveID]
		)
	}

	def getEvents() {
		def loId = params.learningObjectiveID
		def currentLO = LearningObjective.get(loId)

		DateTimeFormatter fmt = DateTimeFormat.forPattern('yyyy-MM-dd')
		DateTime startDate = fmt.parseDateTime(params.startDate)
		DateTime endDate = fmt.parseDateTime(params.endDate)

		def lo = loId.toString()

		def events = currentLO.withCriteria {
           			scheduleEvents {
           				//or {
           					and {
           						gt('startDate', startDate.toDate())
							    lt('startDate', endDate.toDate())
           					}
           					and {
           						gt('endDate', startDate.toDate())
							    lt('endDate', endDate.toDate())
           					}
							and {
								eq('lo', lo)
							}

           				//}

           			}
           		}


		//println(events.scheduleEvents[0])
		render (
			[
				events: events.scheduleEvents[0]
			] as JSON
		)

	}

	/**
	 * Delete an event for a particular learning objective
	 */
	def deleteEvent() {

		def learningObjectiveID = params.lo.toLong()
		def id = params.id

		//def event1 = ScheduleEvent.findById(id)
		def event1 = ScheduleEvent.get(id)

		final currentImod = Imod.get(params.imodId)
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		if ( event1 != null ) {

			currentLearningObjective.removeFromScheduleEvents(event1)
		    		event1.delete()
		}

		redirect(
			controller: 'Schedule',
			action: 'index',
			id: params.imodId,
			params: [learningObjectiveID: learningObjectiveID]
		)
	}

	def findImodScheduleInfo() {
		render (
			[
			startDate: startDate1,
			startDate1: startDate1,
			endDate1: endDate1,
			creditHours1: creditHours1,
			timeRatio1: timeRatio1
			]
		) as JSON
	}

	def schedule2() {
		render(view: 'schedule2',  model: [name: 'John Doe'])
	}

	def testData() {
			def result = [:]
			result['name'] = 'Sales'
			result['type'] = 'bar'
			result['data'] = [5, 20, 45, 10, 10, 20]
			[jsonTest: result as JSON]
	}
}
