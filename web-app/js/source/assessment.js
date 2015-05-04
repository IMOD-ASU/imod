'use strict';

// load techniques on page load
filterAssessmentTechniques();

// the filters for the pedagogy technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();


// auto hide the add new technique modal
$('#new-technique').dialog({
	autoOpen: false
});

// auto hide the add new technique modal
$('#display-new-technique').dialog({
	autoOpen: false
});

// attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// when add new technique button is clicked open modal
$('#new-technique-button').on('click', openNewAssessmentTechniqueModal);

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewAssessmentTechniqueModal() {
	$('#techniqueId').val('');
	$('#new-technique').dialog('open');
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible pedagogy techniques
 */
function filterAssessmentTechniques() {
	// get all of the selected checkboxes
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');

	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];

	// get the id of the grails domain from the value attribute in the html
	for (var index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// bundle the data into an object
	var data = {
		selectedKnowledgeDimensions: selectedKnowledgeDimensionsData,
		selectedLearningDomains: selectedLearningDomainsData,
		selectedDomainCategories: selectedDomainCategoriesData
	};

	// send the data to the find matching techniques action in grails
	// and process the response with the display pedagogy techniques callback
	$.ajax({
			url: '../findMatchingTechniques',
			method: 'post',
			data: JSON.stringify(data),
			contentType: 'application/json'
		})
		.done(displayAssessmentTechniques, showAssessmentTechnique);
}

function showAssessmentTechnique(data){

	$('#ideal-matches').buttonset().click(function() {
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();

	});

	$('#extended-matches').buttonset().click(function() {
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();
	});


}



/**
 * callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques(data) {
	var idealText = '';
	// take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.idealAssessmentTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnque" value="' + currentTechnique.id + '"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	var extendedText = '';
	// take the titles and make html code to display
	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		extendedText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnque" value="' + currentTechnique.id + '"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	// add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset();
	$('#extended-matches').buttonset();

}





function displayAssessmentInformationInEdit() {
	$('#techniqueId1').val($('label.ui-state-active').attr('for'));


	$.ajax({
			url: '../../assessmentTechnique/display/' + $('label.ui-state-active').attr('for'),
			method: 'GET'
		})
		.done(populateAssessmentTechnique);
}

function populateAssessmentTechnique(data) {
	var currentTechnique = data.assessmentTechnique;

	alert("current tech params ::::   "+ currentTechnique.assignedToLearningObjective);

	// set the text fields
	$('#title1').val(currentTechnique.title);
	$('#title2').val(currentTechnique.title);

	$('#description1').val(currentTechnique.description);
	$('#description2').val(currentTechnique.description);

	$('#procedure1').val(currentTechnique.procedure);
	$('#procedure2').val(currentTechnique.procedure);

	$("input[name='duration1']").val(currentTechnique.duration);
	$("input[name='duration2']").val(currentTechnique.duration);


	$("input[name='assessmentFeedback1']").val(currentTechnique.assessmentFeedback);

	var checkedCheckboxes = $('.assessmentFeedback2:checked');

	$('.assessmentFeedback3').val($.each(checkedCheckboxes, function(index, checkbox)
		{var theValue = checkbox.value;}
	));



	$('#learningDomain option[value=' + currentTechnique.learningDomain[0].id+ ']').prop('selected', true).val();
	//alert("this is the display alert to return value"+ ld);
	$('#domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);

	$( ".allInputs" ).hide();

	$(".allInputs1").replaceWith( function() {
		return "<span class=\"allspans1\"> :" + $( this ).val() + "</span>";
	});


	$('#View').hide();


}


//clicking on edit and View in display technique modal
$( "#Edit" ).click( function() {

	$('#View').show();

	$( ".allInputs" ).show();

	$( ".allspans1" ).hide();
});



$( "#View" ).click( function() {

$(".allInputs").hide();
$( ".allspans1" ).show();

});
