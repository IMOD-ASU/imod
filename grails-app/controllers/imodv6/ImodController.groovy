package imodv6

import org.springframework.dao.DataIntegrityViolationException

class ImodController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService

    def currentUser

    def beforeInterceptor = {
        if (!springSecurityService.isLoggedIn()) {
            redirect(controller: 'login', action: 'auth')
            return false
        }
        currentUser = springSecurityService.currentUser.id

    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def displayList = Imod.executeQuery("select distinct i from Imod i where i.owner=" + currentUser)
        [imodInstanceList: displayList, imodInstanceTotal: displayList.size(), sort: "name"]
        //[imodInstanceList: Imod.list(params), imodInstanceTotal: Imod.count()]
    }

    def create() {
        [imodInstance: new Imod(params), currentUser: currentUser]
    }

    def save() {
        params.remove('owner')
        params.remove('owner.id')
        params.put('owner.id', currentUser)
        def imodInstance = new Imod(params)
        if (!imodInstance.save(flush: true)) {
            render(view: "create", model: [imodInstance: imodInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
        redirect(action: "list")
    }

    def show(Long id) {
        def imodInstance = Imod.get(id)
        if (!imodInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "list")
            return
        }

        [imodInstance: imodInstance]
    }

    def edit(Long id) {
        def imodInstance = Imod.get(id)

        if (!params.objectiveId) {
            params.objectiveId = LearningObjective.findByImod(imodInstance)?.id
        }
        if (!imodInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "list")
            return
        }
        [imodInstance: imodInstance, objectiveId: params.objectiveId, currentUser: currentUser]
    }

    def update(Long id, Long version) {
        def imodInstance = Imod.get(id)
        if (!imodInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (imodInstance.version > version) {
                imodInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'imod.label', default: 'Imod')] as Object[],
                        "Another user has updated this Imod while you were editing")
                render(view: "edit", model: [imodInstance: imodInstance])
                return
            }
        }

        imodInstance.properties = params

        if (!imodInstance.save(flush: true)) {
            render(view: "edit", model: [imodInstance: imodInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
        redirect(action: "list")
    }

    def delete(Long id) {
        def imodInstance = Imod.get(id)
        if (!imodInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "list")
            return
        }

        try {
            imodInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'imod.label', default: 'Imod'), imodInstance])
            redirect(action: "show", id: id)
        }
    }
}
