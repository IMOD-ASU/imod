package imodv6

/**
 * TODO is this a duplicate of Spring Security Role?
 */
class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

	def String toString(){
		return authority
	}
}
