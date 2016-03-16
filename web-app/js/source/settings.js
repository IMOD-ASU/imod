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
					minlength: 7,
					maxlength: 16
				},
				reenterPassword: {
					equalTo: '#newPassword'
				}
			}
		});
	});
