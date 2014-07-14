package imodv6

/**
 * TODO what is this?
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
