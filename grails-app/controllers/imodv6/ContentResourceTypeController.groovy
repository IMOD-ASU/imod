package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ContentResourceTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contentResourceTypeInstanceList: ContentResourceType.list(params), contentResourceTypeInstanceTotal: ContentResourceType.count()]
    }

    def create() {
        [contentResourceTypeInstance: new ContentResourceType(params)]
    }

    def save() {
        def contentResourceTypeInstance = new ContentResourceType(params)
        if (!contentResourceTypeInstance.save(flush: true)) {
            render(view: "create", model: [contentResourceTypeInstance: contentResourceTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), contentResourceTypeInstance.id])
        redirect(action: "show", id: contentResourceTypeInstance.id)
    }

    def show(Long id) {
        def contentResourceTypeInstance = ContentResourceType.get(id)
        if (!contentResourceTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "list")
            return
        }

        [contentResourceTypeInstance: contentResourceTypeInstance]
    }

    def edit(Long id) {
        def contentResourceTypeInstance = ContentResourceType.get(id)
        if (!contentResourceTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "list")
            return
        }

        [contentResourceTypeInstance: contentResourceTypeInstance]
    }

    def update(Long id, Long version) {
        def contentResourceTypeInstance = ContentResourceType.get(id)
        if (!contentResourceTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (contentResourceTypeInstance.version > version) {
                contentResourceTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contentResourceType.label', default: 'ContentResourceType')] as Object[],
                          "Another user has updated this ContentResourceType while you were editing")
                render(view: "edit", model: [contentResourceTypeInstance: contentResourceTypeInstance])
                return
            }
        }

        contentResourceTypeInstance.properties = params

        if (!contentResourceTypeInstance.save(flush: true)) {
            render(view: "edit", model: [contentResourceTypeInstance: contentResourceTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), contentResourceTypeInstance.id])
        redirect(action: "show", id: contentResourceTypeInstance.id)
    }

    def delete(Long id) {
        def contentResourceTypeInstance = ContentResourceType.get(id)
        if (!contentResourceTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "list")
            return
        }

        try {
            contentResourceTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contentResourceType.label', default: 'ContentResourceType'), id])
            redirect(action: "show", id: id)
        }
    }
}
