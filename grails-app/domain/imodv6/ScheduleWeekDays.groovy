package imodv6

class ScheduleWeekDays {
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
