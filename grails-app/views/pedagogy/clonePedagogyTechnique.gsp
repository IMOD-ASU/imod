<!-- FIXME remove all inline CSS from file -->
<!-- FIXME re organize template as a grails server page, instead of a fragment -->

<g:javascript src="source/pedagogy.pedagogyCloneTechnique.gsp.js" defer="defer" />

<%--Clone Technique Form --%>
<g:formRemote name="cloneTechniqueForm" id="cloneTechniqueForm" url="[controller:'pedagogy',action:'cloneSaveTech']" update="[success:'cloneTechnique',failure:'cloneTechnique']" onSuccess="closeCloneTechniqueTable(data);">
	<g:hiddenField name="id" value="${params.id ?: currentImod?.id}" />
	<g:hiddenField name="pedagogy_tech_id" value="${pedagogyTech?.id}" />
	<g:if test="${errorMsg}">
		Oops
	</g:if>
	<g:render template="pedagogyTechniqueForm"/>
	<div align="center" style="padding-top:10px">
		<input type="submit" class="showHover" value="Save" name="addNewSubmit" style="padding: 0; font-size: 16px; Background: #D0D0D0;" />
		<input type="button" class="showHover" value="Cancel" name="addNewCancel" style="margin-left:5px;padding: 0; font-size: 16px; Background: #D0D0D0;" onclick="closeCloneTechnique()" />
	</div>
</g:formRemote>
