package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ImodController {

	static allowedMethods = [
		save: "POST",
		update: "POST",
		delete: "POST"
	]

	def springSecurityService


	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		// search for imods owned by current user
		def displayList = Imod.findAllWhere(owner: currentUser)
		[
			imodInstanceList: displayList,
			imodInstanceTotal: displayList.size(),
			sort: "name"
		]
	}

	def create() {
		redirect(controller: "courseOverview", action: "create")
	}

	def save() {
		params.remove('owner')
		params.remove('owner.id')
		params.put('owner.id', springSecurityService.currentUser.id)
		def imodInstance = new Imod(params)
		if (!imodInstance.save(flush: true)) {
			render(view: "create", model: [imodInstance: imodInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
		redirect(action: "list")
	}

	def update(Long id, Long version) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (imodInstance.version > version) {
				imodInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'imod.label', default: 'Imod')] as Object[],
						"Another user has updated this Imod while you were editing")
				render(view: "edit", model: [imodInstance: imodInstance])
				return
			}
		}

		imodInstance.properties = params

		if (!imodInstance.save(flush: true)) {
			render(view: "edit", model: [imodInstance: imodInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
		redirect(action: "list")
	}

	def delete(Long id) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(action: "list")
			return
		}

		try {
			imodInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(action: "show", id: id)
		}
	}
}
