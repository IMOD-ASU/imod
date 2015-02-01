package imod

import grails.transaction.Transactional

@Transactional
class LearningObjectiveService {
    /**
     * Ensure that the Imod has at least one learning objective
     */
    def ensureLearningObjectiveExists(Imod currentImod) {
        // get a list of all of the learning objectives for this imod
        def learningObjectives = LearningObjective.findAllByImod(currentImod)

        // if there are no learning objectives create one
        if (learningObjectives.size() < 1) {
            create(currentImod.id)
            // updates the list of all of the learning objectives for this imod
            learningObjectives = LearningObjective.findAllByImod(currentImod)
        }
    }

    /**
     * Gets all learning objectives linked to selected Imod
     */
    def getAllByImod(Imod currentImod) {
        ensureLearningObjectiveExists(currentImod)
        return LearningObjective.findAllByImod(currentImod)
    }

    /**
     * Get a learning objective by id, and ensures that objective belongs to
     * selected Imods
     */
    def safeGet(Imod currentImod, Long learningObjectiveID) {
        ensureLearningObjectiveExists(currentImod)
        def objective
        // when there is not objective specified, pick first
        if (learningObjectiveID == null) {
            objective = currentImod.learningObjectives.first()
        }
        // otherwise get that objective
        else {
            objective = LearningObjective.findWhere(imod: currentImod, id: learningObjectiveID)
            // if that objective doesn't exist, get first
            if (objective == null) {
                objective = currentImod.learningObjectives.first()
            }
        }
        return objective
    }
}
