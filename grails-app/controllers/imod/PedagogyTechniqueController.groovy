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
		def knowledgeDimensions = PedagogyTechnique.get(id).knowledgeDimension
		def learningDomains = PedagogyTechnique.get(id).learningDomain
		def domainCategories = PedagogyTechnique.get(id).domainCategory
		def pedagogyFocuses = PedagogyTechnique.get(id).activityFocus

		render (
			[
				pedagogyTechnique: PedagogyTechnique.get(id),
				learningDomain: LearningDomain.findById(PedagogyTechnique.get(id).learningDomain[0].id).toString(),
				domainCategory: DomainCategory.findById(PedagogyTechnique.get(id).domainCategory[0].id).toString(),
				knowledgeDimension: knowledgeDimensions.join(','),
				learningDomains: learningDomains.join(','),
				domainCategories: domainCategories.join(','),
				activityFocus: pedagogyFocuses.join(','),
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
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		def newTechnique = new PedagogyTechnique()

		if (params.techniqueId && params.cloneDetect != 'clone') {

			newTechnique = PedagogyTechnique.get(params.techniqueId)

			def isAdminUser = false
			newTechnique.users.each {
				if (it.username == 'imodadmin' && currentUser.username == 'imodadmin') {
					isAdminUser = true
					return true
				}
			}

			// check if technique is admin
			// if it is, it can only be edited
			// by an admin
			if (newTechnique.isAdmin && !isAdminUser) {
				render(status: 401, text: 'Unauthorized')
				return
			}

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
		String[] lD = params.list('learningDomain')
		String[] dC = params.list('domainCategory')
		String[] pF = params.list('pedagogyFocus')

		if (kD != null) {
			for (int i = 0; i < kD.length; i++) {
				if (kD[i] != null) {
					// println (kD[i])
					String test = kD[i].trim()
					newTechnique.addToKnowledgeDimension(
					KnowledgeDimension.findByDescription(test))
				}
			}
		}

		if (lD != null) {
			for (int i = 0; i < lD.length; i++) {
				if (lD[i] != null) {
					// println (lD[i])
					newTechnique.addToLearningDomain(LearningDomain.findByName(lD[i]))
				}
			}
		}

		if (dC != null) {
			for (int i = 0; i < dC.length; i++) {
				if (dC[i] != null) {
					// println (dC[i])
					if (DomainCategory.findByName(dC[i]) != null) {
						newTechnique.addToDomainCategory(DomainCategory.findByName(dC[i]))
					}
				}
			}
		}

		if (pF != null) {
			for (int i = 0; i < pF.length; i++) {
				if (pF[i] != null) {
					// println (pF[i])
					if (PedagogyActivityFocus.findByFocus(pF[i]) != null) {
						newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus(pF[i]))
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
		// store relationship
		currentUser.addToPedagogyTechnique(newTechnique)
		currentUser.save()

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

	def getAssignedPedTechCount(Long id) {
		def currentLearningObjective = LearningObjective.findById(id.toLong())
		def size = currentLearningObjective.getPedagogyTechniques().size()

		render([
			count: size
		] as JSON
		)
	}
}
