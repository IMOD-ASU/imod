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

		LinkedHashSet pedaTechList
		if(kdomain.size() > 0)
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct join p.knowledge kn where dm.name in (:dc) AND (ct.name in (:ld) AND kn.description in (:kd)) order by p.pedagogyTitle', [dc: domain,ld: domainCategory,kd: kdomain])
		else
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct where dm.name in (:dc) AND (ct.name in (:ld)) order by p.pedagogyTitle', [dc: domain,ld: domainCategory])

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
			pedaTechList: pedaTechList,
			objectiveId: params.objectiveId,
			userId: ImodUser.get(springSecurityService.principal.id)
		]
	}
	/**
	 * addNewTechnique, is used to create new Technique
	 * @return
	 */
	def addNewTechnique() {
		def pedagogyActivity
		def pedagogyReference
		def pedTecInstance

		pedTecInstance = new PedagogyTechnique(params)
		pedTecInstance.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		if (!pedTecInstance.save()) {
			render(
				view: 'error',
				model: [
					pedagogyTechniqueInstance: pedTecInstance
				]
			)
			return
		}
		params.each{
			if(it.key.startsWith('pedagogyActivity') && it.toString().contains(':')) {
				pedagogyActivity = new PedagogyActivity(it.value)
				pedagogyActivity.pedagogyTechnique = pedTecInstance
				pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(it.value.duration)
				pedagogyActivity.save()
			}

			if(it.key.startsWith('pedagogyReference') && it.toString().contains(':')) {
				pedagogyReference = new PedagogyReference(it.value)
				pedagogyReference.pedagogyTechnique = pedTecInstance
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
		def imod = Imod.get(id)

		Long objectiveId = params.objectiveId ? params.long('objectiveId') : null
		def learningDomainList = LearningDomain.list();

		List<LearningObjective> objectiveList = LearningObjective.findAllByImod(imod)
		Set<Content> remainingContent = []
		Set<Content> currentImodContentList = []
		objectiveList.each {
			currentImodContentList.addAll(it.contents);
			remainingContent.addAll(it.contents);
		}
		LearningObjective objective = objectiveId ? LearningObjective.get(objectiveId) : objectiveList ? objectiveList?.first() : null

		List<Content> kdList = Content.findAllByObjective(objective)
		def strkdList = []
		def contentTitle = []
		def performanceTitle = '${objective.performance}'

		kdList.each{
			print '${it.knowledgeDomainCode}'
			strkdList.add('${it.knowledgeDomainCode}')
			contentTitle.add('${it.topicTitle}')
		}

		List<imodv6.ContentSchedule> topicDateForCurrentLearningObjectiveList = []
		Set<Content> contentList = objective ? objective.contents : []
		topicDateForCurrentLearningObjectiveList.addAll(imodv6.ContentSchedule.findAllByContentInList(contentList))
		topicDateForCurrentLearningObjectiveList = topicDateForCurrentLearningObjectiveList.flatten()
		List<Date> learningObjectiveDates = []
		Date today = new Date()
		today.clearTime()
		learningObjectiveDates.add(today)
		for (number in 1..30) {
			learningObjectiveDates.add(today + number)
		}
		remainingContent.removeAll(contentList ? contentList.toList() : [])
		List<KnowledgeDimension> knowledgeDomainCodes = KnowledgeDimension.values()
		List<ContentPriorityCode> contentPriorityCodeTypeList = ContentPriorityCode.values()

		//To get the Domain Category
		LearningDomain domain = LearningDomain.get(1)
		def domainList = DomainCategory.findAllByDomain(domain);


		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()
		Map<String, String> mapkdList = new HashMap<String,String>()
		def kdmnList = []

		KnowledgeDomainlist.each{ kd ->
			def flag = false
			def sKD = '${kd}'
			//println sKD
			strkdList.each{ strkd ->
				def sStrKD = '${strkd}'
				//println sKD + '  ' + sStrKD
				if(sKD.equals(sStrKD)) {
					flag = true
				}
			}

			if(flag) {
				mapkdList.put(sKD, 'true')
				kdmnList.add(sKD.toString())
			}
			else {
				mapkdList.put(sKD, 'false')
			}
		}

		LinkedHashSet pedaTechList
		if(kdmnList.size() > 0) {
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct join p.knowledge kn where dm.name in (:dc) AND (ct.name in (:ld) AND kn.description in (:kd))  order by p.pedagogyTitle', [dc: objective.learningDomain.toString(),ld: objective.domainCategory.toString(),kd: kdmnList])
		}
		else {
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct where dm.name in (:dc) AND (ct.name in (:ld))  order by p.pedagogyTitle', [dc: objective.learningDomain.toString(),ld: objective.domainCategory.toString()])
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
				currentPage: 'pedagogy',
				topicDateForCurrentLearningObjectiveList: topicDateForCurrentLearningObjectiveList,
				learningObjectiveDates: learningObjectiveDates,
				remainingContent: remainingContent,
				imod: imod,
				mapkdList: mapkdList,
				currentImodContentList: currentImodContentList,
				contentTitle: contentTitle,
				selectionLine:selectionLine,
				currentChapterContentList: contentList,
				chapter: objective,
				dmn: objective.learningDomain,
				dc: objective.domainCategory,
				chapterCount: (objectiveList?.size() > 1),
				contentPriorityCodeTypeList: contentPriorityCodeTypeList,
				objectiveList: objectiveList,
				contentList: contentList,
				learningObjectiveTypeList: knowledgeDomainCodes,
				domainList: domainList,
				KnowledgeDomainlist: KnowledgeDomainlist,
				learningDomainList: learningDomainList,
				pedaTechList: pedaTechList,
				userId: ImodUser.get(springSecurityService.principal.id),
				favPedaTechList: favPedaTechList,
				referenceTypeList: PedagogyReferenceType.list()
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
