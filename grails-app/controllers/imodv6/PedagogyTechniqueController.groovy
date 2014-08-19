package imodv6

import grails.converters.JSON
/* scaffold controller created by Grails for Create Read Update and Delete operation */
class PedagogyTechniqueController {

	static scaffold = PedagogyTechnique


	def save() {
		def imodInstance = Imod.get(48)
		def pedagogyTechniqueInstance = new PedagogyTechnique(params)
		if (!pedagogyTechniqueInstance.save()) {
			render(view: 'create', model: [pedagogyTechniqueInstance: pedagogyTechniqueInstance])
			return
		}


		flash.message = message(code: 'default.created.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
		redirect(action: 'list')
	}


	def index(Long id) {

		//To get Learning Domain
		def domains = LearningDomain.list()

		//To get the Domain Category
		LearningDomain domain = LearningDomain.get(1)
		def domainList = DomainCategory.findAllByDomain(domain);

		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()

		[
			imodInstance: Imod.get(id),
			domains : domains,
			domainList: domainList,
			KnowledgeDomainlist: KnowledgeDomainlist
		]
	}
}
