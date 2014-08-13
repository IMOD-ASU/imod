package imodv6

/**
 * TODO: What is this?
 * It is used by Pedagogy somehow
 */
class ContentPriorityCode {
	String description

	static mapping = {
		version false
	}

	def String toString() {
		return description
	}

	public static List<ContentPriorityCode> values() {
		return ContentPriorityCode.list() ?: []
	}
}
