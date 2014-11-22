package imodv6

/**
 * Describes how often the schedule cycles
 */
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
