package imodv6

/**
 * Describes when a topic should be covered during the curriculum (TODO ?)
 * @param content the topic being covered
 * @param startDate when content will be covered
 * @param imod TODO why is this here?
 */
class ContentSchedule {
	Content content
	Date startDate
	Imod imod

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString() {
		return "${content.topicTitle}:${startDate}"
	}
}
