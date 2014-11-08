package imodv6
import grails.converters.JSON
import rita.*

class LearningObjectiveController {

	static allowedMethods = [
		create: 					'GET',
		getDomainCategories: 		'GET',
		getActionWordCategories:	'GET',
		updateDefinition:			'POST',
		performance: 				'GET',
		condition: 					'GET',
		criteria: 					'GET',
		content: 					'GET',
		save: 						'POST',
	]

	// same as having index action redirect to performance tab
	static defaultAction = 'performance'

	/**
	 * Creates a Learning Objective
	 * @param  id of the IMOD that learning objective will be linked to
	 * @return    redirects to the performance tab to allow editing
	 */
	def create(Long id) {
		// get the IMOD that this learning objective will be associated with
		def imodInstance = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(
			imod: imodInstance,
			criteriaAccuracyEnabled:	true,
			criteriaQualityEnabled:		true,
			criteriaQuantityEnabled:	true,
			criteriaSpeedEnabled:		true,
			criteriaAccuracyHidden:		true,
			criteriaQualityHidden:		true,
			criteriaQuantityHidden:		true,
			criteriaSpeedHidden:		true,
		)
		// add the learning objective to the collection of learning objectives in the imod
		imodInstance.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod and the learning objective
		imodInstance.save()
		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: 'performance',
			id: id,
			learningObjectiveID: learningObjectiveInstance.id,
		)
	}

	/**
	 * Updates an existing learning objective
	 * @param  id                  IMOD that the learning objective is linked to
	 * @param  learningObjectiveID ID of the learning objective being updated
	 * @param  pageType            Describes the type (from what tab) of content is being updated at this time
	 * @return                     redirects back to the page that user was just on
	 */
	//TODO: add confirmation that the content was successfully saved
	def save (Long id, Long learningObjectiveID, String pageType){
		//gets the learning objective to be updated
		def learningObjectiveInstance = LearningObjective.get(learningObjectiveID)

		switch (pageType) {
			// if the user is saving performance page
			case 'performance':
				learningObjectiveInstance.actionWordCategory = ActionWordCategory.findByActionWordCategory(params.actionWordCategory)
				learningObjectiveInstance.performance = params.performance
				break

			// if the user is saving the condition page
			case 'condition':
				if (params.LO_condition_type == 'Generic') {
					learningObjectiveInstance.condition = params.LO_generic
				}
				if (params.LO_condition_type == 'Custom') {
					learningObjectiveInstance.condition = params.LO_custom
				}
				learningObjectiveInstance.hideFromLearningObjectiveCondition = (params.LO_hide_from_Objective == 'on' ? true : false)
				break

			// if the user is saving the criteria page
			case 'criteria':
				// check if the field is enabled
				// NOTE: when a check box is unchecked it returns null, hence the conditional
				learningObjectiveInstance.criteriaAccuracyEnabled	= (params.enableAccuracy	== null ? false : true)
				learningObjectiveInstance.criteriaQualityEnabled	= (params.enableQuality		== null ? false : true)
				learningObjectiveInstance.criteriaQuantityEnabled	= (params.enableQuantity	== null ? false : true)
				learningObjectiveInstance.criteriaSpeedEnabled		= (params.enableSpeed		== null ? false : true)

				// store the text content of each of the learning objective criteriae
				learningObjectiveInstance.criteriaAccuracy	= params.accuracy
				learningObjectiveInstance.criteriaQuality	= params.quality
				learningObjectiveInstance.criteriaQuantity	= params.quantity
				learningObjectiveInstance.criteriaSpeed		= params.speed

				// check if the field is enabled
				// NOTE: when a check box is unchecked it returns null, hence the conditional
				learningObjectiveInstance.criteriaAccuracyHidden	= (params.hideAccuracy	== null ? false : true)
				learningObjectiveInstance.criteriaQualityHidden		= (params.hideQuality	== null ? false : true)
				learningObjectiveInstance.criteriaQuantityHidden	= (params.hideQuantity	== null ? false : true)
				learningObjectiveInstance.criteriaSpeedHidden		= (params.hideSpeed		== null ? false : true)
				break

			// if page type is not recognized
			// TODO: add an error message
			default:
				pageType = 'performance'
		}
		// save all of the changes
		learningObjectiveInstance.save()

		// redirect to the correct page
		redirect(
			action:					pageType,
			id:						id,
			learningObjectiveID:	learningObjectiveID
		)
	}

	/**
	 * This allows the user to set Performance measures for their learning objectives
	 * Peformance measures are created through action words
	 * Action Words are found by starting with a Learning Domain, choosing a domain category, the selecting an action word from a list
	 * @param  id                  IMOD that learning objective is associated with
	 * @param  learningObjectiveID ID of the specific learning objective being edited
	 * @return                     Renders peformance page
	 */
	def performance(Long id, Long learningObjectiveID) {
		// get relevant imod
		def imodInstance = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = learningObjectiveManager(imodInstance)

		// get all performance data to set in the Performance page
		def learningObjective = getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def selectedActionWordCategory = learningObjective.actionWordCategory
		def selectedDomainCategory = selectedActionWordCategory?.category
		def selectedDomain = selectedDomainCategory?.domain

		// get list of Domains, categories and Actions, defaulting to the first of each in case none has been defined for the Learning Objective
		def domainList = LearningDomain.list()
		def domainCategoriesList = selectedDomain ? DomainCategory.findAllByLearningDomain(selectedDomain) : DomainCategory.findAllByLearningDomain(domainList.first())
		def actionWordCategoryList = selectedDomainCategory ? ActionWordCategory.findAllByCategory(selectedDomainCategory) : ActionWordCategory.findAllByCategory(domainCategoriesList.first())

		[
			imodInstance:				imodInstance,
			learningObjectivesList:		learningObjectivesList,
			currentPage:				'learning objective performance',
			learningObjective:			learningObjective,
			selectedActionWordCategory:	selectedActionWordCategory,
			selectedDomainCategory:		selectedDomainCategory,
			selectedDomain:				selectedDomain,
			domainList:					domainList,
			categoriesList:				domainCategoriesList,
			actionWordCategoryList:		actionWordCategoryList,
		]
	}

	/**
	 * [content description]
	 * @param  id                  [description]
	 * @param  learningObjectiveID [description]
	 * @return                     [description]
	 */
	def content(Long id, Long learningObjectiveID) {
		def imodInstance = Imod.get(id)
		def learningObjectivesList = learningObjectiveManager(imodInstance)
		def learningObjectiveInstance = getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def contentList = Content.findAllWhere(imod: imodInstance, parentContent: null)
		def contents = [];
		if (contentList.size() < 1) {
			contentList.add(new Content(imod: imodInstance))
		}
		contentList.collect(contents) {
			getSubContent(it,learningObjectiveInstance)
		}
		contents = new groovy.json.JsonBuilder(contents).toString()
		contents = contents.replaceAll('"', /'/)

		[
			imodInstance:			imodInstance,
			learningObjectivesList:	learningObjectivesList,
			currentPage:			'learning objective content',
			learningObjective:		learningObjectiveInstance,
			contentList:			contents,
		]
	}

	/**
	 * [condition description]
	 * @param  id                  [description]
	 * @param  learningObjectiveID [description]
	 * @return                     [description]
	 */
	def condition(Long id, Long learningObjectiveID) {
		def imodInstance				=  Imod.get(id)
		def learningObjectivesList		=  learningObjectiveManager(imodInstance)
		def learningObjectiveInstance	=  getDefaultLearningObjective(imodInstance, learningObjectiveID)
		def currentCondition			=  learningObjectiveInstance.condition?:LearningObjective.genericConditions[0]
		def isCustom					=! ((boolean) (LearningObjective.genericConditions.find{it == currentCondition}))
		def hideCondition				=  learningObjectiveInstance.hideFromLearningObjectiveCondition

		[
			imodInstance:			imodInstance,
			learningObjectivesList:	learningObjectivesList,
			currentPage:			'learning objective condition',
			learningObjective:		learningObjectiveInstance,
			currentCondition:		currentCondition,
			isCustom:				isCustom,
			hideCondition:			hideCondition,

		]
	}

	/**
	 * [criteria description]
	 * @param  id                  [description]
	 * @param  learningObjectiveID [description]
	 * @return                     [description]
	 */
	def criteria(Long id, Long learningObjectiveID) {
		def imodInstance = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = learningObjectiveManager(imodInstance)
		def learningObjective = getDefaultLearningObjective(imodInstance, learningObjectiveID)

		[
			imodInstance:			imodInstance,
			currentPage:			'learning objective criteria',
			learningObjective:		learningObjective,
			learningObjectivesList:	learningObjectivesList,
		]
	}

	private def getSubContent(Content current, LearningObjective objective) {
		// TODO remove html from controller
		def listChildren = []
		def topicSelected = "topicNotSelected"
		if (objective.contents.contains(current) as Boolean){
			topicSelected = "topicSelected"
		}
		def currentID = current.id
		def idValue = "content" + currentID
		def topicTitle = '<span class="fa-stack">' +
			'<i class="checkboxBackground"></i>'+
			'<i class="fa fa-stack-1x checkbox" id="select' + currentID + '"></i> ' +
			'</span> ' + current.topicTitle
		def returnValue = {}
		def rootNode = ""
		if (current.parentContent == null){
			rootNode = "rootNode"
		}
		if (current.subContents != null){
			current.subContents.collect(listChildren){
				getSubContent(it,objective)
			}

		}

		returnValue = [
			id: idValue,
			text: topicTitle,
			li_attr: [
				"class": topicSelected
			],
			a_attr: [
				"class":rootNode
			],
			children: listChildren
		]
		return returnValue
	}


	/**
	 * gather the Domain Categories for selected Learning Domain
	 * @param  domainName String that is the contents (or name) of a Learning Domain
	 * @return            sorted list of Domain Categories
	 */
	def getDomainCategories(String domainName) {
		// Find the selected learning domain
		def domain = LearningDomain.findByName(domainName)
		// get all related domain categories and sort by name
		def domainCategories = DomainCategory.findAllByLearningDomain(domain)
		// pass back domain categories as a json data structure
		render (
			[
				value: domainCategories
			] as JSON
		)
	}

	/**
	 * gather the Action Words for selected Domain Category
	 * @param  domainName String that is the contents (or name) of a Action Word Category
	 * @return            sorted list of Action Words
	 */
	def getActionWordCategories(String domainName) {
		// Find the selected learning domain
		def domainCategory = DomainCategory.findByName(domainName)
		// get all related domain categories and sort by name
		def actionWordCategories = ActionWordCategory.findAllByCategory(domainCategory)
		// pass back domain categories as a json data structure
		render (
			[
				value: actionWordCategories
			] as JSON
		)
	}

	/**
	 * gather the Action Words for selected Domain Category
	 * @param  actionWordCategory String that is the contents (or name) of a Domain Category
	 * @return            sorted list of Action Words
	 */
	def getActionWords(String actionWordCategory) {
		// import the wordnet database
		def wordNetAbsolutePath = request.getSession().getServletContext().getRealPath('../lib/WordNet-3.1')
		RiWordNet wordnet = new RiWordNet(wordNetAbsolutePath)

		// print all debug information for word in the terminal
		println wordnet.exists(actionWordCategory)
		println wordnet.getDescription(actionWordCategory, wordnet.getBestPos(actionWordCategory))
		def actionWords = wordnet.getAllSimilar(actionWordCategory, wordnet.getBestPos(actionWordCategory))
		println actionWords
		render (
			[
				value: actionWords
			] as JSON
		)
	}

	/**
	 * Gets learning objectives for an Imod, auto generates a Learning Objective if there are none
	 * @param  imodInstance Imod that Learning Objective are linked to
	 * @return              List of Learning Objective Domain Objects
	 */
	private def learningObjectiveManager(Imod imodInstance) {
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = LearningObjective.findAllByImod(imodInstance)

		// if there are no learning objectives create one
		if (learningObjectivesList.size() < 1) {
			create(imodInstance.id)
			// updates the list of all of the learning objectives for this imod
			learningObjectivesList = LearningObjective.findAllByImod(imodInstance)
		}

		return learningObjectivesList
	}

	/**
	 * [getDefaultLearningObjective description]
	 * @param  imodInstance        [description]
	 * @param  learningObjectiveID [description]
	 * @return                     [description]
	 */
	private def getDefaultLearningObjective(Imod imodInstance, Long learningObjectiveID) {
		def objective
		// when there is not objective specified, pick first
		if (learningObjectiveID == null) {
			objective = imodInstance.learningObjectives.first()
		}
		// otherwise get that objective
		else {
			objective = LearningObjective.findWhere(imod: imodInstance, id: learningObjectiveID)
			// if that objective doesn't exist, get first
			if (objective == null) {
				objective = imodInstance.learningObjectives.first()
			}
		}
		return objective
	}
}
