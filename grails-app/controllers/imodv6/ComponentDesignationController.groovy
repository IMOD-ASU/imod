package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ComponentDesignationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [componentDesignationInstanceList: ComponentDesignation.list(params), componentDesignationInstanceTotal: ComponentDesignation.count()]
    }

    def create() {
        [componentDesignationInstance: new ComponentDesignation(params)]
    }

    def save() {
        def componentDesignationInstance = new ComponentDesignation(params)
        if (!componentDesignationInstance.save(flush: true)) {
            render(view: "create", model: [componentDesignationInstance: componentDesignationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), componentDesignationInstance.id])
        redirect(action: "show", id: componentDesignationInstance.id)
    }

    def show(Long id) {
        def componentDesignationInstance = ComponentDesignation.get(id)
        if (!componentDesignationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "list")
            return
        }

        [componentDesignationInstance: componentDesignationInstance]
    }

    def edit(Long id) {
        def componentDesignationInstance = ComponentDesignation.get(id)
        if (!componentDesignationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "list")
            return
        }

        [componentDesignationInstance: componentDesignationInstance]
    }

    def update(Long id, Long version) {
        def componentDesignationInstance = ComponentDesignation.get(id)
        if (!componentDesignationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (componentDesignationInstance.version > version) {
                componentDesignationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'componentDesignation.label', default: 'ComponentDesignation')] as Object[],
                          "Another user has updated this ComponentDesignation while you were editing")
                render(view: "edit", model: [componentDesignationInstance: componentDesignationInstance])
                return
            }
        }

        componentDesignationInstance.properties = params

        if (!componentDesignationInstance.save(flush: true)) {
            render(view: "edit", model: [componentDesignationInstance: componentDesignationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), componentDesignationInstance.id])
        redirect(action: "show", id: componentDesignationInstance.id)
    }

    def delete(Long id) {
        def componentDesignationInstance = ComponentDesignation.get(id)
        if (!componentDesignationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "list")
            return
        }

        try {
            componentDesignationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'componentDesignation.label', default: 'ComponentDesignation'), id])
            redirect(action: "show", id: id)
        }
    }
}
