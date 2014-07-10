package imodv6

class LearningObjectiveController {
	def performance(Long id) {
		[
			imodInstance: Imod.get(id)
		]
	}

	def content(Long id) {
		[
			imodInstance: Imod.get(id)
		]
	}

	def condition(Long id) {
		[
			imodInstance: Imod.get(id)
		]
	}

	def criteria(Long id) {
		[
			imodInstance: Imod.get(id)
		]
	}
}
