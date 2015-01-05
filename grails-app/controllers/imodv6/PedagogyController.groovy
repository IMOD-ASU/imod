package imodv6

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
		// FIXME domains should be treated as list object, not a string representing a list
		if(params.domain.toString().contains('[')) {
			params.domain.each{
				domain.add(it)
			}
		}
		else {
			domain.add(params.domain)
		}

		// FIXME domain categories should be treated as list object, not a string representing a list
		if(params.domainCategory.toString().contains('[')) {
			params.domainCategory.each{
				domainCategory.add(it)
			}
		}
		else {
			domainCategory.add(params.domainCategory)
		}

		// FIXME knowledge domains should be treated as list object, not a string representing a list
		if(params.kdomain.toString().contains('[')) {
			params.kdomain.each{
				kdomain.add(it)
			}
		}
		else if(params.kdomain) {
			kdomain.add(params.kdomain)
		}

		// find all techniques where the Domain Category or the Learning Domain
		// is the same fo the learning objective
		def pedagogyTechniqueList = PedagogyTechnique.withCriteria(uniqueResult: true) {
			or {
				domainCategory {
					eq("name", objective.actionWordCategory.domainCategory.learningDomain.toString())
				}
				learningDomain {
					eq("name", objective.actionWordCategory.domainCategory.toString())
				}
			}
		}

		def selectionLine = 'Domain (${domain.size()} Selections)'
		selectionLine += ' > Domain Category (${domainCategory.size()} Selections)'
		selectionLine += ' > Knowledge Dimension (${kdomain.size()} Selections)'

		[
			selectionLine: selectionLine,
			pedagogyTechniqueList: pedagogyTechniqueList,
			objectiveId: params.objectiveId,
			userId: ImodUser.get(springSecurityService.principal.id)
		]
	}
	/**
	 * addNewTechnique, is used to create new Technique
	 */
	def addNewTechnique(Long id) {
		def pedagogyTechniqueList = new PedagogyTechnique(params)
		pedagogyTechniqueList.pedagogyMode = PedagogyMode.get(params.pedagogyModeId)
		pedagogyTechniqueList.save()

		redirect(
			action: 'index',
			id: id
		)
	}

	/**
	 * index action is called when used click on pedagogy tab from main page.
	 * @param id
	 */
	// TODO test if no learning objectives exist
	// TODO test if no domain category is set for learning objective
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
		// FIXME Learning Domain id should be dynamically set via param
		def domain = LearningDomain.first()
		def domainList = DomainCategory.findAllByLearningDomain(domain);


		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()
		Map<String, String> mapKnowledgeDomainList = new HashMap<String,String>()
		def kdmnList = []

		for (knowledgeDomain in knowledgeDomainList) {
			mapKnowledgeDomainList.put(knowledgeDomain.toString(), 'true')
			kdmnList.add(knowledgeDomain.toString().toString())
		}

		// find all techniques where the Domain Category or the Learning Domain
		// is the same fo the learning objective
		def pedagogyTechniqueList = PedagogyTechnique.withCriteria(uniqueResult: true) {
			or {
				domainCategory {
					eq("name", objective.actionWordCategory.domainCategory.learningDomain.toString())
				}
				learningDomain {
					eq("name", objective.actionWordCategory.domainCategory.toString())
				}
			}
		}

		//def favPedaTechList = ImodUserPedagogyFavorite.findAllByImodUser(ImodUser.get(springSecurityService.principal.id))
		// def selectionLine = '${objective.learningDomain} > ${objective.domainCategory}'
		def selectionLine = ' > Knowledge Dimension (${kdmnList.size()} Selections)'

		[
			// dc: objective.domainCategory,
			// dmn: objective.learningDomain,
			chapter: objective,
			chapterCount: (objectiveList?.size() > 1),
			contentList: contentList,
			contentPriorityCodeTypeList: contentPriorityCodeTypeList,
			contentTitles: contentTitles,
			currentChapterContentList: contentList,
			currentImod: imod,
			currentImodContentList: currentImodContentList,
			currentPage: 'pedagogy',
			domainList: domainList,
			//favPedaTechList: favPedaTechList,
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

	def favoriteTechnique(Long id, Long objectiveId) {
		def imodUser = ImodUser.get(springSecurityService.principal.id)
		def pedagogyTech = PedagogyTechnique.get(params.pedtecID)
		// def favTech = ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(imodUser,pedagogyTech)
		// ImodUserPedagogyFavorite fav = favTech ? favTech :new ImodUserPedagogyFavorite()
		// fav.pedagogyTechnique = pedagogyTech
		// fav.imodUser = imodUser
		// if(favTech) {
		// 	fav.delete()
		// }
		// else {
		// 	fav.save()
		// }
		redirect(
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}

	def assignTechnique(Long id, Long objectiveId) {
		def imodUser = ImodUser.get(springSecurityService.principal.id)
		def pedagogyTech = PedagogyTechnique.get(params.pedtecID)
		// def learningObjective = LearningObjective.get(params.objectiveId)
		// def assingTech = ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(learningObjective,pedagogyTech)
		// ImodPedagogyAssign assign = assingTech ? assingTech : new ImodPedagogyAssign()
		// assign.pedagogyTechnique = pedagogyTech
		// assign.learningObjective = learningObjective
		// if(assingTech) {
		// 	assign.delete()
		// }
		// else {
		// 	assign.save()
		// }
		redirect(
			action: 'index',
			id: id,
			params: [
				objectiveId: objectiveId
			]
		)
	}
}
