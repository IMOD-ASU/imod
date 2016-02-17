package imod

import grails.converters.JSON
import groovy.json.JsonSlurper

class ScheduleController {

	static allowedMethods = [
	index: 'GET',
	findMatchingTechniques: 'POST'
	]

	def index(Long id) {


		/*
		//final startDate1 = currentImod.schedule.date;
		final startDate1 = "currentImod";

		render(view: index, model:[p:startDate1]);

		def responseData = [
		'results': results,
		'status': results ? "OK" : "Nothing present"
		]
		*/

		//render responseData as JSON

		def x = "currentImod";
		def startDate1=[]
		def endDate1=[]
		def creditHours1 = -1
		def timeRatio1 = "no time"
		def currName1 = "noName"


		startDate1 = Imod.get(id).schedule.startDate
		endDate1 = Imod.get(id).schedule.endDate
		creditHours1 = Imod.get(id).creditHours
		timeRatio1 = Imod.get(id).timeRatio
		currName1 = Imod.get(id).name

		[
		currentImod: Imod.get(id),
		currentPage: 'schedule',
		startDate1: startDate1,
		endDate1: endDate1,
		creditHours1: creditHours1,
		timeRatio1: timeRatio1,
		currName1: currName1

		]

/*
		render (
		[
		startDate: startDate1,
		startDate1: startDate1,
		endDate1: endDate1,
		creditHours1: creditHours1,
		timeRatio1: timeRatio1
		]
		)

*/

	}

	def findImodScheduleInfo(){
		render (
		[
		startDate: startDate1,
		startDate1: startDate1,
		endDate1: endDate1,
		creditHours1: creditHours1,
		timeRatio1: timeRatio1
		]
		) as JSON
		}

		def schedule2() {
			render(view: "schedule2",  model: [name:"John Doe"])
		}

		def testData() {
			def result = [:]
			result['name'] = "Sales"
			result['type'] = "bar"
			result['data'] = [5, 20, 45, 10, 10, 20]
			[jsonTest: result as JSON]
		}
	}
