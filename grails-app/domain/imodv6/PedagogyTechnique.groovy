package imodv6

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class PedagogyTechnique {

	String pedagogyTitle
	String pedagogyDescription

	static hasMany = [
		learningDomain: LearningDomain,
		domainCategory: DomainCategory,
		knowledgeDimension: KnowledgeDimension,
		activityFocus: PedagogyActivityFocus,
		userFavorite: ImodUser
	]

	static belongsTo = [
		ImodUser,
		PedagogyMode
	]

	static mapping = {
		pedagogyDescription type: 'text'
		version false
	}

    static constraints = {
		pedagogyDescription nullable: true, blank: true
		activityFocus nullable: true
		userFavorite nullable: true
    }
}
