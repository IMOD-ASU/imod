
<%@ page import="imodv6.ScheduleRepeatsEvery" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scheduleRepeatsEvery.label', default: 'ScheduleRepeatsEvery')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-scheduleRepeatsEvery" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scheduleRepeatsEvery" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'scheduleRepeatsEvery.description.label', default: 'Description')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scheduleRepeatsEveryInstanceList}" status="i" var="scheduleRepeatsEveryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scheduleRepeatsEveryInstance.id}">${fieldValue(bean: scheduleRepeatsEveryInstance, field: "description")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scheduleRepeatsEveryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
