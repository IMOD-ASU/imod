'use strict';

$(function() {
	var jsonData = JSON.parse($("input[name=treeData]").val());
	buildContentTree(jsonData, false);
	$('#contentTree').on('ready.jstree', function() {
		var idList = $('#contentTree').find('li.topicSelected');
		$(idList).each(function() {
			refreshTreeValues($(this).children('a'), false);
		});
		var newJSONdata = $('#contentTree').jstree(true).get_json();
		$('#contentTree').jstree('destroy');
		buildContentTree(newJSONdata, true);
	});

	$('#deleteTopicModal').click(function() {
		var nodeArray = $('#contentTree').jstree(true)._model.data['#'].children_d;
		var defaultClass = 'fa fa-stack-1x checkbox';
		var idArray = [];
		$(nodeArray).each(function() {
			var icon = $('#select' + this.substr(7));
			if ($('#' + this).hasClass('topicSelected')) {
				icon.attr('class', defaultClass + ' fa-check');
				idArray.push(this.substr(7));
			}
		});

		if (idArray.length < 1) {
			alert('Please select at least one topic');
		} else {
			deleteTopicSubTab(idArray);
		}

		return false;
	});
});

function deleteTopicSubTab(contentIDs) {
	contentIDs = JSON.stringify(contentIDs);
	$.ajax({
		url: '../../content/deleteTopic/',
		type: 'GET',
		dataType: 'json',
		data: {
			contentIDs: contentIDs
		},
		success: function(data) {
			// window.location.reload();
		}
	});
}

function buildContentTree(jsonData, refreshDB) {
	$('#contentTree').jstree({
		'core': {
			'data': jsonData,
			'check_callback': true,
			'themes': {
				'icons': false
			}
		},
		'dnd': {
			'copy': false
		},
		'plugins': ['wholerow', 'dnd', 'types']
	});
	$('#contentTree').on('ready.jstree', function() {
		$('#contentTree').jstree('open_all');

		$('#contentTree .jstree-wholerow').on('click', function() {
			selectCheckboxes(this);
		});
		$('#contentTree .jstree-anchor').on('click', function() {
			selectCheckboxes(this);
		});
		refreshCheckboxes(refreshDB);
	});
	$('#contentTree').on('move_node.jstree', function(e, data) {
		$('#contentTree').jstree('open_node', '#' + data.node.parent);
		refreshTreeValues($('#' + data.node.id).children('a'), true);
		moveContent(data.node.id.substr(7), data.node.parent.substr(7));
	});
	$('#contentTree').off('click.jstree');
}

function selectCheckboxes(currentRow) {
	var contentID = $(currentRow).parent('li').attr('id');
	var contentNode = $('#contentTree').jstree(true).get_node(contentID);
	var contentNodeClass = contentNode.li_attr['class'];
	var testingObject = $('#' + contentID);
	if (contentNodeClass === 'topicNotSelected' || contentNodeClass === 'topicIndeterminate') {
		contentNode.li_attr['class'] = 'topicSelected';
		testingObject.find('li').each(function() {
			var childNode = $('#contentTree').jstree(true).get_node($(this).attr('id'));
			childNode.li_attr['class'] = 'topicSelected';
		});
	} else {
		contentNode.li_attr['class'] = 'topicNotSelected';
		testingObject.find('li').each(function() {
			var childNode = $('#contentTree').jstree(true).get_node($(this).attr('id'));
			childNode.li_attr['class'] = 'topicNotSelected';
		});
	}
	refreshTreeValues(currentRow, true);
}

function refreshTreeValues(currentRow, doRefresh) {
	var contentID = $(currentRow).parent('li').attr('id');
	var contentNode = $('#contentTree').jstree(true).get_node(contentID);
	var newJSONdata;
	var indeterminate = false;
	var currentNode = $('#contentTree').jstree(true).get_node(contentNode);
	while (currentNode.parents.length > 1) {
		currentNode = $('#contentTree').jstree(true).get_node(currentNode.parent);
		if (currentNode.parents.length === 1) {
			currentNode.a_attr['class'] = 'rootNode';
		} else {
			currentNode.a_attr['class'] = '';
		}
		if (!indeterminate) {
			var numIndeterminate;
			$(currentNode.children_d).each(function() {
				if ($('#contentTree').jstree(true).get_node(this).li_attr['class'] === 'topicIndeterminate') {
					numIndeterminate++;
				}
			});
			if (numIndeterminate > 0) {
				indeterminate = true;
				currentNode.li_attr['class'] = 'topicIndeterminate';
			} else {
				var treeSize = currentNode.children_d.length;
				var numSelected = 0;
				$(currentNode.children_d).each(function() {
					if ($('#contentTree').jstree(true).get_node(this).li_attr['class'] === 'topicSelected') {
						numSelected++;
					}
				});
				if (treeSize !== numSelected && numSelected > 0) {
					indeterminate = true;
					currentNode.li_attr['class'] = 'topicIndeterminate';
				} else if (treeSize === numSelected && numSelected > 0) {
					currentNode.li_attr['class'] = 'topicSelected';
				} else {
					currentNode.li_attr['class'] = 'topicNotSelected';
				}
			}
		} else {
			currentNode.li_attr['class'] = 'topicIndeterminate';
		}
	}
	if (doRefresh) {
		newJSONdata = $('#contentTree').jstree(true).get_json();
		$('#contentTree').jstree('destroy');
		buildContentTree(newJSONdata, true);
	}
}

function refreshCheckboxes(refreshDB) {
	var nodeArray = $('#contentTree').jstree(true)._model.data['#'].children_d;
	var defaultClass = 'fa fa-stack-1x checkbox';
	var idArray = [];
	$(nodeArray).each(function() {
		var icon = $('#select' + this.substr(7));
		if ($('#' + this).hasClass('topicSelected')) {
			icon.attr('class', defaultClass + ' fa-check');
			idArray.push(this.substr(7));
		} else if ($('#' + this).hasClass('topicIndeterminate')) {
			icon.attr('class', defaultClass + ' fa-minus');
		} else {
			icon.attr('class', defaultClass);
		}
	});
	if (refreshDB) {
		setContents(idArray);
	}
}

function moveContent(contentID, parentID) {
	$.ajax({
		url: '../../content/updateHierarchy',
		type: 'POST',
		dataType: 'json',
		data: {
			contentID: contentID,
			parentID: parentID
		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

function setContents(idArray) {
	var objectiveID = $('input[name=learningObjectiveID]').val();
	idArray = JSON.stringify(idArray);
	$.ajax({
		url: '../../content/setLearningObjective',
		type: 'POST',
		dataType: 'json',
		data: {
			objectiveID: objectiveID,
			idArray: idArray
		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

// FIXME if unused remove topicList parameter
function populateTopics(topicList) {
	var contentTree = $.jstree.reference('#contentTree');
	$('#topicList tbody').html('');
	$(topicList).each(function() {
		contentTree.create_node(
			'#', {
				'id': 'content' + this.contentID,
				'text': this.topicTitle
			}
		);
	});
	$('#contentTree').jstree('open_all');
}
