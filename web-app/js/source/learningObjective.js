//on page load
$(document).ready(function() {
	// listen for the selected learning domain to change, when it does call ajax
	$('#learning-domain-list').on(
		'click',
		populateDomainCategories
	);

	// listen for the selected domain category to change, when it does call ajax
	$('#domain-category-list').on(
		'click',
		populateActionWords
	);

	// if the condition is set to hidden do not display it in the definition box above
	$('#LO_hide_from_Objective').change(function(){
		if(this.checked) {
			$('#learning-objective-condition').css("display","none")
		}
		else {
			$('#learning-objective-condition').css("display","inline")
		}
	});

	// when a custom condition is added, display in the definition box above
	$('#LO_condition_custom').keyup(
		propagateToDefinition(this.value, "condition")
	);

	// when a standard condition is added, display in the definition box above
	$(".LO_condition_data").change(
		propagateToDefinition(this.value, "condition")
	);

	// TODO no idea what this is doing
	$('input:radio[name=LO_condition_type]').on(
		'change',
		function() {
			if(this.value == 'Generic'){
				$('#LO_condition_custom').css("display","none")
				$('#LO_condition_generic').css("display","block")
			}
			else{
				$('#LO_condition_generic').css("display","none")
				$('#LO_condition_custom').css("display","block")
			}
		}
	);

	// manually tiggers the radio box change event
	$('input:radio[name=LO_condition_type]:checked').change()

	// making action words selectable through jquery ui
	$('#action-words' ).selectable();

	// This listens for when a learning objective is selected and saves
	$('.action-word').on(
		'change',
		function() {
			$('.learning-objective-performance').html(
				$('.ui-selected').innerHTML
				//TODO create some sort of save
			)
		}
	);
});

/**
 * ajax to pull domain categories based on which Learning Domain was selected,
 * then populate the select box with the domain categories
 * @param  {String} domain text from the domain select box
 * @return {XML}        Populates the domain category box with options
 */
function populateDomainCategories(event) {
	$.ajax({
		url: "/imodv6/learningObjective/getDomainCategories",
		type: "GET",
		dataType: "json",
		data: {
			domainName: this.value
		},
		success: function(data){
			var categories = data.value
			var options = '';
			for (var i = 0; i < categories.length; i++){
				options += '<option value="' + categories[i].name + '">' + categories[i].name + '</option>'
			}
			$('#domain-category-list').html(options);
		},
		error: function(xhr){
			console.log(xhr.responseText);
		}
	});
}

/**
 * ajax to pull Action Words based on which Domain Category was selected,
 * then populate page with selectable action word boxes
 * @param  {String} domain text from the domain category select box
 * @return {XML}        Populates the page with action words
 */
function populateActionWords(event) {
	$.ajax({
		url: "/imodv6/learningObjective/getActionWords",
		type: "GET",
		dataType: "json",
		data: {
			domainName: this.value
		},
		success: function(data){
			var actionWords = data.value
			var actionWordsHTML = '';
			for (var i = 0; i < actionWords.length; i++){
				actionWordsHTML += '<li class="action-word ui-state-default">' + actionWords[i].name + '</li>'
			}
			$('#action-words').html(actionWordsHTML);
		},
		error: function(xhr){
			console.log(xhr.responseText);
		}
	});
}

// add the data to the definition box, depending on which type of data it is
function propagateToDefinition(value, type){
	var definitionType = "#learning-objective-" + type
	$(definitionType).text(value)
}
