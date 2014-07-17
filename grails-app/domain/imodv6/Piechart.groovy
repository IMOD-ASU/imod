package imodv6

/**
 * TODO: this can be deleted
 */
class Piechart {
	Integer count
	ContentPriorityCode priority

	static belongsTo = [
		imod: Imod
	]

    static constraints = {
    }

	static mapping = {
		version false
	}
}
