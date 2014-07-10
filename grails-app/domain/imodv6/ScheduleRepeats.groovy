package imodv6

/**
 * TODO what is this?
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
