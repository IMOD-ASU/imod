package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CoursePolicyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [coursePolicyInstanceList: CoursePolicy.list(params), coursePolicyInstanceTotal: CoursePolicy.count()]
    }

    def create() {
        [coursePolicyInstance: new CoursePolicy(params)]
    }

    def save() {
        def coursePolicyInstance = new CoursePolicy(params)
        if (!coursePolicyInstance.save(flush: true)) {
            render(view: "create", model: [coursePolicyInstance: coursePolicyInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), coursePolicyInstance.id])
        redirect(action: "show", id: coursePolicyInstance.id)
    }

    def show(Long id) {
        def coursePolicyInstance = CoursePolicy.get(id)
        if (!coursePolicyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "list")
            return
        }

        [coursePolicyInstance: coursePolicyInstance]
    }

    def edit(Long id) {
        def coursePolicyInstance = CoursePolicy.get(id)
        if (!coursePolicyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "list")
            return
        }

        [coursePolicyInstance: coursePolicyInstance]
    }

    def update(Long id, Long version) {
        def coursePolicyInstance = CoursePolicy.get(id)
        if (!coursePolicyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (coursePolicyInstance.version > version) {
                coursePolicyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'coursePolicy.label', default: 'CoursePolicy')] as Object[],
                          "Another user has updated this CoursePolicy while you were editing")
                render(view: "edit", model: [coursePolicyInstance: coursePolicyInstance])
                return
            }
        }

        coursePolicyInstance.properties = params

        if (!coursePolicyInstance.save(flush: true)) {
            render(view: "edit", model: [coursePolicyInstance: coursePolicyInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), coursePolicyInstance.id])
        redirect(action: "show", id: coursePolicyInstance.id)
    }

    def delete(Long id) {
        def coursePolicyInstance = CoursePolicy.get(id)
        if (!coursePolicyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "list")
            return
        }

        try {
            coursePolicyInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'coursePolicy.label', default: 'CoursePolicy'), id])
            redirect(action: "show", id: id)
        }
    }
}
