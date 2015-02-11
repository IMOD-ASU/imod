package imod

/**
 * Describes when a topic should be covered during the curriculum
 */
class ContentSchedule {
	/**
	 * This is the content that is being covered
	 */
	Content content

	/**
	 * Date to begin covering this topic
	 */
	Date startDate

	/**
	 * Imod that Content and Schedule belong to
	 */
	// FIXME remove unused attribute
	Imod imod

	/**
	 * This is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * printing the Content Schedule will give the topic name and start date for covering the topic
	 * @return topic name and start date for covering the topic
	 */
	def String toString() {
		return "${content.topicTitle}:${startDate}"
	}
}
