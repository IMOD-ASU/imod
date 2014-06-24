package imodv6

class PedagogyReference {

	String title
	String description
	String referenceLinkISBN
	String author

	static belongsTo =[referenceType: PedagogyReferenceType, pedagogyTechnique:PedagogyTechnique]

	static mapping = {
		description type:'text'
	}
    static constraints = {
		description nullable:true
		author nullable:true
    }
}
