package imod

/**
 * How this pedagogy is delivered (online on campus)
 */
class PedagogyMode {
	String name

	static hasMany = [
		pedagogyTechnique: PedagogyTechnique
	]

	String toString() {
	    name
	}

	static mapping = {
		version false
	}
}
