<%@ page import="imodv6.Imod" %>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="imod.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${imodInstance?.name}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'imodNumber', 'error')} ">
	<label for="imodNumber">
		<g:message code="imod.imodNumber.label" default="Course Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="imodNumber" value="${imodInstance?.imodNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="imod.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="url" value="${imodInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'courseLocation', 'error')} ">
	<label for="courseLocation">
		<g:message code="imod.courseLocation.label" default="Classroom Location" />
		
	</label>
	<g:textField name="courseLocation" value="${imodInstance?.courseLocation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'courseSemester', 'error')} ">
	<label for="courseSemester">
		<g:message code="imod.courseSemester.label" default="Semester" />
		
	</label>
	<g:textField name="courseSemester" value="${imodInstance?.courseSemester}"/>
</div>
