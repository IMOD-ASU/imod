package imod
import grails.converters.JSON

class AssessmentTechniqueController {
	def springSecurityService

	static allowedMethods = [
		create: 'POST',
		display: 'GET'
	]

	/**
	 * get info on a selected technique
	 */
	def display(Long id) {
		render (
			[
				assessmentTechnique: AssessmentTechnique.get(id)
			] as JSON
		)
	}

	def cancel(Long id, Long learningObjectiveID) {
		redirect(
			controller: 'assessment',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	def save1(Long id, Long learningObjectiveID) {
		def newTechnique = new AssessmentTechnique()


		if (params.techniqueId1) {
			newTechnique = AssessmentTechnique.get(params.techniqueId1)

		}

		// Store text fields for the display modal
		newTechnique.title = params.title1
		newTechnique.description = params.description1
		newTechnique.procedure = params.procedure1
		newTechnique.duration = params.duration1

		newTechnique.assessmentFeedback= AssessmentFeedback.findByName(params.assessmentFeedback)

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

	/**
	 * creates a new Assessment Technique
	 */
	def save(Long id, Long learningObjectiveID) {
		def newTechnique = new AssessmentTechnique()

		if (params.techniqueId) {
			newTechnique = AssessmentTechnique.get(params.techniqueId)

		}

		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.description
		newTechnique.procedure = params.procedure
		newTechnique.duration= params.duration


		newTechnique.assessmentFeedback= AssessmentFeedback.findByName(params.assessmentFeedback)


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
}
