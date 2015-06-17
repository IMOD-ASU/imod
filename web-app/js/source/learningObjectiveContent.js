'use strict';

function deleteTopicSubTab(contentIDs) {
	contentIDs = JSON.stringify(contentIDs);
	$.ajax({
		url: '../../content/deleteTopic/',
		type: 'GET',
		dataType: 'json',
		data: {
			contentIDs: contentIDs
		},
		success: function () {
			window.location.reload();
		}
	});
}

function getTreeChildren(list, parents, idArray) {
	list.each(function () {
		var item = {};

		var myItem = $(this);

		item.id = myItem.data('itemid');
		item.isChecked = myItem
			.find('.sub-content-tree')
			.find('.checkbox')
			.hasClass('fa-check');

		if (item.isChecked) {
			idArray.push(item.id);
		}

		if ($(this).find('> ul > li').length) {
			var childrenArr = [];
			var children = getTreeChildren(myItem.find('> ul > li'), childrenArr, idArray);
			item.child = children[0];
		} else {
			item.child = '';
		}

		parents.push(item);
	});

	return [parents, idArray];
}

$(
	function () {
		if ($('#contentTree').length) {
			$('#contentTree').sortable();

			$('.delete-topic').click(function () {
				var isDelete = confirm('Are you sure you want to delete this?');

				if (isDelete) {
					var contents = [];
					var contentId = $(this).data('id');
					contents.push(contentId);
					deleteTopicSubTab(contents);
				}

				return false;
			});

			$('.sub-content-tree').click(function () {
				var item = $(this);

				if (item.find('.checkbox').hasClass('fa-check')) {
					item.parent().find('.checkbox').removeClass('fa-check');
				} else {
					item.parent().find('.checkbox').addClass('fa-check');
				}

				return false;
			});

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
						window.location.reload();
					},
					error: function (xhr) {
						console.log(xhr.responseText);
					}
				});
			});
		}
	}
);
