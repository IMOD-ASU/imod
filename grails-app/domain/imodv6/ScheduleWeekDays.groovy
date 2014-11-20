package imodv6

class ScheduleWeekDays {
	String description

	static belongsTo = Schedule
	

	
    static constraints = {
    }
	
	static mapping = {
		version false
	}

	def String toString(){
		return description
	}
	
}
