//Adding all style related JS on top
/*$("#new-technique").dialog({
    create: function(event, ui) {
      var widget = $(this).dialog("widget");
      $(".ui-dialog-titlebar-close span", widget)
          .removeClass("ui-icon-closethick")
          .addClass("ui-icon-minusthick");
   }
});

*/

'use strict';

// Load techniques on page load
filterAssessmentTechniques();

// Filters for the pedagogy technique are wrapped in a accordian
$('#filter-assessment-techniques').accordion();

$('#idealCollapser').load(function(){
	$('#ideal-matches1').slideToggle('slow');

	$('#extended-matches').hide();
});



$('#extCollapser').click(function(){
	$('#extended-matches').slideToggle('slow');


});

$('#favorites').click(function () {
	$('.favDiv').css('visibility', 'visible');
	$('.favDiv').css('display', 'block');
	$('#assessmentFavorites').css('visibility', 'visible');
	$('#assessmentFavorites').css('display', 'block');
	$('#assessmentFavoritesDiv').css('visibility', 'visible');
	$('#assessmentFavoritesDiv').css('display', 'block');

	$('#favorites').hide();
	$('#unfavorites').show();
});

$('#unfavorites').click(function () {
	$('.favDiv').css('visibility', 'hidden');
	$('.favDiv').css('display', 'none');
	$('#assessmentFavorites').css('visibility', 'hidden');
	$('#assessmentFavorites').css('display', 'none');
	$('#assessmentFavoritesDiv').css('visibility', 'hidden');
	$('#assessmentFavoritesDiv').css('display', 'none');

	$('#favorites').show();
	$('#unfavorites').hide();
});

// Auto hide the assessment plan modal
$('#assessment-plan').dialog({
	autoOpen: false
});

// Auto hide the add new technique modal
$('#new-technique').dialog({
	autoOpen: false
});

// Auto hide the add new technique modal
$('#display-new-technique').dialog({
	autoOpen: false
});

// Attach a listener to the checkboxes, to update the assessment techniques
// when the filters have been changed
$('input[name=knowledgeDimension]').on('change', filterAssessmentTechniques);
$('input[name=learningDomain]').on('change', filterAssessmentTechniques);
$('input[name=domainCategory]').on('change', filterAssessmentTechniques);

// When add new technique button is clicked open modal
$('#new-technique-button').on('click', openNewAssessmentTechniqueModal);

// When add assessment  plan button is clicked open modal
$('#assessment-plan-button').on('click', openAssessmentPlanModal);

/**
 * Opens the modal to create a new assessment technique
 */
function openAssessmentPlanModal() {
	$('#assessment-plan').dialog(
	{
		resizable: false,
		height: 'auto',
		title:'Assessment Plan',
		width: '600px',
		modal: true,
		zindex: 1001,
		autoReposition: true,
		buttons: {
			Cancel: function () {
				$(this).dialog('close');
			}
		}
	});
	$('#assessment-plan').dialog('open');
	displayAssessmentPlan();
}

/**
 * Opens the modal to create a new assessment technique
 */
function openNewAssessmentTechniqueModal() {
	$('#techniqueId').val('');

	$('#new-technique').dialog(
	{
		autoReposition: true,
		resizable: false,
		height: 'auto',
		width: 'auto',
		modal: true,
		zindex: 1001,
		autoReposition: true,
	});
	$('#new-technique').dialog('open');
}

/**
 * Reads which filters are selected and sends information to server to update
 * visible assessment techniques
 */
function filterAssessmentTechniques() {
	// Get all of the selected checkboxes
	var selectedKnowledgeDimensions = $('input[name=knowledgeDimension]:checked');
	var selectedLearningDomains = $('input[name=learningDomain]:checked');
	var selectedDomainCategories = $('input[name=domainCategory]:checked');

	// Arrays to store the data
	var selectedKnowledgeDimensionsData = [];
	var selectedLearningDomainsData = [];
	var selectedDomainCategoriesData = [];

	// Get the id of the grails domain from the value attribute in the html
	for (var index = 0; index < selectedKnowledgeDimensions.length; index++) {
		selectedKnowledgeDimensionsData[index] = selectedKnowledgeDimensions[index].value;
	}
	for (index = 0; index < selectedLearningDomains.length; index++) {
		selectedLearningDomainsData[index] = selectedLearningDomains[index].value;
	}
	for (index = 0; index < selectedDomainCategories.length; index++) {
		selectedDomainCategoriesData[index] = selectedDomainCategories[index].value;
	}

	// Bundle the data into an object
	var data = {
		selectedKnowledgeDimensions: selectedKnowledgeDimensionsData,
		selectedLearningDomains: selectedLearningDomainsData,
		selectedDomainCategories: selectedDomainCategoriesData
	};

	// Send the data to the find matching techniques action in grails
	// and process the response with the display pedagogy techniques callback
	$.ajax({
			url: '../findMatchingTechniques',
			method: 'post',
			data: JSON.stringify(data),
			contentType: 'application/json'
		})
		.done(displayAssessmentTechniques, showAssessmentTechnique, checkForAssign);
}

function checkForAssign(data) {
	var assignedLO='';
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.idealAssessmentTechniqueMatch[index];
	//alert("for loop for ideal tech");

	for(var assignedLOindex=0; assignedLOindex<currentTechnique.assignedLearningObjective.length; assignedLOindex++){
		assignedLO =currentTechnique.assignedLearningObjective[assignedLOindex]
	//alert("assignedLO list"+assignedLO.id+" currentLO::"+currentLO+" condition:::"+(currentLO == assignedLO.id));

	if(currentLO == assignedLO.id)

	{
	//	alert("== CHECK :::"+currentLO +" == "+ assignedLO.id);
		if (currentTechnique.assigncheck === true && currentTechnique.favcheck === false) {
			//alert("T T");
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		}
		else if (currentTechnique.assigncheck === true && currentTechnique.favcheck === true) {
			//alert("T F");
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		}


		else if (currentTechnique.assigncheck === false && currentTechnique.favcheck === true) {
			//alert("F T");
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		}
		else if (currentTechnique.assigncheck === false && currentTechnique.favcheck === false) {
			//alert("F F");
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavUnassign');
		}
	}

	}
	}

	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		if (currentTechnique.assigncheck === true && currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavAssign');
		} else if (currentTechnique.assigncheck === true && currentTechnique.favcheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavAssign');
		} else if (currentTechnique.assigncheck === false && currentTechnique.favcheck === true) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentFavUnassign');
		} else if (currentTechnique.assigncheck === false && currentTechnique.favcheck === false) {
			$('#span-' + currentTechnique.id).addClass('icons assessmentUnfavUnassign');
		}
	}
}


function showAssessmentTechnique() {
/*	$('#ideal-matches').buttonset().click(function () {
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();
	});
*/

	$('#ideal-matches1').buttonset().click(function () {

		$('#display-new-technique').dialog(
		{
			autoReposition: true,
			resizable: false,
			height: 'auto',
			width: 'auto',
			modal: true,
			zindex: 1001,
			autoReposition: true,
		});
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();

	});

	$('#extended-matches').click(function () {

		$('#display-new-technique').dialog({
			autoReposition: true,
			resizable: false,
			height: 'auto',
			width: 'auto',
			modal: true,
			zindex: 1001, // Default is 1000
		});
		$('#display-new-technique').dialog('open');
		displayAssessmentInformationInEdit();
	});
}

/**
 * Callback for find matching techniques grails action
 * this takes the json data and processes it into html code
 */
function displayAssessmentTechniques(data) {
	var locheck= data.currentLearningObjective;
	var idealText1 = '';
	var idealText2 = '';
	var extendedText = '';
	var assignedLO='';
//	alert("GLOBAL JS VAR::: "+currentLO);
	// Take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
	var currentTechnique = data.idealAssessmentTechniqueMatch[index];

/*	for(var assignedLOindex=0; assignedLOindex<currentTechnique.assignedLearningObjective.length; assignedLOindex++){
			assignedLO =currentTechnique.assignedLearningObjective[assignedLOindex]

		//	alert("assignedLO list"+assignedLO.id+" currentLO::"+currentLO+" condition:::"+(currentLO == assignedLO.id));

			if(currentLO == assignedLO.id)
			{*/
					idealText1 += '<input  type="radio" id="' + currentTechnique.id+ '" name="assessmentTech1" value="'
							+ currentTechnique.id + '"><span id="span-' + currentTechnique.id + '" class="icons"><label for="'
					+ currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
		/*	}
	}*/

}

	// Take the titles and make html code to display
	for (var index = 0; index < data.idealAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.idealAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === true) {
			if (currentTechnique.assigncheck === true) {
				idealText2 += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavAssign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			} else if (currentTechnique.assigncheck === false){
				idealText2 += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			}
		}

		$('#assessmentFavoritesDiv').html(idealText2);
		$('#assessmentFavoritesDiv').buttonset();
	}


	// Take the titles and make html code to display
	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];


		extendedText += '<input  type="radio" id="' + currentTechnique.id
		 			+ '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span-'
					+ currentTechnique.id + '" class="icons"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';

	}

	var extendedText2 = '';
	// Take the titles and make html code to display
	for (var index = 0; index < data.extendedAssessmentTechniqueMatch.length; index++) {
		var currentTechnique = data.extendedAssessmentTechniqueMatch[index];
		if (currentTechnique.favcheck === true) {
			if (currentTechnique.assigncheck === true) {
				extendedText2 += '<input  type="radio" id="fav' + currentTechnique.id + '" name="assessmentTech1" value="' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavAssign "><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			} else if (currentTechnique.assigncheck === false) {
				extendedText2 += '<input  type="radio" id="' + currentTechnique.id + '" name="assessmentTech1" value="fav' + currentTechnique.id + '"><span id="span1-' + currentTechnique.id + '" class="icons assessmentFavUnassign"><label for="' + currentTechnique.id + '">' + currentTechnique.title + '</label></span></input>';
			}
		}
		$('#assessmentFavoritesDiv').html(extendedText2);
		$('#assessmentFavoritesDiv').buttonset();
	}

	// Add html code to the page
	$('#ideal-matches1').html(idealText1);
	$('#extended-matches').html(extendedText);

	$('#ideal-matches1').buttonset();
	$('#extended-matches').buttonset();
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

function assessmentPlanData(data) {
	var allAssessmentData ='';
	var allLOData ='';
	var listLOTech ='';
	var loId='';
	var listAssignedTech='';
	var assignloId='';
	var assessmentPlan='';

	for(var indexer=0; indexer < data.listLO.length; indexer++){
		listLOTech = data.listLO[indexer];
		loId=listLOTech.id;
		//alert("loId:"+loId);
		allLOData ='<div class="loHeading">Learning Objective :<i>'+listLOTech.definition+'</i></div>';
	for (var index = 0; index < data.assessmentTechInstance.length; index++) {
		assessmentPlan = data.assessmentTechInstance[index];

	for(var indexer1=0; indexer1 < assessmentPlan.assignedLearningObjective.length; indexer1++){
		listAssignedTech = assessmentPlan.assignedLearningObjective[indexer1];
		//alert(	listAssignedTech.id);
		assignloId = listAssignedTech.id;
		//alert("asignloId:"+assignloId);
	if(loId == assignloId)
	{
		var techkd = '';
		var techld = '';
		var techdc ='';
		var techfm='';

		for(var ind = 0; ind < assessmentPlan.knowledgeDimension.length; ind++){
			var xx = assessmentPlan.knowledgeDimension[ind];
		//	alert("xx"+ xx.id);
			techkd = xx.id;
		//	alert("techkd"+techkd);
		}

		for(var ind1 = 0; ind1 < assessmentPlan.learningDomain.length; ind1++){
			var xx1 = assessmentPlan.learningDomain[ind1];
		//	alert("xx"+ xx.id);
			techld = xx1.id;
		//	alert("techkd"+techkd);
		}

		for(var ind2 = 0; ind2 < assessmentPlan.domainCategory.length; ind2++){
			var xx2 = assessmentPlan.domainCategory[ind2];
		//	alert("xx"+ xx.id);
			techdc = xx2.id;
		//	alert("techkd"+techkd);
		}

		for(var ind3 = 0; ind3 < assessmentPlan.assessmentFeedback.length; ind3++){
			var xx3 = assessmentPlan.assessmentFeedback[ind3];
		//	alert("xx"+ xx.id);
			techfm = xx3.id;
		//	alert("techkd"+techkd);
		}
		//alert("after loop techkd:"+techkd);
		var xkd = '';
		var xdc='';
		var xld='';
		var xfm='';
		var kd='';
		var ld='';
		var dc='';
		var fm='';

		for(var index1 = 0; index1 < data.knowledgeDimensions.length; index1++){
			kd = data.knowledgeDimensions[index1];
			if(kd.id == techkd)
			{
				//alert("if works"+kd+techkd);
				var xkd= kd.description;

				//alert("final X::"+xkd);
			}
		}

		for(var index2 = 0; index2 < data.learningDomains.length; index2++){
			ld = data.learningDomains[index2];
			if(ld.id == techld)
			{
				//alert("if works"+kd+techkd);
				var xld= ld.name;

				//alert("final X::"+xkd);
			}
		}

		for(var index3 = 0; index3 < data.domainCategories.length; index3++){
			dc = data.domainCategories[index3];
			if(dc.id == techdc)
			{
				//alert("if works"+kd+techkd);
				var xdc= dc.name;
				//alert("final X::"+xkd);
			}
		}

		for(var index4 = 0; index4 < data.assessmentFeedback.length; index4++){
			fm = data.assessmentFeedback[index4];
			if(fm.id == techfm)
			{
				//alert("if works"+kd+techkd);
				var xfm= fm.name;
				//alert("final X::"+xkd);
			}
		}

		//$('#listLO').html(allLOData);
	//alert("allLOData::"+listLOTech.id);
	allAssessmentData += allLOData +'<div class="loTechniques" id="'+assessmentPlan.id+'"><span><h2>'
						  +assessmentPlan.title+'</h2></span><span> Description : '
						  +assessmentPlan.description+'</span><br/><span>Procedure :'
					      +assessmentPlan.procedure+'</span><br/><span>Duration :'
						  +assessmentPlan.duration+'</span><br/><span>DomainCategory :'
						  +xdc+'</span><br/><span>Learning Domain :'
						  +xld+'</span><br/><span> Knowledge Dimension : '
						  +xkd+'</span><br/><br/>';

	}//loid == assignloId if ends

	}//assignedLearningObjective for loop ends
	}// assessmentTechniqueInstance for loop ends
	}//listLO for loop end

	$('#assessmentData').html(allAssessmentData);

}




function checkIndex(){

}

function displayAssessmentInformationInEdit() {
	$('#techniqueId1').val($('label.ui-state-active').attr('for'));

	$.ajax({
			url: '../../assessmentTechnique/display/' + $('label.ui-state-active').attr('for'),
			method: 'GET'
		})
		.done(populateAssessmentTechnique);
}

function populateAssessmentTechnique(data) {
	var currentTechnique = data.assessmentTechnique;

	// Set the text fields
	$('#title1').val(currentTechnique.title);
	$('#title2').val(currentTechnique.title);

	$('#description1').val(currentTechnique.description);
	$('#description2').val(currentTechnique.description);

	$('#procedure1').val(currentTechnique.procedure);
	$('#procedure2').val(currentTechnique.procedure);

	$('input[name=\'duration1\']').val(currentTechnique.duration);
	$('input[name=\'duration2\']').val(currentTechnique.duration);

	$('input[name=\'assessmentFeedback1\']').val(currentTechnique.assessmentFeedback);

	var checkedCheckboxes = $('.assessmentFeedback2:checked');

	$('.assessmentFeedback3').val($.each(checkedCheckboxes, function (index, checkbox) {
		// FIXME unused variable
		var theValue = checkbox.value;
	}));

	$('#domainCategory option[value=' + currentTechnique.domainCategory[0].id + ']').prop('selected', true);

	$('.allInputs').hide();

	$('.allInputs1').replaceWith(function () {
		return '<span class="allspans1">' +  $(this).val() + '</span>';
	});

	$('#viewModal').hide();
}

// Clicking on edit and View in display technique modal
$('#editModal').click(function () {
	$('#viewModal').show();
	$('.allInputs').show();
	$('.allspans1').hide();
});

$('#viewModal').click(function () {
	$('.allInputs').hide();
	$('.allspans1').show();
	$('#editModal').show();
});
