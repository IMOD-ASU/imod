package imod
import grails.converters.JSON

class PedagogyTechniqueController {
	def springSecurityService

	static allowedMethods = [
		create: 'POST',
		get: 'GET',
		unassignFavorite: 'GET',
		assignFavorite: 'GET'
	]

	/**
	 * get info on a selected technique
	 */
	def get(Long id) {
		String [] knowledgedimensions = PedagogyTechnique.get(id).knowledgeDimension;
		String  knowledgeDimensions="";


		for(int i = 0; i< knowledgedimensions.size(); i++) {
			knowledgeDimensions=knowledgeDimensions+knowledgedimensions[i]+",";
		}

		// add some stuff if (KnowledgeDimension.findById(PedagogyTechnique.get(id).knowledgeDimension[i].id).toString()!=null)
		//println(knowledgeDimensions);
		render (
			[
				pedagogyTechnique: PedagogyTechnique.get(id),
				learningDomain: LearningDomain.findById(PedagogyTechnique.get(id).learningDomain[0].id).toString(),
				domainCategory: DomainCategory.findById(PedagogyTechnique.get(id).domainCategory[0].id).toString(),
				knowledgeDimension:knowledgeDimensions,
				activityFocus:PedagogyActivityFocus.findById(PedagogyTechnique.get(id).activityFocus[0].id).toString(),
				pedagogyMode:(PedagogyTechnique.get(id).pedagogyMode).toString(),
				pedagogyDuration:(PedagogyTechnique.get(id).pedagogyDuration).toString(),
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

		print params

		if (params.techniqueId && params.cloneDetect != 'clone') {
			PedagogyTechnique.get(params.techniqueId)
			PedagogyTechnique.get(params.techniqueId).knowledgeDimension.clear()
			PedagogyTechnique.get(params.techniqueId).learningDomain.clear()
			PedagogyTechnique.get(params.techniqueId).domainCategory.clear()
			PedagogyTechnique.get(params.techniqueId).activityFocus.clear()
		}


		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription
		newTechnique.direction = params.duration
		newTechnique.materials = params.materials
		newTechnique.reference = params.reference
		newTechnique.activityDescription = params.activityDescription
		String[] kD = params.knowledgeDimension.split(",");

		if (kD != null) {
			for(int i=0; i < kD.length; i++) {

				if (kD[i]!=null) {
					newTechnique.addToKnowledgeDimension(
					KnowledgeDimension.findByDescription(kD[i]))
				}

			}
		}

		// Store relationships
		newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration(params.pedagogyDuration)
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)
		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(learningObjectiveID)
		)
		newTechnique.addToDomainCategory(
			DomainCategory.findByName(params.domainCategory)
		)
		newTechnique.addToLearningDomain(
			LearningDomain.findByName(params.learningDomain)
		)
		newTechnique.addToActivityFocus(
			PedagogyActivityFocus.findByFocus(params.pedagogyFocus)
		)

		// persist new technique to database
		newTechnique.save()

		redirect(
			controller: 'pedagogy',
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
		currentUser.addToFavoriteTechnique(PedagogyTechnique.get(id))

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
		currentUser.removeFromFavoriteTechnique(PedagogyTechnique.get(id))

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
		currentLearningObjective.addToPedagogyTechniques(PedagogyTechnique.get(data.pedagogyTechniqueID.toLong()))

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
		currentLearningObjective.removeFromPedagogyTechniques(PedagogyTechnique.get(data.pedagogyTechniqueID.toLong()))

		// store relationship
		currentLearningObjective.save()
		render (
            [
                value: 'success'
            ] as JSON
        )
	}

	def clone(Long id, Long learningObjectiveID) {
		def newTechnique = new PedagogyTechnique()

		if (params.techniqueId) {
			String [] knowledgedimensions = PedagogyTechnique.get(params.techniqueId).knowledgeDimension;
			for(int i = 0; i < knowledgedimensions.size(); i++) {
				newTechnique.addToKnowledgeDimension(
		  		KnowledgeDimension.findByDescription(knowledgedimensions[i]))
			}
		}

		// Store text fields
		newTechnique.title = params.title
		newTechnique.description = params.activityDescription
		newTechnique.direction = params.duration
		newTechnique.materials = params.materials
		newTechnique.reference = params.reference
		newTechnique.activityDescription = params.activityDescription

		String[] kD = params.knowledgeDimension.split(",");

		// Store relationships
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)
		newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration(params.pedagogyDuration)
		newTechnique.addToAssignedLearningObjective(
			LearningObjective.get(learningObjectiveID)
		)
		newTechnique.addToDomainCategory(
			DomainCategory.findByName(params.domainCategory)
		)
		//newTechnique.addToKnowledgeDimension(
			//KnowledgeDimension.findByDescription(params.knowledgeDimension)
		//)
		newTechnique.addToLearningDomain(
			LearningDomain.findByName(params.learningDomain)
		)
		newTechnique.addToActivityFocus(
			PedagogyActivityFocus.findByFocus(params.pedagogyFocus)
		)

		// persist new technique to database
		newTechnique.save()

		if (params.assignedToLearningObjective != null) {
			// get current user object
			def currentLearningObjective = LearningObjective.findById(learningObjectiveID)

			// add the technique to the user's favorite list
			currentLearningObjective.addToPedagogyTechniques(newTechnique)

			// store relationship
			currentLearningObjective.save()
		}

		if (params.favoriteTechnique != null) {
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
