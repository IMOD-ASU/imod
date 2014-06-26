package imodv6

import org.springframework.dao.DataIntegrityViolationException

class DomainCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def springSecurityService

	def beforeInterceptor ={
		if(!springSecurityService.isLoggedIn()){
			redirect(controller:'login', action: 'auth')
			return false
		}
	}
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [domainCategoryInstanceList: DomainCategory.list(params), domainCategoryInstanceTotal: DomainCategory.count()]
    }

    def create() {
        [domainCategoryInstance: new DomainCategory(params)]
    }

    def save() {
        def domainCategoryInstance = new DomainCategory(params)
        if (!domainCategoryInstance.save(flush: true)) {
            render(view: "create", model: [domainCategoryInstance: domainCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
        redirect(action: "show", id: domainCategoryInstance.id)
    }

    def show(Long id) {
        def domainCategoryInstance = DomainCategory.get(id)
        if (!domainCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "list")
            return
        }

        [domainCategoryInstance: domainCategoryInstance]
    }

    def edit(Long id) {
        def domainCategoryInstance = DomainCategory.get(id)
        if (!domainCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "list")
            return
        }

        [domainCategoryInstance: domainCategoryInstance]
    }

    def update(Long id, Long version) {
        def domainCategoryInstance = DomainCategory.get(id)
        if (!domainCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (domainCategoryInstance.version > version) {
                domainCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'domainCategory.label', default: 'DomainCategory')] as Object[],
                          "Another user has updated this DomainCategory while you were editing")
                render(view: "edit", model: [domainCategoryInstance: domainCategoryInstance])
                return
            }
        }

        domainCategoryInstance.properties = params

        if (!domainCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [domainCategoryInstance: domainCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
        redirect(action: "show", id: domainCategoryInstance.id)
    }

    def delete(Long id) {
        def domainCategoryInstance = DomainCategory.get(id)
        if (!domainCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "list")
            return
        }

        try {
            domainCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'domainCategory.label', default: 'DomainCategory'), domainCategoryInstance])
            redirect(action: "show", id: id)
        }
    }
}
