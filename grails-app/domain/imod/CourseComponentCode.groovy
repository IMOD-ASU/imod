package imod

class CourseComponentCode {
	String description
	ComponentDesignation designation

	static constraints = {
	}

	static mapping = {
		version false
	}

	String toString(){
		description
	}
}
