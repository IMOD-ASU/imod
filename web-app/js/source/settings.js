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
				webPage: {
					regx: /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/
				},
				phoneNumber: {
					regx: /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/
				}
			},
			messages: {
				webPage: {
					regx: 'Please enter a valid url.'
				},
				phoneNumber: {
					regx: 'Phone Number is invalid. Valid Example: 202-555-0125.'
				}
			}
		});

		$.validator.addMethod('regx', function (value, element, regexpr) {
			if (value.trim().length > 0) {
				return regexpr.test(value);
			}

			return true;
		}, 'Pattern does not match');
	});
