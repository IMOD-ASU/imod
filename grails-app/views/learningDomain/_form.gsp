<%@ page import="imodv6.LearningDomain" %>



<div class="fieldcontain ${hasErrors(bean: learningDomainInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="learningDomain.name.label" default="Name" />

	</label>
	<g:textField name="name" value="${learningDomainInstance?.name}"/>
</div>

