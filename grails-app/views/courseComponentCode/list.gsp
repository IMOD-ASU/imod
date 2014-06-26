
<%@ page import="imodv6.CourseComponentCode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'courseComponentCode.label', default: 'CourseComponentCode')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-courseComponentCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-courseComponentCode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="description" title="${message(code: 'courseComponentCode.description.label', default: 'Description')}" />

						<th><g:message code="courseComponentCode.designation.label" default="Designation" /></th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${courseComponentCodeInstanceList}" status="i" var="courseComponentCodeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${courseComponentCodeInstance.id}">${fieldValue(bean: courseComponentCodeInstance, field: "description")}</g:link></td>

						<td>${fieldValue(bean: courseComponentCodeInstance, field: "designation")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${courseComponentCodeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
