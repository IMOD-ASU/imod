'use strict';

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewPedagogyTechniqueModal () {
	$('#techniqueId').val('');
	$('#add-new-technique').dialog('open');
}

function populatePedagogyTechnique (data) {
	var currentTechnique = data.pedagogyTechnique;
	// Set the text fields
	$('#title').val(currentTechnique.title);
	$('#location').val(currentTechnique.location);
	$('#direction').val(currentTechnique.direction);
	$('#materials').val(currentTechnique.materials);
	$('#reference').val(currentTechnique.reference);
	$('#strategyDescription').val(currentTechnique.strategyDescription);
	$('#activityDescription').val(currentTechnique.activityDescription);

	// Choose correct item from selectables
	$('.learningDomain option[value=' + currentTechnique.learningDomain[0].id + ']').prop('selected', true);
	$('.domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);
}

function displayPedagogyInformationInEdit () {
	$('#techniqueId').val($('label.ui-state-active').attr('for'));
	$.ajax({
			url: '../../pedagogyTechnique/get/' + $('label.ui-state-active').attr('for'),
			method: 'GET'
		})
		.done(populatePedagogyTechnique);
}

/**
 * Callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayPedagogyTechniques (data) {
	var idealText = '';
	// Take the titles and make html code to display
	for (var index = 0; index < data.idealPedagogyTechniqueMatch.length; index++) {
		var currentTechnique = data.idealPedagogyTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnique" value="' + currentTechnique.id + '">';
		idealText += '<label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	var extendedText = '';
	// Take the titles and make html code to display
	for (index = 0; index < data.extendedPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.extendedPedagogyTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnique" value="' + currentTechnique.id + '">';
		idealText += '<label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	// Add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset().click(
		function () {
			$('#add-new-technique').dialog('open');
			displayPedagogyInformationInEdit();
		}
	);

	$('#extended-matches').buttonset().click(
		function () {
			$('#add-new-technique').dialog('open');
			displayPedagogyInformationInEdit();
		}
	);
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible pedagogy techniques
 */
function filterPedagogyTechniques () {
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
	}).done(displayPedagogyTechniques);
}

// Load techniques on page load
filterPedagogyTechniques();

// The filters for the pedagogy technique are wrapped in a accordian
$('#filter-pedagogy-techniques').accordion();

// Add hide the add new technique modal
$('#add-new-technique').dialog({
	autoOpen: false
});

// Attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterPedagogyTechniques);
$('input[name=learningDomain]').on('change', filterPedagogyTechniques);
$('input[name=domainCategory]').on('change', filterPedagogyTechniques);

// When add new technique button is clicked open modal
$('#add-new-technique-button').on('click', openNewPedagogyTechniqueModal);
