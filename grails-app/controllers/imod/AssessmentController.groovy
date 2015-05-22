package imod
import grails.converters.JSON

class AssessmentController {
	def learningObjectiveService

	static allowedMethods = [
		index: 'GET',
		findMatchingTechniques: 'POST'
	]

	/**
	 * index called when Assessment tab loads
	 * @param id
	 */
	def index(Long id, Long learningObjectiveID) {

		// get the selected imod
		def currentImod = Imod.get(id)

		// finds all the learning objective linked to this imod
		def learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		// select current learning objective
		def currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		// get all of the filters used to find Assessment techniques
		def domainCategories = DomainCategory.list()
		def knowledgeDimensions = KnowledgeDimension.list()
		def learningDomains = LearningDomain.list()
		def assessmentFeedback = AssessmentFeedback.list()

		[
			currentImod: currentImod,
			currentLearningObjective: currentLearningObjective,
			currentPage: 'assessment',
			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
			learningObjectives: learningObjectives,
			assessmentFeedback: assessmentFeedback,
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
		def idealAssessmentTechniqueMatch = AssessmentTechnique.withCriteria() {
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
		def extendedAssessmentTechniqueMatch = AssessmentTechnique.withCriteria() {
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
				idealAssessmentTechniqueMatch: idealAssessmentTechniqueMatch,
				extendedAssessmentTechniqueMatch: extendedAssessmentTechniqueMatch
			] as JSON
		)
	}
}
