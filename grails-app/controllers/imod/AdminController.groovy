package imod

import grails.converters.JSON

class AdminController {

	static allowedMethods = [
		changeStatus: 'POST'
	]

    def assessment() {

    	def max = 10
    	def currentPageNumber = params.page ? params.page.toInteger() : 1

    	params.max = max
		params.offset = params.page ? (params.page.toInteger() - 1) * max : 0
		params.sort = 'id'
		params.order = 'asc'

		def techniques = AssessmentTechnique.list(params)

		def pages = Math.ceil(techniques.totalCount / max).toInteger()

    	[
			currentPage: 'assessment',
			techniques: techniques,
			pages: pages,
			currentPageNumber: currentPageNumber,
		]
    }

    def pedagogy() {
    	def max = 10
    	def currentPageNumber = params.page ? params.page.toInteger() : 1

    	params.max = max
		params.offset = params.page ? (params.page.toInteger() - 1) * max : 0
		params.sort = 'id'
		params.order = 'asc'

		def techniques = PedagogyTechnique.list(params)

		def pages = Math.ceil(techniques.totalCount / max).toInteger()

    	[
			currentPage: 'pedagogy',
			techniques: techniques,
			pages: pages,
			currentPageNumber: currentPageNumber,
		]
    }

    def users() {

    	def max = 10
    	def currentPageNumber = params.page ? params.page.toInteger() : 1

    	params.max = max
		params.offset = params.page ? (params.page.toInteger() - 1) * max : 0
		params.sort = 'id'
		params.order = 'asc'

    	def users = ImodUser.list(params)

    	def pages = Math.ceil(users.totalCount / max).toInteger()

    	[
			currentPage: 'users',
			users: users,
			pages: pages,
			currentPageNumber: currentPageNumber,
		]
    }

    def changeStatus() {

    	def success = false
    	def technique = null

    	if (params.type == 'assessment') {
    		technique = AssessmentTechnique.findById(params.id)
    		technique.isAdmin = params.isAdmin.toBoolean()
    		technique.save()
    		success = true

		} else if (params.type == 'pedagogy') {

			technique = PedagogyTechnique.findById(params.id)
			technique.isAdmin = params.isAdmin.toBoolean()
			technique.save()
			success = true

		} else {
			success = false
		}

    	render(
			[
				success: true
			] as JSON
		)
    }
}
