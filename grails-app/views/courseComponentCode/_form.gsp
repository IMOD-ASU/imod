<%@ page import="imodv6.CourseComponentCode" %>



<div class="fieldcontain ${hasErrors(bean: courseComponentCodeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="courseComponentCode.description.label" default="Description" />
	</label>
	<g:textField name="description" value="${courseComponentCodeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseComponentCodeInstance, field: 'designation', 'error')} required">
	<label for="designation">
		<g:message code="courseComponentCode.designation.label" default="Designation" />
		<span class="required-indicator">
			*
		</span>
	</label>
	<g:select id="designation" name="designation.id" from="${imodv6.ComponentDesignation.list()}" optionKey="id" required="" value="${courseComponentCodeInstance?.designation?.id}" class="many-to-one"/>
</div>

