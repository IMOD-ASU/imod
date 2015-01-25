package imodv6
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

		// get all the learning domains
		def learningDomainList = LearningDomain.list();

		// finds all the learning objective linked to this imod
		def learningObjectivesList = LearningObjective.findAllByImod(currentImod)

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

		[
			currentLearningObjective: currentLearningObjective,
			currentImod: currentImod,
			currentPage: 'pedagogy',
			learningObjectivesList: learningObjectivesList,
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
		// find all technique where both the knowledge dimension and the domain category match
		def idealPedagogyTechniqueMatch = PedagogyTechnique.withCriteria(uniqueResult: true) {
			and {
				knowledgeDimension {
					'in' ('name', params.knowledgeDimensions)
				}
				or {
					domainCategory {
						'in' ('name', params.domainCategories)
					}
					learningDomain {
						'in' ('name', params.learningDomains)
					}
				}
			}
		}

		render(
			[
				idealPedagogyTechniqueMatch: idealPedagogyTechniqueMatch
			] as JSON
		)
	}
}
