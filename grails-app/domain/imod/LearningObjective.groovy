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
		pedagogyTechniques: PedagogyTechnique,
		assessmentTechniques: AssessmentTechnique,
		scheduleEvents: ScheduleEvent
	]

	public static final List genericConditions = [
		'Given a problem specification, the student will be able to',
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
		hideFromLearningObjectiveCondition	nullable: true
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

	def isEmpty() {
		// check each of the criteria attributes
		boolean criteriaAccuracyEmpty = criteriaAccuracyEnabled == false || criteriaAccuracy == null || criteriaAccuracyHidden == true
		boolean criteriaQualityEmpty = criteriaQualityEnabled == false || criteriaQuality == null || criteriaQualityHidden == true
		boolean criteriaQuantityEmpty = criteriaQuantityEnabled == false || criteriaQuantity == null || criteriaQuantityHidden == true
		boolean criteriaSpeedEmpty = criteriaSpeedEnabled == false || criteriaSpeed == null || criteriaSpeedHidden == true
		// check the complete criteria page
		boolean criteriaPageEmpty =  criteriaAccuracyEmpty && criteriaQualityEmpty && criteriaQuantityEmpty && criteriaSpeedEmpty
		// check all the other attributes
		boolean otherPagesEmpty = condition == null && indicator == null && performance == null
		// return result
		otherPagesEmpty && criteriaPageEmpty
	}

	String toString() {
		definition
	}

	def buildDefinition() {
		definition = ''
		if (actionWord == 'Enter the details here') {
			actionWord = ''
		}
		if (isEmpty()) {
			definition = 'Empty Learning Objective'
			return
		}
		if (!hideFromLearningObjectiveCondition && condition != null) {
			definition += condition
		}
		if (performance != null) {
			definition += ' ' + actionWord
		}

		definition += listToSentence(contents.sort {
			it.topicTitle
		})

		List<String> criteria = [] as String[]
		if (criteriaAccuracy != null && criteriaAccuracyHidden == false) {
			criteria.push(criteriaAccuracy + ' accuracy')
		}
		if (criteriaQuality != null && criteriaQualityHidden == false) {
			criteria.push(criteriaQuality + ' quality')
		}
		if (criteriaQuantity != null && criteriaQuantityHidden == false) {
			criteria.push(criteriaQuantity + ' quantity')
		}
		if (criteriaSpeed != null && criteriaSpeedHidden == false) {
			criteria.push(criteriaSpeed + ' speed')
		}

		if (indicator != null) {
			definition += indicator
		}

		definition += listToSentence(criteria, ' with ')
	}

	private String listToSentence(list, openingSpace = ' ') {
		String returnString = ''
		if (list != null) {
			// there is only one item in the list
			if (list.size() == 1) {
				if (list[0].toString() != null) {
					returnString += openingSpace + list[0]
				}
			}
			// there are two items in the list
			else if (list.size() == 2) {
				if (list[0] != null) {
					returnString += openingSpace + list[0]
				}
				returnString += ' and'
				if (list[1] != null) {
					returnString += ' ' + list[1]
				}
			}
			// there are no items or many times in the list
			else {
				for (int index = 0; index < list.size(); index++) {
					if (list[index] != null) {
						// add space in front of first item
						if (index == 0) {
							returnString += openingSpace
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
