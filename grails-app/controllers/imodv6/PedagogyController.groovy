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
	def updateExtendedTechnique(){
		def domain = []
		def domainCategory = []
		def kdomain = []
		if(params.domain.toString().contains('[')){
			params.domain.each{
				domain.add(it)
			}
		}else{
			domain.add(params.domain)
		}

		if(params.domainCategory.toString().contains('[')){
			params.domainCategory.each{
				domainCategory.add(it)
			}
		}else{
			domainCategory.add(params.domainCategory)
		}

		if(params.kdomain.toString().contains('[')){
			params.kdomain.each{
				kdomain.add(it)
			}
		}else if(params.kdomain){
			kdomain.add(params.kdomain)
		}
		LinkedHashSet pedaTechList
		if(kdomain.size() > 0)
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct join p.knowledge kn where dm.name in (:dc) AND (ct.name in (:ld) AND kn.description in (:kd)) order by p.pedagogyTitle', [dc: domain,ld: domainCategory,kd: kdomain])
		else
			pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct where dm.name in (:dc) AND (ct.name in (:ld)) order by p.pedagogyTitle', [dc: domain,ld: domainCategory])

		def selectionLine = ''
		if(domain.size() > 0){
			if(domain.size() == 1){
				selectionLine += '${domain.get(0)}'
			}else if(domain.size() == 2){
				selectionLine += '${domain.get(0)} or ${domain.get(1)}'
			}else{
				selectionLine += 'Domain (${domain.size()} Selections)'
			}
		}
		if(domainCategory.size() > 0){
			if(domainCategory.size() == 1){
				selectionLine += ' > ${domainCategory.get(0)}'
			}else if(domainCategory.size() == 2){
				selectionLine += ' > ${domainCategory.get(0)} or ${domainCategory.get(1)}'
			}else{
				selectionLine += ' > Domain Category (${domainCategory.size()} Selections)'
			}
		}
		if(kdomain.size() > 0){
			if(kdomain.size() == 1){
				selectionLine += ' > ${kdomain.get(0)}'
			}else if(kdomain.size() == 2){
				selectionLine += ' > ${kdomain.get(0)} or ${kdomain.get(1)}'
			}else{
				selectionLine += ' > Knowledge Dimension (${kdomain.size()} Selections)'
			}
		}

		render view:'_pedagogyExtendedMatch', model:[selectionLine:selectionLine, pedaTechList:pedaTechList,objectiveId:params.objectiveId,userId:ImodUser.get(springSecurityService.principal.id)]
	}
	/**
	 * addNewTechnique, is used to create new Technique
	 * @return
	 */
	def addNewTechnique(){
		def pedagogyActivity
		def pedagogyReference
		def pedTecInstance

		pedTecInstance = new PedagogyTechnique(params)
		pedTecInstance.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		if (!pedTecInstance.save()) {
			render(view: 'error', model: [pedagogyTechniqueInstance: pedTecInstance])
			return
		}
		params.each{
			if(it.key.startsWith('pedagogyActivity') && it.toString().contains(':')){
				pedagogyActivity = new PedagogyActivity(it.value)
				pedagogyActivity.pedagogyTechnique = pedTecInstance
				pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(it.value.duration)
				pedagogyActivity.save()
			}
			if(it.key.startsWith('pedagogyReference') && it.toString().contains(':')){
				pedagogyReference = new PedagogyReference(it.value)
				pedagogyReference.pedagogyTechnique = pedTecInstance
				pedagogyReference.referenceType = PedagogyReferenceType.get(it.value.refeType)
				pedagogyReference.save()
			}
		}
		redirect(controller: 'imod', action: 'edit', id: params.id, params: [loadPedagogyTab: true])
	}

	/**
	 * index action is called when used click on pedagogy tab from main page.
	 * @param id
	 * @return
	 */
	def index(Long id) {
		Imod imod = id ? Imod.get(id) : null
		if (imod) {
			Long objectiveId = params.objectiveId ? params.long('objectiveId') : null
			def lrnDomainlist = LearningDomain.list(sort:'id');

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
			(1..30).each {
				learningObjectiveDates.add(today + it)
			}
			remainingContent.removeAll(contentList ? contentList.toList() : [])
			List<KnowledgeDimension> knowledgeDomainCodes = KnowledgeDimension.values()
			List<ContentPriorityCode> contentPriorityCodeTypeList = ContentPriorityCode.values()

			//To get the Domain Category
			LearningDomain domain = LearningDomain.get(1)
			def domainList = DomainCategory.findAllByDomain(domain,[sort:'id']);


			//To get the Knowledge Dimension
			def KnowledgeDomainlist = KnowledgeDimension.list(sort:'id')
			Map<String, String> mapkdList = new HashMap<String,String>()
			def kdmnList = []

			KnowledgeDomainlist.each{ kd ->
				def flag = false
				def sKD = '${kd}'
				//println sKD
				strkdList.each{ strkd ->
					def sStrKD = '${strkd}'
					//println sKD + '  ' + sStrKD
					if(sKD.equals(sStrKD)){
						flag = true
					}
				}
				if(flag){
					mapkdList.put(sKD, 'true')
					kdmnList.add(sKD.toString())
				}
				else{
					mapkdList.put(sKD, 'false')
				}
			}

			LinkedHashSet pedaTechList
			if(kdmnList.size() > 0)
				pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct join p.knowledge kn where dm.name in (:dc) AND (ct.name in (:ld) AND kn.description in (:kd))  order by p.pedagogyTitle', [dc: objective.learningDomain.toString(),ld: objective.domainCategory.toString(),kd: kdmnList])
			else
				pedaTechList = PedagogyTechnique.executeQuery('select p from PedagogyTechnique as p inner join p.domain as dm join p.category as ct where dm.name in (:dc) AND (ct.name in (:ld))  order by p.pedagogyTitle', [dc: objective.learningDomain.toString(),ld: objective.domainCategory.toString()])

			def favPedaTechList = ImodUserPedagogyFavorite.findAllByImodUser(ImodUser.get(springSecurityService.principal.id))
			def selectionLine = '${objective.learningDomain} > ${objective.domainCategory}'
			if(kdmnList.size() > 0){
				if(kdmnList.size() == 1){
					selectionLine += ' > ${kdmnList.get(0)}'
				}else if(kdmnList.size() == 2){
					selectionLine += ' > ${kdmnList.get(0)} or ${kdmnList.get(1)}'
				}else{
					selectionLine += ' > Knowledge Dimension (${kdmnList.size()} Selections)'
				}
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
				lrnDomainlist: lrnDomainlist,
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
		if(params.fav == 'true'){
			def favTech = ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(imodUser,pedagogyTech)
			ImodUserPedagogyFavorite fav = favTech ? favTech :new ImodUserPedagogyFavorite()
			fav.pedagogyTechnique = pedagogyTech
			fav.imodUser = imodUser
			if(favTech){
				fav.delete()
			}else{
				fav.save()
			}
		}
		if(params.assign == 'true'){
			def learningObjective = LearningObjective.get(params.objectiveId)
			def assingTech = ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(learningObjective,pedagogyTech)
			ImodPedagogyAssign assign = assingTech ? assingTech : new ImodPedagogyAssign()
			assign.pedagogyTechnique = pedagogyTech
			assign.learningObjective = learningObjective
			if(assingTech){
				assign.delete()
			}else{
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

	/**
	 * Index, This action will used to load left panel on Pedagogy
	 */
	def index ={

		// TO get the Learning Objectives
		Imod imod = Imod.get(48)
		def objectiveList = LearningObjective.findAllByImod(imod)

		//To get the Domain Category
		LearningDomain domain = LearningDomain.get(1)
		def domainList = DomainCategory.findAllByDomain(domain);

		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()


		return [
			objectiveList : objectiveList,
			domainList: domainList,
			KnowledgeDomainlist: KnowledgeDomainlist
		]
	}
	/**
	 * To open Pedagogy Technique clone popup
	 */
	def clonePedagogyTech={
		render template:'pedagogyCloneTechnique', model:[pedagogyTech:PedagogyTechnique.get(params.techId),lrnDomainlist:LearningDomain.list(),domainList:DomainCategory.list(),KnowledgeDomainlist:KnowledgeDimension.list()]
	}
	/**
	 * cloneSaveTech, This action is used for validating cloned Technique.
	 */
	def cloneSaveTech={
		def flag = false
		def message = 'other'
		def pedagogyTechnique = PedagogyTechnique.read(params.pedagogy_tech_id)
		def pedagogyActivity
		def pedagogyReference
		def activityList = new ArrayList<PedagogyActivity>()
		def referenceList = new ArrayList<PedagogyReference>()
		params.each{
			if(it.key.startsWith('pedagogyActivity') && it.toString().contains(':')){
				pedagogyActivity = new PedagogyActivity(it.value)
				pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(it.value.duration)
				activityList.add(pedagogyActivity)
			}
			if(it.key.startsWith('pedagogyReference') && it.toString().contains(':')){
				pedagogyReference = new PedagogyReference(it.value)
				pedagogyReference.referenceType = PedagogyReferenceType.get(it.value.refeType)
				referenceList.add(pedagogyReference)
			}
		}
		if(PedagogyTechnique.findByPedagogyTitle(params.pedagogyTitle)?.pedagogyTitle.equals(params.pedagogyTitle)){
			flag = true
			message = 'title'
		}
		if(flag==false){
			println 'title changed'
			def activity = PedagogyActivity.findAllByPedagogyTechnique(pedagogyTechnique)
			def reference = PedagogyReference.findAllByPedagogyTechnique(pedagogyTechnique)
			if(pedagogyTechnique.pedagogyDescription.equals(params.pedagogyDescription)
				&& pedagogyTechnique.focus.sort() == PedagogyActivityFocus.getAll(params.list('focus')).sort()
				&& pedagogyTechnique.pedagogyMode.id.toString().equals(params.pedagogyModeId)
				&& activityList.size()==activity.size()
				&& referenceList.size()==reference.size()){
				println 'check'
				flag = true
			}
			if(flag){
				activityList.eachWithIndex {ele,index ->
					flag = ele.title.equals(activity.get(index).title) && ele.description.equals(activity.get(index).description) && ele.example.equals(activity.get(index).example)  && ele.material.equals(activity.get(index).material) && activity.get(index).pedagogyActivityDuration.id.toString().equals(ele.pedagogyActivityDuration.id.toString())?true:false
				}
			}
			if(flag){
				referenceList.eachWithIndex {ele,index ->
					flag = ele.title.equals(reference.get(index).title) && ele.description.equals(reference.get(index).description) && ele.referenceLinkISBN.equals(reference.get(index).referenceLinkISBN) && ele.author.equals(reference.get(index).author) && reference.get(index).referenceType.id.toString().equals(ele.referenceType.id.toString())?true:false
				}
			}
		}
		if(flag){
			println message
			render template:'pedagogyCloneTechnique', model:[errorMsg:message,pedagogyTech:PedagogyTechnique.get(params.pedagogy_tech_id),lrnDomainlist:LearningDomain.list(),domainList:DomainCategory.list(),KnowledgeDomainlist:KnowledgeDimension.list()]
//			render template:'pedagogyCloneTechnique',status:HttpServletResponse.SC_INTERNAL_SERVER_ERROR
		}else{
			cloneNewTechnique(params)
			render 'done'
		}
	}
	/**
	 * cloneNewTechnique, This method is used to Save cloned Technique
	 * @param params
	 * @return
	 */
	def cloneNewTechnique(params){
		def pedagogyActivity
		def pedagogyReference
		def pedTecInstance

		pedTecInstance = new PedagogyTechnique(params)
		pedTecInstance.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		if (!pedTecInstance.save()) {
			render(view: 'error', model: [pedagogyTechniqueInstance: pedTecInstance])
			return
		}
		params.each{
			if(it.key.startsWith('pedagogyActivity') && it.toString().contains(':')){
				pedagogyActivity = new PedagogyActivity(it.value)
				pedagogyActivity.pedagogyTechnique = pedTecInstance
				pedagogyActivity.pedagogyActivityDuration = PedagogyActivityDuration.get(it.value.duration)
				pedagogyActivity.save()
			}
			if(it.key.startsWith('pedagogyReference') && it.toString().contains(':')){
				pedagogyReference = new PedagogyReference(it.value)
				pedagogyReference.pedagogyTechnique = pedTecInstance
				pedagogyReference.referenceType = PedagogyReferenceType.get(it.value.refeType)
				pedagogyReference.save()
			}
		}
	}
}
