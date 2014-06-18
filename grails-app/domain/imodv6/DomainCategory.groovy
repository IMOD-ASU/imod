package imodv6

class DomainCategory {
	String name
	LearningDomain domain

    static constraints = {
    }
	
	static mapping = {
		version false
	}
	
	def String toString(){
		return name
	}
}
