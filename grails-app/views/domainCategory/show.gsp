
<%@ page import="imodv6.DomainCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'domainCategory.label', default: 'DomainCategory')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<a href="#show-domainCategory" class="skip" tabindex="-1">
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
		<div id="show-domainCategory" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<ol class="property-list domainCategory">
				<g:if test="${domainCategoryInstance?.domain}">
					<li class="fieldcontain">
						<span id="domain-label" class="property-label">
							<g:message code="domainCategory.domain.label" default="Domain" />
						</span>
						<span class="property-value" aria-labelledby="domain-label">
							<g:link controller="learningDomain" action="show" id="${domainCategoryInstance?.domain?.id}">
								${domainCategoryInstance?.domain?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${domainCategoryInstance?.name}">
					<li class="fieldcontain">
						<span id="name-label" class="property-label">
							<g:message code="domainCategory.name.label" default="Name" />
						</span>
						<span class="property-value" aria-labelledby="name-label">
							<g:fieldValue bean="${domainCategoryInstance}" field="name"/>
						</span>
					</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${domainCategoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${domainCategoryInstance?.id}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
