package imod

/**
 * An Over-arching domain that holds Domain Categories
 * Domain Categories contain Performance Action words
 */
class LearningDomain {
	String name

	static hasMany=[
		domainCategories: DomainCategory
	]

    static constraints = {
    }

	static mapping = {
		version false
		sort 'id'
	}

	String toString() {
		name
	}
}
