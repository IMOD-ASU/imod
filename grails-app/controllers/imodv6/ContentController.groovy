package imodv6

import grails.converters.JSON

class ContentController {
	static allowedMethods=[
		addNewTopic:"GET",
	]
	def addNewTopic(id)
	{
		def imodInstance=Imod.get(id)
		def contentInstance=new Content(imod:imodInstance)
		def dimensions=KnowledgeDimensionsEnum.values()
		def priorities=Content.priorities
		render([
			id: contentInstance.id,
			dimensions: dimensions,
			priorities: priorities,
		] as JSON) 
	}
}
