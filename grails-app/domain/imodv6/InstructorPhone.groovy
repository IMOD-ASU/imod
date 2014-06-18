package imodv6

class InstructorPhone {
	String number
	String type
	
	static belongsTo = [instructor:Instructor]
	
    static constraints = {
    }
	
	static mapping = {
		version false
	}
	
	def String toString(){
		return number
	}
}
