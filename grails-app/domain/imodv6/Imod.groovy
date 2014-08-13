package imodv6

/**
 * This is the core class in the IMODS system
 * an IMOD is a course that will be using the PC^3 model to create its curriculum
 * an Imod belongs to an instructor and contains:
 * - Course Overview - When and Where of the course
 * - Learning Objectives - What this course will teach
 * - Content - resources that will be used to cover these topics
 * - Assessment - How student progress will be measured through the course
 * - Pedagogy - How a Learning Objective will be taught
 */
class Imod {
	/*
	 ***********************
	 * Required Attributes *
	 ***********************
	 */

	/**
	 * Course title
	 */
	String			name

	/**
	 * link to the course website
	 */
	String			url

	/**
	 * General subject area that course will be covering
	 */
	String			subjectArea


	/*
	 ***********************
	 * Optional Attributes *
	 ***********************
	 */

	/**
	 * a short description of the course
	 */
	String			overview

	/**
	 * semester that the course will be taught in
	 * TODO: should this be an object?
	 */
	String			courseSemester

	/**
	 * Campus, Room and Building where course will be taught
	 */
	String			courseLocation

	/**
	 * TODO what is this?
	 */
	String			timeRatio

	/**
	 * TODO What is this?
	 */
	String			imodNumber

	/**
	 * number of students enrolled in course
	 */
	Integer			numberOfSeats

	/**
	 * number of credit hours that course is awarded
	 */
	Integer			creditHours


	/*
	 *****************
	 * Relationships *
	 *****************
	 */
	/**
	 * @param owner instructor that owns the course
	 * @param coursePolicy TODO What is this?
	 * @param scedule TODO How are these supposed to work?
	 * @param scheduleDate TODO How are these supposed to work?
	 * @param repeats TODO How are these supposed to work?
	 * @param learningObjectives a collection of all of the learning objectives that will be used in the IMOD
	 * @param repeatsEvery TODO How are these supposed to work?
	 */
	CoursePolicy			coursePolicy
	Schedule				schedule
	ScheduleDate			scheduleDate
	ScheduleRepeats			repeats
	ScheduleRepeatsEvery	repeatsEvery

	static belongsTo = [
		owner: ImodUser
	]

	static hasMany = [
		learningObjectives:	LearningObjective,
		// disable this until it is actually used
		// sharedOwners:		ImodUser,
		instructors:		Instructor,
		courseComponents:	CourseComponentCode,
		audience:			Audience,
		contents:			Content
	]


	/*
	 *****************
	 * Configuration *
	 *****************
	 */
	static constraints = {
		// These are required to create an IMOD
		name			nullable: false,	blank: false
		url				nullable: false,	blank: false
		subjectArea		nullable: false,	blank: false

		// These are optional string atrributes of an IMOD
		overview		nullable: true
		courseSemester	nullable: true
		courseLocation	nullable: true
		timeRatio		nullable: true
		imodNumber		nullable: true
		numberOfSeats	nullable: true
		creditHours		nullable: true

		// Domains belonging to an IMOD
		coursePolicy	nullable: true
		schedule		nullable: true
		scheduleDate	nullable: true
		repeats			nullable: true
		repeatsEvery	nullable: true
	}

	static mapping = {
		version false
	}


	/*
	 *************
	 * Functions *
	 *************
	 */

	/**
	 * printing the Help will give the tool tip text
	 * @return tool tip text
	 */
	def String toString() {
		return name
	}
}
