package imod

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class LearningObjectiveController {
	def learningObjectiveService

	static allowedMethods = [
		condition: 'GET',
		content: 'GET',
		create: 'GET',
		criteria: 'GET',
		getActionWordCategories: 'GET',
		getDomainCategories: 'GET',
		performance: 'GET',
		save: 'POST',
		saveDefinition: 'POST',
		remove: 'GET',
	]

	// same as having index action redirect to performance tab
	static defaultAction = 'performance'

	/**
	 * Creates a Learning Objective
	 * @param  id of the IMOD that learning objective will be linked to
	 * @return	redirects to the performance tab to allow editing
	 */
	def create(Long id) {

		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: 'performance',
			id: id,
			params: [
				learningObjectiveID: 'new'
			]
		)
	}

	/**
	 * Removes a Learning Objective
	 * @param  id of the IMOD that learning objective will be linked to
	 * @return	redirects to the performance tab to allow editing
	 */
	def remove(Long id, Long learningObjectiveID) {
		final currentImod = Imod.get(id)
		def learningObjectiveId = learningObjectiveService.remove(currentImod, learningObjectiveID)

		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: 'performance',
			id: id,
			params: [
				learningObjectiveID: learningObjectiveId
			]
		)
	}

	def saveDefinition(Long id, Long learningObjectiveID, String pageType) {
		final currentImod = Imod.get(id)
		def selectedLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		selectedLearningObjective.definition = params.customDefinition

		selectedLearningObjective.save()

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
	 * Updates an existing learning objective
	 * @param  id				  IMOD that the learning objective is linked to
	 * @param  learningObjectiveID ID of the learning objective being updated
	 * @param  pageType			Describes the type (from what tab) of content is being updated at this time
	 * @return					 redirects back to the page that user was just on
	 */
	//TODO: add confirmation that the content was successfully saved
	// FIXME each page should have its own save
	def save (Long id, String learningObjectiveID, String pageType) {
		//gets the learning objective to be updated
		final currentImod = Imod.get(id)

		if ( learningObjectiveID != null && (learningObjectiveID == 'new' || learningObjectiveID == '')) {

			def actionWord = ''
			if (params.actionWord == 'other') {
				actionWord = params.customActionWord
			} else if (params.actionWord == 'select') {
				actionWord = 'Enter the details here'
			} else {
				actionWord = params.actionWord
			}

			def selectedLearningObjective = new LearningObjective(
				imod: currentImod,
				criteriaAccuracyEnabled:	false,
				criteriaQualityEnabled:		false,
				criteriaQuantityEnabled:	false,
				criteriaSpeedEnabled:		false,
				criteriaAccuracyHidden:		false,
				criteriaQualityHidden:		false,
				criteriaQuantityHidden:		false,
				criteriaSpeedHidden:		false,
				actionWordCategory:  ActionWordCategory.findByActionWordCategory(params.actionWordCategory),
				actionWord: actionWord,
				performance: params.DCL,
				definition: actionWord

			)
			// add the learning objective to the collection of learning objectives in the imod
			currentImod.addToLearningObjectives(selectedLearningObjective)
			// saves the imod and the learning objective
			currentImod.save()

			// redirect to performance
			redirect(
				action: 'performance',
				id: id,
				params: [
					learningObjectiveID: selectedLearningObjective.id
				]
			)

		} else {

			def selectedLearningObjective = learningObjectiveService.safeGet(currentImod, Long.parseLong(learningObjectiveID))

			switch (pageType) {
				// if the user is saving performance page
				case 'performance':
					selectedLearningObjective.actionWordCategory = ActionWordCategory.findByActionWordCategory(params.actionWordCategory)
					selectedLearningObjective.performance = params.DCL
					if (params.actionWord == 'other') {
						selectedLearningObjective.actionWord = params.customActionWord
					} else if (params.actionWord == 'select') {
						selectedLearningObjective.actionWord = 'Enter the details here'
					} else {
						selectedLearningObjective.actionWord = params.actionWord
					}
					break

				// if the user is saving the condition page
				case 'condition':
					if (params.conditionType == 'Generic') {
						selectedLearningObjective.condition = params.genericCondition
					}
					if (params.conditionType == 'Custom') {
						selectedLearningObjective.condition = params.customCondition
					}
					selectedLearningObjective.hideFromLearningObjectiveCondition = params.hideCondition == 'on'
					if (LearningObjective.genericConditions.contains(selectedLearningObjective.condition)) {
						selectedLearningObjective.customCondition = ''
					} else {
						selectedLearningObjective.customCondition = selectedLearningObjective.condition
					}
					break

				// if the user is saving the criteria page
				case 'criteria':
					// check if the field is enabled
					// NOTE: when a check box is unchecked it returns null, hence the conditional
					selectedLearningObjective.criteriaAccuracyEnabled	= params.enableAccuracy	!= null
					selectedLearningObjective.criteriaQualityEnabled	= params.enableQuality	!= null
					selectedLearningObjective.criteriaQuantityEnabled	= params.enableQuantity != null
					selectedLearningObjective.criteriaSpeedEnabled		= params.enableSpeed != null

					// store the text content of each of the learning objective criteriae
					selectedLearningObjective.criteriaAccuracy	= params.accuracy
					selectedLearningObjective.criteriaQuality	= params.quality
					selectedLearningObjective.criteriaQuantity	= params.quantity
					selectedLearningObjective.criteriaSpeed		= params.speed

					// check if the field is enabled
					// NOTE: when a check box is unchecked it returns null, hence the conditional
					selectedLearningObjective.criteriaAccuracyHidden	= params.hideAccuracy != null
					selectedLearningObjective.criteriaQualityHidden		= params.hideQuality != null
					selectedLearningObjective.criteriaQuantityHidden	= params.hideQuantity != null
					selectedLearningObjective.criteriaSpeedHidden		= params.hideSpeed	!= null
					break

				// if page type is not recognized
				// TODO: add an error message
				default:
					pageType = 'performance'
			}
			// rebuild learning Objective definition
			selectedLearningObjective.buildDefinition()

			// save all of the changes
			selectedLearningObjective.save()

			// redirect to the correct page
			redirect(
				action: pageType,
				id: id,
				params: [
					learningObjectiveID: selectedLearningObjective.id
				]
			)

		}

	}

	/**
	 * This allows the user to set Performance measures for their learning objectives
	 * Peformance measures are created through action words
	 * Action Words are found by starting with a Learning Domain, choosing a domain category, the selecting an action word from a list
	 * @param  id				  IMOD that learning objective is associated with
	 * @param  learningObjectiveID ID of the specific learning objective being edited
	 */
	def performance(Long id, String learningObjectiveID) {
		// get relevant imod
		final currentImod = Imod.get(id)

		// get a list of all of the learning objectives for this imod
		final learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		if (learningObjectiveID == '') {

			def LoID = 'new'

			if (learningObjectives.size > 0) {
				LoID = learningObjectives[0].id
			}

			redirect(
  				controller: 'learningObjective',
  				action: 'performance',
  				id:  id,
  				params: [learningObjectiveID: LoID]
  			)

		} else if (learningObjectiveID != null && learningObjectiveID == 'new') {

			// get all performance data to set in the Performance page
			final currentLearningObjective = null
			final selectedActionWordCategory = null
			final selectedDomainCategory = null
			final selectedDomain = null

			// get list of Domains, categories and Actions, defaulting to the first of each in case none has been defined for the Learning Objective
			final domainList = LearningDomain.list()
			final domainCategoriesList = selectedDomain ? DomainCategory.findAllByLearningDomain(selectedDomain) : DomainCategory.findAllByLearningDomain(domainList.first())
			final actionWordCategoryList = selectedDomainCategory ? ActionWordCategory.findAllByDomainCategory(selectedDomainCategory) : ActionWordCategory.findAllByDomainCategory(domainCategoriesList.first())

			[
				actionWord: 				null,
				actionWordCategoryList:		actionWordCategoryList,
				categoriesList:				domainCategoriesList,
				currentImod:				currentImod,
				currentLearningObjective:	currentLearningObjective,
				currentPage:				'learning objective performance',
				domainList:					domainList,
				learningObjectives:			learningObjectives,
				selectedActionWordCategory:	selectedActionWordCategory,
				selectedDomain:				selectedDomain,
				selectedDomainCategory:		selectedDomainCategory,
			]

		} else {

			if (params.learningObjectiveID == null) {
				def LoID = 'new'

				if (learningObjectives.size > 0) {
					LoID = learningObjectives[0].id
				}

				redirect(
	  				controller: 'learningObjective',
	  				action: 'performance',
	  				id:  id,
	  				params: [learningObjectiveID: LoID]
	  			)

			} else {

				// get all performance data to set in the Performance page
				final currentLearningObjective = learningObjectiveService.safeGet(currentImod, Long.parseLong(learningObjectiveID))
				final selectedActionWordCategory = currentLearningObjective?.actionWordCategory
				final selectedDomainCategory = selectedActionWordCategory?.domainCategory
				final selectedDomain = selectedDomainCategory?.learningDomain

				// get list of Domains, categories and Actions, defaulting to the first of each in case none has been defined for the Learning Objective
				final domainList = LearningDomain.list()
				final domainCategoriesList = selectedDomain ? DomainCategory.findAllByLearningDomain(selectedDomain) : DomainCategory.findAllByLearningDomain(domainList.first())
				final actionWordCategoryList = selectedDomainCategory ? ActionWordCategory.findAllByDomainCategory(selectedDomainCategory) : ActionWordCategory.findAllByDomainCategory(domainCategoriesList.first())

				[
					actionWord: 				currentLearningObjective?.actionWord,
					actionWordCategoryList:		actionWordCategoryList,
					categoriesList:				domainCategoriesList,
					currentImod:				currentImod,
					currentLearningObjective:	currentLearningObjective,
					currentPage:				'learning objective performance',
					domainList:					domainList,
					learningObjectives:			learningObjectives,
					selectedActionWordCategory:	selectedActionWordCategory,
					selectedDomain:				selectedDomain,
					selectedDomainCategory:		selectedDomainCategory,
				]

			}
		}

	}

	def content(Long id, Long learningObjectiveID) {
		def currentImod = Imod.get(id)
		def learningObjectives = learningObjectiveService.getAllByImod(currentImod)
		def currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
		def contentList = Content.findAllWhere(imod: currentImod, parentContent: null)
		def contentList2 = contentList
		def contents = []

		contentList.collect(contents) {
			getSubContent(it, currentLearningObjective)
		}
		contents = new groovy.json.JsonBuilder(contents).toString()

		def text = null

		if (contentList != null) {
			text = '<ul id="contentTree">'
			contentList2.sort { a, b -> return a.id - b.id }each {
				text += getSubContentHTML(it, currentLearningObjective)
			}

			text += '</ul>'
		}

		[
			contentList:				contents,
			currentImod:				currentImod,
			currentLearningObjective:	currentLearningObjective,
			currentPage:				'learning objective content',
			learningObjectives:			learningObjectives,
			contentList2: 				text,
		]
	}

	def condition(Long id, Long learningObjectiveID) {
		def currentImod	= Imod.get(id)
		def learningObjectives = learningObjectiveService.getAllByImod(currentImod)
		def currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
		def currentCondition = currentLearningObjective?.condition ?: ''
		def currentCustomCondition = currentLearningObjective?.customCondition
		def isCustom = ((boolean) (LearningObjective.genericConditions.find {
			it == currentCondition
		}))
		def hideCondition = currentLearningObjective?.hideFromLearningObjectiveCondition

		[
			currentCondition:			currentCondition,
			currentCustomCondition:		currentCustomCondition,
			currentImod:				currentImod,
			currentLearningObjective:	currentLearningObjective,
			currentPage:				'learning objective condition',
			hideCondition:				hideCondition,
			isCustom:					isCustom,
			learningObjectives:			learningObjectives,
		]
	}

	def criteria(Long id, Long learningObjectiveID) {
		def currentImod = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectives = learningObjectiveService.getAllByImod(currentImod)

		def currentLearningObjective = learningObjectiveService.safeGet(currentImod, learningObjectiveID)

		[
			currentImod:				currentImod,
			currentPage:				'learning objective criteria',
			currentLearningObjective:	currentLearningObjective,
			learningObjectives:			learningObjectives,
		]
	}

	private getSubContent(Content current, LearningObjective objective) {
		// FIXME remove html from controller
		def listChildren = []
		def topicSelected = 'topicNotSelected'
		if (objective != null) {
			if (objective.contents.contains(current) as Boolean) {
				topicSelected = 'topicSelected'
			}
		}
		def currentID = current.id
		def idValue = 'content' + currentID
		def topicTitle = '<span class="fa-stack">' +
			'<i class="checkboxBackground"></i>' +
			'<i class="fa fa-stack-1x checkbox" id="select' + currentID + '"></i> ' +
			'</span> ' + current.topicTitle + ' <span class="delete-topic" data-id="' + currentID + '">x</span>'
		def returnValue = { }
		def rootNode = ''
		if (current.parentContent == null) {
			rootNode = 'rootNode'
		}
		if (current.subContents != null) {
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

		// return return value
		returnValue
	}

	private getSubContentHTML(Content current, LearningObjective objective) {
		def text = ''

		def topicSelected = ''
		if (objective != null) {
			if (objective.contents.contains(current) as Boolean) {
				topicSelected = 'fa-check'
			}
		}
		def currentID = current.id
		def topicTitle = '<span class="sub-content-tree fa-stack">' +
			'<i class="checkboxBackground"></i>' +
			'<i class="fa fa-stack-1x checkbox ' + topicSelected + '" id="select' + currentID + '"></i> ' +
			'</span> ' + current.topicTitle + ' <span class="delete-topic" data-id="' + currentID + '">x</span>'

		text += '<li data-itemid="' + currentID + '">' + topicTitle

		if (current.subContents != null) {
			text += '<ul>'
			current.subContents.sort { a, b -> return a.id - b.id }.each {
				text += getSubContentHTML(it, objective)
			}
			text += '</ul>'
		}

		text +=  '</li>'

		// return text
		text
	}

	/**
	 * gather the Domain Categories for selected Learning Domain
	 * @param  domainName String that is the contents (or name) of a Learning Domain
	 * @return			sorted list of Domain Categories
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
	 * @return			sorted list of Action Words
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
	 * @return			sorted list of Action Words
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
