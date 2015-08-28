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
    $('#generate-syllabus').click(function () {

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
			success: function (data) {
				window.open(baseUrl + 'courseOverview/generatedSyllabus/' + $('#imodID').val(), '_blank');
			}
		});

		return false;

    });
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
