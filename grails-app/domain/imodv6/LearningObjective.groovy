package imodv6

class LearningObjective {
	/**
	 * These Strings store the informantion used to make the learning objective definition
	 * preAttributes are the filler String used to give context to the attribute
	 * attributes are the dynamic content from the learning objective
	 * postAtrributes are the linking words between this attribute and the next
	 */
	String preCondition
	String condition
	String postCondition

	String prePerformance
	String performance
	String postPerformance

	String preCriteria
	String criteria
	String postCriteria

	String preIndicator
	String indicator
	String postIndicator

	// TODO: what is this for?
	String completeLearningObjective

	/**
	 * Linkers to other domain objects (?)
	 */
	LearningDomain learningDomain
	DomainCategory domainCategory
	ActionWord actionWord
	LearningObjectiveCriteriaType criteriaType

	/**
	 * Relationships to other domain objects
	 */
	static belongsTo = [imod: Imod]

	static hasMany = [contents: Content, pedogoyTechniques: PedagogyTechnique]

	static transients = ['name']

	/**
	 * Constraints on the fields of Learning Objective
	 */
	static constraints = {
		condition nullable: true
		performance nullable: true
		criteria nullable: true
		indicator nullable: true
		learningDomain nullable: true
		domainCategory nullable: true
		actionWord nullable: true
		criteriaType nullable: true
	}

	//TODO: What does this do?
	static mapping = {
		version false
	}

	//TODO: What does this do?
	def String toString() {
		return completeLearningObjective
	}

	//TODO: What does this do?
	def String getName() {
		return completeLearningObjective
	}
}
