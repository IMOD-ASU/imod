package imodv6

import grails.converters.JSON
import groovy.json.JsonSlurper

class ContentController {
	static allowedMethods=[
		addNewTopic:"GET",
		saveTopic:"POST",
		deleteTopic:"GET",
	]
	def addNewTopic(Long id)
	{
		def imodInstance=Imod.get(id)
		def contentInstance=new Content(imod:imodInstance)
		contentInstance.save(flush:true, failOnError:true)
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

	
}
