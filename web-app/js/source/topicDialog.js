
var errorMessages=[]
var isFlashing=null
$(function(){
	$("#addTopicModal").click(showTopicDialog)
	$("#addTopic").click(addTopic)
	$('#saveTopic').click(saveTopic)
	$('#cancelTopic').click(hideTopicDialog)
	$('.knowledgeDimensionButton').click(openDimModal)
	$('#knowDimFinished').click(closeDimModal)
	$('#removeTopic').click(deleteTopic)
	$("#topicList > tbody").on("click","tr",toggleSelected)
	$('#selectKnowledgeDimensions').on('change','input:checkbox',changePic)
	$("#topicList > tbody").on("change","input",function(){
		var id=$(this).parents("tr .topicItem").attr('id')
		highlightUnsaved(id)
	})
	$("#topicList > tbody").on("change","select",function(){
		var id=$(this).parents("tr .topicItem").attr('id')
		highlightUnsaved(id)
	})

});
function showTopicDialog(){
	$("#topicDialogBackground").css("display","block")
	$("#topicDialog").css("display","block")
}
function hideTopicDialog(){
	var topicList=[]
	var hasError=false;
	$("#topicList tbody tr").each(function(){ 
		var contentID=this.id
		var topicTitle=$("#topicTitle"+contentID).val()
		var dimensions=$("#knowDimensionList"+contentID).val()
		if (dimensions==""){
			errorMessage("Topic: "+topicTitle+" must have a Knowledge Dimension!")
			hasError=true
		}
		topicList.push({
			contentID: contentID,
			dimensions: dimensions,
			topicTitle: topicTitle
		})
	})
	if (hasError){
		return
	}
	populateTopics(topicList)
	$("#topicDialogBackground").css("display","none")
	$("#topicDialog").css("display","none")

}
function errorMessage(message){
	errorMessages.push(message)
	if (isFlashing==null){
		flashError()
		isFlashing=setInterval(flashError,4000)
	}
}
function flashError(){
	var message=errorMessages.shift()
	$("#errorMessage").text(message)
	$("#errorMessage").fadeIn("fast").delay(3000).fadeOut("slow","swing",function(){
		if (errorMessages.length==0){
			clearInterval(isFlashing)
			isFlashing=null
		}
	})
}
function changePic(){
	var iconName=""
	$('#selectKnowledgeDimensions').find('input:checkbox').each(function(){
		if ($(this).prop('checked')){
			iconName=iconName+$(this).val().charAt(0)
		}
	})
	if (iconName==""){
		iconName=$('#imgNone').attr('href')
	}
	else{
		iconName=$('#img'+iconName).attr('href')
	}
	$('#dimImage').attr('src',iconName)
}
function toggleSelected(){
	if (!((event.target.nodeName=='OPTION')||
			(event.target.nodeName=="INPUT")||
			(event.target.nodeName=="BUTTON")||
			(event.target.nodeName=="SELECT"))){
		$(this).find(".saveIcon > i").toggleClass("hidden")
		$(this).toggleClass("selected")
	}
}
function closeDimModal(){
	var contentID=$("#topicID").val()
	var dimensions=[];
	var dialog=$("#selectKnowledgeDimensions")
	var background=$("#selectKnowledgeDimensionBackground")
	var contentDimensions=$("#knowDimensionList"+contentID)

	$(this).siblings('span').find('input').each(function(){
		if (this.checked)
			dimensions.push($(this).val())
	})
	if (dimensions.length==0){
		dimensions=""
	}
	else{
		dimensions=dimensions.toString()
	}
	$("#topicID").val("")
	dialog.find('input:checkbox').each(function(){
		$(this).prop('checked',false)
	})
	
	if (contentDimensions.val()!=dimensions){
		contentDimensions.val(dimensions)
		$("#"+contentID).addClass("unsaved")
	}
	$("#"+contentID).find('img').attr('src',$('#dimImage').attr('src'))
	dialog.css("display","none")
	background.css("display","none")
}
function openDimModal(){
	var contentID=$(this).parents('.topicItem').attr('id')
	var dimString=$(this).siblings('input').val()
	var dimensionList=[]
	var dialog=	$("#selectKnowledgeDimensions")
	var background=$("#selectKnowledgeDimensionBackground")

	if (dimString!=""){
		dimensionList=dimString.split(",")
	}	
	for(var i=0;i<dimensionList.length;i++){
		var findCheckBox=$(dialog).find("#"+dimensionList[i])
		if (findCheckBox.length==1){
			findCheckBox.prop('checked',true)
		}
	}
	changePic()
	$("#topicID").val(contentID)
	dialog.css("display","inherit")
	background.css("display","block")
}

function highlightUnsaved(id){
	$("#"+id).addClass("unsaved")
}
function deleteTopic(){
	var imodID=$("#imodID").val()
	var contentIDs=[]
	$("#topicList .selected").each(function(){ 
		contentIDs.push(this.id)
	})
	contentIDs=JSON.stringify(contentIDs)
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
			})
		},
	})
}

function saveTopic(){
	var imodID=$("#imodID").val()
	var contentData=[]
	var hasError=false
	$("#topicList tbody tr").each(function(){ 
		var contentID=this.id
		var topicTitle=$("#topicTitle"+contentID).val()
		var dimensions=$("#knowDimensionList"+contentID).val()
		var priority=$("#topicPriority"+contentID).val()
		var preReq=$("#topicPreReq"+contentID).is(':checked')
		if (dimensions==""){
			errorMessage("Topic: "+topicTitle+" must have a Knowledge Dimension!")
			hasError=true
		}
		contentData.push({
			contentID: contentID,
			dimensions: dimensions,
			priority: priority,
			preReq: preReq,
			topicTitle: topicTitle
		})
	})
	if (hasError){
		return
	}
	contentData=JSON.stringify(contentData)
	$.ajax({
		url:"../../content/saveTopic/",
		type:"POST",
		dataType:"json",
		data: {id:imodID,
			JSONData:contentData
			},
		success: function(data){
			data.success.forEach(function(element){
				$("#"+element).removeClass("unsaved")
				hideTopicDialog()
			})
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	})

}
function addTopic(){
	var imodID=$("#imodID").val()
	$.ajax({
		url:"../../content/addNewTopic",
		type:"GET",
		dataType:"json",
		data:{id:imodID},
		success:function(data){
			var dimensions=data.dimensions
			var priorities=data.priorities
			var id=data.id
			var dimensionOptions='';
			var prioritiesOptions='';
			var topicDiv=$('#topicList tbody')
			
			for (var i=0;i<dimensions.length;i++){
				dimensionOptions+='<option value="'+dimensions[i]+'">'+dimensions[i]+'</option>'
			}
			for (var i=0;i<priorities.length;i++){
				prioritiesOptions+='<option value="'+priorities[i]+'">'+priorities[i]+'</option>'
			}
			$('<tr id="'+id+'">'+
				'<td>'+
					'<i class="hidden fa fa-check"></i>'+
				'</td><td>'+
					'<input type="text" name="topicTitle'+id+'"> '+
				'</td><td>'+
					'<select size="1" multiple="true" name="topicDimensions'+id+' id="topicDimensions'+id+'"> '+
					dimensionOptions+
					'</select> '+
				'</td><td>'+
					'<select size="3" name="topicPriority'+id+' id="topicPriority'+id+'"> '+
					prioritiesOptions+
					'</select> '+
				'</td><td>'+
					'<button type="button" id="topicResource'+id+'">Resources</button> '+
				'</td><td>'+
					'<input type="checkbox" name="topicPreReq'+id+'"> '+
				'</td>'+
			'</tr>'
			).appendTo(topicDiv)
			
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}