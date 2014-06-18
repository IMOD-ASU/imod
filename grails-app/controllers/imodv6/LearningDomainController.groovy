package imodv6

import org.springframework.dao.DataIntegrityViolationException

class LearningDomainController {

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
        [learningDomainInstanceList: LearningDomain.list(params), learningDomainInstanceTotal: LearningDomain.count()]
    }

    def create() {
        [learningDomainInstance: new LearningDomain(params)]
    }

    def save() {
        def learningDomainInstance = new LearningDomain(params)
        if (!learningDomainInstance.save(flush: true)) {
            render(view: "create", model: [learningDomainInstance: learningDomainInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
        redirect(action: "show", id: learningDomainInstance.id)
    }

    def show(Long id) {
        def learningDomainInstance = LearningDomain.get(id)
        if (!learningDomainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "list")
            return
        }

        [learningDomainInstance: learningDomainInstance]
    }

    def edit(Long id) {
        def learningDomainInstance = LearningDomain.get(id)
        if (!learningDomainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "list")
            return
        }

        [learningDomainInstance: learningDomainInstance]
    }

    def update(Long id, Long version) {
        def learningDomainInstance = LearningDomain.get(id)
        if (!learningDomainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (learningDomainInstance.version > version) {
                learningDomainInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'learningDomain.label', default: 'LearningDomain')] as Object[],
                          "Another user has updated this LearningDomain while you were editing")
                render(view: "edit", model: [learningDomainInstance: learningDomainInstance])
                return
            }
        }

        learningDomainInstance.properties = params

        if (!learningDomainInstance.save(flush: true)) {
            render(view: "edit", model: [learningDomainInstance: learningDomainInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
        redirect(action: "show", id: learningDomainInstance.id)
    }

    def delete(Long id) {
        def learningDomainInstance = LearningDomain.get(id)
        if (!learningDomainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "list")
            return
        }

        try {
            learningDomainInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'learningDomain.label', default: 'LearningDomain'), learningDomainInstance])
            redirect(action: "show", id: id)
        }
    }
}
