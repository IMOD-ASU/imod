package imodv6

class UserPreferences {
	String dateRepresentation
	boolean showHelp
	ImodUser user

    static constraints = {
		dateRepresentation nullable: true
		showHelp nullable: false
		}
	static mapping = {
		showHelp defaultValue: "'true'"
		dateRepresentation defaultValue: "'mm/dd/yy'"
		version false
	}

}
