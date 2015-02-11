package imod

class PedagogyTechniqueController {

	static allowedMethods = [
		create: 'POST',
		favoriteByUser: 'POST',
		assignToObjective: 'POST'
	]

	/**
	 * creates a new Pedagogy Technique
	 */
	def create(Long id, Long learningObjectiveID) {
		def newTechnique = new PedagogyTechnique()
		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription

		// Store relationships
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)
		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(learningObjectiveID)
		)
		newTechnique.addToDomainCategory(
			DomainCategory.findByName(params.domainCategory)
		)
		newTechnique.addToKnowledgeDimension(
			KnowledgeDimension.findByDescription(params.knowledgeDimension)
		)
		newTechnique.addToLearningDomain(
			LearningDomain.findByName(params.learningDomain)
		)
		newTechnique.addToActivityFocus(
			PedagogyActivityFocus.findByFocus(params.pedagogyFocus)
		)

		// persist new technique to database
		newTechnique.save()

		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	def favoriteByUser(Long id, Long learningObjectiveID) {
		// TODO link technique to imod user
		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	def assignToObjective(Long id, Long learningObjectiveID) {
		// TODO link technique to learning objective
		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}
}
