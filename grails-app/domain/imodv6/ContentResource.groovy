package imodv6

/**
 * Stores information of a document or other resource relevant to teaching a topic in a course
 * TODO review this Dr. Bansal is not sure about these fields
 * @param name TODO what is this?
 * @param resourceType TODO what is this?
 * @param file TODO what is this?
 * @param resourceUrl link to the location of an online resource
 * @param fileName name of resource (TODO is this a duplicate of URL/ could this be replaced with path?)
 * @param fileContentType The mime type of the resource
 * @param description a short explanation of how to resource relates to the topic
 *
 * @param content topic that this resource belongs to
 */
class ContentResource {
	String name
	ContentResourceType resourceType
	byte[] file
	String resourceURL
	String fileName
	String fileContentType
	String description

	static belongsTo = [
		content: Content
	]

	static constraints = {
		description nullable: true
		fileName nullable: true
		fileContentType nullable: true
		resourceURL nullable: true
		resourceType nullable: true
		file(
			size: 0..20000000,
			nullable: true
		)
	}

	static mapping = {
		version false
		description type: 'text'
	}

	def String toString() {
		return name
	}
}
