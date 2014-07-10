$(function(){
	$('#learning-domain-list').change(function(){
		populateDomainCategories(this.value)
	});

	$('#LO_hide_from_Objective').change(function(){
		changeDefinition( this.checked, "hideFromObjective")
		if(this.checked){
			$('#learning-objective-condition').css("display","none")
		}
		else{
			$('#learning-objective-condition').css("display","inline")
		}
	});
	$('#LO_condition_custom').keyup(function(){
		propagateToDefinition(this.value, "condition")
	});
	$(".LO_condition_data").change(function(){
		changeDefinition(this.value, "condition")
	});	
	$('input:radio[name=LO_condition_type]').change(function(){
		if(this.value=='Generic'){
			$('#LO_condition_custom').css("display","none")
			$('#LO_condition_generic').css("display","block")
		}
		else{
			$('#LO_condition_generic').css("display","none")
			$('#LO_condition_custom').css("display","block")
		}
	});
	$('#action-words' ).selectable();
	$('.action-word').change(function() {
		$( '.learning-objective-performance').html(
				$( '.ui-selected' ).innerHTML
			)
	});
});
function populateDomainCategories(domain){
	$.ajax({
		url:"../../learningObjective/getDomainCategories",
		type:"GET",
		dataType:"json",
		data:{domain:domain},
		success:function(data){
			var categories=data.value
			var options='';
			for (var i=0;i<categories.length;i++){
				options+='<option value="'+categories[i].name+'">'+categories[i].name+'</option>'
			}
			$('#domain-category-list').html(options);
		},
	})
}
function changeDefinition(value, type){
	$.ajax({
		url:"../../learningObjective/updateDefinition",
		type:"POST",
		dataType:"json",
		data:{type:type,value:value},
		success:function(data){
			propagateToDefinition(data.value, data.type)
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}
function propagateToDefinition(value, type){
	var definitionType="#learning-objective-"+type
	$(definitionType).text(value)
}