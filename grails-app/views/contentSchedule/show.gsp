
<%@ page import="imodv6.ContentSchedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contentSchedule.label', default: 'ContentSchedule')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contentSchedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-contentSchedule" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contentSchedule">
			
				<g:if test="${contentScheduleInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="contentSchedule.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:link controller="content" action="show" id="${contentScheduleInstance?.content?.id}">${contentScheduleInstance?.content?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contentScheduleInstance?.imod}">
				<li class="fieldcontain">
					<span id="imod-label" class="property-label"><g:message code="contentSchedule.imod.label" default="Imod" /></span>
					
						<span class="property-value" aria-labelledby="imod-label"><g:link controller="imod" action="show" id="${contentScheduleInstance?.imod?.id}">${contentScheduleInstance?.imod?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contentScheduleInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="contentSchedule.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${contentScheduleInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contentScheduleInstance?.id}" />
					<g:link class="edit" action="edit" id="${contentScheduleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
