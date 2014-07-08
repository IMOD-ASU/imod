package imodv6

class LearningObjectiveController {
	def performance(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def content(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def condition(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def criteria(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def create(Long id) {
		params.remove('owner')
		params.remove('owner.id')
		params.put(
			'owner.id',
			id
		)
		def learningObjectiveInstance = new LearningObjective(params)
		learningObjectiveInstance.save(
			flush: true
		)
		redirect(
			action: "performance",
			id: id
		)
	}
}
