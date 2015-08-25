$('#course-details-check').click(function () {
	'use strict';
	toggleVisibility('#course-details-check', '#course');
});

$('#schedule-check').click(function () {
	'use strict';
	toggleVisibility('#schedule-check', '#schedule');
});

$('#course-description-check').click(function () {
	'use strict';
	toggleVisibility('#course-description-check', '#description');
});

$('#course-policy-check').click(function () {
	'use strict';
	toggleVisibility('#course-policy-check', '#policy');
});

$('#instructor-check').click(function () {
	'use strict';
	toggleVisibility('#instructor-check', '#instructor');
});

$('#grading-procedure-check').click(function () {
	'use strict';
	toggleVisibility('#grading-procedure-check', '#grading-procedure');
});

$('#attendance-check').click(function () {
	'use strict';
	toggleVisibility('#attendance-check', '#attendance');
});

$('#participation-check').click(function () {
	'use strict';
	toggleVisibility('#participation-check', '#participation');
});

$('#conduct-check').click(function () {
	'use strict';
	toggleVisibility('#conduct-check', '#conduct');
});

$('#exams-check').click(function () {
	'use strict';
	toggleVisibility('#exams-check', '#exams');
});
$('#missed-exams-check').click(function () {
	'use strict';
	if ($('#missed-exams-check').is(':checked')) {
		$('#missed-exams').css('display', 'block');
	} else {
		$('#missed-exams').css('display', 'none');
	}
});

$('#printpdf').click(function () {
	'use strict';
	togglePrintView('#course-details-check', '#course');
	togglePrintView('#schedule-check', '#schedule');
	togglePrintView('#course-description-check', '#description');
	togglePrintView('#course-policy-check', '#policy');
	togglePrintView('#instructor-check', '#instructor');
	togglePrintView('#grading-procedure-check', '#grading-procedure');
	togglePrintView('#attendance-check', '#attendance');
	togglePrintView('#participation-check', '#participation');
	togglePrintView('#conduct-check', '#conduct');
	togglePrintView('#exams-check', '#exams');
	togglePrintView('#missed-exams-check', '#missed-exams');

	$('#printpdf').css('visibility', 'hidden');
	window.print();
});

function toggleVisibility (toggle, destination) {
	'use strict';
	if ($(toggle).is(':checked')) {
		$(destination).css('display', 'block');
	} else {
		$(destination).css('display', 'none');
	}
}

function togglePrintView (toggle, alternate) {
	'use strict';
	if ($(toggle).is(':checked')) {
		$(toggle).css('display', 'none');
	} else {
		$(alternate).parent().css('display', 'none');
	}
}
