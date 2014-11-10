// SpringSecurityUI namespace
if (typeof SpringSecurityUI == 'undefined') {
	SpringSecurityUI = new Object();

	/**
	 * Displays a message.
	 * @param  type  the type of message
	 * @param  text  the message text
	 * @param  duration  how long to display the message
	 */
	/*public*/ SpringSecurityUI.message = function(type, text, duration) {
		var clazz = 'icon '
		if (type == 'error') {
			clazz += 'icon_error';
		}
		else if (type == 'info') {
			clazz += 'icon_info';
		}

		if(text == 'Sorry, your account is locked.'){
			//let the message stay till user dismisses it
			//if the message says account is locked

			$.jGrowl('<span class="' + clazz + '">' + text + '</span>', {
				sticky: true
			});	
		}else{
			$.jGrowl('<span class="' + clazz + '">' + text + '</span>', {
				life: duration
			});	
		}
		
	};
}
