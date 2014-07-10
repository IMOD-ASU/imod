package imodv6

/**
 * Gives a
 */
class ContentPriorityCode {
	String description

	static constraints = {
	}

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
