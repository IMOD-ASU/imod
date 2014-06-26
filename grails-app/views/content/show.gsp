
<%@ page import="imodv6.Content" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'content.label', default: 'Content')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-content" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list content">

				<g:if test="${contentInstance?.chapter}">
				<li class="fieldcontain">
					<span id="chapter-label" class="property-label"><g:message code="content.chapter.label" default="Chapter" /></span>

						<span class="property-value" aria-labelledby="chapter-label"><g:link controller="contentChapter" action="show" id="${contentInstance?.chapter?.id}">${contentInstance?.chapter?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${contentInstance?.knowledgeDomainCode}">
				<li class="fieldcontain">
					<span id="learningObjectiveType-label" class="property-label"><g:message code="content.learningObjectiveType.label" default="Learning Objective Type" /></span>

						<span class="property-value" aria-labelledby="learningObjectiveType-label"><g:fieldValue bean="${contentInstance}" field="knowledgeDomainCode"/></span>

				</li>
				</g:if>

				<g:if test="${contentInstance?.priorityCode}">
				<li class="fieldcontain">
					<span id="priorityCodeType-label" class="property-label"><g:message code="content.priorityCodeType.label" default="Priority Code Type" /></span>

						<span class="property-value" aria-labelledby="priorityCodeType-label"><g:fieldValue bean="${contentInstance}" field="priorityCode"/></span>

				</li>
				</g:if>

				<g:if test="${contentInstance?.resources}">
				<li class="fieldcontain">
					<span id="resources-label" class="property-label"><g:message code="content.resources.label" default="Resources" /></span>

						<g:each in="${contentInstance.resources}" var="r">
						<span class="property-value" aria-labelledby="resources-label"><g:link controller="contentResource" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${contentInstance?.topicTitle}">
				<li class="fieldcontain">
					<span id="topicTitle-label" class="property-label"><g:message code="content.topicTitle.label" default="Topic Title" /></span>

						<span class="property-value" aria-labelledby="topicTitle-label"><g:fieldValue bean="${contentInstance}" field="topicTitle"/></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contentInstance?.id}" />
					<g:link class="edit" action="edit" id="${contentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
