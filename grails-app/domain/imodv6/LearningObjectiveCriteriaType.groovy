package imodv6

class LearningObjectiveCriteriaType {
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
