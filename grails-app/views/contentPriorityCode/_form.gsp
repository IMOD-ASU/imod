<%@ page import="imodv6.ContentPriorityCode" %>



<div class="fieldcontain ${hasErrors(bean: contentPriorityCodeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="contentPriorityCode.description.label" default="Description" />

	</label>
	<g:textField name="description" value="${contentPriorityCodeInstance?.description}"/>
</div>

