package imod

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
