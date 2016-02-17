$(function () {
	'use strict';
	$('.isAdminSelect').change(function () {
		var adminSelect = $(this);
		var techniqueClass = '';

		if (adminSelect.parents('.technique-table').hasClass('assessment')) {
			techniqueClass = 'assessment';
		} else if (adminSelect.parents('.technique-table').hasClass('pedagogy')) {
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

	$('.user-row').find('.save-btn').click(function () {
		var conf = confirm('Are you sure you want to change this?');
		var btn = $(this);
		var userRow = btn.parents('.user-row');
		var data = null;

		if (!conf) {
			userRow.find('select').find('.last_selected').prop('selected', true);
			return false;
		}

		data = userRow.find('select, input').serialize();

		$.post('changeUserStatus', data, function (data) {
			if (data.success) {
				userRow.find('select').find('option').removeClass('last_selected');
				userRow.find('select').find('option:selected').addClass('last_selected');
				alert('User status changed successfully.');
				location.reload();
			} else {
				alert(data.text);
				userRow.find('select').find('.last_selected').prop('selected', true);
			}
		}).fail(function () {
			alert('Looks like something went wrong. Please try again.');
			userRow.find('select').find('.last_selected').prop('selected', true);
		});

		return false;
	});
});
