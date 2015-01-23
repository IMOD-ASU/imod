package imodv6

class PedagogyController {
	def springSecurityService
	static allowedMethods = [
		index: 'GET',
		favoriteTechnique: 'POST',
		assignTechnique: 'POST'
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

		def currentLearningObjective
		// No learning objective is selected, choose first
		if (learningObjectiveID == null) {
			currentLearningObjective = currentImod.learningObjectives.first()
		}
		// Learning objective is selected, get it
		else {
			currentLearningObjective = LearningObjective.get(learningObjectiveID)
		}

		// find all techniques where the Domain Category or the Learning Domain
		// is the same fo the learning objective
		def extendedPedagogyTechniqueMatch = PedagogyTechnique.withCriteria(uniqueResult: true) {
			or {
				domainCategory {
					eq("name", currentLearningObjective.actionWordCategory.domainCategory.learningDomain.toString())
				}
				learningDomain {
					eq("name", currentLearningObjective.actionWordCategory.domainCategory.toString())
				}
			}
		}
		[
			currentLearningObjective: currentLearningObjective,
			currentImod: currentImod,
			currentPage: 'pedagogy',
			learningObjectivesList: learningObjectivesList,
			extendedPedagogyTechniqueMatch: extendedPedagogyTechniqueMatch,
		]
	}

	def favoriteTechnique(Long id, Long objectiveId) {
		// TODO link technique to imod user
		redirect(
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}

	def assignTechnique(Long id, Long objectiveId) {
		// TODO link technique to learning objective
		redirect(
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}
}
