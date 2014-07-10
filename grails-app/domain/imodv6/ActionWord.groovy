package imodv6

/**
 * An action word is a Learning Objective Performance indicator
 * @param visibility determines if action word will be shown
 * @param actionWord actual conent of the action word
 * @param user that generated the action word (TODO ?)
 * @param domain General domain that word belongs to ( TODO is the necessary?)
 * @param category specific learning domain category that will pull up word
 */
class ActionWord {
	String visibility
	String actionWord
	ImodUser user
	LearningDomain domain
	DomainCategory category

	//TODO is this necessary?
	static constraints = {
	}

	//TODO what is this?
	static mapping = {
		version false
	}
}
