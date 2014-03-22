<%@ page import="imodv6.ScheduleRepeatsEvery" %>



<div class="fieldcontain ${hasErrors(bean: scheduleRepeatsEveryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="scheduleRepeatsEvery.description.label" default="Description" />
	</label>
	<g:textField name="description" value="${scheduleRepeatsEveryInstance?.description}"/>
</div>


