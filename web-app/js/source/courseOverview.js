'use strict';

var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];


// source: http://stackoverflow.com/a/2855946
function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	return pattern.test(emailAddress);
}

function isRequired(fieldValue) {
	if (fieldValue !== '' && fieldValue !== null && fieldValue !== undefined) {
		return true;
	} else {
		return false;
	}
}

// custom validation function for instructors
function instructorValidator() {
	var errorList = [];

	$('.instructor-form').find('.error').remove();

	$('.instructor-form').find('.first_name, .last_name, .email, .role').each(function() {

		if (!isRequired($(this).val())) {
			errorList.push({
				'element': $(this),
				message: 'This field is required'
			});
		} else if ($(this).hasClass('email')) {

			if (!isValidEmailAddress($(this).val())) {
				errorList.push({
					'element': $(this),
					message: 'Requires a valid email address'
				});
			}

		}

	});

	if (errorList.length > 0) {

		for (var i = 0; i < errorList.length; i++) {
			var errorMsg = '<label class="error">';
			errorMsg += errorList[i].message;
			errorMsg += "</label>";

			errorList[i].element.after(errorMsg);
		}

		return false;

	} else {
		return true;
	}

}

// compares startTime and EndTime
function compareStartEndTimes() {
	// check if end time is greater than start time
	var startHour = $('#schedule-start-time_hour').val();
	var startMinute = $('#schedule-start-time_minute').val();

	var endHour = $('#schedule-end-time_hour').val();
	var endMinute = $('#schedule-end-time_minute').val();

	var currentDate = new Date();

	var year = currentDate.getYear();
	var month = currentDate.getMonth();
	var day = currentDate.getDate();

	var startTime = new Date(year, month, day, startHour, startMinute);
	var endTime = new Date(year, month, day, endHour, endMinute);

	$('#time-error').remove();

	if (endTime <= startTime) {
		$('#schedule-end-time_hour').parent().append('<label id="time-error" class="error">End time has to be greater than start time</label>');
		return false;
	}

	return true;
}

function gradingRadio(radio) {
	$('#grading-procedure-text').hide();
	if (radio.val() === 'Custom') {
		$('#grading-procedure-text').show();
	}
	radio.parents('.ui-accordion-content').css('height', 'auto');
}

function populateRepeatsEvery() {
	if ($('#repeats option:selected').text() === 'Daily') {
		$('#duration').text('days');
		$('#duration, label[for="repeatsEvery"], #repeatsEvery').css('visibility', 'visible');
		$('label[for="scheduleWeekDays"], label[for="weekdays"], :checkbox').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
	} else if ($('#repeats option:selected').text() === 'Weekly') {
		$('#duration').text('weeks');
		$('#duration, label[for="repeatsEvery"], #repeatsEvery, label[for="scheduleWeekDays"], label[for="weekdays"], :checkbox, #repeats-every').css('visibility', 'visible');
	} else {
		$(':checkbox, label[for="weekdays"], label[for="scheduleWeekDays"], #duration, label[for="repeatsEvery"], #repeatsEvery').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
	}
}

// TODO is the method unused?
function toggleSelected(event) {
	if (!(event.target.nodeName in ['OPTION', 'INPUT', 'BUTTON', 'SELECT'])) {
		$(this).find('.saveIcon > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
		$(this).toggleClass('selected');
	}
}


$(document).ready(function() {
	populateRepeatsEvery();
	$('#repeats').on(
		'change',
		populateRepeatsEvery
	);

	// delete instructor logic
	$('.delete-instructor').click(function() {

		var ids = [];
		$('.instructor-list').find('.topicListRow.selected').each(function() {
			ids.push($(this).data('id'));
		});

		if (ids.length < 1) {
			alert('Please select an instructor to delete');
			return false;
		}

		if (confirm('Are you sure you want to delete the instructor(s)?') === false) {
			return false;
		}

		$.ajax({
			url: baseUrl + 'courseOverview/delete',
			type: 'POST',
			dataType: 'json',
			data: {
				imod_id: $('#imodID').val(),
				selected: ids
			},
			success: function() {
				location.reload();
			},
			error: function(xhr) {
				// when something goes wrong log to the browser console
				console.log(xhr.responseText);
			}
		});

		return false;

	});

	$('#topicList').on('click', '.saveIcon', function() {
		$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
		$(this).parent().toggleClass('selected');
	});

	$('.saveIcon-parent').click(function() {
		$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');

		if ($(this).find("i").hasClass('fa-square-o')) {

			$(this).parents('table')
				.find('tbody')
				.find('tr')
				.removeClass('selected')
				.find('.saveIcon')
				.find('> i')
				.removeClass('fa-check-square')
				.addClass('fa-square-o');

		} else {

			$(this).parents('table')
				.find('tbody')
				.find('tr')
				.addClass('selected')
				.find('.saveIcon')
				.find('> i')
				.removeClass('fa-square-o')
				.addClass('fa-check-square');

		}

		return false;
	});


	$('.topicButtonGradient .add').click(function() {
		var row = '';
		row += '<tr class="topicListRow">';
		row += '<td class="saveIcon">';
		row += '	<i class="fa fa-square-o"></i>';
		row += '</td>';
		row += '    <td><input type="text" name="firstName[]" value="" id="firstName" class="first_name" /></td>';
		row += '    <td><input type="text" name="lastName[]" value="" id="lastName" class="last_name" /></td>';
		row += '    <td><input type="text" name="email[]" value="" id="email"  class="email" /></td>';
		row += '    <td><input type="text" name="officeHours[]" value="" id="officeHours" class="office_hours" /></td>';
		row += '    <td><input type="text" name="webPage[]" value="" id="webPage" class="web_page" /></td>';
		row += '<td>';
		row += '    <select name="role[]" id="role" class="role">';
		row += '        <option value="">Select Role</option>';
		row += '        <option>Assistant Professor</option>';
		row += '        <option>Associate Professor</option>';
		row += '        <option>Professor</option>';
		row += '        <option>Teaching Assistant</option>';
		row += '        <option>Course Assistant</option>';
		row += '        <option>Grader</option>';
		row += '    </select>';
		row += '</td>';
		row += '    <td><input type="text" name="location[]" value="" id="location" class="location" /></td>';
		row += '</tr>';

		$('#topicList tbody').append(row);
		return false;
	});

	$('#cancelTopic').click(function() {
		$('#topicDialogBackground, #topicDialog').hide();
		return false;
	});

	$('.overview-save').click(function() {

		var isValid = compareStartEndTimes();

		if (isValid) {

			isValid = instructorValidator();

			if (isValid) {

				var parameterList = [];

				$('.topicListRow').each(function() {
					var row = $(this);

					parameterList.push({
						id: row.data('id'),
						lastName: row.find('input[name="lastName[]"]').val(),
						firstName: row.find('input[name="firstName[]"]').val(),
						email: row.find('input[name="email[]"]').val(),
						officeHours: row.find('input[name="officeHours[]"]').val(),
						webPage: row.find('input[name="webPage[]"]').val(),
						role: row.find('select[name="role[]"]').val(),
						location: row.find('input[name="location[]"]').val()
					});


				});


				$.ajax({
					url: baseUrl + 'courseOverview/create',
					type: 'POST',
					dataType: 'json',
					data: {
						imod_id: $('input[name=id]').val(),
						parameters: JSON.stringify(parameterList)
					},
					success: function() {
						$('.courseoverview').submit();
					},
					error: function(xhr) {
						// when something goes wrong log to the browser console
						console.log(xhr.responseText);
					}
				});

			}

		}

		return false;

	});

	// regex method for url
	$.validator.addMethod('urlRule', function(value, element, regexpr) {
		return regexpr.test(value);
	}, 'Please enter a valid URL.');

	$('#time-ratio').mask('9:9');

	$('.timeFields').find('select').change(function() {
		var isValid = compareStartEndTimes();
		if (!isValid) {
			return false;
		}
	});

	// course overview validation
	$('.courseoverview').validate({
		rules: {
			imodNumber: 'required',
			name: {
				required: true
			},
			url: {
				required: true,
				urlRule: /^[a-z0-9./?:@\-_=#]+\.([a-z0-9./?:@\-_=#])*$/i
			},
			subjectArea: {
				required: true
			},
			creditHours: {
				min: 0,
				max: 7,
				digits: true
			},
			numberOfSeats: {
				min: 0,
				max: 1000,
				digits: true
			}
		},
		messages: {
			url: {
				url: 'Please enter a valid URL eg. http://google.com'
			},
			creditHours: {
				min: 'Credit hours cannot be negative'
			},
			numberOfSeats: {
				min: 'Number of seats cannot be negative'
			}
		}
	});

	gradingRadio($('.grading-radio:checked'));

	// grading procedure radio buttons
	$('.grading-radio').change(function() {
		gradingRadio($(this));
	});

	$('.courseoverview').submit(function() {
		var radio = $('.grading-radio:checked');

		if (radio.val() !== 'Custom') {
			$('#grading-procedure-text').val(radio.val());
		}
	});

});
