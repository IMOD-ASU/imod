package imodv6

import javax.servlet.http.HttpServletResponse

class PedagogyController {
	def springSecurityService
	static allowedMethods = [
		reloadPedagogyTab: 'GET',
		index: 'GET',
		addNewTechnique: 'POST'
	]

	/**
	 * updateExtendedTechnique action used to updated Extended Match
	 * @return
	 */
	def updateExtendedTechnique() {
		def domain = []
		def domainCategory = []
		def kdomain = []
		if(params.domain.toString().contains('[')) {
			params.domain.each{
				domain.add(it)
			}
		}
		else {
			domain.add(params.domain)
		}

		if(params.domainCategory.toString().contains('[')) {
			params.domainCategory.each{
				domainCategory.add(it)
			}
		}
		else {
			domainCategory.add(params.domainCategory)
		}

		if(params.kdomain.toString().contains('[')) {
			params.kdomain.each{
				kdomain.add(it)
			}
		}
		else if(params.kdomain) {
			kdomain.add(params.kdomain)
		}

		LinkedHashSet pedagogyTechniqueList
		if(kdomain.size() > 0) {
			def query = """
				SELECT PedagogyTechnique
				FROM PedagogyTechnique
				INNER JOIN PedagogyTechnique.domain AS domain
				JOIN PedagogyTechnique.category AS category
				JOIN PedagogyTechnique.knowledge AS knowledge

				WHERE domain.name IN (:domainCategory)
				AND	category.name IN (:learningDomain)
				AND knowledge.description IN (:knowledgeDomain)
				ORDER BY PedagogyTechnique.pedagogyTitle
			"""
			pedagogyTechniqueList = PedagogyTechnique.executeQuery(
				query,
				[
					domainCategory: domain,
					learningDomain: domainCategory,
					knowledgeDomain: kdomain
				]
			)
		}
		else {
			def query = """
				SELECT PedagogyTechnique
				FROM PedagogyTechnique
				INNER JOIN PedagogyTechnique.domain AS domain
				JOIN PedagogyTechnique.category AS category

				WHERE domain.name IN (:domainCategory)
				AND category.name IN (:learningDomain)
				ORDER BY PedagogyTechnique.pedagogyTitle
			"""
			pedagogyTechniqueList = PedagogyTechnique.executeQuery(
				query,
				[
					domainCategory: domain,
					learningDomain: domainCategory
				]
			)
		}
		def selectionLine = ''
		if(domain.size() > 0){
			if(domain.size() == 1) {
				selectionLine += '${domain.get(0)}'
			}
			else if(domain.size() == 2) {
				selectionLine += '${domain.get(0)} or ${domain.get(1)}'
			}
			else {
				selectionLine += 'Domain (${domain.size()} Selections)'
			}
		}
		if(domainCategory.size() > 0){
			if(domainCategory.size() == 1) {
				selectionLine += ' > ${domainCategory.get(0)}'
			}
			else if(domainCategory.size() == 2) {
				selectionLine += ' > ${domainCategory.get(0)} or ${domainCategory.get(1)}'
			}
			else {
				selectionLine += ' > Domain Category (${domainCategory.size()} Selections)'
			}
		}
		if(kdomain.size() > 0) {
			if(kdomain.size() == 1) {
				selectionLine += ' > ${kdomain.get(0)}'
			}
			else if(kdomain.size() == 2) {
				selectionLine += ' > ${kdomain.get(0)} or ${kdomain.get(1)}'
			}
			else {
				selectionLine += ' > Knowledge Dimension (${kdomain.size()} Selections)'
			}
		}

		[
			selectionLine: selectionLine,
			pedagogyTechniqueList: pedagogyTechniqueList,
			objectiveId: params.objectiveId,
			userId: ImodUser.get(springSecurityService.principal.id)
		]
	}
	/**
	 * addNewTechnique, is used to create new Technique
	 * @return
	 */
	def addNewTechnique() {
		def pedagogyTechniqueList = new PedagogyTechnique(params)
		pedagogyTechniqueList.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		if (!pedagogyTechniqueList.save()) {
			render(
				view: 'error',
				model: [
					pedagogyTechniqueInstance: pedagogyTechniqueList
				]
			)
			return
		}
		params.each{
			if(it.key.startsWith('pedagogyActivity') && it.toString().contains(':')) {
				def pedagogyActivity = new PedagogyActivity(it.value)
				pedagogyActivity.pedagogyTechnique = pedagogyTechniqueList
				pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(it.value.duration)
				pedagogyActivity.save()
			}

			if(it.key.startsWith('pedagogyReference') && it.toString().contains(':')) {
				def pedagogyReference = new PedagogyReference(it.value)
				pedagogyReference.pedagogyTechnique = pedagogyTechniqueList
				pedagogyReference.referenceType = PedagogyReferenceType.get(it.value.refeType)
				pedagogyReference.save()
			}
		}

		redirect(
			controller: 'imod',
			action: 'edit',
			id: params.id,
			params: [
				loadPedagogyTab: true
			]
		)
	}

	/**
	 * index action is called when used click on pedagogy tab from main page.
	 * @param id
	 * @return
	 */
	def index(Long id) {
		// get the selected imod
		def imod = Imod.get(id)

		// get all the learning domains
		def learningDomainList = LearningDomain.list();

		// finds all the learning objective linked to this imod
		def objectiveList = LearningObjective.findAllByImod(imod)

		// lists for content
		Set<Content> remainingContent = []
		Set<Content> currentImodContentList = []

		// for each objective add the content to a list
		for (def objective in objectiveList) {
			currentImodContentList.addAll(objective.contents)
			remainingContent.addAll(objective.contents)
		}

		def objective
		// if there is a selected objective get it
		if(params.containsKey('objectiveId')) {
			objective = LearningObjective.get(params.long('objectiveId'))
		}
		// otherwise get the first objective
		else {
			objective = objectiveList.first()
		}

		// TODO why is knowledge domain list same as content list?
		def knowledgeDomainList = objective.contents
		def knowledgeDomainListAsStrings = []
		def contentTitles = []

		// add the strings to the arrays
		for (def content in knowledgeDomainList) {
			knowledgeDomainListAsStrings.add('${content.knowledgeDomainCode}')
			contentTitles.add('${content.topicTitle}')
		}

		def topicDateForCurrentLearningObjectiveList = []
		def contentList = objective.contents
		topicDateForCurrentLearningObjectiveList.addAll(imodv6.ContentSchedule.findAllByContentInList(contentList))
		topicDateForCurrentLearningObjectiveList = topicDateForCurrentLearningObjectiveList.flatten()

		def learningObjectiveDates = []
		def today = new Date()
		today.clearTime()
		learningObjectiveDates.add(today)
		for (number in 1..30) {
			learningObjectiveDates.add(today + number)
		}
		remainingContent.removeAll(contentList ? contentList.toList() : [])
		List<KnowledgeDimension> knowledgeDomainCodes = KnowledgeDimension.values()
		List<ContentPriorityCode> contentPriorityCodeTypeList = ContentPriorityCode.values()

		//To get the Domain Category
		// TODO why is this always the first?
		def domain = LearningDomain.first()
		def domainList = DomainCategory.findAllByLearningDomain(domain);


		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()
		Map<String, String> mapKnowledgeDomainList = new HashMap<String,String>()
		def kdmnList = []

		KnowledgeDomainlist.each{ kd ->
			def flag = false
			def sKD = '${kd}'
			//println sKD
			knowledgeDomainListAsStrings.each{ strkd ->
				def sStrKD = '${strkd}'
				//println sKD + '  ' + sStrKD
				if(sKD.equals(sStrKD)) {
					flag = true
				}
			}

			if(flag) {
				mapKnowledgeDomainList.put(sKD, 'true')
				kdmnList.add(sKD.toString())
			}
			else {
				mapKnowledgeDomainList.put(sKD, 'false')
			}
		}

		def pedagogyTechniqueList
		if(kdmnList.size() > 0) {
			def query = """
				SELECT PedagogyTechnique
				FROM PedagogyTechnique
				INNER JOIN PedagogyTechnique.domain AS domain
				JOIN PedagogyTechnique.category AS category
				JOIN PedagogyTechnique.knowledge AS knowledge

				WHERE domain.name IN (:domainCategory)
				AND category.name IN (:learningDomain)
				AND knowledge.description IN (:knowledgeDomain)
				ORDER BY PedagogyTechnique.pedagogyTitle
			"""
			pedagogyTechniqueList = PedagogyTechnique.executeQuery(
				query,
				[
					domainCategory: objective.learningDomain.toString(),
					learningDomain: objective.domainCategory.toString(),
					knowledgeDomain: kdmnList
				]
			)
		}
		else {
			def query = """
				SELECT PedagogyTechnique
				FROM PedagogyTechnique
				INNER JOIN PedagogyTechnique.domain AS domain
				JOIN PedagogyTechnique.category AS category

				WHERE domain.name IN (:domainCategory)
				AND category.name IN (:learningDomain)
				ORDER BY PedagogyTechnique.pedagogyTitle
			"""
			// TODO check to ensure that an objective has an action word category
			pedagogyTechniqueList = PedagogyTechnique.executeQuery(
				query,
				[
					domainCategory: objective.actionWordCategory.domainCategory.learningDomain.toString(),
					learningDomain: objective.actionWordCategory.domainCategory.toString()
				]
			)
		}

		def favPedaTechList = ImodUserPedagogyFavorite.findAllByImodUser(ImodUser.get(springSecurityService.principal.id))
		def selectionLine = '${objective.learningDomain} > ${objective.domainCategory}'
		if(kdmnList.size() > 0) {
			if(kdmnList.size() == 1) {
				selectionLine += ' > ${kdmnList.get(0)}'
			}
			else if(kdmnList.size() == 2) {
				selectionLine += ' > ${kdmnList.get(0)} or ${kdmnList.get(1)}'
			}
			else {
				selectionLine += ' > Knowledge Dimension (${kdmnList.size()} Selections)'
			}
			[
				chapter: objective,
				chapterCount: (objectiveList?.size() > 1),
				contentList: contentList,
				contentPriorityCodeTypeList: contentPriorityCodeTypeList,
				contentTitles: contentTitles,
				currentChapterContentList: contentList,
				currentImodContentList: currentImodContentList,
				currentPage: 'pedagogy',
				dc: objective.domainCategory,
				dmn: objective.learningDomain,
				domainList: domainList,
				favPedaTechList: favPedaTechList,
				imod: imod,
				KnowledgeDomainlist: KnowledgeDomainlist,
				learningDomainList: learningDomainList,
				learningObjectiveDates: learningObjectiveDates,
				learningObjectiveTypeList: knowledgeDomainCodes,
				mapKnowledgeDomainList: mapKnowledgeDomainList,
				objectiveList: objectiveList,
				pedagogyTechniqueList: pedagogyTechniqueList,
				referenceTypeList: PedagogyReferenceType.list(),
				remainingContent: remainingContent,
				selectionLine:selectionLine,
				topicDateForCurrentLearningObjectiveList: topicDateForCurrentLearningObjectiveList,
				userId: ImodUser.get(springSecurityService.principal.id)
			]
		}
	}
	/**
	 * reloadPedagogyTab, This action is used for Assign and Favorite Technique and reload page after that.
	 * @param id
	 * @param objectiveId
	 * @return
	 */
	def reloadPedagogyTab(Long id, Long objectiveId) {
		def imodUser = ImodUser.get(springSecurityService.principal.id)
		def pedagogyTech = PedagogyTechnique.get(params.pedtecID)
		if(params.fav == 'true') {
			def favTech = ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(imodUser,pedagogyTech)
			ImodUserPedagogyFavorite fav = favTech ? favTech :new ImodUserPedagogyFavorite()
			fav.pedagogyTechnique = pedagogyTech
			fav.imodUser = imodUser
			if(favTech) {
				fav.delete()
			}
			else {
				fav.save()
			}
		}
		if(params.assign == 'true'){
			def learningObjective = LearningObjective.get(params.objectiveId)
			def assingTech = ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(learningObjective,pedagogyTech)
			ImodPedagogyAssign assign = assingTech ? assingTech : new ImodPedagogyAssign()
			assign.pedagogyTechnique = pedagogyTech
			assign.learningObjective = learningObjective
			if(assingTech) {
				assign.delete()
			}
			else {
				assign.save()
			}
		}
		redirect(
			controller: 'imod',
			action: 'edit',
			id: id,
			params: [
				loadPedagogyTab: true,
				objectiveId: objectiveId
			]
		)
	}

	// /**
	//  * Index, This action will used to load left panel on Pedagogy
	//  */
	// def index() {
	//
	// 	// TO get the Learning Objectives
	// 	Imod imod = Imod.get(48)
	// 	def objectiveList = LearningObjective.findAllByImod(imod)
	//
	// 	//To get the Domain Category
	// 	LearningDomain domain = LearningDomain.get(1)
	// 	def domainList = DomainCategory.findAllByDomain(domain);
	//
	// 	//To get the Knowledge Dimension
	// 	def KnowledgeDomainlist = KnowledgeDimension.list()
	//
	//
	// 	[
	// 		objectiveList : objectiveList,
	// 		domainList: domainList,
	// 		KnowledgeDomainlist: KnowledgeDomainlist
	// 	]
	// }
	/**
	 * To open Pedagogy Technique clone popup
	 */
	def clonePedagogyTechnique() {
		[
			pedagogyTech: PedagogyTechnique.get(params.techId),
			learningDomainList: LearningDomain.list(),
			domainList: DomainCategory.list(),
			KnowledgeDomainlist: KnowledgeDimension.list()
		]
	}
	/**
	 * cloneSaveTech, This action is used for validating cloned Technique.
	 */
	def cloneSaveTechnqiue() {
		def pedagogyTechnique = PedagogyTechnique.read(params.pedagogy_tech_id)

		// check if there should be a linked activity
		if (params.containsKey('pedagogyActivity')) {
			// create the link
			def pedagogyActivity = new PedagogyActivity(params.pedagogyActivity)
			pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(params.pedagogyActivity.duration)
		}

		// checks if there should be a linked reference object
		if (params.containsKey('pedagogyReference')) {
			// create a link between technique and reference
			def pedagogyReference = new PedagogyReference(params.pedagogyReference)
			pedagogyReference.referenceType = PedagogyReferenceType.get(params.pedagogyReference.refeType)
		}

		// if there is not technique by this name
		if(PedagogyTechnique.findByPedagogyTitle(params.pedagogyTitle) == null) {
			// go back to edit page and show error message
			render template:'pedagogyCloneTechnique', model: [
				errorMsg: 'title',
				pedagogyTech: PedagogyTechnique.get(params.pedagogy_tech_id),
				learningDomainList: LearningDomain.list(),
				domainList: DomainCategory.list(),
				KnowledgeDomainlist: KnowledgeDimension.list()
			]
		}
		// it is okay to change title
		else {
			println 'title changed'
			def activity = PedagogyActivity.findAllByPedagogyTechnique(pedagogyTechnique)
			def reference = PedagogyReference.findAllByPedagogyTechnique(pedagogyTechnique)
		}

		cloneNewTechnique(params)
		render 'done'
	}
	/**
	 * cloneNewTechnique, This method is used to Save cloned Technique
	 * @param params
	 * @return
	 */
	def cloneNewTechnique(params){
		def pedagogyTechniqueInstance = new PedagogyTechnique(params)
		pedagogyTechniqueInstance.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		// save the new instance
		if (!pedagogyTechniqueInstance.save()) {
			// if it failed to save, show error page and break out of function
			render(
				view: 'error',
				model: [
					pedagogyTechniqueInstance: pedagogyTechniqueInstance
				]
			)
			return
		}

		// if there a pedegogy activity refrenced in the request link new instance to activity
		if (params.containsKey('pedagogyActivity')) {
			def pedagogyActivity = new PedagogyActivity(params.pedagogyActivity.value)
			pedagogyActivity.pedagogyTechnique = pedagogyTechniqueInstance
			pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(params.pedagogyActivity.duration)
			pedagogyActivity.save()
		}

		// if there a refrence objective id in the request link new instance to activity
		if (params.containsKey('pedagogyReference')) {
			def pedagogyReference = new PedagogyReference(params.pedagogyReference)
			pedagogyReference.pedagogyTechnique = pedagogyTechniqueInstance
			pedagogyReference.referenceType = PedagogyReferenceType.get(params.pedagogyReference.refeType)
			pedagogyReference.save()
		}
	}
}
