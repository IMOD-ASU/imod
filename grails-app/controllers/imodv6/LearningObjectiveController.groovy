package imodv6
import grails.converters.JSON

class LearningObjectiveController {
	static allowedMethods = [getDomainCategories:"GET", updateDefinition:"POST", performance: "GET", condition: "GET", criteria: "GET", content: "GET",]
	
	def performance(Long id) {
		[imodInstance: Imod.get(id),currentPage:"performance",domainList:LearningDomain.list()]
	}

	def content(Long id) {
		[imodInstance: Imod.get(id),currentPage:"content"]
	}

	def condition(Long id) {
		[imodInstance: Imod.get(id),currentPage:"condition"]
	}

	def criteria(Long id) {
		[imodInstance: Imod.get(id),currentPage:"criteria"]
	}
	// insert new values for performance, condition, etc into the learning Objective
	def updateDefinition(){
		def type=params.type
		def value=params.value
		def id=params.LOid
		render ([type:type,value:value] as JSON)
	}
	def getDomainCategories(){
		def domain=LearningDomain.findByName(params.domain)
		def value=domain.domainCategories
		render ([value:value] as JSON)
	}
}
