
<%@ page import="imodv6.Imod" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-imod" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-imod" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list imod">

				<g:if test="${imodInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="imod.name.label" default="Name" /></span>

						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${imodInstance}" field="name"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.creditHours}">
				<li class="fieldcontain">
					<span id="creditHours-label" class="property-label"><g:message code="imod.creditHours.label" default="Credit Hours" /></span>

						<span class="property-value" aria-labelledby="creditHours-label"><g:fieldValue bean="${imodInstance}" field="creditHours"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.numberOfSeats}">
				<li class="fieldcontain">
					<span id="numberOfSeats-label" class="property-label"><g:message code="imod.numberOfSeats.label" default="Number Of Seats" /></span>

						<span class="property-value" aria-labelledby="numberOfSeats-label"><g:fieldValue bean="${imodInstance}" field="numberOfSeats"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.coursePolicy}">
				<li class="fieldcontain">
					<span id="coursePolicy-label" class="property-label"><g:message code="imod.coursePolicy.label" default="Course Policy" /></span>

						<span class="property-value" aria-labelledby="coursePolicy-label"><g:link controller="coursePolicy" action="show" id="${imodInstance?.coursePolicy?.id}">${imodInstance?.coursePolicy?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.schedule}">
				<li class="fieldcontain">
					<span id="schedule-label" class="property-label"><g:message code="imod.schedule.label" default="Schedule" /></span>

						<span class="property-value" aria-labelledby="schedule-label"><g:link controller="schedule" action="show" id="${imodInstance?.schedule?.id}">${imodInstance?.schedule?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.scheduleDate}">
				<li class="fieldcontain">
					<span id="scheduleDate-label" class="property-label"><g:message code="imod.scheduleDate.label" default="Schedule Date" /></span>

						<span class="property-value" aria-labelledby="scheduleDate-label"><g:link controller="scheduleDate" action="show" id="${imodInstance?.scheduleDate?.id}">${imodInstance?.scheduleDate?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.piechart}">
				<li class="fieldcontain">
					<span id="piechart-label" class="property-label"><g:message code="imod.piechart.label" default="Piechart" /></span>

						<span class="property-value" aria-labelledby="piechart-label"><g:link controller="piechart" action="show" id="${imodInstance?.piechart?.id}">${imodInstance?.piechart?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.timeRatio}">
				<li class="fieldcontain">
					<span id="timeRatio-label" class="property-label"><g:message code="imod.timeRatio.label" default="Time Ratio" /></span>

						<span class="property-value" aria-labelledby="timeRatio-label"><g:fieldValue bean="${imodInstance}" field="timeRatio"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.overview}">
				<li class="fieldcontain">
					<span id="overview-label" class="property-label"><g:message code="imod.overview.label" default="Overview" /></span>

						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${imodInstance}" field="overview"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.courseLocation}">
				<li class="fieldcontain">
					<span id="courseLocation-label" class="property-label"><g:message code="imod.courseLocation.label" default="Course Location" /></span>

						<span class="property-value" aria-labelledby="courseLocation-label"><g:fieldValue bean="${imodInstance}" field="courseLocation"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.courseSemester}">
				<li class="fieldcontain">
					<span id="courseSemester-label" class="property-label"><g:message code="imod.courseSemester.label" default="Course Semester" /></span>

						<span class="property-value" aria-labelledby="courseSemester-label"><g:fieldValue bean="${imodInstance}" field="courseSemester"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.imodNumber}">
				<li class="fieldcontain">
					<span id="imodNumber-label" class="property-label"><g:message code="imod.imodNumber.label" default="Imod Number" /></span>

						<span class="property-value" aria-labelledby="imodNumber-label"><g:fieldValue bean="${imodInstance}" field="imodNumber"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.repeats}">
				<li class="fieldcontain">
					<span id="repeats-label" class="property-label"><g:message code="imod.repeats.label" default="Repeats" /></span>

						<span class="property-value" aria-labelledby="repeats-label"><g:link controller="scheduleRepeats" action="show" id="${imodInstance?.repeats?.id}">${imodInstance?.repeats?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.repeatsEvery}">
				<li class="fieldcontain">
					<span id="repeatsEvery-label" class="property-label"><g:message code="imod.repeatsEvery.label" default="Repeats Every" /></span>

						<span class="property-value" aria-labelledby="repeatsEvery-label"><g:link controller="scheduleRepeatsEvery" action="show" id="${imodInstance?.repeatsEvery?.id}">${imodInstance?.repeatsEvery?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.audience}">
				<li class="fieldcontain">
					<span id="audience-label" class="property-label"><g:message code="imod.audience.label" default="Audience" /></span>

						<g:each in="${imodInstance.audience}" var="a">
						<span class="property-value" aria-labelledby="audience-label"><g:link controller="audience" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.contents}">
				<li class="fieldcontain">
					<span id="contents-label" class="property-label"><g:message code="imod.contents.label" default="Contents" /></span>

						<g:each in="${imodInstance.contents}" var="c">
						<span class="property-value" aria-labelledby="contents-label"><g:link controller="content" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.courseComponents}">
				<li class="fieldcontain">
					<span id="courseComponents-label" class="property-label"><g:message code="imod.courseComponents.label" default="Course Components" /></span>

						<g:each in="${imodInstance.courseComponents}" var="c">
						<span class="property-value" aria-labelledby="courseComponents-label"><g:link controller="courseComponentCode" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.instructors}">
				<li class="fieldcontain">
					<span id="instructors-label" class="property-label"><g:message code="imod.instructors.label" default="Instructors" /></span>

						<g:each in="${imodInstance.instructors}" var="i">
						<span class="property-value" aria-labelledby="instructors-label"><g:link controller="instructor" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.learningObjectives}">
				<li class="fieldcontain">
					<span id="learningObjectives-label" class="property-label"><g:message code="imod.learningObjectives.label" default="Learning Objectives" /></span>

						<g:each in="${imodInstance.learningObjectives}" var="l">
						<span class="property-value" aria-labelledby="learningObjectives-label"><g:link controller="learningObjective" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="imod.owner.label" default="Owner" /></span>

						<span class="property-value" aria-labelledby="owner-label"><g:link controller="imodUser" action="show" id="${imodInstance?.owner?.id}">${imodInstance?.owner?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.sharedOwners}">
				<li class="fieldcontain">
					<span id="sharedOwners-label" class="property-label"><g:message code="imod.sharedOwners.label" default="Shared Owners" /></span>

						<g:each in="${imodInstance.sharedOwners}" var="s">
						<span class="property-value" aria-labelledby="sharedOwners-label"><g:link controller="imodUser" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${imodInstance?.subjectArea}">
				<li class="fieldcontain">
					<span id="subjectArea-label" class="property-label"><g:message code="imod.subjectArea.label" default="Subject Area" /></span>

						<span class="property-value" aria-labelledby="subjectArea-label"><g:fieldValue bean="${imodInstance}" field="subjectArea"/></span>

				</li>
				</g:if>

				<g:if test="${imodInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="imod.url.label" default="Url" /></span>

						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${imodInstance}" field="url"/></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${imodInstance?.id}" />
					<g:link class="edit" action="edit" id="${imodInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
