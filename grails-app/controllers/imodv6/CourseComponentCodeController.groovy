package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CourseComponentCodeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [courseComponentCodeInstanceList: CourseComponentCode.list(params), courseComponentCodeInstanceTotal: CourseComponentCode.count()]
    }

    def create() {
        [courseComponentCodeInstance: new CourseComponentCode(params)]
    }

    def save() {
        def courseComponentCodeInstance = new CourseComponentCode(params)
        if (!courseComponentCodeInstance.save(flush: true)) {
            render(view: "create", model: [courseComponentCodeInstance: courseComponentCodeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), courseComponentCodeInstance.id])
        redirect(action: "show", id: courseComponentCodeInstance.id)
    }

    def show(Long id) {
        def courseComponentCodeInstance = CourseComponentCode.get(id)
        if (!courseComponentCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "list")
            return
        }

        [courseComponentCodeInstance: courseComponentCodeInstance]
    }

    def edit(Long id) {
        def courseComponentCodeInstance = CourseComponentCode.get(id)
        if (!courseComponentCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "list")
            return
        }

        [courseComponentCodeInstance: courseComponentCodeInstance]
    }

    def update(Long id, Long version) {
        def courseComponentCodeInstance = CourseComponentCode.get(id)
        if (!courseComponentCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (courseComponentCodeInstance.version > version) {
                courseComponentCodeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'courseComponentCode.label', default: 'CourseComponentCode')] as Object[],
                          "Another user has updated this CourseComponentCode while you were editing")
                render(view: "edit", model: [courseComponentCodeInstance: courseComponentCodeInstance])
                return
            }
        }

        courseComponentCodeInstance.properties = params

        if (!courseComponentCodeInstance.save(flush: true)) {
            render(view: "edit", model: [courseComponentCodeInstance: courseComponentCodeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), courseComponentCodeInstance.id])
        redirect(action: "show", id: courseComponentCodeInstance.id)
    }

    def delete(Long id) {
        def courseComponentCodeInstance = CourseComponentCode.get(id)
        if (!courseComponentCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "list")
            return
        }

        try {
            courseComponentCodeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'courseComponentCode.label', default: 'CourseComponentCode'), id])
            redirect(action: "show", id: id)
        }
    }
}
