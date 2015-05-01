package imod

/**
 * This is the key table,
 * This holds all of the pedagogy parts together
 */
class AssessmentTechnique {
	String title
	String description
	String procedure
	String	duration
	AssessmentFeedback assessmentFeedback

	static hasMany = [
		//activityFocus: PedagogyActivityFocus,
		assignedLearningObjective: LearningObjective,
		domainCategory: DomainCategory,
		knowledgeDimension: KnowledgeDimension,
		learningDomain: LearningDomain,
		userFavorite: ImodUser
	]

	static belongsTo = [
		ImodUser,
		LearningObjective,
		AssessmentFeedback
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
		duration nullable: true, blank:true
		userFavorite nullable: true
    }
}
