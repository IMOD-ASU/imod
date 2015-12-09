package imod

// TODO Instructors should become a subset of users
class Instructor {
	String firstName
	String lastName
	String middleInitial
	String location
	String email
	String officeHours
	String webPage
	String phoneNumber
	String role
	Imod createdBy

	static hasMany = [
		phones: InstructorPhone
	]

	static constraints = {
		middleInitial nullable: true
		firstName nullable: true
		location nullable: true
		email nullable: true
		officeHours nullable: true
		webPage nullable: true
		role nullable:true
		phoneNumber nullable: true
	}

	static mapping = {
		version false
	}

	String toString(){
		def displayName = lastName
		if(firstName != null) {
			displayName = displayName + ' ' + firstName
		}
		if(middleInitial != null){
			displayName = displayName + ' ' + middleInitial
		}
		displayName
	}
}
