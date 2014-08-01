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
			"copy":false
		},
		"plugins":["wholerow", "checkbox","dnd"],
	})
	$("#contentTree").on('loaded.jstree', function(){
		$("#contentTree").jstree('open_all')
	})
	$("#contentTree").on("select_node.jstree",function(e,data){
		console.log(data.node.id.substr(7)+"select")
	})
	$("#contentTree").on("deselect_node.jstree",function(e,data){
		console.log(data.node.id.substr(7)+"deselect")
	})

	$("#contentTree").on("move_node.jstree",function(e,data){
		console.log(data.node.id.substr(7)+'move')
	})
})
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