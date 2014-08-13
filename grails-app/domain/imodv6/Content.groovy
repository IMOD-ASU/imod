package imodv6

/**
 * Describes a content topic that will be covered in the IMODS
 * @param topicTitle the title of the content to be covered
 * @param piorityCode gives a text description of the important of a topic
 * @param knowledgeDomainCode TODO what is this?
 * @param preReq TODO: what is the boolean for?
 *
 * @param ContentResource a reference to the actual content for this topic ( TODO ? )
 * @param subTopic another topic that falls under the general over-arching topic
 */
class Content {
	String	topicTitle
	String	priority
	Boolean	preReq
	Imod	imod
	Content	parentContent


	static hasMany = [
		dimensions:		KnowledgeDimensionEnum,
		resources:		ContentResource,
		subTopic:		String,
		objectives:		LearningObjective,
		subContents:	Content,
	]

	static belongsTo = [
		LearningObjective,
		Content
	]

	static List priorities() {
		def priorityList = [
			'Critical',
			'Very Important',
			'Good to Know'
		]
		return priorityList
	}

	static constraints = {
		dimensions	nullable:	true
		preReq		nullable:	true
		priority	nullable:	true
		topicTitle	nullable:	true
		objectives	nullable:	true
		subContents	nullable:	true, validator: {
			val,
			obj,
			errors -> checkRecursion(val.parentContent,obj,errors)
		}
	}

	static mapping = {
		version false
	}

	static transients = ['priorities']

	def checkRecursion(currentContent, contentToAdd, errors) {
		if (currentContent == null) {
			return true
		}
		if (currentContent == contentToAdd) {
			errors.rejectValue('subContents', 'Cannot contain self')
		}
		else{
			return checkRecursion(currentContent.parentContent, contentToAdd, errors)
		}

	}
}
