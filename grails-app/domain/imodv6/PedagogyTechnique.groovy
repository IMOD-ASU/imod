package imodv6

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class PedagogyTechnique {

	String pedagogyTitle
	String pedagogyDescription

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
		pedagogyDescription type: 'text'
		version false
	}

    static constraints = {
		activityFocus nullable: true
		assignedLearningObjective nullable: true
		pedagogyDescription nullable: true, blank: true
		userFavorite nullable: true
    }
}
