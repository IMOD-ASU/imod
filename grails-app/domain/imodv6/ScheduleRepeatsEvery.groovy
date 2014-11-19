package imodv6

/**
 * TODO what is this?
 */
class ScheduleRepeatsEvery {
	String description

    static constraints = {
    }

	static mapping = {
		version false
	}
	
	static belongsTo = Schedule

	def String toString(){
		return description
	}
}
