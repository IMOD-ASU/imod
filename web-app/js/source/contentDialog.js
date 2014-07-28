//$(function{
//	$("#contentDialog").dialog({
//		dialogClass: "no-title"
//	});
//});
$(function(){
	$("#addTopic").click(addContent)
	$('#saveTopic').click(saveContent)
	$('.knowledgeDimensionButton').click(openDimModal)
	$('#knowDimFinished').click(closeDimModal)
	$('#removeTopic').click(deleteContent)
	$('#selectKnowledgeDimensions').on('change','input:checkbox',changePic)
	$("#contentList > tbody").on("click","tr",toggleSelected)
	$("#contentList > tbody").on("change","input",function(){
		var id=$(this).parents("tr .contentItem").attr('id')
		highlightUnsaved(id)
	})
	$("#contentList > tbody").on("change","select",function(){
		var id=$(this).parents("tr .contentItem").attr('id')
		highlightUnsaved(id)
	})

});
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
function closeDimModal(){
	var contentID=$("#contentID").val()
	var dimensions=[];
	var dialog=$("#selectKnowledgeDimensions")
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
	$("#contentID").val("")
	dialog.find('input:checkbox').each(function(){
		$(this).prop('checked',false)
	})
	$("#knowDimensionList"+contentID).val(dimensions)
	$("#"+contentID).find('img').attr('src',$('#dimImage').attr('src'))
	dialog.css("display","none")
}
function openDimModal(){
	var contentID=$(this).parents('.contentItem').attr('id')
	var dimString=$(this).siblings('input').val()
	var dimensionList=[]
	if (dimString!=""){
		dimensionList=dimString.split(",")
	}	
	var dialog=	$("#selectKnowledgeDimensions")
	for(var i=0;i<dimensionList.length;i++){
		var findCheckBox=$(dialog).find("#"+dimensionList[i])
		if (findCheckBox.length==1){
			findCheckBox.prop('checked',true)
		}
	}
	changePic()
	$("#contentID").val(contentID)
	dialog.css("display","inherit")
	
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
function highlightUnsaved(id){
	$("#"+id).addClass("unsaved")
}
function deleteContent(){
	var imodID=$("#imodID").val()
	var contentIDs=[]
	$("#contentList .selected").each(function(){ 
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
function saveContent(){
	var imodID=$("#imodID").val()
	var contentData=[];
	$("#contentList .selected").each(function(){ 
		var contentID=this.id
		var topicTitle=$("#contentTitle"+contentID).val()
		var dimensions=$("#knowDimensionList"+contentID).val()
		var priority=$("#contentPriority"+contentID).val()
		var preReq=$("#contentPreReq"+contentID).is(':checked')
		contentData.push({
			contentID: contentID,
			dimensions: dimensions,
			priority: priority,
			preReq: preReq,
			topicTitle: topicTitle
		})
	})
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
				$("#"+element).removeClass("selected")
				$("#"+element).find(".saveIcon > i").toggleClass("hidden")
			})
		},
	})

}
function addContent(){
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
			var topicDiv=$('#contentList tbody')
			
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
					'<input type="text" name="contentTitle'+id+'"> '+
				'</td><td>'+
					'<select size="1" multiple="true" name="contentDimensions'+id+' id="contentDimensions'+id+'"> '+
					dimensionOptions+
					'</select> '+
				'</td><td>'+
					'<select size="3" name="contentPriority'+id+' id="contentPriority'+id+'"> '+
					prioritiesOptions+
					'</select> '+
				'</td><td>'+
					'<button type="button" id="contentResource'+id+'">Resources</button> '+
				'</td><td>'+
					'<input type="checkbox" name="contentPreReq'+id+'"> '+
				'</td>'+
			'</tr>'
			).appendTo(topicDiv)
			
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}