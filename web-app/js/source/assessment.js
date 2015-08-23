/**
 * Opens the modal to create a new assessment technique
 */
function openAssessmentPlanModal () {
	'use strict';
	$('#assessment-plan').dialog('open');
	$('#assessment-plan').dialog({
		resizable: false,
		height: 'auto',
		title: 'Assessment Plan',
		width: 400,
		modal: true,
		zindex: 1001,
		buttons: {
			Cancel: function () {
				$(this).dialog('close');
			}
		}
	});

	displayAssessmentPlan();
}

/**
 * Opens the modal to create a new assessment technique
 */
function openNewAssessmentTechniqueModal () {
	'use strict';
	$('#techniqueId').val('');
	$('#new-technique').dialog('open');
	$('#new-technique').dialog({
		resizable: false,
		height: 'auto',
		title: 'New Assessment Technique',
		width: 'auto',
		modal: true,
		zindex: 1000
	});
}

function populateAssessmentTechnique (data) {
	'use strict';
	var currentTechnique = data.assessmentTechnique;

	// Set the text fields
	$('#title1').val(currentTechnique.title);
	$('#title2').val(currentTechnique.title);

	$('#description1').val(currentTechnique.description);
	$('#description2').val(currentTechnique.description);

	$('#procedure1').val(currentTechnique.procedure);
	$('#procedure2').val(currentTechnique.procedure);

	$('input[name=\'duration1\']').val(currentTechnique.duration);
	$('input[name=\'duration2\']').val(currentTechnique.duration);

	$('input[name=\'assessmentFeedback1\']').val(currentTechnique.assessmentFeedback);

	$('#domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);

	$('.allInputs').hide();

	$('.allInputs1').replaceWith(function () {
		return '<span class="allspans1"> :' + $(this).val() + '</span>';
	});

	$('#View').hide();
}

function displayAssessmentInformationInEdit () {
	'use strict';
	$('#techniqueId1').val($('label.ui-state-active').attr('for'));

	$.ajax({
		url: '../../assessmentTechnique/display/' + $('label.ui-state-active').attr('for'),
		method: 'GET'
	})
	.done(populateAssessmentTechnique);
}

function showAssessmentTechnique () {
	'use strict';
	$('#ideal-matches').buttonset().click(function () {
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();
	});

	$('#ideal-matches1').buttonset().click(function () {
		$('#display-new-technique').dialog('open');
		$('#display-new-technique').dialog({
			resizable: false,
			height: 'auto',
			title: 'Display Technique',
			width: 'auto',
			modal: true,
			zindex: 1000
		});
		displayAssessmentInformationInEdit();
	});

	$('#extended-matches').click(function () {
		$('#display-new-technique').dialog('open');
		$('#display-new-technique').dialog({
			resizable: false,
			height: 'auto',
			title: 'Display Technique',
			width: 'auto',
			modal: true,
			zindex: 1000
		});

		displayAssessmentInformationInEdit();
	});
}

function checkForAssign (data) {
	'use strict';
	var currentTechnique;
	var index;

	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if (currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		} else if (currentTechnique.assigncheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		} else if (currentTechnique.assigncheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		}
	}

	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		} else if (currentTechnique.assigncheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		} else if (currentTechnique.assigncheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		}
	}
}

/**
 * Callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques (data) {
	'use strict';
	var text = '';
	var index;
	var currentTechnique;

	// Take the titles and make html code to display
	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];
		text += '<input  type="radio" id="' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '">';
		text += '<span id="span-' + currentTechnique.id + '" class="icons">';
		text += '  <label for="' + currentTechnique.id + '">';
		text += '    ' + currentTechnique.title;
		text += '  </label>';
		text += '</span>';
	}

	$('#ideal-matches1').html(text);
	$('#ideal-matches1').buttonset();
	text = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === true && currentTechnique.assigncheck === true) {
			text += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavAssign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
		} else if (currentTechnique.assigncheck === false) {
			text += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
		}
	}

	$('#assessmentFavoritesDiv').html(text);
	$('#assessmentFavoritesDiv').buttonset();
	text = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		text += '<input  type="radio" id="' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span-' + currentTechnique.id + '" class="icons"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
	}

	$('#extended-matches').html(text);
	$('#extended-matches').buttonset();
	text = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === true) {
			if (currentTechnique.assigncheck === true) {
				text += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavAssign "><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			} else if (currentTechnique.assigncheck === false) {
				text += '<input  type="radio" id="' + currentTechnique.id + '" name="assessmentTech1" value="fav' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			}
		}
	}
	$('#assessmentFavoritesDiv1').html(text);
	$('#assessmentFavoritesDiv1').buttonset();
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible assessment techniques
 */
function filterAssessmentTechniques () {
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
	})
	.done(displayAssessmentTechniques, showAssessmentTechnique, checkForAssign);
}

function displayAssessmentPlan (data) {
	'use strict';
	$.ajax({
		url: '../../assessmentTechnique/assessmentplan',
		method: 'post',
		data: JSON.stringify(data),
		contentType: 'application/json'
	})
	.done(assessmentPlanData);
}

function assessmentPlanData (data) {
	'use strict';
	var allAssessmentData = '';
	var techniqueIndex;
	var assessmentPlan;
	var techniqueKnowledgeDimension;
	var knowledgeDimenisionIndex;
	var knowledgeDimension = '';
	// FIXME domain catgory is never assigned
	var domainCategory = '';
	// FIXME learning domain is never assigned
	var learningDomain = '';
	var index;
	var temporaryKnowledgeDimension;

	for (techniqueIndex = 0; techniqueIndex < data.assessmentTechInstance.length; techniqueIndex++) {
		assessmentPlan = data.assessmentTechInstance[techniqueIndex];

		// FIXME this looks like it access the last element, why not access directly?
		techniqueKnowledgeDimension = '';
		for (knowledgeDimenisionIndex = 0; knowledgeDimenisionIndex < assessmentPlan.knowledgeDimension.length; knowledgeDimenisionIndex++) {
			techniqueKnowledgeDimension = assessmentPlan.knowledgeDimension[knowledgeDimenisionIndex].id;
		}

		for (index = 0; index < data.knowledgeDimensions.length; index++) {
			temporaryKnowledgeDimension = data.knowledgeDimensions[index];

			if (temporaryKnowledgeDimension.id === techniqueKnowledgeDimension) {
				knowledgeDimension = temporaryKnowledgeDimension.description;
			}
			allAssessmentData += '<div id="' + assessmentPlan.id + '">';
			allAssessmentData += '  <span>';
			allAssessmentData += '    <h2>';
			allAssessmentData += '      ' + assessmentPlan.title;
			allAssessmentData += '    </h2>';
			allAssessmentData += '  </span>';
			allAssessmentData += '  <br/>';
			allAssessmentData += '  <span>';
			allAssessmentData += '    Description : ' + assessmentPlan.description;
			allAssessmentData += '  </span>';
			allAssessmentData += '  <br/>';
			allAssessmentData += '  <span>';
			allAssessmentData += '    ' + assessmentPlan.procedure;
			allAssessmentData += '  </span>';
			allAssessmentData += '  <br/>';
			allAssessmentData += '  <span>';
			allAssessmentData += '    ' + assessmentPlan.duration;
			allAssessmentData += '  </span>';
			allAssessmentData += '  <br/>';
			allAssessmentData += '  <span>';
			allAssessmentData += '    ' + assessmentPlan.assessmentFeedback.name;
			allAssessmentData += '  </span>';
			allAssessmentData += '</div>';
		}

		allAssessmentData += '<div id="' + assessmentPlan.id + '">';
		allAssessmentData += '  <span>';
		allAssessmentData += '    <h2>';
		allAssessmentData += '      ' + assessmentPlan.title;
		allAssessmentData += '    </h2>';
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    Description : ' + assessmentPlan.description;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    ' + assessmentPlan.procedure;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    ' + assessmentPlan.duration;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    DomainCategory :' + domainCategory;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    Learning Domain :' + learningDomain;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    Knowledge Dimension : ' + knowledgeDimension;
		allAssessmentData += '  </span>';
		allAssessmentData += '  <br/>';
		allAssessmentData += '  <span>';
		allAssessmentData += '    ' + assessmentPlan.assessmentFeedback.name;
		allAssessmentData += '  </span>';
		allAssessmentData += '</div>';
	}
}

// Load techniques on page load
filterAssessmentTechniques();

// Filters for the pedagogy technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();

$('#favorites').click(function () {
	'use strict';
	$('.favDiv').css('visibility', 'visible');
	$('.favDiv').css('display', 'block');
	$('#assessmentFavorites').css('visibility', 'visible');
	$('#assessmentFavorites').css('display', 'block');
	$('#assessmentFavoritesDiv').css('visibility', 'visible');
	$('#assessmentFavoritesDiv').css('display', 'block');

	$('#favorites').hide();
	$('#unfavorites').show();
});

$('#unfavorites').click(function () {
	'use strict';
	$('.favDiv').css('visibility', 'hidden');
	$('.favDiv').css('display', 'none');
	$('#assessmentFavorites').css('visibility', 'hidden');
	$('#assessmentFavorites').css('display', 'none');
	$('#assessmentFavoritesDiv').css('visibility', 'hidden');
	$('#assessmentFavoritesDiv').css('display', 'none');

	$('#favorites').show();
	$('#unfavorites').hide();
});

// Auto hide the assessment plan modal
$('#assessment-plan').dialog({
	autoOpen: false
});

// Auto hide the add new technique modal
$('#new-technique').dialog({
	autoOpen: false
});

// Auto hide the add new technique modal
$('#display-new-technique').dialog({
	autoOpen: false
});

// Attach a listener to the checkboxes, to update the assessment techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// When add new technique button is clicked open modal
$('#new-technique-button').on('click', openNewAssessmentTechniqueModal);

// When add assessment  plan button is clicked open modal
$('#assessment-plan-button').on('click', openAssessmentPlanModal);

// Clicking on edit and View in display technique modal
$('#Edit').click(function () {
	'use strict';
	$('#View').show();
	$('.allInputs').show();
	$('.allspans1').hide();
});

$('#View').click(function () {
	'use strict';
	$('.allInputs').hide();
	$('.allspans1').show();
});
