package imod

/**
 * Describes presentation format (video, text, slides) of a Resource
 */
class ResourceType {
	String description

	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
}
