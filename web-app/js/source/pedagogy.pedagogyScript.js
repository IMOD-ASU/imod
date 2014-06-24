jQuery(document).ready(function () {
		jQuery.browser = {};
		(function () {
			jQuery.browser.msie = false;
			jQuery.browser.version = 0;
			if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
				jQuery.browser.msie = true;
				jQuery.browser.version = RegExp.$1;
			}
		})();
		jQuery("#topicTreeView").treeview({control: "#treecontrol"});
		jQuery("#addLearningObjectiveDiv").dialog({ autoOpen: false, height: 300, width: 500, show: "fadein", hide: "fadeout", modal: true });
		jQuery("#removeTopicForm").dialog({ autoOpen: false, height: 300, width: 500, show: "fadein", hide: "fadeout", modal: true });
		jQuery(".topic_information_widget").dialog({ autoOpen: false, height: 400, width: 600, show: "fadein", hide: "fadeout", modal: true });
		jQuery("#showTopicScheduleTable").dialog({ autoOpen: false, height: 350, width: 600, font-size: 14px; show: "fadein", hide: "fadeout", modal: true });
		jQuery("#showTechniqueTable").dialog({ autoOpen: false, height: 350, width: 600, show: "fadein", hide: "fadeout", modal: true });
		jQuery("#showResourcesDiv").dialog({ autoOpen: false, height: 400, width: 1000, show: "fadein", hide: "fadeout", modal: true });
		jQuery("#addTopicForm").dialog({ autoOpen: false, height: 300, width: 500, show: "fadein", hide: "fadeout", modal: true });

		jQuery('.pc3_informationIcon').pc3_hover({
			show: 'mouseover',
			hide: 'mouseout',
			style: {
				'background-color': '#efefef',
				tip: 'bottomMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
			},
			position: { adjust: { x: 3, y: -38 } }
		});

		jQuery('.informationIcon').qtip({
			show: 'mouseover',
			hide: 'mouseout',
			style: {
				'background-color': '#efefef',
				tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
			},
			position: { adjust: { x: 3, y: -38 } }
		});
		jQuery('.showHover').qtip({
			content: jQuery('.topic_addition_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		jQuery('.topic_hierarchy_widget').qtip({
			content: jQuery('.topic_hierarchy_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		jQuery('.topic_information_widget').qtip({
			content: jQuery('.topic_information_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		jQuery('.topic_schedule_widget').qtip({
			content: jQuery('.topic_schedule_widget').attr('title'),
			show: 'mouseover',
			hide: 'mouseout',
			position: {
				type: 'static',
				container: $('div#qtipPlace')
			}
		});
		updateResourceURL();
		jQuery('.topic_information_widget2').hide();
	});
	function addObjective() {
		jQuery('#addObjectiveName').val('');
		jQuery('#addObjectiveName').show();
		jQuery('#addObjectiveName').focus();
		jQuery('#addObjectiveName').on('keyup', function (e) {
			if (e.keyCode == 13) {
				saveObjective();
			}
		});
	}

	function saveObjective() {
		jQuery.ajax({
			url: '${createLink(controller: 'learningObjective',action: 'ajaxSave')}',
			method: 'POST',
			data: {'name': jQuery('#addObjectiveName').val(), 'imod.id':${imod?.id}},
			success: function (data) {
				if (data) {
					jQuery('#chapterListUL').append(data);
					jQuery('#addObjectiveName').val('');
					jQuery('#addObjectiveName').hide();
					if (jQuery("#chapterListUL li").length > 0) {
						window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?objectiveId=${params.objectiveId?:chapter?.id}&loadContentTab=${true}"
					}
				}
			}
		});
		return false;
	}

	function removeTopic() {
		if (jQuery('#removeTopicForm #contents').length) {
			if (confirm('Are you sure?')) {
				jQuery.ajax({
					url: '${createLink(controller: 'content',action:'ajaxDelete')}' + '/' + jQuery('#topic_table tbody tr:first input[type="hidden"]').val(),
					method: 'POST',
					data: {'contents': jQuery('#removeTopicForm #contents').val()},
					success: function (data) {
						if (data == 'success') {
							window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?objectiveId=${params.objectiveId?:chapter?.id}&loadContentTab=${true}"
						}
					}
				})
			}
		} else {
			alert('No topic found');
		}
	}

	function addTopic() {
		jQuery('#topic_table tbody tr:first').remove();
	}

	function closeRemoveTopicPopup() {
		jQuery("#removeTopicForm").dialog("close");
		return false;
	}

	function openRemoveTopicPopup() {
		jQuery("#removeTopicForm").dialog("open");
	}

	function openTopicDistributionPopup() {
		if ("${params.objectiveId?true:chapter?.id?true:false}" == "true") {
			jQuery.ajax({
				url: '${createLink(controller: 'content',action: 'fetchGraph')}',
				data: {'objectiveId': '${params.objectiveId?:chapter?.id}', 'id': '${imod?.id}'},
				success: function (data) {
					if (data) {
						jQuery('.topic_information_widget').append(data);
						jQuery(".topic_information_widget").dialog("open");
					}
				}
			})
		}

		jQuery(".topic_information_widget").dialog("open");
	}


	function closeTopicDistributionPopup() {
		jQuery(".topic_information_widget").dialog("close");
	}

	function addResourceField() {
		jQuery('#showResourcesTable').append("<tr><td></td><td><input type='text' name='resource' value=''/></td></tr>");
	}

	function changePreReq(contentId) {
		var topicTitle = jQuery('tr#' + contentId + ' #topicTitle').val();
		var priorityCode = jQuery('tr#' + contentId + ' #priorityCodeType').val();
		var learningObjectiveType = jQuery('tr#' + contentId + ' #learningObjectiveType').val();
		jQuery.ajax({
			url: '${createLink(controller: 'content',action:'changePreReq')}',
			method: "POST",
			data: {topicTitle: topicTitle, contentId: contentId, priorityCode: priorityCode, learningObjectiveType: learningObjectiveType},
			success: function (data) {
				if (data == "success") {
					window.location.reload();
				}
			}
		})
	}

	function closeAddTopicPopup() {
		jQuery("#addTopicForm").dialog("close");
		return false;
	}

	function updateCurrentTopic(id) {
		changePreReq(id);
		return false;
	}

	function removeResource(id, contentResourceIdElement) {
		if (id) {
			if (confirm("Are you sure?")) {
				jQuery.ajax({
					url: '${createLink(controller: 'content',action:'removeResource')}',
					data: {resourceId: id},
					success: function (data) {
						if (data == "success") {
							jQuery('tr#' + contentResourceIdElement).remove()
						}
					}
				});
			}
		} else {
			alert("No resource found.")
		}
		return false;
	}

	function removeTopicSchedule(id, topicScheduleElement) {
		if (id) {
			if (confirm("Are you sure?")) {
				jQuery.ajax({
					url: '${createLink(controller: 'content',action:'removeTopicSchedule')}',
					data: {topicScheduleId: id},
					success: function (data) {
						if (data == "success") {
							jQuery('tr#' + topicScheduleElement).remove()
						}
					}
				});
			}
		} else {
			alert("No topic schedule found.")
		}
		return false;
	}

	function openAddTopicPopup() {
		jQuery("#addTopicForm").dialog("open");
	}

	function addNewSubTopic() {
		jQuery('#addTopicPopupTable').append('<tr><td></td><td><input type="text" name="subTopic" id="subTopic"/></td></tr>')
	}

	function showObjective() {
		jQuery('#addObjectiveName').val('');
		jQuery("#addLearningObjectiveDiv").dialog("open");
		return false;
	}

	function showResourcesPopup(topicId) {
		jQuery('#currentContentIdForResource').val(topicId);
		jQuery.ajax({
			url: '${createLink(controller: 'content',action: 'fetchResource')}',
			method: "POST",
			data: {topicId: topicId},
			success: function (data) {
				jQuery(".existingResources").remove();
				jQuery('#showResourcesTable tbody').append(data);
				updateResourceURL();
				jQuery("#showResourcesDiv").dialog("open");
			}
		});
		return false;
	}

	function addTopicScheduleAfter() {
		jQuery("tbody#showTopicScheduleBody").after(jQuery('#templateForTopicSchedule table').html());
		return false;
	}

	function removeTopicScheduleBefore() {
		var contentScheduleId = jQuery('#topicScheduleMainTable tbody tr:first').attr('dir');
		if (contentScheduleId == '0') {
			jQuery('#topicScheduleMainTable tbody tr:first').remove();
		} else {
			removeTopicSchedule(contentScheduleId, jQuery('#topicScheduleMainTable tbody tr:first').attr('id'));
		}
	}

	function addResourceBefore() {
		if (jQuery('#showResourcesTable tbody tr:first').size() != 0) {
			jQuery('#showResourcesTable tbody tr:first').before("<tr  class='contentResources' dir='0'><td><input type='hidden' value='0' name='resourceIds'/><input type='text' name='resourceNames' class='resourceNames' value=''/></td><td><input type='file' name='files' class='files' disabled='disabled' style='display: none;'/><input type='text' name='resourceURLs' class='resourceURLs' value='' style='display: none;' /></td><td><select style='padding:0;' name='types' class='types' onchange='updateResourceURL()'><option value=''>Select one</option><option value=\"Document\">Document</option><option value=\"Book\">Book</option><option value=\"Chapter\">Chapter</option><option value=\"URL\">URL</option></select></td></tr>")
		} else {
			jQuery('#showResourcesTable tbody').append("<tr  class='contentResources' dir='0'><td><input type='hidden' value='0' name='resourceIds'/><input type='text' name='resourceNames' class='resourceNames' value=''/></td><td><input type='file' name='files' class='files' disabled='disabled' style='display: none;'/><input type='text' name='resourceURLs' class='resourceURLs' value='' style='display: none;' /></td><td><select name='types' class='types' style='padding:0;' onchange='updateResourceURL()'><option value=''>Select one</option><option value=\"Document\">Document</option><option value=\"Book\">Book</option><option value=\"Chapter\">Chapter</option><option value=\"URL\">URL</option></select></td></tr>")
		}
		return false;
	}

	function removeResourceBefore() {
		var resourceId = jQuery('#showResourcesTable tbody tr:first').attr('dir');
		if (resourceId == '0') {
			jQuery('#showResourcesTable tbody tr:first').remove();
		} else {
			removeResource(resourceId, jQuery('#showResourcesTable tbody tr:first').attr('id'));
		}
	}

	function closeResourcesPopup() {
		jQuery("#showResourcesDiv").dialog("close");
		return false;
	}

	function showTopicSchedule() {
		if ("${contentList ?true:false}" == "true") {
			addCssToSchedule();
			jQuery("#showTopicScheduleTable").dialog("open");
		} else {
			alert("No topic found.")
		}
		return false;
	}

	function openAddNewForm() {
		alert("I am here")
		if (true) {
			addCssToSchedule();
			jQuery("#showTechniqueTable").dialog("open");
		} else {
			alert("No topic found.")
		}
		return false;
	}


	function saveTopicScheduleTable() {
		var id = '${params.id}';
		jQuery.ajax({
			url: '${createLink(controller: 'content',action:'saveTopicSchedule')}',
			method: "POST",
			data: {topicId: jQuery('#scheduleTopicSelected').val(), id: id, scheduleDate: jQuery('#scheduleTopicSelected_input').val()},
			success: function (data) {
				if (data == "success") {
					closeLearningObjective();
					window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
				}
			}
		});
	}

	function saveLearningObjective() {
		var id = '${params.id}';
		jQuery.ajax({
			url: '${createLink(controller: 'content',action:'saveObjectives')}',
			method: "POST",
			data: {name: jQuery('#addObjectiveName').val(), id: id},
			success: function (data) {
				if (data == "success") {
					closeLearningObjective();
					window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
				}
			}
		});
	}

	function closeTopicScheduleTable() {
		jQuery("#showTopicScheduleTable").dialog("close");
		return false;
	}

	function closeLearningObjective() {
		jQuery("#addLearningObjectiveDiv").dialog("close");
		return false;
	}

	function addCssToSchedule() {
		jQuery.each(jQuery('select[name="scheduleDate_day"]'),function(){jQuery(this).attr('style','padding:0;')})
		jQuery.each(jQuery('select[name="scheduleDate_month"]'),function(){jQuery(this).attr('style','padding:0;')})
		jQuery.each(jQuery('select[name="scheduleDate_year"]'),function(){jQuery(this).attr('style','padding:0;')})
	}

	function removeAllObjective() {
		var id = '${params.id}';
		if (confirm("Are you sure?")) {
			jQuery.ajax({
				url: '${createLink(controller: 'content',action:'removeAllObjectives')}',
				method: "POST",
				data: {id: id},
				success: function (data) {
					if (data == "success") {
						window.location.href = "${createLink(controller: 'imod',action: 'edit',id: params.id?:imod?.id)}?loadContentTab=${true}"
					}
				}
			});
		}
	}

	function updateResourceURL(resourceTR) {
		jQuery.each(jQuery('.contentResources'), function (key, value) {
			var type = jQuery(value).children().find('.types').val();
			if (type == "URL") {
				jQuery(this).children().find('.files').hide();
				jQuery(this).children().find('.resourceURLs').show();
			} else {
				jQuery(this).children().find('.files').show();
				jQuery(this).children().find('.resourceURLs').hide();
			}
		});
	}
