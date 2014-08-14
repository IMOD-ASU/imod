package imodv6

/**
 * Gives information about Grading Policy, Attendacy policy etc
 */
class CoursePolicy {
	/**
	 * Display name for the policy
	 */
	String name

	/**
	 * A text description of the policy
	 */
	String description

	/**
	 * What type of policy this is
	 * TODO: Should this belong to a Category instead?
	 */
	CoursePolicyCategory category

	/**
	 * TODO: why is a course policy related to a user?
	 * TODO: why is this not a relationship?
	 */
	ImodUser user

	/**
	 * Course Policy is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * printing the Course Policy will give Display name for the policy
	 * @return display name
	 */
	def String toString(){
		return name
	}
}
