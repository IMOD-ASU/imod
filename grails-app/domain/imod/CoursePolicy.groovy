package imod

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
	 */
	CoursePolicyCategory category

	// FIXME course policy should relate to Imod not User
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
