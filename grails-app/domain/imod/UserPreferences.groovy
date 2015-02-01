package imod

/**
 * These store users preferences on how the IMODS ui should be customized
 */
class UserPreferences {
	String dateRepresentation
	boolean showHelp
	ImodUser user

	static belongsTo = ImodUser

    static constraints = {
		dateRepresentation nullable: true
		showHelp nullable: true
	}

	static mapping = {
		showHelp defaultValue: 'true'
		dateRepresentation defaultValue: "'mm/dd/yy'"
		version false
	}

}
