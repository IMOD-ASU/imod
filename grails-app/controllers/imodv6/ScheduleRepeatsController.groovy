package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ScheduleRepeatsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scheduleRepeatsInstanceList: ScheduleRepeats.list(params), scheduleRepeatsInstanceTotal: ScheduleRepeats.count()]
    }

    def create() {
        [scheduleRepeatsInstance: new ScheduleRepeats(params)]
    }

    def save() {
        def scheduleRepeatsInstance = new ScheduleRepeats(params)
        if (!scheduleRepeatsInstance.save(flush: true)) {
            render(view: "create", model: [scheduleRepeatsInstance: scheduleRepeatsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), scheduleRepeatsInstance.id])
        redirect(action: "show", id: scheduleRepeatsInstance.id)
    }

    def show(Long id) {
        def scheduleRepeatsInstance = ScheduleRepeats.get(id)
        if (!scheduleRepeatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "list")
            return
        }

        [scheduleRepeatsInstance: scheduleRepeatsInstance]
    }

    def edit(Long id) {
        def scheduleRepeatsInstance = ScheduleRepeats.get(id)
        if (!scheduleRepeatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "list")
            return
        }

        [scheduleRepeatsInstance: scheduleRepeatsInstance]
    }

    def update(Long id, Long version) {
        def scheduleRepeatsInstance = ScheduleRepeats.get(id)
        if (!scheduleRepeatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scheduleRepeatsInstance.version > version) {
                scheduleRepeatsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats')] as Object[],
                          "Another user has updated this ScheduleRepeats while you were editing")
                render(view: "edit", model: [scheduleRepeatsInstance: scheduleRepeatsInstance])
                return
            }
        }

        scheduleRepeatsInstance.properties = params

        if (!scheduleRepeatsInstance.save(flush: true)) {
            render(view: "edit", model: [scheduleRepeatsInstance: scheduleRepeatsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), scheduleRepeatsInstance.id])
        redirect(action: "show", id: scheduleRepeatsInstance.id)
    }

    def delete(Long id) {
        def scheduleRepeatsInstance = ScheduleRepeats.get(id)
        if (!scheduleRepeatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "list")
            return
        }

        try {
            scheduleRepeatsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scheduleRepeats.label', default: 'ScheduleRepeats'), id])
            redirect(action: "show", id: id)
        }
    }
}
