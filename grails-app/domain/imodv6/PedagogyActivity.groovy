package imodv6

/**
 * these are activities that are needed complete the pedagogy
 */
class PedagogyActivity {
	String description
	String example
	String title
	String material

	static belongsTo = [
		pedagogyTechnique: PedagogyTechnique,
		pedagogyActivityDuration: PedagogyActivityDuration
	]

	static mapping = {
		description type: 'text'
		example type: 'text'
	}
	
	static constraints = {
		example nullable: true
		material nullable: true
	}
}
