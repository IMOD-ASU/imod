var errorMessages = [];
var isFlashing = null;
var content = '';
var resourceOptions = '';
var resourceData = [];
var unsavedResourceData = [];

window.unsavedResource = false;

function showTopicDialog () {
	'use strict';
	$('#topicDialogBackground').css('display', 'block');
	$('#topicDialog').css('display', 'block');
}

function hideTopicDialog () {
	'use strict';
	$('#topicDialogBackground').css('display', 'none');
	$('#topicDialog').css('display', 'none');
}

function flashError (resourceId) {
	'use strict';
	var message = errorMessages.shift();

	if (typeof a === 'undefined') {
		resourceId = 'errorMessage';
	}

	$('#' + resourceId).text(message);
	$('#' + resourceId)
		.fadeIn('fast')
		.delay(3000)
		.fadeOut(
			'slow',
			'swing',
			function () {
				if (errorMessages.length === 0) {
					clearInterval(isFlashing);
					isFlashing = null;
				}
			}
		);
}

function errorMessage (message, resourceId) {
	'use strict';
	errorMessages.push(message);

	if (typeof a === 'undefined') {
		resourceId = 'errorMessage';
	}

	if (isFlashing === null) {
		flashError(resourceId);
		isFlashing = setInterval(function () {
			flashError(resourceId);
		}, 4000);
	}
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

function saveDimModal () {
	'use strict';
	var contentID = $('#topicID').val();
	var dimensions = [];
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');
	var contentDimensions = document.getElementById('knowDimensionList' + contentID);
	// var contentDimensions = $(this).closest('.knowledgeDimensionButton');

	$(this).siblings('span').find('input').each(
		function () {
			if (this.checked) {
				dimensions.push($(this).val());
			}
		}
	);
	if (dimensions.length === 0) {
		dimensions = '';
	} else {
		dimensions = dimensions.toString();
	}
	$('#topicID').val('');
	dialog.find('input:checkbox').each(
		function () {
			$(this).prop('checked', false);
		}
	);

	if (contentDimensions.value !== dimensions) {
		contentDimensions.value = dimensions;
		$('#' + contentID).addClass('unsaved');
	}
	document.getElementById(contentID).getElementsByTagName('img')[0].setAttribute('src', document.getElementById('dimImage').getAttribute('src'));
	dialog.css('display', 'none');
	background.css('display', 'none');
}

function closeDimModal () {
	'use strict';
	var contentID = $('#topicID').val();
	var dimensions = [];
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');
	var contentDimensions = $('#knowDimensionList' + contentID);

	$(this).siblings('span').find('input').each(
		function () {
			if (this.checked) {
				dimensions.push($(this).val());
			}
		}
	);

	if (dimensions.length === 0) {
		dimensions = '';
	} else {
		dimensions = dimensions.toString();
	}

	$('#topicID').val('');
	dialog.find('input:checkbox').each(
		function () {
			$(this).prop('checked', false);
		}
	);

	if (contentDimensions.val() !== dimensions) {
		$('#' + contentID).addClass('unsaved');
	}

	dialog.css('display', 'none');
	background.css('display', 'none');
}

function openDimModal () {
	'use strict';
	var contentID = $(this).parents('.topicItem').attr('id');
	var dimString = document.getElementById('knowDimensionList' + contentID).value;
	var dimensionList = [];
	var dialog = $('#selectKnowledgeDimensions');
	var background = $('#selectKnowledgeDimensionBackground');
	var index;
	var findCheckBox;

	if (dimString !== '') {
		dimensionList = dimString.split(',');
	}
	for (index = 0; index < dimensionList.length; index++) {
		findCheckBox = $(dialog).find('#' + dimensionList[index]);
		if (findCheckBox.length === 1) {
			findCheckBox.prop('checked', true);
		}
	}
	changePic();
	$('#topicID').val(contentID);
	dialog.css('display', 'inherit');
	background.css('display', 'block');
}

function getTempResource () {
	'use strict';
	var contentID = content.split('topicResources')[1];
	var resourceDiv = $('#resourceList tbody');
	var inc;
	var resourcesNew;
	var checkTempId = document.getElementById('topicTempID' + contentID).value;
	var resourceItem = [];

	resourceDiv.html('');
	if (checkTempId === 'undefined') {
		resourceItem = [];

		for (inc = 0; inc < resourceData.length; inc++) {
			if (resourceData[inc].contentID === contentID) {
				resourceItem.push({
					class: 'imod.Resource',
					content: {
						class: 'imod.Content',
						id: contentID
					},
					description: resourceData[inc].resourceDescription,
					id: resourceData[inc].resourceID,
					name: resourceData[inc].resourceName,
					resourceType: resourceData[inc].resourceType
				});
			}
		}
		$.each(resourceItem, function (key, value) {
			value.id = value.id.toString();
			value.content.id = value.content.id.toString();
		});
		// resourceData1 = refineUnsavedResources(resources);
		resourcesNew = removeDuplicateResource(resourceItem);
		resourcesNew.sort(function (itemOne, itemTwo) {
			return (itemOne.id > itemTwo.id);
		});

		$.each(resourcesNew, function (key, value) {
			var id = value.id;

			// FIXME move html out of JS
			$('<tr id="' + id + '" class="resourceItem">' +
				'<td class="saveIcon">' +
				'<i class="fa fa-square-o"></i>' +
				'</td><td class="resourceName">' +
				'<input type="text" id="resourceName' + id + '" value="' + value.name + '"> ' +
				'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
				'<input type="hidden" value="' + id + '" id="tempResId' + id + '"> ' +
				'</td><td class="resourceDescription">' +
				'<input type="text" id="resourceDescription' + id + '" value="' + value.description + '"> ' +
				'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
				'</td><td class="resourceType">' +
				'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '" class="custom-dropdown"> ' +
				resourceOptions +
				'</select> ' +

				'<input type="hidden" name="resourceTypeSaved' + id + '"> ' +
				'</td></tr>'
			).appendTo(resourceDiv);
			$('#resourceType' + id).val(value.resourceType);
		});
	} else {
		$.ajax({
			url: '../../content/getResource',
			type: 'GET',
			dataType: 'json',
			data: {
				contentID: contentID
			},
			success: function (data) {
				var resources = data.resources;

				for (inc = 0; inc < resourceData.length; inc++) {
					if (resourceData[inc].contentID === contentID) {
						resources.push({
							class: 'imod.Resource',
							content: {
								class: 'imod.Content',
								id: contentID
							},
							description: resourceData[inc].resourceDescription,
							id: resourceData[inc].resourceID,
							name: resourceData[inc].resourceName,
							resourceType: resourceData[inc].resourceType
						});
					}
				}
				$.each(resources, function (key, value) {
					value.id = value.id.toString();
					value.content.id = value.content.id.toString();
				});
				// resourceData1 = refineUnsavedResources(resources);
				resourcesNew = removeDuplicateResource(resources);
				resourcesNew.sort(function (itemOne, itemTwo) {
					return (itemOne.id > itemTwo.id);
				});

				$.each(resourcesNew, function (key, value) {
					var id = value.id;

					// FIXME move html out of JS
					$('<tr id="' + id + '" class="resourceItem">' +
						'<td class="saveIcon">' +
						'<i class="fa fa-square-o"></i>' +
						'</td><td class="resourceName">' +
						'<input type="text" id="resourceName' + id + '" value="' + value.name + '"> ' +
						'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
						'<input type="hidden" value="' + id + '" id="tempResId' + id + '"> ' +
						'</td><td class="resourceDescription">' +
						'<input type="text" id="resourceDescription' + id + '" value="' + value.description + '"> ' +
						'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
						'</td><td class="resourceType">' +
						'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '" class="custom-dropdown"> ' +
						resourceOptions +
						'</select> ' +

						'<input type="hidden" name="resourceTypeSaved' + id + '"> ' +
						'</td></tr>'
					).appendTo(resourceDiv);
					$('#resourceType' + id).val(value.resourceType);
				});
			}
		});
	}
}
function getResource () {
	'use strict';
	var contentID = content.split('topicResources')[1];
	// var topic_title = document.getElementById(content).name;
	var checkTempId = document.getElementById('topicTempID' + contentID).value;
	var resourceDiv = $('#resourceList tbody');

	resourceDiv.html('');
	if (checkTempId !== 'undefined') {
		$.ajax({
			url: '../../content/getResource',
			type: 'GET',
			dataType: 'json',
			data: {
				contentID: contentID
			},
			success: function (data) {
				var resources = data.resources;

				resources.sort(function (itemOne, itemTwo) {
					return (itemOne.id > itemTwo.id);
				});

				$.each(resources, function (key, value) {
					var id = value.id;

					// FIXME move html out of JS
					$('<tr id="' + id + '" class="resourceItem">' +
						'<td class="saveIcon">' +
						'<i class="fa fa-square-o"></i>' +
						'</td><td class="resourceName">' +
						'<input type="text" id="resourceName' + id + '" value="' + value.name + '"> ' +
						'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
						'<input type="hidden" value="' + id + '" id="tempResId' + id + '"> ' +
						'</td><td class="resourceDescription">' +
						'<input type="text" id="resourceDescription' + id + '" value="' + value.description + '"> ' +
						'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
						'</td><td class="resourceType">' +
						'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '" class="custom-dropdown"> ' +
						resourceOptions +
						'</select> ' +

						'<input type="hidden" name="resourceTypeSaved' + id + '"> ' +
						'</td></tr>'
					).appendTo(resourceDiv);
					$('#resourceType' + id).val(value.resourceType);
				});
			}
		});
	}
}

function openResourceModal () {
	'use strict';
	content = this.id;
	$('#selectResource').css('display', 'inherit');
	$('#selectResourceBackground').css('display', 'block');
	if (window.unsavedResource === true) {
		getTempResource();
	} else {
		getResource();
	}
}

function closeResourceModal () {
	'use strict';
	$('#selectResourceBackground').css('display', 'none');
	$('#selectResource').css('display', 'none');
}

function highlightUnsaved (id) {
	'use strict';
	$('#' + id).addClass('unsaved');
}

function deleteTopic (contentIDs) {
	'use strict';
	$.ajax({
		url: '../../content/deleteTopic/',
		type: 'GET',
		dataType: 'json',
		data: {
			contentIDs: JSON.stringify(contentIDs)
		},
		success: function (data) {
			data.result.forEach(
				function (element) {
					$('#' + element).remove();
				}
			);
			window.cleanForm = $('form, #contentTable').find('select, textarea, input').serialize();
			location.reload();
			$('#topic-removed-success').dialog('open');
		}
	});
}

function saveTopic () {
	'use strict';
	var imodID = $('#imodID').val();
	var contentData = [];
	var hasError = false;
	var topicList = [];

	$('#topicList tbody tr').each(
		function () {
			var contentID = this.id;
			// var param = Math.random();
			var topicTitle = document.getElementById('topicTitle' + contentID).value;
			var tempID = document.getElementById('topicTempID' + contentID).value;
			var dimensions = document.getElementById('knowDimensionList' + contentID).value;
			var priority = document.getElementById('topicPriority' + contentID).value;
			var preReq = $('#topicPreReq' + contentID).is(':checked');

			if (dimensions === '') {
				errorMessage('Topic: ' + topicTitle + ' must have a Knowledge Dimension!', 'errorMessage');
				hasError = true;
			}
			if (topicTitle === '') {
				errorMessage('Topic title is required', 'errorMessage');
				hasError = true;
			}

			if (tempID === 'undefined') {
				topicList.push({
					contentID: contentID,
					dimensions: dimensions,
					topicTitle: topicTitle,
					tempID: tempID
				});
				contentData.push({
					contentID: contentID,
					dimensions: dimensions,
					priority: priority,
					preReq: preReq,
					topicTitle: topicTitle,
					tempID: tempID
				});
			} else {
				topicList.push({
					contentID: contentID,
					dimensions: dimensions,
					topicTitle: topicTitle,
					tempID: contentID
				});
				contentData.push({
					contentID: contentID,
					dimensions: dimensions,
					priority: priority,
					preReq: preReq,
					topicTitle: topicTitle,
					tempID: contentID
				});
			}
		}
	);
	if (hasError) {
		return;
	}
	contentData = JSON.stringify(contentData);
	$.ajax({
		url: '../../content/saveTopic/',
		type: 'POST',
		dataType: 'json',
		data: {
			id: imodID,
			JSONData: contentData
		},
		success: function (data) {
			var index;

			for (index = 0; index < data.success.length; index++) {
				data.contentData[index].contentID = data.success[index];
			}
			if (resourceData.length > 0) {
				outerSaveResource(data.contentData);
			} else {
				if ($('#contentTable').length) {
					window.cleanForm = $('form, #contentTable').find('select, textarea, input').serialize();
				}
				location.reload();
			}
		}
	});
}

function getTopicSavedItems (currentRow) {
	'use strict';
	var topicID = currentRow.id;

	return {
		title: $('#topicTitle' + topicID),
		titleSaved: $('#topicTitleSaved' + topicID),
		dimensions: $('#knowDimensionList' + topicID),
		dimensionsSaved: $('#knowDimensionListSaved' + topicID),
		priority: $('#topicPriority' + topicID),
		prioritySaved: $('#topicPrioritySaved' + topicID),
		preReq: $('#topicPreReq' + topicID),
		preReqSaved: $('#topicPreReq' + topicID)
	};
}

function revertChanges () {
	'use strict';
	$('.topicItem').each(
		function () {
			if (document.getElementById('topicTempID' + this.id).value === 'undefined') {
				$(this).remove();
			}
		}
	);
	$('#topicList tbody tr').each(
		function () {
			var rowData = getTopicSavedItems(this);
			var dimensions = [];
			var dimensionShort = '';
			var icon = '';
			var contentIDs = [];

			if ($(rowData.dimensionsSaved).val() === '') {
				contentIDs.push(this.id);
			} else {
				$(rowData.title).val($(rowData.titleSaved).val());
				$(rowData.dimensions).val($(rowData.dimensionsSaved).val());
				$(rowData.priority).val($(rowData.prioritySaved).val());
				$(rowData.preReq).val($(rowData.preReqSaved).val());
				$('#' + this.id).removeClass('unsaved');
				dimensions = $(rowData.dimensions).val().split(',');
				$(dimensions).each(
					function () {
						dimensionShort += this.charAt(0);
					}
				);
				if (dimensionShort === '') {
					icon = $('#imgNone').attr('href');
				} else {
					icon = $('#img' + dimensionShort).attr('href');
				}
				$(rowData.dimensions).siblings('img').attr('src', icon);
			}
			deleteTopic(contentIDs);
		}
	);
}

function addTopic () {
	'use strict';
	var imodID = $('#imodID').val();

	$.ajax({
		url: '../../content/addNewTopic',
		type: 'GET',
		dataType: 'json',
		data: {
			id: imodID
		},
		success: function (data) {
			var dimensions = data.dimensions;
			var priorities = data.priorities;
			var tempID = data.id;
			var id = Math.random();
			var dimensionOptions = '';
			var prioritiesOptions = '';
			var topicDiv = $('#topicList tbody');
			var index;

			for (index = 0; index < dimensions.length; index++) {
				dimensionOptions += '<option value="' + dimensions[index] + '">' + dimensions[index] + '</option>';
			}
			for (index = 0; index < priorities.length; index++) {
				prioritiesOptions += '<option value="' + priorities[index] + '">' + priorities[index] + '</option>';
			}
			// FIXME move html block out of javascript file
			$('<tr id="' + id + '" class="topicItem">' +
				'<td class="saveIcon">' +
				'<i class="fa fa-square-o"></i>' +
				'</td><td class="topicTitle">' +
				'<input type="text" class="newTopic" id="topicTitle' + id + '" autofocus> ' +
				'<input type="hidden" id="topicTitleSaved' + id + '"> ' +
				'<input type="hidden" value="' + tempID + '" id="topicTempID' + id + '"> ' +
				'</td><td class="topicDimensions show-hover-new">' +
				'<span>' +
				'<img id="dimImageModal' + id + '"  class="tooltipsternew" src="' + $('#imgNone').attr('href') + '" /> ' +
				'<button ' +
				'class="knowledgeDimensionButton tooltipsternew" ' +
				'value="" ' +
				'type="button" ' +
				'id="knowDimensionList' + id + '" ' +
				'title="Click on Knowledge Dimensions button to select one or more knowledge dimensions"' +
				'> ' +
				' Knowledge Dimensions ' +
				'</button> ' +
				'<input type="hidden" id="knowDimensionListSaved' + id + '" value=""> ' +
				'</span> ' +
				'</td><td class="topicPriority">' +
				'<select size="1" name="topicPriority' + id + '" id="topicPriority' + id + '" class="custom-dropdown tooltipsternew" title="Select an appropriate priority for the topic"> ' +
				prioritiesOptions +
				'</select> ' +
				'<input type="hidden" name="topicPrioritySaved' + id + '"> ' +
				'</td><td class="topicResources">' +
				'<button type="button" class="ResourceButton tooltipsternew" id="topicResources' + id + '"  title="Click on the Resource Button to add new resource to the topic">Resources</button> ' +
				'</td><td class="topicPreReq">' +
				'<input type="checkbox" name="topicPreReq' + id + '"> ' +
				'<input type="hidden" name="topicPreReqSaved' + id + '"> ' +
				'</td>' +
				'</tr>'
			).appendTo(topicDiv);
			// $('#topicTitle' + id).focus();
			$('.tooltipsternew').tooltipster({
				theme: 'tooltipster-noir',
				position: 'bottom'
			});
			$('.newTopic').focus();
			// $('#topicTitle' + id).focus();
			$('.knowledgeDimensionButton').click(openDimModal);
			$('.ResourceButton').click(openResourceModal);
		}
	});
}

function addResource () {
	'use strict';
	var id = Math.random();
	var tempResId = null;
	var resourceDiv = $('#resourceList tbody');

	// FIXME move html out of JS
	$('<tr id="' + id + '" class="resourceItem">' +
		'<td class="saveIcon">' +
		'<i class="fa fa-square-o"></i>' +
		'</td><td class="resourceName">' +
		'<input type="text" id="resourceName' + id + '" autofocus> ' +
		'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
		'<input type="hidden" value="' + tempResId + '" id="tempResId' + id + '"> ' +
		'</td><td class="resourceDescription">' +
		'<input type="text" id="resourceDescription' + id + '" autofocus> ' +
		'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
		'</td><td class="resourceType">' +
		'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '" class="custom-dropdown"> ' +
		resourceOptions +
		'</select> ' +
		'<input type="hidden" name="resourceTypeSaved' + id + '"> ' +
		'</td></tr>'
	).appendTo(resourceDiv);
}

function saveResource () {
	'use strict';
	var contentID = content.split('topicResources')[1];
	var topicTitle = document.getElementById(content).name;
	var hasError = false;
	var contentResource = $('#resourceDataStore');
	var resourceDataNew;
	var resource;
	var refinedData;

	if (topicTitle === '') {
		topicTitle = document.getElementById('topicTitle' + contentID).value;
	}

	$('#resourceList tbody tr').each(
		function () {
			var resourceID = this.id;


			var resourceName = document.getElementById('resourceName' + resourceID).value;
			var resourceDescription = document.getElementById('resourceDescription' + resourceID).value;
			var resourceType = document.getElementById('resourceType' + resourceID).value;
			var tempID = document.getElementById('tempResId' + resourceID).value;

			if (resourceDescription === '') {
				errorMessage('Resource: ' + resourceName + ' must have a Description!', 'errorMessageResources');
				hasError = true;
			}
			if (resourceName === '') {
				errorMessage('Resource Name is required', 'errorMessageResources');
				hasError = true;
			}
			resourceData.push({
				resourceID: resourceID,
				resourceName: resourceName,
				resourceDescription: resourceDescription,
				resourceType: resourceType,
				contentID: contentID,
				uniqueParam: Math.random(),
				topicTitle: topicTitle,
				tempID: tempID
			});
		}
	);
	if (hasError) {
		return false;
	}
	refinedData = refineUnsavedResources(resourceData);
	resourceDataNew = removeDuplicateResource(refinedData);
	unsavedResourceData = resourceDataNew;
	resource = JSON.stringify(resourceDataNew);

	if (contentResource.val !== resource) {
		contentResource.val(resource);
	}

	if (window.unsavedResource === false) {
		window.unsavedResource = true;
	}
	return closeResourceModal();
}

function refineUnsavedResources (arr) {
	'use strict';
	var finalArr = [];
	var collection = [];
	var originalArr = arr;
	var returnedArray;

	$.each(arr, function (index, value) {
		if ($.inArray(value.resourceID, finalArr) === -1) {
			finalArr.push(value.resourceID);
			collection.push(value);
		}
	});
	if (collection.length > 0) {
		returnedArray = collection;
	} else {
		returnedArray = originalArr;
	}
	return returnedArray;
}

function removeDuplicateResource (arr) {
	'use strict';
	var cleaned = [];

	arr.forEach(function (itm) {
		var unique = true;

		cleaned.forEach(function (itmTwo) {
			if (_.isEqual(itm, itmTwo)) {
				unique = false;
			}
		});
		if (unique) {
			cleaned.push(itm);
		}
	});
	return cleaned;
}

function outerSaveResource (contentData) {
	'use strict';
	var imodID = $('#imodID').val();
	var index;
	var outerIndex;

	for (index = 0; index < contentData.length; index++) {
		for (outerIndex = 0; outerIndex < unsavedResourceData.length; outerIndex++) {
			if (contentData[index].topicTitle.toLowerCase() === unsavedResourceData[outerIndex].topicTitle.toLowerCase()) {
				unsavedResourceData[outerIndex].contentID = contentData[index].contentID;
			}
		}
	}

	$.ajax({
		url: '../../content/saveResource/',
		type: 'POST',
		dataType: 'json',
		data: {
			id: imodID,
			JSONData: JSON.stringify(unsavedResourceData)
		},
		success: function () {
			window.unsavedResource = false;
			location.reload();
			// closeResourceModal();
		}
	});
}

function deleteResource (resourceIDs) {
	'use strict';
	$.ajax({
		url: '../../content/deleteResource/',
		type: 'GET',
		dataType: 'json',
		data: {
			resourceIDs: JSON.stringify(resourceIDs)
		},
		success: function (data) {
			data.result.forEach(
				function (element) {
					$('#' + element).remove();
				}
			);
		}
	});
}

$(
	function () {
		'use strict';
		var numberOfCriticalTopics = 0;
		var numberOfVeryImportantTopics = 0;
		var numberOfGoodToKnowTopics = 0;
		var ctx;
		var chartData;
		var options;
		var myPieChart;

		if ($('#topicList tbody tr').length) {
			ctx = document.getElementById('chart').getContext('2d');
		}

		$('input[id^="topicPrioritySaved"]').each(function () {
			if ($(this).val() === 'Critical') {
				numberOfCriticalTopics += 1;
			}
			if ($(this).val() === 'Very Important') {
				numberOfVeryImportantTopics += 1;
			}
			if ($(this).val() === 'Good to Know') {
				numberOfGoodToKnowTopics += 1;
			}
		});

		if ($('#topicList tbody tr').length) {
			chartData = [
				{
					value: numberOfCriticalTopics,
					color: '#3fa196',
					highlight: '#23a99a',
					label: 'Critical'
				},
				{
					value: numberOfVeryImportantTopics,
					color: '#6167b3',
					highlight: '#3b44bf',
					label: 'Very Important'
				},
				{
					value: numberOfGoodToKnowTopics,
					color: '#8348ad',
					highlight: '#792ab2',
					label: 'Good to Know'
				}
			];

			options = [];
			myPieChart = new Chart(ctx).Pie(chartData, options);
			document.getElementById('js-legend').innerHTML = myPieChart.generateLegend();
		}

		// Attach event listeners
		$('#addTopicModal').click(showTopicDialog);
		$('#addTopic').click(
			function () {
				addTopic();
				// $('.newTopic').focus();
			});
		$('.saveTopic').click(
			function () {
				saveTopic();
			});
		$('.cancelTopic').click(
			function () {
				revertChanges();
				hideTopicDialog();
				location.reload();
			}
		);
		$('#cancelTopic').click(
			function () {
				hideTopicDialog();
			}
		);
		$.ajax({
			url: '../../content/getResourceTypes',
			type: 'GET',
			dataType: 'json',
			async: false,
			success: function (data) {
				var resources = data.resources;
				var index;

				for (index = 0; index < resources.length; index++) {
					resourceOptions += '<option value="' + resources[index] + '">' + resources[index] + '</option>';
				}
			}
		});
		$('.knowledgeDimensionButton').click(openDimModal);
		$('#knowDimFinished').click(saveDimModal);
		$('#closeKnowDim').click(closeDimModal);
		$('.ResourceButton').click(openResourceModal);
		$('#addResource').click(addResource);
		$('#cancelResource').click(closeResourceModal);
		$('#saveResource').click(saveResource);
		$('#removeResource').click(
			function () {
				var resourceIDs = [];

				$('#resourceList .selected').each(
					function () {
						resourceIDs.push($(this).parent().attr('id'));
					}
				);
				deleteResource(resourceIDs);
			}
		);
		$('#removeTopic').click(
			function () {
				var contentIDs = [];

				$('#topicList .selected').each(
					function () {
						window.cleanForm = $('form, #contentTable').find('select, textarea, input').serialize();

						if (typeof $('#topicTempID' + this.id).val() === 'undefined') {
							$(this).remove();
						} else {
							contentIDs.push(this.id);
						}
					}
				);

				if (contentIDs.length < 1) {
					$('#please-select-topic').dialog('open');
					return false;
				}
				$('#confirm-topic-remove').dialog('open');
				return false;
			}
		);

		$('#please-select-topic').dialog({
			autoOpen: false,
			closeOnEscape: false,
			open: function (event, ui) {
				$('.ui-dialog-titlebar-close', ui.dialog | ui).hide();
			},
			buttons: {
				OK: function () {
					$(this).dialog('close');
				}
			}
		});

		$('#topic-removed-success').dialog({
			autoOpen: false
		});

		$('#confirm-topic-remove').dialog({
			autoOpen: false,
			modal: true,
			dialogClass: 'flora',
			buttons: {
				yes: function () {
					var contentIDs = [];

					$(this).dialog('close');

					$('#topicList .selected').each(
						function () {
							window.cleanForm = $('form, #contentTable').find('select, textarea, input').serialize();

							if (typeof $('#topicTempID' + this.id).val() === 'undefined') {
								$(this).remove();
							} else {
								contentIDs.push(this.id);
							}
						}
					);

					if (contentIDs.length > 0) {
						deleteTopic(contentIDs);
					}
				},
				no: function () {
					$(this).dialog('close');
					location.reload();
				}
			}
		});
		$('.flora.ui-dialog').css({position: 'fixed'});

		$('#topicList').on(
			'click',
			'.saveIcon',
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
				$(this).parent().toggleClass('selected');
				if (!$(this).parent().hasClass('selected') && $('.saveIcon-parent').hasClass('all-selected')) {
					$('.saveIcon-parent').find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
					$('.saveIcon-parent').toggleClass('all-selected');
				}
			}
		);

		$('.saveIcon-parent').click(
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
				$(this).toggleClass('all-selected');
				if ($(this).find('i').hasClass('fa-square-o')) {
					$(this).parents('table')
						.find('tbody')
						.find('tr')
						.removeClass('selected')
						.find('.saveIcon')
						.find('> i')
						.removeClass('fa-check-square')
						.addClass('fa-square-o');
				} else {
					$(this).parents('table')
						.find('tbody')
						.find('tr')
						.addClass('selected')
						.find('.saveIcon')
						.find('> i')
						.removeClass('fa-square-o')
						.addClass('fa-check-square');
				}
				return false;
			}
		);

		$('#resourceList').on(
			'click',
			'.saveIcon',
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
				$(this).toggleClass('selected');
			}
		);

		$('#selectKnowledgeDimensions').on('change', 'input:checkbox', changePic);
		$('#topicList > tbody').on(
			'change',
			'input',
			function () {
				var id = $(this).parents('tr .topicItem').attr('id');

				highlightUnsaved(id);
			}
		);
		$('#topicList > tbody').on(
			'change',
			'select',
			function () {
				var id = $(this).parents('tr .topicItem').attr('id');

				highlightUnsaved(id);
			}
		);
	}
);
