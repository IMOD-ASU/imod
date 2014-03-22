<%@ page import="imodv6.Schedule" %>



<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'endOccurences', 'error')} ">
	<label for="endOccurences">
		<g:message code="schedule.endOccurences.label" default="End Occurences" />
		
	</label>
	<g:field name="endOccurences" type="number" value="${scheduleInstance.endOccurences}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'sunday', 'error')} ">
	<label for="sunday">
		<g:message code="schedule.sunday.label" default="Sunday" />
		
	</label>
	<g:field name="sunday" type="number" value="${scheduleInstance.sunday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'monday', 'error')} ">
	<label for="monday">
		<g:message code="schedule.monday.label" default="Monday" />
		
	</label>
	<g:field name="monday" type="number" value="${scheduleInstance.monday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'tuesday', 'error')} ">
	<label for="tuesday">
		<g:message code="schedule.tuesday.label" default="Tuesday" />
		
	</label>
	<g:field name="tuesday" type="number" value="${scheduleInstance.tuesday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'wednesday', 'error')} ">
	<label for="wednesday">
		<g:message code="schedule.wednesday.label" default="Wednesday" />
		
	</label>
	<g:field name="wednesday" type="number" value="${scheduleInstance.wednesday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'thursday', 'error')} ">
	<label for="thursday">
		<g:message code="schedule.thursday.label" default="Thursday" />
		
	</label>
	<g:field name="thursday" type="number" value="${scheduleInstance.thursday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'friday', 'error')} ">
	<label for="friday">
		<g:message code="schedule.friday.label" default="Friday" />
		
	</label>
	<g:field name="friday" type="number" value="${scheduleInstance.friday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'saturday', 'error')} ">
	<label for="saturday">
		<g:message code="schedule.saturday.label" default="Saturday" />
		
	</label>
	<g:field name="saturday" type="number" value="${scheduleInstance.saturday}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'repeatsDays', 'error')} ">
	<label for="repeatsDays">
		<g:message code="schedule.repeatsDays.label" default="Repeats Days" />
		
	</label>
	<g:textField name="repeatsDays" value="${scheduleInstance?.repeatsDays}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="schedule.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${scheduleInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'imod', 'error')} required">
	<label for="imod">
		<g:message code="schedule.imod.label" default="Imod" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="imod" name="imod.id" from="${imodv6.Imod.list()}" optionKey="id" required="" value="${scheduleInstance?.imod?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="schedule.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${scheduleInstance?.startDate}"  />
</div>

