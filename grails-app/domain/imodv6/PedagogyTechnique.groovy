package imodv6

/**
 * TODO what is this?
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
