package imodv6

/**
 * Describes a content topic that will be covered in the IMODS
 */
class Content {
	/*
	 ***********************
	 * Required Attributes *
	 ***********************
	 */

	/**
	 * TODO: What is this doing?
	 */
	Content parentContent


	/*
	 ***********************
	 * Optional Attributes *
	 ***********************
	 */

	/**
	 * title of the content to be covered
	 */
	String topicTitle

	/**
	 * gives a text description of the important of a topic
	 */
	String priority

	/**
	 * TODO: what is the boolean for?
	 * Should this be ```Boolean externalPreprerequisite
	 */
	Boolean preReq

	/**
	 * Todo: Why is Content Directly related to an Imod?
	 * If this is required why is it not a belongs to relationship?
	 */
	Imod imod

	/*
	 *****************
	 * Relationships *
	 *****************
	 */
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

	/*
	 *****************
	 * Configuration *
	 *****************
	 */

	/**
	 * Describes which attributes an contain a null (empty) value
	 */
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

	/**
	 * This is not versioned
	 */
	static mapping = {
		version false
	}

	static transients = ['priorities']


	/*
	 *************
	 * Functions *
	 *************
	 */

	/**
	 * Lists all of the possible priorities that a content could have
	 * @return array of priorities
	 */
	static List priorities() {
		def priorityList = [
			'Critical',
			'Very Important',
			'Good to Know'
		]
		return priorityList
	}

	/**
	 * [checkRecursion description]
	 * @param  currentContent [description]
	 * @param  contentToAdd   [description]
	 * @param  errors         [description]
	 * @return                [description]
	 */
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
