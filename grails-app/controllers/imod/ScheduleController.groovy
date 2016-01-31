package imod

import grails.converters.JSON
import groovy.json.JsonSlurper

class ScheduleController {



	def index(Long id) {
		[
			currentImod: Imod.get(id),
			currentPage: 'schedule'
		]
	}

	}
