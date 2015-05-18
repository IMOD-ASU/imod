package imod

/**
 * This designates the type of a Course Component Code
 */
class ComponentDesignation {
	/**
	 * A description of the designation
	 */
	String description

	/**
	 * This is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * printing the Component Designation will give the instance's description
	 * @return the description of the Component Designation
	 */
	def String toString(){
		return description
	}
}
