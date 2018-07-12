var formSubmitted = false;
var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var profileVal = 0;
var profileBuffer = 100;
var info = '';

$(document).ready(function () {
	'use strict';

	var progressbar = $('#progressbar');
	var progressLabel = $('.progress-label');

	// progressbarValue = progressbar.find(".ui-progressbar-value");

	progressbar.progressbar({
		value: false,
		change: function () {
			// var selector = '#' + this.id + ' > div';
			var value = this.getAttribute('aria-valuenow');

			progressLabel.text('Course Design Completion .......' + progressbar.progressbar('value') + '%');
			document.getElementById('parent').innerHTML = info;

			if (value < 15) {
				$('.ui-widget-header').css({background: '#B71C1C'});
			} else if (value < 25) {
				$('.ui-widget-header').css({background: '#FFB300'});
			} else if (value < 35) {
				$('.ui-widget-header').css({background: '#3F51B5'});
			} else if (value < 70) {
				$('.ui-widget-header').css({background: '#CDDC39'});
			} else {
				$('.ui-widget-header').css({background: '#8BC34A'});
			}
		},
		complete: function () {
			progressLabel.text('Course Design Complete !!');
			progressLabel.css({left: '35%'});
			$('.ui-widget-header').css({background: '#4CAF50'});
		}
	});

	// function progress() {
	// 	var val = progressbar.progressbar('value') || 0;
	// }

	// setTimeout( progress, 2000 );

	$('.tooltipster').tooltipster({
		theme: 'tooltipster-noir',
		position: 'bottom'
	});

	$('.tooltipsterForm').tooltipster({
		theme: 'tooltipster-noir',
		position: 'right'
	});

	$('#accordion').accordion({
		heightStyle: 'content',
		collapsible: true
	});

	// Make modals draggable
	$('.draggable').draggabilly({
		handle: '.draggable-handle'
	});

	$('#help-placeholder > #open-button, #help-placeholder-2 > #open-button').click(function () {
		var helpbox = $('#help-placeholder');
		var helpboxTwo = $('#help-placeholder-2');
		var tabContainer = $('#tabs-container');

		if (helpbox.css('display') === 'block') {
			helpbox.css('display', 'none');
			helpboxTwo.css('display', 'block');
			tabContainer.css('margin-right', '5.5%');
		} else {
			helpbox.css('display', 'block');
			helpboxTwo.css('display', 'none');
			tabContainer.css('margin-right', '17.5%');
		}
	});

	$('#save-before-leaving').dialog({
		autoOpen: false
	});

	$('.ui-tabs-nav.ui-widget-header.ui-corner-all > li > a').click(function (event) {
		if ($('meta[name="imod-is-saved"]').attr('value') === false) {
			event.preventDefault();
			$('#save-before-leaving').dialog('open');
		}
	});

	// Hide modal when background is clicked
	$(document).on('click', '.modalBackground', function () {
		var zIndex = parseInt($(this).css('z-index'), 10);
		var elem;

		$('.draggable').each(function () {
			elem = $(this);

			if (elem.prop('id') === 'add-new-technique') {
				$('.add-new-technique-form')
					.validate()
					.resetForm();
				$('#errorMessage').text('');
			}

			if (zIndex <= parseInt(elem.css('z-index'), 10)) {
				elem.hide();
			}
		});

		$(this).hide();
	});

	$(document).on('click', '.modalBackground2', function () {
		$('.modelBackground2Target').hide();
		$('.modalBackground2').hide();
	});

	// checks if a form has been submitted
	$('form').submit(function () {
		formSubmitted = true;
	});
});

function calculateLO (learningObjectives) {
	'use strict';
	var count = learningObjectives.length;

	return count;
}

function calculateContent (contents) {
	'use strict';
	var count = contents.length;

	return count;
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
		if (getAssignedTechniqueCount(value).responseJSON.count >= 1) {
			count += 1;
		}
	});
	return count;
}

function calculateAssignedPedTechCount (loList) {
	'use strict';
	var count = 0;

	$.each(loList, function (index, value) {
		if (getAssignedPedTechniqueCount(value).responseJSON.count >= 1) {
			count += 1;
		}
	});
	return count;
}

function calculatePercentage (response) {
	'use strict';
	var data = response;
	var currentImod = data.currentImod;
	// var user = data.user;
	var req = 0;
	var contentCount = 0;
	var coPercent = 0;
	var instrPercent = 0;
	var loCount = 0;
	var loPercent = 0;
	var contentPercent = 0;
	var asstCount = 0;
	var pedCount = 0;
	var asstPercent = 0;
	var pedPercent = 0;
	var minLo = 3;
	var minInstr = 1;
	var diffOne = 0;
	var diffTwo = 0;
	var minContent = 6;
	var contentMid = 2;
	var contentHigh = 5;
	var contentHighPercent = 80;
	var coPercentAssigned = 15;
	var totalPercent = 100;
	var assignedInstrPercent = 5;
	var checkId = localStorage.getItem('checkId');
	var initial = 'Please fill the course overview to see the minimum requirements to complete the course design.';
	var instStatus = 'Add instructor information. \n';
	var loStatus = 'At least three learning objectives needed - ';
	var contentStatus = ' content topics need to be added. \n';
	var asstStatus = 'At least one assessment technique needs to be added for each objective. \n';
	var pedStatus = 'At least one pedagogy technique needs to be added for each objective. \n';
	var compStatement = 'You have met the minimum requirements to design an IMOD';


	if (checkId === 'new') {
		coPercent = 0;
		info += initial;
	} else {
		coPercent = coPercentAssigned;
		if (currentImod.instructors.length >= minInstr) {
			instrPercent = assignedInstrPercent;
		}

		if (currentImod.learningObjectives.length >= minLo) {
			profileBuffer -= 20;
		} else {
			profileBuffer = currentImod.learningObjectives.length * 40;
		}
		loCount = calculateLO(currentImod.learningObjectives);
		if (loCount >= minLo) {
			loPercent = totalPercent;
		}
		contentCount = calculateContent(currentImod.contents);
		if (contentCount < contentMid) {
			contentPercent = 0;
		} else if (contentCount >= contentMid && contentCount <= contentHigh) {
			contentPercent = contentHighPercent;
		} else {
			contentPercent = totalPercent;
		}
		asstCount = calculateAssignedTechCount(currentImod.learningObjectives);
		if (asstCount >= loCount) {
			asstPercent = totalPercent;
		}
		else {
			diffOne = loCount - asstCount;
			asstPercent = 100 - (diffOne * 100 / loCount);
		}
		pedCount = calculateAssignedPedTechCount(currentImod.learningObjectives);
		if (pedCount >= loCount) {
			pedPercent = totalPercent;
		}
		else {
			diffTwo = loCount - pedCount;
			pedPercent = 100 - (diffTwo * 100 / loCount);
		}
		if (instrPercent < assignedInstrPercent) {
			info += instStatus;
		}
		if (loPercent < totalPercent) {
			info += loStatus + loCount + ' defined. \n';
		}
		if (contentCount < minContent) {
			req = minContent - contentCount;
			info += req + contentStatus;
		}
		if (asstCount < loCount) {
			info += asstStatus;
		}

		if (pedCount < loCount) {
			info += pedStatus;
		}
		if (loPercent === totalPercent && instrPercent === assignedInstrPercent && contentPercent === totalPercent && asstPercent === totalPercent && pedPercent === totalPercent) {
			info += compStatement;
		}
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

window.onload = function () {
	'use strict';
	evaluateProfile();
	// dont apply to pedagogy and assessment tabs
	if ($('.pedagogy-nav-bar').length) {
		return null;
	}

	if ($('#contentTable').length) {
		window.cleanForm = $('form, #contentTable').find('select, textarea, input').serialize();
	} else {
		window.cleanForm = $('form').find('select, textarea, input').serialize();
	}
	return true;
};

window.onbeforeunload = function () {
	'use strict';
	var dirtyForm;

	// dont apply to pedagogy and assessment tabs
	if ($('.pedagogy-nav-bar').length) {
		return null;
	}

	if ($('#contentTable').length) {
		dirtyForm = $('form, #contentTable').find('select, textarea, input').serialize();
	} else {
		dirtyForm = $('form').find('select, textarea, input').serialize();
	}

	if ((!formSubmitted && window.cleanForm !== dirtyForm) || window.unsavedResource) {
		return 'You have unsaved changes. Please save them before proceeding.';
	}
	return null;
};

