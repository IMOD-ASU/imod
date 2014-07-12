package imodv6

/**
 * TODO What is this?
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
