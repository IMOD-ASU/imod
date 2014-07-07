$( '#action-words' ).selectable();
//$( '#tabs-LO-subhead' ).tabs();
$("#tabs-container").tabs();

$( '.action-word' )
	.change(
		function() {
			$( '.learning-objective-performance')
				.html(
					$( '.ui-selected' ).innerHTML
				)
		}
	);
