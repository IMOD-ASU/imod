package imodv6

class CoursePolicy {
	String name
	String description
	CoursePolicyCategory category
	ImodUser user

    static constraints = {
    }
	
	static mapping = {
		version false
	}
	
	def String toString(){
		return name
	}
}
