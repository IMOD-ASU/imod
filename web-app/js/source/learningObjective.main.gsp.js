$( '#action-words' ).selectable();
//$( '#tabs-LO-subhead' ).tabs();

$( '.action-word' )
	.change(
		function() {
			$( '.learning-objective-performance')
				.html(
					$( '.ui-selected' ).innerHTML
				)
		}
	);
