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
		/**
		 * These are the various Knowledge Dimensio categories that a Content topic can belong to (many)
		 */
		dimensions:		KnowledgeDimensionEnum,

		/**
		 * the materials for this content topic
		 */
		resources:		ContentResource,

		/**
		 * A more specific topic pertaining to this general topic
		 * TODO: How is this different than sub contents?
		 * TODO: Should this be renamed ```externalSubtopic```?
		 */
		subTopic:		String,

		/**
		 * These are the learning objectives that this content topic tries to teach
		 */
		objectives:		LearningObjective,

		/**
		 * A more specific content topic pertaining to this general topic
		 */
		subContents:	Content,
	]

	static belongsTo = [
		/**
		 * TODO: why does content belong to one learning objective, while having many learning objectives?
		 */
		LearningObjective,

		/**
		 * Todo: why does content belong to content? Is this the content's parent?
		 */
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

	/**
	 * Priorities are read only, they cannot be written
	 */
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
