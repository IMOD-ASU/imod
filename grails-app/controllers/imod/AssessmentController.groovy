package imod
import grails.converters.JSON

class AssessmentController {
	def learningObjectiveService
	def springSecurityService

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
		final currentImod = Imod.get(id)

		// finds all the learning objective linked to this imod
		final learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		// select current learning objective
		final currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		// get all of the filters used to find Assessment techniques
		final domainCategories = DomainCategory.list()
		final knowledgeDimensions = KnowledgeDimension.list()
		final learningDomains = LearningDomain.list()
		final assessmentFeedback = AssessmentFeedback.list()
		final selectedActionWordCategory = currentLearningObjective?.actionWordCategory
		final selectedDomainCategory = selectedActionWordCategory?.domainCategory
		final selectedDomain = selectedDomainCategory?.learningDomain
		final content = currentLearningObjective?.contents
		def dimension = []
		if(content != null){
			dimension = content[0]?.dimensions
		}
		def dimensionSize = 0
		if (dimension != null){
			dimensionSize  = dimension.size() - 1
		}

		[
			currentImod: currentImod,
			currentLearningObjective: currentLearningObjective,
			currentPage: 'assessment',
			domainCategories: domainCategories,
			knowledgeDimensions: knowledgeDimensions,
			learningDomains: learningDomains,
			learningObjectives: learningObjectives,
			assessmentFeedback: assessmentFeedback,
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
		final idealAssessmentTechniqueMatch = AssessmentTechnique.withCriteria() {
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
		final extendedAssessmentTechniqueMatch = AssessmentTechnique.withCriteria() {
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

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
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
}
