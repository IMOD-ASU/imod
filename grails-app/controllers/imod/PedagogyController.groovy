package imod

import grails.converters.JSON

class PedagogyController {
	def learningObjectiveService
	def springSecurityService

	static allowedMethods = [
		index: 'GET',
		findMatchingTechniques: 'POST'
	]

	/**
	 * index action is called when used click on pedagogy tab from main page.
	 * @param id
	 */
	def index(Long id, Long learningObjectiveID) {
		// get the selected imod
		final currentImod = Imod.get(id)

		// finds all the learning objective linked to this imod
		final learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		// If no learning objective is selected
		// select the first one if it exists
		if (params.learningObjectiveID == null && learningObjectives.size > 0) {
			redirect(
  				controller: 'pedagogy',
  				action: 'index',
  				id:  id,
  				params: [learningObjectiveID: learningObjectives[0].id]
  			)
		}

		// select current learning objective
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		// get all of the filters used to find pedagogy techniques
		final domainCategories = DomainCategory.list()
		final knowledgeDimensions = KnowledgeDimension.list()
		final learningDomains = LearningDomain.list()
		final pedagogyModes = PedagogyMode.list()
		final pedagogyFocuses = PedagogyActivityFocus.list()
		final pedagogyDuration = PedagogyActivityDuration.list()

		final selectedActionWordCategory = currentLearningObjective?.actionWordCategory
		final selectedDomainCategory = selectedActionWordCategory?.domainCategory
		final selectedDomain = selectedDomainCategory?.learningDomain
		final content = currentLearningObjective.contents
		def knowDimensionList = []
		def dimension=[]

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

		[
			currentImod: currentImod,
			currentLearningObjective: currentLearningObjective,
			currentPage: 'pedagogy',
			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
			learningObjectives: learningObjectives,
			pedagogyModes: pedagogyModes,
			pedagogyFocuses: pedagogyFocuses,
			pedagogyDuration: pedagogyDuration,
			selectedDomain: selectedDomain,
			selectedDomainCategory: selectedDomainCategory,
			dimension: dimension,
			dimensionSize: dimensionSize
		]
	}

	def getInstructionalPlan(long id) {
		// get the selected imod
		final currentImod = Imod.get(id)

		// finds all the learning objective linked to this imod
		final learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		def list = []

		learningObjectives.each { it ->
			def obj = [:]
			obj['text'] = it.definition
			obj['id'] = it.id
			obj['techs'] = []

			it.pedagogyTechniques.each { p ->
				obj['techs'].add(p.title)
			}
			list.add(obj)
		}

		render (
			[
				techniques: list
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
	def findMatchingTechniques() {
		final data = request.JSON

		// process strings to longs
		def selectedKnowledgeDimensions = []
		def selectedDomainCategories = []
		def selectedLearningDomains = []
		def allLearningDomains = []

		for (def knowledgeDimension in data.selectedKnowledgeDimensions) {
			selectedKnowledgeDimensions.add(knowledgeDimension.toLong())
		}

		for (def domainCategory in data.selectedDomainCategories) {
			selectedDomainCategories.add(domainCategory.toLong())
		}

		for (def learningDomain in data.selectedLearningDomains) {
			selectedLearningDomains.add(learningDomain.toLong())
		}

		LearningDomain.list().each {
			allLearningDomains.add(it.id)
		}

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		def idealPedagogyTechniqueMatch

		if (selectedKnowledgeDimensions.size() && selectedDomainCategories.size() && selectedLearningDomains.size()) {
			// find all technique where both the knowledge dimension and the domain category match
			idealPedagogyTechniqueMatch = PedagogyTechnique.withCriteria {
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
					domainCategory {
						'in' ('id', selectedDomainCategories)
					}
					learningDomain {
						'in' ('id', selectedLearningDomains)
					}
				}
				resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
			}
		} else {
			idealPedagogyTechniqueMatch = []
		}

		def extendedPedagogyTechniqueMatch

		if (selectedLearningDomains.size()) {
			// find all technique that are not ideal, but have the learning domain
			extendedPedagogyTechniqueMatch = PedagogyTechnique.withCriteria {
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
				}
				resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
			}
		} else {
			extendedPedagogyTechniqueMatch = []
		}

		extendedPedagogyTechniqueMatch = (idealPedagogyTechniqueMatch + extendedPedagogyTechniqueMatch) - extendedPedagogyTechniqueMatch.intersect(idealPedagogyTechniqueMatch)

		def userPedagogyTechniqueMatch = PedagogyTechnique.withCriteria {
			and {
				users {
					eq('id', currentUser.id)
				}
				learningDomain {
					'in' ('id', allLearningDomains)
				}

			}
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}
		final favoriteTechniques = currentUser.favoriteTechnique.id
		def stringfavoriteTechniques = []
		// Convert int to string
		for (def favoriteTechnique in favoriteTechniques) {
			stringfavoriteTechniques.add(favoriteTechnique.toString())
		}

		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())
		final LOPedagogyTechniques = currentLearningObjective?.pedagogyTechniques.id
		def stringLOPedagogyTechniques = []
		// Convert int to string
		for (def LOPedagogyTechnique in LOPedagogyTechniques) {
			stringLOPedagogyTechniques.add(LOPedagogyTechnique.toString())
		}
		idealPedagogyTechniqueMatch.sort {
			it.title.toUpperCase()
		}
		extendedPedagogyTechniqueMatch.sort {
			it.title.toUpperCase()
		}

		render(
			[
				idealPedagogyTechniqueMatch: idealPedagogyTechniqueMatch,
				extendedPedagogyTechniqueMatch: extendedPedagogyTechniqueMatch,
				userTechniques: userPedagogyTechniqueMatch,
				favoriteTechniques: stringfavoriteTechniques,
				LOPedagogyTechniques: stringLOPedagogyTechniques
			] as JSON
		)
	}

	/**
	 * Find assigned techniques to a given Learning Objective
	 */
	def findAssignedTechniques() {
		final data = request.JSON
		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())
		final LOPedagogyTechniques = currentLearningObjective.pedagogyTechniques.title
		def stringLOPedagogyTechniques = []

		// Convert int to string
		for (def LOPedagogyTechnique in LOPedagogyTechniques) {
			stringLOPedagogyTechniques.add(LOPedagogyTechnique.toString())
		}

		render(
				[
					LOPedagogyTechniques: stringLOPedagogyTechniques,
					currentLearningObjectiveID: data.learningObjectiveID,
					currentLearningObjective: currentLearningObjective.toString()
				] as JSON
			  )
	}

	/**
	 * Print instructional plan
	 */
	def instructionalPlan(Long id) {
		final currentImod = Imod.get(id)
		final learningObjectives = LearningObjective.findAllByImod(currentImod)
		def arrayOfLOPedagogyTechniques = []
		for (def learningObjective in learningObjectives) {
			def currentLearningObjective = LearningObjective.findById(learningObjective.id)
			final LOPedagogyTechniques = currentLearningObjective.pedagogyTechniques.title
			def stringLOPedagogyTechniques = []

			// Convert int to string
			for (def LOPedagogyTechnique in LOPedagogyTechniques) {
				stringLOPedagogyTechniques.add(LOPedagogyTechnique.toString())
			}
			arrayOfLOPedagogyTechniques.add(stringLOPedagogyTechniques)
		}

		[
			learningObjectives: learningObjectives,
			arrayOfLOPedagogyTechniques: arrayOfLOPedagogyTechniques
		]
	}
	def handler (Exception e) {
		print (e)
		def idealPedagogyTechniqueMatch = []
		final data = request.JSON
		def selectedLearningDomains = []
		def allLearningDomains =[(94).toLong(), (95).toLong(), (96).toLong()]

		for (def learningDomain in data.selectedLearningDomains) {
			selectedLearningDomains.add(learningDomain.toLong())
		}

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		// find all technique that are not ideal, but have the learning domain
		def extendedPedagogyTechniqueMatch = PedagogyTechnique.withCriteria {
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
			}
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}
		extendedPedagogyTechniqueMatch = (idealPedagogyTechniqueMatch + extendedPedagogyTechniqueMatch) - extendedPedagogyTechniqueMatch.intersect(idealPedagogyTechniqueMatch)

		def userPedagogyTechniqueMatch = PedagogyTechnique.withCriteria {
			and {
					users {
						eq('id', currentUser.id)
					}
				learningDomain {
					'in' ('id', allLearningDomains)
				}

			}
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}
		final favoriteTechniques = currentUser.favoriteTechnique.id
		def stringfavoriteTechniques = []
		// Convert int to string
		for (def favoriteTechnique in favoriteTechniques) {
			stringfavoriteTechniques.add(favoriteTechnique.toString())
		}

		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())
		final LOPedagogyTechniques = currentLearningObjective?.pedagogyTechniques.id
		def stringLOPedagogyTechniques = []
		// Convert int to string
		for (def LOPedagogyTechnique in LOPedagogyTechniques) {
			stringLOPedagogyTechniques.add(LOPedagogyTechnique.toString())
		}
		extendedPedagogyTechniqueMatch.sort {
			it.title.toUpperCase()
		}

		render(
			[
				idealPedagogyTechniqueMatch: idealPedagogyTechniqueMatch,
				extendedPedagogyTechniqueMatch: extendedPedagogyTechniqueMatch,
				userTechniques: userPedagogyTechniqueMatch,
				favoriteTechniques: stringfavoriteTechniques,
				LOPedagogyTechniques: stringLOPedagogyTechniques
			] as JSON
		)
	}
}
