<%@ page import="imodv6.ContentSchedule" %>



<div class="fieldcontain ${hasErrors(bean: contentScheduleInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="contentSchedule.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="content" name="content.id" from="${imodv6.Content.list()}" optionKey="id" required="" value="${contentScheduleInstance?.content?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentScheduleInstance, field: 'imod', 'error')} required">
	<label for="imod">
		<g:message code="contentSchedule.imod.label" default="Imod" />
		<span class="required-indicator">
			*
		</span>
	</label>
	<g:select id="imod" name="imod.id" from="${imodv6.Imod.list()}" optionKey="id" required="" value="${contentScheduleInstance?.imod?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentScheduleInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="contentSchedule.startDate.label" default="Start Date" />
		<span class="required-indicator">
			*
		</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${contentScheduleInstance?.startDate}"  />
</div>

