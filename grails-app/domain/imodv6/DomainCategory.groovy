package imodv6

/**
 * This is a category determined by the Learning Domain Area,
 * That in turn allows Performance Action Words to be given
 * @param name is the name of this category
 * @param domain Learning Domain that this category belongs to
 */
class DomainCategory {
	String name

	static belongsTo = [
		learningDomain: LearningDomain
	]

	static hasMany = [
		actionWords: ActionWord
	]

	static constraints = {
	}

	static mapping = {
		version false
	}

	def String toString(){
		return name
	}
}
