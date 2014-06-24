package imodv6

class Piechart {
	Integer count
	ContentPriorityCode priority

	static belongsTo = [imod:Imod]

    static constraints = {
    }

	static mapping = {
		version false
	}
}
