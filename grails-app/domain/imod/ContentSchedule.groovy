package imod
import java.text.DateFormat
import java.text.SimpleDateFormat

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
		DateFormat df = new SimpleDateFormat('MM/dd/yyyy')
		return "${content.topicTitle + ':' + df.format(startDate)}"
	}
}
