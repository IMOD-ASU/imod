<%@ page import="imodv6.Imod" %>



<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="imod.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${imodInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'creditHours', 'error')} ">
	<label for="creditHours">
		<g:message code="imod.creditHours.label" default="Credit Hours" />

	</label>
	<g:field name="creditHours" type="number" value="${imodInstance.creditHours}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'numberOfSeats', 'error')} ">
	<label for="numberOfSeats">
		<g:message code="imod.numberOfSeats.label" default="Number Of Seats" />

	</label>
	<g:field name="numberOfSeats" type="number" value="${imodInstance.numberOfSeats}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'coursePolicy', 'error')} ">
	<label for="coursePolicy">
		<g:message code="imod.coursePolicy.label" default="Course Policy" />

	</label>
	<g:select id="coursePolicy" name="coursePolicy.id" from="${imodv6.CoursePolicy.list()}" optionKey="id" value="${imodInstance?.coursePolicy?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'schedule', 'error')} ">
	<label for="schedule">
		<g:message code="imod.schedule.label" default="Schedule" />

	</label>
	<g:select id="schedule" name="schedule.id" from="${imodv6.Schedule.list()}" optionKey="id" value="${imodInstance?.schedule?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'scheduleDate', 'error')} ">
	<label for="scheduleDate">
		<g:message code="imod.scheduleDate.label" default="Schedule Date" />

	</label>
	<g:select id="scheduleDate" name="scheduleDate.id" from="${imodv6.ScheduleDate.list()}" optionKey="id" value="${imodInstance?.scheduleDate?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'piechart', 'error')} ">
	<label for="piechart">
		<g:message code="imod.piechart.label" default="Piechart" />

	</label>
	<g:select id="piechart" name="piechart.id" from="${imodv6.Piechart.list()}" optionKey="id" value="${imodInstance?.piechart?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'timeRatio', 'error')} ">
	<label for="timeRatio">
		<g:message code="imod.timeRatio.label" default="Time Ratio" />

	</label>
	<g:textField name="timeRatio" value="${imodInstance?.timeRatio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'overview', 'error')} ">
	<label for="overview">
		<g:message code="imod.overview.label" default="Overview" />

	</label>
	<g:textField name="overview" value="${imodInstance?.overview}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'courseLocation', 'error')} ">
	<label for="courseLocation">
		<g:message code="imod.courseLocation.label" default="Course Location" />

	</label>
	<g:textField name="courseLocation" value="${imodInstance?.courseLocation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'courseSemester', 'error')} ">
	<label for="courseSemester">
		<g:message code="imod.courseSemester.label" default="Course Semester" />

	</label>
	<g:textField name="courseSemester" value="${imodInstance?.courseSemester}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'imodNumber', 'error')} ">
	<label for="imodNumber">
		<g:message code="imod.imodNumber.label" default="Imod Number" />

	</label>
	<g:textField name="imodNumber" value="${imodInstance?.imodNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'repeats', 'error')} ">
	<label for="repeats">
		<g:message code="imod.repeats.label" default="Repeats" />

	</label>
	<g:select id="repeats" name="repeats.id" from="${imodv6.ScheduleRepeats.list()}" optionKey="id" value="${imodInstance?.repeats?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'repeatsEvery', 'error')} ">
	<label for="repeatsEvery">
		<g:message code="imod.repeatsEvery.label" default="Repeats Every" />

	</label>
	<g:select id="repeatsEvery" name="repeatsEvery.id" from="${imodv6.ScheduleRepeatsEvery.list()}" optionKey="id" value="${imodInstance?.repeatsEvery?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'audience', 'error')} ">
	<label for="audience">
		<g:message code="imod.audience.label" default="Audience" />

	</label>
	<g:select name="audience" from="${imodv6.Audience.list()}" multiple="multiple" optionKey="id" size="5" value="${imodInstance?.audience*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'contents', 'error')} ">
	<label for="contents">
		<g:message code="imod.contents.label" default="Contents" />

	</label>
	<g:select name="contents" from="${imodv6.Content.list()}" multiple="multiple" optionKey="id" size="5" value="${imodInstance?.contents*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'courseComponents', 'error')} ">
	<label for="courseComponents">
		<g:message code="imod.courseComponents.label" default="Course Components" />

	</label>
	<g:select name="courseComponents" from="${imodv6.CourseComponentCode.list()}" multiple="multiple" optionKey="id" size="5" value="${imodInstance?.courseComponents*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'instructors', 'error')} ">
	<label for="instructors">
		<g:message code="imod.instructors.label" default="Instructors" />

	</label>
	<g:select name="instructors" from="${imodv6.Instructor.list()}" multiple="multiple" optionKey="id" size="5" value="${imodInstance?.instructors*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'learningObjectives', 'error')} ">
	<label for="learningObjectives">
		<g:message code="imod.learningObjectives.label" default="Learning Objectives" />

	</label>

<ul class="one-to-many">
<g:each in="${imodInstance?.learningObjectives?}" var="l">
    <li><g:link controller="learningObjective" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="learningObjective" action="create" params="['imod.id': imodInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'learningObjective.label', default: 'LearningObjective')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="imod.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${imodv6.ImodUser.list()}" optionKey="id" required="" value="${imodInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'sharedOwners', 'error')} ">
	<label for="sharedOwners">
		<g:message code="imod.sharedOwners.label" default="Shared Owners" />

	</label>

</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'subjectArea', 'error')} ">
	<label for="subjectArea">
		<g:message code="imod.subjectArea.label" default="Subject Area" />

	</label>
	<g:textField name="subjectArea" value="${imodInstance?.subjectArea}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="imod.url.label" default="Url" />

	</label>
	<g:textField name="url" value="${imodInstance?.url}"/>
</div>

