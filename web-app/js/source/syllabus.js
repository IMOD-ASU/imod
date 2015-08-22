$('#course-details-check').click(function () {
	'use strict';
	if ($('#course-details-check').is(':checked')) {
		$('#course').css('display', 'block');
	} else {
		$('#course').css('display', 'none');
	}
});
$('#schedule-check').click(function () {
	'use strict';
	if ($('#schedule-check').is(':checked')) {
		$('#schedule').css('display', 'block');
	} else {
		$('#schedule').css('display', 'none');
	}
});

$('#course-description-check').click(function () {
	'use strict';
	if ($('#course-description-check').is(':checked')) {
		$('#description').css('display', 'block');
	} else {
		$('#description').css('display', 'none');
	}
});
$('#course-policy-check').click(function () {
	'use strict';
	if ($('#course-policy-check').is(':checked')) {
		$('#policy').css('display', 'block');
	} else {
		$('#policy').css('display', 'none');
	}
});
$('#instructor-check').click(function () {
	'use strict';
	if ($('#instructor-check').is(':checked')) {
		$('#instructor').css('display', 'block');
	} else {
		$('#instructor').css('display', 'none');
	}
});

$('#grading-procedure-check').click(function () {
	'use strict';
	if ($('#grading-procedure-check').is(':checked')) {
		$('#grading-procedure').css('display', 'block');
	} else {
		$('#grading-procedure').css('display', 'none');
	}
});
$('#attendance-check').click(function () {
	'use strict';
	if ($('#attendance-check').is(':checked')) {
		$('#attendance').css('display', 'block');
	} else {
		$('#attendance').css('display', 'none');
	}
});
$('#participation-check').click(function () {
	'use strict';
	if ($('#participation-check').is(':checked')) {
		$('#participation').css('display', 'block');
	} else {
		$('#participation').css('display', 'none');
	}
});
$('#conduct-check').click(function () {
	'use strict';
	if ($('#conduct-check').is(':checked')) {
		$('#conduct').css('display', 'block');
	} else {
		$('#conduct').css('display', 'none');
	}
});
$('#exams-check').click(function () {
	'use strict';
	if ($('#exams-check').is(':checked')) {
		$('#exams').css('display', 'block');
	} else {
		$('#exams').css('display', 'none');
	}
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
	if ($('#course-details-check').is(':checked')) {
		$('#course-details-check').css('display', 'none');
	} else {
		$('#course').parent().css('display', 'none');
	}
	if ($('#schedule-check').is(':checked')) {
		$('#schedule-check').css('display', 'none');
	} else {
		$('#schedule').parent().css('display', 'none');
	}
	if ($('#course-description-check').is(':checked')) {
		$('#course-description-check').css('display', 'none');
	} else {
		$('#description').parent().css('display', 'none');
	}
	if ($('#course-policy-check').is(':checked')) {
		$('#course-policy-check').css('display', 'none');
	} else {
		$('#policy').parent.css('display', 'none');
	}
	if ($('#instructor-check').is(':checked')) {
		$('#instructor-check').css('display', 'none');
	} else {
		$('#instructor').parent().css('display', 'none');
	}
	if ($('#grading-procedure-check').is(':checked')) {
		$('#grading-procedure-check').css('display', 'none');
	} else {
		$('#grading-procedure').parent().css('display', 'none');
	}
	if ($('#attendance-check').is(':checked')) {
		$('#attendance-check').css('display', 'none');
	} else {
		$('#attendance').parent().css('display', 'none');
	}
	if ($('#participation-check').is(':checked')) {
		$('#participation-check').css('display', 'none');
	} else {
		$('#participation').parent().css('display', 'none');
	}
	if ($('#conduct-check').is(':checked')) {
		$('#conduct-check').css('display', 'none');
	} else {
		$('#conduct').parent().css('display', 'none');
	}
	if ($('#exams-check').is(':checked')) {
		$('#exams-check').css('display', 'none');
	} else {
		$('#exams').parent().css('display', 'none');
	}
	if ($('#missed-exams-check').is(':checked')) {
		$('#missed-exams-check').css('display', 'none');
	} else {
		$('#missed-exams').parent().css('display', 'none');
	}
	$('#printpdf').css('visibility', 'hidden');
	window.print();
});
