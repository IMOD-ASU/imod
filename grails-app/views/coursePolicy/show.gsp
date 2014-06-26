
<%@ page import="imodv6.CoursePolicy" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'coursePolicy.label', default: 'CoursePolicy')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-coursePolicy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-coursePolicy" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list coursePolicy">

				<g:if test="${coursePolicyInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="coursePolicy.category.label" default="Category" /></span>

						<span class="property-value" aria-labelledby="category-label"><g:link controller="coursePolicyCategory" action="show" id="${coursePolicyInstance?.category?.id}">${coursePolicyInstance?.category?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${coursePolicyInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="coursePolicy.description.label" default="Description" /></span>

						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${coursePolicyInstance}" field="description"/></span>

				</li>
				</g:if>

				<g:if test="${coursePolicyInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="coursePolicy.name.label" default="Name" /></span>

						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${coursePolicyInstance}" field="name"/></span>

				</li>
				</g:if>

				<g:if test="${coursePolicyInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="coursePolicy.user.label" default="User" /></span>

						<span class="property-value" aria-labelledby="user-label"><g:link controller="imodUser" action="show" id="${coursePolicyInstance?.user?.id}">${coursePolicyInstance?.user?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${coursePolicyInstance?.id}" />
					<g:link class="edit" action="edit" id="${coursePolicyInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
