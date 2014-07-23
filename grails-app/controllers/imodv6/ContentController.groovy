package imodv6

import grails.converters.JSON
import groovy.json.JsonSlurper

class ContentController {
	static allowedMethods=[
		addNewTopic:"GET",
		saveTopic:"POST",
	]
	def addNewTopic(Long id)
	{
		def imodInstance=Imod.get(id)
		def contentInstance=new Content(imod:imodInstance)
		contentInstance.save(flush:true, failOnError:true)
		contentInstance.errors.allErrors.each{
			println it
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
		def contentData=new JsonSlurper().parseText(JSONData)
		def imodInstance=Imod.get(id)
		
		render([result:"hi"] as JSON)
	}

}
