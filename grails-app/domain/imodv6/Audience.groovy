package imodv6

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
