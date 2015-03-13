'use strict';

var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

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

		if (confirm('Are you sure you want to delete the instructor(s)') === false) {
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
			success: function(data) {
				location.reload();
			},
			error: function(xhr) {
				// when something goes wrong log to the browser console
				console.log(xhr.responseText);
			}
		});

		return false;

	});


	//add instructor modal
	/*$('.add-instructor').click(function(){
		$('#topicDialogBackground, #topicDialog').show()
		return false;
	});

	$('.topicButtonGradient .remove').click(function(){

		if ($('#topicList tbody tr').length > 1){
			$('#topicList tbody tr:last').remove();
		}

		return false;
	});*/

	$('#topicList > tbody').on('click', 'tr', toggleSelected);

	$('.topicButtonGradient .add').click(function() {
		var row = "";
		row += "<tr class=\"topicListRow\">";
		row += "	<td class=\"saveIcon\">";
		row += "		<i class=\"hidden fa fa-eraser\"><\/i>";
		row += "	<\/td>";
		row += "	<td><input type=\"text\" name=\"firstName\" value=\"\" id=\"firstName\"  class=\"first_name\" \/><\/td>";
		row += "	<td><input type=\"text\" name=\"lastName\" value=\"\" id=\"lastName\"  class=\"last_name\" \/><\/td>";
		row += "	<td><input type=\"text\" name=\"email\" value=\"\" id=\"email\"  class=\"email\" \/><\/td>";
		row += "	<td><input type=\"text\" name=\"officeHours\" value=\"\" id=\"officeHours\" class=\"office_hours\" \/><\/td>";
		row += "	<td><input type=\"text\" name=\"webPage\" value=\"\" id=\"webPage\" class=\"web_page\" \/><\/td>";
		row += "<td>";
		row += "	<select name=\"role\" id=\"role\" class=\"role\">";
		row += "		<option value=\"\">Select Role<\/option>";
		row += "		<option>Assistant Professor<\/option>";
		row += "		<option>Associate Professor<\/option>";
		row += "		<option>Professor<\/option>";
		row += "		<option>Teaching Assistant<\/option>";
		row += "		<option>Course Assistant<\/option>";
		row += "		<option>Grader<\/option>";
		row += "	<\/select>";
		row += "<\/td>";
		row += "	<td><input type=\"text\" name=\"location\" value=\"\" id=\"location\" class=\"location\" \/><\/td>";
		row += "<\/tr>";

		$('#topicList tbody').append(row);
		/*$('#topicList tbody tr:last input').each(function(){
			$(this).val('');
		});*/
		return false;
	});

	$('#cancelTopic').click(function() {
		$('#topicDialogBackground, #topicDialog').hide();
		return false;
	});

	$('.save-instructors').click(function() {

		var isValid = $('form.instructor-form').valid();

		if (isValid) {

			var parameterList = [];

			$('.topicListRow').each(function() {
				var row = $(this);
				console.log(row.data('id'));
				if (!row.data('id')) {
					parameterList.push({
						lastName: row.find('input[name=lastName]').val(),
						firstName: row.find('input[name=firstName]').val(),
						email: row.find('input[name=email]').val(),
						officeHours: row.find('input[name=officeHours]').val(),
						webPage: row.find('input[name=webPage]').val(),
						role: row.find('select[name=role]').val(),
						location: row.find('input[name=location]').val()
					});
				}

			});


			$.ajax({
				url: baseUrl + 'courseOverview/create',
				type: 'POST',
				dataType: 'json',
				data: {
					imod_id: $('input[name=id]').val(),
					parameters: JSON.stringify(parameterList)
				},
				success: function(data) {
					location.reload();
				},
				error: function(xhr) {
					// when something goes wrong log to the browser console
					console.log(xhr.responseText);
				}
			});

		}

		return false;
	});

	// instructor validation
	jQuery.validator.addClassRules({
		first_name: {
			required: true
		},
		last_name: {
			required: true
		},
		email: {
			required: true,
			email: true
		},
		role: {
			required: true
		}
	});

	// regex method for url
	$.validator.addMethod("urlRule", function(value, element, regexpr) {          
	    return regexpr.test(value);
	}, "Please enter a valid URL.");

	$('#time-ratio').mask("9:9")

	// course overview validation
	$(".courseoverview").validate({
	  	rules: {
	    	imodNumber: "required",
	    	name:{
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
	      		url: "Please enter a valid URL eg. http://google.com"
	    	},
	    	creditHours: {
	    		min: "Credit hours cannot be negative"
	    	},
	    	numberOfSeats: {
	    		min: "Number of seats cannot be negative"
	    	}
		}
	});

	// instructor form validation
	$('.instructor-form').validate();

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

function gradingRadio(radio) {
	$('#grading-procedure-text').hide();
	if (radio.val() === 'Custom') {
		$('#grading-procedure-text').show();
	}
	radio.parents('.ui-accordion-content').css('height', 'auto');
}

function populateRepeatsEvery() {
	if ($('#repeats option:selected').text() === "Daily") {
		$('#duration').text("days");
		$('#duration').css('visibility', 'visible');
		$('label[for="repeatsEvery"]').css('visibility', 'visible');
		$('#repeatsEvery').css('visibility', 'visible');
		$('label[for="scheduleWeekDays"]').css('visibility', 'hidden');
		$('label[for="weekdays"]').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
		$(':checkbox').css('visibility', 'hidden');
		$('#repeats-every').css('visibility', 'visible');

	} else if ($('#repeats option:selected').text() === "Weekly") {
		$('#duration').text("weeks");
		$('#duration').css('visibility', 'visible');
		$('label[for="repeatsEvery"]').css('visibility', 'visible');
		$('#repeatsEvery').css('visibility', 'visible');
		$('label[for="scheduleWeekDays"]').css('visibility', 'visible');
		$('label[for="weekdays"]').css('visibility', 'visible');
		$(':checkbox').css('visibility', 'visible');
		$('#repeats-every').css('visibility', 'visible');
	} else {
		$('label[for="repeatsEvery"]').css('visibility', 'hidden');
		$('#repeatsEvery').css('visibility', 'hidden');
		$('#duration').css('visibility', 'hidden');
		$('label[for="scheduleWeekDays"]').css('visibility', 'hidden');
		$('label[for="weekdays"]').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
		$(':checkbox').css('visibility', 'hidden');
		$('#repeats-every').css('visibility', 'hidden');
	}
}

function toggleSelected(event) {
	if (!(event.target.nodeName in ['OPTION', 'INPUT', 'BUTTON', 'SELECT'])) {
		$(this).find('.saveIcon > i').toggleClass("fa-square-o").toggleClass("fa-check-square");
		$(this).toggleClass('selected');
	}
}
