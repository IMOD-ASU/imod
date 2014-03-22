package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ScheduleRepeatsEveryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scheduleRepeatsEveryInstanceList: ScheduleRepeatsEvery.list(params), scheduleRepeatsEveryInstanceTotal: ScheduleRepeatsEvery.count()]
    }

    def create() {
        [scheduleRepeatsEveryInstance: new ScheduleRepeatsEvery(params)]
    }

    def save() {
        def scheduleRepeatsEveryInstance = new ScheduleRepeatsEvery(params)
        if (!scheduleRepeatsEveryInstance.save(flush: true)) {
            render(view: "create", model: [scheduleRepeatsEveryInstance: scheduleRepeatsEveryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), scheduleRepeatsEveryInstance.id])
        redirect(action: "show", id: scheduleRepeatsEveryInstance.id)
    }

    def show(Long id) {
        def scheduleRepeatsEveryInstance = ScheduleRepeatsEvery.get(id)
        if (!scheduleRepeatsEveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "list")
            return
        }

        [scheduleRepeatsEveryInstance: scheduleRepeatsEveryInstance]
    }

    def edit(Long id) {
        def scheduleRepeatsEveryInstance = ScheduleRepeatsEvery.get(id)
        if (!scheduleRepeatsEveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "list")
            return
        }

        [scheduleRepeatsEveryInstance: scheduleRepeatsEveryInstance]
    }

    def update(Long id, Long version) {
        def scheduleRepeatsEveryInstance = ScheduleRepeatsEvery.get(id)
        if (!scheduleRepeatsEveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scheduleRepeatsEveryInstance.version > version) {
                scheduleRepeatsEveryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery')] as Object[],
                          "Another user has updated this ScheduleRepeatsEvery while you were editing")
                render(view: "edit", model: [scheduleRepeatsEveryInstance: scheduleRepeatsEveryInstance])
                return
            }
        }

        scheduleRepeatsEveryInstance.properties = params

        if (!scheduleRepeatsEveryInstance.save(flush: true)) {
            render(view: "edit", model: [scheduleRepeatsEveryInstance: scheduleRepeatsEveryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), scheduleRepeatsEveryInstance.id])
        redirect(action: "show", id: scheduleRepeatsEveryInstance.id)
    }

    def delete(Long id) {
        def scheduleRepeatsEveryInstance = ScheduleRepeatsEvery.get(id)
        if (!scheduleRepeatsEveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "list")
            return
        }

        try {
            scheduleRepeatsEveryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery'), id])
            redirect(action: "show", id: id)
        }
    }
}
