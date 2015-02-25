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

		if (confirm("Are you sure you want to delete the instructor(s)") == false) {
	        return false;
	    }

	    var ids = [];
		$('.instructor-list').find('.topicListRow.selected').each(function(){
			ids.push($(this).data('id'));
		});

		$.ajax({
			url: baseUrl + 'courseOverview/delete',
			type: 'POST',
			dataType: 'json',
			data: {
				imod_id: $('#imodID').val(),
				selected: ids
			},
			success: function(data){
				location.reload()
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

	$('.topicButtonGradient .add').click(function(){
		var row = $('#topicList tbody tr:first').clone();
		$('#topicList tbody').append(row);
		$('#topicList tbody tr:last input').each(function(){
			$(this).val('');
		});
		return false;
	});

	$('#cancelTopic').click(function(){
		$('#topicDialogBackground, #topicDialog').hide()
		return false;
	});

	$('.save-instructors').click(function(){

		var parameterList = [];

		$('.topicListRow').each(function(){
			var row = $(this);
			console.log(row)

			parameterList.push({
				lastName: row.find('input[name=lastName]').val(),
				firstName: row.find('input[name=firstName]').val(),
				email: row.find('input[name=email]').val(),
				officeHours: row.find('input[name=officeHours]').val(),
				webPage: row.find('input[name=webPage]').val(),
				role: row.find('input[name=role]').val(),
				location: row.find('input[name=location]').val(),
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
			success: function(data){
				location.reload()
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

function toggleSelected(event){
	if (!(event.target.nodeName in ['OPTION', 'INPUT', 'BUTTON','SELECT'])){
		$(this).find(".saveIcon > i").toggleClass("hidden");
		$(this).toggleClass("selected");
	}
}