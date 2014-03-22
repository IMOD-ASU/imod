package imodv6

class ScheduleRepeats {
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
