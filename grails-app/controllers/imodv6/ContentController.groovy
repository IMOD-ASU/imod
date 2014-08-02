package imodv6

import grails.converters.JSON
import groovy.json.JsonSlurper

class ContentController {
	static allowedMethods=[
		addNewTopic:"GET",
		saveTopic:"POST",
		deleteTopic:"GET",
		updateHierarchy:"POST",
		setLearningObjective:"POST",
	]
	def addNewTopic(Long id)
	{
		def imodInstance=Imod.get(id)
		def contentInstance=new Content(imod:imodInstance, failOnError:true)
		contentInstance.save(flush:true)
		if (!contentInstance){
			contentInstance.errors.allErrors.each{
				log.error messageSource.getMessage(it,null)
			}
		}
		def dimensions=KnowledgeDimensionEnum.values()*.value
		def priorities=Content.priorities()
		render([
			id: contentInstance.id,
			dimensions: dimensions,
			priorities: priorities,
		] as JSON) 
	}
	def saveTopic(Long id, String JSONData){
		def jsonParser=new JsonSlurper()
		def contentData=jsonParser.parseText(JSONData)
		def imodInstance=Imod.get(id)
		def success=[]
		def fail=[]
		contentData.each(){
			def contentID=it.contentID.toLong()
			def dimensions=[]
			def priority=it.priority
			def preReq=it.preReq
			def topicTitle=it.topicTitle
			
			def contentInstance=Content.get(contentID)
			if (it.dimensions!=""){
				dimensions=it.dimensions.split(",").collect(){
					it.toUpperCase() as KnowledgeDimensionEnum
				}
			}

			contentInstance.dimensions=dimensions
			contentInstance.priority=priority
			contentInstance.preReq=preReq
			contentInstance.topicTitle=topicTitle
			contentInstance.save()
			
			if (!contentInstance){
				contentInstance.errors.allErrors.each{
					log.error messageSource.getMessage(it,null)
				}
				fail.add(contentID)
			}
			else {
				success.add(contentID)
			}
		};
		
		render([success:success, fail:fail] as JSON)
	}
	def deleteTopic(Long id, String contentIDs){
		def imodInstance=Imod.get(id)
		def success=[]
		def contentIDList=new JsonSlurper().parseText(contentIDs)

		
		contentIDList.each(){
			def deletedContent=Content.get(it)
			deletedContent.delete()
			success.add(it)
		}
		render([result:success] as JSON)
	}
	def updateHierarchy(Long contentID, Long parentID){
		def childContent=Content.get(contentID)
		if (parentID!="#"){
			def parentContent=Content.get(parentID)
			parentContent.addToSubContents(childContent)
		}
		else{
			childContent.parentContent=null
		}
	}
	def setLearningObjective(String contentIDs, Long objectiveID, Boolean toAdd){
		def jsonParser=new JsonSlurper()
		def contentIDList=jsonParser.parseText(contentIDs)
		def learningObjectiveInstance=LearningObjective.get(objectiveID)
		
		contentIDList.each {
			def contentInstance=Content.get(this.id)
			if (toAdd){
				learningObjectiveInstance.addToContents(contentInstance)
			}
			else{
				learningObjectiveInstance.removeFromContents(contentInstance)
			}
		}
	}
	
}
