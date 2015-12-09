package imod

/**
 * Describes the type of knowledge that a Pedagogy contains
 */
class KnowledgeDimension {
	/**
	 * Describes the information that this dimension encapsulates
	 */
	String description
	String info

	/**
	 * Knowledge dimensions are not versioned
	 */
	static mapping = {
		version false
		sort 'id'
	}

	/**
	 * printing a Knowledge Dimension will give the instance's description
	 * @return the description of the knowledge dimension
	 */
	String toString() {
		description
	}

	/**
	 * Lists all of the possible knowledge dimensions
	 * @return List of dimensions
	 */
	static List<KnowledgeDimension> values() {
		KnowledgeDimension.list() ?: []
	}
}
