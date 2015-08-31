package imod
import grails.converters.JSON

class PedagogyController {
	def learningObjectiveService

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

		// select current learning objective
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		// get all of the filters used to find pedagogy techniques
		final domainCategories = DomainCategory.list()
		final knowledgeDimensions = KnowledgeDimension.list()
		final learningDomains = LearningDomain.list()
		final pedagogyModes = PedagogyMode.list()
		final pedagogyFocuses = PedagogyActivityFocus.list()
		final selectedActionWordCategory = currentLearningObjective.actionWordCategory
		final selectedDomainCategory = selectedActionWordCategory?.domainCategory
		final selectedDomain = selectedDomainCategory?.learningDomain
		final content = currentLearningObjective.contents
		def dimension = content[0]?.dimensions
		def dimensionSize = 0
		if (dimension != null){
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
			selectedDomain:selectedDomain,
			selectedDomainCategory:selectedDomainCategory,
			dimension:dimension,
			dimensionSize:dimensionSize
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

		// find all technique where both the knowledge dimension and the domain category match
		final idealPedagogyTechniqueMatch = PedagogyTechnique.withCriteria() {
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
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}

		// find all technique that are not ideal, but have the learning domain
		final extendedPedagogyTechniqueMatch = PedagogyTechnique.withCriteria() {
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
			resultTransformer org.hibernate.Criteria.DISTINCT_ROOT_ENTITY
		}

		render(
			[
				idealPedagogyTechniqueMatch: idealPedagogyTechniqueMatch,
				extendedPedagogyTechniqueMatch: extendedPedagogyTechniqueMatch
			] as JSON
		)
	}
}
