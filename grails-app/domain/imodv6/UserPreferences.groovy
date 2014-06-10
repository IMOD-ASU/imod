package imodv6

class UserPreferences {
	String dateRepresentation
	Boolean showHelp
	ImodUser user
	
    static constraints = {
		dateRepresentation nullable: false
		showHelp nullable: false
		}
	static mapping = {
		showHelp defaultValue: "true"
		dateRepresentation defaultValue: "'mm/dd/yy'"
		version false
	}

}
