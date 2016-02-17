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
	}

	static mapping = {
		password column: '`password`'
	}

	static mappedBy = [
		favoriteTechnique: "userFavorite",
		pedagogyTechnique: "users"
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
