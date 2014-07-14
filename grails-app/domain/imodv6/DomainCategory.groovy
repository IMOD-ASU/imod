package imodv6

class DomainCategory {
	String name
	LearningDomain domain
	
	static hasMany=[actionWords:ActionWord]

    static constraints = {
    }

	static mapping = {
		version false
	}

	def String toString(){
		return name
	}
}
