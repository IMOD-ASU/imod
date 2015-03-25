'use strict';

// load techniques on page load
filterPedagogyTechniques();

// the filters for the pedagogy technique are wrapped in a accordian
$('#filter-pedagogy-techniques').accordion();

// add hide the add new technique modal
$('#add-new-technique').dialog({
	autoOpen: false
});

// attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterPedagogyTechniques);
$('input[name=learningDomain]').on('change', filterPedagogyTechniques);
$('input[name=domainCategory]').on('change', filterPedagogyTechniques);

// when add new technique button is clicked open modal
$('#add-new-technique-button').on('click', openNewPedagogyTechniqueModal);

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewPedagogyTechniqueModal() {
	$('#techniqueId').val('');
	$('#add-new-technique').dialog('open');
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
	for (var index = 0; index < data.idealPedagogyTechniqueMatch.length; index++) {
		var currentTechnique = data.idealPedagogyTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnque" value="' + currentTechnique.id + '"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	var extendedText = '';
	// take the titles and make html code to display
	for (index = 0; index < data.extendedPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.extendedPedagogyTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnque" value="' + currentTechnique.id + '"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	// add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset().click(function() {
		$('#add-new-technique').dialog('open');
		displayPedagogyInformationInEdit();
	});

	$('#extended-matches').buttonset().click(function() {
		$('#add-new-technique').dialog('open');
		displayPedagogyInformationInEdit();
	});
}

function displayPedagogyInformationInEdit() {
	$('#techniqueId').val($('label.ui-state-active').attr('for'));
	$.ajax({
			url: '../../pedagogyTechnique/get/' + $('label.ui-state-active').attr('for'),
			method: 'GET'
		})
		.done(populatePedagogyTechnique);
}

function populatePedagogyTechnique(data) {
	var currentTechnique = data.pedagogyTechnique;
	// set the text fields
	$('#title').val(currentTechnique.title);
	$('#location').val(currentTechnique.location);
	$('#direction').val(currentTechnique.direction);
	$('#materials').val(currentTechnique.materials);
	$('#reference').val(currentTechnique.reference);
	$('#strategyDescription').val(currentTechnique.strategyDescription);
	$('#activityDescription').val(currentTechnique.activityDescription);

	// choose correct item from selectables
	$('.learningDomain option[value=' + currentTechnique.learningDomain[0].id + ']').prop('selected', true);
	$('.domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);
}
