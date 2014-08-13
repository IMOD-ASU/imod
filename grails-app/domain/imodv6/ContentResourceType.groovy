package imodv6

class ContentResourceType {
	String description

	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
}
