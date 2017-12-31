var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

localStorage.setItem('checkId', $('input[name="checkId"]').val());

// Source: http://stackoverflow.com/a/2855946
function isValidEmailAddress (emailAddress) {
	'use strict';
	var pattern = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

	return pattern.test(emailAddress);
}

function isValidUrl (url) {
	'use strict';
	var pattern = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;

	if (url === null || url === '') {
		return true;
	}
	return pattern.test(url);
}

function isRequired (fieldValue) {
	'use strict';
	return fieldValue && fieldValue !== '';
}

function scrollToError () {
	'use strict';
	if ($('.errorcontain').length) {
		$('html, body').animate({
			scrollTop: $('.errorcontain:visible:first').offset().top - 40
		}, 1000);
	} else {
		$('html, body').animate({
			scrollTop: $('label.error:visible:first').offset().top - 40
		}, 1000);
	}
}

// Custom validation function for instructors
function instructorValidator () {
	'use strict';
	var errorList = [];
	var index;
	var errorMsg;

	$('.instructor-form').find('.error').remove();

	$('.instructor-form').find('.first_name, .last_name, .email, .web_page,.role').each(
		function () {
			if (!isRequired($(this).val()) && !($(this).hasClass('web_page'))) {
				errorList.push({
					element: $(this),
					message: 'This field is required'
				});
			} else if ($(this).hasClass('email')) {
				if (!isValidEmailAddress($(this).val())) {
					errorList.push({
						element: $(this),
						message: 'Requires a valid email address'
					});
				}
			} else if ($(this).hasClass('web_page')) {
				if (!isValidUrl($(this).val())) {
					errorList.push({
						element: $(this),
						message: 'Requires a valid webpage'
					});
				}
			}
		});

	if (errorList.length > 0) {
		for (index = 0; index < errorList.length; index++) {
			errorMsg = '<label class="error">';
			errorMsg += errorList[index].message;
			errorMsg += '</label>';

			errorList[index].element.after(errorMsg);
		}
		return false;
	}

	return true;
}

// Compares startTime and EndTime
function compareStartEndTimes () {
	'use strict';
	// Check if end time is greater than start time
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
	var startYear = parseInt($('#schedule-start-date_year').val(), 10);
	var endYear = parseInt($('#schedule-end-date_year').val(), 10);
	var startMonth = parseInt($('#schedule-start-date_month').val(), 10);
	var endMonth = parseInt($('#schedule-end-date_month').val(), 10);
	var startDay = parseInt($('#schedule-start-date_day').val(), 10);
	var endDay = parseInt($('#schedule-end-date_day').val(), 10);
	var startDate = 365 * startYear + 31 * startMonth + startDay;
	var endDate = 365 * endYear + 31 * endMonth + endDay;
	var errorLabel;
	var isDateValid = true;
	var startDateTime = new Date(startYear, startMonth - 1, startDay, startHour, startMinute);
	var endDateTime = new Date(endYear, endMonth - 1, endDay, endHour, endMinute);
	var startDateTimeMonth = parseInt(startDateTime.getMonth(), 10) + 1;
	var startDateTimeDay = startDateTime.getDate();
	var endDateTimeMonth = parseInt(endDateTime.getMonth(), 10) + 1;
	var endDateTimeDay = endDateTime.getDate();

	$('.time-error').remove();
	$('.date-error').remove();

	if (startHour === '' || startMinute === '') {
		errorLabel = '<div  class="errorcontain"><label class="date-error error">Start time is required</label></div>';
		$('#schedule-start-time_minute').parent().append(errorLabel);
		isDateValid = false;
	}

	if (endHour === '' || endMinute === '') {
		errorLabel = '<div  class="errorcontain"><label class="date-error error">End time is required</label></div>';
		$('#schedule-end-time_minute').parent().append(errorLabel);
		isDateValid = false;
	}

	if (startDateTimeMonth !== startMonth || startDateTimeDay !== startDay) {
		$('.time-error').remove();
		errorLabel = '<div  class="errorcontain"><label class="date-error error">Start date is invalid</label></div>';
		$('#schedule-start-date_year').parent().append(errorLabel);
		isDateValid = false;
	}

	if (endDateTimeMonth !== endMonth || endDateTimeDay !== endDay) {
		$('.time-error').remove();
		errorLabel = '<div  class="errorcontain"><label class="date-error error">End date is invalid</label></div>';
		$('#schedule-end-date_year').parent().append(errorLabel);
		isDateValid = false;
	}

	if (isNaN(startDate)) {
		$('.time-error').remove();
		errorLabel = '<div  class="errorcontain"><label class="date-error error">Start date is required</label></div>';
		$('#schedule-start-date_year').parent().append(errorLabel);
		isDateValid = false;
	}

	if (isNaN(endDate)) {
		$('.time-error').remove();
		errorLabel = '<div  class="errorcontain"><label class="date-error error">End date is required</label></div>';
		$('#schedule-end-date_day').parent().append(errorLabel);
		isDateValid = false;
	}

	if (endDate < startDate) {
		$('.time-error').remove();
		errorLabel = '<div  class="errorcontain"><label class="date-error error">End date has to be greater than start date</label></div>';
		$('#schedule-end-date_day').parent().append(errorLabel);
		isDateValid = false;
	}

	$('.time-error').remove();

	if (endTime < startTime) {
		errorLabel = '<div  class="errorcontain"><label class="time-error error">End time has to be greater than start time</label></div>';
		$('#schedule-end-time_hour').parent().append(errorLabel);
		isDateValid = false;
	}

	if (isDateValid) {
		return true;
	}

	return false;
}

function gradingRadio (radio) {
	'use strict';
	$('#grading-procedure-text').hide();
	if (radio.val() === 'Custom') {
		$('#grading-procedure-text').show();
	}
	radio.parents('.ui-accordion-content').css('height', 'auto');
}

function populateRepeatsEvery () {
	'use strict';
	if ($('#repeats option:selected').text() === 'Daily') {
		$('#duration').text('Days');
		$('#duration, label[for="repeatsEvery"], #repeats-every').css('visibility', 'visible');
		$('label[for="scheduleWeekDays"], label[for="weekdays"], :checkbox').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
	} else if ($('#repeats option:selected').text() === 'Weekly') {
		$('#duration').text('Weeks');
		$('#duration, label[for="repeatsEvery"], #repeatsEvery, label[for="scheduleWeekDays"], label[for="weekdays"], :checkbox, #repeats-every').css('visibility', 'visible');
	} else {
		$(':checkbox, label[for="weekdays"], label[for="scheduleWeekDays"], #duration, label[for="repeatsEvery"], #repeats-every').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
	}
}

function setDefaultHint () {
	'use strict';
	var courseName = $('#courseName').val();
	var courseUrl = $('#courseUrl').val();

	if (courseName === 'New Imod' && courseUrl === 'example.com') {
		$('#courseName').val('');
		$('#imod-number').val('');
		$('#courseUrl').val('');
		$('#subject-area').val('');
	}
}

$(document).ready(
	function () {
		'use strict';
		populateRepeatsEvery();
		$('#attendance-box').css('visibility', 'visible');
		$('#participation-box').css('visibility', 'visible');
		$('#professional-conduct-box').css('visibility', 'visible');
		$('#missed-exams-box').css('visibility', 'visible');
		$('#missed-assignments-box').css('visibility', 'visible');
		if (!$('.instructor-list').find('tbody tr').length) {
			$('.remove-instructor').hide();
		}

		setDefaultHint();
		$('#repeats').on('change', populateRepeatsEvery);
		$('#please-select-instructor').dialog({
			autoOpen: false,
			buttons: {
				OK: function () {
					$(this).dialog('close');
				}
			}
		});

		$('#instructor-removed-success').dialog({
			autoOpen: false
		});

		$('#confirm-instructor-remove').dialog({
			autoOpen: false,
			modal: true,
			dialogClass: 'flora',
			buttons: {
				yes: function () {
					var ids = [];

					$(this).dialog('close');

					$('.instructor-list').find('.topicListRow.selected').each(
						function () {
							ids.push($(this).data('id'));
							if (!($(this).data('id'))) {
								$(this).remove();
							}
						}
					);

					$.ajax({
						url: baseUrl + 'courseOverview/delete',
						type: 'POST',
						dataType: 'json',
						data: {
							imodId: $('#imodID').val(),
							selected: ids
						},
						success: function () {
							var index;

							$('#instructor-removed-success').dialog('open');
							for (index = 0; index < ids.length; index++) {
								$('#instructor' + ids[index]).remove();
							}

							if (!$('.instructor-list').find('tbody tr').length) {
								$('.remove-instructor').hide();
							}
						}
					});
				},
				no: function () {
					$(this).dialog('close');
				}
			}
		});
		$('.flora.ui-dialog').css({position: 'fixed'});

		// Delete instructor logic
		$('.delete-instructor').click(
			function () {
				var ids = [];

				$('.instructor-list').find('.topicListRow.selected').each(
					function () {
						ids.push($(this).data('id'));
					}
				);

				if (ids.length < 1) {
					$('#please-select-instructor').dialog('open');
					return false;
				}

				$('#confirm-instructor-remove').dialog('open');

				return false;
			}
		);

		$('#topicList').on('click', '.saveIcon',
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
				$(this).parent().toggleClass('selected');
			}
		);

		$('.saveIcon-parent').click(
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');

				if ($(this).find('i').hasClass('fa-square-o')) {
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
			}
		);

		$('#coursePolicyRadioCustom').click(function () {
			$('#attendance-tardiness').val('');
			$('#class-participation').val('');
			$('#professional-conduct').val('');
			$('#missed-exams').val('');
			$('#missed-assignments').val('');
		});

		$('#coursePolicyRadioDefault').click(function () {
			$('#attendance-tardiness').val('Regular on-time attendance in this course is expected.');
			$('#class-participation').val('Students are expected to participate in the educational process and not be a disruptive element with regard to the learning of others.');
			$('#professional-conduct').val('All students should be familiar with the Student Code of Conduct, which can be found at http://www.asu.edu/studentlife/judicial/.');
			$('#missed-exams').val('The only legitimate reasons for missing an exam are business or university related travel or illness for more than half the assignment period with appropriate documentation. Contact your instructor to make appropriate arrangements.');
			$('#missed-assignments').val('Assignments should be turned in by the specified deadline. Late assignments will not be accepted unless prior arrangements have been made with the instructor.');
			return false;
		});

		$('.topicButtonGradient .add').click(
			function () {
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
				row += '    <select name="role[]" id="role" class="role custom-dropdown">';
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
				$('.remove-instructor').show();
				return false;
			}
		);

		$('#cancelTopic').click(
			function () {
				$('#topicDialogBackground, #topicDialog').hide();
				return false;
			}
		);

		$('.overview-save').click(
			function () {
				var isScheduleValid;
				var isOverviewValid;
				var isInstructorValid;
				var parameterList = [];
				var row;

				$('.errorcontain').remove();

				isOverviewValid = $('.courseoverview').valid();
				isScheduleValid = compareStartEndTimes();
				isInstructorValid = instructorValidator();

				if (isScheduleValid & isOverviewValid & isInstructorValid) {
					$('.topicListRow').each(
						function () {
							row = $(this);

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
						}
					);

					$.ajax({
						url: baseUrl + 'courseOverview/create',
						type: 'POST',
						dataType: 'json',
						data: {
							imodId: $('input[name=id]').val(),
							parameters: JSON.stringify(parameterList)
						},
						success:
							function () {
								$('.courseoverview').submit();
							}
					});
				} else {
					scrollToError();
				}
				return false;
			}
		);

		// Regex method for url
		$.validator.addMethod('urlRule',
			function (value, element, regexpr) {
				if (value === null || value === '') {
					return true;
				}
				return regexpr.test(value);
			},
			'Please enter a valid URL.'
		);

		$('#time-ratio').mask('9:9');

		/* $('.timeFields').find('select').change(
		 function () {
		 return compareStartEndTimes();
		 }
		 );

		 $('.dateFields').find('select').change(
		 function () {
		 return compareStartEndTimes();
		 }
		 ); */

		$('#attendance-box').click(
			function () {
				$('#attendance').val('Regular on-time attendance in this course is expected.');
				$('#attendance-box').removeAttr('checked');
				return false;
			}
		);
		$('#participation-box').click(
			function () {
				$('#class-participation').val('Students are expected to participate in the educational process and not be a disruptive element with regard to the learning of others.');
				$('#participation-box').removeAttr('checked');
				return false;
			}
		);
		$('#professional-conduct-box').click(
			function () {
				$('#professional-conduct').val('All students should be familiar with the Student Code of Conduct, which can be found at http://www.asu.edu/studentlife/judicial/.');
				$('#professional-conduct-box').removeAttr('checked');
				return false;
			}
		);
		$('#missed-exams-box').click(
			function () {
				$('#missed-exams').val('The only legitimate reasons for missing an exam are business or university related travel or illness for more than half the assignment period with appropriate documentation. Contact your instructor to make appropriate arrangements.');
				$('#missed-exams-box').removeAttr('checked');
				return false;
			}
		);

		$('#missed-assignments-box').click(
			function () {
				$('#missed-assignments').val('Assignments should be turned in by the specified deadline. Late assignments will not be accepted unless prior arrangements have been made with the instructor.');
				$('#missed-assignments-box').removeAttr('checked');
				return false;
			}
		);

		// Course overview validation
		$('.courseoverview').validate({
			rules: {
				imodNumber: 'required',
				name: {
					required: true
				},
				url: {
					required: false,
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

		// Grading procedure radio buttons
		$('.grading-radio').change(
			function () {
				gradingRadio($(this));
			}
		);

		$('.courseoverview').submit(
			function () {
				var radio = $('.grading-radio:checked');

				if (radio.val() !== 'Custom') {
					$('#grading-procedure-text').val(radio.val());
				}
			}
		);
	}
);
