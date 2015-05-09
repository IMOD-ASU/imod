package imod
import grails.converters.JSON

class AssessmentTechniqueController {
	def springSecurityService
	def learningObjectiveService

	static allowedMethods = [
		create: 'POST',
		display: 'GET',
		assessmentplan: 'POST'
	]


	def assessmentplan(Long id, Long learningObjectiveID) {
		def assessmentTechInstance = AssessmentTechnique.findAllByAssigncheck(true)
		def domainCategories = DomainCategory.list()
		def knowledgeDimensions = KnowledgeDimension.list()
		def learningDomains = LearningDomain.list()
		def assessmentFeedback = AssessmentFeedback.list()

		println(assessmentTechInstance)
		render (
			[
				assessmentTechInstance: assessmentTechInstance,
				domainCategories: domainCategories,
				knowledgeDimensions: knowledgeDimensions,
				learningDomains: learningDomains,
				assessmentFeedback: assessmentFeedback,
			] as JSON
		)
	}


	/**
	 * get info on a selected technique
	 */
	def display(Long id) {
		def assessmentTechInstance1 = AssessmentTechnique.findAllByAssigncheck(true)
		println(assessmentTechInstance1.id+assessmentTechInstance1.title)
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
		newTechnique.assigncheck = params.assignedToLearningObjective as boolean
		newTechnique.favcheck = params.favoriteTechnique as boolean

		newTechnique.assessmentFeedback= AssessmentFeedback.findByName(params.assessmentFeedback1)

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

		// This checks when a technique is assigned to a learning objective

		if (params.assigncheck == true) {
			// get current user object
			def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

			// add the technique to the user's favorite list
			currentLearningObjective.addToAssessmentTechniques(newTechnique)

			// store relationship
			currentLearningObjective.save()
		}

		// This checks when a technique is favoritized  to by a user
		if (params.favcheck == true) {
			// get current user object
			def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

			// add the technique to the user's favorite list
			currentUser.addToFavoriteAssessmentTechnique(newTechnique)

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
		newTechnique.assigncheck = params.assignedToLearningObjective as boolean
		newTechnique.favcheck = params.favoriteTechnique as boolean

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

		// This checks when a technique is assigned to a learning objective
		if (params.assigncheck == true) {
			// get current user object
			def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

			// add the technique to the user's favorite list
			currentLearningObjective.addToAssessmentTechniques(newTechnique)

			// store relationship
			currentLearningObjective.save()
		}

		// This checks when a technique is favoritized  to by a user
		if (params.favcheck == true) {
			// get current user object
			def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

			// add the technique to the user's favorite list
			currentUser.addToFavoriteAssessmentTechnique(newTechnique)

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
