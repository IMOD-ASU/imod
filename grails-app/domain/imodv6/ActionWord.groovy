package imodv6

class ActionWord {
	String visibility
	String actionWord
	DomainCategory category

    static constraints = {
    }

	static mapping = {
		version false
	}

	def String toString(){
		return actionWord
	}
}
