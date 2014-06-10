package imodv6

class Schedule {
	String repeatsDays
	Date startDate
	Date endDate
	Integer endOccurences
	Integer sunday
	Integer monday
	Integer tuesday
	Integer wednesday
	Integer thursday
	Integer friday
	Integer saturday
	
	static belongsTo = [imod:Imod]
	
    static constraints = {
		endOccurences nullable: true
		sunday nullable: true
		monday nullable: true
		tuesday nullable: true
		wednesday nullable: true
		thursday nullable: true
		friday nullable: true
		saturday nullable: true
		repeatsDays nullable: true
    }
	
	static mapping = {
		version false
	}
}
