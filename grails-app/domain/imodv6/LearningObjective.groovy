package imodv6

/**
 * a Learning Objective is a topic which the instructor wishes the students to master
 * Each Learning Objective has a
 * @param condition condition where a learning objective has been mastered
 * @param criteria the measure of how the objective has been the completed
 * @param indicator the point part of the objective that is being measured by the Criteria, Condition and Performance
 * @param performance allows measure of progress and velocity of student moving toward mastering topic
 */
class LearningObjective {
	/**
	 * These Strings store the information used to make the learning objective definition
	 * attributes are the dynamic content from the learning objective
	 * Boolean to keep track of whether or not to hide the Condition from the Learning Objective
	 */
	String condition
	String indicator
	String performance
	// Hide condition from objective
	Boolean hideFromLearningObjectiveCondition

	/**
	 * the four criteriae of success are stored here
	 */
	// text value of each criteria type
	String criteriaAccuracy
	String criteriaQuality
	String criteriaQuantity
	String criteriaSpeed

	// if the criteria is enabled or not
	Boolean criteriaAccuracyEnabled
	Boolean criteriaQualityEnabled
	Boolean criteriaQuantityEnabled
	Boolean criteriaSpeedEnabled

	// if the criteria should be hidden from the definition of the learning objective
	Boolean criteriaAccuracyHidden
	Boolean criteriaQualityHidden
	Boolean criteriaQuantityHidden
	Boolean criteriaSpeedHidden

	// Learning Objective has many criteria, each  criteria has an enumerated type and hidden or not
	LearningObjectiveCriteriaType criteriaType

	/**
	 * Relationships to other domain objects
	 */
	static belongsTo = [
		imod: Imod,
		actionWordCategory: ActionWordCategory
	]

	static hasMany = [
		contents: Content,
		pedogoyTechniques: PedagogyTechnique
	]

	public static final List genericConditions = [
		'Given a program specification',
		'Students completing this course will be able to',
		'After completing the course, the student will be able to'
	]

	static transients = [
		'name'
	]

	/**
	 * Constraints on the fields of Learning Objective
	 */
	static constraints = {
		criteriaType		nullable: true
		actionWordCategory	nullable: true
		condition			nullable: true
		criteriaAccuracy	nullable: true
		criteriaQuality		nullable: true
		criteriaQuantity	nullable: true
		criteriaSpeed		nullable: true
		indicator			nullable: true
		performance			nullable: true
		hideFromLearningObjectiveCondition	nullable:true

	}

	//TODO: What does this do?
	static mapping = {
		version false
		hideFromLearningObjectiveCondition defaultValue: 'false'

		criteriaAccuracyHidden	defaultValue: 'true'
		criteriaQualityHidden	defaultValue: 'true'
		criteriaQuantityHidden	defaultValue: 'true'
		criteriaSpeedHidden		defaultValue: 'true'

		criteriaAccuracyEnabled	defaultValue: 'true'
		criteriaQualityEnabled	defaultValue: 'true'
		criteriaQuantityEnabled	defaultValue: 'true'
		criteriaSpeedEnabled	defaultValue: 'true'
	}


	public isEmpty() {
		// check each of the criteria attributes
		boolean criteriaAccuracyEmpty = criteriaAccuracyEnabled == false || criteriaAccuracy == null || criteriaAccuracyHidden == true
		boolean criteriaQualityEmpty = criteriaQualityEnabled == false || criteriaQuality == null || criteriaQualityHidden == true
		boolean criteriaQuantityEmpty = criteriaQuantityEnabled == false || criteriaQuantity == null || criteriaQuantityHidden == true
		boolean criteriaSpeedEmpty = criteriaSpeedEnabled == false || criteriaSpeed == null || criteriaSpeedHidden == true
		// check the complete criteria page
		boolean criteriaPageEmpty =  criteriaAccuracyEmpty && criteriaQualityEmpty && criteriaQuantityEmpty && criteriaSpeedEmpty
		// check all the other attributes
		boolean otherPagesEmpty = condition == null && indicator == null && performance == null
		// put it all together
		return otherPagesEmpty && criteriaPageEmpty
	}
}
