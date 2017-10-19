package imod

class ScheduleEvent {
	String title
	Date startDate
	Date endDate
	// learnO-> LearningObjective, knowD -> knowledgeDimension; temporarily stored as string for dev prototype
	//this 'learnO' is currently the stored value of 'Link to Online Resource' for each new event. Due to time constraints didn't have time to change this and safely test/demo and deploy.
	String learnO
	String knowD
	//
	String enviro
	Double workTime
	String notes
	String activity
	String lo

	// relationship with learning objective
	static belongsTo = [
		LearningObjective

		//learningObjective: LearningObjective
	]

	// various constraints for validation
    static constraints = {
    	title	blank: false
    	endDate  blank: false, validator: { endDate, schedule ->
			endDate >= schedule.startDate
		}
		learnO	nullable: true
		knowD	nullable: true
		enviro	nullable: true
		workTime	nullable: true
		notes	nullable: true
		activity nullable: true
		lo nullable: true
    }

    static mapping = {
	}

	String toString() {
		title + ' ' + startDate.toString() + ' ' + endDate.toString()  + ' ' + learnO + ' ' + knowD + ' ' + enviro + ' ' + workTime + ' ' + notes + ' ' + activity + ' ' + lo
	}
}
