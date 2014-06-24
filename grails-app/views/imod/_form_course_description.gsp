<%@ page import="imodv6.Imod" %>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'overview', 'error')} ">
	<label for="overview">
		<g:message code="imod.overview.label" default="Overview" />

	</label>
	<g:textArea name="overview" value="${imodInstance?.overview}" rows="4" cols="40"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'subjectArea', 'error')} ">
	<label for="subjectArea">
		<g:message code="imod.subjectArea.label" default="Subject Area" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subjectArea" value="${imodInstance?.subjectArea}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'audience', 'error')} ">
	<label for="audience">
		<g:message code="imod.audience.label" default="Audience" />

	</label>
	<g:select name="audience" from="${imodv6.Audience.list()}" noSelection="${['none':'No Audience'] }"multiple="multiple" optionKey="id" size="5" value="${imodInstance?.audience*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'creditHours', 'error')} ">
	<label for="creditHours">
		<g:message code="imod.creditHours.label" default="Credit Hours" />

	</label>
	<g:field name="creditHours" type="number" value="${imodInstance.creditHours}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'timeRatio', 'error')} ">
	<label for="timeRatio">
		<g:message code="imod.timeRatio.label" default="Time Ratio" />

	</label>
	<g:textField name="timeRatio" value="${imodInstance?.timeRatio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'numberOfSeats', 'error')} ">
	<label for="numberOfSeats">
		<g:message code="imod.numberOfSeats.label" default="Number Of Seats" />

	</label>
	<g:field name="numberOfSeats" type="number" value="${imodInstance.numberOfSeats}"/>
</div>

