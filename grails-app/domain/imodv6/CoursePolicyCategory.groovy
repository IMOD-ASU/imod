package imodv6

class CoursePolicyCategory {
	String name
	
    static constraints = {
    }
	
	static mapping = {
		version false
	}
	
	def String toString(){
		return name
	}
}
