
<%@ page import="imodv6.InstructorPhone" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instructorPhone.label', default: 'InstructorPhone')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-instructorPhone" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-instructorPhone" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list instructorPhone">

				<g:if test="${instructorPhoneInstance?.instructor}">
				<li class="fieldcontain">
					<span id="instructor-label" class="property-label"><g:message code="instructorPhone.instructor.label" default="Instructor" /></span>

						<span class="property-value" aria-labelledby="instructor-label"><g:link controller="instructor" action="show" id="${instructorPhoneInstance?.instructor?.id}">${instructorPhoneInstance?.instructor?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${instructorPhoneInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="instructorPhone.number.label" default="Number" /></span>

						<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${instructorPhoneInstance}" field="number"/></span>

				</li>
				</g:if>

				<g:if test="${instructorPhoneInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="instructorPhone.type.label" default="Type" /></span>

						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${instructorPhoneInstance}" field="type"/></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${instructorPhoneInstance?.id}" />
					<g:link class="edit" action="edit" id="${instructorPhoneInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
