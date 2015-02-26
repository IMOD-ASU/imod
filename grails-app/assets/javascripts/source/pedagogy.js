'use strict';

// load techniques on page load
filterPedagogyTechniques();

// the filters for the pedagogy technique are wrapped in a accordian
$('#filter-pedagogy-techniques').accordion();

// add hide the add new technique modal
$('#add-new-technique').dialog({autoOpen: false});

// icons are managed as a button set
$('.icons').buttonset();

// attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterPedagogyTechniques);
$('input[name=learningDomain]').on('change', filterPedagogyTechniques);
$('input[name=domainCategory]').on('change', filterPedagogyTechniques);

// when add new technique button is clicked open modal
$('#add-new-technique-button').on('click', openNewPedagogyTechniqueModal);

// when cancel button is clicked close modal
$('#create-pedagogy-cancel').on('click', closeNewPedagogyTechniqueModal);

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewPedagogyTechniqueModal() {
	$('#add-new-technique').dialog('open');
}

/**
* Closes the modal to create a new pedagogy technique
*/
function closeNewPedagogyTechniqueModal() {
	$('#add-new-technique').dialog('close');
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible pedagogy techniques
 */
function filterPedagogyTechniques() {
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
	.done(displayPedagogyTechniques);
}

/**
 * callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayPedagogyTechniques(data) {
	var idealText = '';
	// take the titles and make html code to display
	for(var index = 0; index < data.idealPedagogyTechniqueMatch.length; index++) {
		idealText += '<input type="radio" name="actionWordCategory" value="' + data.idealPedagogyTechniqueMatch[index].title + '"><label>' + data.idealPedagogyTechniqueMatch[index].title + '</label>';
	}

	var extendedText = '';
	// take the titles and make html code to display
	for(index = 0; index < data.extendedPedagogyTechniqueMatch.length; index++) {
		extendedText += '<div>' + data.extendedPedagogyTechniqueMatch[index].title + '</div>';
	}

	// add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);
}
