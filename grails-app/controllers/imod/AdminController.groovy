package imod

class AdminController {


    def assessment() {

    	print AssessmentTechnique.list()

    	[
			currentPage: 'assessment'
		]
    }

     def pedagogy() {
    	[
			currentPage: 'pedagogy'
		]
    }
}
