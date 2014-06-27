<%@ page import="imodv6.ComponentDesignation" %>



<div class="fieldcontain ${hasErrors(bean: componentDesignationInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="componentDesignation.description.label" default="Description" />
	</label>
	<g:textField name="description" value="${componentDesignationInstance?.description}"/>
</div>

