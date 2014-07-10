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

	// TODO this probably should be replaced by Spring Security users default implimentation
	def beforeInsert() {
		profile =new UserProfile().save()
		preferences = new UserPreferences().save()
		encodePassword()
	}

	// TODO this probably should be replaced by Spring Security users default implimentation
	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	// TODO this probably should be replaced by Spring Security users default implimentation
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	def String toString(){
		return username
	}
}
