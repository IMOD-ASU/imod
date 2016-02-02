package imod

import grails.converters.JSON
import groovy.json.JsonSlurper

class ScheduleController {

	static allowedMethods = [
		index: 'GET',
		findMatchingTechniques: 'POST'
	]

	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'schedule'
		]
	}

	}
