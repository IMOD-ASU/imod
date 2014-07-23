//$(function{
//	$("#contentDialog").dialog({
//		dialogClass: "no-title"
//	});
//});
$(function(){
	$("#addTopic").click(function(){
		var imodID=$("#imodID").val()
		addContent(imodID)
	})
	$('#saveTopic').click(function(){
		saveContent()
	})
	$("#contentList > tbody").selectable({filter: "tr"})
});
function saveContent(){
	var imodID=$("#imodID").val()
	var contentData=[];
	$("#contentList .ui-selected").each(function(){ 
		var contentID=this.id
		var dimensions=[]
		$("#contentDimensions"+contentID+" :selected").each(function(i, selected){
			dimensions[i]=$(selected).val();
		})
		dimensions=JSON.stringify(dimensions)
		var priority=$("#contentPriority"+contentID).val()
		var preReq=$("#contentPreReq"+contentID).is(':checked')
		contentData.push({
			contentID: contentID,
			dimensions: dimensions,
			priority: priority,
			preReq: preReq
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
			console.log(data)
		},
	})

}
function addContent(id){
	$.ajax({
		url:"../../content/addNewTopic",
		type:"GET",
		dataType:"json",
		data:{id:id},
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