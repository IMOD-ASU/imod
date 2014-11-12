package imodv6

/**
 * This is the extension of the Spring Security User
 * @param username duplicate of Spring Security User name
 * @param password duplicate password of Spring Security User password
 * @param enabled TODO How does this work?
 * @param accountExpired TODO how does this work?
 * @param accountLocked TODO how does this work?
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
		imods: Imod
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
	}

	static mapping = {
		password column: '`password`'

	}

	Set<Role> getAuthorities() {
		ImodUserRole.findAllByImodUser(this).collect { it.role } as Set
	}

	// TODO this probably should be replaced by Spring Security users default implementation
	def beforeInsert() {
		// Currently these next two lines cause a database constraint error on creating user
		//profile = new UserProfile().save()
		//preferences = new UserPreferences().save()
		encodePassword()
	}

	// TODO this probably should be replaced by Spring Security users default implementation
	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	// TODO this probably should be replaced by Spring Security users default implementation
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	def String toString(){
		return username
	}
}
