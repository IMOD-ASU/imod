
<%@ page import="imodv6.ActionWord" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actionWord.label', default: 'ActionWord')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-actionWord" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-actionWord" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="actionWord" title="${message(code: 'actionWord.actionWord.label', default: 'Action Word')}" />
					
						<th><g:message code="actionWord.category.label" default="Category" /></th>
					
						<th><g:message code="actionWord.domain.label" default="Domain" /></th>
					
						<th><g:message code="actionWord.user.label" default="User" /></th>
					
						<g:sortableColumn property="visibility" title="${message(code: 'actionWord.visibility.label', default: 'Visibility')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${actionWordInstanceList}" status="i" var="actionWordInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${actionWordInstance.id}">${fieldValue(bean: actionWordInstance, field: "actionWord")}</g:link></td>
					
						<td>${fieldValue(bean: actionWordInstance, field: "category")}</td>
					
						<td>${fieldValue(bean: actionWordInstance, field: "domain")}</td>
					
						<td>${fieldValue(bean: actionWordInstance, field: "user")}</td>
					
						<td>${fieldValue(bean: actionWordInstance, field: "visibility")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${actionWordInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
