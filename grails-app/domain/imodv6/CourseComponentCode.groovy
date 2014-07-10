package imodv6

/**
 * TODO what is this?
 */
class CourseComponentCode {
	String description
	ComponentDesignation designation

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
}
