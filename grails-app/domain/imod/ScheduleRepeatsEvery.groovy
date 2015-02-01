package imod

/**
 * Describes if a schedule repeats daily, weeky, monthly etc
 */
class ScheduleRepeatsEvery {
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
