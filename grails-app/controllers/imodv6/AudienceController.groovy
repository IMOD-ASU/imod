package imodv6

import org.springframework.dao.DataIntegrityViolationException

class AudienceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [audienceInstanceList: Audience.list(params), audienceInstanceTotal: Audience.count()]
    }

    def create() {
        [audienceInstance: new Audience(params)]
    }

    def save() {
        def audienceInstance = new Audience(params)
        if (!audienceInstance.save(flush: true)) {
            render(view: "create", model: [audienceInstance: audienceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'audience.label', default: 'Audience'), audienceInstance.id])
        redirect(action: "show", id: audienceInstance.id)
    }

    def show(Long id) {
        def audienceInstance = Audience.get(id)
        if (!audienceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "list")
            return
        }

        [audienceInstance: audienceInstance]
    }

    def edit(Long id) {
        def audienceInstance = Audience.get(id)
        if (!audienceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "list")
            return
        }

        [audienceInstance: audienceInstance]
    }

    def update(Long id, Long version) {
        def audienceInstance = Audience.get(id)
        if (!audienceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (audienceInstance.version > version) {
                audienceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'audience.label', default: 'Audience')] as Object[],
                          "Another user has updated this Audience while you were editing")
                render(view: "edit", model: [audienceInstance: audienceInstance])
                return
            }
        }

        audienceInstance.properties = params

        if (!audienceInstance.save(flush: true)) {
            render(view: "edit", model: [audienceInstance: audienceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'audience.label', default: 'Audience'), audienceInstance.id])
        redirect(action: "show", id: audienceInstance.id)
    }

    def delete(Long id) {
        def audienceInstance = Audience.get(id)
        if (!audienceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "list")
            return
        }

        try {
            audienceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'audience.label', default: 'Audience'), id])
            redirect(action: "show", id: id)
        }
    }
}
