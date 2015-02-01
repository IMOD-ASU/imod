package imod

class AssessmentController {
    def index(Long id) {
		[
            currentImod: Imod.get(id),
			currentPage: 'assessment'
		]
	}
}
