var activity = 2;
var reference = 2;

// To open Add New Technique Dialog box
function addNewTechnique() {
	jQuery("#showAddNewTechnique").dialog("open");
		activity = 2;
	reference = 2;
		return false;
}

// To open Clone Technique Dialog box
function cloneTechnique() {
	jQuery("#cloneTechnique").dialog("open");
		return false;
}

// To close Add New Technique Dialog box
function closeNewTechniqueTable() {
		jQuery("#showAddNewTechnique").dialog("close");
		return false;
}

// To close Clone Technique Dialog box
function closeCloneTechniqueTable(data) {
	if(data=="done"){
		jQuery("#cloneTechnique").dialog("close");
		var test = 4;
		location.href = "44?loadPedagogyTab=true&objectiveId=4"
			return false;
	}
}

// To Validate while creating a new technique
function onSubmitValidate(){
	if($("#pedagogyTitle_dialog").val()==''){
		alert("Please provide title");
		return false;
	}
	if($("#pedagogyModeId_dialog option:selected").val()==''){
		alert("Please select Mode");
		return false;
	}
	if($("#domain_dialog").val()==null){
		alert("Please select Domain");
		return false;
	}
	if($("#category_dialog").val()==null){
		alert("Please select Category");
		return false;
	}
	if($("#knowledge_dialog").val()==null){
		alert("Please select Knowledge Dimension");
		return false;
	}
	return true;
}


var referenceTypeOptions="";
var durationOptions="";

//Initializing the jquery ui - accordions, dialogs and tool tip
$(function() {
		jQuery("#showAddNewTechnique").dialog({autoOpen: false, height: 500, width: 750, show: "fadein", hide: "fadeout", modal: true});
		jQuery("#cloneTechnique").dialog({autoOpen: false, height: 500, width: 750, show: "fadein", hide: "fadeout", modal: true});
		$( "#ped1_accordion" ).accordion({
			heightStyle: "content"
		});
		$("#ped_technique").accordion({
			heightStyle: "content",
			collapsible: true,
			active: false
		});
		$("#performance-tab").tooltip({
				position: {
						my: "center bottom-20",
						at: "center top",
						using: function( position, feedback ) {
							$( this ).css( position );
							$( "<div>" )
								.addClass( "arrow" )
								.addClass( feedback.vertical )
								.addClass( feedback.horizontal )
								.appendTo( this );
						}
					}
				});
		$("#content-tab").tooltip({
				position: {
						my: "center bottom-20",
						at: "center top",
						using: function( position, feedback ) {
							$( this ).css( position );
							$( "<div>" )
								.addClass( "arrow" )
								.addClass( feedback.vertical )
								.addClass( feedback.horizontal )
								.appendTo( this );
						}
					}
				});
		$("#condition-tab").tooltip({
				position: {
						my: "center bottom-20",
						at: "center top",
						using: function( position, feedback ) {
							$( this ).css( position );
							$( "<div>" )
								.addClass( "arrow" )
								.addClass( feedback.vertical )
								.addClass( feedback.horizontal )
								.appendTo( this );
						}
					}
				});
		$("#criteria-tab").tooltip({
				position: {
						my: "center bottom-20",
						at: "center top",
						using: function( position, feedback ) {
							$( this ).css( position );
							$( "<div>" )
								.addClass( "arrow" )
								.addClass( feedback.vertical )
								.addClass( feedback.horizontal )
								.appendTo( this );
						}
					}
				});
			referenceTypeOptions = "<option value=''>Select one</option>"
				<g:each var="referenceType" in="${referenceTypeList}">
					referenceTypeOptions += "<option value='${referenceType.id}'>${referenceType.description}</option>"
				</g:each>
				durationOptions = "<option value=''>Select one</option>"
				<g:each var="duration" in="${PedagogyActivityDuration.list()}">
					durationOptions += "<option value='${duration.id}'>${duration.duration}</option>"
				</g:each>
	});

// Expand the favorite accordion
	function expandFavorite(){
		$("#ped1_accordion").accordion({active:2});
	}

// Expand the extended match accordion
	function expandExtended(){
		$("#ped1_accordion").accordion({active:1});
	}

// To add activity row dynamically at new and clone technique
	function addRowActivity(flag){
		var newRow = "<tr> <td><input type='text' name='pedagogyActivity"+activity+".title' value=''/></td> <td><input type='text' name='pedagogyActivity"+activity+".description' value=''/></td> <td><select name='pedagogyActivity"+activity+".duration'>"+durationOptions+"</select></td> <td><input type='text' name='pedagogyActivity"+activity+".example' value=''/></td> <td><input type='text' name='pedagogyActivity"+activity+".material' value=''/></td><td><a href='javascript:' onclick='removeRow(this);' style='text-decoration:none;font-weight:bold;'>x</a></td></tr>";
		if(flag==true){
			$("form#cloneTechniqueForm").find("#techniqueTableData").append(newRow);
		}else{
			$("#techniqueTableData").append(newRow);
		}
		activity++;
	}

// To remove activity/reference row dynamically at new and clone technique
	function removeRow(ref,flag){
		var rowCount = ref.parentNode.parentNode.parentNode.rows.length;
		if(rowCount > 2){
			var row = ref.parentNode.parentNode;
			row.parentNode.removeChild(row);
			activity--;
		}
	}

// To add reference row dynamically at new and clone technique
	function addRowReference(flag){
		var newRow = "<tr> <td><input type='text' name='pedagogyReference"+reference+".title' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".author' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".description' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".referenceLinkISBN' value=''/></td> <td><select name='pedagogyReference"+reference+".refeType'>"+referenceTypeOptions+"</select></td><td><a href='javascript:' onclick='removeRow(this);' style='text-decoration:none;font-weight:bold;'>x</a></td></tr>";
		if(flag==true){
			$("form#cloneTechniqueForm").find("#referenceTableData").append(newRow);
		}else{
			$("#referenceTableData").append(newRow);
		}
		reference++;
	}

// Ajax call to clone activity
	function clonePedagogyTech(id){
		${remoteFunction(controller: 'pedagogy', action: 'clonePedagogyTech', update:'cloneTechnique', params:'\'techId=\'+id', onSuccess:'cloneTechnique();')};
	}
