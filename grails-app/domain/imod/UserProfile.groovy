package imod

/**
 * General Instructor information
 * TODO this could be shared between instructor and user
 */
class UserProfile {
	String firstName
	String lastName
	String middleInitial
	String email

	String organization
	String themeCode
	String streetAddress
	String city
	// FIXME state is a reserved keywork in SQL
	// String state
	String country
	String zip


	ImodUser user


    static constraints = {
		// remove these constraints selectively in the future if profile is not nullable in ImodUser class
		firstName nullable: true
		lastName nullable: true
		middleInitial nullable: true
		email nullable: true
		organization nullable: true
		themeCode nullable: true
		streetAddress nullable: true
		city nullable: true
		// state nullable: true
		country nullable: true
		zip nullable: true
   }

	static mapping = {
		version false
	}
}
