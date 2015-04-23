package imod

/**
 * Stores information of a document or other resource relevant to teaching a topic in a course
 */
class Resource {
	/*
	 ***********************
	 * Required Attributes *
	 ***********************
	 */

	/**
	 * A display name for the resource
	 */
	String name


	/*
	 ***********************
	 * Optional Attributes *
	 ***********************
	 */

	/**
	 * A short description of what this resource covers, or how it relates to the content
	 */
	String description

	/**
	 * What type of content the resource contains
	 * e.g. video, pdf, interactive content
	 */
	String resourceType

	/**
	 *
	 */
	//String resourceURL

	/*
	 *****************
	 * Relationships *
	 *****************
	 */

	/**
	 * Each resource belong to one content topic
	 */
	static belongsTo = [
		content: Content
	]


	/*
	 *****************
	 * Configuration *
	 *****************
	 */

	/**
	 * Describes what attributes are optional
	 */
	static constraints = {
		name nullable: true
		description nullable: true
		//resourceURL nullable: true
		resourceType nullable: true
	}

	/**
	 * This is not versioned
	 * description is a LONGVARCHAR or a large variable sized text field
	 */
	static mapping = {
		version false
		description type: 'text'
	}

	/*
	 *************
	 * Functions *
	 *************
	 */

	static List resourceTypes() {
		def resourceList = [
			'Book',
			'Chapter',
			'Document',
			'URL'
		]
		return resourceList
	}

	/**
	 * printing the Resource will give the resource's display name
	 * @return display name for resource
	 */
	def String toString() {
		return name
	}
}
