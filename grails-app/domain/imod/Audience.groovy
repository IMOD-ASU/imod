package imod

/**
 * This describes who the IMOD is catering to
 */
class Audience {
	/**
	 * A description of the intended audiance
	 */
	String description

	/**
	 * This is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * printing the Audience object will give the instance's description
	 * @return the description of the audience
	 */
	def String toString(){
		return description
	}
}
