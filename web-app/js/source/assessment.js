'use strict';

// load techniques on page load
filterAssessmentTechniques();

// the filters for the pedagogy technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();


$('#favorites').click(function(){
	$('.favDiv').css('visibility', 'visible');
	$('.favDiv').css('display', 'block');
	$('#assessmentFavorites').css('visibility', 'visible');
	$('#assessmentFavorites').css('display', 'block');
	$('#assessmentFavoritesDiv').css('visibility', 'visible');
	$('#assessmentFavoritesDiv').css('display', 'block');

	$('#favorites').hide();

	$('#unfavorites').show();


});

$('#unfavorites').click(function(){
	$('.favDiv').css('visibility', 'hidden');
	$('.favDiv').css('display', 'none');
	$('#assessmentFavorites').css('visibility', 'hidden');
	$('#assessmentFavorites').css('display', 'none');
	$('#assessmentFavoritesDiv').css('visibility', 'hidden');
	$('#assessmentFavoritesDiv').css('display', 'none');

	$('#favorites').show();

	$('#unfavorites').hide();


});

// auto hide the assessment plan modal
$('#assessment-plan').dialog({
	autoOpen: false
});


// auto hide the add new technique modal
$('#new-technique').dialog({
	autoOpen: false
});

// auto hide the add new technique modal
$('#display-new-technique').dialog({
	autoOpen: false
});

// attach a listener to the checkboxes, to update the pedaogy techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// when add new technique button is clicked open modal
$('#new-technique-button').on('click', openNewAssessmentTechniqueModal);

// when add assessment  plan button is clicked open modal
$('#assessment-plan-button').on('click', openAssessmentPlanModal);

/**
 * Opens the modal to create a new assessment technique
 */
function openAssessmentPlanModal() {

	//	alert ("click calls the displayAssessmentPlan fucntion with ajax data");

	$('#assessment-plan').dialog('open');
	$('#assessment-plan').dialog(
	{
		resizable: false,
	    height: 'auto',
		title:'Assessment Plan',
	    width: 400,
	    modal: true,
	    zindex: 1001, // Default is 1000
    buttons: {
        Cancel: function() {
            $(this).dialog('close');
        }
    }
	});

	displayAssessmentPlan();

}

/**
 * Opens the modal to create a new assessment technique
 */
function openNewAssessmentTechniqueModal() {
	$('#techniqueId').val('');
	$('#new-technique').dialog('open');
	$('#new-technique').dialog(
	{
		resizable: false,
	    height: 'auto',
		title:'New Assessment Technique',
	    width: 'auto',
	    modal: true,
	    zindex: 1001, // Default is 1000
    buttons: {
    /*    Cancel: function() {
            $(this).dialog('close');
        }*/
    }
	});
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible assessment techniques
 */
function filterAssessmentTechniques() {
	// get all of the selected checkboxes
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');

	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];

	// get the id of the grails domain from the value attribute in the html
	for (var index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// bundle the data into an object
	var data = {
		selectedKnowledgeDimensions: selectedKnowledgeDimensionsData,
		selectedLearningDomains: selectedLearningDomainsData,
		selectedDomainCategories: selectedDomainCategoriesData
	};

	// send the data to the find matching techniques action in grails
	// and process the response with the display pedagogy techniques callback
	$.ajax({
			url: '../findMatchingTechniques',
			method: 'post',
			data: JSON.stringify(data),
		/*	success: function(data) {
				getFavorites(data);
			},*/
			contentType: 'application/json'
		})
		.done(displayAssessmentTechniques, showAssessmentTechnique, checkForAssign);
}

function checkForAssign(data) {
    // Your code

for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
	var currentTechnique = data.idealAssessmentTechniqueMatch[index];

	//alert("this is Assign Check with Ideal Number of techs: "+currentTechnique.id);

	if(currentTechnique.assigncheck == true && currentTechnique.favcheck == false){

		//alert("assignCheck IDEAL TRUE FALSE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentUnfavAssign');

	}
	else if(currentTechnique.assigncheck == true && currentTechnique.favcheck == true){

	//	alert("assignCheck IDEAL TRUE TRUE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentFavAssign');

	}
	else if(currentTechnique.assigncheck == false && currentTechnique.favcheck == true){

	//	alert("assignCheck IDEAL FALSE TRUE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentFavUnassign');

	}
	else if(currentTechnique.assigncheck == false && currentTechnique.favcheck == false){

	//	alert("assignCheck IDEAL FALSE FALSE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentUnfavUnassign');

	}
}


for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];

	//	alert("This is AssignCheck extended alert for  tech :"+currentTechnique.id)

	if(currentTechnique.assigncheck == true && currentTechnique.favcheck == false){

	//alert("assignCheck EXT TRUE FALSE: "+$('#span-'+currentTechnique.id).val());

	$('#span-'+currentTechnique.id).addClass('icons assessmentUnfavAssign');

	}
	else if(currentTechnique.assigncheck == true && currentTechnique.favcheck == true){

	//alert("assignCheck EXT TRUE TRUE : "+$("#span-"+currentTechnique.id).val());

	$('#span-'+currentTechnique.id).addClass('icons assessmentFavAssign');

	}
	else if(currentTechnique.assigncheck == false && currentTechnique.favcheck == true){

	//	alert("assignCheck ext FALSE TRUE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentFavUnassign');

	}
	else if(currentTechnique.assigncheck == false && currentTechnique.favcheck == false){

	//	alert("assignCheck ext FALSE FALSE "+$("#span-"+currentTechnique.id).val());

	$("#span-"+currentTechnique.id).addClass('icons assessmentUnfavUnassign');

	}
}



}

function getFavorites(data){


for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
	var currentTechnique = data.idealAssessmentTechniqueMatch[index];

	//	alert("get FAV alert Ideal: "+	$("#span-"+currentTechnique.id).val());


		if(currentTechnique.favcheck == true  && currentTechnique.assigncheck == false){



		//	alert("get FAV alert Ideal TRUE TRUE: "+currentTechnique.id);

		//	$("#span-"+currentTechnique.id).attr("id","#span1-"+currentTechnique.id )
		$("#span1-"+currentTechnique.id).addClass('icons assessmentFavUnassign');


		}

		else if(currentTechnique.favcheck == true  && currentTechnique.assigncheck == true){


		//	alert("get FAV alert Ideal TRUE TRUE: "+currentTechnique.id);

		//	$("#span-"+currentTechnique.id).attr("id","#span1-"+currentTechnique.id )
		$("#span1-"+currentTechnique.id).addClass('icons assessmentFavAssign');


			}



	}


	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {

			var currentTechnique = data.extendedAssessmentTechniqueMatch[index];


			if(currentTechnique.favcheck == true  && currentTechnique.assigncheck == true){



			$("#span1-"+currentTechnique.id).addClass('icons assessmentFavUnassign');


			}
			else if(currentTechnique.favcheck == true   && currentTechnique.assigncheck == true){


			$("#span1-"+currentTechnique.id).addClass('icons assessmentFavAssign');


			}


		}


}


function showAssessmentTechnique(data){

	$('#ideal-matches').buttonset().click(function() {
		$('#display-new-technique').dialog('open');

		displayAssessmentInformationInEdit();

	});

	$('#ideal-matches1').buttonset().click(function() {
		$('#display-new-technique').dialog('open');
		$('#display-new-technique').dialog(
		{
			resizable: false,
		    height: 'auto',
			title:'Display Technique',
		    width: 'auto',
		    modal: true,
		    zindex: 1001, // Default is 1000
	    buttons: {
	    /*    Cancel: function() {
	            $(this).dialog('close');
	        }*/
	    }
		});
		displayAssessmentInformationInEdit();

	});

	$('#extended-matches').click(function() {
		$('#display-new-technique').dialog('open');
		$('#display-new-technique').dialog(
		{
			resizable: false,
			height: 'auto',
			title:'Display Technique',
			width: 'auto',
			modal: true,
			zindex: 1001, // Default is 1000
		buttons: {
		/*    Cancel: function() {
				$(this).dialog('close');
			}*/
		}
		});

		displayAssessmentInformationInEdit();
	});


}

/**
 * callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques(data) {



	var idealText1 = '';
	// take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.idealAssessmentTechniqueMatch[index];

					idealText1 += '<input  type="radio" id="'
					+ currentTechnique.id + '" name="assessmentTech1" value="'
					+ currentTechnique.id + '"><span id="span-'
					+currentTechnique.id+'" class="icons"><label for="' + currentTechnique.id
					+ '">'
					+ currentTechnique.title +'</label></span></input>';



	}

	var idealText2 = '';
	// take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.idealAssessmentTechniqueMatch[index];

		if(currentTechnique.favcheck == true){

			if(currentTechnique.assigncheck == true){

				idealText2 += '<input  type="radio" id="fav'
				+ currentTechnique.id + '" name="assessmentTech1" value="'
				+ currentTechnique.id + '"><span id="span1-'
				+currentTechnique.id+'" class="icons assessmentFavAssign"><label for="' + currentTechnique.id
				+ '">'
				+ currentTechnique.title +'</label></span></input>';

			}
			else if(currentTechnique.assigncheck == false){

				idealText2 += '<input  type="radio" id="fav'
				+ currentTechnique.id + '" name="assessmentTech1" value="'
				+ currentTechnique.id + '"><span id="span1-'
				+currentTechnique.id+'" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id
				+ '">'
				+ currentTechnique.title +'</label></span></input>';

			}

		}//main if


		$('#assessmentFavoritesDiv').html(idealText2);
		$('#assessmentFavoritesDiv').buttonset();
	}



	var extendedText = '';
	// take the titles and make html code to display
	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];



					extendedText += '<input  type="radio" id="'
					+ currentTechnique.id + '" name="assessmentTech1" value="'
					+ currentTechnique.id + '"><span id="span-'
					+currentTechnique.id+'" class="icons"><label for="' + currentTechnique.id
					+ '">'
					+ currentTechnique.title +'</label></span></input>';


	}


	var extendedText2 = '';
	// take the titles and make html code to display
	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];


		if(currentTechnique.favcheck == true){
			if(currentTechnique.assigncheck == true){

				extendedText2 += '<input  type="radio" id="fav'
				+ currentTechnique.id + '" name="assessmentTech1" value="'
				+ currentTechnique.id + '"><span id="span1-'
				+currentTechnique.id+'" class="icons assessmentFavAssign "><label for="' + currentTechnique.id
				+ '">'
				+ currentTechnique.title +'</label></span></input>';
			}

			else if(currentTechnique.assigncheck == false){

				extendedText2 += '<input  type="radio" id="'
				+ currentTechnique.id + '" name="assessmentTech1" value="fav'
				+ currentTechnique.id + '"><span id="span1-'
				+currentTechnique.id+'" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id
				+ '">'
				+ currentTechnique.title +'</label></span></input>';
			}

		}//main if


				$('#assessmentFavoritesDiv1').html(extendedText2);
				$('#assessmentFavoritesDiv1').buttonset();

	}


	// add html code to the page

	$('#ideal-matches1').html(idealText1);
	$('#extended-matches').html(extendedText);




	$('#ideal-matches1').buttonset();
	$('#extended-matches').buttonset();
//	$('#assessmentFavoritesDiv').buttonset();
}


function displayAssessmentPlan(data) {

	$.ajax({
			url: '../../assessmentTechnique/assessmentplan',
			method: 'post',
			data: JSON.stringify(data),
			contentType: 'application/json'
		})
		.done(assessmentPlanData);
}

/*
function dd(data){

	$.ajax({
			url: '../../assessmentTechnique/save',
			method: 'post',
			data: JSON.stringify(data),
			contentType: 'application/json'
		})
		.done(ddcheck);

}

function ddcheck(data){

	alert("alert for ddcheck"+data.newTechnique.length);

	for (var index = 0; index < data.newTechnique.length; index++) {

	}

}
*/

function assessmentPlanData(data){
	var allAssessmentData ='';

	//alert(data.learningDomains.id);
	for (var index = 0; index < data.assessmentTechInstance.length; index++) {
		var assessmentPlan = data.assessmentTechInstance[index];



		var techkd = '';
		var techld = '';
		var techdc ='';
		for(var ind = 0; ind < assessmentPlan.knowledgeDimension.length; ind++){
			var xx = assessmentPlan.knowledgeDimension[ind];
		//	alert("xx"+ xx.id);
			techkd = xx.id;
		//	alert("techkd"+techkd);
		}

		//alert("after loop techkd:"+techkd);
		var xkd = "";
		var kd='';

		for(var index1 = 0; index1 < data.knowledgeDimensions.length; index1++){
			kd = data.knowledgeDimensions[index1];

			//alert("kd:"+ kd.id);
			//alert("for works");

			if(kd.id == techkd)
			{
				//alert("if works"+kd+techkd);
				var xkd= kd.description;

				//alert("final X::"+xkd);
			}
		}


	allAssessmentData += '<div id="'+assessmentPlan.id+'"><span><h2>'
						  +assessmentPlan.title+'</h2></span><br/><span> Description : '
						  +assessmentPlan.description+'</span><br/><span>'
					      +assessmentPlan.procedure+'</span><br/><span>'
						  +assessmentPlan.duration+'</span><br/><span>DomainCategory :'
						  +xdc+'</span><br/><span>Learning Domain :'
						  +xld+'</span><br/><span> Knowledge Dimension : '
						  +xkd.description+'</span><br/><span>'
						  +assessmentPlan.assessmentFeedback.name+'</span>'
						 '</div>';

	//alert(allAssessmentData);

	}

	$('#assessmentData').html(allAssessmentData);

}


function displayAssessmentInformationInEdit(data) {
	$('#techniqueId1').val($('label.ui-state-active').attr('for'));


	$.ajax({
			url: '../../assessmentTechnique/display/' + $('label.ui-state-active').attr('for'),
			method: 'GET'
		})
		.done(populateAssessmentTechnique);
}

function populateAssessmentTechnique(data) {
	var currentTechnique = data.assessmentTechnique;

	for (var index = 0; index < data.assessmentTechnique.length; index++) {

		//alert("alert"+data.assessmentTechnique.id);
	}




//	alert("current tech params :::: assign :   "+ currentTechnique.assigncheck+" favorite :  "+currentTechnique.favcheck);

	// set the text fields
	$('#title1').val(currentTechnique.title);
	$('#title2').val(currentTechnique.title);

	$('#description1').val(currentTechnique.description);
	$('#description2').val(currentTechnique.description);

	$('#procedure1').val(currentTechnique.procedure);
	$('#procedure2').val(currentTechnique.procedure);

	$("input[name='duration1']").val(currentTechnique.duration);
	$("input[name='duration2']").val(currentTechnique.duration);

//	alert("this is the learning domain chosen"+ currentTechnique.id);


	$("input[name='assessmentFeedback1']").val(currentTechnique.assessmentFeedback);

	var checkedCheckboxes = $('.assessmentFeedback2:checked');

	$('.assessmentFeedback3').val($.each(checkedCheckboxes, function(index, checkbox)
		{var theValue = checkbox.value;}
	));


//alert($('#learningDomain option[value=' + currentTechnique.learningDomain.id + ']').text());
		//alert("this is the display alert to return value"+ ld);
	$('#domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);



	$( ".allInputs" ).hide();

	$(".allInputs1").replaceWith( function() {
		return "<span class=\"allspans1\"> :" + $( this ).val() + "</span>";
	});


	$('#View').hide();


}


//clicking on edit and View in display technique modal
$( "#Edit" ).click( function() {

	$('#View').show();

	$( ".allInputs" ).show();

	$( ".allspans1" ).hide();
});



$( "#View" ).click( function() {

$(".allInputs").hide();
$( ".allspans1" ).show();

});
