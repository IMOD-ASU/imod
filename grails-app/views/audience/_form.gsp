<%@ page import="imodv6.Audience" %>



<div class="fieldcontain ${hasErrors(bean: audienceInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="audience.description.label" default="Description" />

	</label>
	<g:textField name="description" value="${audienceInstance?.description}"/>
</div>

