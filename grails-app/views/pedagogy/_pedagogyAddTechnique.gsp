<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyActivityFocus" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>
<%@ page import="imodv6.PedagogyActivity" %>
<%@ page import="imodv6.PedagogyReference" %>

<%--To render the add new Technique dialog box--%>
<g:form controller="pedagogy" action="addNewTechnique" name="newTechniqueForm" onsubmit="return onSubmitValidate();">
	<g:hiddenField name="id" value="${params.id ?: imod?.id}" />
	<g:render template="pedagogyTechniqueForm"></g:render>
	<div align="center" style="padding-top:10px">
		<input type="submit" class="showHover" value="Save" name="addNewSubmit" style="padding: 0; font-size: 16px; Background: #D0D0D0;" />
		<input type="button" class="showHover" value="Cancel" name="addNewCancel" style="margin-left:5px;padding: 0; font-size: 16px; Background: #D0D0D0;" onclick="closeNewTechniqueTable()" />
	</div>
</g:form>
