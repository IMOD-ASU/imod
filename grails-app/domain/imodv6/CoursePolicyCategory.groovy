package imodv6

/**
 * Describes what Category (Grading Policy, Attendacy policy etc) a Course Policy falls under
 */
class CoursePolicyCategory {
	/**
	 * Name of the category
	 */
	String name

	/**
	 * This is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * printing the Course Policy Category will give the display name for the category
	 * @return dsiplay name
	 */
	def String toString(){
		return name
	}
}
