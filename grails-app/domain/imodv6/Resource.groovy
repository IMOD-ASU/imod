package imodv6

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
	ResourceType resourceType

	/**
	 *
	 */
	String resourceURL

	//This was earlier developers attempt at storing a file
	//byte[] file
	//String fileName
	//String fileContentType


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
		description nullable: true
		resourceURL nullable: true
		resourceType nullable: true

		//fileName nullable: true
		//fileContentType nullable: true
		//file(
		//	size: 0..20000000,
		//	nullable: true
		//)
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

	/**
	 * printing the Resource will give the resource's display name
	 * @return display name for resource
	 */
	def String toString() {
		return name
	}
}
