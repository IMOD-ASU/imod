package imod

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
	// FIXME condition is a SQL reserved word
	String condition
	String customCondition
	// FIXME indicator is a SQL reserved word
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
	String actionWord

	// Store a custom definition
	String definition

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
		pedagogyTechniques: PedagogyTechnique
	]

	public static final List genericConditions = [
		'Given a problem specification',
		'Students completing this course will be able to',
		'After completing the course, the student will be able to'
	]

	/**
	 * Constraints on the fields of Learning Objective
	 */
	static constraints = {
		criteriaType		nullable: true
		actionWordCategory	nullable: true
		condition			nullable: true
		customCondition		nullable: true
		definition			nullable: true, maxSize: 65000
		criteriaAccuracy	nullable: true
		criteriaQuality		nullable: true
		criteriaQuantity	nullable: true
		criteriaSpeed		nullable: true
		indicator			nullable: true
		performance			nullable: true
		actionWord			nullable: true
		hideFromLearningObjectiveCondition	nullable:true
	}

	static mapping = {
		version false
		sort 'id'

		hideFromLearningObjectiveCondition defaultValue: 'false'

		criteriaAccuracyHidden	defaultValue: 'false'
		criteriaQualityHidden	defaultValue: 'false'
		criteriaQuantityHidden	defaultValue: 'false'
		criteriaSpeedHidden		defaultValue: 'false'

		criteriaAccuracyEnabled	defaultValue: 'false'
		criteriaQualityEnabled	defaultValue: 'false'
		criteriaQuantityEnabled	defaultValue: 'false'
		criteriaSpeedEnabled	defaultValue: 'false'
		autoTimestamp true
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

	String toString() {
	    return definition
	}

	public buildDefinition() {
		definition = ''
		if (isEmpty()) {
			definition = 'Empty Learning Objective'
			return
		}
		if(!hideFromLearningObjectiveCondition && condition != null) {
			definition += condition
		}
		if(performance != null) {
			definition += ' ' + actionWord
		}

		definition += listToSentance(contents)

		if (criteriaAccuracy != null && criteriaAccuracyHidden == false) {
			definition += ' ' + criteriaAccuracy
		}
		if (criteriaQuality != null && criteriaQualityHidden == false) {
			definition += ' ' + criteriaQuality
		}
		if (criteriaQuantity != null && criteriaQuantityHidden == false) {
			definition += ' ' + criteriaQuantity
		}
		if (criteriaSpeed != null && criteriaSpeedHidden == false) {
			definition += ' ' + criteriaSpeed
		}
		if (indicator != null) {
			definition += ' ' + indicator
		}
	}

	private String listToSentance(list) {
		String returnString = '';
		if(list != null) {
			// there is only one item in the list
			if (list.size() == 1) {
				if (first.toString() != null) {
					returnString += ' ' + list[0]
				}
			}
			// there are two items in the list
			else if (list.size() == 2) {
				if (first != null) {
					returnString += ' ' + list[0]
				}
				returnString += ' and'
				if (second != null) {
					returnString += ' ' + list[1]
				}
			}
			// there are no items or many times in the list
			else {
				for (int index = 0; index < list.size(); index++) {
					if (list[index] != null) {
						// add space in front of first item
						if(index == 0) {
							returnString += ' '
						}
						// comma seperate middle items
						else {
							returnString += ', '
						}
						// add an 'and' for the last item
						if (index == list.size() - 1) {
							returnString += 'and '
						}
						// add the item
						returnString += list[index]
					}
				}
			}
			return returnString
		}
	}
}
