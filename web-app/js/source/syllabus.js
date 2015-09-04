var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

$(function () {
	'use strict';

	// hide unchecked areas on page load
	$('.display-toggle').each(function () {
		toggleVisibility($(this), $('#' + $(this).data('id')));
	});

	$('.display-toggle').click(function () {
		toggleVisibility($(this), $('#' + $(this).data('id')));
	});

	$('#printpdf').click(function () {
		$('.display-toggle').each(function () {
			togglePrintView($(this));
		});

		$('#printpdf').css('visibility', 'hidden');

		window.print();
	});

	// Save the syllabus hide/show data for the imod
	// and redirect the user to generate syllabus
	$('.generate-syllabus').click(function () {
		var hideSectionsList = [];

		$('.display-toggle').each(function () {
			if (!$(this).is(':checked')) {
				hideSectionsList.push('-' + $(this).data('id') + '-');
			}
		});

		$.ajax({
			url: baseUrl + 'courseOverview/updateSyllabusPrefs',
			type: 'POST',
			dataType: 'json',
			data: {
				imodId: $('#imodID').val(),
				hideSectionsList: hideSectionsList.join()
			},
			async: false,
			success: function () {
				window.open(baseUrl + 'courseOverview/generatedSyllabus/' + $('#imodID').val(), '_blank');
			}
		});

		return false;
	});

	// Generate sorting arrows
	var sortArrows = '<div class="sort-arrows">';

	sortArrows += '<i class="fa fa-sort-up"></i>';
	sortArrows += '<i class="fa fa-sort-down"></i>';
	sortArrows += '</div>';

	$('.form-title').each(function () {
		$(this).find('h3').prepend(sortArrows);
		$(this).addClass('form-title-sort-arrows');
	});

	$(document).on('click', '.fa-sort-up', function () {
		var target = $(this).parent().parent().parent().parent();
		var previous = target.prev();
		var next = previous.clone();

		previous.remove();
		target.after(next);
		sortSections();
		return false;
	});

	$(document).on('click', '.fa-sort-down', function () {
		var target = $(this).parent().parent().parent().parent();
		var next = target.next();
		var previous = next.clone();

		next.remove();
		target.before(previous);
		sortSections();
		return false;
	});


	// order the sections based on the id list generated
	if ($('#sortIdList').val() !== '') {
		var contentList = [];
		var sortIdList = $('#sortIdList').val();

		sortIdList = sortIdList.split(',');
		for (var index = 0; index < sortIdList.length; index++) {
			var id = sortIdList[index].substring(1, sortIdList[index].length - 1);
			var parent = $('#' + id).parent();

			contentList.push(parent.clone());
			parent.remove();
		}

		for (index = 0; index < contentList.length; index++) {
			$('#syllabus-content').append(contentList[index]);
		}
	}
});

function toggleVisibility (toggle, target) {
	'use strict';

	if (toggle.is(':checked')) {
		target.show();
	} else {
		target.hide();
	}
}

function togglePrintView (toggle) {
	'use strict';

	if (toggle.is(':checked')) {
		toggle.hide();
	} else {
		toggle.parent().hide();
	}
}

// saves the order of the sections to the DB
function sortSections () {
	'use strict';

	var sortIdList = [];

	$('.form-title').each(function () {
		// get the next div's id
		// and add it to the array
		sortIdList.push('-' + $(this).next().prop('id') + '-');
	});

	$.ajax({
		url: baseUrl + 'courseOverview/updateSyllabusOrder',
		type: 'POST',
		dataType: 'json',
		data: {
			imodId: $('#imodID').val(),
			sortIdList: sortIdList.join()
		}
	});
}
