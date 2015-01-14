'use strict';

var errorMessages=[];
var isFlashing=null;
var content = '';

$(function(){
	$("#addTopicModal").click(showTopicDialog);
	$("#addTopic").click(addTopic);
	$('#saveTopic').click(saveTopic);
	$('#cancelTopic').click(function(){
		revertChanges();
		hideTopicDialog();
	});
	$('.knowledgeDimensionButton').click(openDimModal);
	$('#knowDimFinished').click(closeDimModal);
	$('.ResourceButton').click(openResourceModal);
	$('#addResource').click(function(){
		addResource();
	});
	$('#cancelResource').click(closeResourceModal);
	$('#saveResource').click(saveResource);
	$('#removeResource').click(function(){
		var resourceIDs=[];
		$("#resourceList .selected").each(function(){
			resourceIDs.push(this.id);
		});
		deleteResource(resourceIDs);
	});
	$('#removeTopic').click(function(){
		var contentIDs=[];
		$("#topicList .selected").each(function(){
			contentIDs.push(this.id);
		});
		deleteTopic(contentIDs);
	});
	$("#topicList > tbody").on("click", "tr", toggleSelected);
	$("#resourceList > tbody").on("click", "tr", toggleSelected);
	$('#selectKnowledgeDimensions').on('change', 'input:checkbox', changePic);
	$("#topicList > tbody").on("change", "input", function(){
		var id = $(this).parents("tr .topicItem").attr('id');
		highlightUnsaved(id);
	});
	$("#topicList > tbody").on("change", "select", function(){
		var id = $(this).parents("tr .topicItem").attr('id');
		highlightUnsaved(id);
	});

});

function showTopicDialog(){
	$("#topicDialogBackground").css("display","block");
	$("#topicDialog").css("display","block");
}

function hideTopicDialog(){
	$("#topicDialogBackground").css("display","none");
	$("#topicDialog").css("display","none");

}

function errorMessage(message){
	errorMessages.push(message);
	if (isFlashing===null){
		flashError();
		isFlashing=setInterval(flashError,4000);
	}
}

function flashError(){
	var message=errorMessages.shift();
	$("#errorMessage").text(message);
	$("#errorMessage").fadeIn("fast").delay(3000).fadeOut("slow","swing",function(){
		if (errorMessages.length===0){
			clearInterval(isFlashing);
			isFlashing=null;
		}
	});
}

function changePic(imageToChange){
	var iconName="";
	$('#selectKnowledgeDimensions').find('input:checkbox').each(function(){
		if ($(this).prop('checked')){
			iconName=iconName+$(this).val().charAt(0);
		}
	});
	if (iconName===""){
		iconName=$('#imgNone').attr('href');
	}
	else{
		iconName=$('#img'+iconName).attr('href');
	}
	$('#dimImage').attr('src',iconName);
}

function toggleSelected(){
	if (!((event.target.nodeName=='OPTION')||
			(event.target.nodeName=="INPUT")||
			(event.target.nodeName=="BUTTON")||
			(event.target.nodeName=="SELECT"))){
		$(this).find(".saveIcon > i").toggleClass("hidden");
		$(this).toggleClass("selected");
	}
}

function closeDimModal(){
	var contentID=$("#topicID").val();
	var dimensions=[];
	var dialog=$("#selectKnowledgeDimensions");
	var background=$("#selectKnowledgeDimensionBackground");
	var contentDimensions=$("#knowDimensionList"+contentID);

	$(this).siblings('span').find('input').each(function() {
		if (this.checked) {
			dimensions.push($(this).val());
		}
	});
	if (dimensions.length===0){
		dimensions="";
	}
	else{
		dimensions=dimensions.toString();
	}
	$("#topicID").val("");
	dialog.find('input:checkbox').each(function(){
		$(this).prop('checked',false);
	});

	if (contentDimensions.val()!=dimensions){
		contentDimensions.val(dimensions);
		$("#"+contentID).addClass("unsaved");
	}
	$("#"+contentID).find('img').attr('src',$('#dimImage').attr('src'));
	dialog.css("display","none");
	background.css("display","none");
}

function openDimModal(){
	var contentID=$(this).parents('.topicItem').attr('id');
	var dimString=$("#knowDimensionList"+contentID).val();
	var dimensionList=[];
	var dialog=	$("#selectKnowledgeDimensions");
	var background=$("#selectKnowledgeDimensionBackground");

	if (dimString!==""){
		dimensionList=dimString.split(",");
	}
	for(var i=0;i<dimensionList.length;i++){
		var findCheckBox=$(dialog).find("#"+dimensionList[i]);
		if (findCheckBox.length==1){
			findCheckBox.prop('checked',true);
		}
	}
	changePic();
	$("#topicID").val(contentID);
	dialog.css("display","inherit");
	background.css("display","block");
}

function openResourceModal() {
	content = this.id;
	$("#selectResource").css("display", "inherit");
	$("#selectResourceBackground").css("display", "block");
	getResource();
}

function closeResourceModal() {
	$("#selectResourceBackground").css("display","none");
	$("#selectResource").css("display","none");

}

function highlightUnsaved(id){
	$("#"+id).addClass("unsaved");
}

function deleteTopic(contentIDs){
	contentIDs=JSON.stringify(contentIDs);
	$.ajax({
		url:"../../content/deleteTopic/",
		type:"GET",
		dataType:"json",
		data: {
			contentIDs:contentIDs
			},
		success: function(data){
			data.result.forEach(function(element){
				$("#"+element).remove();
			});
		},
	});
}

function saveTopic(){
	var imodID=$("#imodID").val();
	var contentData=[];
	var hasError=false;
	var topicList=[];
	$("#topicList tbody tr").each(function(){
		var contentID=this.id;
		var topicTitle=$("#topicTitle"+contentID).val();
		var dimensions=$("#knowDimensionList"+contentID).val();
		var priority=$("#topicPriority"+contentID).val();
		var preReq=$("#topicPreReq"+contentID).is(':checked');
		if (dimensions===""){
			errorMessage("Topic: "+topicTitle+" must have a Knowledge Dimension!");
			hasError=true;
		}
		if (topicTitle===""){
			errorMessage("Topic title is required");
			hasError=true;
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
	});
	if (hasError){
		return;
	}
	contentData=JSON.stringify(contentData);
	$.ajax({
		url:"../../content/saveTopic/",
		type:"POST",
		dataType:"json",
		data: {id:imodID,
			JSONData:contentData
			},
		success: function() {
		 	location.reload();
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});

}

function refreshSaves() {
	$('#topicList tbody tr').each(function(){
		var rowData=getTopicSavedItems(this);
		$(rowData.titleSaved).val($(rowData.title).val());
		$(rowData.dimensionsSaved).val($(rowData.dimensions).val());
		$(rowData.prioritySaved).val($(rowData.priority).val());
		$(rowData.preReqSaved).val($(rowData.preReq).val());
	});
}

function revertChanges() {
	$("#topicList tbody tr").each(function(){
		var rowData = getTopicSavedItems(this);
		var dimensions = [];
		var dimensionShort = '';
		var icon = '';
		var contentIDs = [];

		if($(rowData.dimensionsSaved).val() === ''){
			contentIDs.push(this.id);
		}
		else{
			$(rowData.title).val($(rowData.titleSaved).val());
			$(rowData.dimensions).val($(rowData.dimensionsSaved).val());
			$(rowData.priority).val($(rowData.prioritySaved).val());
			$(rowData.preReq).val($(rowData.preReqSaved).val());
			$('#' + this.id).removeClass('unsaved');
			dimensions=$(rowData.dimensions).val().split(',');
			$(dimensions).each(function(){
				dimensionShort += this.charAt(0);
			});
			if (dimensionShort === ''){
				icon=$('#imgNone').attr('href');
			}
			else{
				icon=$('#img' + dimensionShort).attr('href');
			}
			$(rowData.dimensions).siblings('img').attr('src', icon);
		}
		deleteTopic(contentIDs);

	});
}
function getTopicSavedItems(currentRow) {
	var topicID = currentRow.id;
	var rowData = {
		title: $("#topicTitle"+topicID),
		titleSaved: $("#topicTitleSaved"+topicID),
		dimensions: $("#knowDimensionList"+topicID),
		dimensionsSaved: $("#knowDimensionListSaved"+topicID),
		priority: $("#topicPriority"+topicID),
		prioritySaved: $("#topicPrioritySaved"+topicID),
		preReq: $("#topicPreReq"+topicID),
		preReqSaved: $("#topicPreReq"+topicID),
	};
	return rowData;
}

function addTopic() {
	var imodID=$("#imodID").val();
	$.ajax({
		url: '../../content/addNewTopic',
		type: 'GET',
		dataType: 'json',
		data: {
			id: imodID
		},
		success:function(data){
			var dimensions = data.dimensions;
			var priorities = data.priorities;
			var id = data.id;
			var dimensionOptions = '';
			var prioritiesOptions = '';
			var topicDiv = $('#topicList tbody');

			for (var i = 0; i < dimensions.length; i++){
				dimensionOptions+='<option value="'+dimensions[i]+'">'+dimensions[i]+'</option>';
			}
			for (i = 0; i < priorities.length; i++){
				prioritiesOptions+='<option value="'+priorities[i]+'">'+priorities[i]+'</option>';
			}
			// FIXME move html block out of javascript file
			$('<tr id="'+id+'" class="topicItem">'+
				'<td class="saveIcon">'+
					'<i class="hidden fa fa-eraser"></i>'+
				'</td><td class="topicTitle">'+
					'<input type="text" id="topicTitle'+id+'"> '+
					'<input type="hidden" id="topicTitleSaved'+id+'"> '+
				'</td><td class="topicDimensions">'+
					'<span>'+
						'<img src="'+$("#imgNone").attr('href')+'"/> '+
						'<button '+
							'class="knowledgeDimensionButton" '+
							'value="" '+
							'type="button" '+
							'id="knowDimensionList'+id+'" '+
						'> '+
							' Knowledge Dimensions '+
						'</button> '+
						'<input type="hidden" id="knowDimensionListSaved'+id+'" value=""> '+
					'</span> '+
				'</td><td class="topicPriority">'+
					'<select size="1" name="topicPriority'+id+'" id="topicPriority'+id+'"> '+
					prioritiesOptions+
					'</select> '+
					'<input type="hidden" name="topicPrioritySaved'+id+'"> '+
				'</td><td class="topicResources">'+
					'<button type="button" class="ResourceButton" id="topicResources'+id+'">Resources</button> '+
				'</td><td class="topicPreReq">'+
					'<input type="checkbox" name="topicPreReq'+id+'"> '+
					'<input type="hidden" name="topicPreReqSaved'+id+'"> '+
				'</td>'+
			'</tr>'
			).appendTo(topicDiv);
			$('#topicTitle'+id).focus();
			$('.knowledgeDimensionButton').click(openDimModal);
			$('.ResourceButton').click(openResourceModal);
		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

function addResource() {
	var contentID=content.split("topicResources");
	contentID =contentID[1];
	$.ajax({
		url:"../../content/addResource",
		type:"GET",
		dataType:"json",
		data:{contentID:contentID},
		success:function(data){
			var id = data.id;
			var resources = data.resources;
			var resourceOptions = '';
			var resourceDiv=$('#resourceList tbody');
			for (var i = 0; i < resources.length; i++){
				resourceOptions+='<option value="'+resources[i]+'">'+resources[i]+'</option>';
			}
			// FIXME move html out of JS
			$('<tr id="'+id+'" class="resourceItem">'+
					'<td class="saveIcon">'+
					'<i class="hidden fa fa-eraser"></i>'+
				'</td><td class="resourceName">'+
					'<input type="text" id="resourceName'+id+'"> '+
					'<input type="hidden" id="resourceNameSaved'+id+'"> '+
				'</td><td class="resourceDescription">'+
					'<input type="text" id="resourceDescription'+id+'"> '+
					'<input type="hidden" id="resourceDescriptionSaved'+id+'"> '+
					'</td><td class="resourceType">'+
					'<select size="1" name="resourceType'+id+'" id="resourceType'+id+'"> '+
					resourceOptions+
					'</select> '+
					'<input type="hidden" name="resourceTypeSaved'+id+'"> '+
					'</td></tr>'
					).appendTo(resourceDiv);
		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

function getResource() {
	var contentID=content.split("topicResources");
	contentID = contentID[1];
	var resourceDiv=$('#resourceList tbody');
	resourceDiv.html("");
	var resourceOptions="";
	$.ajax({
		url:"../../content/getResource",
		type:"GET",
		dataType:"json",
		data:{contentID:contentID},
		success:function(data){
			var resources = data.resources;
			var resourceTypes = data.resourceTypes
			for (var i = 0; i < resourceTypes.length; i++){
				resourceOptions += '<option value="' + resourceTypes[i] + '">' + resourceTypes[i] + '</option>';
			}
			$.each( resources, function(key, value) {
				var id = value.id;
				// FIXME move html out of JS
				$('<tr id="' + id + '" class="resourceItem">'+
						'<td class="saveIcon">'+
						'<i class="hidden fa fa-eraser"></i>'+
					'</td><td class="resourceName">'+
						'<input type="text" id="resourceName' + id + '" value="' + value.name + '"> '+
						'<input type="hidden" id="resourceNameSaved' + id + '"> '+
					'</td><td class="resourceDescription">'+
						'<input type="text" id="resourceDescription' + id + '" value="' + value.description + '"> '+
						'<input type="hidden" id="resourceDescriptionSaved'+id+'"> '+
						'</td><td class="resourceType">'+
						'<select size="1" name="resourceType' + id + '" id="resourceType' + id + '"> '+
						resourceOptions+
						'</select> '+

						'<input type="hidden" name="resourceTypeSaved' + id + '"> '+
						'</td></tr>'
						).appendTo(resourceDiv);
				$("#resourceType"+id).val(value.resourceType);
		   });

		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

function saveResource() {
	var imodID = $("#imodID").val();
	var resourceData = [];
	var hasError = false;
	$("#resourceList tbody tr").each(function() {
		var resourceID = this.id;
		var resourceName = $('#resourceName' + resourceID).val();
		var resourceDescription = $('#resourceDescription' + resourceID).val();
		var resourceType = $('#resourceType' + resourceID).val();
		if (resourceDescription === ''){
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
	});
	if (hasError){
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
		success: function() {
			closeResourceModal();
		},
		error: function(xhr) {
			alert(xhr.responseText);
		}
	});
}

function deleteResource(resourceIDs){
	resourceIDs=JSON.stringify(resourceIDs);
	$.ajax({
		url: '../../content/deleteResource/',
		type: 'GET',
		dataType: 'json',
		data: {
			resourceIDs: resourceIDs
		},
		success: function(data){
			data.result.forEach(function(element) {
				$("#"+element).remove();
			});
		},
	});
}
