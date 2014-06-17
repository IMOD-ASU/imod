
<%@ page import="imodv6.InstructorPhone" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instructorPhone.label', default: 'InstructorPhone')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-instructorPhone" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-instructorPhone" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="instructorPhone.instructor.label" default="Instructor" /></th>
					
						<g:sortableColumn property="number" title="${message(code: 'instructorPhone.number.label', default: 'Number')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'instructorPhone.type.label', default: 'Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${instructorPhoneInstanceList}" status="i" var="instructorPhoneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${instructorPhoneInstance.id}">${fieldValue(bean: instructorPhoneInstance, field: "instructor")}</g:link></td>
					
						<td>${fieldValue(bean: instructorPhoneInstance, field: "number")}</td>
					
						<td>${fieldValue(bean: instructorPhoneInstance, field: "type")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${instructorPhoneInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
