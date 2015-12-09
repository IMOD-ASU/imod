package imod

import grails.transaction.Transactional

@Transactional
class LearningObjectiveService {
    def create(Imod currentImod) {
        // create a learning objective, linked to the imod
        def newLearningObjective = new LearningObjective(
            imod: currentImod,
            criteriaAccuracyEnabled:	false,
            criteriaQualityEnabled:		false,
            criteriaQuantityEnabled:	false,
            criteriaSpeedEnabled:		false,
            criteriaAccuracyHidden:		false,
            criteriaQualityHidden:		false,
            criteriaQuantityHidden:		false,
            criteriaSpeedHidden:		false,
        )
        // add the learning objective to the collection of learning objectives in the imod
        currentImod.addToLearningObjectives(newLearningObjective)
        // saves the imod and the learning objective
        currentImod.save()

        // return the id
        newLearningObjective.id
    }

    /**
        Remove the selected learning objective, linked to the imod
    */
    def remove(Imod currentImod,Long learningObjectiveID) {
        def deletedObjective = LearningObjective.get(learningObjectiveID)
        def contentsList = []
        def contents = deletedObjective.contents

        // remove contents association
        if (contents != null) {
            contentsList.addAll(contents)
            contentsList.each {
                deletedObjective.removeFromContents(it)
            }
        }

        // remove pedagogyTechniques association
        def pedagogyTechniquesList = []
        def pedagogyTechniques = deletedObjective.pedagogyTechniques
        if (pedagogyTechniques != null) {
            pedagogyTechniquesList.addAll(pedagogyTechniques)
            pedagogyTechniquesList.each {
                deletedObjective.removeFromPedagogyTechniques(it)
            }
        }

        // remove assessmentTechniques association
        def assessmentTechniquesList = []
        def assessmentTechniques = deletedObjective.assessmentTechniques
        if (assessmentTechniques != null) {
            assessmentTechniquesList.addAll(assessmentTechniques)
            assessmentTechniquesList.each {
                deletedObjective.removeFromAssessmentTechniques(it)
            }
        }

        deletedObjective.delete(flush:true)
        def objectives = currentImod.learningObjectives.id

        // return objective
        objectives[0]
    }


    /**
     * Ensure that the Imod has at least one learning objective
     */
    def ensureLearningObjectiveExists(Imod currentImod) {
        // get a list of all of the learning objectives for this imod
        final learningObjectives = LearningObjective.findAllByImod(currentImod)

        // if there are no learning objectives create one
        if (learningObjectives.size() < 1) {
            create(currentImod)
        }
    }

    /**
     * Gets all learning objectives linked to selected Imod
     */
    LearningObjective getAllByImod(Imod currentImod) {
        LearningObjective.findAllByImod(currentImod)
    }

    /**
     * Get a learning objective by id, and ensures that objective belongs to
     * selected Imods
     */
    LearningObjective safeGet(Imod currentImod, Long learningObjectiveID) {
        LearningObjective objective
        // when there is not objective specified, pick first
        if (learningObjectiveID == null) {
            if(currentImod.learningObjectives.size() > 0){
                objective = currentImod.learningObjectives.first()
            } else {
                objective = null
            }

        }
        // otherwise get that objective
        else {
            objective = LearningObjective.findWhere(imod: currentImod, id: learningObjectiveID)
            // if that objective doesnt exist, get first
            if (objective == null) {
                objective = currentImod.learningObjectives.first()
            }
        }
        objective
    }
}
