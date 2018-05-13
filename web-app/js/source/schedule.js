var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
// var isTopLeftClicked = 1;
// var isTopRightClicked = 1;
var oldView = 'month';
var profileVal = 0;
var profileBuffer = 100;
var imodStartDateYear;
var imodStartDateMonth;
var imodStartDateDay;
var imodEndDateYear;
var imodEndDateMonth;
var imodEndDateDay;
var imodId;
/* Purpose: Javascript Logic for the Schedule View in IMODS.
*
* @author Wesley Coomber Wesley.Coomber@asu.edu
*
* @version April 15 2016
*/

/*
Chart.js code over here
*/

function calculateLO (learningObjectives) {
	'use strict';
	var count = learningObjectives.length;
	var loPercent = 0;

	if (count > 2) {
		loPercent = 100;
	}
	return loPercent;
}

function calculateContent (contents) {
	'use strict';
	var count = contents.length;
	var contentPercent = 0;

	if (count === 0) {
		contentPercent = 0;
	} else if (count < 5) {
		contentPercent = 80;
	} else {
		contentPercent = 100;
	}
	return contentPercent;
}

function getAssignedTechniqueCount (lo) {
	'use strict';
	return $.ajax({
		url: baseUrl + 'assessmentTechnique/getAssignedAssessmtTechCount',
		type: 'GET',
		dataType: 'json',
		async: false,
		data: {
			id: lo.id
		}
	});
}

function getAssignedPedTechniqueCount (lo) {
	'use strict';
	return $.ajax({
		url: baseUrl + 'pedagogyTechnique/getAssignedPedTechCount',
		type: 'GET',
		dataType: 'json',
		async: false,
		data: {
			id: lo.id
		}
	});
}

function calculateAssignedTechCount (loList) {
	'use strict';
	var count = 0;

	$.each(loList, function (index, value) {
		count += getAssignedTechniqueCount(value).responseJSON.count;
	});
	return count;
}

function calculateAssignedPedTechCount (loList) {
	'use strict';
	var count = 0;

	$.each(loList, function (index, value) {
		count += getAssignedPedTechniqueCount(value).responseJSON.count;
	});
	return count;
}

function calculateAsst (assessmentTech, lolist) {
	'use strict';
	var asstPercent = 0;
	var count = calculateAssignedTechCount(lolist);

	if (count > 6) {
		asstPercent = 100;
	}
	return asstPercent;
}

function calculatePed (pedagogyTech, lolist) {
	'use strict';
	var pedPercent = 0;
	var count = calculateAssignedPedTechCount(lolist);

	if (count > 6) {
		pedPercent = 100;
	}
	return pedPercent;
}

function calculatePercentage (response) {
	'use strict';
	var data = response;
	var currentImod = data.currentImod;
	var user = data.user;
	var coPercent = 0;
	var instrPercent = 0;
	var loPercent = 0;
	var contentPercent = 0;
	var asstPercent = 0;
	var pedPercent = 0;
	var checkId = localStorage.getItem('checkId');

	if (checkId === 'new') {
		coPercent = 0;
		// return profileVal;
	} else {
		coPercent = 15;
		if (currentImod.instructors.length > 0) {
			instrPercent = 5;
		}

		if (currentImod.learningObjectives.length > 2) {
			profileBuffer -= 20;
		} else {
			profileBuffer = currentImod.learningObjectives.length * 40;
		}
		loPercent = calculateLO(currentImod.learningObjectives);
		contentPercent = calculateContent(currentImod.contents);
		asstPercent = calculateAsst(user.assessmentTechnique, currentImod.learningObjectives);
		pedPercent = calculatePed(user.pedagogyTechnique, currentImod.learningObjectives);
	}

	profileVal += coPercent + instrPercent + Math.round((loPercent + contentPercent + asstPercent + pedPercent) / 400 * profileBuffer);

	return profileVal;
}

function evaluateProfile () {
	'use strict';
	var progressbar = $('#progressbar');
	var checkId = localStorage.getItem('checkId');
	var profileValue = 0;

	$.ajax({
		url: baseUrl + 'imod/getCurrentImod',
		type: 'GET',
		dataType: 'json',
		data: {
			id: checkId
		},
		success: function (response) {
			profileValue = calculatePercentage(response);
			progressbar.progressbar('value', profileValue);
		}
	});
}

function newTaskValidationError () {
	'use strict';
	if ($('.errorcontain').length) {
		$('.errorcontain').css('color', 'red');
		$('.errorcontain').css('font-size', '0.8em');
	}
}

window.onload = function () {
	'use strict';
	evaluateProfile();
};

function filterEvents (selectedKnowledgeDimensions, selectedActivityTypes, selectedTaskEnvironments, fetchedEvents) {
	'use strict';
	var filteredEvents = [];
	var index = 0;

	if ((!selectedKnowledgeDimensions) &&
		(!selectedActivityTypes) &&
		(!selectedTaskEnvironments)) {
		filteredEvents = fetchedEvents;
	} else if (selectedKnowledgeDimensions.length === 0 && selectedActivityTypes.length === 0 && selectedTaskEnvironments.length === 0) {
		filteredEvents = fetchedEvents;
	} else if (selectedKnowledgeDimensions.length !== 0 && selectedActivityTypes.length === 0 && selectedTaskEnvironments.length === 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedKnowledgeDimensions.indexOf(fetchedEvents[index].knowD) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else if (selectedKnowledgeDimensions.length === 0 && selectedActivityTypes.length !== 0 && selectedTaskEnvironments.length === 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedActivityTypes.indexOf(fetchedEvents[index].activity) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else if (selectedKnowledgeDimensions.length === 0 && selectedActivityTypes.length === 0 && selectedTaskEnvironments.length !== 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedTaskEnvironments.indexOf(fetchedEvents[index].enviro) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else if (selectedKnowledgeDimensions.length !== 0 && selectedActivityTypes.length !== 0 && selectedTaskEnvironments.length === 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedKnowledgeDimensions.indexOf(fetchedEvents[index].knowD) > -1 &&
				selectedActivityTypes.indexOf(fetchedEvents[index].activity) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else if (selectedKnowledgeDimensions.length !== 0 && selectedActivityTypes.length === 0 && selectedTaskEnvironments.length !== 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedKnowledgeDimensions.indexOf(fetchedEvents[index].knowD) > -1 &&
				selectedTaskEnvironments.indexOf(fetchedEvents[index].enviro) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else if (selectedKnowledgeDimensions.length === 0 && selectedActivityTypes.length !== 0 && selectedTaskEnvironments.length !== 0) {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedActivityTypes.indexOf(fetchedEvents[index].activity) > -1 &&
				selectedTaskEnvironments.indexOf(fetchedEvents[index].enviro) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	} else {
		for (index = 0; index < fetchedEvents.length; index++) {
			if (selectedActivityTypes.indexOf(fetchedEvents[index].activity) > -1 &&
				selectedTaskEnvironments.indexOf(fetchedEvents[index].enviro) > -1 &&
				selectedKnowledgeDimensions.indexOf(fetchedEvents[index].knowD) > -1) {
				filteredEvents.push(fetchedEvents[index]);
			}
		}
	}
	return filteredEvents;
}


function loadCalendar (selectedKnowledgeDimensions, selectedActivityTypes, selectedTaskEnvironments) {
	'use strict';
	var hourRatio = 3;
	var inClassHours = 3;
	var outClassHours = inClassHours * hourRatio;

	var assignA = {
		name: 'Contructing Algorithims Lab',
		hours: 4.5,
		location: 'In-class'
	};
	var assignB = {
		name: 'Intro to Algorithims Reading',
		hours: 1,
		location: 'Out-Of-Class'
	};
	var assignC = {
		name: 'Data Structure Worksheet',
		hours: 3,
		location: 'Out-Of-Class'
	};

	var chart = null;

	var eventsForGraph = [];

	var years = [];
	var tempMonth;
	var tempEle;
	var inc = 0;
	var choiceHour;
	var choiceYear;
	var months;
	var choiceMonth;
	var days = [];
	var choiceDay;
	var hours = [];

	window.CanvasJS
		.addColorSet('greenShades',
			// colorSet Array
		[
			'#7CFC00',
			'#00FF00',
			'#76EE00',
			'#83F52C',
			'#5DFC0A',
			'#4DBD33',
			'#49E20E'
		]
		);

	window.CanvasJS.addColorSet('redShades', [
		// colorSet Array for Red Warnings

		'#FF0000',
		'#FF3030',
		'#DB2929',
		'#E3170D',
		'#FC1501'
	]);

	window.CanvasJS.addColorSet('orangeShades', [
		// colorSet Array for Orange-Yellow warnings

		'#ffb84d',
		'#ffad33',
		'#ffa31a',
		'#ff9900',
		'#cc7a00',
		'#e68a00'
	]);

	chart = new window.CanvasJS.Chart('chartContainer', {
		colorSet: 'greenShades',
		title: {
			text: 'Course Hours'
		},
		axisY: {
			title: 'Percent of total credit hours (' + (assignA.hours + assignB.hours + assignC.hours) + ' out of ' + outClassHours + ') per week'
		},
		animationEnabled: true,
		toolTip: {
			shared: true,
			// content: "{name}: {y} Hours - <strong>#percent%</strong>" + "(#percent)"
			content: '{name}: {y} Hours. {extra}'
		},
		data: [{
			type: 'stackedBar',
			showInLegend: true,
			name: '' + assignA.name,
			toolTipContent: '{name}: {y} Hours. ' + assignA.location,
			dataPoints: [{
				y: assignA.hours,
				label: 'iMods Week Visualizer'
			}]
		}, {
			type: 'stackedBar',
			showInLegend: true,
			name: '' + assignB.name,
			toolTipContent: '{name}: {y} Hours. ' + assignB.location,
			dataPoints: [{
				y: assignB.hours,
				label: 'iMods Week Visualizer'
			}]
		}, {
			type: 'stackedBar',
			showInLegend: true,
			name: '' + assignC.name,
			toolTipContent: '{name}: {y} Hours. ' + assignC.location,
			dataPoints: [{
				y: assignC.hours,
				label: 'iMods Week Visualizer'
			}]
		}]
	});
	// chart.render();

	// end of chart JS

	for (inc = 2010; inc <= 2035; inc++) {
		years.push(inc);
	}

	// Populate the choice box with choices from the years [2010 to 2035] array
	choiceYear = document.getElementById('chooseYear');
	for (inc = 0; inc < years.length; inc++) {
		// .log(inc);
		tempMonth = years[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceYear.appendChild(tempEle);
	}

	choiceYear = document.getElementById('chooseYear2');
	for (inc = 0; inc < years.length; inc++) {
		// console.log(inc);
		tempMonth = years[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceYear.appendChild(tempEle);
	}

	months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

	// Populate the choice box with choices from the months array
	choiceMonth = document.getElementById('chooseMonth');
	for (inc = 0; inc < months.length; inc++) {
		// console.log(inc);
		tempMonth = months[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceMonth.appendChild(tempEle);
	}

	choiceMonth = document.getElementById('chooseMonth2');
	for (inc = 0; inc < months.length; inc++) {
		// console.log(inc);
		tempMonth = months[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceMonth.appendChild(tempEle);
	}

	for (inc = 1; inc <= 31; inc++) {
		days.push(inc);
	}

	// Populate the choice box with choices from the days [1 to 31] array
	choiceDay = document.getElementById('chooseDay');
	for (inc = 0; inc < days.length; inc++) {
		// console.log(inc);
		tempMonth = days[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceDay.appendChild(tempEle);
	}

	choiceDay = document.getElementById('chooseDay2');
	for (inc = 0; inc < days.length; inc++) {
		// console.log(inc);
		tempMonth = days[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceDay.appendChild(tempEle);
	}

	for (inc = 1; inc <= 23; inc++) {
		hours.push(inc);
	}

	// Populate the choice box with choices from the hours [1 to 23] array
	choiceHour = document.getElementById('chooseHour');
	for (inc = 0; inc < hours.length; inc++) {
		// console.log(inc);
		tempMonth = days[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceHour.appendChild(tempEle);
	}

	choiceHour = document.getElementById('chooseHour2');
	for (inc = 0; inc < hours.length; inc++) {
		// console.log(inc);
		tempMonth = days[inc];
		tempEle = document.createElement('option');
		tempEle.textContent = tempMonth;
		tempEle.value = tempMonth;
		choiceHour.appendChild(tempEle);
	}
	$('#scheduleCalendar').fullCalendar({
		header: {
			left: 'prev title next',
			center: '',
			right: 'month basicWeek'
		},
		lazyFetching: false,
		// runs when view changes to week and vice-versa.
		// toggles the visibility (hide) of the
		viewRender: function (view) {
			// jquery.toggle() is ideal because it makes sure the chartContainer div doesnt take up space when its hidden, but it was causing rendering errors in firefox when switching between month and week view. would fix when i open firebug? not sure why
			if (view.name === 'basicWeek' && oldView === 'month') {
				// $("#chartContainer").toggle();
				// console.log("graphRevealed!");
				document.getElementById('chartContainer').style.visibility = 'visible';
				oldView = view.name;
			}
			if (view.name === 'month' && oldView === 'basicWeek') {
				// $("#chartContainer").toggle();
				document.getElementById('chartContainer').style.visibility = 'hidden';
				// console.log("graphHidden!");
				oldView = view.name;
			}
		},
		eventClick: function (event) {
			var dateSeparate = [];
			// all arguments: (event, jsEvent, view)
			var view = $('#scheduleCalendar').fullCalendar('getView');
			var beginMonthT = (view.start).toISOString();

			var beginMonthB = window.moment(beginMonthT);
			var beginMonthC = window.moment(beginMonthT);
			var beginMonthD = window.moment(beginMonthT);
			var beginMonthE = window.moment(beginMonthT);
			var beginMonthF = window.moment(beginMonthT);

			beginMonthB = beginMonthB.add(7, 'days');
			beginMonthC = beginMonthC.add(14, 'days');
			beginMonthD = beginMonthD.add(21, 'days');
			beginMonthE = beginMonthE.add(28, 'days');
			beginMonthF = beginMonthF.add(35, 'days');
			// alert("The view's title is " + beginMonthA.format('YYYY-M-D') + " " + beginMonthB.format('YYYY-M-D') + " " + beginMonthC.format('YYYY-M-D') + " " + beginMonthD.format('YYYY-M-D') + " " + beginMonthE.format('YYYY-M-D') + " " + beginMonthF.format('YYYY-M-D') + " ");

			event = JSON.parse(JSON.stringify(event));

			// set the values and open the modal
			$('#taskTitle').val(event.title);
			$('#taskLearningDomain').val(event.learnO);
			$('#taskKnowledgeDimension').val(event.knowD);
			$('#taskInfo').html(event.notes);
			$('#taskTypeOfActivity').val(event.activity);
			$('#taskEnvironment').val(event.enviro);
			$('#taskWorkTime').val(event.workTime);
			$('#taskEnviro').val(event.enviro);
			$('#taskLink').attr('href', event.url);
			$('#taskKnowDimensions').val(event.knowD);
			$('#taskID').val(event.id);
			$('#taskID2').val(event.id);

			if (event.start !== null) {
				dateSeparate = 	event.start.split('-');
			}

			if (dateSeparate !== null) {
				$('#taskStartDate_day').val(Number(dateSeparate[2].substring(0, 2)));
				$('#taskStartDate_month').val(Number(dateSeparate[1]));
				$('#taskStartDate_year').val(Number(dateSeparate[0]));
			}

			if (event.end !== null) {
				dateSeparate = 	event.end.split('-');
			}

			if (dateSeparate !== null) {
				$('#taskEndDate_day').val(Number(dateSeparate[2].substring(0, 2)));
				$('#taskEndDate_month').val(Number(dateSeparate[1]));
				$('#taskEndDate_year').val(Number(dateSeparate[0]));
			}

			$('.date-error').remove();
			$('.taskError').remove();
			$('#editCheck').text('yes');
			document.getElementById('deleteButton').style.visibility = 'visible';
			$('#add-new-technique').css('display', 'block');
			$('#topicDialogBackground').css('display', 'block');
			return false;
		},

		events: function (start, end, timezone, callback) {
			if (!document.getElementById('selectAllLOBox').checked) {
				$.ajax({
					url: '../../schedule/getEvents/',
					data: {
						learningObjectiveID: $('#lo').val(),
						// Start date is first day that is displayed
						// startDate: ((start.subtract(1, 'days')).toISOString()),
						startDate: (start).toISOString(),
						// End  date is last day that is displayed
						// endDate: ((end.add(1, 'days')).toISOString())
						endDate: (end).toISOString()
					},
					method: 'GET'
				})
					.done(function (data) {
						var fetchedEvents = data.events;
						var filteredEvents = [];
						var events = [];
						var hourSum = 0;
						var str = $('#timeRatioH').html();
						var cutStr = /[^:]*$/.exec(str)[0];
						var strB = $('#creditHoursH').html();
						var timeRatI = parseInt(cutStr, 10);
						var creditHoursI = parseInt(strB, 10);
						var outClassHoursB;
						var iterator;

						eventsForGraph = [];
						filteredEvents = filterEvents(selectedKnowledgeDimensions, selectedActivityTypes, selectedTaskEnvironments, fetchedEvents);

						function compare (firstEvent, secondEvent) {
							if (firstEvent.startDate < secondEvent.startDate) {
								return -1;
							} else if (firstEvent.startDate > secondEvent.startDate) {
								return 1;
							}
							return 0;
						}

						if (filteredEvents !== null) {
							filteredEvents.sort(compare);
						}

						$.each(filteredEvents, function (index, obj) {
							var tempEnd = window.moment(obj.endDate);
							var tempStart = window.moment(obj.startDate);

							events.push({
								title: obj.title,
								allDay: true,
								start: window.window.moment(obj.startDate, window.window.moment.ISO_8601),
								end: window.window.moment(obj.endDate, window.window.moment.ISO_8601),
								learnO: obj.learnO,
								knowD: obj.knowD,
								activity: obj.activity,
								enviro: obj.enviro,
								workTime: obj.workTime,
								description: obj.notes,
								id: obj.id,
								notes: obj.notes,

								// this used to be a temporary learnO variable input, but due to time constraints I've made it the field where you enter in a url for additional online resources
								url: obj.learnO
							});

							if (tempEnd > start && tempStart < end) {
								eventsForGraph.push({
									type: 'stackedBar',
									showInLegend: true,
									name: '' + obj.title,
									toolTipContent: '{name}: {y} Hours. ' + obj.enviro,
									dataPoints: [{
										y: obj.workTime,
										label: 'iMods Week Visualizer',
										x: 0
									}]
								});
							}
						});

						if (eventsForGraph.length === 0) {
							eventsForGraph.push({
								type: 'stackedBar',
								showInLegend: true,
								name: '',
								toolTipContent: 'No activity',
								dataPoints: [{
									y: 0,
									label: 'iMods Week Visualizer',
									x: 0
								}]
							});
						}
						// console.info(events);
						// console.info(eventsForGraph);
						callback(events);

						// if (eventsForGraph.length < chart.options.data.length) {
						// 	chart.options.data = [];
						// }


						chart.options.data = [];

						for (iterator = 0; iterator < eventsForGraph.length; iterator++) {
							chart.options.data[iterator] = eventsForGraph[iterator];

							hourSum += eventsForGraph[iterator].dataPoints[0].y;
						}
						// multiply the credit hours of the course by the time ratio to get the hours per week that students should be working. Eg. 3 credit hour course, a 1:3 time ratio, means 3x3 = 9 hours per week of expected working time.
						outClassHoursB = timeRatI * creditHoursI;

						chart.options.axisY.title = 'Currently Assigned Working Hours (' + (hourSum) + ' out of ' + outClassHoursB + ') per week';

						chart.options.axisY.maximum = outClassHoursB;
						// chart.options.axisY.minimum = outClassHoursB

						chart.options.colorSet = 'greenShades';

						if (((hourSum / outClassHoursB) > 0.0) && ((hourSum / outClassHoursB) <= 0.20)) {
							chart.options.colorSet = 'redShades';
						} else if (((hourSum / outClassHoursB) > 0.20) && ((hourSum / outClassHoursB) <= 0.80)) {
							chart.options.colorSet = 'orangeShades';
						} else if (((hourSum / outClassHoursB) > 0.80) && ((hourSum / outClassHoursB) <= 1.00)) {
							chart.options.colorSet = 'greenShades';
						} else {
							chart.options.colorSet = 'redShades';
						}

						chart.render();

						events.splice(0, events.length);
					});
			}

			if (document.getElementById('selectAllLOBox').checked) {
				$.ajax({
					url: '../../schedule/getAllEvents/',
					data: {
						learningObjectiveID: $('#lo').val(),
						imodId: imodId,
						startDate: (start).toISOString(),

						endDate: (end).toISOString()
					},
					method: 'GET'
				})
					.done(function (data) {
						var fetchedEvents = [];
						var filteredEvents = [];
						var events = [];
						var hourSum = 0;
						var str = $('#timeRatioH').html();
						var cutStr = /[^:]*$/.exec(str)[0];
						var strB = $('#creditHoursH').html();
						var timeRatI = parseInt(cutStr, 10);
						var creditHoursI = parseInt(strB, 10);
						var outClassHoursB;
						var iterator;
						var loopIndex = 0;

						for (loopIndex = 0; loopIndex < data.events.length; loopIndex++) {
							if (!fetchedEvents.includes(data.events[loopIndex])) {
								fetchedEvents = fetchedEvents.concat(data.events[loopIndex]);
							}
						}

						eventsForGraph = [];
						filteredEvents = filterEvents(selectedKnowledgeDimensions, selectedActivityTypes, selectedTaskEnvironments, fetchedEvents);

						function compare (firstEvent, secondEvent) {
							if (firstEvent.startDate < secondEvent.startDate) {
								return -1;
							} else if (firstEvent.startDate > secondEvent.startDate) {
								return 1;
							}
							return 0;
						}

						if (filteredEvents !== null) {
							filteredEvents.sort(compare);
						}

						$.each(filteredEvents, function (index, obj) {
							var tempEnd = window.moment(obj.endDate);
							var tempStart = window.moment(obj.startDate);

							events.push({
								title: obj.title,
								allDay: true,
								start: window.window.moment(obj.startDate, window.window.moment.ISO_8601),
								end: window.window.moment(obj.endDate, window.window.moment.ISO_8601),
								learnO: obj.learnO,
								knowD: obj.knowD,
								activity: obj.activity,
								enviro: obj.enviro,
								workTime: obj.workTime,
								description: obj.notes,
								id: obj.id,
								notes: obj.notes,

								// this used to be a temporary learnO variable input, but due to time constraints I've made it the field where you enter in a url for additional online resources
								url: obj.learnO
							});

							if (tempEnd > start && tempStart < end) {
								eventsForGraph.push({
									type: 'stackedBar',
									showInLegend: true,
									name: '' + obj.title,
									toolTipContent: '{name}: {y} Hours. ' + obj.enviro,
									dataPoints: [{
										y: obj.workTime,
										label: 'iMods Week Visualizer',
										x: 0
									}]
								});
							}
						});

						if (eventsForGraph.length === 0) {
							eventsForGraph.push({
								type: 'stackedBar',
								showInLegend: true,
								name: '',
								toolTipContent: 'No activity',
								dataPoints: [{
									y: 0,
									label: 'iMods Week Visualizer',
									x: 0
								}]
							});
						}
						// console.info(events);
						// console.info(eventsForGraph);
						callback(events);

						// if (eventsForGraph.length < chart.options.data.length) {
						// 	chart.options.data = [];
						// }


						chart.options.data = [];

						for (iterator = 0; iterator < eventsForGraph.length; iterator++) {
							chart.options.data[iterator] = eventsForGraph[iterator];

							hourSum += eventsForGraph[iterator].dataPoints[0].y;
						}
						// multiply the credit hours of the course by the time ratio to get the hours per week that students should be working. Eg. 3 credit hour course, a 1:3 time ratio, means 3x3 = 9 hours per week of expected working time.
						outClassHoursB = timeRatI * creditHoursI;

						chart.options.axisY.title = 'Currently Assigned Working Hours (' + (hourSum) + ' out of ' + outClassHoursB + ') per week';

						chart.options.axisY.maximum = outClassHoursB;
						// chart.options.axisY.minimum = outClassHoursB

						chart.options.colorSet = 'greenShades';

						if (((hourSum / outClassHoursB) > 0.0) && ((hourSum / outClassHoursB) <= 0.20)) {
							chart.options.colorSet = 'redShades';
						} else if (((hourSum / outClassHoursB) > 0.20) && ((hourSum / outClassHoursB) <= 0.80)) {
							chart.options.colorSet = 'orangeShades';
						} else if (((hourSum / outClassHoursB) > 0.80) && ((hourSum / outClassHoursB) <= 1.00)) {
							chart.options.colorSet = 'greenShades';
						} else {
							chart.options.colorSet = 'redShades';
						}

						chart.render();

						events.splice(0, events.length);
					});
			}
		}
	});
}

document.getElementById('selectAllLOBox').addEventListener('change', function () {
	'use strict';
	$('#scheduleCalendar').fullCalendar('destroy');
	loadCalendar();
});

$(document).ready(function () {
	'use strict';
	//  hide the chart container on page load
	document.getElementById('chartContainer').style.visibility = 'hidden';
	loadCalendar();

	$('#addT').click(function () {
		var sYear = $('#chooseYear option:selected').text();
		var sMonth = $('#chooseMonth option:selected').index();
		var sDay = $('#chooseDay option:selected').text();
		var sHour = $('#chooseHour option:selected').text();

		var eYear = $('#chooseYear2 option:selected').text();
		var eMonth = $('#chooseMonth2 option:selected').index();
		var eDay = $('#chooseDay2 option:selected').text();
		var eHour = $('#chooseHour2 option:selected').text();
		// console.log(sYear + sMonth + sDay + sHour);

		var startDate = new Date(sYear, sMonth, sDay, sHour, 0, 0, 0);
		var endDate = new Date(eYear, eMonth, eDay, eHour, 0, 0, 0);
		// console.log(d1);

		addEvent(startDate, endDate, 'testTask', 'testDescript');
	});
});

function addEvent (startDate, endDate, title, desc) {
	'use strict';
	$('#scheduleCalendar')
		.fullCalendar('renderEvent', {
			id: startDate,
			title: title,
			start: startDate,
			end: endDate,
			description: desc,
			allDay: true
		}, true);
}

// $("#chartContainer").toggle();

// Cancel Button logic for +AddNewTask modal popup here.
$('button[name=cancelButton]').click(function () {
	'use strict';
	$('#add-new-technique, #topicDialogBackground').hide();
	return false;
});

$('button[name=cancelButton]').click(function () {
	'use strict';
	$('#edit-technique, #topicDialogBackground').hide();
	return false;
});
/*
ASSESSMENT.JS STARTS HERE
*/

/**
 * Opens the modal to create a new assessment technique
 */
function openAssessmentPlanModal () {
	'use strict';
	$('#topicDialogBackground').css('display', 'block');
	$('#assessment-plan').css('display', 'block');
}

/**
 * Opens the modal to create a new assessment technique
 */
function openNewAssessmentTechniqueModal () {
	'use strict';

	// reset form on new modal open
	$('.date-error').remove();
	$('.taskError').remove();
	document.getElementById('deleteButton').style.visibility = 'hidden';
	$('#add-new-technique').find('input:not(#lo, #imodId), select, textarea').val('');
	$('#dimImageModal')
		.prop('src', '../../images/content/knowDimNone.png')
		.prop('title', '');

	$('#techniqueId').val('');
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');
	$('#add-new-technique').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}

function editAssessmentTechniqueModal () {
	'use strict';

	// reset form on new modal open
	$('#edit-technique').find('input:not(#lo, #imodId), select, textarea').val('');
	$('#dimImageModal')
		.prop('src', '../../images/content/knowDimNone.png')
		.prop('title', '');

	$('#techniqueId').val('');
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');
	$('#edit-technique').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}

function closeDimModal () {
	'use strict';
	var checked = '';
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');

	$('#selectKnowledgeDimensions input[type=checkbox]').each(function () {
		if ($(this).is(':checked')) {
			if (checked === '') {
				checked += ($(this).val());
			} else {
				checked += ', ' + ($(this).val());
			}
		}
	});
	document.getElementById('knowledgeDimension').value = checked;
	document.getElementById('dimImageModal').title = checked;
	dialog.css('display', 'none');
	background.css('display', 'none');
}

function closeDimModalCancel () {
	'use strict';

	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');

	dialog.css('display', 'none');
	background.css('display', 'none');

	return false;
}

function changePic () {
	'use strict';
	var iconName = '';

	$('#selectKnowledgeDimensions').find('input:checkbox').each(
		function () {
			if ($(this).prop('checked')) {
				iconName += $(this).val().charAt(0);
			}
		}
	);
	if (iconName === '') {
		iconName = $('#imgNone').attr('href');
	} else {
		iconName = $('#img' + iconName).attr('href');
	}
	$('#dimImageModal').attr('src', iconName);
	$('#dimImage').attr('src', iconName);
}

function openDimModal () {
	'use strict';
	var dimString = $('#knowDimensionList').val();
	var dimensionList = [];
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');
	var index;
	var findCheckBox;

	if (dimString !== '' && dimString !== null && typeof dimString !== 'undefined') {
		dimensionList = dimString.split(',');
	}
	for (index = 0; index < dimensionList.length; index++) {
		findCheckBox = $(dialog).find('#' + dimensionList[index]);
		if (findCheckBox.length === 1) {
			findCheckBox.prop('checked', true);
		}
	}
	changePic();
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');
	dialog.css('display', 'inherit');
	background.css('display', 'block');
	return false;
}

function populateAssessmentTechnique (data, isClone) {
	'use strict';
	var currentTechnique = data.assessmentTechnique;
	var count;
	var checked = '';
	var arrayOfKnowledgeDimensions = data.knowledgeDimension.split(',');
	var arrayOfLearningDomains = data.learningDomains.split(',');
	var arrayOfDomainCategories = data.domainCategories.split(',');

	$('#titlecheck').val(currentTechnique.title);
	$('#dimImageModal').attr('title', data.knowledgeDimension.substring(0, data.knowledgeDimension.length));
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');

	// Set the text fields
	if (!isClone) {
		$('#title').val(currentTechnique.title);
	}
	// $('#title2').val(currentTechnique.title);

	$('#description').val(currentTechnique.description);
	// $('#description2').val(currentTechnique.description);

	$('#procedure').val(currentTechnique.procedure);
	// $('#procedure2').val(currentTechnique.procedure);

	$('input[name=\'duration\']').val(currentTechnique.duration);
	// $('input[name=\'duration2\']').val(currentTechnique.duration);

	$('#activityDescription').val(currentTechnique.description);

	if (currentTechnique.assessmentFeedback !== null) {
		$('#feedback-' + currentTechnique.assessmentFeedback.id).prop('selected', true);
	}

	$('#assessmentDifficulty option[value=' + currentTechnique.difficulty + ']').prop('selected', true);
	$('#assessmentTime option[value=' + currentTechnique.whenToCarryOut + ']').prop('selected', true);
	$('#assessmentType option[value=' + currentTechnique.type + ']').prop('selected', true);

	$('#references').val(currentTechnique.reference);

	for (count = 0; count < arrayOfKnowledgeDimensions.length; count++) {
		if (arrayOfKnowledgeDimensions[count] !== '') {
			$('#' + arrayOfKnowledgeDimensions[count]).prop('checked', true);
		}
	}
	$('#selectKnowledgeDimensions input[type=checkbox]').each(function () {
		if ($(this).is(':checked')) {
			checked = checked + ($(this).val()) + ',';
		}
	});
	changePic();
	document.getElementById('knowledgeDimension').value = checked;

	for (count = 0; count < arrayOfLearningDomains.length; count++) {
		if (arrayOfLearningDomains[count] !== '') {
			$('#learningDomain option[value="' + arrayOfLearningDomains[count] + '"]').attr('selected', 'selected');
		}
	}
	for (count = 0; count < arrayOfDomainCategories.length; count++) {
		if (arrayOfDomainCategories[count] !== '') {
			$('#domainCategory option[value = "' + arrayOfDomainCategories[count] + '"]').attr('selected', 'selected');
		}
	}

	$('#View').hide();
}

function displayAssessmentInformationInEdit (isClone, block) {
	'use strict';
	var res = '';

	var str = $('label.ui-state-hover').attr('for');
	var indexNo = str.indexOf('Extended');

	if (isClone) {
		$('.assessment-title').html('<strong>Edit Alternate Name For Clone</strong>');
	} else {
		$('.assessment-title').html('<strong>Edit Assessment Technique</strong>');
	}

	if (block.parents('.assessment-block').hasClass('isAdmin') && $('a.admin-link').length < 1) {
		$('.admin-edit-msg').remove();
		if (isClone) {
			$('#saveButton').show();
			$('.admin-edit-msg').remove();
		} else {
			$('.assessment-title').html('<strong>View Assessment Technique</strong>');
			$('#saveButton').hide();
			$('#saveButton').after('<div class="admin-edit-msg">This technique is provided by the administrator and cannot be edited</div>');
		}
	} else {
		$('#saveButton').show();
		$('.admin-edit-msg').remove();
	}

	if (indexNo > -1) {
		res = str.substring(0, indexNo);
	} else {
		res = $('label.ui-state-hover').attr('for');
	}

	$('#techniqueId').val(res);


	$.ajax({
		url: '../../assessmentTechnique/display/' + res,
		method: 'GET'
	})
		.done(function (data) {
			populateAssessmentTechnique(data, isClone);
			window.cleanForm = $('form').find('select, textarea, input').serialize();
		});
}

window.onbeforeunload = function warning () {
	'use strict';
};

function assessmentEqualHeights (parent) {
	'use strict';
	var max = 0;
	var isOpen = false;
	var parentBlock = $(parent);
	var assessmentBlock = parentBlock.find('.assessment-block');

	if (parentBlock.css('display') === 'none') {
		parentBlock.show();
		isOpen = true;
	}

	assessmentBlock.each(function () {
		var height = $(this).height();

		if (max < height) {
			max = height;
		}
	});
	assessmentBlock.height(max);
	if (isOpen) {
		parentBlock.hide();
	}
}

function displayAssessmentFavoriteTechniques (data) {
	'use strict';
	var text = '';
	var index;
	var currentTechnique;
	var favoriteImgToggle = '';
	var isAdmin = '';

	if (data.assessmentTechniques.length < 1) {
		text = '<br><strong>You do not have any favorite techniques</strong><br><br>';
	}

	for (index = 0; index < data.assessmentTechniques.length; index++) {
		currentTechnique = data.assessmentTechniques[index];

		favoriteImgToggle = '../../images/fav.png';

		if (currentTechnique.isAdmin) {
			isAdmin = 'isAdmin';
		} else {
			isAdmin = '';
		}

		text += '<input type="radio" id="' + currentTechnique.id + '" name="assessmentTechnique" value="' + currentTechnique.id + '">';
		text += '<label class="assessment-block ' + isAdmin + '" for="' + currentTechnique.id + '"><div class="favorite topLeft"><img src="' + favoriteImgToggle + '"/>' +
			'</div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><br><br><button class="new-technique-popup-button clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
	}

	$('.favorites-inner').html(text);
	$('.favorites-inner').buttonset();
	$('.assessment-block').addClass('ui-button ui-widget ui-state-default ui-button-text-only');
	assessmentEqualHeights('.favorites-inner');
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible assessment techniques
 */
function filterAssessmentTechniques () {
	'use strict';
	// Get all of the selected checkboxes
	var knowledgeDimensions = $('input[name=knowledgeDimension]');
	var activityTypes = $('input[name=activityType]');
	var taskEnvironments = $('input[name=taskEnv]');
	var selectedKnowledgeDimensions = [];
	var selectedActivityTypes = [];
	var selectedTaskEnvironments = [];
	var index;

	// Get the id of the grails domain from the value attribute in the html
	for (index = 0; index < knowledgeDimensions.length; index++) {
		if (knowledgeDimensions[index].checked) {
			selectedKnowledgeDimensions.push(knowledgeDimensions[index].value);
		}
	}

	for (index = 0; index < activityTypes.length; index++) {
		if (activityTypes[index].checked) {
			selectedActivityTypes.push(activityTypes[index].value);
		}
	}

	for (index = 0; index < taskEnvironments.length; index++) {
		if (taskEnvironments[index].checked) {
			selectedTaskEnvironments.push(taskEnvironments[index].value);
		}
	}

	$('#scheduleCalendar').fullCalendar('destroy');
	loadCalendar(selectedKnowledgeDimensions, selectedActivityTypes, selectedTaskEnvironments);
}

$('input[name=knowledgeDimension]').on('change',
	function () {
		'use strict';
		var checkBoxName = 'knowledgeDimension';

		updateTextArea(checkBoxName);
		filterAssessmentTechniques();
		$('#selectAllkD').prop('checked', false);
	});

$('input[name=activityType]').on('change',
	function () {
		'use strict';
		var checkBoxName = 'learningDomain';

		updateTextArea(checkBoxName);
		filterAssessmentTechniques();
		$('#selectAlllD').prop('checked', false);
	});

$('input[name=taskEnv]').on('change',
	function () {
		'use strict';
		var checkBoxName = 'domainCategory';

		updateTextArea(checkBoxName);
		filterAssessmentTechniques();
		$('#selectAlldC').prop('checked', false);
	});


function truncateString (string, count) {
	'use strict';
	if (string.length > count) {
		return string.substring(0, count) + '...';
	}

	return string;
}

// Load techniques on page load

// Filters for the assessment technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();

$('#favorites').click(function () {
	'use strict';
	$('.favDiv').css('visibility', 'visible');
	$('.favDiv').css('display', 'block');
	$('#assessmentFavorites').css('visibility', 'visible');
	$('#assessmentFavorites').css('display', 'block');
	$('#assessmentFavoritesDiv').css('visibility', 'visible');
	$('#assessmentFavoritesDiv').css('display', 'block');

	$('#favorites').hide();
	$('#unfavorites').show();
});

$('#unfavorites').click(function () {
	'use strict';
	$('.favDiv').css('visibility', 'hidden');
	$('.favDiv').css('display', 'none');
	$('#assessmentFavorites').css('visibility', 'hidden');
	$('#assessmentFavorites').css('display', 'none');
	$('#assessmentFavoritesDiv').css('visibility', 'hidden');
	$('#assessmentFavoritesDiv').css('display', 'none');

	$('#favorites').show();
	$('#unfavorites').hide();
});

// Auto hide the assessment plan modal
// $('#assessment-plan').dialog({
//  autoOpen: false
// });

// Auto hide the add new technique modal
$('#new-technique').dialog({
	autoOpen: false
});

// Auto hide the add new technique modal
$('#display-new-technique').dialog({
	autoOpen: false
});

// Attach a listener to the checkboxes, to update the assessment techniques
// when the filters have been changed
// $('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
// $('input[name=learningDomain]').on('change', filterAssessmentTechniques);
// $('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// When add new technique button is clicked open modal
$('#new-technique-button').on('click', function () {
	'use strict';
	$('.assessment-title').html('<strong>Add New Assessment Technique</strong>');
	openNewAssessmentTechniqueModal();
	$('#saveButton').show();
	$('.admin-edit-msg').remove();
});

// When add assessment  plan button is clicked open modal
$('#assessment-plan-button').on('click', openAssessmentPlanModal);

$('#closeAssessmentPlan').on('click', function () {
	'use strict';
	$('#assessment-plan').css('display', 'none');
	$('#topicDialogBackground').css('display', 'none');
});

// Clicking on edit and View in display technique modal
$('#Edit').click(function () {
	'use strict';
	$('#View').show();
	$('.allInputs').show();
	$('.allspans1').hide();
});

$('#View').click(function () {
	'use strict';
	$('.allInputs').hide();
	$('.allspans1').show();
});

$('.select-all').on('click', function () {
	'use strict';
	if ($(this).is(':checked')) {
		$(this).closest('div').find(':checkbox').prop('checked', true);
	} else {
		$(this).closest('div').find(':checkbox').prop('checked', false);
	}
	filterAssessmentTechniques();
});

$('#title').change(function () {
	'use strict';
	if ($('#title').val() === $('#titlecheck').val()) {
		$('#errorMessage').text('Enter title which is different from the original technique');
		return false;
	}
	return true;
});

function getMinHeight (liArray) {
	'use strict';
	var minHeight = Math.floor(liArray.eq(0).height());

	liArray.each(
		function () {
			var refineText;

			if (Math.floor($(this).height()) < minHeight) {
				minHeight = Math.floor($(this).height());
			}
			refineText = $('a', this).text().replace(/[\s\t]+/g, ' ');
			$('a', this).text(refineText);
		}
	);
	return minHeight;
}

/* Populate the text above ideal matches*/
function updateTextArea (checkBoxName) {
	'use strict';
	var allVals = [];
	var valsLength;
	var text = '';
	var right = '';

	$('input[name=' + checkBoxName + ']:checked').each(function () {
		allVals.push($(this).prev().prev().text().trim());
	});
	valsLength = allVals.length;

	switch (checkBoxName) {
		case 'domainCategory':
			text = 'Domain Category';
			right = '&nbsp;&nbsp;<i class="fa fa-caret-right"></i>';
			break;
		case 'learningDomain':
			text = 'Learning Domain';
			right = '&nbsp;&nbsp;<i class="fa fa-caret-right"></i>';
			break;
		case 'knowledgeDimension':
			text = 'Knowledge Dimension';
			right = '';
			break;
	}

	if (allVals.length > 2) {
		$('#' + checkBoxName + 'span').html('<b>' + text + ' (' + valsLength + ' Selections)</b>' + right + '</span>&nbsp;&nbsp;');
	} else {
		$('#' + checkBoxName + 'span').html('<b>' + allVals + '</b>' + right + '</span>&nbsp;&nbsp;');
	}
}

/* callback added to do something when the response of the asyncronous ajax call has arrived*/
function populateDomainCategories (callback) {
	'use strict';
	$.ajax({
		url: baseUrl + 'learningObjective/getDomainCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#learning-domain').val().trim()
		},
		success: function (data) {
			// Stores the data from the call back
			var categories = data.value;
			// This stores the new html that will be added
			var options = '';
			var index;

			// For each of the categories
			for (index = 0; index < categories.length; index++) {
				// Create the html for the category
				options += '<option value="' + categories[index].name + '">' + categories[index].name + '</option>';
			}
			// Store this to the page
			$('#domain-category').empty().append(options);
			if (callback) {
				callback();
			}
		}
	});
}


$(document).ready(
	function () {
		'use strict';
		var liArray;
		var height;
		var currHeader;
		var currContent;
		var isPanelSelected;

		// Load techniques on page load
		// The filters for the assessment technique are wrapped in a accordian
		// beforeActivate is to be able to open both ideal & extended matches simultaneously
		$('#filter-assessment-techniques').accordion({
			collapsible: true,
			heightStyle: 'content'
		});
		$('#assessment-plan-accordion').accordion({
			collapsible: true,
			heightStyle: 'content',
			active: false
		});
		// clone
		$(document).on('click', '.clone', function () {
			openNewAssessmentTechniqueModal();
			displayAssessmentInformationInEdit(true, $(this));
			document.getElementById('cloneDetect').value = 'clone';
			$('#title').val('');
			$('#techniqueId').val('');
			$('.assessment-title').html('<strong>Enter Alternate Name For Clone</strong>');
			return false;
		});
		$('#ideal-matches-toggle').accordion({
			collapsible: true,
			beforeActivate: function (event, ui) {
				// The accordion believes a panel is being opened
				if (ui.newHeader[0]) {
					currHeader = ui.newHeader;
					currContent = currHeader.next('.ui-accordion-content');
					// The accordion believes a panel is being closed
				} else {
					currHeader = ui.oldHeader;
					currContent = currHeader.next('.ui-accordion-content');
				}
				// Since we've changed the default behavior, this detects the actual status
				isPanelSelected = currHeader.attr('aria-selected') === 'true';
				// Toggle the panel's header
				currHeader.toggleClass('ui-corner-all', isPanelSelected).toggleClass('accordion-header-active ui-state-active ui-corner-top', !isPanelSelected).attr('aria-selected', ((!isPanelSelected).toString()));
				// Toggle the panel's icon
				currHeader.children('.ui-icon').toggleClass('ui-icon-triangle-1-e', isPanelSelected).toggleClass('ui-icon-triangle-1-s', !isPanelSelected);
				// Toggle the panel's content
				currContent.toggleClass('accordion-content-active', !isPanelSelected);
				if (isPanelSelected) {
					currContent.slideUp();
				} else {
					currContent.slideDown();
				}
				// Cancels the default action
				return false;
			}
		});
		$('#k1').click(openDimModal);
		$('#knowDimFinished').click(closeDimModal);
		$('#closeKnowDim').click(closeDimModalCancel);
		$('#selectKnowledgeDimensions').on('change', 'input:checkbox', changePic);
		// Attach a listener to the checkboxes, to update the pedaogy techniques
		// when the filters have been changed


		$('#saveButton').on('click', function () {
			var errorLabel;

			$('.date-error').remove();
			$('.taskError').remove();

			if ($('#taskTitle').val() === '') {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task title is mandatory</label></div>';
				$('#taskTitle').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ($('#taskLearningDomain').val() === '' || $('#taskLearningDomain').val() === null) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Choose at least one Learning domain</label></div>';
				$('#taskLearningDomain').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ($('#taskKnowledgeDimension').val() === '' || $('#taskKnowledgeDimension').val() === null) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Choose at least one Knowledge Dimension</label></div>';
				$('#taskKnowledgeDimension').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ($('#taskTypeOfActivity').val() === '' || $('#taskTypeOfActivity').val() === null) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Choose at least one type</label></div>';
				$('#taskTypeOfActivity').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if ($('#taskStartDate_day').val() === '' || $('#taskStartDate_month').val() === '' ||
				$('#taskStartDate_year').val() === '') {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Start date cannot be empty </label></div>';
				$('#startDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ($('#taskEndDate_day').val() === '' || $('#taskEndDate_month').val() === '' ||
				$('#taskEndDate_year').val() === '') {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">End date cannot be empty </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if (Number($('#taskEndDate_year').val()) < Number($('#taskStartDate_year').val())) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">End date cannot be smaller than start date </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskEndDate_year').val()) === Number($('#taskStartDate_year').val())) &&
				(Number($('#taskEndDate_month').val()) < Number($('#taskStartDate_month').val()))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">End date cannot be smaller than start date </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskEndDate_year').val()) === Number($('#taskStartDate_year').val())) &&
				(Number($('#taskEndDate_month').val()) === Number($('#taskStartDate_month').val())) &&
				(Number($('#taskEndDate_day').val()) < Number($('#taskStartDate_day').val()))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">End date cannot be smaller than start date </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if (Number($('#taskStartDate_year').val()) < Number(imodStartDateYear)) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task start date should be greater than imod start date </label></div>';
				$('#startDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskStartDate_year').val()) === Number(imodStartDateYear)) &&
				(Number($('#taskStartDate_month').val()) < Number(imodStartDateMonth))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task start date should be greater than imod start date  </label></div>';
				$('#startDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskStartDate_year').val()) === Number(imodStartDateYear)) &&
				(Number($('#taskStartDate_month').val()) === Number(imodStartDateMonth)) &&
				(Number($('#taskStartDate_day').val()) < Number(imodStartDateDay))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task start date should be greater than imod start date   </label></div>';
				$('#startDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if (Number($('#taskEndDate_year').val()) > Number(imodEndDateYear)) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task end date should be less than imod end date </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskEndDate_year').val()) === Number(imodEndDateYear)) &&
				(Number($('#taskEndDate_month').val()) > Number(imodEndDateMonth))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task end date should be less than imod end date </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}
			if ((Number($('#taskEndDate_year').val()) === Number(imodEndDateYear)) &&
				(Number($('#taskEndDate_month').val()) === Number(imodEndDateMonth)) &&
				(Number($('#taskEndDate_day').val()) > Number(imodEndDateDay))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Task end date should be less than imod end date  </label></div>';
				$('#endDateError').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if ($('#taskEnvironment').val() === '' || $('#taskEnvironment').val() === null) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Select task environmant</label></div>';
				$('#taskEnvironment').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if ($('#taskWorkTime').val() === '' || $('#taskEnvironment').val() === null) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Please enter working hours</label></div>';
				$('#taskWorkTime').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			if (isNaN(Number($('#taskWorkTime').val()))) {
				errorLabel = '<div class="errorcontain" ><label class="taskError error">Please enter numeric vaule for working hours</label></div>';
				$('#taskWorkTime').parent().append(errorLabel);
				newTaskValidationError();
				return false;
			}

			return true;
		});

		// When add new technique button is clicked open modal
		$('#add-new-technique-button').on('click', function () {
			openNewAssessmentTechniqueModal();
			$('.assessment-title').html('<strong>Add New Assessment Technique</strong>');
		});

		$('#edit-button').on('click', function () {
			editAssessmentTechniqueModal();
			$('.assessment-title').html('<strong>Add New Assessment Technique</strong>');
		});

		// When hovered over LO side-tab list, it displays full text as tool-tip
		liArray = $('ul.learning-objective.list-wrapper').children('li');
		height = getMinHeight(liArray);

		liArray.each(
			function () {
				$('a', this).attr('title', $('a', this).text());
				if (Math.floor($(this).height()) !== height) {
					$('a', this).text($('a', this).text().substring(0, 70) + '...');
				}
				if ($(this).hasClass('active')) {
					$('a', this).text($('a', this).attr('title'));
				}
			}
		);

		// Listen for the selected learning domain to change, when it does call ajax
		$('#learning-domain').on(
			'change',
			function () {
				populateDomainCategories();
			}
		);


		// Open favorite techniques
		$('#favorites-button').click(function () {
			$('.modalBackgroundFavorites').css('display', 'block');
			$('.favorites-modal').css('display', 'block');

			$.ajax({
				url: '../../assessmentTechnique/favorites/',
				method: 'GET'
			})
			.done(function (data) {
				displayAssessmentFavoriteTechniques(data);
			});

			return false;
		});

		$('.modalBackgroundFavorites, #closeFavoritesModalButton').click(function () {
			$('.modalBackgroundFavorites').css('display', 'none');
			$('.favorites-modal').css('display', 'none');

			return false;
		});

		$(document)
			.on('click', '.favorites-modal .text-block.title', function () {
				openNewAssessmentTechniqueModal();
				displayAssessmentInformationInEdit(false, $(this));
				return false;
			});
	}
);
