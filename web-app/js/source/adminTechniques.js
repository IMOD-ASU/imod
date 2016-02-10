$(function () {
	'use strict';
	$('.isAdminSelect').change(function () {
		var adminSelect = $(this);
		var techniqueClass = '';

		if (adminSelect.parents('.technique-table').hasClass('assessment')) {
			techniqueClass = 'assessment';
		} else if (adminSelect.parents('.technique-table').hasClass('assessment')) {
			techniqueClass = 'pedagogy';
		}

		$.post('changeStatus', {
			isAdmin: adminSelect.val(),
			id: adminSelect.data('id'),
			type: techniqueClass
		}, function (data) {
			if (data.success) {
				alert('Technique admin status changed successfully.');
			} else {
				alert('Technique admin status could not be changed.');
			}
		}).fail(function () {
			alert('Looks like something went wrong. Please try again.');
		});
	});
});
