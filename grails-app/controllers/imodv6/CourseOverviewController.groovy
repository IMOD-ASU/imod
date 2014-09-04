package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		[
			imodInstance: Imod.get(id),
			currentPage: 'course overview'
		]
	}
}
