package imodv6

class CourseOverviewController {

	def index() {
		redirect(action: "list", params: params)
	}

	def show(Long id) {
		def imodInstance = Imod.get(id)
		if (!imodInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
			redirect(action: "list")
			return
		}

		render(view: "index", model: [imodInstance: imodInstance])
		[imodInstance: imodInstance]
	}
}
