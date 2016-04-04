$(document).ready(
	function () {
		'use strict';
		$('.update-settings').validate({
			rules: {
				currentPassword: {
					required: true
				},
				newPassword: {
					required: true,
					minlength: 6,
					maxlength: 16
				},
				reenterPassword: {
					equalTo: '#newPassword'
				}
			}
		});

		$('.details-settings').validate({
			rules: {
				firstName: {
					required: true,
					minlength: 3,
					maxlength: 20
				},
				lastName: {
					required: true,
					minlength: 3,
					maxlength: 20
				},
				role: {
					required: true
				}
			}
		});
	});

