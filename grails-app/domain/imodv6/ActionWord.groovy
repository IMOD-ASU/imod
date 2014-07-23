package imodv6

/**
 * An action word is a Learning Objective Performance indicator
 * @param actionWord actual content of the action word
 * @param category specific learning domain category that will pull up word
 */
class ActionWord {
	String actionWord

	static belongsTo = [
		category: DomainCategory
	]

	// lets grails know that action word text is the printable attribute
	def String toString(){
		return actionWord
	}
}
