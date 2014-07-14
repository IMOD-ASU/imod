package imodv6

/**
 * Information for a special date for the course
 */
class ScheduleDate {
	Date date

	static belongsTo = [
		imod:Imod
	]

    static constraints = {
    }

	static mapping = {
		version false
	}

	def String toString(){
		return date
	}
}
