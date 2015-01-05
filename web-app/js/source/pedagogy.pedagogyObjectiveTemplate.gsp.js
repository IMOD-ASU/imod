'use strict';

$(function() {
		$( "#ped_accordion" ).accordion({
			heightStyle: "content"
		});
	});
function submitRefresh(){
	$("#pedAccordion").submit();
	// FIXME call to function not defined in file
	expandExtended();
}
