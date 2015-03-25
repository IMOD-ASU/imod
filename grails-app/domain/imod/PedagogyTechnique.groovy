package imod

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class PedagogyTechnique {
	String title
	String description
	String direction
	String materials
	String strategyDescription
	String activityDescription
	PedagogyMode pedagogyMode

	static hasMany = [
		activityFocus: PedagogyActivityFocus,
		assignedLearningObjective: LearningObjective,
		domainCategory: DomainCategory,
		knowledgeDimension: KnowledgeDimension,
		learningDomain: LearningDomain,
		userFavorite: ImodUser
	]

	static belongsTo = [
		ImodUser,
		LearningObjective,
		PedagogyMode
	]

	static mapping = {
		description type: 'text'
		version false
	}

    static constraints = {
		activityDescription nullable: true
		activityFocus nullable: true
		assignedLearningObjective nullable: true
		description nullable: true, blank: true
		direction nullable: true
		materials nullable: true
		strategyDescription nullable: true
		userFavorite nullable: true
    }
}
