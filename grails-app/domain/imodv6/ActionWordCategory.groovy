package imodv6

/**
 * An action word is a Learning Objective Performance indicator
 * @param actionWordCategory actual content of the action word
 * @param category specific learning domain category that will pull up word
 */
class ActionWordCategory {
	String actionWordCategory

	static belongsTo = [
		category: DomainCategory
	]
}
