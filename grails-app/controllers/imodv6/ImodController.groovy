package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ImodController {

	static allowedMethods = [
		save: 'POST',
		update: 'POST',
		delete: 'POST'
	]

	def springSecurityService


	def index() {
		redirect(
			action: 'list',
			params: params
		)
	}

	/**
	 * TODO: This appears to be written without using GORM
	 */
	def list(Integer max) {
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)
		// search for imods owned by current user
		def displayList = Imod.findAllWhere(owner: currentUser)
		[
			imodInstanceList: displayList,
			sort: 'name'
		]
	}

	def create() {
		// get the current user
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		// create a new imod
		def newImod = new Imod(
			owner: currentUser,
			name: 'New Imod',
			url: 'example.com',
			subjectArea: 'sample'
		)

		// update current user
		currentUser.addToImods(newImod)

		// save new imod and the updated user to database
		currentUser.save()

		// redirect to editing new Imod
		redirect(
			controller: 'CourseOverview',
			action: 'index',
			id: newImod.id
		)
	}

	def save() {
		// get the correct owner id from Spring Security
		params.remove('owner')
		params.remove('owner.id')
		params.put(
			'owner.id',
			springSecurityService.currentUser.id
		)

		def imodInstance = new Imod(params)
		if (!imodInstance.save()) {
			render(
				view: 'create',
				model: [
					imodInstance: imodInstance
				]
			)
			return
		}

		flash.message = message(
			code: 'default.created.message',
			args: [
				message(
					code: 'imod.label',
					default: 'Imod'
				),
				imodInstance
			]
		)
		redirect(
			action: 'list'
		)
	}

	def update(Long id, Long version) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(
				code: 'default.not.found.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					imodInstance
				]
			)
			redirect(
				action: 'list'
			)
			return
		}

		if (version != null) {
			if (imodInstance.version > version) {
				imodInstance.errors.rejectValue(
					'version',
					'default.optimistic.locking.failure',
					[
						message(
							code: 'imod.label',
							default: 'Imod'
						)
					] as Object[],
					'Another user has updated this Imod while you were editing'
				)
				render (
					view: 'edit',
					model: [
						imodInstance: imodInstance
					]
				)
				return
			}
		}

		imodInstance.properties = params

		if (!imodInstance.save()) {
			render(
				view: 'edit',
				model: [
					imodInstance: imodInstance
				]
			)
			return
		}

		flash.message = message (
			code: 'default.updated.message',
			args: [
				message (
					code: 'imod.label',
					default: 'Imod'
				),
				imodInstance
			]
		)
		redirect(
			controller: 'courseOverview',
			action: 'index',
			id: imodInstance.id
		)
	}

	def delete(Long id) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(
				code: 'default.not.found.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					imodInstance
				]
			)
			redirect(
				action: 'list'
			)
			return
		}

		try {
			imodInstance.delete()
			flash.message = message (
				code: 'default.deleted.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					imodInstance
				]
			)
			redirect(
				action: 'list'
			)
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(
				code: 'default.not.deleted.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					imodInstance
				]
			)
			redirect(
				action: 'show',
				id: id
			)
		}
	}
}
