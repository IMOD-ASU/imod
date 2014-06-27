
<%@ page import="imodv6.ActionWord" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actionWord.label', default: 'ActionWord')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<a href="#show-actionWord" class="skip" tabindex="-1">
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
					<g:link class="list" action="list">
						<g:message code="default.list.label" args="[entityName]" />
					</g:link>
				</li>
				<li>
					<g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link>
				</li>
			</ul>
		</div>
		<div id="show-actionWord" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list actionWord">
				<g:if test="${actionWordInstance?.actionWord}">
					<li class="fieldcontain">
						<span id="actionWord-label" class="property-label">
							<g:message code="actionWord.actionWord.label" default="Action Word" />
						</span>
						<span class="property-value" aria-labelledby="actionWord-label">
							<g:fieldValue bean="${actionWordInstance}" field="actionWord"/>
						</span>
					</li>
				</g:if>

				<g:if test="${actionWordInstance?.category}">
					<li class="fieldcontain">
						<span id="category-label" class="property-label">
							<g:message code="actionWord.category.label" default="Category" />
						</span>

						<span class="property-value" aria-labelledby="category-label">
							<g:link controller="domainCategory" action="show" id="${actionWordInstance?.category?.id}">
								${actionWordInstance?.category?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${actionWordInstance?.domain}">
					<li class="fieldcontain">
						<span id="domain-label" class="property-label">
							<g:message code="actionWord.domain.label" default="Domain" />
						</span>

						<span class="property-value" aria-labelledby="domain-label">
							<g:link controller="learningDomain" action="show" id="${actionWordInstance?.domain?.id}">
								${actionWordInstance?.domain?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${actionWordInstance?.user}">
					<li class="fieldcontain">
						<span id="user-label" class="property-label">
							<g:message code="actionWord.user.label" default="User" />
						</span>

						<span class="property-value" aria-labelledby="user-label">
							<g:link controller="imodUser" action="show" id="${actionWordInstance?.user?.id}">
								${actionWordInstance?.user?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${actionWordInstance?.visibility}">
					<li class="fieldcontain">
						<span id="visibility-label" class="property-label">
							<g:message code="actionWord.visibility.label" default="Visibility" />
						</span>

						<span class="property-value" aria-labelledby="visibility-label">
							<g:fieldValue bean="${actionWordInstance}" field="visibility"/>
						</span>
					</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${actionWordInstance?.id}" />
					<g:link class="edit" action="edit" id="${actionWordInstance?.id}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
