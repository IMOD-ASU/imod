package imod

/**
 * Related content for this pedagogy
 */
class PedagogyReference {

	String title
	String description
	String referenceLinkISBN
	String author

	static belongsTo =[
		referenceType: PedagogyReferenceType,
		pedagogyTechnique: PedagogyTechnique
	]

	String toString() {
	    title
	}

	static mapping = {
		version false
		description type: 'text'
	}
    static constraints = {
		description nullable: true
		author nullable: true
    }
}
