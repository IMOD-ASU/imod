package imodv6

import org.springframework.dao.DataIntegrityViolationException

class CoursePolicyCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [coursePolicyCategoryInstanceList: CoursePolicyCategory.list(params), coursePolicyCategoryInstanceTotal: CoursePolicyCategory.count()]
    }

    def create() {
        [coursePolicyCategoryInstance: new CoursePolicyCategory(params)]
    }

    def save() {
        def coursePolicyCategoryInstance = new CoursePolicyCategory(params)
        if (!coursePolicyCategoryInstance.save(flush: true)) {
            render(view: "create", model: [coursePolicyCategoryInstance: coursePolicyCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), coursePolicyCategoryInstance.id])
        redirect(action: "show", id: coursePolicyCategoryInstance.id)
    }

    def show(Long id) {
        def coursePolicyCategoryInstance = CoursePolicyCategory.get(id)
        if (!coursePolicyCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "list")
            return
        }

        [coursePolicyCategoryInstance: coursePolicyCategoryInstance]
    }

    def edit(Long id) {
        def coursePolicyCategoryInstance = CoursePolicyCategory.get(id)
        if (!coursePolicyCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "list")
            return
        }

        [coursePolicyCategoryInstance: coursePolicyCategoryInstance]
    }

    def update(Long id, Long version) {
        def coursePolicyCategoryInstance = CoursePolicyCategory.get(id)
        if (!coursePolicyCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (coursePolicyCategoryInstance.version > version) {
                coursePolicyCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory')] as Object[],
                          "Another user has updated this CoursePolicyCategory while you were editing")
                render(view: "edit", model: [coursePolicyCategoryInstance: coursePolicyCategoryInstance])
                return
            }
        }

        coursePolicyCategoryInstance.properties = params

        if (!coursePolicyCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [coursePolicyCategoryInstance: coursePolicyCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), coursePolicyCategoryInstance.id])
        redirect(action: "show", id: coursePolicyCategoryInstance.id)
    }

    def delete(Long id) {
        def coursePolicyCategoryInstance = CoursePolicyCategory.get(id)
        if (!coursePolicyCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            coursePolicyCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'coursePolicyCategory.label', default: 'CoursePolicyCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
