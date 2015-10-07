package imod

import grails.converters.JSON
import groovy.json.JsonSlurper

class ContentController {

	static allowedMethods = [
		addNewTopic: 'GET',
		saveTopic: 'POST',
		deleteTopic:'GET',
		updateHierarchy: 'POST',
		setLearningObjective: 'POST',
		saveResource: 'POST',
		getResourceTypes: 'GET',
	]

	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'content'
		]
	}

	def addNewTopic(Long id) {
		final currentImod = Imod.get(id)
		final dimensions = KnowledgeDimensionEnum.values()*.value
		final priorities = Content.priorities()

		render([
			dimensions: dimensions,
			priorities: priorities,
		] as JSON)
	}

	def saveTopic(String JSONData, Long id) {
		final currentImod = Imod.get(id)
		final jsonParser = new JsonSlurper()
		final contentData = jsonParser.parseText(JSONData)
		def contentID = 0
		def success = []
		def fail = []

		contentData.each() {

			/* If a new topic is added, create an ID. Otherwise, the topic already exists, so use its ID*/
			if(it.contentID == 'undefined'){
				def contentInstance = new Content(imod: currentImod, failOnError: true)

				contentInstance.save()
				if (!contentInstance) {
					contentInstance.errors.allErrors.each {
						log.error messageSource.getMessage(it,null)
					}
				}
				 contentID = contentInstance.id
			}else{
				 contentID = it.contentID.toLong()
			}

			final priority = it.priority
			final preReq = it.preReq
			final topicTitle = it.topicTitle

			def dimensions = []
			def contentInstance = Content.get(contentID)

			if (it.dimensions != '') {
				dimensions = it.dimensions.split(',').collect() {
					it.toUpperCase() as KnowledgeDimensionEnum
				}
			}

			contentInstance.dimensions = dimensions
			contentInstance.priority = priority
			contentInstance.preReq = preReq
			contentInstance.topicTitle = topicTitle
			contentInstance.save()

			if (!contentInstance) {
				contentInstance.errors.allErrors.each {
					log.error messageSource.getMessage(it,null)
				}
				fail.add(contentID)
			}
			else {
				success.add(contentID)
			}
		}

		render(
			[
				success: success,
				fail: fail
			] as JSON
		)
	}

	def deleteTopic(String contentIDs) {
		final contentIDList = new JsonSlurper().parseText(contentIDs)

		def success = []

		contentIDList.each() { item ->
			def deletedContent = Content.get(item)

			// remove any parent association
			def oldParent = deletedContent.parentContent
			if (oldParent != null) {
				oldParent.removeFromSubContents(deletedContent)
			}

			// remove all children association
			def childrenList = []
			def children = deletedContent.subContents
			if (children != null) {
				childrenList.addAll(children)

				childrenList.each() { child ->
					deletedContent.removeFromSubContents(child)
				}
			}

			def learningObjectiveList = []
			def learningObjectives = deletedContent.objectives

			// remove learning objective association
			if (learningObjectives != null) {
				learningObjectiveList.addAll(learningObjectives)
				learningObjectiveList.each() {
					deletedContent.removeFromObjectives(it)
				}
			}

			deletedContent.delete()
			success.add(item)
		}
		render(
			[
				result: success
			] as JSON
		)
	}

	def updateHierarchy() {
		final data = request.JSON
		final contents = data.topics
		final idArray = data.idArray

		contents.each() {
			buildHierarchy(it, null)
		}

		setLearningObjective(data.objId, idArray);

		render(
			[
				success: true
			] as JSON
		)
	}

	def buildHierarchy(content, Long parentID) {
		def childContent = Content.get(content.id)
		def oldParent = childContent.parentContent

		if (oldParent != null) {
			oldParent.removeFromSubContents(childContent)
		}

		if (parentID != null) {
			def parentContent = Content.get(parentID)
			parentContent.addToSubContents(childContent)
			childContent.parentContent = parentContent
		}
		else {
			childContent.parentContent = null
		}

		if (content.child != '') {
			content.child.each(){
				buildHierarchy(it, content.id)
			}
		}
	}

	def setLearningObjective(String objectiveID, idArray) {
		def learningObjectiveInstance = LearningObjective.get(objectiveID)

		if (idArray != null) {
			def contentList = idArray.collect {Content.get(it)}
			learningObjectiveInstance.contents.clear()
			contentList.each() {
				learningObjectiveInstance.addToContents(it)
			}

			// reset the definition
			learningObjectiveInstance.buildDefinition()

			learningObjectiveInstance.save(failOnError:true)
		}
		else {
			learningObjectiveInstance.contents = null
		}
	}

	def getResourceTypes() {
		def resources = Resource.resourceTypes()
		render([
			resources: resources,
		] as JSON)
	}

	def getResource(Long contentID) {
		def contentInstance = Content.get(contentID)
		def resources = contentInstance.getResources()
		render([
			resources: resources,
		] as JSON)
	}

	def saveResource(String JSONData) {
		final jsonParser = new JsonSlurper()
		final resourceData = jsonParser.parseText(JSONData)

		def success = []
		def fail = []

		resourceData.each() {

			final resourceName = it.resourceName
			final resourceDescription = it.resourceDescription
			final resourceType = it.resourceType
			def resourceInstance = null
			def resourceID = null

			if (it.resourceID == "null" ) {
				def contentInstance = Content.get(it.contentID)
				resourceInstance = new Resource(
					content: contentInstance,
					name: resourceName,
					description: resourceDescription,
					resourceType: resourceType
				)
				resourceInstance.save()
				resourceID = resourceInstance.id
				contentInstance.addToResources(resourceInstance)
			} else {
				resourceID = it.resourceID.toLong()
				resourceInstance = Resource.get(resourceID)

				resourceInstance.name = resourceName
				resourceInstance.description = resourceDescription
				resourceInstance.resourceType = resourceType
				resourceInstance.save()

			}

			if (!resourceInstance) {
				resourceInstance.errors.allErrors.each {
					log.error messageSource.getMessage(it,null)
				}
				fail.add(resourceID)
			}
			else {
				success.add(resourceID)
			}
		}

		render(
			[
				success: success,
				fail: fail
			] as JSON
		)
	}

	def deleteResource(String resourceIDs) {
		final resourceIDList = new JsonSlurper().parseText(resourceIDs)

		def success = []

		resourceIDList.each() {
			def deletedResource = Resource.get(it)
			deletedResource.delete()
			success.add(it)
		}
		render(
			[
				result: success
			] as JSON
		)
	}
}
