'use strict';

// Start at the beggining of the path, get the first '/' then and all the characters between that and the second '/'
var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var prevKeyword = '';

/**
 * Ajax to pull Action Words based on which Action Word Category was selected,
 * then populates a select tag with action words
 * @param  {String} domain text from the action word category boxes
 * @return {XML}		Populates the page with action words
 */
function populateActionWords (event) {
	if (prevKeyword === $('.icons').find('.ui-state-active').text()) {
		return;
	}
	else {
		prevKeyword = $('.icons').find('.ui-state-active').text();
	}

	$.ajax({
		url: baseUrl + 'learningObjective/getActionWords',
		type: 'GET',
		dataType: 'json',
		data: {
			actionWordCategory: $('.icons').find('.ui-state-active').text().trim()
		},
		// FIXME simplify complex function
		success: function (data) {
			var actionWordsHTML = '';
			var flag = 0;

			if (event === true) {
				var originalActionWord = $('#action-words').val();
			}

			// Store the data from the call back
			if (data.value !== null) {
				if (data.value.verb !== undefined && data.value.verb !== null && data.value.verb !== '') {
					var actionWordsVerb = data.value.verb.syn;

					// This will store the html for the action words
					// For each action word
					for (var i = 0; i < actionWordsVerb.length; i++) {
						// Create the html for the action word
						if (actionWordsVerb[i] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsVerb[i] + '">' + actionWordsVerb[i] + '</option>';
						}
						else {
							actionWordsHTML += '<option value="' + actionWordsVerb[i] + '">' + actionWordsVerb[i] + '</option>';
						}
					}
				}

				if (data.value.noun !== undefined && data.value.noun !== null && data.value.noun !== '') {
					var actionWordsNoun = data.value.noun.syn;
					for (i = 0; i < actionWordsNoun.length; i++) {
						// Create the html for the action word
						if (actionWordsNoun[i] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsNoun[i] + '">' + actionWordsNoun[i] + '</option>';
						}
						else {
							actionWordsHTML += '<option value="' + actionWordsNoun[i] + '">' + actionWordsNoun[i] + '</option>';
						}
					}
				}

				if (data.value.adjective !== undefined && data.value.adjective !== null && data.value.adjective !== '') {
					var actionWordsAdj = data.value.adjective.syn;
					for (i = 0; i < actionWordsAdj.length; i++) {
						// Create the html for the action word
						if (actionWordsAdj[i] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsAdj[i] + '">' + actionWordsAdj[i] + '</option>';
						}
						else {
							actionWordsHTML += '<option value="' + actionWordsAdj[i] + '">' + actionWordsAdj[i] + '</option>';
						}
					}
				}
			}

			if (flag === 0 && event === true) {
				actionWordsHTML += '<option selected value="other"> --Other-- </option>';
				$('#custom-action-words').css('visibility', 'visible');
				$('input#custom-action-words').val(originalActionWord);
			}
			else {
				actionWordsHTML += '<option value="other"> --Other-- </option>';
				$('#custom-action-words').css('visibility', 'hidden');
			}
			// Display the html for the action words
			$('#action-words').html(actionWordsHTML);
		},
		error: function (xhr) {
			// When something goes wrong log to the browser console
			console.log(xhr.responseText);
		}
	});
}

/**
 * Ajax to pull Action Words based on which Domain Category was selected,
 * then populate page with selectable action word category boxes
 * @param  {String} domain text from the domain category select box
 * @return {XML}		Populates the page with action word categories
 */
function populateActionWordCategories () {
	$.ajax({
		url: baseUrl + 'learningObjective/getActionWordCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#domain-category-list').val().trim()
		},
		success: function (data) {
			// Store the data from the call back
			var actionWordCategories = data.value;
			// This will temporarily store the html for the categories
			var actionWordCategoriesHTML = '';
			// For each category
			for (var i = 0; i < actionWordCategories.length; i++) {
				// Create the html
				actionWordCategoriesHTML += '<input type="radio" id="radio' + i + '" name="actionWordCategory" value="' + actionWordCategories[i].actionWordCategory + '"><label for="radio' + i + '">' + actionWordCategories[i].actionWordCategory + '</label>';
			}
			// Display the html on the page
			$('.icons').html(actionWordCategoriesHTML);
			// Since the markup is reloaded, re-initiate buttonset
			$('.icons').buttonset();

			populateActionWords();
		},
		error: function (xhr) {
			// When something goes wrong log to the browser console
			console.log(xhr.responseText);
		}
	});
}


/**
 * Ajax to pull domain categories based on which Learning Domain was selected,
 * then populate the select box with the domain categories
 * @param  {String} domain text from the domain select box
 * @return {XML}		Populates the domain category box with options
 */
function populateDomainCategories () {
	if ($('#learning-domain-list').val() !== 'null') {
		$('label[for="domain-category-list"]').css('visibility', 'visible');
		$('#domain-category-list').css('visibility', 'visible');
		$('.icons').css('visibility', 'visible');
		$('#action-words').css('visibility', 'visible');
	}
	else {
		$('label[for="domain-category-list"]').css('visibility', 'hidden');
		$('#domain-category-list').css('visibility', 'hidden');
		$('.icons').css('visibility', 'hidden');
		$('#action-words').css('visibility', 'hidden');
		$('#custom-action-words').css('visibility', 'hidden');
	}

	$.ajax({
		url: baseUrl + 'learningObjective/getDomainCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#learning-domain-list').val().trim()
		},
		success: function (data) {
			// Stores the data from the call back
			var categories = data.value;
			// This stores the new html that will be added
			var options = '';
			// For each of the categories
			for (var i = 0; i < categories.length; i++) {
				// Create the html for the category
				options += '<option value="' + categories[i].name + '">' + categories[i].name + '</option>';
			}
			// Store this to the page
			$('#domain-category-list').html(options);

			populateActionWordCategories();
		},
		error: function (xhr) {
			// When something goes wrong log to the browser console
			console.log(xhr.responseText);
		}
	});
}

// Add the data to the definition box, depending on which type of data it is
function propagateToDefinition (value, type) {
	var definitionType = '.learning-objective-current .learning-objective-' + type;
	$(definitionType).text(value);
}

function getMinHeight (liArray) {
	var minHeight = Math.floor(liArray.eq(0).height());
	liArray.each(
		function () {
			if (Math.floor($(this).height()) < minHeight) {
				minHeight = Math.floor($(this).height());
			}
			var refineText = $('a', this).text().replace(/[\s\t]+/g, ' ');
			$('a', this).text(refineText);
		}
	);
	return minHeight;
}

// On page load
$(document).ready(
	function () {
		$('#custom-action-words').css('visibility', 'hidden');

		$('#action-words').change(
			function () {
				if (this.value === 'other') {
					$('#custom-action-words').css('visibility', 'visible');
				}
				else {
					$('#custom-action-words').css('visibility', 'hidden');
				}
			}
		);

		$(':button').hover(
			function () {
				$('#qtip-place').html($(this).attr('title'));
			},
			function () {
				$('#qtip-place').html('');
			}
		);

		// Initially domain categories and action words will not be displayed as learning domain is null
		if ($('#domain-category-list').val() === 'null') {
			$('label[for="domain-category-list"]').css('visibility', 'hidden');

			$('#domain-category-list').css('visibility', 'hidden');
			$('.icons').css('visibility', 'hidden');
			$('#action-words').css('visibility', 'hidden');
		}

		$('.learning-objective.definition-display').click(
			function () {
				$('.learning-objective.definition-display').removeClass('active');
				$('.learning-objective.definition-edit').addClass('active');
				$('.learning-objective.definition-edit #customDefinition').focus();
			}
		);

		$('.learning-objective.definition-edit #customDefinition').blur(
			function () {
				$('.learning-objective.definition-display').addClass('active');
				$('.learning-objective.definition-edit').removeClass('active');
				$('.learning-objective.definition-edit').submit();
			}
		);

		// Listen for the selected learning domain to change, when it does call ajax
		$('#learning-domain-list').on(
			'change',
			function () {
				populateDomainCategories();
			});

		// Listen for the selected domain category to change, when it does call ajax
		$('#domain-category-list').on(
			'change',
			function () {
				populateActionWordCategories();
			});

		// Listen for change in action word categories, when it does call ajax
		$('.icons').on(
			'click',
			function () {
				populateActionWords();
			});

		// If the condition is set to hidden do not display it in the definition box above
		$('#LO-hide-from-Objective').change(
			function () {
				if (this.checked) {
					$('.learning-objective-current .learning-objective-condition').css('display', 'none');
				}
				else {
					$('.learning-objective-current .learning-objective-condition').css('display', 'inline');
				}
			}
		);

		// When a custom condition is added, display in the definition box above
		$('#custom-condition-text').keyup(
			propagateToDefinition(this.value, 'condition')
		);

		// When a standard condition is added, display in the definition box above
		$('.learning-objective.condition.generic.text').change(
			propagateToDefinition(this.value, 'condition')
		);

		$('input:radio[name=conditionType]').on(
			'change',
			function () {
				if (this.value === 'Generic') {
					$('#custom-condition-text').css('display', 'none');
					$('.learning-objective.condition.generic').css('display', 'block');
				}
				else {
					$('.learning-objective.condition.generic').css('display', 'none');
					$('#custom-condition-text').css('display', 'block');
				}
			}
		);

		// Manually tiggers the radio box change event
		$('input:radio[name=conditionType]:checked').change();

		// Trigger jquery ui button for better radio buttons
		var category = $('input[name=selectedActionWordCategory]').val();
		$('.icons input[value="' + category + '"]').prop('checked', true);
		$('.icons').buttonset();

		// Populate action words if a category is already selected
		populateActionWords(true);

		// Reset radio buttons if a selected radio button is clicked again
		$(document).on(
			'click',
			'.icons label',
			function () {
				if ($(this).hasClass('is-active')) {
					$('.icons label').removeClass('is-active');
					setTimeout(
						function () {
							$('.icons input:checked').removeAttr('checked').button('refresh');
						},
						0
					);
				}
				else {
					$('.icons label').removeClass('is-active');
					$(this).addClass('is-active');
				}
			}
		);

		// When the checkbox is changed disable text box and other check box
		$('#enable-accuracy').on(
			'click',
			function () {
				$('#accuracy-text').prop(
					'disabled', !$('#enable-accuracy').is(':checked')
				);
				$('#accuracy-text').value('');
				$('#accuracy-hide').prop(
					'disabled', !$('#enable-accuracy').is(':checked')
				);
			}
		);

		// When the checkbox is changed disable text box and other check box
		$('#enable-quality').on(
			'click',
			function () {
				$('#quality-text').prop(
					'disabled', !$('#enable-quality').is(':checked')
				);
				$('#quality-text').value('');
				$('#quality-hide').prop(
					'disabled', !$('#enable-quality').is(':checked')
				);
			}
		);

		// When the checkbox is changed disable text box and other check box
		$('#enable-quantity').on(
			'click',
			function () {
				$('#quantity-text').prop(
					'disabled', !$('#enable-quantity').is(':checked')
				);
				$('#quantity-text').value('');
				$('#quantity-hide').prop(
					'disabled', !$('#enable-quantity').is(':checked')
				);
			}
		);

		// When the checkbox is changed disable text box and other check box
		$('#enable-speed').on(
			'click',
			function () {
				$('#speed-text').prop(
					'disabled', !$('#enable-speed').is(':checked')
				);
				$('#speed-text').value('');
				$('#speed-hide').prop(
					'disabled', !$('#enable-speed').is(':checked')
				);
			}
		);

		// When learning domain isn't selected, do not save learning objective
		$('.learning-objective-button.save').click(
			function () {
				if ($('#learning-domain-list').val() === 'null' || $('#domain-category-list').val() === 'null' || $('input[name=actionWordCategory]').is(':checked') === false) {
					alert('Learning Domain, Domain Category and Action Word Categories are required');
					return false;
				}
			}
		);

		// When hovered over LO side-tab list, it displays full text as tool-tip
		var $liArray = $('ul.learning-objective.list-wrapper').children('li');
		var height = getMinHeight($liArray);

		$liArray.each(
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
