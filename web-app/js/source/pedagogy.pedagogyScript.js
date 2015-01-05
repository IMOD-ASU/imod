'use strict';

$(document).ready(
	function () {
		$.browser = {};
		(function () {
			$.browser.msie = false;
			$.browser.version = 0;
			if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
				$.browser.msie = true;
				$.browser.version = RegExp.$1;
			}
		})();
		$("#topicTreeView").treeview({
			control: "#treecontrol"
		});
		$("#addLearningObjectiveDiv").dialog({
			autoOpen: false,
			height: 300,
			width: 500,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$("#removeTopicForm").dialog({
			autoOpen: false,
			height: 300,
			width: 500,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$(".topic_information_widget").dialog({
			autoOpen: false,
			height: 400,
			width: 600,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$("#showTopicScheduleTable").dialog({
			autoOpen: false,
			height: 350,
			width: 600,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$("#showTechniqueTable").dialog({
			autoOpen: false,
			height: 350,
			width: 600,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$("#showResourcesDiv").dialog({
			autoOpen: false,
			height: 400,
			width: 1000,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$("#addTopicForm").dialog({
			autoOpen: false,
			height: 300,
			width: 500,
			show: "fadein",
			hide: "fadeout",
			modal: true
		});
		$('.pc3_informationIcon').pc3_hover({
			show: 'mouseover',
			hide: 'mouseout',
			style: {
				'background-color': '#efefef',
				tip: 'bottomMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
			},
			position: {
				adjust: {
					x: 3,
					y: -38
				}
			}
		});

		$('.informationIcon').qtip({
			show: 'mouseover',
			hide: 'mouseout',
			style: {
				'background-color': '#efefef',
				tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
			},
			position: { adjust: { x: 3, y: -38 } }
		});
		$('.showHover').qtip({
			content: $('.topic_addition_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		$('.topic_hierarchy_widget').qtip({
			content: $('.topic_hierarchy_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		$('.topic_information_widget').qtip({
			content: $('.topic_information_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		$('.topic_schedule_widget').qtip({
			content: $('.topic_schedule_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		updateResourceURL();
		$('.topic_information_widget2').hide();
	});
	function addObjective() {
		$('#addObjectiveName').val('');
		$('#addObjectiveName').show();
		$('#addObjectiveName').focus();
		$('#addObjectiveName').on('keyup', function (e) {
			if (e.keyCode == 13) {
				saveObjective();
			}
		});
	}

	function saveObjective() {
		$.ajax({
			// FIXME broken URL
			// url: '${createLink( \
			// 	controller: "learningObjective", \
			// 	action: "ajaxSave" \
			// )}',
			method: 'POST',
			data: {
				'name': $('#addObjectiveName').val(),
				// FIXME get imod id
				//'imod.id': ${imod?.id}
			},
			success: function(data) {
				if (data) {
					$('#chapterListUL').append(data);
					$('#addObjectiveName').val('');
					$('#addObjectiveName').hide();
					if ($("#chapterListUL li").length > 0) {
						// FIXME fix url
						// window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?objectiveId=${params.objectiveId?:chapter?.id}&loadContentTab=${true}"
					}
				}
			}
		});
		return false;
	}

	function removeTopic() {
		if ($('#removeTopicForm #contents').length) {
			// FIXME replace deprecated confirm call
			if (confirm('Are you sure?')) {
				$.ajax({
					// FIXME fix url
					//url: '${createLink(controller: 'content',action:'ajaxDelete')}' + '/' + $('#topic_table tbody tr:first input[type="hidden"]').val(),
					method: 'POST',
					data: {
						'contents': $('#removeTopicForm #contents').val()
					},
					success: function (data) {
						if (data == 'success') {
							// FIXME fix url
							// window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?objectiveId=${params.objectiveId?:chapter?.id}&loadContentTab=${true}"
						}
					}
				});
			}
		} else {
			alert('No topic found');
		}
	}

	function addTopic() {
		$('#topic_table tbody tr:first').remove();
	}

	function closeRemoveTopicPopup() {
		$("#removeTopicForm").dialog("close");
		return false;
	}

	function openRemoveTopicPopup() {
		$("#removeTopicForm").dialog("open");
	}

	function openTopicDistributionPopup() {
		if ("${params.objectiveId?true:chapter?.id?true:false}" == "true") {
			$.ajax({
				// FIXME fix URL
				// url: '${createLink( \
				// 	controller: "content", \
				// 	action: "fetchGraph" \
				// )}',
				data: {
					// FIXME get correct variables
					'objectiveId': '${params.objectiveId?:chapter?.id}',
					'id': '${imod?.id}'
				},
				success: function (data) {
					if (data) {
						$('.topic_information_widget').append(data);
						$(".topic_information_widget").dialog("open");
					}
				}
			});
		}

		$(".topic_information_widget").dialog("open");
	}


	function closeTopicDistributionPopup() {
		$(".topic_information_widget").dialog("close");
	}

	function addResourceField() {
		// FIXME avoid markup in JS
		$('#showResourcesTable').append("<tr><td></td><td><input type='text' name='resource' value=''/></td></tr>");
	}

	function changePreReq(contentId) {
		var topicTitle = $('tr#' + contentId + ' #topicTitle').val();
		var priorityCode = $('tr#' + contentId + ' #priorityCodeType').val();
		var learningObjectiveType = $('tr#' + contentId + ' #learningObjectiveType').val();
		$.ajax({
			// FIXME fix URL
			// url: '${createLink( \
			// 	controller: "content", \
			// 	action: "changePreReq" \
			// )}',
			method: "POST",
			data: {
				topicTitle: topicTitle,
				contentId: contentId,
				priorityCode: priorityCode,
				learningObjectiveType: learningObjectiveType
			},
			success: function (data) {
				if (data == "success") {
					window.location.reload();
				}
			}
		})
	}

	function closeAddTopicPopup() {
		$("#addTopicForm").dialog("close");
		return false;
	}

	function updateCurrentTopic(id) {
		changePreReq(id);
		return false;
	}

	function removeResource(id, contentResourceIdElement) {
		if (id) {
			// FIXME remove deprecated confirm call
			if (confirm("Are you sure?")) {
				$.ajax({
					// FIXME fix URL
					// url: '${createLink( \
					// 	controller: "content", \
					// 	action: "removeResource" \
					// )}',
					data: {
						resourceId: id
					},
					success: function (data) {
						if (data == "success") {
							$('tr#' + contentResourceIdElement).remove()
						}
					}
				});
			}
		} else {
			alert("No resource found.");
		}
		return false;
	}

	function removeTopicSchedule(id, topicScheduleElement) {
		if (id) {
			// FIXME remove deprecated confirm call
			if (confirm("Are you sure?")) {
				$.ajax({
					// FIXME fix URL
					//url: '${createLink(controller: 'content',action:'removeTopicSchedule')}',
					data: {
						topicScheduleId: id
					},
					success: function (data) {
						if (data == "success") {
							$('tr#' + topicScheduleElement).remove()
						}
					}
				});
			}
		} else {
			alert("No topic schedule found.");
		}
		return false;
	}

	function openAddTopicPopup() {
		$("#addTopicForm").dialog("open");
	}

	function addNewSubTopic() {
		// FIXME remove markup inside of JS
		$('#addTopicPopupTable').append('<tr><td></td><td><input type="text" name="subTopic" id="subTopic"/></td></tr>');
	}

	function showObjective() {
		$('#addObjectiveName').val('');
		$("#addLearningObjectiveDiv").dialog("open");
		return false;
	}

	function showResourcesPopup(topicId) {
		$('#currentContentIdForResource').val(topicId);
		$.ajax({
			// FIXME fix URL
			//url: '${createLink(controller: 'content',action: 'fetchResource')}',
			method: "POST",
			data: {topicId: topicId},
			success: function (data) {
				$(".existingResources").remove();
				$('#showResourcesTable tbody').append(data);
				updateResourceURL();
				$("#showResourcesDiv").dialog("open");
			}
		});
		return false;
	}

	function addTopicScheduleAfter() {
		$("tbody#showTopicScheduleBody").after($('#templateForTopicSchedule table').html());
		return false;
	}

	function removeTopicScheduleBefore() {
		var contentScheduleId = $('#topicScheduleMainTable tbody tr:first').attr('dir');
		if (contentScheduleId == '0') {
			$('#topicScheduleMainTable tbody tr:first').remove();
		} else {
			removeTopicSchedule(contentScheduleId, $('#topicScheduleMainTable tbody tr:first').attr('id'));
		}
	}

	function addResourceBefore() {
		if ($('#showResourcesTable tbody tr:first').size() != 0) {
			// FIXME avoid markup in JS
			$('#showResourcesTable tbody tr:first').before("<tr  class='contentResources' dir='0'><td><input type='hidden' value='0' name='resourceIds'/><input type='text' name='resourceNames' class='resourceNames' value=''/></td><td><input type='file' name='files' class='files' disabled='disabled' style='display: none;'/><input type='text' name='resourceURLs' class='resourceURLs' value='' style='display: none;' /></td><td><select style='padding:0;' name='types' class='types' onchange='updateResourceURL()'><option value=''>Select one</option><option value=\"Document\">Document</option><option value=\"Book\">Book</option><option value=\"Chapter\">Chapter</option><option value=\"URL\">URL</option></select></td></tr>")
		} else {
			// FIXME avoid markup in JS
			$('#showResourcesTable tbody').append("<tr  class='contentResources' dir='0'><td><input type='hidden' value='0' name='resourceIds'/><input type='text' name='resourceNames' class='resourceNames' value=''/></td><td><input type='file' name='files' class='files' disabled='disabled' style='display: none;'/><input type='text' name='resourceURLs' class='resourceURLs' value='' style='display: none;' /></td><td><select name='types' class='types' style='padding:0;' onchange='updateResourceURL()'><option value=''>Select one</option><option value=\"Document\">Document</option><option value=\"Book\">Book</option><option value=\"Chapter\">Chapter</option><option value=\"URL\">URL</option></select></td></tr>")
		}
		return false;
	}

	function removeResourceBefore() {
		var resourceId = $('#showResourcesTable tbody tr:first').attr('dir');
		if (resourceId == '0') {
			$('#showResourcesTable tbody tr:first').remove();
		} else {
			removeResource(resourceId, $('#showResourcesTable tbody tr:first').attr('id'));
		}
	}

	function closeResourcesPopup() {
		$("#showResourcesDiv").dialog("close");
		return false;
	}

	function showTopicSchedule() {
		if ("${contentList ?true:false}" == "true") {
			// FIXME function not defined in file
			addCssToSchedule();
			$("#showTopicScheduleTable").dialog("open");
		} else {
			alert("No topic found.")
		}
		return false;
	}

	function openAddNewForm() {
		alert("I am here")
		if (true) {
			addCssToSchedule();
			$("#showTechniqueTable").dialog("open");
		} else {
			alert("No topic found.")
		}
		return false;
	}


	function saveTopicScheduleTable() {
		var id = '${params.id}';
		$.ajax({
			// FIXME fix URL
			//url: '${createLink(controller: 'content',action:'saveTopicSchedule')}',
			method: "POST",
			data: {
				topicId: $('#scheduleTopicSelected').val(),
				id: id,
				scheduleDate: $('#scheduleTopicSelected_input').val()
			},
			success: function (data) {
				if (data == "success") {
					closeLearningObjective();
					// FIXME fix URL
					// window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
				}
			}
		});
	}

	function saveLearningObjective() {
		var id = '${params.id}';
		$.ajax({
			// FIXME fix URL
			// url: '${createLink(controller: 'content',action:'saveObjectives')}',
			method: "POST",
			data: {
				name: $('#addObjectiveName').val(),
				id: id
			},
			success: function (data) {
				if (data == "success") {
					closeLearningObjective();
					// FIXME fix URL
					// window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
				}
			}
		});
	}

	function closeTopicScheduleTable() {
		$("#showTopicScheduleTable").dialog("close");
		return false;
	}

	function closeLearningObjective() {
		$("#addLearningObjectiveDiv").dialog("close");
		return false;
	}

	function addCssToSchedule() {
		$.each(
			$('select[name="scheduleDate_day"]'),
			function(){
				$(this).attr('style','padding:0;');
			}
		);
		$.each(
			$('select[name="scheduleDate_month"]'),
			function(){
				$(this).attr('style','padding:0;');
			}
		);
		$.each(
			$('select[name="scheduleDate_year"]'),
			function(){
				$(this).attr('style','padding:0;');
			}
		);
	}

	function removeAllObjective() {
		var id = '${params.id}';
		// FIXME remove deprecated confirm call
		if (confirm("Are you sure?")) {
			$.ajax({
				// FIXME fix URL
				// url: '${createLink(controller: 'content',action:'removeAllObjectives')}',
				method: "POST",
				data: {id: id},
				success: function (data) {
					if (data == "success") {
						// FIXME fix URL
						// window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
					}
				}
			});
		}
	}

	function updateResourceURL() {
		$.each($('.contentResources'), function (key, value) {
			var type = $(value).children().find('.types').val();
			if (type == "URL") {
				$(this).children().find('.files').hide();
				$(this).children().find('.resourceURLs').show();
			} else {
				$(this).children().find('.files').show();
				$(this).children().find('.resourceURLs').hide();
			}
		});
	}
