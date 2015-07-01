function updateDatePicker () {
	'use strict';
	$('input[value="date.struct"]:hidden').each(
		function () {
			var dateFormat = 'dd/mm/yy';
			var name = $(this).attr('name');
			// Create JQuery Friendly ID
			var id = name.replace('.', '_').replace('[', '_').replace(']', '_') + '_input';

			var selectDay;
			var selectMonth;
			var selectYear;
			var dateDay;
			var dateMonth;
			var dateYear;
			var val;
			var template;
			var displayWidget;
			var date;

			if ($('#' + id).length === 0) {
				// Find the Select Elements
				selectDay = $(this).nextAll('select:eq(0)').hide();
				selectMonth = $(this).nextAll('select:eq(1)').hide();
				selectYear = $(this).nextAll('select:eq(2)').hide();

				// Get the Values
				dateDay = $(selectDay).val();
				dateMonth = $(selectMonth).val();
				dateYear = $(selectYear).val();

				// Calculate the Current Input Value
				val = '';
				// If there is a date in the Selects then use it otherwise it's empty
				if (dateDay !== '' && dateYear !== '' && dateMonth !== '') {
					date = new Date(dateYear, dateMonth - 1, dateDay);
					val = $.datepicker.formatDate(dateFormat, date);
				}

				// Create element
				template = '<input type="text" name="' + id + '" id="' + id + '" value="' + val + '"/>';

				if ($(this).parent('.datePickerCalenderView').size()) {
					template = '<div id="' + id + '"/>';
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
					changeMonth: true,
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

$(document).ready(
	function () {
		'use strict';
		$('.show-hover-new').qtip({
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtip-place')
			}
		});

		document.getElementById('repeats').removeAttribute('multiple');
		document.getElementById('repeatsEvery').removeAttribute('multiple');
		$('#tabs').tabs({
			ajaxOptions: {
				error: function (xhr, status, index, anchor) {
					$(anchor.hash).html('errorLoadingTabMessage');
				}
			}
		});
		$('#accordion').accordion();
		$('#clickthis').click(
			function () {
				$('#dialog').dialog();
			}
		);
		updateDatePicker();
		$('#spinner').ajaxComplete(
			function () {
				updateDatePicker();
			}
		);
	}
);
