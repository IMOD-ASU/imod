package imodv6

/**
 * Grading Policy, Attendacy policy etc
 */
class CoursePolicy {
	String name
	String description
	CoursePolicyCategory category
	ImodUser user

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString(){
		return name
	}
}
