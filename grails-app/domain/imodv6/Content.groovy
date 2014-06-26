package imodv6

class Content {
    String topicTitle
    ContentPriorityCode priorityCode
    ContentKnowledgeDomainCode knowledgeDomainCode
    Boolean preReq

    static hasMany = [resources: ContentResource, subTopic: String]

    static belongsTo = [objective: LearningObjective]

    static constraints = {
        preReq nullable: true
        knowledgeDomainCode nullable: true
        priorityCode nullable: true
    }

    static mapping = {
        version false
    }

    def String toString() {
        return topicTitle
    }
}
