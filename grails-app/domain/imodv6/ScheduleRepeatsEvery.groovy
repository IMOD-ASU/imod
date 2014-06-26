package imodv6

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
