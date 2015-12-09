
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

	def favorites() {

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		final favoriteTechniques = currentUser.favoriteAssessmentTechnique

		 render(
			[
				assessmentTechniques: favoriteTechniques
			] as JSON
		)
	}


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
		String [] knowledgedimensions = AssessmentTechnique.get(id).knowledgeDimension
		String [] learningdomains = AssessmentTechnique.get(id).learningDomain
		String [] domaincategories = AssessmentTechnique.get(id).domainCategory

		render (
			[
				assessmentTechnique: AssessmentTechnique.get(id),
				knowledgeDimension:knowledgedimensions.join(','),
				learningDomains: learningdomains.join(','),
				domainCategories: domaincategories.join(',')
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
			newTechnique.learningDomain.clear()
			newTechnique.domainCategory.clear()


		}

		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription
		newTechnique.procedure = params.assessmentProcedure
		newTechnique.duration= params.duration
		newTechnique.difficulty = params.assessmentDifficulty
		newTechnique.whenToCarryOut = params.assessmentTime
		newTechnique.reference = params.references
		newTechnique.assigncheck = params.assignedToLearningObjective as boolean
		newTechnique.favcheck = params.favoriteTechnique as boolean
		newTechnique.type = params.assessmentType

		newTechnique.assessmentFeedback= AssessmentFeedback.findByName(params.assessmentFeedback)

		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(learningObjectiveID)
		)

		params.domainCategory.each{
			newTechnique.addToDomainCategory(
				DomainCategory.findByName(it)
			)
		}

		String[] kD = params.knowledgeDimension.split(',')
		String[] lD = params.learningDomain
		String[] dC = params.domainCategory

		if (kD != null) {
			for(int i=0; i < kD.length; i++) {

				if (kD[i]!=null) {
					newTechnique.addToKnowledgeDimension(
					KnowledgeDimension.findByDescription(kD[i]))
				}

			}
		}

		if (lD != null) {
			for(int i=0; i < lD.length; i++) {

				if (lD[i]!=null) {
					newTechnique.addToLearningDomain(LearningDomain.findByName(lD[i]))
				}

			}
		}

		if (dC != null) {
			for(int i=0; i < dC.length; i++) {

				if (dC[i]!=null) {
					if (DomainCategory.findByName(dC[i])!= null)
					{

					println (dC[i])
					newTechnique.addToDomainCategory(DomainCategory.findByName(dC[i]))
					}
				}

			}
		}

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

	def assignFavorite(Long id){
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		// add the technique to the users favorite list
		currentUser.addToFavoriteAssessmentTechnique(AssessmentTechnique.get(id))

		// store relationship
		currentUser.save()
		render (
            [
                value: 'success'
            ] as JSON
        )
	}

	def unassignFavorite(Long id){
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		// add the technique to the users favorite list
		currentUser.removeFromFavoriteAssessmentTechnique(AssessmentTechnique.get(id))

		// store relationship
		currentUser.save()
		render (
            [
                value: 'success'
            ] as JSON
        )
	}

	def assignToLearningObjective(){
		final data = request.JSON
		// get current user object
		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())
		// add the technique to the current learning objective
		currentLearningObjective.addToAssessmentTechniques(AssessmentTechnique.get(data.assessmentTechniqueID.toLong()))

		// store relationship
		currentLearningObjective.save()
		render (
            [
                value: 'success'
            ] as JSON
        )
	}

	def unassignToLearningObjective(){
		final data = request.JSON
		// get current user object
		def currentLearningObjective = LearningObjective.findById(data.learningObjectiveID.toLong())

		// add the technique to the current learning objective
		currentLearningObjective.removeFromAssessmentTechniques(AssessmentTechnique.get(data.assessmentTechniqueID.toLong()))

		// store relationship
		currentLearningObjective.save()
		render (
            [
                value: 'success'
            ] as JSON
        )
	}
}
