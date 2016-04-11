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

			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
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

		DateTimeFormatter fmt = DateTimeFormat.forPattern('MM/dd/yyyy HH:mm')
		DateTime sDate = fmt.parseDateTime(params.startDate)
		DateTime eDate = fmt.parseDateTime(params.endDate)

		def title = params.title
		def learningObjectiveID = params.lo.toLong()

		def learnO = params.learnO
		def knowD = params.knowD

		def enviro = params.enviro
		def workTime = params.workTime
		def notes = params.notes

		def event = new ScheduleEvent(
			title: title,
			startDate: sDate,
			endDate: eDate,

			learnO: learnO,
			knowD: knowD,

			enviro: enviro,
			workTime: workTime,
			notes: notes
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

		def events = currentLO.withCriteria {
           			scheduleEvents {
           				gte('startDate', startDate.toDate())
					    lte('endDate', endDate.toDate())
           			}
           		}

		render (
			[
				events: events.scheduleEvents[0]
			] as JSON
		)

	}

	/**
	 * Finds ideal and extended matches based on learning domains and knowledge dimensions
	 * expects params
	 * - knowledgeDimesions: name of each selected dimension
	 * - domain category: name of each selected category
	 * - learning domain: name of each selected domain
	 */

	 /*
	def findMatchingTechniques() {
		final data = request.JSON

		// process strings to longs
		def selectedKnowledgeDimensions = []
		def selectedDomainCategories = []
		def selectedLearningDomains = []

		for (def knowledgeDimension in data.selectedKnowledgeDimensions) {
			selectedKnowledgeDimensions.add(knowledgeDimension.toLong())
		}
		for (def domainCategory in data.selectedDomainCategories) {
			selectedDomainCategories.add(domainCategory.toLong())
		}
		for (def learningDomain in data.selectedLearningDomains) {
			selectedLearningDomains.add(learningDomain.toLong())
		}

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		// find all technique where both the knowledge dimension and the domain category match
		final idealAssessmentTechniqueMatch = AssessmentTechnique.withCriteria {
			and {
				or {
					eq('isAdmin', true)
					users {
						eq('id', currentUser.id)
					}
				}
				knowledgeDimension {
					'in' ('id', selectedKnowledgeDimensions)
				}
				or {
					domainCategory {
						'in' ('id', selectedDomainCategories)
					}
					learningDomain {
						'in' ('id', selectedLearningDomains)
					}
				}
			}
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}

		// find all technique that are not ideal, but have the learning domain
		final extendedAssessmentTechniqueMatch = AssessmentTechnique.withCriteria {
			and {
				or {
					eq('isAdmin', true)
					users {
						eq('id', currentUser.id)
					}
				}
				learningDomain {
					'in' ('id', selectedLearningDomains)
				}
				not {
					and {
						knowledgeDimension {
							'in' ('id', selectedKnowledgeDimensions)
						}
						or {
							domainCategory {
								'in' ('id', selectedDomainCategories)
							}
							learningDomain {
								'in' ('id', selectedLearningDomains)
							}
						}
					}
				}
			}
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}

		final favoriteTechniques = currentUser.favoriteAssessmentTechnique.id
		def stringfavoriteTechniques = []
		//Convert int to string
		for (def favoriteTechnique in favoriteTechniques) {
			stringfavoriteTechniques.add(favoriteTechnique.toString())
		}

		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())
		final LOAssessmentTechniques = currentLearningObjective.assessmentTechniques.id
		def stringLOAssessmentTechniques = []
		//Convert int to string
		for (def LOAssessmentTechnique in LOAssessmentTechniques) {
			stringLOAssessmentTechniques.add(LOAssessmentTechnique.toString())
		}

		render(
			[
				idealAssessmentTechniqueMatch: idealAssessmentTechniqueMatch,
				extendedAssessmentTechniqueMatch: extendedAssessmentTechniqueMatch,
				favoriteTechniques: stringfavoriteTechniques,
				LOAssessmentTechniques: stringLOAssessmentTechniques
			] as JSON
		)
	}
	*/

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
