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
		populateActionWordCategories
	);

	// if the condition is set to hidden do not display it in the definition box above
	$('#LO-hide-from-Objective').change(function(){
		if(this.checked) {
			$('.learning-objective-current .learning-objective-condition').css("display","none")
		}
		else {
			$('.learning-objective-current .learning-objective-condition').css("display","inline")
		}
	});

	// when a custom condition is added, display in the definition box above
	$('#LO-condition-custom').keyup(
		propagateToDefinition(this.value, "condition")
	);

	// when a standard condition is added, display in the definition box above
	$(".LO-condition-data").change(
		propagateToDefinition(this.value, "condition")
	);

	// TODO no idea what this is doing
	$('input:radio[name=LO_condition_type]').on(
		'change',
		function() {
			if(this.value == 'Generic'){
				$('#LO-condition-custom').css("display","none")
				$('#LO-condition-generic').css("display","block")
			}
			else{
				$('#LO-condition-generic').css("display","none")
				$('#LO-condition-custom').css("display","block")
			}
		}
	);

	// manually tiggers the radio box change event
	$('input:radio[name=LO_condition_type]:checked').change()

	// making action words selectable through jquery ui
	$('#action-word-categories' ).selectable();

	// This listens for when a learning objective is selected and saves
	$('.action-word-category').on(
		'change',
		function() {
			$('.learning-objective-performance').html(
				$('.ui-selected').innerHTML
				//TODO create some sort of save
			)
		}
	);

	// when the checkbox is changed disable text box and other check box
	$('#enable-accuracy').on(
		'click',
		function() {
			$('#accuracy-text').prop(
				'disabled',
				$('#enable-accuracy').is(':checked')
			);
			$('#accuracy-hide').prop(
				'disabled',
				$('#enable-accuracy').is(':checked')
			);
		}
	)

	// when the checkbox is changed disable text box and other check box
	$('#enable-quality').on(
		'click',
		function() {
			$('#quality-text').prop(
				'disabled',
				$('#enable-quality').is(':checked')
			);
			$('#quality-hide').prop(
				'disabled',
				$('#enable-quality').is(':checked')
			);
		}
	)

	// when the checkbox is changed disable text box and other check box
	$('#enable-quantity').on(
		'click',
		function() {
			$('#quantity-text').prop(
				'disabled',
				$('#enable-quantity').is(':checked')
			);
			$('#quantity-hide').prop(
				'disabled',
				$('#enable-quantity').is(':checked')
			);
		}
	)

	// when the checkbox is changed disable text box and other check box
	$('#enable-speed').on(
		'click',
		function() {
			$('#speed-text').prop(
				'disabled',
				$('#enable-speed').is(':checked')
			);
			$('#speed-hide').prop(
				'disabled',
				$('#enable-speed').is(':checked')
			);
		}
	)
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
 * then populate page with selectable action word category boxes
 * @param  {String} domain text from the domain category select box
 * @return {XML}        Populates the page with action word categories
 */
function populateActionWordCategories(event) {
	$.ajax({
		url: "/imodv6/learningObjective/getActionWordCategories",
		type: "GET",
		dataType: "json",
		data: {
			domainName: this.value
		},
		success: function(data){
			var actionWordCategories = data.value;
			var actionWordCategoriesHTML = '';
			for (var i = 0; i < actionWordCategories.length; i++){
				actionWordCategoriesHTML += '<li class="action-word-category ui-state-default">' + actionWordCategories[i].actionWordCategory + '</li>'
			}
			$('#action-word-categories').html(actionWordCategoriesHTML);
		},
		error: function(xhr){
			console.log(xhr.responseText);
		}
	});
}

/**
 * ajax to pull Action Words based on which Action Word Category was selected,
 * then populates a select tag with action words
 * @param  {String} domain text from the action word category boxes
 * @return {XML}        Populates the page with action words
 */
function populateActionWordCategories(event) {
	$.ajax({
		url: "/imodv6/learningObjective/getActionWords",
		type: "GET",
		dataType: "json",
		data: {
			domainName: this.value
		},
		success: function(data){
			var actionWords = data.value;
			var actionWordsHTML = '';
			for (var i = 0; i < actionWordCategories.length; i++){
				actionWordCategoriesHTML += '<option value="' + actionWords[i].actionWord + '"/>'
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
	var definitionType = ".learning-objective-current .learning-objective-" + type
	$(definitionType).text(value)
}
