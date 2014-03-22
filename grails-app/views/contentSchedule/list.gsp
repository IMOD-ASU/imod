
<%@ page import="imodv6.ContentSchedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contentSchedule.label', default: 'ContentSchedule')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-contentSchedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-contentSchedule" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="contentSchedule.content.label" default="Content" /></th>
					
						<th><g:message code="contentSchedule.imod.label" default="Imod" /></th>
					
						<g:sortableColumn property="startDate" title="${message(code: 'contentSchedule.startDate.label', default: 'Start Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contentScheduleInstanceList}" status="i" var="contentScheduleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contentScheduleInstance.id}">${fieldValue(bean: contentScheduleInstance, field: "content")}</g:link></td>
					
						<td>${fieldValue(bean: contentScheduleInstance, field: "imod")}</td>
					
						<td><g:formatDate date="${contentScheduleInstance.startDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contentScheduleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
