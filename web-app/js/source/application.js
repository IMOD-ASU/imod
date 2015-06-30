$(document).ready(function () {
	'use strict';
	$('#accordion').accordion({
		heightStyle: 'content'
	});
	$('#help-placeholder-2').css('display', 'none');
	$('.show-hover-new').qtip({
		style: {
			background: 'transparent',
			border: 'none',
			color: 'black'
		},
		show: 'mouseover',
		hide: 'mouseout',
		position: {
			type: 'static',
			container: $('div#qtip-place')
		}
	});

	$('*').focus(
		function () {
			$('#qtip-place').html(document.activeElement.title);
		}
	);

	$('input[type="submit"]').hover(
		function () {
			$('#qtip-place').html($(this).attr('oldtitle'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);

	$('input[type="checkbox"], input[type="radio"], .knowledge-dim-label').hover(
		function () {
			$('#qtip-place').html($(this).attr('title'));
		},
		function () {
			$('#qtip-place').html('');
		}
	);

	// Make modals draggable
	$('.draggable')
		.not(document.getElementById('topicList'))
		.not(document.getElementById('resourceList'))
		.drags();

	$('#help-placeholder > #open-button, #help-placeholder-2 > #open-button').click(function () {
		var helpbox = $('#help-placeholder');
		var helpbox2 = $('#help-placeholder-2');
		var tabContainer = $('#tabs-container');
		if (helpbox.css('display') === 'block') {
			helpbox.css('display', 'none');
			helpbox2.css('display', 'block');
			tabContainer.css('margin-right', '5.5%');
		} else {
			helpbox.css('display', 'block');
			helpbox2.css('display', 'none');
			tabContainer.css('margin-right', '17.5%');
		}
	});

	$('.ui-tabs-nav.ui-widget-header.ui-corner-all > li > a').click(function (event) {
		if ($('meta[name="imod-is-saved"]').attr('value') === 'true') {
			event.preventDefault();
			alert('Please save the course overview page before proceeding to next page');
		}
	});

	// Hide modal when background is clicked
	$(document).on('click', '.modalBackground', function () {
		$('.draggable').hide();
		$('.modalBackground').hide();
	});
});
