package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ActionWordController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [actionWordInstanceList: ActionWord.list(params), actionWordInstanceTotal: ActionWord.count()]
    }

    def create() {
        [actionWordInstance: new ActionWord(params)]
    }

    def save() {
        def actionWordInstance = new ActionWord(params)
        if (!actionWordInstance.save(flush: true)) {
            render(view: "create", model: [actionWordInstance: actionWordInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), actionWordInstance.id])
        redirect(action: "show", id: actionWordInstance.id)
    }

    def show(Long id) {
        def actionWordInstance = ActionWord.get(id)
        if (!actionWordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "list")
            return
        }

        [actionWordInstance: actionWordInstance]
    }

    def edit(Long id) {
        def actionWordInstance = ActionWord.get(id)
        if (!actionWordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "list")
            return
        }

        [actionWordInstance: actionWordInstance]
    }

    def update(Long id, Long version) {
        def actionWordInstance = ActionWord.get(id)
        if (!actionWordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (actionWordInstance.version > version) {
                actionWordInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'actionWord.label', default: 'ActionWord')] as Object[],
                          "Another user has updated this ActionWord while you were editing")
                render(view: "edit", model: [actionWordInstance: actionWordInstance])
                return
            }
        }

        actionWordInstance.properties = params

        if (!actionWordInstance.save(flush: true)) {
            render(view: "edit", model: [actionWordInstance: actionWordInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), actionWordInstance.id])
        redirect(action: "show", id: actionWordInstance.id)
    }

    def delete(Long id) {
        def actionWordInstance = ActionWord.get(id)
        if (!actionWordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "list")
            return
        }

        try {
            actionWordInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'actionWord.label', default: 'ActionWord'), id])
            redirect(action: "show", id: id)
        }
    }
}
