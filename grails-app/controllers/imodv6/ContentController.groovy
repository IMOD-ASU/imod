package imodv6

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.commons.CommonsMultipartFile

import java.text.SimpleDateFormat

class ContentController {

	static allowedMethods = [reloadContentTab: "GET", fetchResource: "POST", saveTopicSchedule: "POST", changePreReq: "POST", saveResource: "POST", saveTopic: "POST", save: "POST", addToChapter: "POST", saveObjectives: "POST", update: "POST", delete: "POST", removeAllObjectives: "POST", ajaxDelete: "POST", contentTab: "GET"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[contentInstanceList: Content.list(params), contentInstanceTotal: Content.count()]
	}

	def create() {
		[contentInstance: new Content(params)]
	}

	def save() {
		def contentInstance = new Content(params)
		if (!contentInstance.save(flush: true)) {
			render(view: "create", model: [contentInstance: contentInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'content.label', default: 'Content'), contentInstance.id])
		redirect(action: "show", id: contentInstance.id)
	}

	def show(Long id) {
		def contentInstance = Content.get(id)
		if (!contentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "list")
			return
		}

		[contentInstance: contentInstance]
	}

	def edit(Long id) {
		def contentInstance = Content.get(id)
		if (!contentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "list")
			return
		}

		[contentInstance: contentInstance]
	}

	def update(Long id, Long version) {
		def contentInstance = Content.get(id)
		if (!contentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (contentInstance.version > version) {
				contentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'content.label', default: 'Content')] as Object[],
						"Another user has updated this Content while you were editing")
				render(view: "edit", model: [contentInstance: contentInstance])
				return
			}
		}

		contentInstance.properties = params

		if (!contentInstance.save(flush: true)) {
			render(view: "edit", model: [contentInstance: contentInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'content.label', default: 'Content'), contentInstance.id])
		redirect(action: "show", id: contentInstance.id)
	}

	def delete(Long id) {
		def contentInstance = Content.get(id)
		if (!contentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "list")
			return
		}

		try {
			contentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'content.label', default: 'Content'), id])
			redirect(action: "show", id: id)
		}
	}

	def ajaxDelete(Long id) {
		List<Long> contentIds2 = []
		List<Long> content3 = []
		List content2 = [params.'contents[]']
		content2 = content2.flatten()
		if (content2) {
			content2.each {
				content3.add(it as Long)
			}
		}
		List<Long> contentIds = content3 ? content3 : [id]
		contentIds.each {
			contentIds2.add(Long.valueOf(it))
		}
		List<Content> contentList = Content.findAllByIdInList(contentIds2)
		if (!contentList) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), id])
			render ''
			return
		}
		try {
			contentList.each { Content content ->
				LearningObjective learningObjective = LearningObjective.createCriteria().get {
					contents {
						inList("id", contentList?.id)
					}
				}
				if (learningObjective) {
					learningObjective.removeFromContents(content)
					learningObjective.save(flush: true)
				}
				content.delete(flush: true)
			}
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'content.label', default: 'Content'), id])
			render "success"
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'content.label', default: 'Content'), id])
			render ''
		}
	}

	def contentTab(Long id) {
		Imod imod = id ? Imod.get(id) : null
		if (imod) {
			Long objectiveId = params.objectiveId ? params.long('objectiveId') : null
			List<LearningObjective> objectiveList = LearningObjective.findAllByImod(imod)
			Set<Content> remainingContent = []
			Set<Content> currentImodContentList = []
			objectiveList.each {
				currentImodContentList.addAll(it.contents);
				remainingContent.addAll(it.contents);
			}
			LearningObjective objective = objectiveId ? LearningObjective.get(objectiveId) : objectiveList ? objectiveList?.first() : null
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
			List<ContentKnowledgeDomainCode> knowledgeDomainCodes = ContentKnowledgeDomainCode.values()
			List<ContentPriorityCode> contentPriorityCodeTypeList = ContentPriorityCode.values()

			[
				topicDateForCurrentLearningObjectiveList: topicDateForCurrentLearningObjectiveList,
				learningObjectiveDates: learningObjectiveDates,
				remainingContent: remainingContent,
				imod: imod,
				imodInstance: imod,
				currentImodContentList: currentImodContentList,
				currentChapterContentList: contentList,
				chapter: objective,
				chapterCount: (objectiveList?.size() > 1),
				contentPriorityCodeTypeList: contentPriorityCodeTypeList,
				objectiveList: objectiveList,
				contentList: contentList,
				learningObjectiveTypeList: knowledgeDomainCodes
			]
		}
	}


	def reloadContentTab(Long id, Long objectiveId) {
		redirect(controller: 'imod', action: 'edit', id: id, params: [loadContentTab: true, objectiveId: objectiveId])
	}

	def saveObjectives(Long id, String name) {
		Imod imod = Imod.get(id)
		LearningObjective objective = new LearningObjective(completeLearningObjective: name)
		objective.imod = imod
		objective.save(flush: true)
		render "success"
	}

	def saveTopic(Long id, Long objectiveId, String topicTitle) {
		LearningObjective objective = LearningObjective.get(objectiveId)
		Content content = new Content(topicTitle: topicTitle)
		content.objective = objective
		content.save(flush: true)
		redirect(controller: 'imod', action: 'edit', id: id, params: [loadContentTab: true])
	}

	def removeAllObjectives(Long id) {
		Imod imod = Imod.get(id)
		List<LearningObjective> objectiveList = LearningObjective.findAllByImod(imod)
		List<Content> contentList = []
		objectiveList.each {
			contentList.addAll(it.contents)
		}
		List<imodv6.ContentSchedule> contentScheduleList = imodv6.ContentSchedule.findAllByContentInList(contentList)
		contentScheduleList.each {
			it.delete(flush: true)
		}
		objectiveList.each {
			it.delete(flush: true)
		}
		render "success"
	}

	def removeResource(Long resourceId) {
		ContentResource contentResource = resourceId ? ContentResource.get(resourceId) : null
		if (contentResource) {
			Content content = contentResource.content
			if (content) {
				content.removeFromResources(contentResource)
				content.save(flush: true)
			}
			contentResource.delete(flush: true)
		}
		render "success"
	}

	def removeTopicSchedule(Long topicScheduleId) {
		imodv6.ContentSchedule contentSchedule = topicScheduleId ? imodv6.ContentSchedule.get(topicScheduleId) : null
		if (contentSchedule) {
			contentSchedule.delete(flush: true)
		}
		render "success"
	}

	def saveTopicSchedule(ContentTopicScheduleCO contentTopicScheduleCO) {
		Imod imod = Imod.get(contentTopicScheduleCO.id)
		contentTopicScheduleCO.topicScheduleIds.eachWithIndex { obj, index ->
			imodv6.ContentSchedule contentSchedule = (obj == 0) ? new imodv6.ContentSchedule(imod: imod) : imodv6.ContentSchedule.get(obj)
			contentSchedule.content = contentTopicScheduleCO.scheduleTopicList.get(index) ? Content.get(contentTopicScheduleCO.scheduleTopicList.get(index)) : contentSchedule.content
			contentSchedule.startDate = contentTopicScheduleCO.scheduleDate_day.get(index) ? new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(contentTopicScheduleCO.dateCalc(index)) : null
			if (!contentSchedule.validate()) {
				println contentSchedule.errors.allErrors
			}
			contentSchedule.save(flush: true)
		}
		redirect(controller: 'imod', action: 'edit', id: contentTopicScheduleCO.id, params: [loadContentTab: true])
	}

	def saveResource(ContentResourceCO contentResourceCO) {
		Content content = Content.get(contentResourceCO.currentContentIdForResource)
		if (content) {
			contentResourceCO.resourceIds.eachWithIndex { obj, index ->
				ContentResource contentResource = (obj == 0) ? new ContentResource(name: contentResourceCO.resourceNames.get(index), content: content, resourceType: contentResourceCO.types.get(index) ? ContentResourceType.findByDescription(contentResourceCO.types.get(index)) : null) : ContentResource.get(obj)
				if (obj != 0) {
					contentResource.name = contentResourceCO.resourceNames.get(index) ?: contentResource.name
					contentResource.resourceType = contentResourceCO.types.get(index) ? ContentResourceType.findByDescription(contentResourceCO.types.get(index)) : null
				}
				if (contentResourceCO.types.get(index)) {
					if (contentResource.resourceType?.description == "URL") {
						contentResource.resourceURL = params.resourceURL
					} else if (contentResourceCO.files.size()) {
						CommonsMultipartFile commonsMultipartFile = contentResourceCO.files.get(index)
						if (commonsMultipartFile && !commonsMultipartFile.isEmpty()) {
							contentResource.fileName = commonsMultipartFile.originalFilename
							contentResource.fileContentType = commonsMultipartFile.contentType
							contentResource.file = commonsMultipartFile.bytes
						}
					}
				}
				if (!contentResource.validate()) {
					println contentResource.errors.allErrors
				}
				contentResource.save(flush: true)
				if (obj == 0) {
					content.addToResources(contentResource)
				}
				if (!content.validate()) {
					println content.errors.allErrors
				}
				content.save(flush: true)
			}
		}
		redirect(controller: 'imod', action: 'edit', id: contentResourceCO.id, params: [loadContentTab: true])
	}

	def changePreReq(Long contentId, String priorityCode, String learningObjectiveType, String topicTitle) {
		ContentPriorityCode contentPriorityCode = ContentPriorityCode.values().find { it?.description == priorityCode }
		ContentKnowledgeDomainCode knowledgeDomainCode = ContentKnowledgeDomainCode.values().find { it?.description == learningObjectiveType }
		Content content = Content.get(contentId)
		content.priorityCode = contentPriorityCode ? contentPriorityCode : content?.priorityCode
		content.knowledgeDomainCode = knowledgeDomainCode ? knowledgeDomainCode : content?.knowledgeDomainCode
		content.preReq = !content.preReq
		content.topicTitle = content.topicTitle
		content.save(flush: true)
		render "success"
	}

	def fetchResource(Long topicId) {
		String data = ""
		Content content = Content.get(topicId)
		content.resources.each {
			data += "<tr class='contentResources existingResources' dir='${it?.id}' id='contentResource-${it?.id}'><td><input type='hidden' value='${it.id}' name='resourceIds'/>${g.textField(class: 'resourceNames', name: 'resourceNames', value: it.name)}</td><td><input class=\"files\" type=\"file\" name=\"file\" ${(it.resourceType?.description == "URL") ? "style='display:none;' disabled='disabled'" : "disabled='disabled'"}/> ${g.textField(class: 'resourceURLs', name: 'resourceURLs', value: it.resourceURL, style: "${(it.resourceType?.description != "URL") ? "display:none;" : ''}")}</td><td>${g.select(noSelection: ['': 'Select one'], class: 'types', name: 'types', style: 'padding:0', from: imodv6.ContentResourceType.list()*.description, onchange: 'updateResourceURL()', value: it.resourceType?.description)}</td></tr>"
		}
		render data
	}

	def fetchGraph(Long id, Long objectiveId) {
		Imod imod = Imod.get(id)
		List<LearningObjective> objectiveList = LearningObjective.findAllByImod(imod)
		LearningObjective objective = objectiveId ? LearningObjective.get(objectiveId) : objectiveList ? objectiveList?.first() : null
		Set<Content> contentList = objective ? objective.contents : []
		def contentPriorityMap = LearningObjective.createCriteria().list() {
			createAlias("contents", "contents")
			projections {
				groupProperty("contents.priorityCode")
				count("contents.priorityCode")
			}
			eq("imod", imod)
			eq("id", objective?.id)
		}
		if (contentPriorityMap) {
			ContentPriorityCode.values().findAll { !(it?.description in contentList.collect { it.priorityCode?.description }) }.each {
				contentPriorityMap.add([it, 0])
			}
		}
		def topicInformationColumns = [['string', 'Topic'], ['number', 'Priority']]
		render template: '/content/graphView', model: [topicInformationColumns: topicInformationColumns, contentPriorityMap: contentPriorityMap]
	}
}
