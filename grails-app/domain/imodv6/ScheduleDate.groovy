package imodv6

class ScheduleDate {
	Date date

	static belongsTo = [imod:Imod]

    static constraints = {
    }

	static mapping = {
		version false
	}

	def String toString(){
		return date
	}
}
