$(function(){
	$("#contentTree").jstree({
		"core": {
			"data":eval($("#treeData").val()),
			"check_callback": true,
			"themes":{
				"icons":false
			}
		},
		"dnd":{
			"copy":false,
		},
		"plugins":["wholerow", "dnd"],
	})
	$("#contentTree").on('loaded.jstree', function(){
		$("#contentTree").jstree('open_all')
		$("#contentTree .jstree-wholerow").on("click", function(e){
			selectCheckboxes(this)
		})
		$("#contentTree .jstree-anchor").on("click", function(e){
			selectCheckboxes(this)
		})
	})
	$("#contentTree").on("move_node.jstree",function(e,data){
		refreshTreeValues($("#"+data.node.id+" li").children('a'))
		$("#contentTree").jstree("open_node","#"+data.node.parent)
		moveContent(data.node.id.substr(7),data.node.parent.substr(7))

	})
	$("#contentTree").off("click.jstree")

})

function selectCheckboxes(currentRow){
	var contentID=$(currentRow).parent("li").attr('id').substr(7)
	var checkbox=$("#select"+contentID)
	var testingObject=$("#content"+contentID)
	if (!(checkbox.hasClass("fa-plus"))||(checkbox.hasClass("fa-minus"))){
		checkbox.addClass("fa-plus")
		testingObject.find("i.checkbox").each(function(){
			$(this).addClass("fa-plus")
		})
	}
	else{
		checkbox.removeClass("fa-plus fa-minus")
		testingObject.find("i.checkbox").each(function(){
			$(this).removeClass("fa-plus fa-minus")
		})
	}
	refreshTreeValues(currentRow)
}
function refreshTreeValues(currentRow){
	var contentID=$(currentRow).parent("li").attr('id').substr(7)
	var checkbox=$("#select"+contentID)
	var testingObject=$("#content"+contentID)
	var indeterminate=false
	var nodeList=[]
	while ($(testingObject).parents("li").length>0){
		testingObject=$(testingObject).parents("li")[0]
		var currentID=testingObject.id.substr(7)
		if (!indeterminate){
			if($(testingObject).children("ul").find("i.fa-minus").length>0){
				indeterminate=true
				$("#select"+currentID).addClass("fa-minus")
			}
			else{
				var treeDepth=$(testingObject).children("ul").find("i.fa").length
				var numChecked=$(testingObject).children("ul").find("i.fa-plus").length
				$("#select"+currentID).removeClass("fa-plus fa-minus")
				if ((treeDepth!=numChecked)&&(numChecked>0)){
					indeterminate=true
					$("#select"+currentID).addClass("fa-minus")
				}
				else if ((treeDepth==numChecked)&&(numChecked>0)){
					$("#select"+currentID).addClass("fa-plus")
				}
			}
		}
		else{
			$("#select"+currentID).addClass("fa-minus")
		}
		nodeList.push(currentID)
	}
	$(nodeList).each(function(){
		var checkboxHTML=$("#select"+this).parent().prop("outerHTML")
		var nodeText=$("#select"+this).parents("a").text()
		var nodeContents=checkboxHTML+nodeText
		var currentNode=$('#content'+this)
		$("#contentTree").jstree('rename_node',currentNode,nodeContents)
	})

	$("#contentTree .jstree-wholerow").on("click", function(e){
		selectCheckboxes(this)
	})
	$("#contentTree .jstree-anchor").on("click", function(e){
		selectCheckboxes(this)
	})
}

function moveContent(contentID, parentID){
//	$.ajax({
//		url:"../../content/updateHierarchy/",
//		type:"POST",
//		dataType:"json",
//		data: {
//			contentID:contentID,
//			parentID:parentID
//			},
//		success: function(data){
//		},
//		error: function(xhr){
//			alert(xhr.responseText);
//		}
//	})
}
function toggleLearningObjective(contentID, toAdd){
	var contentIDs=[]
	var objectiveID=$("#learningObjectiveID").val()

	contentIDs.push({id:contentID})
	$("#content"+contentID).find("li").each(function(){
		contentIDs.push({id:this.id.substr(7)})
	})
	contentIDs=JSON.stringify(contentIDs)
//	$.ajax({
//		url:"../../content/setLearningObjective/",
//		type:"POST",
//		dataType:"json",
//		data: {
//			contentIDs:contentIDs,
//			objectiveID:objectiveID,
//			toAdd:toAdd
//			},
//		success: function(data){
//		},
//		error: function(xhr){
//			alert(xhr.responseText);
//		}
//	})
}
function populateTopics(topicList){
	var contentTree=$.jstree.reference("#contentTree")
	$("#topicList tbody").html("")
	$(topicList).each(function(){
		contentTree.create_node(
				"#",
				{
					"id":"content"+this.contentID, "text": this.topicTitle
				}
		)
	})
	$("#contentTree").jstree('open_all')

}