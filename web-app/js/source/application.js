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
			document.getElementById('hover-content').innerHTML = info;

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

	if (count < 2) {
		contentPercent = 0;
	} else if (count >= 2 && count <= 5) {
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

	if (count > 0) {
		asstPercent = 100;
	}
	return asstPercent;
}

function calculatePed (pedagogyTech, lolist) {
	'use strict';
	var pedPercent = 0;
	var count = calculateAssignedPedTechCount(lolist);

	if (count > 0) {
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
		info += 'More than two learning objectives, five content topics, at least one assessment and at least one pedagogy techniques should be added.';
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
		if (instrPercent < 5) {
			info += 'Add instructor information. \n';
		}
		if (loPercent < 100) {
			info += 'More than two learning objectives need to be defined. \n';
		}
		if (contentPercent < 100) {
			info += 'More then five differnt content topics needs to be added. \n';
		}
		if (asstPercent < 100) {
			info += 'At least one assessment technique needs to be added. \n';
		}
		if (pedPercent < 100) {
			info += 'At least one pedagogy technique needs to be added. \n';
		}
		if (loPercent === 100 && instrPercent === 5 && contentPercent === 100 && asstPercent === 100 && pedPercent === 100) {
			info += 'Everything\'s perfect! You have met the minimum requirements to design an IMOD';
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

