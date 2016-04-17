var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var isTopLeftClicked = 1;
var isTopRightClicked = 1;
var hourRatio = 3;
var inClassHours = 3;
var outClassHours = inClassHours * hourRatio;
var assignAlgo = {
	name: 'Contructing Algorithims Lab',
	hours: 4.5,
	location: 'In-class'
};
var assignIntroToAlgo = {
	name: 'Intro to Algorithims Reading',
	hours: 1,
	location: 'Out-Of-Class'
};
var assignDataStructures = {
	name: 'Data Structure Worksheet',
	hours: 3,
	location: 'Out-Of-Class'
};

/*
	Chart.js code over here
*/
// console.log(outClassHours);
window.onload = function () {
	'use strict';
	var chart;

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

	chart = new window.CanvasJS.Chart('chartContainer',
		{
			colorSet: 'greenShades',
			title: {
				text: 'Division of out-of-class hours in Course'
			},
			axisY: {
				title: 'Percent of total credit hours (' + assignAlgo.hours + assignIntroToAlgo.hours + assignDataStructures.hours + ' out of ' + outClassHours + ') per week'
			},
			animationEnabled: true,
			toolTip: {
				shared: true,
				// content: '{name}: {y} Hours - <strong>#percent%</strong>' + '(#percent)'
				content: '{name}: {y} Hours. {extra}'
			},
			data: [
				{
					type: 'stackedBar100',
					showInLegend: true,
					name: '' + assignAlgo.name,
					toolTipContent: '{name}: {y} Hours. ' + assignAlgo.location,
					dataPoints: [
						{
							y: assignAlgo.hours,
							label: 'iMods Week Visualizer'
						}
					]
				},
				{
					type: 'stackedBar100',
					showInLegend: true,
					name: '' + assignIntroToAlgo.name,
					toolTipContent: '{name}: {y} Hours. ' + assignIntroToAlgo.location,
					dataPoints: [
						{
							y: assignIntroToAlgo.hours,
							label: 'iMods Week Visualizer'
						}
					]
				},
				{
					type: 'stackedBar100',
					showInLegend: true,
					name: '' + assignDataStructures.name,
					toolTipContent: '{name}: {y} Hours. ' + assignDataStructures.location,
					dataPoints: [
						{
							y: assignDataStructures.hours,
							label: 'iMods Week Visualizer'
						}
					]
				}
			]
		});
	chart.render();
};


$(document).ready(function () {
	'use strict';

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

	for (inc = 2010; inc <= 2035; inc++) {
		years.push(inc);
	}

	// Populate the choice box with choices from the years [2010 to 2035] array
	choiceYear = document.getElementById('chooseYear');
	for (inc = 0; inc < years.length; inc++) {
		// console.log(inc);
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
});

// function addTask () {
// 	'use strict';
// 	var sYear = $('#chooseYear option:selected').text();
// 	var sMonth = $('#chooseMonth option:selected').index();
// 	var sDay = $('#chooseDay option:selected').text();
// 	var sHour = $('#chooseHour option:selected').text();

// 	var eYear = $('#chooseYear2 option:selected').text();
// 	var eMonth = $('#chooseMonth2 option:selected').index();
// 	var eDay = $('#chooseDay2 option:selected').text();
// 	var eHour = $('#chooseHour2 option:selected').text();
// 	// console.log(sYear + sMonth + sDay + sHour);

// 	var startDate = new Date(sYear, sMonth, sDay, sHour, 0, 0, 0);
// 	var endDate = new Date(eYear, eMonth, eDay, eHour, 0, 0, 0);
// 	// console.log(d1);

// 	addEvent(startDate, endDate, 'testTask');
// }

// function addTask2 () {
// 	'use strict';
// 	var sYear = $('#chooseYear option:selected').text();
// 	var sMonth = $('#chooseMonth option:selected').index();
// 	var sDay = $('#chooseDay option:selected').text();
// 	var sHour = $('#chooseHour option:selected').text();

// 	var eYear = $('#chooseYear2 option:selected').text();
// 	var eMonth = $('#chooseMonth2 option:selected').index();
// 	var eDay = $('#chooseDay2 option:selected').text();
// 	var eHour = $('#chooseHour2 option:selected').text();
// 	// console.log(sYear + sMonth + sDay + sHour);

// 	var startDate = new Date(sYear, sMonth, sDay, sHour, 0, 0, 0);
// 	var endDate = new Date(eYear, eMonth, eDay, eHour, 0, 0, 0);
// 	// console.log(d1);

// 	addEvent(startDate, endDate, 'testTask');
// }

$(document).ready(function () {
	'use strict';
	$('#scheduleCalendar').fullCalendar({
		header: {
			left: 'prev title next',
			center: '',
			right: ''
		},
		eventClick: function (event) {
			// all arguments: (event, jsEvent, view)

			// set the values and open the modal
			$('#taskInfo').html(event.description);
			$('#taskLink').attr('href', event.url);
			$('#taskTime').text(event.end);
			$('#taskContent').dialog({
				modal: true,
				title: event.title
			});
			return false;
		},
		events:
		[
			{
				title: 'JQuery Quiz',
				allday: 'false',
				description: '<p>Sample description for a JQuery Quiz Assignment</p>',
				start: window.moment().subtract(2, 'days'),
				end: window.moment().subtract(1, 'days'),
				url: 'http://www.w3schools.com/jquery/'
			},
			{
				title: 'Python Worksheet',
				allday: 'false',
				description: '<p>Sample description for a Python WS Assignment</p>',
				start: window.moment().add(7, 'days'),
				end: window.moment().add(7, 'days'),
				url: 'https://en.wikipedia.org/wiki/Python_%28programming_language%29'
			}
		]
	});

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

		addEvent(startDate, endDate, 'testTask');
	});
});

function addEvent (startDate, endDate, title) {
	'use strict';
	$('#scheduleCalendar')
		.fullCalendar('renderEvent', {
			id: startDate,
			title: title,
			start: startDate,
			end: endDate,
			allDay: false
		}, true);
}


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
	$('#add-new-technique').find('input, select, textarea').val('');
	$('#dimImageModal')
		.prop('src', '../../images/content/knowDimNone.png')
		.prop('title', '');

	$('#techniqueId').val('');
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');
	$('#add-new-technique').css('display', 'block');
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
function showAssessmentTechnique () {
	'use strict';
	$('#ideal-matches .text-block.title')
		.click(function () {
			openNewAssessmentTechniqueModal();
			displayAssessmentInformationInEdit(false, $(this));
			return false;
		});

	$('#ideal-matches1 .text-block.title')
		.click(function () {
			openNewAssessmentTechniqueModal();
			displayAssessmentInformationInEdit(false, $(this));
			return false;
		});

	$('#extended-matches .text-block.title')
		.click(function () {
			openNewAssessmentTechniqueModal();
			displayAssessmentInformationInEdit(false, $(this));
			return false;
		});
}

function checkForAssign (data) {
	'use strict';
	var currentTechnique;
	var index;

	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if (currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		} else if (currentTechnique.assigncheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		} else if (currentTechnique.assigncheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		}
	}

	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		} else if (currentTechnique.assigncheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		} else if (currentTechnique.assigncheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		}
	}
}

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

/**
 * Callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques (data) {
	'use strict';
	var text = '';
	var index;
	var currentTechnique;
	var favoriteImgToggle = '';
	var assignImgToggle = '';
	var isAdmin = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOAssessmentTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

		if (currentTechnique.isAdmin) {
			isAdmin = 'isAdmin';
		} else {
			isAdmin = '';
		}

		text += '<input type="radio" id="' + currentTechnique.id + '" name="assessmentTechnique" value="' + currentTechnique.id + '">';
		text += '<label class="assessment-block ' + isAdmin + '" for="' + currentTechnique.id + '"><div class="favorite topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div class="assign" id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><br><br><span></span></div><button class="clone"><i class="fa fa-clone blue"></i> Clone</button></label>';
	}

	$('#ideal-matches1').html(text);
	$('#ideal-matches1').buttonset();
	$('#ideal-matches1').height(300);

	text = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];

		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOAssessmentTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

		if (currentTechnique.isAdmin) {
			isAdmin = 'isAdmin';
		} else {
			isAdmin = '';
		}


		text += '<input type="radio" id="' + currentTechnique.id + '" name="assessmentTechnique" value="' + currentTechnique.id + '">';
		text += '<label class="assessment-block ' + isAdmin + '" for="' + currentTechnique.id + '"><div class="favorite topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div class="assign" id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><br><br><button class="clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
	}

	$('#extended-matches').html(text);
	$('#extended-matches').buttonset();

	$(document).on('click', '.topLeft img', function () {
		var str = '';
		var indexNo = '';
		var res = '';
		var currentImg = $(this);
		var assessmentBlock = null;
		var unfavId = null;

		if (isTopLeftClicked) {
			isTopLeftClicked = 0;
			return false;
		}


		if ($(this).attr('src') === '../../images/fav.png') {
			$(this).attr('src', '../../images/unfav.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}
			$.ajax({
				url: '../../assessmentTechnique/unassignFavorite/' + res,
				method: 'GET',
				success: function () {
					isTopLeftClicked = 1;
					if (currentImg.parents('.favorites-inner').length) {
						assessmentBlock = currentImg.parents('.assessment-block');
						unfavId = assessmentBlock.prop('for');
						assessmentBlock.remove();

						// remove fav icon from the technique in ideal
						// and extended matches
						$('label[for=' + unfavId + ']')
							.find('.topLeft img')
							.prop('src', '../../images/unfav.png');

						// if no techniques exist
						// show empty fav techniques message
						if (!$('.favorites-inner').find('.assessment-block').length) {
							$('.favorites-inner').append('<br><strong>You do not have any favorite techniques</strong><br><br>');
						}
					}
				}

			});
		} else {
			$(this).attr('src', '../../images/fav.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}
			$.ajax({
				url: '../../assessmentTechnique/assignFavorite/' + res,
				method: 'GET',
				success: function () {
					isTopLeftClicked = 1;
				}
			});
		}

		return false;
	});

	$(document).on('click', '#topRight img', function () {
		var str = '';
		var indexNo = '';
		var res = '';
		var inputData = null;

		if (isTopRightClicked) {
			isTopRightClicked = 0;
			return false;
		}

		if ($(this).attr('src') === '../../images/assign.png') {
			$(this).attr('src', '../../images/unassign.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}

			inputData = {
				learningObjectiveID: $('#learningObjectiveID').val(),
				assessmentTechniqueID: res
			};
			$.ajax({
				url: '../../assessmentTechnique/unassignToLearningObjective',
				type: 'POST',
				data: JSON.stringify(inputData),
				contentType: 'application/json',
				success: function () {
					isTopRightClicked = 1;
				}
			});
		} else {
			$(this).attr('src', '../../images/assign.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}

			inputData = {
				learningObjectiveID: $('#learningObjectiveID').val(),
				assessmentTechniqueID: res
			};
			$.ajax({
				url: '../../assessmentTechnique/assignToLearningObjective',
				type: 'POST',
				data: JSON.stringify(inputData),
				contentType: 'application/json',
				success: function () {
					isTopRightClicked = 1;
				}
			});
		}

		return false;
	});
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
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');

	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];

	var index;
	var data;

	// Get the id of the grails domain from the value attribute in the html
	for (index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// Bundle the data into an object
	data = {
		selectedKnowledgeDimensions: selectedKnowledgeDimensionsData,
		selectedLearningDomains: selectedLearningDomainsData,
		selectedDomainCategories: selectedDomainCategoriesData,
		learningObjectiveID: $('#learningObjectiveID').val()

	};

	// Send the data to the find matching techniques action in grails
	// and process the response with the display assessment techniques callback
	$.ajax({
		url: '../findMatchingTechniques',
		method: 'post',
		data: JSON.stringify(data),
		contentType: 'application/json'
	})
	.done(function (data) {
		displayAssessmentTechniques(data);
		showAssessmentTechnique(data);
		checkForAssign(data);
		assessmentEqualHeights('#ideal-matches1');
		assessmentEqualHeights('#extended-matches');
	});
}

function truncateString (string, count) {
	'use strict';
	if (string.length > count) {
		return string.substring(0, count) + '...';
	}

	return string;
}

// Load techniques on page load
filterAssessmentTechniques();

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
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

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
		var checkBoxName;
		var cloneDetect = '';

		// Load techniques on page load
		filterAssessmentTechniques();
		// The filters for the assessment technique are wrapped in a accordian
		// beforeActivate is to be able to open both ideal & extended matches simultaneously
		$('#filter-assessment-techniques').accordion({collapsible: true, heightStyle: 'content'});
		$('#assessment-plan-accordion').accordion({collapsible: true, heightStyle: 'content', active: false});
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
		$('#ideal-matches-toggle').accordion({collapsible: true,
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
		$('input[name=knowledgeDimension]').on('change',
			function () {
				checkBoxName = 'knowledgeDimension';
				updateTextArea(checkBoxName);
				filterAssessmentTechniques();
				$('#selectAllkD').prop('checked', false);
			});

		$('input[name=learningDomain]').on('change',
			function () {
				checkBoxName = 'learningDomain';
				updateTextArea(checkBoxName);
				filterAssessmentTechniques();
				$('#selectAlllD').prop('checked', false);
			});

		$('input[name=domainCategory]').on('change',
			function () {
				checkBoxName = 'domainCategory';
				updateTextArea(checkBoxName);
				filterAssessmentTechniques();
				$('#selectAlldC').prop('checked', false);
			});

		$('#saveButton').on('click', function () {
			cloneDetect = document.getElementById('cloneDetect').value;

			if ($('#title').val() === '') {
				$('#errorMessage').text('Technique must have a title!');
				return false;
			}

			if ($('#knowledgeDimension').val() === '') {
				$('#errorMessage').text('Knowledge Dimensions are required!');
				return false;
			}

			if ($('#learningDomain').val() === '' || $('#learningDomain').val() === null) {
				$('#errorMessage').text('Learning Domains are required');
				return false;
			}

			if ($('#domainCategory').val() === '' || $('#domainCategory').val() === null) {
				$('#errorMessage').text('Domain Categories are required');
				return false;
			}
			if ($('#title').val() === $('#titlecheck').val() && cloneDetect === 'clone') {
				$('#errorMessage').text('Enter title which is different from the original technique');
				return false;
			}

			return true;
		});

		// When add new technique button is clicked open modal
		$('#add-new-technique-button').on('click', function () {
			openNewAssessmentTechniqueModal();
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
