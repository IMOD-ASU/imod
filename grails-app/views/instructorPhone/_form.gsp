<%@ page import="imodv6.InstructorPhone" %>



<div class="fieldcontain ${hasErrors(bean: instructorPhoneInstance, field: 'instructor', 'error')} required">
	<label for="instructor">
		<g:message code="instructorPhone.instructor.label" default="Instructor" />
		<span class="required-indicator">
			*
		</span>
	</label>
	<g:select id="instructor" name="instructor.id" from="${imodv6.Instructor.list()}" optionKey="id" required="" value="${instructorPhoneInstance?.instructor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorPhoneInstance, field: 'number', 'error')} ">
	<label for="number">
		<g:message code="instructorPhone.number.label" default="Number" />
	</label>
	<g:textField name="number" value="${instructorPhoneInstance?.number}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorPhoneInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="instructorPhone.type.label" default="Type" />
	</label>
	<g:textField name="type" value="${instructorPhoneInstance?.type}"/>
</div>

