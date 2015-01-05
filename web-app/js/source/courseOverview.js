'use strict';

$(document).ready(function() {

	populateRepeatsEvery();

	$('#repeats').on(
			'change',
			populateRepeatsEvery
			);
});

function populateRepeatsEvery(event) {
	if($('#repeats option:selected').text() == "Daily") {
		$('#duration').text("days");
		$('#duration').css('visibility','visible');
		$('label[for="repeatsEvery"]').css('visibility', 'visible');
		$('#repeatsEvery').css('visibility','visible');
		$('label[for="scheduleWeekDays"]').css('visibility', 'hidden');
		$('label[for="weekdays"]').css('visibility', 'hidden');
		$(':checkbox').removeAttr('checked');
		$(':checkbox').css('visibility','hidden');

	}
	else if($('#repeats option:selected').text() == "Weekly") {
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
