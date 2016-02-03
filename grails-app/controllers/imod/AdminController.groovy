package imod

class AdminController {

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
    	[
			currentPage: 'pedagogy'
		]
    }
}
