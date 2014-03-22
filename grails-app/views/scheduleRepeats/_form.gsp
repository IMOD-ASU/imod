<%@ page import="imodv6.ScheduleRepeats" %>



<div class="fieldcontain ${hasErrors(bean: scheduleRepeatsInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="scheduleRepeats.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${scheduleRepeatsInstance?.description}"/>
</div>



