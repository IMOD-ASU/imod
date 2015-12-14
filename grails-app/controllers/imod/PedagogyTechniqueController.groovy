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

	def favorites() {

		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		final favoriteTechniques = currentUser.favoriteTechnique

		 render(
			[
				pedagogyTechniques: favoriteTechniques
			] as JSON
		)
	}

	/**
	 * get info on a selected technique
	 */
	def get(Long id) {
		String [] knowledgedimensions = PedagogyTechnique.get(id).knowledgeDimension
		String [] learningdomains = PedagogyTechnique.get(id).learningDomain
		String [] domaincategories = PedagogyTechnique.get(id).domainCategory

		String  knowledgeDimensions=''
		String  learningDomains=''
		String  domainCategories=''

		for (int i = 0; i < knowledgedimensions.size(); i++) {
			knowledgeDimensions = knowledgeDimensions + knowledgedimensions[i] + ','
		}
		for (int i = 0; i < learningdomains.size(); i++) {
			learningDomains = learningDomains + learningdomains[i] + ','
		}
		for (int i = 0; i < domaincategories.size(); i++) {
			domainCategories = domainCategories + domaincategories[i] + ','
		}

		// add some stuff if (KnowledgeDimension.findById(PedagogyTechnique.get(id).knowledgeDimension[i].id).toString()!=null)
		render (
			[
				pedagogyTechnique: PedagogyTechnique.get(id),
				learningDomain: LearningDomain.findById(PedagogyTechnique.get(id).learningDomain[0].id).toString(),
				domainCategory: DomainCategory.findById(PedagogyTechnique.get(id).domainCategory[0].id).toString(),
				knowledgeDimension: knowledgeDimensions,
				learningDomains: learningDomains,
				domainCategories: domainCategories,
				activityFocus: PedagogyActivityFocus.findById(PedagogyTechnique.get(id).activityFocus[0].id).toString(),
				pedagogyMode: (PedagogyTechnique.get(id).pedagogyMode).toString(),
				pedagogyDuration: (PedagogyTechnique.get(id).pedagogyDuration).toString(),
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
		String[] kD = params.knowledgeDimension.split(',')
		String[] lD = params.domainSelected.split(',')
		String[] dC = params.domainCategorySelected.split(',')

		if (kD != null) {
			for (int i = 0; i < kD.length; i++) {
				if (kD[i] != null) {
					println (kD[i])
					newTechnique.addToKnowledgeDimension(
					KnowledgeDimension.findByDescription(kD[i]))
				}
			}
		}

		if (lD != null) {
			for (int i = 0; i < lD.length; i++) {
				if (lD[i] != null) {
					println (lD[i])
					newTechnique.addToLearningDomain(LearningDomain.findByName(lD[i]))
				}
			}
		}

		if (dC != null) {
			for (int i = 0; i < dC.length; i++) {
				if (dC[i] != null) {
					println (dC[i])
					if (DomainCategory.findByName(dC[i]) != null) {
						newTechnique.addToDomainCategory(DomainCategory.findByName(dC[i]))
					}
				}
			}
		}

		// Store relationships
		newTechnique.pedagogyDuration = PedagogyActivityDuration.findByDuration(params.pedagogyDuration)
		newTechnique.pedagogyMode = PedagogyMode.findByName(params.pedagogyMode)

		//newTechnique.addToDomainCategory(DomainCategory.findByName(params.domainCategory))
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

	def assignFavorite(Long id) {
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

	def unassignFavorite(Long id) {
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

	def assignToLearningObjective() {
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

	def unassignToLearningObjective() {
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
}
