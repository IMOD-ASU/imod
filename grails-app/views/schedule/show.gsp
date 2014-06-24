
<%@ page import="imodv6.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-schedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-schedule" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list schedule">

				<g:if test="${scheduleInstance?.endOccurences}">
				<li class="fieldcontain">
					<span id="endOccurences-label" class="property-label"><g:message code="schedule.endOccurences.label" default="End Occurences" /></span>

						<span class="property-value" aria-labelledby="endOccurences-label"><g:fieldValue bean="${scheduleInstance}" field="endOccurences"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.sunday}">
				<li class="fieldcontain">
					<span id="sunday-label" class="property-label"><g:message code="schedule.sunday.label" default="Sunday" /></span>

						<span class="property-value" aria-labelledby="sunday-label"><g:fieldValue bean="${scheduleInstance}" field="sunday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.monday}">
				<li class="fieldcontain">
					<span id="monday-label" class="property-label"><g:message code="schedule.monday.label" default="Monday" /></span>

						<span class="property-value" aria-labelledby="monday-label"><g:fieldValue bean="${scheduleInstance}" field="monday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.tuesday}">
				<li class="fieldcontain">
					<span id="tuesday-label" class="property-label"><g:message code="schedule.tuesday.label" default="Tuesday" /></span>

						<span class="property-value" aria-labelledby="tuesday-label"><g:fieldValue bean="${scheduleInstance}" field="tuesday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.wednesday}">
				<li class="fieldcontain">
					<span id="wednesday-label" class="property-label"><g:message code="schedule.wednesday.label" default="Wednesday" /></span>

						<span class="property-value" aria-labelledby="wednesday-label"><g:fieldValue bean="${scheduleInstance}" field="wednesday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.thursday}">
				<li class="fieldcontain">
					<span id="thursday-label" class="property-label"><g:message code="schedule.thursday.label" default="Thursday" /></span>

						<span class="property-value" aria-labelledby="thursday-label"><g:fieldValue bean="${scheduleInstance}" field="thursday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.friday}">
				<li class="fieldcontain">
					<span id="friday-label" class="property-label"><g:message code="schedule.friday.label" default="Friday" /></span>

						<span class="property-value" aria-labelledby="friday-label"><g:fieldValue bean="${scheduleInstance}" field="friday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.saturday}">
				<li class="fieldcontain">
					<span id="saturday-label" class="property-label"><g:message code="schedule.saturday.label" default="Saturday" /></span>

						<span class="property-value" aria-labelledby="saturday-label"><g:fieldValue bean="${scheduleInstance}" field="saturday"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.repeatsDays}">
				<li class="fieldcontain">
					<span id="repeatsDays-label" class="property-label"><g:message code="schedule.repeatsDays.label" default="Repeats Days" /></span>

						<span class="property-value" aria-labelledby="repeatsDays-label"><g:fieldValue bean="${scheduleInstance}" field="repeatsDays"/></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="schedule.endDate.label" default="End Date" /></span>

						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${scheduleInstance?.endDate}" /></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.imod}">
				<li class="fieldcontain">
					<span id="imod-label" class="property-label"><g:message code="schedule.imod.label" default="Imod" /></span>

						<span class="property-value" aria-labelledby="imod-label"><g:link controller="imod" action="show" id="${scheduleInstance?.imod?.id}">${scheduleInstance?.imod?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${scheduleInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="schedule.startDate.label" default="Start Date" /></span>

						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${scheduleInstance?.startDate}" /></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scheduleInstance?.id}" />
					<g:link class="edit" action="edit" id="${scheduleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
