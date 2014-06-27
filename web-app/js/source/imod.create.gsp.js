$(document).ready(function() {
	document.getElementById("repeats").removeAttribute("multiple")
	document.getElementById("repeatsEvery").removeAttribute("multiple")
	$("#LO_hide_from_Objective").change(function(){
		changeDefinition("hideFromObjective", this.checked)
	});
	$(".LO_condition_data").change(function(){
		propagateToDefinition("condition", this.value)
		changeDefinition("condition", this.value)
	});	$("#LO_condition_custom").keyup(function() {
		propagateToDefinition("condition",this.value)
	});
	$("#tabs").tabs({
		ajaxOptions: {
			error: function (xhr, status, index, anchor) {
				$(anchor.hash).html('errorLoadingTabMessage');
			}
		}
	});
	$("#accordion").accordion();
	$( "#help_placeholder" ).draggable();
	$("#open_help").draggable();
	jQuery.browser = {};
	(function () {
		jQuery.browser.msie = false;
		jQuery.browser.version = 0;
		if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
			jQuery.browser.msie = true;
			jQuery.browser.version = RegExp.$1;
		}
	})();
	jQuery('.showHoverNew').qtip({
		style: {background: 'transparent', border: 'none', color: 'black'},
		show: 'mouseover',
		hide: 'mouseout',
		position: {
			type: 'static',
			container: $('div#qtipPlace')
		}
	});
	$("#tabs-LO-subhead").tabs({
			activate: function(event,ui){
				var active=$('#tabs-LO-subhead').tabs('option','active');
				var imageSrc=$("#tabs-LO-subhead ul>li a").eq(active).attr("file");

				$("#LO-type-icon").attr("src",imageSrc);
			}
		}
	);
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
});

function showHelp(displayHelp){

	if (displayHelp!="true"){
		$("#help_placeholder").css("display","none")
	}

}
function updateDatePicker (dateFormat) {
	$("input[value='date.struct']:hidden").each(function() {
		var name = $(this).attr('name');
		var id = name.replace(".", "_").replace("[", "_").replace("]", "_") + "_input"; // Create JQuery Friendly ID

		if ($('#'+id).length == 0) {

			// Find the Select Elements
			var selectDay= $(this).nextAll("select:eq(0)").hide();
			var selectMonth = $(this).nextAll("select:eq(1)").hide();
			var selectYear = $(this).nextAll("select:eq(2)").hide();

			// Get the Values
			var dateDay= $(selectDay).val();
			var dateMonth = $(selectMonth).val();
			var dateYear = $(selectYear).val();

			// Calculate the Current Input Value
			var val = "";
			if (dateDay != "" && dateYear != "" && dateMonth != "") { // If there is a date in the Selects then use it otherwise it's empty
				var date = new Date (dateYear, dateMonth-1, dateDay);
				val = $.datepicker.formatDate(dateFormat, date);
			}

			// Create element
			var template = "<input type='text' name='"+ id +"' id='"+ id +"' value='"+ val +"'/>";

			if ($(this).parent(".datePickerCalenderView").size()) {
				template = "<div id='"+ id +"'/>";
			}


			$(this).before(template);
			var displayWidget = $('#' + id );

			displayWidget.blur(function() {
				var date = $.datepicker.parseDate(dateFormat, $(this).val());

				if (date == null) {
					$(selectDay).val("");
					$(selectMonth).val("");
					$(selectYear).val("");
				}
				else {
					$(selectDay).val(date.getDate());
					$(selectMonth).val(date.getMonth()+1);
					$(selectYear).val(date.getFullYear());
				}
			}).keydown(function(event) {
				// Show popup on Down Arrow
				if (event.keyCode == 40) {
					displayWidget.datepicker("show");
				}
			});

			displayWidget.datepicker({
				changeMonth: true,
				changeYear: true,
				dateFormat: dateFormat,
				constrainInput: true,
				showButtonPanel: true,
				showWeeks: true,
				showOn: 'button',
				onSelect: function(dateText, inst) {
					if (inst == null) {
						$(selectDay).val("");
						$(selectMonth).val("");
						$(selectYear).val("");
					}
					else {
						$(selectDay).val(inst.selectedDay);
						$(selectMonth).val(inst.selectedMonth+1);
						$(selectYear).val(inst.selectedYear);
					}
				}
			});
		}
	});
}
function changeDefinition(type, value){
	$.ajax({
		url:"../learningObjective/updateDefinition",
		type:"POST",
		dataType:'json',
		data:{type:type, value:value},
		success: function(data){
			console.log(data);
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
	});
	$("#accordion").accordion();


});

}
function propagateToDefinition(type, value){
	var definitionType=".learning-objective-"+type
	$(definitionType).text(value)
}

function toggleHelp(){
	var helpbox=$("#help_placeholder")
	var openbox=$("#open_help")
	if (helpbox.css("display")=="block"){
		boxtop=helpbox.css("top")
		boxleft=helpbox.css("left")
		helpbox.css("display","none")
		openbox.css("display","block")
		openbox.css("top",boxtop)
		openbox.css("left",boxleft)
	}
	else {
		boxtop=openbox.css("top")
		boxleft=openbox.css("left")
		helpbox.css("display","block")
		openbox.css("display","none")
		helpbox.css("top",boxtop)
		helpbox.css("left",boxleft)
	}

}
