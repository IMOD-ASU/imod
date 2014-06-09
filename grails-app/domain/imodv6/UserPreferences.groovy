package imodv6

class UserPreferences {
	String dateRepresentation
	ImodUser user
	
    static constraints = {
		dateRepresentation nullable: false
		}
	static mapping = {
		dateRepresentation defaultValue: "'mm/dd/yy'"
		version false
	}

}
