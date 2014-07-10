package imodv6

/**
 * This instructor is completely unrelated to IMOD user (TODO what?)
 *
 */
class Instructor {
	String firstName
	String lastName
	String middleInitial
	String location
	String email
	String officeHours
	String webPage
	String phoneNumber
	ImodUser createdBy

	static hasMany = [
		phones:InstructorPhone
	]

	static constraints = {
		middleInitial nullable: true
		firstName nullable: true
		location nullable: true
		email nullable: true
		officeHours nullable: true
		webPage nullable: true
		phoneNumber nullable: true
	}

	static mapping = {
		version false
	}

	def String toString(){
		def displayName = lastName
		if(firstName != null){ displayName = displayName + " " + firstName}
		if(middleInitial != null){ displayName = displayName + " " + middleInitial}
		return displayName
	}
}
