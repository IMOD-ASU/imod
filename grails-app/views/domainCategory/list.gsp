
<%@ page import="imodv6.DomainCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'domainCategory.label', default: 'DomainCategory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-domainCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-domainCategory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="domainCategory.domain.label" default="Domain" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'domainCategory.name.label', default: 'Name')}" />			
					</tr>
				</thead>
				<tbody>
				<g:each in="${domainCategoryInstanceList}" status="i" var="domainCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${domainCategoryInstance.id}">${fieldValue(bean: domainCategoryInstance, field: "domain")}</g:link></td>
					
						<td>${fieldValue(bean: domainCategoryInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${domainCategoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
