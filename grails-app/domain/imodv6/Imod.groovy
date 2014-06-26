package imodv6

class Imod {
    ImodUser owner
    String name
    String url
    String overview
    String courseSemester
    String courseLocation
    String timeRatio
    Integer numberOfSeats
    String subjectArea
    Integer creditHours
    String imodNumber
    CoursePolicy coursePolicy
    Schedule schedule
    ScheduleDate scheduleDate
    ScheduleRepeats repeats
    ScheduleRepeatsEvery repeatsEvery
    Piechart piechart

    static belongsTo = ImodUser

    static hasMany = [learningObjectives: LearningObjective, sharedOwners: ImodUser, instructors: Instructor,
            courseComponents: CourseComponentCode, audience: Audience, contents: Content]

    static constraints = {
        name blank: false, nullable: false
        creditHours nullable: true
        numberOfSeats nullable: true
        coursePolicy nullable: true
        schedule nullable: true
        scheduleDate nullable: true
        piechart nullable: true
        timeRatio nullable: true
        overview nullable: true
        courseLocation nullable: true
        courseSemester nullable: true
        imodNumber nullable: true
        repeats nullable: true
        repeatsEvery nullable: true
    }

    static mapping = {
        version false
    }

    def String toString() {
        return name
    }
}
