package imod

import org.joda.time.LocalTime
import org.jadira.usertype.dateandtime.joda.PersistentLocalTime

/**
 * Store all of the general information on when the course will happen
 */
class Schedule {
	Date startDate
	Date endDate
	LocalTime startTime
	LocalTime endTime
	ScheduleRepeats repeats
	ScheduleRepeatsEvery repeatsEvery

	static belongsTo = [
		imod: Imod
	]

	static hasMany = [
		scheduleWeekDays: ScheduleWeekDays
	]

    static constraints = {
		repeats			nullable: true
		repeatsEvery	nullable: true
		endDate         validator: { endDate, schedule ->
			endDate >= schedule.startDate
		}
		endTime			validator: { endTime, schedule ->
			endTime > schedule.startTime
		}
    }

	static mapping = {
		version false
		repeats lazy: false
		repeatsEvery lazy: false
		startTime type: PersistentLocalTime
		endTime   type: PersistentLocalTime
	}
}
