package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ContentPriorityCodeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contentPriorityCodeInstanceList: ContentPriorityCode.list(params), contentPriorityCodeInstanceTotal: ContentPriorityCode.count()]
    }

    def create() {
        [contentPriorityCodeInstance: new ContentPriorityCode(params)]
    }

    def save() {
        def contentPriorityCodeInstance = new ContentPriorityCode(params)
        if (!contentPriorityCodeInstance.save(flush: true)) {
            render(view: "create", model: [contentPriorityCodeInstance: contentPriorityCodeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), contentPriorityCodeInstance.id])
        redirect(action: "show", id: contentPriorityCodeInstance.id)
    }

    def show(Long id) {
        def contentPriorityCodeInstance = ContentPriorityCode.get(id)
        if (!contentPriorityCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "list")
            return
        }

        [contentPriorityCodeInstance: contentPriorityCodeInstance]
    }

    def edit(Long id) {
        def contentPriorityCodeInstance = ContentPriorityCode.get(id)
        if (!contentPriorityCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "list")
            return
        }

        [contentPriorityCodeInstance: contentPriorityCodeInstance]
    }

    def update(Long id, Long version) {
        def contentPriorityCodeInstance = ContentPriorityCode.get(id)
        if (!contentPriorityCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (contentPriorityCodeInstance.version > version) {
                contentPriorityCodeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode')] as Object[],
                          "Another user has updated this ContentPriorityCode while you were editing")
                render(view: "edit", model: [contentPriorityCodeInstance: contentPriorityCodeInstance])
                return
            }
        }

        contentPriorityCodeInstance.properties = params

        if (!contentPriorityCodeInstance.save(flush: true)) {
            render(view: "edit", model: [contentPriorityCodeInstance: contentPriorityCodeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), contentPriorityCodeInstance.id])
        redirect(action: "show", id: contentPriorityCodeInstance.id)
    }

    def delete(Long id) {
        def contentPriorityCodeInstance = ContentPriorityCode.get(id)
        if (!contentPriorityCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "list")
            return
        }

        try {
            contentPriorityCodeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contentPriorityCode.label', default: 'ContentPriorityCode'), id])
            redirect(action: "show", id: id)
        }
    }
}
