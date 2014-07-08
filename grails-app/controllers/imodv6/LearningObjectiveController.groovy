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
		// get the IMOD that this learning objective will be associated with
		def imodInstance = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(imod: imodInstance)
		// add the learning objective to the collection of learning objectives in the imod
		imodInstance.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod (and in theory the learning objective)
		imodInstance.save(flush: true)
		// redirects to the performance page to allow for newly created imod to be edited
		redirect(
			action: "performance",
			id: id
		)
	}
}
