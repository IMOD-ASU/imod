package imodv6


import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class LearningObjectiveController {

    static allowedMethods = [updateDefinition:"POST", save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [learningObjectiveInstanceList: LearningObjective.list(params), learningObjectiveInstanceTotal: LearningObjective.count()]
    }

    def create() {
        [learningObjectiveInstance: new LearningObjective(params)]
    }

    def save() {
        def learningObjectiveInstance = new LearningObjective(params)
        if (!learningObjectiveInstance.save(flush: true)) {
            render(view: "create", model: [learningObjectiveInstance: learningObjectiveInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), learningObjectiveInstance.id])
        redirect(action: "show", id: learningObjectiveInstance.id)
    }

    def show(Long id) {
        def learningObjectiveInstance = LearningObjective.get(id)
        if (!learningObjectiveInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "list")
            return
        }

        [learningObjectiveInstance: learningObjectiveInstance]
    }

    def edit(Long id) {
        def learningObjectiveInstance = LearningObjective.get(id)
        if (!learningObjectiveInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "list")
            return
        }

        [learningObjectiveInstance: learningObjectiveInstance]
    }

    def update(Long id, Long version) {
        def learningObjectiveInstance = LearningObjective.get(id)
        if (!learningObjectiveInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (learningObjectiveInstance.version > version) {
                learningObjectiveInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'learningObjective.label', default: 'LearningObjective')] as Object[],
                        "Another user has updated this LearningObjective while you were editing")
                render(view: "edit", model: [learningObjectiveInstance: learningObjectiveInstance])
                return
            }
        }

        learningObjectiveInstance.properties = params

        if (!learningObjectiveInstance.save(flush: true)) {
            render(view: "edit", model: [learningObjectiveInstance: learningObjectiveInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), learningObjectiveInstance.id])
        redirect(action: "show", id: learningObjectiveInstance.id)
    }

    def delete(Long id) {
        def learningObjectiveInstance = LearningObjective.get(id)
        if (!learningObjectiveInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "list")
            return
        }

        try {
            learningObjectiveInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), id])
            redirect(action: "show", id: id)
        }
    }


    def ajaxSave() {
        def objectiveInstance = new LearningObjective(params)
        if (!objectiveInstance.save(flush: true)) {
            render ''
            return;
        }
        render template: '/content/chapterListTableTemplate', model: [chapter: objectiveInstance, count: imodv6.LearningObjective.countByImod(objectiveInstance.imod) - 1]
    }
	def updateDefinition(){
		def type=params.type
		def value=params.value
		render ([type:type, value:value] as JSON)
	}
}
