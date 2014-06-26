<%@ page import="imodv6.ContentResource" %>



<div class="fieldcontain ${hasErrors(bean: contentResourceInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="contentResource.name.label" default="Name" />

	</label>
	<g:textField name="name" value="${contentResourceInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentResourceInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="contentResource.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${imodv6.ContentResourceType.list()}" optionKey="id" required="" value="${contentResourceInstance?.type?.id}" class="many-to-one"/>
</div>

