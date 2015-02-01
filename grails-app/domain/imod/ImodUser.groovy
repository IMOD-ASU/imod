package imod

/**
 * This is the extension of the Spring Security User
 *
 * IMOD User Relationships
 * a User can have many IMODs
 * each user has their own profile and preferances
 */
class ImodUser {

	transient springSecurityService

	String email
	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired


	static hasMany = [
		imods: Imod,
		favoriteTechnique: PedagogyTechnique
	]

	static hasOne = [
		preferences: UserPreferences,
		profile: UserProfile
	]


	static constraints = {
		username 	blank: false,	unique: true
		password 	blank: false
		email	 	unique: true, email: true
		profile 	nullable: true
		preferences nullable: true
		favoriteTechnique nullable: true
	}

	static mapping = {
		password column: '`password`'

	}

	Set<Role> getAuthorities() {
		ImodUserRole.findAllByImodUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		// Currently these next two lines cause a database constraint error on creating user
		//profile = new UserProfile().save()
		//preferences = new UserPreferences().save()
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