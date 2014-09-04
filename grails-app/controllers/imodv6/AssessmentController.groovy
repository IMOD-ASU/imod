package imodv6

class AssessmentController {
    def index(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage: 'assessment'
		]
	}
}
