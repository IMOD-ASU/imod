'use strict';

var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

$(document).ready(function() {
	populateRepeatsEvery();
	$('#repeats').on(
		'change',
		populateRepeatsEvery
	);

	// delete instructor logic
	$('.delete-instructor').click(function(){
		var id = $(this).data('imodid');

		$.ajax({
			url: baseUrl + 'courseOverview/delete',
			type: 'POST',
			dataType: 'json',
			data: {
				imod_id: id,
				selected: $('.selected-instructors').val()
			},
			success: function(data){

				console.log(data)
			},
			error: function(xhr) {
				// when something goes wrong log to the browser console
				console.log(xhr.responseText);
			}
		});
		return false;
	});
});

function populateRepeatsEvery() {
	if($('#repeats option:selected').text() === "Daily") {
		$('#duration').text("days");
		$('#duration').css('visibility','visible');
		$('label[for="repeatsEvery"]').css('visibility', 'visible');
		$('#repeatsEvery').css('visibility','visible');
		$('label[for="scheduleWeekDays"]').css('visibility', 'hidden');
		$('label[for="weekdays"]').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
		$(':checkbox').css('visibility','hidden');

	}
	else if($('#repeats option:selected').text() === "Weekly") {
		$('#duration').text("weeks");
		$('#duration').css('visibility','visible');
		$('label[for="repeatsEvery"]').css('visibility', 'visible');
		$('#repeatsEvery').css('visibility','visible');
		$('label[for="scheduleWeekDays"]').css('visibility', 'visible');
		$('label[for="weekdays"]').css('visibility', 'visible');
		$(':checkbox').css('visibility','visible');
	}
	else {
		$('label[for="repeatsEvery"]').css('visibility', 'hidden');
		$('#repeatsEvery').css('visibility','hidden');
		$('#duration').css('visibility','hidden');
		$('label[for="scheduleWeekDays"]').css('visibility', 'hidden');
		$('label[for="weekdays"]').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
		$(':checkbox').css('visibility','hidden');
	}
}
