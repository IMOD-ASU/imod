package imodv6

/**
 * Describes a content topic that will be covered in the IMODS
 * @param topicTitle the title of the content to be covered
 * @param piorityCode gives a text description of the important of a topic
 * @param knowledgeDomainCode TODO what is this?
 * @param preReq describes what a student should have knowledge of before starting topic
 *
 * @param ContentResource a reference to the actual content for this topic ( TODO ? )
 * @param subTopic another topic that falls under the general over-arching topic
 */
class Content {
	String topicTitle
	ContentPriorityCode priorityCode
	ContentKnowledgeDomainCode knowledgeDomainCode
	Boolean preReq

	static hasMany = [
		resources: ContentResource,
		subTopic: String
	]

	static belongsTo = [
		objective: LearningObjective
	]

	static constraints = {
		preReq nullable: true
		knowledgeDomainCode nullable: true
		priorityCode nullable: true
	}

	static mapping = {
		version false
	}
}
