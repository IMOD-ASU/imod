package imod

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
	 */
	String			courseSemester

	/**
	 * Campus, Room and Building where course will be taught
	 */
	String			courseLocation

	/**
	 *
	 */
	String			gradingProcedure
	String 			attendance
	String			classParticipation
	String 			professionalConduct
	String 			missedExams
	String 			missedAssignments

	String			timeRatio

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
	 * Gives information about Grading Policy, Attendacy policy etc
	 */
	CoursePolicy			coursePolicy

	/**
	 * What days and times the course occurs on
	 */
	Schedule				schedule


	static belongsTo = [
		/**
		 * instructor that owns the course
		 */
		owner: ImodUser
	]

	static hasMany = [
		/**
		 * all of the learning objectives that will be used in this IMOD
		 */
		learningObjectives:	LearningObjective,

		// disable this until it is actually used
		// sharedOwners:		ImodUser,

		/**
		 * All instructors who have access this couse
		 */
		instructors:		Instructor,

		/**
		 * Components of the course
		 */
		courseComponents:	CourseComponentCode,

		/**
		 * People that is course caters to
		 */
		audience:			Audience,

		/**
		 * All of the Topics and Resources to be covered in the course
		 */
		contents:			Content
	]


	/*
	 *****************
	 * Configuration *
	 *****************
	 */

	/**
	 * Which attributes are optional or required
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
		gradingProcedure nullable: true
		attendance 		nullable: true
		classParticipation nullable: true
		professionalConduct nullable: true
		missedExams		nullable: true
		missedAssignments nullable: true
		numberOfSeats	nullable: true,		min: 1,		scale: 1
		creditHours		nullable: true,		min: 1,		scale: 1

		// Domains belonging to an IMOD
		coursePolicy	nullable: true
		schedule		nullable: true
	}

	/**
	 * Courses are not versioned
	 */
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