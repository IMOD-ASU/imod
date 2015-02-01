package imod

class LearningObjectiveCriteriaType {
	String description

	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
}
