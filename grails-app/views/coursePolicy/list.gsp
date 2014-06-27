
<%@ page import="imodv6.CoursePolicy" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'coursePolicy.label', default: 'CoursePolicy')}" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<a href="#list-coursePolicy" class="skip" tabindex="-1">
			<g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
		</a>
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label"/>
					</a>
				</li>
				<li>
					<g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link>
				</li>
			</ul>
		</div>
		<div id="list-coursePolicy" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th>
							<g:message code="coursePolicy.category.label" default="Category" />
						</th>
						<g:sortableColumn property="description" title="${message(code: 'coursePolicy.description.label', default: 'Description')}" />
						<g:sortableColumn property="name" title="${message(code: 'coursePolicy.name.label', default: 'Name')}" />
						<th>
							<g:message code="coursePolicy.user.label" default="User" />
						</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${coursePolicyInstanceList}" status="i" var="coursePolicyInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>
								<g:link action="show" id="${coursePolicyInstance.id}">
									${fieldValue(bean: coursePolicyInstance, field: "category")}
								</g:link>
							</td>
							<td>
								${fieldValue(bean: coursePolicyInstance, field: "description")}
							</td>
							<td>
								${fieldValue(bean: coursePolicyInstance, field: "name")}
							</td>
							<td>
								${fieldValue(bean: coursePolicyInstance, field: "user")}
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${coursePolicyInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
