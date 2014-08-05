package imodv6

/**
 * TODO this is a content knowledge dimension
 * Describes the type of knowledge that this contains
 * @param description TODO what is this?
 */
class ContentKnowledgeDomainCode {
	String description

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString() {
		return description
	}

	public static List<ContentKnowledgeDomainCode> values() {
		return ContentKnowledgeDomainCode.list() ?: []
	}
}
