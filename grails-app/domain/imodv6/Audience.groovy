package imodv6

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

	def String toString(){
		return description
	}
}
