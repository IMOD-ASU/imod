package imod

import grails.transaction.Transactional

@Transactional
class LearningObjectiveService {
    /**
     * Ensure that the Imod has at least one learning objective
     */
    def ensureLearningObjectiveExists(Long imodID) {
        def currentImod = Imod.get(imodID)
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
     * Ensure that objective is owned by a user
     */
    def getAllLearningObjectives(Long imodID) {
        ensureLearningObjectiveExists(imodID)
        def currentImod = Imod.get(imodID)
        return LearningObjective.findAllByImod(currentImod)
    }

    /**
     * Ensure that objective is owned by a user
     */
    def getLearningObjective(Long imodID, Long learningObjectiveID) {
        ensureLearningObjectiveExists(imodID)
        def currentImod = Imod.get(imodID)
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
