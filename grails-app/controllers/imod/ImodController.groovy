package imod

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

	def list() {
		// get current user object
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)


		// create sample imod if it doesn't exist
		/*def sample = Imod.findByName('OBJECT ORIENTED SOFTWARE DEVELOPMENT - SAMPLE')

		if(sample == null){
			// create a new imod
			def newImod = new Imod(
				owner: currentUser,
				name: 'OBJECT ORIENTED SOFTWARE DEVELOPMENT - SAMPLE',
				url: 'https://piazza.com/asu/spring2014/cst100/home',			
				subjectArea: 'Computer Science',
				imodNumber: 'cst100',
				courseLocation: 'Peralta 213',
				overview: 'Introduces problem solving with a state-of-the-art programming language. Expressions, statements, basic control flow and methods. Data, data aggregation and usage.',
				courseSemester: 'Fall 2015',
				gradingProcedure: 'Attainment of course outcomes will be assessed based on your performance in various course activities throughout the semester. Table 1 shows a breakdown of the percentage allocation for each type of course assessment activity.',
				attendance: 'Regular on-time attendance in this course is expected. The presentations, activities and discussions that will occur during class sessions are designed to enhance your educational experience and help you achieve the identified course objectives.',

				classParticipation: 'Class Participation',
				professionalConduct: 'Professional Conduct',
				missedExams: 'missedExams',
				missedAssignments: 'Missed assignment section',
				creditHours: '3',
				timeRatio: '1:3',
				numberOfSeats: '30',
				audience: "48",

			)

			// update current user
			currentUser.addToImods(newImod)

			// save new imod and the updated user to database
			currentUser.save()


			def newInstructor = new Instructor(
	            firstName: "Dr. Ajay",
	            lastName: "Bansal",
	            email: "ajay.bansal@asu.edu",
	            role: "Professor",
	            officeHours: "Tuesdays & Thursdays | 9:00am – 10:0am or by appointment",
	            location: "Peralta 230V",
	            createdBy: newImod.id
	        )


	        // save new instructor and the updated user to database
	        newInstructor.save()

	        newInstructor = new Instructor(
	            firstName: "Rehman",
	            lastName: "Chughtai",
	            email: "Rehman.Chughtai@asu.edu",
	            role: "Assistant Professor",
	            officeHours: "by email appointment",
	            location: "Peralta 235 (Bullpen)",
	            createdBy: newImod.id
	        )


	        // save new instructor and the updated user to database
	        newInstructor.save()

		}*/	


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
			subjectArea: 'sample',
			imodNumber: '1'
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

		// FIXME unbundle variable assignment
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

		// FIXME unbundle variable assignment
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

	/*def sample(){
		


		// search for imods owned by current user
		def displayList = Imod.findAllWhere(owner: currentUser)
		[
			imodInstanceList: displayList,
			sort: 'name'
		]
	}*/

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
