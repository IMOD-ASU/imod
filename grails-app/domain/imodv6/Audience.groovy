package imodv6

/**
 * TODO what is the purpose of this?
 */
class Audience {
	String description

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
}
