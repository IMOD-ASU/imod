package imodv6

class ImodUser {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	UserProfile profile
	
	static hasMany = [imods:Imod]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		profile nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		ImodUserRole.findAllByImodUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	def String toString(){
		return username
	}
}
