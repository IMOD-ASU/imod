package imod
import grails.converters.JSON

class PedagogyController {

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
		def currentImod = Imod.get(id)

		// finds all the learning objective linked to this imod
		def learningObjectives = LearningObjective.findAllByImod(currentImod)

		// Search for selected learning objective
		// if there are no learning objectives set null
		// if there is no selected learning objective, pick first
		// otherwise get the objective
		def currentLearningObjective
		def objectivesForCurrentImod = currentImod.learningObjectives
		if (objectivesForCurrentImod.size() < 1) {
			currentLearningObjective = null
		}
		else if (learningObjectiveID == null) {
			currentLearningObjective = objectivesForCurrentImod.first()
		}
		else {
			currentLearningObjective = objectivesForCurrentImod.find { it.id == learningObjectiveID }
		}

		// get all of the filters used to find pedagogy techniques
		def domainCategories = DomainCategory.list()
		def knowledgeDimensions = KnowledgeDimension.list()
		def learningDomains = LearningDomain.list()

		[
			currentImod: currentImod,
			currentLearningObjective: currentLearningObjective,
			currentPage: 'pedagogy',
			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
			learningObjectives: learningObjectives,
		]
	}

	/**
	 * Finds ideal and extended matches based on learning domains and knowledge dimensions
	 * expects params
	 * - knowledgeDimesions: name of each selected dimension
	 * - domain category: name of each selected category
	 * - learning domain: name of each selected domain
	 */
	def findMatchingTechniques() {
		def data = request.JSON

		// process strings to longs
		def selectedKnowledgeDimensions = []
		def selectedDomainCategories = []
		def selectedLearningDomains = []
		for (def knowledgeDimesion in data.selectedKnowledgeDimensions) {
			selectedKnowledgeDimensions.add(knowledgeDimesion.toLong())
		}
		for (def domainCategory in data.selectedDomainCategories) {
			selectedDomainCategories.add(domainCategory.toLong())
		}
		for (def learningDomain in data.selectedLearningDomains) {
			selectedLearningDomains.add(learningDomain.toLong())
		}

		// find all technique where both the knowledge dimension and the domain category match
		def idealPedagogyTechniqueMatch = PedagogyTechnique.withCriteria() {
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

		// find all technique that are not ideal, but have the learning domain
		def extendedPedagogyTechniqueMatch = PedagogyTechnique.withCriteria() {
			and {
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
		}

		render(
			[
				idealPedagogyTechniqueMatch: idealPedagogyTechniqueMatch,
				extendedPedagogyTechniqueMatch: extendedPedagogyTechniqueMatch
			] as JSON
		)
	}
}