package imod

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

	String toString() {
		description
	}
}
