
<%@ page import="imodv6.Instructor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instructor.label', default: 'Instructor')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<a href="#show-instructor" class="skip" tabindex="-1">
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
		<div id="show-instructor" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<ol class="property-list instructor">
				<g:if test="${instructorInstance?.middleInitial}">
					<li class="fieldcontain">
						<span id="middleInitial-label" class="property-label">
							<g:message code="instructor.middleInitial.label" default="Middle Initial" />
						</span>
						<span class="property-value" aria-labelledby="middleInitial-label">
							<g:fieldValue bean="${instructorInstance}" field="middleInitial"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.lastName}">
					<li class="fieldcontain">
						<span id="lastName-label" class="property-label">
							<g:message code="instructor.lastName.label" default="Last Name" />
						</span>
						<span class="property-value" aria-labelledby="lastName-label">
							<g:fieldValue bean="${instructorInstance}" field="lastName"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.location}">
					<li class="fieldcontain">
						<span id="location-label" class="property-label">
							<g:message code="instructor.location.label" default="Location" />
						</span>
						<span class="property-value" aria-labelledby="location-label">
							<g:fieldValue bean="${instructorInstance}" field="location"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.email}">
					<li class="fieldcontain">
						<span id="email-label" class="property-label">
							<g:message code="instructor.email.label" default="Email" />
						</span>
						<span class="property-value" aria-labelledby="email-label">
							<g:fieldValue bean="${instructorInstance}" field="email"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.officeHours}">
					<li class="fieldcontain">
						<span id="officeHours-label" class="property-label">
							<g:message code="instructor.officeHours.label" default="Office Hours" />
						</span>
						<span class="property-value" aria-labelledby="officeHours-label">
							<g:fieldValue bean="${instructorInstance}" field="officeHours"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.webPage}">
					<li class="fieldcontain">
						<span id="webPage-label" class="property-label">
							<g:message code="instructor.webPage.label" default="Web Page" />
						</span>
						<span class="property-value" aria-labelledby="webPage-label">
							<g:fieldValue bean="${instructorInstance}" field="webPage"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.phoneNumber}">
					<li class="fieldcontain">
						<span id="phoneNumber-label" class="property-label">
							<g:message code="instructor.phoneNumber.label" default="Phone Number" />
						</span>
						<span class="property-value" aria-labelledby="phoneNumber-label">
							<g:fieldValue bean="${instructorInstance}" field="phoneNumber"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.createdBy}">
					<li class="fieldcontain">
						<span id="createdBy-label" class="property-label">
							<g:message code="instructor.createdBy.label" default="Created By" />
						</span>
						<span class="property-value" aria-labelledby="createdBy-label">
							<g:link controller="imodUser" action="show" id="${instructorInstance?.createdBy?.id}">
								${instructorInstance?.createdBy?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.firstName}">
					<li class="fieldcontain">
						<span id="firstName-label" class="property-label">
							<g:message code="instructor.firstName.label" default="First Name" />
						</span>
						<span class="property-value" aria-labelledby="firstName-label">
							<g:fieldValue bean="${instructorInstance}" field="firstName"/>
						</span>
					</li>
				</g:if>

				<g:if test="${instructorInstance?.phones}">
					<li class="fieldcontain">
						<span id="phones-label" class="property-label">
							<g:message code="instructor.phones.label" default="Phones" />
						</span>
						<g:each in="${instructorInstance.phones}" var="p">
							<span class="property-value" aria-labelledby="phones-label">
								<g:link controller="instructorPhone" action="show" id="${p.id}">
									${p?.encodeAsHTML()}
								</g:link>
							</span>
						</g:each>
					</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${instructorInstance?.id}" />
					<g:link class="edit" action="edit" id="${instructorInstance?.id}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
