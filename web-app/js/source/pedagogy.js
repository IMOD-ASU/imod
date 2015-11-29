var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];

/**
 * Opens the modal to create a new pedagogy technique
 */
function openNewPedagogyTechniqueModal () {
	'use strict';

	$('#techniqueId').val('');
	$('#add-new-technique').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}

function repopulateCheckboxes () {
	'use strict';
	var checkboxValues = $.cookie('checkboxValues');

	if (checkboxValues) {
		Object.keys(checkboxValues).forEach(function (element) {
			var checked = checkboxValues[element];

			$('#' + element).prop('checked', checked);
		});
	}
}
function openInstructionalPlanModal () {
	'use strict';
	var instructionPlanBlock = $('#instruction-plan-accordion').find('.istructional-plan-LO');

	instructionPlanBlock.each(function () {
		var spanHTML = $(this).find('span').clone().wrap('<p>').parent().html();

		$(this).html(spanHTML + truncateString($(this).text(), 80));
	});
	$('#instruction-plan').css('display', 'block');
	$('#topicDialogBackground').css('display', 'block');
}

function populateInstructionalPlanTechniques () {
	'use strict';
	var data;
	var index;
	var assignedTechniques = '';
	var headerText = '';
	var spanHTML = '';
	var accordionOpened = $('#instruction-plan-accordion').find('.ui-state-active').length;

	headerText = $(this).text();

	if (accordionOpened === 1) {
	// Bundle the data into an object
		data = {
			learningObjectiveID: $(this).attr('id')
		};

		$.ajax({
			url: '../findAssignedTechniques',
			method: 'post',
			data: JSON.stringify(data),
			contentType: 'application/json'
		}).done(function (data) {
			if (data.LOPedagogyTechniques.length > 0) {
				for (index = 0; index < data.LOPedagogyTechniques.length; index++) {
					assignedTechniques += '<li>' + data.LOPedagogyTechniques[index] + '</li>';
				}
				$('#assignedTechniques-' + data.currentLearningObjectiveID).html('<ul>' + assignedTechniques + '</ul>');
			} else {
				$('#assignedTechniques-' + data.currentLearningObjectiveID).html('No techniques are assigned to this Learning Objective');
			}
			spanHTML = $('#' + data.currentLearningObjectiveID).find('span').clone().wrap('<p>').parent().html();

			$('#' + data.currentLearningObjectiveID).html(spanHTML + data.currentLearningObjective);
		});
	} else {
		spanHTML = $('#' + $(this).attr('id')).find('span').clone().wrap('<p>').parent().html();
		$('#' + $(this).attr('id')).html(spanHTML + truncateString(headerText, 80));
	}
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
function populatePedagogyTechnique (data) {
	'use strict';
	var currentTechnique = data.pedagogyTechnique;
	var count;
	var arrayOfKnowledgeDimensions = data.knowledgeDimension.split(',');
	var arrayOfLearningDomains = data.learningDomains.split(',');
	var arrayOfDomainCategories = data.domainCategories.split(',');
	var checked = '';
	var cloneDetect = document.getElementById('cloneDetect').value;

	$('#titlecheck').val(currentTechnique.title);
	if (cloneDetect === 'clone') {
		$('#editTitle').html('<b>Enter Alternate Name for Clone</b>');
		$('#title').val('');
	} else {
		$('#editTitle').html('<b>Edit Pedagogy Technique</b>');
		$('#title').val(currentTechnique.title);
	}
	$('#learningDomain option[value="null"]').attr('disabled', 'disabled');
	$('#domainCategory option[value="null"]').attr('disabled', 'disabled');
	// $('#location').val(currentTechnique.location);
	$('#duration').val(currentTechnique.direction);
	$('#materials').val(currentTechnique.materials);
	$('#reference').val(currentTechnique.reference);
	// $('#strategyDescription').val(currentTechnique.strategyDescription);
	$('#activityDescription').val(currentTechnique.activityDescription);
	for (count = 0; count < arrayOfKnowledgeDimensions.length; count++) {
		if (arrayOfKnowledgeDimensions[count] !== '') {
			$('#' + arrayOfKnowledgeDimensions[count]).prop('checked', true);
		}
	}
	for (count = 0; count < arrayOfLearningDomains.length; count++) {
		if (arrayOfLearningDomains [count] !== '') {
			$('#learningDomain option[value=' + arrayOfLearningDomains[count] + ']').attr('selected', 'selected');
		}
	}
	for (count = 0; count < arrayOfDomainCategories.length; count++) {
		if (arrayOfDomainCategories [count] !== '') {
			$('#domainCategory option[value = ' + arrayOfDomainCategories[count] + ']').attr('selected', 'selected');
		}
	}

	// Choose correct item from selectables
	// $('#learningDomain option[value=' + data.learningDomain + ']').prop('selected', true);
	$('#selectKnowledgeDimensions input[type=checkbox]').each(function () {
		if ($(this).is(':checked')) {
			checked = checked + ($(this).val()) + ',';
		}
	});
	document.getElementById('knowledgeDimension').value = checked;
	$('#pedagogyFocus option[value=' + data.activityFocus + ']').prop('selected', true);
	$('#pedagogyMode option[value=' + data.pedagogyMode + ']').prop('selected', true);
	$('#pedagogyDuration option[value = "' + data.pedagogyDuration + '"]').prop('selected', true);
	$('#domainCategory option[value= "' + data.domainCategory + '"]').attr('selected', 'selected');
}
function displayPedagogyInformationInEdit () {
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
		url: '../../pedagogyTechnique/get/' + res,
		method: 'GET'
	})
	.done(populatePedagogyTechnique);
}
$('#title').change(function () {
	'use strict';
	var hasError = false;

	if ($('#title').val() === $('#titlecheck').val()) {
		$('#errorMessage').text('Enter title which is different from the original technique');
		hasError = true;
	} else {
		hasError = false;
	}
	if (hasError === true) {
		return false;
	}
});

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
	var favoriteImgToggle = '';
	var assignImgToggle = '';

	// Take the titles and make html code to display
	for (index = 0; index < data.idealPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.idealPedagogyTechniqueMatch[index];
		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOPedagogyTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}

		idealText += '<input type="radio" id="' + currentTechnique.id + '" name="pedagogyTechnique" value="' + currentTechnique.id + '">';
		idealText += '<label class="pedagogy-block" for="' + currentTechnique.id + '"><div class="favorite" id="topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div class="assign" id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" class="text-block title" id="titleDiv"><span>' + truncateString(currentTechnique.title, 100) + '</span><button class="clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
	}

	// Take the titles and make html code to display
	for (index = 0; index < data.extendedPedagogyTechniqueMatch.length; index++) {
		currentTechnique = data.extendedPedagogyTechniqueMatch[index];
		if (data.favoriteTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			favoriteImgToggle = '../../images/fav.png';
		} else {
			favoriteImgToggle = '../../images/unfav.png';
		}

		if (data.LOPedagogyTechniques.indexOf(currentTechnique.id.toString()) > -1) {
			assignImgToggle = '../../images/assign.png';
		} else {
			assignImgToggle = '../../images/unassign.png';
		}
		extendedText += '<input type="radio" id="' + currentTechnique.id + 'Extended" name="pedagogyTechniqueExtended" value="' + currentTechnique.id + '">';
		extendedText += '<label class="pedagogy-block" for="' + currentTechnique.id + 'Extended"><div id="topLeft"><img src="' + favoriteImgToggle + '"/>' +
					'</div><div id="topRight"><img src="' + assignImgToggle + '" /></div><div title="' + currentTechnique.title + '" id="titleDiv" class="text-block"><span>' +
					truncateString(currentTechnique.title, 100) + '</span><button class="clone"><i class="fa fa-clone blue"></i> Clone</button><span></span></div></label>';
	}

	// Add html code to the page
	$('#ideal-matches').html(idealText);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches').buttonset();

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
				url: '../../pedagogyTechnique/unassignFavorite/' + res,
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
				url: '../../pedagogyTechnique/assignFavorite/' + res,
				method: 'GET',
				success: function () {}
			});
		}
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
				pedagogyTechniqueID: res
			};
			$.ajax({
				url: '../../pedagogyTechnique/unassignToLearningObjective',
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
				pedagogyTechniqueID: res
			};
			$.ajax({
				url: '../../pedagogyTechnique/assignToLearningObjective',
				type: 'POST',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function () {}
			});
		}
	});

	$('.title span').click(function () {
		$('#add-new-technique').css('display', 'block');
		$('#topicDialogBackground').css('display', 'block');
		displayPedagogyInformationInEdit();
	});

	$('#extended-matches').buttonset();
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
		selectedDomainCategories: selectedDomainCategoriesData,
		learningObjectiveID: $('#learningObjectiveID').val()
	};

	// Send the data to the find matching techniques action in grails
	// and process the response with the display pedagogy techniques callback
	$.ajax({
		url: '../findMatchingTechniques',
		method: 'post',
		data: JSON.stringify(data),
		contentType: 'application/json'
	}).done(function (data) {
		displayPedagogyTechniques(data);
		pedagogyEqualHeights('#ideal-matches');
		pedagogyEqualHeights('#extended-matches');
	});
}

function truncateString (string, count) {
	'use strict';
	if (string.length > count) {
		return string.substring(0, count) + '...';
	}

	return string;
}

function pedagogyEqualHeights (parent) {
	'use strict';
	var max = 0;
	var isOpen = false;
	var parentBlock = $(parent);
	var pedagogyBlock = parentBlock.find('.pedagogy-block');

	if (parentBlock.css('display') === 'none') {
		parentBlock.show();
		isOpen = true;
	}

	pedagogyBlock.each(function () {
		var height = $(this).height();

		if (max < height) {
			max = height;
		}
	});
	pedagogyBlock.height(max);
	if (isOpen) {
		parentBlock.hide();
	}
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

/* Populate the bread crumbs above ideal matches*/
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

		$.cookie.json = true;
		repopulateCheckboxes();

		// Load techniques on page load
		filterPedagogyTechniques();
		// The filters for the pedagogy technique are wrapped in a accordian
		// beforeActivate is to be able to open both ideal & extended matches simultaneously
		$('#filter-pedagogy-techniques').accordion({collapsible: true, heightStyle: 'content'});
		$('#instruction-plan-accordion').accordion({collapsible: true, heightStyle: 'content', active: false});
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
		$(document).on('click', '.clone', function () {
			$('#add-new-technique').css('display', 'block');
			$('#topicDialogBackground').css('display', 'block');
			document.getElementById('cloneDetect').value = 'clone';
			displayPedagogyInformationInEdit();
		});

		// Attach a listener to the checkboxes, to update the pedaogy techniques
		// when the filters have been changed
		$('input[name=knowledgeDimension]').on('change',
		function () {
			checkBoxName = 'knowledgeDimension';
			updateTextArea(checkBoxName);
			filterPedagogyTechniques();
		});
		$('input[name=learningDomain]').on('change',
		function () {
			checkBoxName = 'learningDomain';
			updateTextArea(checkBoxName);
			filterPedagogyTechniques();
		});
		$('input[name=domainCategory]').on('change',
		function () {
			checkBoxName = 'domainCategory';
			updateTextArea(checkBoxName);
			filterPedagogyTechniques();
		});

		// fetching checkboxes and making a cookie
		$('input[name=knowledgeDimension],input[name=learningDomain],input[name=domainCategory]').on('change', function () {
			var checkboxValues = {};

			$('input[name=knowledgeDimension],input[name=learningDomain],input[name=domainCategory]').each(function () {
				checkboxValues[this.id] = this.checked;
			});
			$.cookie('checkboxValues', checkboxValues);
		});

		$('.learning-objective.list-item,#a,#b,#c,#logout-link,.banner-imod,.banner-home,#imodlogo,.no-objective-defined').on('click',
			function () {
				$.removeCookie('checkboxValues');
			}
		);

		$('#saveButton').on('click',
		function () {
			var tp = '';
			var temp = '';
			var cloneDetect = document.getElementById('cloneDetect').value;

			if ($('#title').val() === '') {
				$('#errorMessage').text('Technique must have a title!');
				hasError = true;
			} else if ($('#learningDomain').val() === '' || $('#learningDomain').val() === null) {
				$('#errorMessage').text('Learning Domains are required');
				hasError = true;
			} else if ($('#domainCategory').val() === '' || $('#domainCategory').val() === null) {
				$('#errorMessage').text('Domain Categories are required');
				hasError = true;
			} else if ($('#knowledgeDimension').val() === '') {
				$('#errorMessage').text('Knowledge Dimensions are required!');
				hasError = true;
			} else {
				$('#learningDomain :selected').each(function (identifier, selected) {
					tp = tp + $(selected).text() + ',';
				});
				$('#domainCategory :selected').each(function (identifier, selected) {
					temp = temp + $(selected).text() + ',';
				});
				document.getElementById('domainSelected').value = tp;
				document.getElementById('domainCategorySelected').value = temp;
				hasError = false;
			}
			if ($('#title').val() === $('#titlecheck').val() && cloneDetect === 'clone') {
				$('#errorMessage').text('Enter title which is different from the original technique');
				hasError = true;
			}
			if (hasError === true) {
				return false;
			}
		});

		// When add new technique button is clicked open modal
		$('#add-new-technique-button').on('click', openNewPedagogyTechniqueModal);

		// When instructional plan button is clicked open modal
		$('#instruction-plan-button').on('click', openInstructionalPlanModal);

		$('#instruction-plan-accordion h3').on('click', populateInstructionalPlanTechniques);

		$('#closeInstructionalPlan').on('click',
			function () {
				$('#instruction-plan').css('display', 'none');
				$('#topicDialogBackground').css('display', 'none');
			}
		);

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

$(window).ready(function () {
	'use strict';
	pedagogyEqualHeights('#ideal-matches');
	pedagogyEqualHeights('#extended-matches');
});
