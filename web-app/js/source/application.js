$(document).ready(
	function () {
		'use strict';
		$('#accordion').accordion({
			heightStyle: 'content'
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

		$('input[type="checkbox"], input[type="radio"], .knowledge-dim-label').hover(
			function () {
				$('#qtip-place').html($(this).attr('title'));
			},
			function () {
				$('#qtip-place').html('');
			}
		);

		// Make modals draggable
		$('.draggable')
			.not(document.getElementById('topicList'))
			.not(document.getElementById('resourceList'))
			.drags();

		// Hide modal when background is clicked
		$(document).on('click', '.modalBackground', function () {
			$('.draggable').hide();
			$('.modalBackground').hide();
		});
	}
);

function updateDatePicker (dateFormat) {
	'use strict';
	$('input[value="date.struct"]:hidden').each(
		function () {
			var name = $(this).attr('name');
			var selectDay;
			var selectMonth;
			var selectYear;
			var dateDay;
			var dateMonth;
			var dateYear;
			var val;
			var date;
			var template;
			var displayWidget;

			// Create JQuery friendly ID
			var id = name.replace('.', '_').replace('[', '_').replace(']', '_') + '_input';

			if ($('#' + id).length === 0) {
				// Find the select elements
				selectDay = $(this).nextAll('select:eq(0)').hide();
				selectMonth = $(this).nextAll('select:eq(1)').hide();
				selectYear = $(this).nextAll('select:eq(2)').hide();

				// Get the values
				dateDay = $(selectDay).val();
				dateMonth = $(selectMonth).val();
				dateYear = $(selectYear).val();

				// Calculate the current input value
				val = '';
				// If there is a date in the Selects then use it otherwise it's empty
				if (dateDay !== '' && dateYear !== '' && dateMonth !== '') {
					date = new Date(dateYear, dateMonth - 1, dateDay);

					val = $.datepicker.formatDate(dateFormat, date);
				}

				// Create element
				template = '<input type="text" name="' + id + '" id="' + id + '" value="' + val + '"/>';

				if ($(this).parent('.datePickerCalenderView').size()) {
					template = '<div id="' + id + '" />';
				}

				$(this).before(template);
				displayWidget = $('#' + id);

				displayWidget.blur(
					function () {
						date = $.datepicker.parseDate(dateFormat, $(this).val());

						if (date === null) {
							$(selectDay).val('');
							$(selectMonth).val('');
							$(selectYear).val('');
						} else {
							$(selectDay).val(date.getDate());
							$(selectMonth).val(date.getMonth() + 1);
							$(selectYear).val(date.getFullYear());
						}
					}
				).keydown(
					function (event) {
						// Show popup on Down Arrow
						if (event.keyCode === 40) {
							displayWidget.datepicker('show');
						}
					}
				);

				displayWidget.datepicker({
					SchangeMonth: true,
					changeYear: true,
					dateFormat: dateFormat,
					constrainInput: true,
					showButtonPanel: true,
					showWeeks: true,
					showOn: 'button',
					onSelect: function (dateText, inst) {
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
		}
	);
}

function toggleHelp () {
	'use strict';
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
