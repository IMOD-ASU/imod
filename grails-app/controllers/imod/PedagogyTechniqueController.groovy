package imod

class PedagogyTechniqueController {

	static allowedMethods = [
		create: 'POST',
		save: 'POST',
		favoriteByUser: 'POST',
		assignToObjective: 'POST'
	]

	/**
	 * creates a new Pedagogy Technique
	 */
	def create(Long id, Long objectiveId) {
		def newTechnique = new PedagogyTechnique()
		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription

		// Store relationships
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)
		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(objectiveId)
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
		newTechnique.addPedagogyActivityFocus(
			PedagogyActivityFocus.findByFocus(params.pedagogyFocus)
		)

		// persist new technique to database
		newTechnique.save()

		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}

	def favoriteByUser(Long id, Long objectiveId) {
		// TODO link technique to imod user
		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}

	def assignToObjective(Long id, Long objectiveId) {
		// TODO link technique to learning objective
		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}
}
