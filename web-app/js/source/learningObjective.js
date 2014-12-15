// start at the beggining of the path, get the first '/' then and all the characters between that and the second '/'
var baseUrl = window.location.pathname.match(/\/[^\/]+\//)[0];
var prevKeyword = ''

//on page load
$(document).ready(function() {

	// Initially domain categories and action words will not be displayed as learning domain is null
	if($('#domain-category-list').val() == 'null'){
		$('label[for="domain-category-list"]').css('visibility', 'hidden');

		$('#domain-category-list').css('visibility', 'hidden');
		$('#action-word-categories').css('visibility', 'hidden');
	}

	// listen for the selected learning domain to change, when it does call ajax
	$('#learning-domain-list').on(
		'change', function(event){
			populateDomainCategories();
	});

	// listen for the selected domain category to change, when it does call ajax
	$('#domain-category-list').on(
		'change', function(){
			populateActionWordCategories();
			
	});

	// listen for change in action word categories, when it does call ajax
	$('#action-word-categories').on(
		'click', function(){
			populateActionWords();
	});

	// if the condition is set to hidden do not display it in the definition box above
	$('#LO-hide-from-Objective').change(function() {
		if(this.checked) {
			$('.learning-objective-current .learning-objective-condition').css('display', 'none');
		}
		else {
			$('.learning-objective-current .learning-objective-condition').css('display', 'inline');
		}
	});

	// when a custom condition is added, display in the definition box above
	$('#LO-condition-custom').keyup(
		propagateToDefinition(this.value, 'condition')
	);

	// when a standard condition is added, display in the definition box above
	$('.LO-condition-data').change(
		propagateToDefinition(this.value, 'condition')
	);

	// TODO no idea what this is doing
	$('input:radio[name=LO_condition_type]').on(
		'change',
		function() {
			if(this.value == 'Generic') {
				$('#LO-condition-custom').css('display', 'none');
				$('#LO-condition-generic').css('display', 'block');
			}
			else{
				$('#LO-condition-generic').css('display', 'none');
				$('#LO-condition-custom').css('display', 'block');
			}
		}
	);

	// manually tiggers the radio box change event
	$('input:radio[name=LO_condition_type]:checked').change();

	//trigger jquery ui button for better radio buttons
	var category = $('input[name=selectedActionWordCategory]').val();
	$('#action-word-categories input[value="'+category+'"]').prop('checked',true)
 	$('#action-word-categories').buttonset();

 	// populate action words if a category is already selected
 	populateActionWords(true);

 	//reset radio buttons if a selected radio button is clicked again
 	$(document).on('click', '#action-word-categories label', function(){
 		if($(this).hasClass('is-active')){
 			$('#action-word-categories label').removeClass('is-active');
 			setTimeout(function(){
	 			$('#action-word-categories input:checked')
	 				.removeAttr('checked').button('refresh');
	        },0);
 		}else{
 			$('#action-word-categories label').removeClass('is-active');
 			$(this).addClass('is-active');
 		}
 	});  	

	// This listens for when a learning objective is selected and saves
	// $('.action-word-category').on(
	// 	'change',
	// 	populateActionWordCategories()
	// );

	// when the checkbox is changed disable text box and other check box
	$('#enable-accuracy').on(
		'click',
		function() {
			$('#accuracy-text').prop(
				'disabled',
				! $('#enable-accuracy').is(':checked')
			);
			$('#accuracy-text').value('');
			$('#accuracy-hide').prop(
				'disabled',
				! $('#enable-accuracy').is(':checked')
			);
		}
	);

	// when the checkbox is changed disable text box and other check box
	$('#enable-quality').on(
		'click',
		function() {
			$('#quality-text').prop(
				'disabled',
				! $('#enable-quality').is(':checked')
			);
			$('#quality-text').value('');
			$('#quality-hide').prop(
				'disabled',
				! $('#enable-quality').is(':checked')
			);
		}
	);

	// when the checkbox is changed disable text box and other check box
	$('#enable-quantity').on(
		'click',
		function() {
			$('#quantity-text').prop(
				'disabled',
				! $('#enable-quantity').is(':checked')
			);
			$('#quantity-text').value('');
			$('#quantity-hide').prop(
				'disabled',
				! $('#enable-quantity').is(':checked')
			);
		}
	);

	// when the checkbox is changed disable text box and other check box
	$('#enable-speed').on(
		'click',
		function() {
			$('#speed-text').prop(
				'disabled',
				! $('#enable-speed').is(':checked')
			);
			$('#speed-text').value('');
			$('#speed-hide').prop(
				'disabled',
				! $('#enable-speed').is(':checked')
			);
		}
	);

	//when learning domain isn't selected, do not save learning objective
	$('.learning-objective-button.save').click(function(){
		if($('#learning-domain-list').val() == "null" ||
			$('#domain-category-list').val() == "null" ||
			$('input[name=actionWordCategory]').is(':checked') == false){
			alert("Learning Domain, Domain Category and Action Word Categories are required");
			return false;	
		}		
	});
});

/**
 * ajax to pull domain categories based on which Learning Domain was selected,
 * then populate the select box with the domain categories
 * @param  {String} domain text from the domain select box
 * @return {XML}        Populates the domain category box with options
 */
function populateDomainCategories(event) {
	if($('#learning-domain-list').val() != 'null')
	{
		$('label[for="domain-category-list"]').css('visibility', 'visible');
		$('#domain-category-list').css('visibility', 'visible');
		$('#action-word-categories').css('visibility', 'visible');
	}
	else
	{
		$('label[for="domain-category-list"]').css('visibility', 'hidden');
		$('#domain-category-list').css('visibility', 'hidden');
		$('#action-word-categories').css('visibility', 'hidden');
	}

	$.ajax({
		url: baseUrl + 'learningObjective/getDomainCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#learning-domain-list').val()
		},
		success: function(data){

			// stores the data from the call back
			var categories = data.value;
			// this stores the new html that will be added
			var options = '';
			// for each of the categories
			for (var i = 0; i < categories.length; i++) {
				// create the html for the category
				options += '<option value="' + categories[i].name + '">' + categories[i].name + '</option>';
			}
			// store this to the page
			$('#domain-category-list').html(options);

			populateActionWordCategories();
		},
		error: function(xhr) {
			// when something goes wrong log to the browser console
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
		url: baseUrl + 'learningObjective/getActionWordCategories',
		type: 'GET',
		dataType: 'json',
		data: {
			domainName: $('#domain-category-list').val()
		},
		success: function(data) {
			// store the data from the call back
			var actionWordCategories = data.value;
			// this will temporarily store the html for the categories
			var actionWordCategoriesHTML = '';
			// for each category
			for (var i = 0; i < actionWordCategories.length; i++) {
				// create the html
				actionWordCategoriesHTML += '<input type="radio" id="radio'+i+'" name="actionWordCategory" value="'+actionWordCategories[i].actionWordCategory+'"><label for="radio'+i+'">' + actionWordCategories[i].actionWordCategory + '</label>';
			}
			// display the html on the page
			$('#action-word-categories').html(actionWordCategoriesHTML);
			// since the markup is reloaded, re-initiate buttonset
			$('#action-word-categories').buttonset();

			populateActionWords();
		},
		error: function(xhr){
			// when something goes wrong log to the browser console
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
function populateActionWords(event, ui) {

	if(prevKeyword == $('#action-word-categories').find('.ui-state-active').text()){
		return;			
	}else{
		prevKeyword = $('#action-word-categories').find('.ui-state-active').text();
	}
	
	$.ajax({
		url: baseUrl + 'learningObjective/getActionWords',
		type: 'GET',
		dataType: 'json',
		data: {
			actionWordCategory: $('#action-word-categories').find('.ui-state-active').text()
		},
		success: function(data) {
			var actionWordsHTML = '';

			if(event = true){
				var originalActionWord = $('#action-words').val();		
			}

			// store the data from the call back
			if(data.value != null){
				if(data.value.verb !== undefined && data.value.verb !== null && data.value.verb !== ''){

					var actionWordsVerb = data.value.verb.syn;
					
					// this will store the html for the action words
					// for each action word
					for (var i = 0; i < actionWordsVerb.length; i++) {
						// create the html for the action word
						if(actionWordsVerb[i] == originalActionWord){
							actionWordsHTML += '<option selected value="' + actionWordsVerb[i] + '">'+ actionWordsVerb[i] + '</option>';
						}else{
							actionWordsHTML += '<option value="' + actionWordsVerb[i] + '">'+ actionWordsVerb[i] + '</option>';	
						}
						
					}
				}

				if(data.value.noun !== undefined && data.value.noun !== null && data.value.noun !== ''){

					var actionWordsNoun = data.value.noun.syn;
					for (var i = 0; i < actionWordsNoun.length; i++) {
						// create the html for the action word
						if(actionWordsNoun[i] == originalActionWord){
							actionWordsHTML += '<option selected value="' + actionWordsNoun[i] + '">'+ actionWordsNoun[i] + '</option>';
						}else{
							actionWordsHTML += '<option value="' + actionWordsNoun[i] + '">'+ actionWordsNoun[i] + '</option>';
						}
					}
				}
			}		

			// display the html for the action words
			$('#action-words').html(actionWordsHTML);
		},
		error: function(xhr) {
			// when something goes wrong log to the browser console
			console.log(xhr.responseText);
		}
	});

}

// add the data to the definition box, depending on which type of data it is
function propagateToDefinition(value, type) {
	var definitionType = '.learning-objective-current .learning-objective-' + type;
	$(definitionType).text(value);
}
