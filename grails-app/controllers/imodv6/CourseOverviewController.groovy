package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		[imodInstance: Imod.get(id)]
	}

	def create() {
		def imodInstance=new Imod(params)
		render(view : "index", model : [imodInstance: imodInstance])
	}
}
