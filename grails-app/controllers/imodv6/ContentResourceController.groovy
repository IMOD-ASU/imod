package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ContentResourceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contentResourceInstanceList: ContentResource.list(params), contentResourceInstanceTotal: ContentResource.count()]
    }

    def create() {
        [contentResourceInstance: new ContentResource(params)]
    }

    def save() {
        def contentResourceInstance = new ContentResource(params)
        if (!contentResourceInstance.save(flush: true)) {
            render(view: "create", model: [contentResourceInstance: contentResourceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), contentResourceInstance.id])
        redirect(action: "show", id: contentResourceInstance.id)
    }

    def show(Long id) {
        def contentResourceInstance = ContentResource.get(id)
        if (!contentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "list")
            return
        }

        [contentResourceInstance: contentResourceInstance]
    }

    def edit(Long id) {
        def contentResourceInstance = ContentResource.get(id)
        if (!contentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "list")
            return
        }

        [contentResourceInstance: contentResourceInstance]
    }

    def update(Long id, Long version) {
        def contentResourceInstance = ContentResource.get(id)
        if (!contentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (contentResourceInstance.version > version) {
                contentResourceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contentResource.label', default: 'ContentResource')] as Object[],
                          "Another user has updated this ContentResource while you were editing")
                render(view: "edit", model: [contentResourceInstance: contentResourceInstance])
                return
            }
        }

        contentResourceInstance.properties = params

        if (!contentResourceInstance.save(flush: true)) {
            render(view: "edit", model: [contentResourceInstance: contentResourceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), contentResourceInstance.id])
        redirect(action: "show", id: contentResourceInstance.id)
    }

    def delete(Long id) {
        def contentResourceInstance = ContentResource.get(id)
        if (!contentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "list")
            return
        }

        try {
            contentResourceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contentResource.label', default: 'ContentResource'), id])
            redirect(action: "show", id: id)
        }
    }
}
