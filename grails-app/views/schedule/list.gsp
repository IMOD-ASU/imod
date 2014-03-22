
<%@ page import="imodv6.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-schedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-schedule" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="endOccurences" title="${message(code: 'schedule.endOccurences.label', default: 'End Occurences')}" />
					
						<g:sortableColumn property="sunday" title="${message(code: 'schedule.sunday.label', default: 'Sunday')}" />
					
						<g:sortableColumn property="monday" title="${message(code: 'schedule.monday.label', default: 'Monday')}" />
					
						<g:sortableColumn property="tuesday" title="${message(code: 'schedule.tuesday.label', default: 'Tuesday')}" />
					
						<g:sortableColumn property="wednesday" title="${message(code: 'schedule.wednesday.label', default: 'Wednesday')}" />
					
						<g:sortableColumn property="thursday" title="${message(code: 'schedule.thursday.label', default: 'Thursday')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scheduleInstanceList}" status="i" var="scheduleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scheduleInstance.id}">${fieldValue(bean: scheduleInstance, field: "endOccurences")}</g:link></td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "sunday")}</td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "monday")}</td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "tuesday")}</td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "wednesday")}</td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "thursday")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scheduleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
