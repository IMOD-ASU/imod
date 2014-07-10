package imodv6

/**
 * An Over-arching domain that holds Domain Categories
 * Domain Categories contain Performance Action words
 */
class LearningDomain {
	String name

	static constraints = {
	}

	static mapping = {
		version false
	}

	String toString(){
		return name
	}
}
