$(function(){
	$('#learning-domain-list').change(function(){
		populateDomainCategories(this.value)
	});

	$('#LO_hide_from_Objective').change(function(){
		if(this.checked){
			$('#learning-objective-condition').css("display","none")
		}
		else{
			$('#learning-objective-condition').css("display","inline")
		}
	})
	.change();
	$('#LO_condition_custom').keyup(function(){
		propagateToDefinition(this.value, "condition")
	});
	$(".LO_condition_data").change(function(){
		propagateToDefinition(this.value, "condition")
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
	$('input:radio[name=LO_condition_type]:checked').change()
	$('#action-words' ).selectable();
	$('.action-word').change(function() {
		$( '.learning-objective-performance').html(
				$( '.ui-selected' ).innerHTML
			)
	});
});

// ajax to pull domain categories based on which Learning Domain was selected, 
// then populate the select box with the domain categories
function populateDomainCategories(domain){
	$.ajax({
		url:"../../learningObjective/getDomainCategories",
		type:"GET",
		dataType:"json",
		data:{domainName:domain},
		success:function(data){
			var categories=data.value
			var options='';
			for (var i=0;i<categories.length;i++){
				options+='<option value="'+categories[i].name+'">'+categories[i].name+'</option>'
			}
			$('#domain-category-list').html(options);
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
}

// add the data to the definition box, depending on which type of data it is
function propagateToDefinition(value, type){
	var definitionType="#learning-objective-"+type
	$(definitionType).text(value)
}