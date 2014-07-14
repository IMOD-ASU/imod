package imodv6

class LearningObjective {
	/**
	 * These Strings store the information used to make the learning objective definition
	 * attributes are the dynamic content from the learning objective
	 * Boolean to keep track of whether or not to hide the Condition from the Learning Objective
	 */
	String condition
	String criteria
	String indicator
	String performance
	Boolean hideFromObjective

	/**
	 * Linkers to other domain objects (?)
	 * TODO: double check this is really what is going on
	 */
	LearningDomain					learningDomain
	DomainCategory					domainCategory
	ActionWord						actionWord
	LearningObjectiveCriteriaType	criteriaType

	/**
	 * Relationships to other domain objects
	 */
	static belongsTo = [
		imod: Imod
	]

	static hasMany = [
		contents: Content,
		pedogoyTechniques: PedagogyTechnique
	]

	public static final List genericConditions=[
						'Given a program specification',
						'Students completing this course will be able to',
						'After completing the course, the student will be able to'
						]
	static transients = ['name']

	/**
	 * Constraints on the fields of Learning Objective
	 */
	static constraints = {
		actionWord		nullable: true
		condition		nullable: true
		criteria		nullable: true
		criteriaType	nullable: true
		domainCategory	nullable: true
		indicator		nullable: true
		learningDomain	nullable: true
		performance		nullable: true
		
	}

	//TODO: What does this do?
	static mapping = {
		version false
		hideFromObjective defaultValue:"'false'"
	}
}
