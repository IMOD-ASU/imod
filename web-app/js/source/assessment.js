'use strict';

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewAssessmentTechniqueModal () {
	$('#add-new-technique').dialog('open');
}

/**
 * Closes the modal to create a new pedagogy technique
 */
function closeNewAssessmentTechniqueModal () {
	$('#add-new-technique').dialog('close');
}

/**
 * Callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques (data) {
	var idealText = '';
	// Take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		idealText += '<input type="radio" id="radio' + index + '" name="assessmentTechnque" value="' + data.idealAssessmentTechniqueMatch[index].title + '">';
		idealText += '<label for="radio' + index + '">' + data.idealAssessmentTechniqueMatch[index].title + '</label>';
	}

	var extendedText = '';
	// Take the titles and make html code to display
	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		extendedText += '<div>' + data.extendedAssessmentTechniqueMatch[index].title + '</div>';
	}

	// Add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset();
	$('#extended-matches').buttonset();
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible pedagogy techniques
 */
function filterAssessmentTechniques () {
	// Get all of the selected checkboxes
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');

	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];

	// Get the id of the grails domain from the value attribute in the html
	for (var index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// Bundle the data into an object
	var data = {
		selectedKnowledgeDimensions: selectedKnowledgeDimensionsData,
		selectedLearningDomains: selectedLearningDomainsData,
		selectedDomainCategories: selectedDomainCategoriesData
	};

	// Send the data to the find matching techniques action in grails
	// and process the response with the display pedagogy techniques callback
	$.ajax({
		url: '../findMatchingTechniques',
		method: 'post',
		data: JSON.stringify(data),
		contentType: 'application/json'
	}).done(displayAssessmentTechniques);
}

// Load techniques on page load
filterAssessmentTechniques();

// The filters for the pedagogy technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();

// Add hide the add new technique modal
$('#add-new-technique').dialog({
	autoOpen: false
});

// Attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// When add new technique button is clicked open modal
$('#add-new-technique-button').on('click', openNewAssessmentTechniqueModal);

// When cancel button is clicked close modal
$('#create-pedagogy-cancel').on('click', closeNewAssessmentTechniqueModal);
