var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

$(function () {
	'use strict';

	var sortArrows;
	var contentList;
	var sortIdList;
	var index;
	var id;
	var parent;

	// hide unchecked areas on page load
	$('.display-toggle').each(function () {
		if ($(this).data().hasOwnProperty('id')) {
			toggleVisibility($(this), $('#' + $(this).data('id')));
		} else {
			toggleVisibility($(this), $('.' + $(this).data('class')));
		}
	});

	$(document).on('click', '.display-toggle', function () {
		if ($(this).data().hasOwnProperty('id')) {
			toggleVisibility($(this), $('#' + $(this).data('id')));
		} else {
			toggleVisibility($(this), $('.' + $(this).data('class')));
		}
	});

	$('.printpdf').click(function () {
		$('.display-toggle').each(function () {
			togglePrintView($(this));
		});

		window.print();
	});

	// Save the syllabus hide/show data for the imod
	// and redirect the user to generate syllabus
	$('.generate-syllabus').click(function () {
		var hideSectionsList = [];

		$('.display-toggle').each(function () {
			if (!$(this).is(':checked')) {
				if ($(this).data().hasOwnProperty('id')) {
					hideSectionsList.push('-' + $(this).data('id') + '-');
				} else {
					hideSectionsList.push('-' + $(this).data('class') + '-');
				}
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
				sortSections();
			}
		});

		return false;
	});

	// Generate sorting arrows
	sortArrows = '<div class="sort-arrows">';

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
		return false;
	});

	$(document).on('click', '.fa-sort-down', function () {
		var target = $(this).parent().parent().parent().parent();
		var next = target.next();
		var previous = next.clone();

		next.remove();
		target.before(previous);
		return false;
	});


	// order the sections based on the id list generated
	if ($('#sortIdList').val() !== '') {
		contentList = [];
		sortIdList = $('#sortIdList').val();

		sortIdList = sortIdList.split(',');
		for (index = 0; index < sortIdList.length; index++) {
			id = sortIdList[index].substring(1, sortIdList[index].length - 1);
			parent = $('#' + id).parent();

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
		async: false,
		data: {
			imodId: $('#imodID').val(),
			sortIdList: sortIdList.join()
		},
		success: function () {
			window.open(baseUrl + 'courseOverview/generatedSyllabus/' + $('#imodID').val(), '_blank');
		}
	});
}
