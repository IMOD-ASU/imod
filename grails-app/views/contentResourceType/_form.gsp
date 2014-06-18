<%@ page import="imodv6.ContentResourceType" %>



<div class="fieldcontain ${hasErrors(bean: contentResourceTypeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="contentResourceType.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${contentResourceTypeInstance?.description}"/>
</div>

