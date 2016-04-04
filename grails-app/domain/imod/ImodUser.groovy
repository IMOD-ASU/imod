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
	String firstName
	String lastName
	String location
	String officeHours
	String webPage
	String phoneNumber
	String role
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [
		imods: Imod,
		favoriteTechnique: PedagogyTechnique,
		favoriteAssessmentTechnique: AssessmentTechnique,
		assessmentTechnique: AssessmentTechnique,
		pedagogyTechnique: PedagogyTechnique

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
		favoriteAssessmentTechnique nullable: true
		assessmentTechnique nullable: true
		pedagogyTechnique nullable: true
		location nullable: true
		officeHours nullable: true
		webPage nullable: true
		phoneNumber nullable: true
		firstName nullable: false, blank: false, minSize: 3, maxSize: 20
		lastName nullable: false, blank: false, minSize: 3, maxSize: 20
		role nullable: false, blank: false, minSize: 3
	}

	static mapping = {
		password column: '`password`'
	}

	static mappedBy = [
		favoriteTechnique: 'userFavorite',
		pedagogyTechnique: 'users'
	]

	Set<Role> getAuthorities() {
		ImodUserRole.findAllByImodUser(this)*.role as Set
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

	String toString() {
		username
	}
}
