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

		def currentImod = new Imod(params)
		if (!currentImod.save()) {
			render(
				view: 'create',
				model: [
					currentImod: currentImod
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
				currentImod
			]
		)
		redirect(
			action: 'list'
		)
	}

	def update(Long id, Long version) {
		def currentImod = Imod.get(id)
		if (!currentImod) {
			flash.message = message(
				code: 'default.not.found.message',
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
			return
		}

		// FIXME handle a null imod seperate from handling versioning
		if (version != null) {
			if (currentImod.version > version) {
				currentImod.errors.rejectValue(
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
						currentImod: currentImod
					]
				)
				return
			}
		}

		currentImod.properties = params

		if (!currentImod.save()) {
			render(
				view: 'edit',
				model: [
					currentImod: currentImod
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
				currentImod
			]
		)
		redirect(
			controller: 'courseOverview',
			action: 'index',
			id: currentImod.id
		)


		def schedule = Schedule.findById(currentImod.properties.get("scheduleId"))

		schedule.scheduleWeekDays = null

		params.each{

			if(it.key.contains("scheduleWeekDays_"))
			{
				if (it.value.contains("on")){
					if(schedule.scheduleWeekDays == null)
					{
						currentImod.schedule.addToScheduleWeekDays(ScheduleWeekDays.get((it.key - "scheduleWeekDays_") as Integer))
					}
					else
					{
						currentImod.schedule.scheduleWeekDays << ScheduleWeekDays.get((it.key - "scheduleWeekDays_") as Integer)

					}
					currentImod.schedule.save()
					currentImod.save()
				}
			}
		}
	}

	def delete(Long id) {
		def currentImod = Imod.get(id)
		if (!currentImod) {
			flash.message = message(
				code: 'default.not.found.message',
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
			return
		}

		try {
			currentImod.delete()
			flash.message = message (
				code: 'default.deleted.message',
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
		catch (DataIntegrityViolationException e) {
			flash.message = message(
				code: 'default.not.deleted.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					currentImod
				]
			)
			redirect(
				action: 'show',
				id: id
			)
		}
	}
}
