<script>
// Initializing the Accordion for Activity & Reference
$(function() {
	$("form#cloneTechniqueForm").find("#ped_technique").accordion({
		heightStyle: "content",
		collapsible: true,
		active: false
	});
});


//To close Clone Technique dialog box 
function closeCloneTechnique() {
	jQuery("#cloneTechnique").dialog("close");
    return false;
}
</script>
<style>
.pedTech table th td{
    border: 1px solid black;
}
</style>
<%--Clone Technique Form --%>
<g:formRemote name="cloneTechniqueForm" id="cloneTechniqueForm" url="[controller:'pedagogy',action:'cloneSaveTech']" update="[success:'cloneTechnique',failure:'cloneTechnique']" onSuccess="closeCloneTechniqueTable(data);">
	<g:hiddenField name="id" value="${params.id ?: imod?.id}" />
	<g:hiddenField name="pedagogy_tech_id" value="${pedagogyTech?.id}" />
	<g:if test="${errorMsg}">
		<script>
			// Validation message for clone technique
			if("${errorMsg}"=="title"){
				alert("ooops!!! \nNo changes have been made to the title. \nPleae make changes to the Title and alteast one other field to create a clone.");
			}else{
				alert("ooops!!! \nPleae make changes to altlest one other field along with the title to create a clone.");
			}
		</script>
	</g:if>
	<g:render template="pedagogyTechniqueForm"></g:render>
	<div align="center" style="padding-top:10px">	
		<input type="submit" class="showHover" value="Save" name="addNewSubmit" style="padding: 0; font-size: 16px; Background: #D0D0D0;" />
		<input type="button" class="showHover" value="Cancel" name="addNewCancel" style="margin-left:5px;padding: 0; font-size: 16px; Background: #D0D0D0;" onclick="closeCloneTechnique()" />
	</div>
</g:formRemote>