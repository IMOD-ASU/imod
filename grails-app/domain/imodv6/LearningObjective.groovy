package imodv6

class LearningObjective {
    String condition
    String performance
    String criteria
    String indicator
    String completeLearningObjective
    LearningDomain learningDomain
    DomainCategory domainCategory
    ActionWord actionWord
    LearningObjectiveCriteriaType criteriaType

    static belongsTo = [imod: Imod]

    static hasMany = [contents: Content, pedogoyTechniques: PedagogyTechnique]

    static transients = ['name']

    static constraints = {
        condition nullable: true
        performance nullable: true
        criteria nullable: true
        indicator nullable: true
        learningDomain nullable: true
        domainCategory nullable: true
        actionWord nullable: true
        criteriaType nullable: true
    }

    static mapping = {
        version false
    }
	

    def String toString() {
        return completeLearningObjective
    }

    def String getName() {
        return completeLearningObjective
    }
}
