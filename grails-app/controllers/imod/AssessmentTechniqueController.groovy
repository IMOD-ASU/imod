
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


	def assessmentplan() {
		final assessmentTechInstance = AssessmentTechnique.findAllByAssigncheck(true)
		final domainCategories = DomainCategory.list()
		final knowledgeDimensions = KnowledgeDimension.list()
		final learningDomains = LearningDomain.list()
		final assessmentFeedback = AssessmentFeedback.list()

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
		final assessmentTechInstance1 = AssessmentTechnique.findAllByAssigncheck(true)

		String [] knowledgedimensions = AssessmentTechnique.get(id).knowledgeDimension;

		render (
			[
				assessmentTechnique: AssessmentTechnique.get(id),
				learningDomain: LearningDomain.findById(AssessmentTechnique.get(id).learningDomain[0].id).toString(),
				domainCategory: DomainCategory.findById(AssessmentTechnique.get(id).domainCategory[0].id).toString(),
				knowledgeDimension:knowledgedimensions.join(",")
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
			newTechnique.knowledgeDimension.clear()
		}

		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription
		newTechnique.procedure = params.assessmentProcedure
		newTechnique.duration= params.duration
		newTechnique.difficulty = params.assessmentDifficulty
		newTechnique.whenToCarryOut = params.assessmentTime
		newTechnique.sources = params.sources
		newTechnique.assigncheck = params.assignedToLearningObjective as boolean
		newTechnique.favcheck = params.favoriteTechnique as boolean

		newTechnique.assessmentFeedback= AssessmentFeedback.findByName(params.assessmentFeedback)

		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(learningObjectiveID)
		)
		newTechnique.addToDomainCategory(
			DomainCategory.findByName(params.domainCategory)
		)

		String[] kD = params.knowledgeDimension.split(",");

		if (kD != null) {
			for(int i=0; i < kD.length; i++) {

				if (kD[i]!=null) {
					newTechnique.addToKnowledgeDimension(
					KnowledgeDimension.findByDescription(kD[i]))
				}

			}
		}

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
