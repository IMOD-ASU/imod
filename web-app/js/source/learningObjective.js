// Start at the beggining of the path, get the first '/' then and all the characters between that and the second '/'
var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var prevKeyword = '';
var dirtyContentTree = false;

/**
 * Ajax to pull Action Words based on which Action Word Category was selected,
 * then populates a select tag with action words
 * @param  {String} domain text from the action word category boxes
 * @return {XML}        Populates the page with action words
 */
function populateActionWords (event) {
	'use strict';
	if (prevKeyword === $('.icons').find('.ui-state-active').text()) {
		return;
	}
	prevKeyword = $('.icons').find('.ui-state-active').text();

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
			var originalActionWord;
			var actionWordsVerb;
			var actionWordsNoun;
			var actionWordsAdj;
			var index;
			var word;
			var indexOf = -1;
			var duplicateWords = [];

			if (event === true) {
				originalActionWord = $('#action-words').val();
			}
			actionWordsHTML += '<option selected value="select"> --Select-- </option>';
			// Store the data from the call back
			if (data.value !== null) {
				if (data.value.verb && data.value.verb !== '') {
					actionWordsVerb = data.value.verb.syn.sort();

					// find british and english words for "ise" "ize"
					for (index = 0; index < actionWordsVerb.length; index++) {
						if (actionWordsVerb[index].substr(actionWordsVerb[index].length - 3) === 'ize') {
							word = actionWordsVerb[index].substring(0, actionWordsVerb[index].length - 3);
							word += 'ise';
							indexOf = actionWordsVerb.indexOf(word);
							if (indexOf > 0) {
								duplicateWords.push(indexOf);
							}
						}
					}

					// remove the duplicates
					for (index = duplicateWords.length - 1; index >= 0; index--) {
						actionWordsVerb.splice(duplicateWords[index], 1);
					}

					// This will store the html for the action words
					// For each action word
					for (index = 0; index < actionWordsVerb.length; index++) {
						// Create the html for the action word
						if (actionWordsVerb[index] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsVerb[index] + '">' + actionWordsVerb[index] + '</option>';
						} else {
							actionWordsHTML += '<option value="' + actionWordsVerb[index] + '">' + actionWordsVerb[index] + '</option>';
						}
					}
				}

				if (data.value.noun && data.value.noun !== '') {
					actionWordsNoun = data.value.noun.syn.sort();
					for (index = 0; index < actionWordsNoun.length; index++) {
						// Create the html for the action word
						if (actionWordsNoun[index] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsNoun[index] + '">' + actionWordsNoun[index] + '</option>';
						} else {
							actionWordsHTML += '<option value="' + actionWordsNoun[index] + '">' + actionWordsNoun[index] + '</option>';
						}
					}
				}

				if (data.value.adjective && data.value.adjective !== '') {
					actionWordsAdj = data.value.adjective.syn.sort();
					for (index = 0; index < actionWordsAdj.length; index++) {
						// Create the html for the action word
						if (actionWordsAdj[index] === originalActionWord) {
							flag = 1;
							actionWordsHTML += '<option selected value="' + actionWordsAdj[index] + '">' + actionWordsAdj[index] + '</option>';
						} else {
							actionWordsHTML += '<option value="' + actionWordsAdj[index] + '">' + actionWordsAdj[index] + '</option>';
						}
					}
				}
			}

			if (flag === 0 && event === true) {
				actionWordsHTML += '<option selected value="other"> --Other-- </option>';
				$('#custom-action-words').css('visibility', 'visible');
				$('input#custom-action-words').val(originalActionWord);
			} else {
				actionWordsHTML += '<option value="other"> --Other-- </option>';
				$('#custom-action-words').css('visibility', 'hidden');
			}
			// Display the html for the action words
			$('#action-words').html(actionWordsHTML);
		}
	});
}

/**
 * Ajax to pull Action Words based on which Domain Category was selected,
 * then populate page with selectable action word category boxes
 * @param  {String} domain text from the domain category select box
 * @return {XML}        Populates the page with action word categories
 */
function populateActionWordCategories () {
	'use strict';
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
			var index;

			// For each category
			for (index = 0; index < actionWordCategories.length; index++) {
				// Create the html
				actionWordCategoriesHTML += '<input type="radio" id="radio' + index + '" name="actionWordCategory" value="' + actionWordCategories[index].actionWordCategory + '">';
				actionWordCategoriesHTML += '<label for="radio' + index + '">' + actionWordCategories[index].actionWordCategory + '</label>';
			}
			// Display the html on the page
			$('.icons').html(actionWordCategoriesHTML);
			// Since the markup is reloaded, re-initiate buttonset
			$('.icons').buttonset();

			populateActionWords();
		}
	});
}

/**
 * Ajax to pull domain categories based on which Learning Domain was selected,
 * then populate the select box with the domain categories
 * @param  {String} domain text from the domain select box
 * @return {XML}        Populates the domain category box with options
 */
function populateDomainCategories () {
	'use strict';
	if ($('#learning-domain-list').val() === 'null') {
		$('label[for="domain-category-list"]').css('visibility', 'hidden');
		$('#domain-category-list').css('visibility', 'hidden');
		$('.icons').css('visibility', 'hidden');
		$('#action-words').css('visibility', 'hidden');
		$('#custom-action-words').css('visibility', 'hidden');
		$('label[for="action-word-category"]').css('visibility', 'hidden');
		$('label[for="action-words"]').css('visibility', 'hidden');
	} else {
		$('label[for="domain-category-list"]').css('visibility', 'visible');
		$('#domain-category-list').css('visibility', 'visible');
		$('.icons').css('visibility', 'visible');
		$('#action-words').css('visibility', 'visible');
		$('label[for="action-word-category"]').css('visibility', 'visible');
		$('label[for="action-words"]').css('visibility', 'visible');
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
			var index;

			// For each of the categories
			for (index = 0; index < categories.length; index++) {
				// Create the html for the category
				options += '<option value="' + categories[index].name + '">' + categories[index].name + '</option>';
			}
			// Store this to the page
			$('#domain-category-list').html(options);

			populateActionWordCategories();
		}
	});
}

// Add the data to the definition box, depending on which type of data it is
function propagateToDefinition (value, type) {
	'use strict';
	var definitionType = '.learning-objective-current .learning-objective-' + type;

	$(definitionType).text(value);
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

function deleteTopicSubTab (contentIDs) {
	'use strict';
	$.ajax({
		url: '../../content/deleteTopic/',
		type: 'GET',
		dataType: 'json',
		data: {
			contentIDs: JSON.stringify(contentIDs)
		},
		success: function () {
			dirtyContentTree = false;
			window.location.reload();
		}
	});
}

function getTreeChildren (list, parents, idArray) {
	'use strict';
	list.each(function () {
		var item = {};
		var myItem = $(this);
		var childrenArr;
		var children;

		item.id = myItem.data('itemid');
		item.isChecked = myItem
			.find('.sub-content-tree')
			.find('.checkbox')
			.hasClass('fa-check');
		if (item.isChecked) {
			idArray.push(item.id);
		}

		if ($(this).find('> ul > li').length) {
			childrenArr = [];
			children = getTreeChildren(myItem.find('> ul > li'), childrenArr, idArray);
			item.child = children[0];
		} else {
			item.child = '';
		}

		parents.push(item);
	});

	return [parents, idArray];
}

// On page load
$(document).ready(
	function () {
		'use strict';

		var category;
		var liArray;
		var height;

		$('#custom-action-words').css('visibility', 'hidden');

		$('#please-select-action-word').dialog({
			autoOpen: false
		});

		$('#learning-objective-missing-required').dialog({
			autoOpen: false
		});

		$('#confirm-remove-content').dialog({
			autoOpen: false,
			buttons: {
				yes: function () {
					$(this).dialog('close');
					deleteTopicSubTab([$('.delete-topic').data('id')]);
				},
				no: function () {
					$(this).dialog('close');
				}
			}
		});

		$('#action-words').change(
			function () {
				if (this.value === 'other') {
					$('#custom-action-words').css('visibility', 'visible');
				} else {
					$('#custom-action-words').css('visibility', 'hidden');
				}
			}
		);

		$('#custom-action-words').click(function () {
			if ($('#custom-action-words').val() === 'Enter the details here') {
				$('#custom-action-words').val('');
			}
		});

		if ($('#contentTree').length) {
			// $('#contentTree').sortable();
			$('#contentTree').sortable({
				onDrop: function (item, container) {
					item.removeClass(container.group.options.draggedClass).removeAttr('style');
					$('body').removeClass(container.group.options.bodyClass);
				}
			});
			$('.delete-topic').click(function () {
				$('#confirm-remove-content').dialog('open');
				return false;
			});

			$('.sub-content-tree').click(function () {
				var item = $(this);

				if (dirtyContentTree === false) {
					dirtyContentTree = true;
				}

				if (item.find('.checkbox').hasClass('fa-check')) {
					item.parent().find('.checkbox').removeClass('fa-check');
				} else {
					item.parent().find('.checkbox').addClass('fa-check');
				}

				return false;
			});

			window.onbeforeunload = function () {
				if (dirtyContentTree === true) {
					return 'You have unsaved changes. Please save them before proceeding.';
				}
				return null;
			};

			$('#save-content').click(function () {
				var parents = [];
				var idArray = [];
				var content = getTreeChildren($('#contentTree > li'), parents, idArray);
				var obj = {
					topics: content[0],
					idArray: content[1],
					objId: $('input[name=learningObjectiveID]').val()
				};

				$.ajax({
					url: '../../content/updateHierarchy',
					type: 'POST',
					dataType: 'json',
					contentType: 'application/json; charset=utf-8',
					data: JSON.stringify(obj),
					success: function () {
						dirtyContentTree = false;
						window.location.reload();
					}
				});
			});
		}

		$(':button,.content,.fa.fa-pencil,.criteria').hover(
			function () {
				// $('#qtip-place').html($(this).attr('title'));
			},
			function () {
				// $('#qtip-place').html('');
			}
		);

		// Initially domain categories and action words will not be displayed as learning domain is null
		if ($('#domain-category-list').val() === 'null') {
			$('label[for="domain-category-list"]').css('visibility', 'hidden');
			$('label[for="action-word-category"]').css('visibility', 'hidden');
			$('label[for="action-words"]').css('visibility', 'hidden');
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
				if ($('#learning-domain-list').val() === 'Cognitive') {
					$('#learning-domain-list').tooltipster('content', 'Mental skills (knowledge)');
				} else if ($('#learning-domain-list').val() === 'Affective') {
					$('#learning-domain-list').tooltipster('content', 'Growth in feelings or emotional areas (attitude or self)');
				} else if ($('#learning-domain-list').val() === 'Psychomotor') {
					$('#learning-domain-list').tooltipster('content', 'Manual or physical skill (skills)');
				} else {
					$('#learning-domain-list').tooltipster('content', 'Select one of the three Learning domains from Bloom\'s Taxonomy: Cognitive, Affective, and Psychomotor. Reference URL: http://www.nwlink.com/~donclark/hrd/bloom.html');
				}
				populateDomainCategories();
			});


		// Listen for the selected domain category to change, when it does call ajax
		$('#domain-category-list').on(
			'change',
			function () {
				switch ($('#domain-category-list').val()) {
					case 'Remember':
						$('#domain-category-list').tooltipster('content', 'Recall or retrieve previous learned information.');
						break;
					case 'Understand':
						$('#domain-category-list').tooltipster('content', 'Comprehending the meaning, translation, interpolation, and interpretation of instructions and problems. State a problem in one\'s own words.');
						break;
					case 'Apply':
						$('#domain-category-list').tooltipster('content', 'Use a concept in a new situation or unprompted use of an abstraction. Applies what was learned in the classroom into novel situations in the work place.');
						break;
					case 'Analyze':
						$('#domain-category-list').tooltipster('content', 'Separates material or concepts into component parts so that its organizational structure may be understood. Distinguishes between facts and inferences.');
						break;
					case 'Evaluate':
						$('#domain-category-list').tooltipster('content', 'Make judgments about the value of ideas or materials.');
						break;
					case 'Create':
						$('#domain-category-list').tooltipster('content', 'Builds a structure or pattern from diverse elements. Put parts together to form a whole, with emphasis on creating a new meaning or structure.');
						break;
					case 'Receiving Phenomena':
						$('#domain-category-list').tooltipster('content', 'Awareness, willingness to hear, selected attention.');
						break;
					case 'Respond':
						$('#domain-category-list').tooltipster('content', 'Active participation on the part of the learners. Attend and react to a particular phenomenon. Learning outcomes may emphasize compliance in responding, willingness to respond, or satisfaction in responding (motivation).');
						break;
					case 'Value':
						$('#domain-category-list').tooltipster('content', 'The worth or value a person attaches to a particular object, phenomenon, or behavior. This ranges from simple acceptance to the more complex state of commitment. Valuing is based on the internalization of a set of specified values, while clues to these values are expressed in the learner\'s overt behavior and are often identifiable.');
						break;
					case 'Organization':
						$('#domain-category-list').tooltipster('content', 'Organizes values into priorities by contrasting different values, resolving conflicts between them, and creating an unique value system. The emphasis is on comparing, relating, and synthesizing values.');
						break;
					case 'Internalizing Values':
						$('#domain-category-list').tooltipster('content', 'Has a value system that controls their behavior. The behavior is pervasive, consistent, predictable, and most important characteristic of the learner. Instructional objectives are concerned with the student\'s general patterns of adjustment (personal, social, emotional).');
						break;
					case 'Perception':
						$('#domain-category-list').tooltipster('content', 'The ability to use sensory cues to guide motor activity.  This ranges from sensory stimulation, through cue selection, to translation.');
						break;
					case 'Set':
						$('#domain-category-list').tooltipster('content', 'Readiness to act. It includes mental, physical, and emotional sets. These three sets are dispositions that predetermine a person\'s response to different situations (sometimes called mindsets).');
						break;
					case 'Guided Response':
						$('#domain-category-list').tooltipster('content', 'The early stages in learning a complex skill that includes imitation and trial and error. Adequacy of performance is achieved by practicing.');
						break;
					case 'Mechanism':
						$('#domain-category-list').tooltipster('content', 'This is the intermediate stage in learning a complex skill. Learned responses have become habitual and the movements can be performed with some confidence and proficiency.');
						break;
					case 'Complex Overt Response':
						$('#domain-category-list').tooltipster('content', 'The skillful performance of motor acts that involve complex movement patterns. Proficiency is indicated by a quick, accurate, and highly coordinated performance, requiring a minimum of energy. This category includes performing without hesitation, and automatic performance.');
						break;
					case 'Adaptation':
						$('#domain-category-list').tooltipster('content', 'Skills are well developed and the individual can modify movement patterns to fit special requirements.');
						break;
					case 'Origination':
						$('#domain-category-list').tooltipster('content', 'Creating new movement patterns to fit a particular situation or specific problem. Learning outcomes emphasize creativity based upon highly developed skills.');
						break;
				}
				populateActionWordCategories();
			});

		// Listen for change in action word categories, when it does call ajax
		$('.icons').on(
			'click',
			function () {
				//	$('#help-placeholder').css('height', '10em');
				//	$('#qtip-place').html('');
				populateActionWords();
			});

		// If the condition is set to hidden do not display it in the definition box above
		$('#LO-hide-from-Objective').change(
			function () {
				if (this.checked) {
					$('.learning-objective-current .learning-objective-condition').css('display', 'none');
				} else {
					$('.learning-objective-current .learning-objective-condition').css('display', 'inline');
				}
			}
		);

		// When a custom condition is added, display in the definition box above
		$('#custom-condition-text').keyup(
			function () {
				propagateToDefinition(this.value, 'condition');
			}
		);

		// When a standard condition is added, display in the definition box above
		/* $('.learning-objective.condition.generic.text').change(
		 propagateToDefinition(this.value, 'condition')
		 ); */

		$('input:radio[name=conditionType]').on(
			'change',
			function () {
				if (this.value === 'Generic') {
					$('#custom-condition-text').css('display', 'none');
					$('.learning-objective.condition.generic').css('display', 'block');
				} else {
					$('.learning-objective.condition.generic').css('display', 'none');
					$('#custom-condition-text').css('display', 'block');
				}
			}
		);

		// Manually tiggers the radio box change event
		$('input:radio[name=conditionType]:checked').change();

		// When hide from objective checkbox is changed
		$('#hide-condition').on(
			'change',
			function () {
				// savedData = false;
			}
		);

		// When save button on condition sub-tab is clicked
		$('#saveCondition').click(
			function () {
				$('#help-placeholder').css('height', '10em');
			}
		);

		// Trigger jquery ui button for better radio buttons
		category = $('input[name=selectedActionWordCategory]').val();
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
				} else {
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

		$('#learning-objective-missing, #learning-objective-missing-required').dialog({
			autoOpen: false
		});
		// When learning domain isn't selected, do not save learning objective
		/*
		 $('.learning-objective-button button').click(
		 function () {
		 if ($('#learning-objective-id').val() === '') {
		 $('#learning-objective-missing').dialog('open');
		 return false;
		 }
		 if ($('#learning-domain-list').val() === 'null' || $('#domain-category-list').val() === 'null' || $('input[name=actionWordCategory]').is(':checked') === false) {
		 $('#learning-objective-missing-required').dialog('open');
		 return false;
		 }
		 return true;
		 }
		 );
		 */

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

		// Checks if the LO is new and doesn't contain an id
		// if that's the case the page is just refreshed
		// instead of trying to remove the learning objective
		// from the db
		$('.remove-lo').click(function () {
			if (!$('.learning-objective.list-wrapper').find('.active').find('a').length) {
				window.location = window.location.pathname;
				return false;
			}
			return true;
		});
	}
);
