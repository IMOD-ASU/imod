var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

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
	$('#add-new-technique').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}
function closeDimModal () {
	'use strict';
	var checked = '';
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');

	$('#selectKnowledgeDimensions input[type=checkbox]').each(function () {
		if ($(this).is(':checked')) {
			checked = checked + ($(this).val()) + ',';
		}
	});
	document.getElementById('knowledgeDimension').value = checked;
	dialog.css('display', 'none');
	background.css('display', 'none');
}
function closeDimModalCancel () {
	'use strict';

	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');

	dialog.css('display', 'none');
	background.css('display', 'none');
}
function changePic () {
	'use strict';
	var iconName = '';

	$('#selectKnowledgeDimensions').find('input:checkbox').each(
		function () {
			if ($(this).prop('checked')) {
				iconName += $(this).val().charAt(0);
			}
		}
	);
	if (iconName === '') {
		iconName = $('#imgNone').attr('href');
	} else {
		iconName = $('#img' + iconName).attr('href');
	}
	$('#dimImage').attr('src', iconName);
}
function openDimModal () {
	'use strict';
	var dimString = $('#knowDimensionList').val();
	var dimensionList = [];
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');
	var index;
	var findCheckBox;

	if (dimString !== '' && dimString !== null && typeof dimString !== 'undefined') {
		dimensionList = dimString.split(',');
	}
	for (index = 0; index < dimensionList.length; index++) {
		findCheckBox = $(dialog).find('#' + dimensionList[index]);
		if (findCheckBox.length === 1) {
			findCheckBox.prop('checked', true);
		}
	}
	changePic();
	dialog.css('display', 'inherit');
	background.css('display', 'block');
	return false;
}
function populateAssessmentTechnique (data) {
	'use strict';
	var currentTechnique = data.assessmentTechnique;
	var count;
	var checked = '';
	var arrayOfKnowledgeDimensions = data.knowledgeDimension.split(',');

	// Set the text fields
	$('#title').val(currentTechnique.title);
	// $('#title2').val(currentTechnique.title);

	$('#description').val(currentTechnique.description);
	// $('#description2').val(currentTechnique.description);

	$('#procedure').val(currentTechnique.procedure);
	// $('#procedure2').val(currentTechnique.procedure);

	$('input[name=\'duration\']').val(currentTechnique.duration);
	// $('input[name=\'duration2\']').val(currentTechnique.duration);

	$('#activityDescription').val(currentTechnique.description);

	$('#feedback-' + currentTechnique.assessmentFeedback.id).prop('selected', true);

	$('#assessmentDifficulty option[value=' + currentTechnique.difficulty + ']').prop('selected', true);
	$('#assessmentTime option[value=' + currentTechnique.whenToCarryOut + ']').prop('selected', true);

	$('#sources').val(currentTechnique.sources);

	for (count = 0; count < arrayOfKnowledgeDimensions.length; count++) {
		if (arrayOfKnowledgeDimensions[count] !== '') {
			$('#' + arrayOfKnowledgeDimensions[count]).prop('checked', true);
		}
	}
	$('#selectKnowledgeDimensions input[type=checkbox]').each(function () {
		if ($(this).is(':checked')) {
			checked = checked + ($(this).val()) + ',';
		}
	});
	document.getElementById('knowledgeDimension').value = checked;

	$('#learning-domain option[value="' + data.learningDomain + '"]').prop('selected', true);
	$('#domain-category option[value="' + data.domainCategory + '"]').prop('selected', true);

	$('#View').hide();
}

function displayAssessmentInformationInEdit () {
	'use strict';
	var res = '';

	var str = $('label.ui-state-hover').attr('for');
	var indexNo = str.indexOf('Extended');

	if (indexNo > -1) {
		res = str.substring(0, indexNo);
	} else {
		res = $('label.ui-state-hover').attr('for');
	}

	$('#techniqueId').val(res);


	$.ajax({
		url: '../../assessmentTechnique/display/' + res,
		method: 'GET'
	})
	.done(populateAssessmentTechnique);
}

function showAssessmentTechnique () {
	'use strict';
	$('#ideal-matches label').click(function () {
		$('#editTitle').html('<strong>Edit Assessment Technique</strong>');
		openNewAssessmentTechniqueModal();
		displayAssessmentInformationInEdit();
		return false;
	});

	$('#ideal-matches1 label').click(function () {
		$('#editTitle').html('<strong>Edit Assessment Technique</strong>');
		openNewAssessmentTechniqueModal();
		displayAssessmentInformationInEdit();
		return false;
	});

	$('#extended-matches label').click(function () {
		$('#editTitle').html('<strong>Edit Assessment Technique</strong>');
		openNewAssessmentTechniqueModal();
		displayAssessmentInformationInEdit();
		return false;
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
	var favoriteImgToggle = '';
	var assignImgToggle = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOAssessmentTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

		text += '<input type="radio" id="' + currentTechnique.id + '" name="assessmentTechnique" value="' + currentTechnique.id + '">';
		text += '<label class="assessment-block" for="' + currentTechnique.id + '"><div class="favorite" id="topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div class="assign" id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><br><br><button class="new-technique-popup-button clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
	}

	$('#ideal-matches1').html(text);
	$('#ideal-matches1').buttonset();
	text = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOAssessmentTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

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

		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOAssessmentTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

		text += '<input type="radio" id="' + currentTechnique.id + '" name="assessmentTechnique" value="' + currentTechnique.id + '">';
		text += '<label class="assessment-block" for="' + currentTechnique.id + '"><div class="favorite" id="topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div class="assign" id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><br><br><button class="new-technique-popup-button clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
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

	$('#topLeft img').click(function () {
		var str = '';
		var indexNo = '';
		var res = '';

		if ($(this).attr('src') === '../../images/fav.png') {
			$(this).attr('src', '../../images/unfav.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}
			$.ajax({
				url: '../../assessmentTechnique/unassignFavorite/' + res,
				method: 'GET',
				success: function () {}

			});
		} else {
			$(this).attr('src', '../../images/fav.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}
			$.ajax({
				url: '../../assessmentTechnique/assignFavorite/' + res,
				method: 'GET',
				success: function () {}
			});
		}

		return false;
	});

	$('#topRight img').click(function () {
		var str = '';
		var indexNo = '';
		var res = '';

		if ($(this).attr('src') === '../../images/assign.png') {
			$(this).attr('src', '../../images/unassign.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}

			data = {
				learningObjectiveID: $('#learningObjectiveID').val(),
				assessmentTechniqueID: res
			};
			$.ajax({
				url: '../../assessmentTechnique/unassignToLearningObjective',
				type: 'POST',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function () {}
			});
		} else {
			$(this).attr('src', '../../images/assign.png');
			str = $('label.ui-state-hover').attr('for');
			indexNo = str.indexOf('Extended');
			if (indexNo > -1) {
				res = str.substring(0, indexNo);
			} else {
				res = str;
			}

			data = {
				learningObjectiveID: $('#learningObjectiveID').val(),
				assessmentTechniqueID: res
			};
			$.ajax({
				url: '../../assessmentTechnique/assignToLearningObjective',
				type: 'POST',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function () {}
			});
		}

		return false;
	});
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
		selectedDomainCategories: selectedDomainCategoriesData,
		learningObjectiveID: $('#learningObjectiveID').val()

	};

	// Send the data to the find matching techniques action in grails
	// and process the response with the display assessment techniques callback
	$.ajax({
		url: '../findMatchingTechniques',
		method: 'post',
		data: JSON.stringify(data),
		contentType: 'application/json'
	})
	.done(function (data) {
		displayAssessmentTechniques(data);
		showAssessmentTechnique(data);
		checkForAssign(data);
		assessmentEqualHeights('#ideal-matches1');
		assessmentEqualHeights('#extended-matches');
	});
}

function truncateString (string, count) {
	'use strict';
	if (string.length > count) {
		return string.substring(0, count) + '...';
	}

	return string;
}

function assessmentEqualHeights (parent) {
	'use strict';
	var max = 0;
	var isOpen = false;
	var parentBlock = $(parent);
	var assessmentBlock = parentBlock.find('.assessment-block');

	if (parentBlock.css('display') === 'none') {
		parentBlock.show();
		isOpen = true;
	}

	assessmentBlock.each(function () {
		var height = $(this).height();

		if (max < height) {
			max = height;
		}
	});
	assessmentBlock.height(max);
	if (isOpen) {
		parentBlock.hide();
	}
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

// Filters for the assessment technique are wrapped in a accordian
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
$('#new-technique-button').on('click', function () {
	'use strict';
	$('#editTitle').html('<strong>Add New Assessment Technique</strong>');
	openNewAssessmentTechniqueModal();
});

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

/* Populate the text above ideal matches*/
function updateTextArea (checkBoxName) {
	'use strict';
	var allVals = [];
	var valsLength;
	var text = '';
	var right = '';

	$('input[name=' + checkBoxName + ']:checked').each(function () {
		allVals.push($(this).prev().prev().text().trim());
	});
	valsLength = allVals.length;

	switch (checkBoxName) {
		case 'domainCategory':
			text = 'Domain Category';
			right = '&nbsp;&nbsp;<i class="fa fa-caret-right"></i>';
			break;
		case 'learningDomain':
			text = 'Learning Domain';
			right = '&nbsp;&nbsp;<i class="fa fa-caret-right"></i>';
			break;
		case 'knowledgeDimension':
			text = 'Knowledge Dimension';
			right = '';
			break;
	}

	if (allVals.length > 2) {
		$('#' + checkBoxName + 'span').html('<b>' + text + ' (' + valsLength + ' Selections)</b>' + right + '</span>&nbsp;&nbsp;');
	} else {
		$('#' + checkBoxName + 'span').html('<b>' + allVals + '</b>' + right + '</span>&nbsp;&nbsp;');
	}
}

/* callback added to do something when the response of the asyncronous ajax call has arrived*/
function populateDomainCategories (callback) {
	'use strict';
	$.ajax({
		url: baseUrl + 'learningObjective/getDomainCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#learning-domain').val().trim()
		},
		success: function (data) {
			// Stores the data from the call back
			var categories = data.value;
			// This stores the new html that will be added
			var options = '';
			var index;

			// For each of the categories
			for (index = 0; index < categories.length; index++) {
				// Create the html for the category
				options += '<option value="' + categories[index].name + '">' + categories[index].name + '</option>';
			}
			// Store this to the page
			$('#domain-category').empty().append(options);
			callback();
		}
	});
}


$(document).ready(
	function () {
		'use strict';
		var liArray;
		var height;
		var currHeader;
		var currContent;
		var isPanelSelected;
		var checkBoxName;
		var hasError = false;

		// Load techniques on page load
		filterAssessmentTechniques();
		// The filters for the assessment technique are wrapped in a accordian
		// beforeActivate is to be able to open both ideal & extended matches simultaneously
		$('#filter-assessment-techniques').accordion({collapsible: true, heightStyle: 'content'});
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
		$('#k1').click(openDimModal);
		$('#knowDimFinished').click(closeDimModal);
		$('#closeKnowDim').click(closeDimModalCancel);
		$('#selectKnowledgeDimensions').on('change', 'input:checkbox', changePic);
		// Attach a listener to the checkboxes, to update the pedaogy techniques
		// when the filters have been changed
		$('input[name=knowledgeDimension]').on('change',
		function () {
			checkBoxName = 'knowledgeDimension';
			updateTextArea(checkBoxName);
			filterAssessmentTechniques();
		});
		$('input[name=learningDomain]').on('change',
		function () {
			checkBoxName = 'learningDomain';
			updateTextArea(checkBoxName);
			filterAssessmentTechniques();
		});
		$('input[name=domainCategory]').on('change',
		function () {
			checkBoxName = 'domainCategory';
			updateTextArea(checkBoxName);
			filterAssessmentTechniques();
		});
		$('#saveButton').on('click',
		function () {
			if ($('#title').val() === '') {
				$('#errorMessage').text('Technique must have a title!');
				hasError = true;
			} else {
				hasError = false;
			}

			if (hasError === true) {
				return false;
			}
		});

		// When add new technique button is clicked open modal
		$('#add-new-technique-button').on('click', function () {
			$('#editTitle').html('<strong>Add New Assessment Technique</strong>');
			openNewAssessmentTechniqueModal();
		});

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

		// Listen for the selected learning domain to change, when it does call ajax
		$('#learning-domain').on(
			'change',
			function () {
				populateDomainCategories(function () {});
			});
	}
);
