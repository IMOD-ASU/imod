package imod

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class PedagogyTechnique {
	String title
	String description
	String location
	String direction
	String materials
	String reference
	String strategyDescription
	String activityDescription
	PedagogyMode pedagogyMode
	PedagogyActivityDuration pedagogyDuration
	Boolean isAdmin = Boolean.FALSE

	static hasMany = [
		activityFocus: PedagogyActivityFocus,
		assignedLearningObjective: LearningObjective,
		domainCategory: DomainCategory,
		knowledgeDimension: KnowledgeDimension,
		learningDomain: LearningDomain,
		userFavorite: ImodUser,
		users: ImodUser
	]

	static belongsTo = [
		ImodUser,
		LearningObjective,
		PedagogyMode,
		PedagogyActivityDuration,
	]

	String toString() {
		title
	}

	static mapping = {
		description type: 'text'
		reference type: 'text'
		version false
	}
	static constraints = {
		activityDescription nullable: true
		activityFocus nullable: true
		assignedLearningObjective nullable: true
		description nullable: true, blank: true
		location nullable: true
		direction nullable: true
		materials nullable: true
		reference nullable: true
		strategyDescription nullable: true
		userFavorite nullable: true
		users nullable: true
		pedagogyDuration nullable: true
	}
}
