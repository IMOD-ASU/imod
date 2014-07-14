package imodv6

/**
 * Store all of the general information on when the course will happen
 * @param repeateDays days that course occurs on
 * @param startDate first date classes occur
 * @param endDate last date classes occur
 * @param sunday start time on sunday
 * @param monday start time on sunday
 * @param tuesday start time on tuesday
 * @param wednesday start time on wednesday
 * @param thursday start time on thursday
 * @param friday start time on friday
 * @param saturday start time on saturday
 */
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

	static belongsTo = [
		imod:Imod
	]

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
