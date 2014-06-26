<%@ page import="imodv6.Help; imodv6.Imod" %>


<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="imod.schedule.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="schedule.startDate" precision="day"  value="${imodInstance?.schedule?.startDate}" class="showHoverNew"  title="${Help.toolTip("OVERVIEW", "Schedule start Date")}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="imod.schedule.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="schedule.endDate" precision="day"  value="${imodInstance?.schedule?.endDate}" title="${Help.toolTip("OVERVIEW", "Schedule end Date")}" class="showHoverNew"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'repeats', 'error')} ">
	<label for="repeats">
		<g:message code="imod.repeats.label" default="Repeats" />

	</label>
	<g:select id="repeats" name="repeats.id" from="${imodv6.ScheduleRepeats.list()}" optionKey="id" value="${imodInstance?.repeats*.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'repeatsEvery', 'error')} ">
	<label for="repeatsEvery">
		<g:message code="imod.repeatsEvery.label" default="Repeats Every" />

	</label>
	<g:select id="repeatsEvery" name="repeatsEvery.id" from="${imodv6.ScheduleRepeatsEvery.list()}" optionKey="id" value="${imodInstance?.repeatsEvery*.id}" class="many-to-one"/>
</div>
