package imodv6

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
		// FIXME replace hardcoded values with domain ids
		newTechnique.title = 'new technique'
		newTechnique.pedagogyMode = PedagogyMode.findByName('online')
		newTechnique.addToDomainCategory(DomainCategory.first())
		newTechnique.addToKnowledgeDimension(KnowledgeDimension.first())
		newTechnique.addToLearningDomain(LearningDomain.first())
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

	def save(Long id, Long objectiveId) {
		def currentImod = Imod.get(id)
		def pedagogyTechniqueInstance = new PedagogyTechnique(params)

		if (!pedagogyTechniqueInstance.save()) {
			render(
				view: 'create',
				model: [
					pedagogyTechniqueInstance: pedagogyTechniqueInstance
				]
			)
			return
		}

		flash.message = message(
			code: 'default.created.message',
			args: [
				message(
					code: 'imod.label',
					default: 'Imod'
				),
				currentImod
			]
		)

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
