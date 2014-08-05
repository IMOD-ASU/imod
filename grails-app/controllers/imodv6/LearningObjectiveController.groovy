package imodv6
import grails.converters.JSON

class LearningObjectiveController {

	static allowedMethods = [
		create:"GET",
		getDomainCategories:"GET",
		updateDefinition:"POST",
		performance: "GET",
		condition: "GET",
		criteria: "GET",
		content: "GET",
		save: "POST",
	]

	static defaultAction = "performance"

	def create(Long id) {
		// get the IMOD that this learning objective will be associated with
		def imodInstance = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(imod: imodInstance)
		// add the learning objective to the collection of learning objectives in the imod
		imodInstance.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod and the learning objective
		imodInstance.save(flush: true)
		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: "performance",
			id: id,
			learningObjectiveID:learningObjectiveInstance.id,
		)

	}

	// save learning objective data
	def save (Long id, Long learningObjectiveID, String pageType){
		def learningObjectiveInstance = LearningObjective.get(learningObjectiveID)
		if (pageType == 'performance') {
			learningObjectiveInstance.actionWord = ActionWord.findByActionWord(params.actionWord)
			redirect(
				action: "performance",
				id: id,
				learningObjectiveID: learningObjectiveID
			)
		}
		else if (pageType == 'condition') {
			if (params.LO_condition_type == 'Generic') {
				learningObjectiveInstance.condition=params.LO_generic
			}
			if (params.LO_condition_type == 'Custom') {
				learningObjectiveInstance.condition = params.LO_custom
			}
			learningObjectiveInstance.hideFromObjective = (params.LO_hide_from_Objective == 'on' ? true : false)
			redirect(
				action: "condition",
				id: id,
				learningObjectiveID: learningObjectiveID
			)
		}
		else{
			redirect(
				action: "performance",
				id: id,
				learningObjectiveID: learningObjectiveID
			)
		}
	}
	// begin editing a Learning Objective
	def edit (Long id) {
		render(
			action: "perfomance",
			learningObjectiveID: id
		)
	}
	def performance(Long id, Long learningObjectiveID) {
		// get relevant imod
		def imodInstance = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = learningObjectiveManager(imodInstance)


		// get all performance data to set in the Performance page
		def learningObjective = getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def selectedActionWord = learningObjective.actionWord
		def selectedDomainCategory = selectedActionWord?.category
		def selectedDomain = selectedDomainCategory?.domain

		// get list of Domains, categories and Actions, defaulting to the first of each in case none has been defined for the Learning Objective
		def domainList = LearningDomain.list()
		def categoriesList = selectedDomain?.domainList?:domainList[0].domainCategories.asList().sort {it.name}
		def actionWordList = selectedDomainCategory?.actionWords?:categoriesList[0].actionWords.asList().sort {it.actionWord}
		[
			imodInstance: imodInstance,
			learningObjectivesList: learningObjectivesList,
			currentPage: "performance",
			learningObjective: learningObjective,
			selectedActionWord: selectedActionWord,
			selectedDomainCategory: selectedDomainCategory,
			selectedDomain: selectedDomain,
			domainList: domainList,
			categoriesList: categoriesList,
			actionWordList: actionWordList,
		]
	}

	def content(Long id, Long learningObjectiveID) {
		def imodInstance = Imod.get(id)
		def learningObjectivesList = learningObjectiveManager(imodInstance)
		def learningObjectiveInstance = getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def contentList=imodInstance.contents.findAll(){it.parentContent==null}.sort(){it.id}
		def contents=[];
		if (contentList.size()==0){
			contentList.add(new Content(imod:imodInstance))
		}
		contentList.collect(contents) {
			getSubContent(it,learningObjectiveInstance)
		}
		contents=new groovy.json.JsonBuilder(contents).toString()
		contents=contents.replaceAll('"', /'/)
		[
			imodInstance: imodInstance,
			learningObjectivesList: learningObjectivesList,
			currentPage: "content",
			learningObjective: learningObjectiveInstance,
			contentList: contents,
		]
	}

	def condition(Long id, Long learningObjectiveID) {
		def imodInstance = Imod.get(id)
		def learningObjectivesList = learningObjectiveManager(imodInstance)
		def learningObjectiveInstance=getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def currentCondition=learningObjectiveInstance.condition?:LearningObjective.genericConditions[0]
		def isCustom=!((boolean)(LearningObjective.genericConditions.find{it==currentCondition}))
		def hideCondition=learningObjectiveInstance.hideFromObjective

		[
			imodInstance: imodInstance,
			learningObjectivesList: learningObjectivesList,
			currentPage:"condition",
			learningObjective:learningObjectiveInstance,
			currentCondition:currentCondition,
			isCustom:isCustom,
			hideCondition:hideCondition,
		]
	}

	def criteria(Long id, Long learningObjectiveID) {
		def imodInstance = Imod.get(id)
		def learningObjective = getDefaultLearningObjective(imodInstance, learningObjectiveID)
		[
			imodInstance: imodInstance,
			currentPage: "criteria",
			learningObjective: learningObjective,
		]
	}


	// gather the domain categories for each Learning Domain, return them sorted
	def getDomainCategories(String domainName) {
		def domain=LearningDomain.findByName(domainName)
		def value=domain.domainCategories.asList().sort {it.name}
		render ([
			value:value
		] as JSON)
	}

	private def getSubContent(Content current, LearningObjective objective){
		def listChildren=[]
		def topicSelected="topicNotSelected"
		if (objective.contents.contains(current) as Boolean){
			topicSelected="topicSelected" 
		}
		def currentID =current.id
		def idValue="content"+currentID
		def topicTitle='<span class="fa-stack">'+
			'<i class="checkboxBackground"></i>'+
			'<i class="fa fa-stack-1x checkbox" id="select'+currentID+'"></i> '+
			'</span> '+current.topicTitle
		def returnValue={}
		def rootNode=""
		if (current.parentContent==null){
			rootNode="rootNode"
		}
		if (current.subContents!=null){
			current.subContents.collect(listChildren){
				getSubContent(it,objective)
			}
			
		}
 
		returnValue=[
			id: idValue,
			text: topicTitle,
			li_attr:["class": topicSelected],
			a_attr:["class":rootNode],
			children:listChildren,
		]
		return returnValue

					
	}
	private def learningObjectiveManager(Imod imodInstance) {
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = imodInstance.learningObjectives.asList()

		// if there are no learning objectives create one
		if (learningObjectivesList.size() < 1) {
			create(id)
			// updates the list of all of the learning objectives for this imod
			learningObjectivesList = imodInstance.learningObjectives.asList()
		}

		return learningObjectivesList
	}

	private def getDefaultLearningObjective(Imod imodInstance, Long learningObjectiveID) {
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = imodInstance.learningObjectives.asList()
		if (learningObjectiveID == null) {
			learningObjectiveID = learningObjectivesList[0].id
		}
		return LearningObjective.get(learningObjectiveID)
	}
}
