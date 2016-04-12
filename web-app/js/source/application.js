var formSubmitted = false;
var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var profileVal = 0;
var profileBuffer = 100;


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

			progressLabel.text('Profile Completion .......' + progressbar.progressbar('value') + '%');

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
			progressLabel.text('Profile Complete !!');
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
	$('#help-placeholder-2').css('display', 'none');
	$('.show-hover-new').qtip({
		style: {
			background: 'transparent',
			border: 'none',
			color: 'black'
		},
		show: 'mouseover',
		hide: 'mouseout',
		position: {
			type: 'static',
			container: $('div#qtip-place')
		}
	});

	$('*').focus(
		function () {
			$('#qtip-place').html(document.activeElement.title);
		}
	);

	$('input[type="submit"]').hover(
		function () {
			$('#qtip-place').html($(this).attr('oldtitle'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);
	$('.topicButton').hover(
		function () {
			$('#qtip-place').html($(this).attr('oldtitle'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);
	$('.knowledgeDimensionButton').hover(
		function () {
			$('#qtip-place').html($(this).attr('oldtitle'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);

	$('input[type="checkbox"], input[type="radio"], .knowledgeDimensionButton,.ResourceButton,.priority').hover(
		function () {
			$('#qtip-place').html($(this).attr('title'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);

	$('.topicModalButton').hover(
		function () {
			$('#help-placeholder').css('height', '15em');
			$('#qtip-place').html($(this).attr('title'));
		},
		function () {
			$('#help-placeholder').css('height', '10em');
			$('#qtip-place').html('');
		}
	);
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

	if (count === 0) {
		contentPercent = 0;
	} else if (count < 5) {
		contentPercent = 80;
	} else {
		contentPercent = 100;
	}
	return contentPercent;
}

function calculateAsst (assessmentTech) {
	'use strict';
	var count = assessmentTech.length;
	var asstPercent = 0;

	if (count > 0) {
		asstPercent = 100;
	}
	return asstPercent;
}

function calculatePed (pedagogyTech) {
	'use strict';
	var count = pedagogyTech.length;
	var pedPercent = 0;

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
		coPercent = 2;
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
		asstPercent = calculateAsst(user.assessmentTechnique);
		pedPercent = calculatePed(user.pedagogyTechnique);
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
