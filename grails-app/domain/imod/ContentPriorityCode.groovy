package imod

class ContentPriorityCode {
	String description

	static mapping = {
		version false
	}

	String toString() {
		description
	}

	static List<ContentPriorityCode> values() {
		ContentPriorityCode.list() ?: []
	}
}
