package imodv6

class ImodUser {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired


	static hasMany = [
		imods:Imod
	]
	static hasOne = [
		preferences:UserPreferences,
		profile:UserProfile
	]


	static constraints = {
		username blank: false, unique: true
		password blank: false
		profile nullable: true
		profile unique: true
		preferences nullable: true
		preferences unique: true
	}

	static mapping = {
		password column: '`password`'

	}

	Set<Role> getAuthorities() {
		ImodUserRole.findAllByImodUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		profile =new UserProfile().save()
		preferences = new UserPreferences().save()
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
