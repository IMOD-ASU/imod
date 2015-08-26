$(function () {
	'use strict';

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
});

function toggleVisibility ($toggle, $target) {
	'use strict';

	if ($toggle.is(':checked')) {
		$target.show();
	} else {
		$target.hide();
	}
}

function togglePrintView ($toggle) {
	'use strict';

	if ($toggle.is(':checked')) {
		$toggle.hide();
	} else {
		$toggle.parent().hide();
	}
}
