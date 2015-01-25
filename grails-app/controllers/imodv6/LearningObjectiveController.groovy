package imodv6
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

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
	* Gets learning objectives for an Imod, auto generates a Learning Objective if there are none
	*/
	// FIXME rename and possibly combine learningObjectiveManager and getDefaultLearningObjective
	private def learningObjectiveManager(Imod currentImod) {
		// get a list of all of the learning objectives for this imod
		def learningObjectives = LearningObjective.findAllByImod(currentImod)

		// if there are no learning objectives create one
		if (learningObjectives.size() < 1) {
			create(currentImod.id)
			// updates the list of all of the learning objectives for this imod
			learningObjectives = LearningObjective.findAllByImod(currentImod)
		}

		return learningObjectives
	}

	/**
	* Gets the default learning objective
	*/
	private def getDefaultLearningObjective(Imod currentImod, Long learningObjectiveID) {
		def objective
		// when there is not objective specified, pick first
		if (learningObjectiveID == null) {
			objective = currentImod.learningObjectives.first()
		}
		// otherwise get that objective
		else {
			objective = LearningObjective.findWhere(imod: currentImod, id: learningObjectiveID)
			// if that objective doesn't exist, get first
			if (objective == null) {
				objective = currentImod.learningObjectives.first()
			}
		}
		return objective
	}

	/**
	 * Creates a Learning Objective
	 * @param  id of the IMOD that learning objective will be linked to
	 * @return    redirects to the performance tab to allow editing
	 */
	def create(Long id) {
		// get the IMOD that this learning objective will be associated with
		def currentImod = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(
			imod: currentImod,
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
		currentImod.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod and the learning objective
		currentImod.save()
		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: 'performance',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveInstance.id
			]
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
	// FIXME each page should have its own save
	def save (Long id, Long learningObjectiveID, String pageType){
		//gets the learning objective to be updated
		def learningObjectiveInstance = LearningObjective.get(learningObjectiveID)

		switch (pageType) {
			// if the user is saving performance page
			case 'performance':
				learningObjectiveInstance.actionWordCategory = ActionWordCategory.findByActionWordCategory(params.actionWordCategory)
				learningObjectiveInstance.performance = params.DCL
				learningObjectiveInstance.actionWord = params.actionWord
				break

			// if the user is saving the condition page
			case 'condition':
				if (params.conditionType == 'Generic') {
					learningObjectiveInstance.condition = params.genericCondition
				}
				if (params.conditionType == 'Custom') {
					learningObjectiveInstance.condition = params.customCondition
				}
				learningObjectiveInstance.hideFromLearningObjectiveCondition = (params.hideCondition == 'on' ? true : false)
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
			action: pageType,
			id: id,
			params: [
				learningObjectiveID: learningObjectiveID
			]
		)
	}

	/**
	 * This allows the user to set Performance measures for their learning objectives
	 * Peformance measures are created through action words
	 * Action Words are found by starting with a Learning Domain, choosing a domain category, the selecting an action word from a list
	 * @param  id                  IMOD that learning objective is associated with
	 * @param  learningObjectiveID ID of the specific learning objective being edited
	 */
	def performance(Long id, Long learningObjectiveID) {

		// get relevant imod
		def currentImod = Imod.get(id)

		// get a list of all of the learning objectives for this imod
		def learningObjectives = learningObjectiveManager(currentImod)

		// get all performance data to set in the Performance page
		def learningObjective = getDefaultLearningObjective(currentImod, learningObjectiveID)
		def selectedActionWordCategory = learningObjective.actionWordCategory
		def selectedDomainCategory = selectedActionWordCategory?.domainCategory
		def selectedDomain = selectedDomainCategory?.learningDomain

		// get list of Domains, categories and Actions, defaulting to the first of each in case none has been defined for the Learning Objective
		def domainList = LearningDomain.list()
		def domainCategoriesList = selectedDomain ? DomainCategory.findAllByLearningDomain(selectedDomain) : DomainCategory.findAllByLearningDomain(domainList.first())
		def actionWordCategoryList = selectedDomainCategory ? ActionWordCategory.findAllByDomainCategory(selectedDomainCategory) : ActionWordCategory.findAllByDomainCategory(domainCategoriesList.first())

		[
			currentImod:				currentImod,
			learningObjectives:		learningObjectives,
			currentPage:				'learning objective performance',
			learningObjective:			learningObjective,
			selectedActionWordCategory:	selectedActionWordCategory,
			selectedDomainCategory:		selectedDomainCategory,
			selectedDomain:				selectedDomain,
			domainList:					domainList,
			categoriesList:				domainCategoriesList,
			actionWordCategoryList:		actionWordCategoryList,
			actionWord: 				learningObjective.actionWord,
		]
	}

	def content(Long id, Long learningObjectiveID) {
		def currentImod = Imod.get(id)
		def learningObjectives = learningObjectiveManager(currentImod)
		def learningObjectiveInstance = getDefaultLearningObjective(currentImod, learningObjectiveID)
		def contentList = Content.findAllWhere(imod: currentImod, parentContent: null)
		def contents = [];
		if (contentList.size() < 1) {
			contentList.add(new Content(imod: currentImod))
		}
		contentList.collect(contents) {
			getSubContent(it,learningObjectiveInstance)
		}
		contents = new groovy.json.JsonBuilder(contents).toString()

		[
			currentImod:			currentImod,
			learningObjectives:	learningObjectives,
			currentPage:			'learning objective content',
			learningObjective:		learningObjectiveInstance,
			contentList:			contents,
		]
	}

	def condition(Long id, Long learningObjectiveID) {
		def currentImod					=  Imod.get(id)
		def learningObjectives		=  learningObjectiveManager(currentImod)
		def learningObjectiveInstance	=  getDefaultLearningObjective(currentImod, learningObjectiveID)
		def currentCondition			=  learningObjectiveInstance.condition?:LearningObjective.genericConditions[0]
		def isCustom					=! ((boolean) (LearningObjective.genericConditions.find{it == currentCondition}))
		def hideCondition				=  learningObjectiveInstance.hideFromLearningObjectiveCondition

		[
			currentImod:			currentImod,
			learningObjectives:	learningObjectives,
			currentPage:			'learning objective condition',
			learningObjective:		learningObjectiveInstance,
			currentCondition:		currentCondition,
			isCustom:				isCustom,
			hideCondition:			hideCondition,

		]
	}

	def criteria(Long id, Long learningObjectiveID) {
		def currentImod = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectives = learningObjectiveManager(currentImod)
		def learningObjective = getDefaultLearningObjective(currentImod, learningObjectiveID)

		[
			currentImod:			currentImod,
			currentPage:			'learning objective criteria',
			learningObjective:		learningObjective,
			learningObjectives:	learningObjectives,
		]
	}

	private def getSubContent(Content current, LearningObjective objective) {
		// FIXME remove html from controller
		def listChildren = []
		def topicSelected = 'topicNotSelected'
		if (objective.contents.contains(current) as Boolean) {
			topicSelected = 'topicSelected'
		}
		def currentID = current.id
		def idValue = 'content' + currentID
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
			current.subContents.collect(listChildren) {
				getSubContent(it, objective)
			}

		}

		returnValue = [
			id: idValue,
			text: topicTitle,
			li_attr: [
				'class': topicSelected
			],
			a_attr: [
				'class': rootNode
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
		def actionWordCategories = ActionWordCategory.findAllByDomainCategory(domainCategory)
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
		// temporarily replace the WordNet API with BigHugeLabsAPI
		def rest = new RestBuilder()
		def resp = rest.get('http://words.bighugelabs.com/api/2/2bbfecfa6c5f51f4cd4ff4562b75bdc5/' + actionWordCategory + '/json')

		render (
			[
				value: resp.json
			] as JSON
		)
	}
}
