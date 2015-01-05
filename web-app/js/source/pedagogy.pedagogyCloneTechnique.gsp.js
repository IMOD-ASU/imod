'use strict';

// Initializing the Accordion for Activity & Reference
$(function() {
	$("form#cloneTechniqueForm").find("#ped_technique").accordion({
		heightStyle: "content",
		collapsible: true,
		active: false
	});
});


//To close Clone Technique dialog box
function closeCloneTechnique() {
	$("#cloneTechnique").dialog("close");
		return false;
}

// Validation message for clone technique
if("${errorMsg}"=="title"){
	alert("ooops!!! \nNo changes have been made to the title. \nPleae make changes to the Title and alteast one other field to create a clone.");
}else{
	alert("ooops!!! \nPleae make changes to altlest one other field along with the title to create a clone.");
}
