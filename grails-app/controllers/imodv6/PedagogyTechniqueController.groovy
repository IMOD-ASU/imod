package imodv6

import grails.converters.JSON
/* scaffold controller created by Grails for Create Read Update and Delete operation */
class PedagogyTechniqueController {

	static scaffold = PedagogyTechnique

	def save() {
		// FIXME imod id be dynamicly set
		def currentImod = Imod.get(48)
		def pedagogyTechniqueInstance = new PedagogyTechnique(params)

		if (!pedagogyTechniqueInstance.save()) {
			render(view: 'create', model: [pedagogyTechniqueInstance: pedagogyTechniqueInstance])
			return
		}

		flash.message = message(
			code: 'default.created.message',
			args: [
				message(
					code: 'imod.label',
					default: 'Imod'
				),
				currentImod
			]
		)

		redirect(
			action: 'list'
		)
	}


	def index(Long id) {

		// To get Learning Domain
		def domains = LearningDomain.list()

		// To get the Domain Category
		// FIXME learning domain should be dynamically set
		LearningDomain domain = LearningDomain.get(1)
		def domainList = DomainCategory.findAllByDomain(domain);

		//To get the Knowledge Dimension
		def KnowledgeDomainlist = KnowledgeDimension.list()

		[
			currentImod: Imod.get(id),
			domains : domains,
			domainList: domainList,
			KnowledgeDomainlist: KnowledgeDomainlist
		]
	}
}
