package imod

/**
 * feedback mechanism
 */
class AssessmentFeedback {
	String name

	static hasMany = [
		assessmentTechnique: AssessmentTechnique
	]

	static mapping = {
		version false
	}

	String toString(){
		return name
	}
}
