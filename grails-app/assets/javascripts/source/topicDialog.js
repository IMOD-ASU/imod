var errorMessages = [];
var isFlashing = null;
var content = '';

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

function flashError () {
	'use strict';
	var message = errorMessages.shift();
	$('#errorMessage').text(message);
	$('#errorMessage')
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

function errorMessage (message) {
	'use strict';
	errorMessages.push(message);
	if (isFlashing === null) {
		flashError();
		isFlashing = setInterval(flashError, 4000);
	}
}

function changePic () {
	'use strict';
	var iconName = '';
	$('#selectKnowledgeDimensions').find('input:checkbox').each(
		function () {
			if ($(this).prop('checked')) {
				iconName = iconName + $(this).val().charAt(0);
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
		contentDimensions.val(dimensions);
		$('#' + contentID).addClass('unsaved');
	}
	$('#' + contentID).find('img').attr('src', $('#dimImage').attr('src'));
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
	var dimString = $('#knowDimensionList' + contentID).val();
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

function getResource () {
	'use strict';
	var contentID = content.split('topicResources')[1];
	var resourceDiv = $('#resourceList tbody');
	var resourceOptions = '';

	resourceDiv.html('');

	$.ajax({
		url: '../../content/getResource',
		type: 'GET',
		dataType: 'json',
		data: {
			contentID: contentID
		},
		success: function (data) {
			var resources = data.resources;
			var resourceTypes = data.resourceTypes;
			var index;
			for (index = 0; index < resourceTypes.length; index++) {
				resourceOptions += '<option value="' + resourceTypes[index] + '">' + resourceTypes[index] + '</option>';
			}
			$.each(resources, function (key, value) {
				var id = value.id;
				// FIXME move html out of JS
				$('<tr id="' + id + '" class="resourceItem">' +
					'<td class="saveIcon">' +
					'<i class="fa fa-square-o"></i>' +
					'</td><td class="resourceName">' +
					'<input type="text" id="resourceName' + id + '" value="' + value.name + '"> ' +
					'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
					'</td><td class="resourceDescription">' +
					'<input type="text" id="resourceDescription' + id + '" value="' + value.description + '"> ' +
					'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
					'</td><td class="resourceType">' +
					'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '"> ' +
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

function openResourceModal () {
	'use strict';
	content = this.id;
	$('#selectResource').css('display', 'inherit');
	$('#selectResourceBackground').css('display', 'block');
	getResource();
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
			var topicTitle = $('#topicTitle' + contentID).val();
			var dimensions = $('#knowDimensionList' + contentID).val();
			var priority = $('#topicPriority' + contentID).val();
			var preReq = $('#topicPreReq' + contentID).is(':checked');
			if (dimensions === '') {
				errorMessage('Topic: ' + topicTitle + ' must have a Knowledge Dimension!');
				hasError = true;
			}
			if (topicTitle === '') {
				errorMessage('Topic title is required');
				hasError = true;
			}
			topicList.push({
				contentID: contentID,
				dimensions: dimensions,
				topicTitle: topicTitle
			});
			contentData.push({
				contentID: contentID,
				dimensions: dimensions,
				priority: priority,
				preReq: preReq,
				topicTitle: topicTitle
			});
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
		success: function () {
			location.reload();
		}
	});
}

function getTopicSavedItems (currentRow) {
	'use strict';
	var topicID = currentRow.id;
	var rowData = {
		title: $('#topicTitle' + topicID),
		titleSaved: $('#topicTitleSaved' + topicID),
		dimensions: $('#knowDimensionList' + topicID),
		dimensionsSaved: $('#knowDimensionListSaved' + topicID),
		priority: $('#topicPriority' + topicID),
		prioritySaved: $('#topicPrioritySaved' + topicID),
		preReq: $('#topicPreReq' + topicID),
		preReqSaved: $('#topicPreReq' + topicID)
	};
	return rowData;
}

function revertChanges () {
	'use strict';
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
			var id = data.id;
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
				'<input type="text" id="topicTitle' + id + '"> ' +
				'<input type="hidden" id="topicTitleSaved' + id + '"> ' +
				'</td><td class="topicDimensions">' +
				'<span>' +
				'<img src="' + $('#imgNone').attr('href') + '"/> ' +
				'<button ' +
				'class="knowledgeDimensionButton" ' +
				'value="" ' +
				'type="button" ' +
				'id="knowDimensionList' + id + '" ' +
				'> ' +
				' Knowledge Dimensions ' +
				'</button> ' +
				'<input type="hidden" id="knowDimensionListSaved' + id + '" value=""> ' +
				'</span> ' +
				'</td><td class="topicPriority">' +
				'<select size="1" name="topicPriority' + id + '" id="topicPriority' + id + '"> ' +
				prioritiesOptions +
				'</select> ' +
				'<input type="hidden" name="topicPrioritySaved' + id + '"> ' +
				'</td><td class="topicResources">' +
				'<button type="button" class="ResourceButton" id="topicResources' + id + '">Resources</button> ' +
				'</td><td class="topicPreReq">' +
				'<input type="checkbox" name="topicPreReq' + id + '"> ' +
				'<input type="hidden" name="topicPreReqSaved' + id + '"> ' +
				'</td>' +
				'</tr>'
			).appendTo(topicDiv);
			$('#topicTitle' + id).focus();
			$('.knowledgeDimensionButton').click(openDimModal);
			$('.ResourceButton').click(openResourceModal);
		}
	});
}

function addResource () {
	'use strict';
	var contentID = content.split('topicResources');
	contentID = contentID[1];
	$.ajax({
		url: '../../content/addResource',
		type: 'GET',
		dataType: 'json',
		data: {
			contentID: contentID
		},
		success: function (data) {
			var id = data.id;
			var resources = data.resources;
			var resourceOptions = '';
			var resourceDiv = $('#resourceList tbody');
			var index;
			for (index = 0; index < resources.length; index++) {
				resourceOptions += '<option value="' + resources[index] + '">' + resources[index] + '</option>';
			}
			// FIXME move html out of JS
			$('<tr id="' + id + '" class="resourceItem">' +
				'<td class="saveIcon">' +
				'<i class="fa fa-square-o"></i>' +
				'</td><td class="resourceName">' +
				'<input type="text" id="resourceName' + id + '" autofocus> ' +
				'<input type="hidden" id="resourceNameSaved' + id + '"> ' +
				'</td><td class="resourceDescription">' +
				'<input type="text" id="resourceDescription' + id + '" autofocus> ' +
				'<input type="hidden" id="resourceDescriptionSaved' + id + '"> ' +
				'</td><td class="resourceType">' +
				'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '"> ' +
				resourceOptions +
				'</select> ' +
				'<input type="hidden" name="resourceTypeSaved' + id + '"> ' +
				'</td></tr>'
			).appendTo(resourceDiv);
		}
	});
}

function saveResource () {
	'use strict';
	var imodID = $('#imodID').val();
	var resourceData = [];
	var hasError = false;
	$('#resourceList tbody tr').each(
		function () {
			var resourceID = this.id;
			var resourceName = $('#resourceName' + resourceID).val();
			var resourceDescription = $('#resourceDescription' + resourceID).val();
			var resourceType = $('#resourceType' + resourceID).val();
			if (resourceDescription === '') {
				errorMessage('Resource: ' + resourceName + ' must have a Description!');
				hasError = true;
			}
			if (resourceName === '') {
				errorMessage('Resource Name is required');
				hasError = true;
			}
			resourceData.push({
				resourceID: resourceID,
				resourceName: resourceName,
				resourceDescription: resourceDescription,
				resourceType: resourceType
			});
		}
	);
	if (hasError) {
		return;
	}
	resourceData = JSON.stringify(resourceData);
	$.ajax({
		url: '../../content/saveResource/',
		type: 'POST',
		dataType: 'json',
		data: {
			id: imodID,
			JSONData: resourceData
		},
		success: function () {
			closeResourceModal();
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
		// Attach event listeners
		$('#addTopicModal').click(showTopicDialog);
		$('#addTopic').click(addTopic);
		$('#saveTopic').click(saveTopic);
		$('#cancelTopic').click(
			function () {
				revertChanges();
				hideTopicDialog();
			}
		);
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
						resourceIDs.push(this.id);
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
						contentIDs.push(this.id);
					}
				);
				deleteTopic(contentIDs);
			}
		);

		$('#topicList').on(
			'click',
			'.saveIcon',
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');
				$(this).parent().toggleClass('selected');
			}
		);

		$('.saveIcon-parent').click(
			function () {
				$(this).find(' > i').toggleClass('fa-square-o').toggleClass('fa-check-square');

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
