package imodv6

class LearningObjectiveController {
	def performance(Long id) {
		// get relevant imod
		def imodInstance = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = imodInstance.learningObjectives.asList()
		[
			imodInstance: imodInstance,
			learningObjectivesList: learningObjectivesList
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

	def create(Long id) {
		// get the IMOD that this learning objective will be associated with
		def imodInstance = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(imod: imodInstance)
		// add the learning objective to the collection of learning objectives in the imod
		imodInstance.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod (and in theory the learning objective)
		imodInstance.save(flush: true)
		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: "performance",
			id: id
		)
	}
}
