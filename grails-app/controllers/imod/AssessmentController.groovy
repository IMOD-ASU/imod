<<<<<<< HEAD:grails-app/controllers/imodv6/AssessmentController.groovy
package imodv6
import grails.converters.JSON
=======
package imod
>>>>>>> 6bd18631b693067f02e882d30d55569328eeb34b:grails-app/controllers/imod/AssessmentController.groovy

class AssessmentController {

    static allowedMethods = [
        index: 'GET'

    ]
    /**
     * index called when Assessment tab loads
     * @param id
     */

    def index(Long id,  Long learningObjectiveID) {
		// get the selected imod
        def currentImod = Imod.get(id)

        // finds all the learning objective linked to this imod
        def learningObjectives = LearningObjective.findAllByImod(currentImod)

        // Search for selected learning objective
        // if there are no learning objectives set null
        // if there is no selected learning objective, pick first
        // otherwise get the objective
        def currentLearningObjective
        def objectivesForCurrentImod = currentImod.learningObjectives
        if (objectivesForCurrentImod.size() < 1) {
            currentLearningObjective = null
        }
        else if (learningObjectiveID == null) {
            currentLearningObjective = objectivesForCurrentImod.first()
        }
        else {
            currentLearningObjective = objectivesForCurrentImod.find { it.id == learningObjectiveID }
        }

        // get all of the filters used to find Assessment techniques
        def domainCategories = DomainCategory.list()
        def knowledgeDimensions = KnowledgeDimension.list()
        def learningDomains = LearningDomain.list()

        [
            currentImod: currentImod,
            currentLearningObjective: currentLearningObjective,
            currentPage: 'assessment',
            domainCategories: domainCategories,
            knowledgeDimensions: knowledgeDimensions,
            learningDomains: learningDomains,
            learningObjectives: learningObjectives,
        ]
	}
}
