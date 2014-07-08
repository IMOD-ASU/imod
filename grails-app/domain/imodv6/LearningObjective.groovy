package imodv6

class LearningObjective {
	/**
	 * These Strings store the informantion used to make the learning objective definition
	 * attributes are the dynamic content from the learning objective
	 */
	String condition
	String criteria
	String indicator
	String performance

	/**
	 * Linkers to other domain objects (?)
	 * TODO: double check this is really what is going on
	 */
	Imod							owner
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
	}
}
