package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ContentScheduleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contentScheduleInstanceList: ContentSchedule.list(params), contentScheduleInstanceTotal: ContentSchedule.count()]
    }

    def create() {
        [contentScheduleInstance: new ContentSchedule(params)]
    }

    def save() {
        def contentScheduleInstance = new ContentSchedule(params)
        if (!contentScheduleInstance.save(flush: true)) {
            render(view: "create", model: [contentScheduleInstance: contentScheduleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), contentScheduleInstance.id])
        redirect(action: "show", id: contentScheduleInstance.id)
    }

    def show(Long id) {
        def contentScheduleInstance = ContentSchedule.get(id)
        if (!contentScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "list")
            return
        }

        [contentScheduleInstance: contentScheduleInstance]
    }

    def edit(Long id) {
        def contentScheduleInstance = ContentSchedule.get(id)
        if (!contentScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "list")
            return
        }

        [contentScheduleInstance: contentScheduleInstance]
    }

    def update(Long id, Long version) {
        def contentScheduleInstance = ContentSchedule.get(id)
        if (!contentScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (contentScheduleInstance.version > version) {
                contentScheduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contentSchedule.label', default: 'ContentSchedule')] as Object[],
                          "Another user has updated this ContentSchedule while you were editing")
                render(view: "edit", model: [contentScheduleInstance: contentScheduleInstance])
                return
            }
        }

        contentScheduleInstance.properties = params

        if (!contentScheduleInstance.save(flush: true)) {
            render(view: "edit", model: [contentScheduleInstance: contentScheduleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), contentScheduleInstance.id])
        redirect(action: "show", id: contentScheduleInstance.id)
    }

    def delete(Long id) {
        def contentScheduleInstance = ContentSchedule.get(id)
        if (!contentScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "list")
            return
        }

        try {
            contentScheduleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contentSchedule.label', default: 'ContentSchedule'), id])
            redirect(action: "show", id: id)
        }
    }
}
