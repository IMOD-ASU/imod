//$(function{
//	$("#contentDialog").dialog({
//		dialogClass: "no-title"
//	});
//});

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
			var priorityOptions='';
			var topicDiv=$('#contentList')
			
			for (var i=0;i<dimensions.length;i++){
				dimensionOptions+='<option value="'+dimensions[i].name+'">'+dimensions[i].name+'</option>'
			}
			for (var i=0;i<priorities.length;i++){
				prioritiesOptions+='<option value="'+priorities[i].name+'">'+priorities[i].name+'</option>'
			}
			$('<div id="'+id+'">'+ 
				'<input type="text" name="contentTitle'+id+'">'+
				dimensionOptions+
				prioritiesOptions+
				'<input type="checkbox" name="contentPreReq'+id+'">'
			).appendTo(topicDiv)
			
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}