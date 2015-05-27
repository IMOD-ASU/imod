package imod

/**
 * A phone number for an instructor
 */
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
