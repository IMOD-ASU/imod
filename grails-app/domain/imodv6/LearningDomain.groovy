package imodv6

class LearningDomain {
	String name

	static hasMany=[domainCategories:DomainCategory]

    static constraints = {
    }

	static mapping = {
		version false
	}

	String toString(){
		return name
	}
}
