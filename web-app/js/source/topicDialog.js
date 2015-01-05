'use strict';

var errorMessages=[];
var isFlashing=null;
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
	$('#removeTopic').click(function(){
		var contentIDs=[];
		$("#topicList .selected").each(function(){
			contentIDs.push(this.id);
		});
		deleteTopic(contentIDs);
	});
	$("#topicList > tbody").on("click","tr",toggleSelected);
	$('#selectKnowledgeDimensions').on('change','input:checkbox',changePic);
	$("#topicList > tbody").on("change","input",function(){
		var id=$(this).parents("tr .topicItem").attr('id');
		highlightUnsaved(id);
	});
	$("#topicList > tbody").on("change","select",function(){
		var id=$(this).parents("tr .topicItem").attr('id');
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

function highlightUnsaved(id){
	$("#"+id).addClass("unsaved");
}
function deleteTopic(contentIDs){
	var imodID=$("#imodID").val();
	contentIDs=JSON.stringify(contentIDs);
	$.ajax({
		url:"../../content/deleteTopic/",
		type:"GET",
		dataType:"json",
		data: {id:imodID,
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
		// success: function(data) {
		// 	location.reload();
		// 	// data.success.forEach(function(element){
		// 	// 	$("#"+element).removeClass("unsaved");
		// 	// 	refreshSaves();
		// 	// 	populateTopics(topicList);
		// 	// 	hideTopicDialog();
		// 	// });
		// },
		error: function(xhr){
			alert(xhr.responseText);
		}
	});

}
function refreshSaves(){
	$("#topicList tbody tr").each(function(){
		var rowData=getTopicSavedItems(this);
		$(rowData.titleSaved).val($(rowData.title).val());
		$(rowData.dimensionsSaved).val($(rowData.dimensions).val());
		$(rowData.prioritySaved).val($(rowData.priority).val());
		$(rowData.preReqSaved).val($(rowData.preReq).val());
	});
}
function revertChanges(){
	$("#topicList tbody tr").each(function(){
		var rowData=getTopicSavedItems(this);
		var dimensions=[];
		var dimensionShort="";
		var icon="";
		var contentIDs=[];

		if($(rowData.dimensionsSaved).val()===""){
			contentIDs.push(this.id);
		}
		else{
			$(rowData.title).val($(rowData.titleSaved).val());
			$(rowData.dimensions).val($(rowData.dimensionsSaved).val());
			$(rowData.priority).val($(rowData.prioritySaved).val());
			$(rowData.preReq).val($(rowData.preReqSaved).val());
			$("#"+this.id).removeClass("unsaved");
			dimensions=$(rowData.dimensions).val().split(",");
			$(dimensions).each(function(){
				dimensionShort+=this.charAt(0);
			});
			if (dimensionShort===""){
				icon=$("#imgNone").attr('href');
			}
			else{
				icon=$("#img"+dimensionShort).attr('href');
			}
			$(rowData.dimensions).siblings("img").attr("src",icon);
		}
		deleteTopic(contentIDs);

	});
}
function getTopicSavedItems(currentRow){
	var topicID=currentRow.id;
	var rowData={
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
function addTopic(){
	var imodID=$("#imodID").val();
	$.ajax({
		url:"../../content/addNewTopic",
		type:"GET",
		dataType:"json",
		data:{id:imodID},
		success:function(data){
			var dimensions=data.dimensions;
			var priorities=data.priorities;
			var id=data.id;
			var dimensionOptions='';
			var prioritiesOptions='';
			var topicDiv=$('#topicList tbody');

			for (var i=0;i<dimensions.length;i++){
				dimensionOptions+='<option value="'+dimensions[i]+'">'+dimensions[i]+'</option>';
			}
			for (i=0;i<priorities.length;i++){
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
					'<button type="button" id="topicResource'+id+'">Resources</button> '+
				'</td><td class="topicPreReq">'+
					'<input type="checkbox" name="topicPreReq'+id+'"> '+
					'<input type="hidden" name="topicPreReqSaved'+id+'"> '+
				'</td>'+
			'</tr>'
			).appendTo(topicDiv);
			$('#topicTitle'+id).focus();
			$('.knowledgeDimensionButton').click(openDimModal);
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}
