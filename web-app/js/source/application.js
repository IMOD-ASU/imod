'use strict';

$(document).ready(function() {
	$('#accordion').accordion();
	// $('#help-placeholder').draggable();
	// $('#open-help').draggable();
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
	$('*').focus(function() {
		//alert(document.activeElement);
		$('#qtip-place').html(document.activeElement.title);
	});

	$('input[type="submit"]').hover(function() {
		$('#qtip-place').html($(this).attr('oldtitle'));
	}, function() {
		$("#qtip-place").html("");
	});

	$('input[type="checkbox"], input[type="radio"]').hover(function() {
		$('#qtip-place').html($(this).attr('title'));
	}, function() {
		$("#qtip-place").html("");
	});

	$('.learning-objective.list-item').each(function() {
		if ($(this).find('.list-link').text().trim() == 'Empty Learning Objective' ||
			$(this).find('.list-link').text().trim() == '' ||
			$(this).find('.list-link').text().trim() == null
			) {
			$(this).hide();
		}
	});
	$('.learning-objective.list-wrapper').show();
});

function showHelp(displayHelp) {
	if (displayHelp !== 'true') {
		$('#help-placeholder').css('display', 'none');
	}
}

function updateDatePicker(dateFormat) {
	$("input[value='date.struct']:hidden").each(function() {
		var name = $(this).attr('name');
		var id = name.replace('.', '_').replace('[', '_').replace(']', '_') + '_input'; // Create JQuery Friendly ID

		if ($('#' + id).length === 0) {

			// Find the Select Elements
			var selectDay = $(this).nextAll('select:eq(0)').hide();
			var selectMonth = $(this).nextAll('select:eq(1)').hide();
			var selectYear = $(this).nextAll('select:eq(2)').hide();

			// Get the Values
			var dateDay = $(selectDay).val();
			var dateMonth = $(selectMonth).val();
			var dateYear = $(selectYear).val();

			// Calculate the Current Input Value
			var val = '';
			if (dateDay !== '' && dateYear !== '' && dateMonth !== '') { // If there is a date in the Selects then use it otherwise it's empty
				var date = new Date(dateYear, dateMonth - 1, dateDay);
				val = $.datepicker.formatDate(dateFormat, date);
			}

			// Create element
			var template = "<input type='text' name='" + id + "' id='" + id + "' value='" + val + "'/>";

			if ($(this).parent('.datePickerCalenderView').size()) {
				template = '<div id="' + id + '" />';
			}


			$(this).before(template);
			var displayWidget = $('#' + id);

			displayWidget.blur(function() {
				var date = $.datepicker.parseDate(dateFormat, $(this).val());

				if (date === null) {
					$(selectDay).val('');
					$(selectMonth).val('');
					$(selectYear).val('');
				} else {
					$(selectDay).val(date.getDate());
					$(selectMonth).val(date.getMonth() + 1);
					$(selectYear).val(date.getFullYear());
				}
			}).keydown(function(event) {
				// Show popup on Down Arrow
				if (event.keyCode === 40) {
					displayWidget.datepicker('show');
				}
			});

			displayWidget.datepicker({
				changeMonth: true,
				changeYear: true,
				dateFormat: dateFormat,
				constrainInput: true,
				showButtonPanel: true,
				showWeeks: true,
				showOn: 'button',
				onSelect: function(dateText, inst) {
					if (inst === null) {
						$(selectDay).val('');
						$(selectMonth).val('');
						$(selectYear).val('');
					} else {
						$(selectDay).val(inst.selectedDay);
						$(selectMonth).val(inst.selectedMonth + 1);
						$(selectYear).val(inst.selectedYear);
					}
				}
			});
		}
	});
}


function toggleHelp() {
	var helpbox = $('#help-placeholder');
	var helpbox2 = $('#help-placeholder-2');
	var tabContainer = $('#tabs-container');
	if (helpbox.css('display') === 'block') {
		helpbox.css('display', 'none');
		helpbox2.css('display', 'block');
		tabContainer.css('margin-right', '5.5%');
	} else {
		helpbox.css('display', 'block');
		helpbox2.css('display', 'none');
		tabContainer.css('margin-right', '17.5%');
	}
}
