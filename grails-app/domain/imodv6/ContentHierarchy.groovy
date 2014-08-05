package imodv6

/**
 * TODO: should have relationships
 * @param parentContent TODO what is this?
 * @param leafContent TODO what is this?
 */
class ContentHierarchy {
	//TODO make this belongs to parent
	Content parentContent

	//TODO make this has many leaves
	Content leafContent

	static constraints = {
	}

	static mapping = {
		version false
	}
}
