<%@ page import="imodv6.Instructor" %>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="instructor.firstName.label" default="First Name" />
	</label>
	<g:textField name="firstName" value="${instructorInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'middleInitial', 'error')} ">
	<label for="middleInitial">
		<g:message code="instructor.middleInitial.label" default="Middle Initial" />
	</label>
	<g:textField name="middleInitial" value="${instructorInstance?.middleInitial}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="instructor.lastName.label" default="Last Name" />
	</label>
	<g:textField name="lastName" value="${instructorInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="instructor.location.label" default="Location" />
	</label>
	<g:textField name="location" value="${instructorInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="instructor.email.label" default="Email" />
	</label>
	<g:textField name="email" value="${instructorInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'officeHours', 'error')} ">
	<label for="officeHours">
		<g:message code="instructor.officeHours.label" default="Office Hours" />
	</label>
	<g:textField name="officeHours" value="${instructorInstance?.officeHours}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'webPage', 'error')} ">
	<label for="webPage">
		<g:message code="instructor.webPage.label" default="Web Page" />
	</label>
	<g:textField name="webPage" value="${instructorInstance?.webPage}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'phoneNumber', 'error')} ">
	<label for="phoneNumber">
		<g:message code="instructor.phoneNumber.label" default="Phone Number" />
	</label>
	<g:textField name="phoneNumber" value="${instructorInstance?.phoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instructorInstance, field: 'phones', 'error')} ">
	<label for="phones">
		<g:message code="instructor.phones.label" default="Phones" />
	</label>
	<ul class="one-to-many">
		<g:each in="$ {instructorInstance?.phones?}" var="p">
			<li>
				<g:link controller="instructorPhone" action="show" id="${p.id}">
					${p?.encodeAsHTML()}
				</g:link>
			</li>
		</g:each>
		<li class="add">
			<g:link controller="instructorPhone" action="create" params="['instructor.id': instructorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'instructorPhone.label', default: 'InstructorPhone')])}</g:link>
		</li>
	</ul>
</div>

