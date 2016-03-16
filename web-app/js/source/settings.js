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
	});

