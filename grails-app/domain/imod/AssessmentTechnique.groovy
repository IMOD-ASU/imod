package imod

/**
 * This is the key table,
 * This holds all of the Assessment parts together
 */
class AssessmentTechnique {
	String title
	String description
	String assesmentype
	String procedure
	String duration
	String difficulty
	String type
	String whenToCarryOut
	String reference
	Boolean assigncheck = Boolean.TRUE
	Boolean favcheck = Boolean.TRUE
	Boolean isAdmin = Boolean.FALSE
	AssessmentFeedback assessmentFeedback

	static hasMany = [
		//activityFocus: PedagogyActivityFocus,
		assignedLearningObjective: LearningObjective,
		domainCategory: DomainCategory,
		knowledgeDimension: KnowledgeDimension,
		learningDomain: LearningDomain,
		userFavorite: ImodUser,
		users: ImodUser
	]

	static belongsTo = [
		ImodUser,
		LearningObjective
	]

	static mapping = {
		description type: 'text'
		version false
	}

	static constraints = {
		//activityFocus nullable: true
		assignedLearningObjective nullable: true
		description nullable: true, blank: true
		procedure nullable: true, blank: true
		duration nullable: true, blank: true
		userFavorite nullable: true
		assessmentFeedback nullable: true
		difficulty nullable: true, blank: true
		type nullable: true, blank: true
		whenToCarryOut nullable: true, blank: true
		reference nullable: true, blank: true
		assesmentype nullable: true, blank: true
	}

	String toString() {
		title
	}
}
