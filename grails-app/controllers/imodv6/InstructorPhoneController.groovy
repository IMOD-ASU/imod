package imodv6

import org.springframework.dao.DataIntegrityViolationException

class InstructorPhoneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [instructorPhoneInstanceList: InstructorPhone.list(params), instructorPhoneInstanceTotal: InstructorPhone.count()]
    }

    def create() {
        [instructorPhoneInstance: new InstructorPhone(params)]
    }

    def save() {
        def instructorPhoneInstance = new InstructorPhone(params)
        if (!instructorPhoneInstance.save(flush: true)) {
            render(view: "create", model: [instructorPhoneInstance: instructorPhoneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), instructorPhoneInstance.id])
        redirect(action: "show", id: instructorPhoneInstance.id)
    }

    def show(Long id) {
        def instructorPhoneInstance = InstructorPhone.get(id)
        if (!instructorPhoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "list")
            return
        }

        [instructorPhoneInstance: instructorPhoneInstance]
    }

    def edit(Long id) {
        def instructorPhoneInstance = InstructorPhone.get(id)
        if (!instructorPhoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "list")
            return
        }

        [instructorPhoneInstance: instructorPhoneInstance]
    }

    def update(Long id, Long version) {
        def instructorPhoneInstance = InstructorPhone.get(id)
        if (!instructorPhoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (instructorPhoneInstance.version > version) {
                instructorPhoneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'instructorPhone.label', default: 'InstructorPhone')] as Object[],
                          "Another user has updated this InstructorPhone while you were editing")
                render(view: "edit", model: [instructorPhoneInstance: instructorPhoneInstance])
                return
            }
        }

        instructorPhoneInstance.properties = params

        if (!instructorPhoneInstance.save(flush: true)) {
            render(view: "edit", model: [instructorPhoneInstance: instructorPhoneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), instructorPhoneInstance.id])
        redirect(action: "show", id: instructorPhoneInstance.id)
    }

    def delete(Long id) {
        def instructorPhoneInstance = InstructorPhone.get(id)
        if (!instructorPhoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "list")
            return
        }

        try {
            instructorPhoneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone'), id])
            redirect(action: "show", id: id)
        }
    }
}
