package imod
import grails.converters.JSON

class PedagogyTechniqueController {
	def springSecurityService

	static allowedMethods = [
		create: 'POST',
		get: 'GET'
	]

	/**
	 * get info on a selected technique
	 */
	def get(Long id) {
		render (
			[
				pedagogyTechnique: PedagogyTechnique.get(id)
			] as JSON
		)
	}

	def cancel(Long id, Long learningObjectiveID) {
		redirect(
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	/**
	 * creates a new Pedagogy Technique
	 */
	def save(Long id, Long learningObjectiveID) {
		def newTechnique = new PedagogyTechnique()

		if (params.techniqueId) {
			newTechnique = PedagogyTechnique.get(params.techniqueId)
		}

		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription
		newTechnique.direction = params.direction
		newTechnique.materials = params.materials
		newTechnique.reference = params.reference
		newTechnique.strategyDescription = params.strategyDescription
		newTechnique.activityDescription = params.activityDescription

		// Store relationships
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)
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
		newTechnique.addToActivityFocus(
			PedagogyActivityFocus.findByFocus(params.pedagogyFocus)
		)

		// persist new technique to database
		newTechnique.save()

		if(params.assignedToLearningObjective != null) {
			// get current user object
			def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

			// add the technique to the user's favorite list
			currentLearningObjective.addToPedagogyTechniques(newTechnique)

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
			controller: 'pedagogy',
			action: 'index',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}
}
