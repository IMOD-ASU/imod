/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewPedagogyTechniqueModal () {
	'use strict';
	$('#techniqueId').val('');
	$('#add-new-technique').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}
function populatePedagogyTechnique (data) {
	'use strict';
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
	$('#learningDomain option[value=' + data.learningDomain + ']').prop('selected', true);
	$('#domainCategory option[value=' + data.domainCategory + ']').prop('selected', true);
}

function displayPedagogyInformationInEdit () {
	'use strict';
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
	'use strict';
	var idealText = '';
	var index;
	var currentTechnique;
	var extendedText = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.idealPedagogyTechniqueMatch[index];
		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnique" value="' + currentTechnique.id + '">';
		idealText += '<label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	// Take the titles and make html code to display
	for (index = 0; index < data.extendedPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.extendedPedagogyTechniqueMatch[index];
		extendedText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnique" value="' + currentTechnique.id + '">';
		extendedText += '<label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label>';
	}

	// Add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset().find(':radio').click(
		function () {
			$('#add-new-technique').css('display', 'block');
			$('#topicDialogBackground').css('display', 'block');
			displayPedagogyInformationInEdit();
		}
	);

	$('#extended-matches').buttonset().find(':radio').click(
		function () {
			$('#add-new-technique').css('display', 'block');
			$('#topicDialogBackground').css('display', 'block');
			displayPedagogyInformationInEdit();
		}
	);
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible pedagogy techniques
 */
function filterPedagogyTechniques () {
	'use strict';
	// Get all of the selected checkboxes
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');
	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];
	var index;
	var data;

	// Get the id of the grails domain from the value attribute in the html
	for (index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// Bundle the data into an object
	data = {
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

function getMinHeight (liArray) {
	'use strict';
	var minHeight = Math.floor(liArray.eq(0).height());

	liArray.each(
		function () {
			var refineText;

			if (Math.floor($(this).height()) < minHeight) {
				minHeight = Math.floor($(this).height());
			}
			refineText = $('a', this).text().replace(/[\s\t]+/g, ' ');
			$('a', this).text(refineText);
		}
	);
	return minHeight;
}

function updateTextArea () {
	'use strict';
	var allVals = [];
	var valsLength;

	$('input[name=domainCategory]:checked').each(function () {
		allVals.push($(this).prev().prev().text().trim());
	});
	valsLength = allVals.length;

	if (allVals.length > 2) {
		$('#domainCategoryspan').html('<b>Domain Category (' + valsLength + ' Selections)</b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;');
	} else {
		$('#domainCategoryspan').html('<b>' + allVals + '</b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;');
	}
 }

$(document).ready(
	function () {
		'use strict';
		var liArray;
		var height;
		var currHeader;
		var currContent;
		var isPanelSelected;

		// Load techniques on page load
		filterPedagogyTechniques();
		// The filters for the pedagogy technique are wrapped in a accordian
		// beforeActivate is to be able to open both ideal & extended matches simultaneously
		$('#filter-pedagogy-techniques').accordion({collapsible: true, heightStyle: 'content'});
		$('#ideal-matches-toggle').accordion({collapsible: true,
			beforeActivate: function (event, ui) {
				// The accordion believes a panel is being opened
				if (ui.newHeader[0]) {
					currHeader = ui.newHeader;
					currContent = currHeader.next('.ui-accordion-content');
					// The accordion believes a panel is being closed
				} else {
					currHeader = ui.oldHeader;
					currContent = currHeader.next('.ui-accordion-content');
				}
				// Since we've changed the default behavior, this detects the actual status
				isPanelSelected = currHeader.attr('aria-selected') === 'true';
				// Toggle the panel's header
				currHeader.toggleClass('ui-corner-all', isPanelSelected).toggleClass('accordion-header-active ui-state-active ui-corner-top', !isPanelSelected).attr('aria-selected', ((!isPanelSelected).toString()));
				// Toggle the panel's icon
				currHeader.children('.ui-icon').toggleClass('ui-icon-triangle-1-e', isPanelSelected).toggleClass('ui-icon-triangle-1-s', !isPanelSelected);
				// Toggle the panel's content
				currContent.toggleClass('accordion-content-active', !isPanelSelected);
				if (isPanelSelected) {
					currContent.slideUp();
				} else {
					currContent.slideDown();
				}
				// Cancels the default action
				return false;
			}
		});


		// Attach a listener to the checkboxes, to update the pedaogy techniques
		// when the filters have been changed
		$('input[name=knowledgeDimension]').on('change', filterPedagogyTechniques);
		$('input[name=learningDomain]').on('change', filterPedagogyTechniques);
		$('input[name=domainCategory]').on('change',
		function () {
			updateTextArea();
			filterPedagogyTechniques();
		});

		// When add new technique button is clicked open modal
		$('#add-new-technique-button').on('click', openNewPedagogyTechniqueModal);

		// When hovered over LO side-tab list, it displays full text as tool-tip
		liArray = $('ul.learning-objective.list-wrapper').children('li');
		height = getMinHeight(liArray);

		liArray.each(
			function () {
				$('a', this).attr('title', $('a', this).text());
				if (Math.floor($(this).height()) !== height) {
					$('a', this).text($('a', this).text().substring(0, 70) + '...');
				}
				if ($(this).hasClass('active')) {
					$('a', this).text($('a', this).attr('title'));
				}
			}
		);
	}
);
