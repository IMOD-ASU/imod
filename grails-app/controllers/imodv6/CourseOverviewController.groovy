package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseOverviewController {
	def index(Long id) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(controller: "imod", action: "list")
			return
		}

		[imodInstance: imodInstance]
	}
}
