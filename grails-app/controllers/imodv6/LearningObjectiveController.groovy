package imodv6
import grails.converters.JSON

class LearningObjectiveController {

	static allowedMethods = [
		getDomainCategories:"GET", 
		updateDefinition:"POST", 
		performance: "GET", 
		condition: "GET", 
		criteria: "GET", 
		content: "GET",
	]
	// begin editing a Learning Objective
	def edit (Long id){
		render(action:"perfomance", learningObjectiveID:id)
	}
	def performance(Long id) {
		// get relevant imod
		def imodInstance = Imod.get(id)
		// get a list of all of the learning objectives for this imod
		def learningObjectivesList = imodInstance.learningObjectives.asList()
		[
			imodInstance: imodInstance,
			learningObjectivesList: learningObjectivesList,
			domainList:LearningDomain.list(),
			currentPage:"performance"
		]
	}

	def content(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage:"content"
		]
	}

	def condition(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage:"condition"
		]
	}

	def criteria(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage:"criteria"
		]
	}

	def create(Long id) {
		// get the IMOD that this learning objective will be associated with
		def imodInstance = Imod.get(id)
		// create a learning objective, linked to the imod
		def learningObjectiveInstance = new LearningObjective(imod: imodInstance).save()
		// add the learning objective to the collection of learning objectives in the imod
		imodInstance.addToLearningObjectives(learningObjectiveInstance)
		// saves the imod (and in theory the learning objective)
		imodInstance.save(flush: true)
		// redirects to the performance page to allow for newly created learning objective to be edited
		redirect(
			action: "performance",
			id: id
		)

	}
	// insert new values for performance, condition, etc into the learning Objective
	// TODO actuall save to database, currently just bouncing data back
	def updateDefinition(){
		def type=params.type
		def value=params.value
		def id=params.LOid
		render ([type:type,value:value] as JSON)
	}
	def getDomainCategories(){
		def domain=LearningDomain.findByName(params.domain)
		def value=domain.domainCategories.asList().sort {it.name}
		render ([value:value] as JSON)
	}
}
