package imod

class AssessmentTechniqueController {
	def springSecurityService

	static allowedMethods = [
		create: 'POST',
		favoriteByUser: 'POST',
		assignToObjective: 'POST'
	]

	/**
	 * creates a new Assessment Technique
	 */
	def create(Long id, Long learningObjectiveID) {
		def newTechnique = new AssessmentTechnique()
		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription

		// Store relationships
		 //newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)

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


		// persist new technique to database
		newTechnique.save()

		if(params.assignedToLearningObjective != null) {
			// get current user object
			def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

			// add the technique to the user's favorite list
			currentLearningObjective.addToAssessmentTechniques(newTechnique)

			// store relationship
			currentLearningObjective.save()
		}

		if(params.favoriteTechnique != null) {
			// get current user object
			def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

			// add the technique to the user's favorite list
			currentUser.addToFavoriteTechnique(newTechnique)

			// store relationship
			currentUser.save()
		}

		redirect(
			controller: 'assessment',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	def favoriteByUser(Long id, Long learningObjectiveID) {
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		// get the selected technique
		def currentTechnique = AssessmentTechnique.findById(params.techniqueID)

		// add the technique to the user's favorite list
		currentUser.addToFavoriteTechnique(currentTechnique)

		// store relationship
		currentUser.save()

		redirect(
			controller: 'assessment',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	def assignToObjective(Long id, Long learningObjectiveID) {
		// get current user object
		def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

		// get the selected technique
		def currentTechnique = AssessmentTechnique.findById(params.techniqueID)

		// add the technique to the user's favorite list
		currentLearningObjective.addToAsssessmentTechniques(currentTechnique)

		// store relationship
		currentLearningObjective.save()

		redirect(
			controller: 'assessment',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}
}
