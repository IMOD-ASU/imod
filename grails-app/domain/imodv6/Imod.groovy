package imodv6

/**
 * This is the core class in the IMODS system
 * an IMOD is a course that will be using the PC^3 model to create its curriculum
 * an Imod belongs to an instructor and contains:
 * - Course Overview - When and Where of the course
 * - Learning Objectives - What this course will teach
 * - Content - resources that will be used to cover these topics
 * - Assessment - How student progress will be measured through the course
 * - Pedagogy - TODO what is this?
 *
 * IMOD attributes
 * @param owner instructor that owns the course
 * @param name Course title
 * @param url link to the course website
 * @param overview a short description of the course
 * @param courseSemenster semester that the course will be taught in (TODO should this be an object)
 * @param courseLocation Campus, Room and Building where course will be taught
 * @param timeRatio TODO what is this?
 * @param numberOfSeats number of students enrolled in course (Should this and course enrollment be seperate?)
 * @param subjectArea General subject area that course will be covering
 * @param creditHours number of credit hours that course is awarded
 * @param imodNumber TODO What is this?
 * @param coursePolicy TODO What is this?
 * @param scedule TODO How are these supposed to work?
 * @param scheduleDate TODO How are these supposed to work?
 * @param repeats TODO How are these supposed to work?
 * @param piechart TODO What is this?
 * @param learningObjectives a collection of all of the learning objectives that will be used in the IMOD
 * @param repeatsEvery TODO How are these supposed to work?
 *
 * IMOD relationships
 * an IMOD belongs to an instructor
 * each IMOD can have several
 * - Learning Objectives
 * - Partnered Instructors
 * - Instructors
 * - Course Components
 * - Topic Content
 */
class Imod {

	ImodUser		owner
	String			name
	String			url
	String			overview
	String			courseSemester
	String			courseLocation
	String			timeRatio
	Integer			numberOfSeats
	String			subjectArea
	Integer			creditHours
	String			imodNumber
	CoursePolicy	coursePolicy
	Schedule		schedule
	ScheduleDate	scheduleDate
	ScheduleRepeats	repeats
	Collection		learningObjectives
	ScheduleRepeatsEvery	repeatsEvery

	static belongsTo = ImodUser

	static hasMany = [
		learningObjectives:	LearningObjective,
		sharedOwners: 		ImodUser,
		instructors: 		Instructor,
		courseComponents: 	CourseComponentCode,
		audience: 			Audience,
		contents: 			Content
	]

	static constraints = {
		name			nullable: false,	blank: false
		creditHours		nullable: true
		numberOfSeats	nullable: true
		coursePolicy	nullable: true
		schedule		nullable: true
		scheduleDate	nullable: true
		timeRatio		nullable: true
		overview		nullable: true
		courseLocation	nullable: true
		courseSemester	nullable: true
		imodNumber		nullable: true
		repeats			nullable: true
		repeatsEvery	nullable: true

	}

	static mapping = {
		version false
	}

	def String toString() {
		return name
	}
}
