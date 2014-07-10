package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def create() {

		render(
			view  : "index",
			model : [
				imodInstance: new Imod(params)
			]
		)

	}
}
