package imodv6

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class PedagogyTechnique {

	String pedagogyTitle
	String pedagogyDescription

	static hasMany = [
		domain: LearningDomain,
		category: DomainCategory,
		knowledge: ContentKnowledgeDomainCode,
		focus: PedagogyActivityFocus
	]

	static belongsTo = [
		pedagogyMode: PedagogyMode
	]

	static mapping = {
		pedagogyDescription type: 'text'
	}

    static constraints = {
		pedagogyDescription nullable: true, blank: true
		focus nullable: true
    }


}
