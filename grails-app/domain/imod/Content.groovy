package imod

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
	 * FIXME: This is correct but needs to be set by jstree
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
	 * This flags that is requires knowledge from outside the course
	 */
	// FIXME rename to hasExternalPreRequisite
	Boolean preReq

	/*
	 *****************
	 * Relationships *
	 *****************
	 */

	Imod imod

	static hasMany = [
		/**
		 * These are the various Knowledge Dimensio categories that a Content topic can belong to (many)
		 */
		dimensions:		KnowledgeDimensionEnum,

		/**
		 * the materials for this content topic
		 */
		resources:		Resource,

		/**
		 * A more specific topic pertaining to this general topic
		 */
		// FIXME rename to externalSubTopic
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
		LearningObjective,
		Content,
		Imod
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
		subContents	nullable:	true
	}

	/**
	 * This is not versioned
	 */
	static mapping = {
		sort 'id'
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

	String toString() {
	    return topicTitle
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
