package imod

class ScheduleEvent {
	String title
	Date startDate
	Date endDate
	// Add other fields here

	// relationship with learning objective
	static belongsTo = [
		LearningObjective
	]

	// various constraints for validation
    static constraints = {
    	title	blank: false
    	endDate  blank: false, validator: { endDate, schedule ->
			endDate >= schedule.startDate
		}
    }

    static mapping = {
	}

	String toString() {
		title + ' ' + startDate.toString() + ' ' + endDate.toString()
	}
}
