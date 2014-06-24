package imodv6

class ActionWord {
	String visibility
	String actionWord
	ImodUser user
	LearningDomain domain
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
