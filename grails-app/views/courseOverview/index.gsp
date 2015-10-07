<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            Course Overview
        </title>
        <meta name="layout" content="imod" />
        <g:external dir="css/source" file="topicModal.css" />
        <g:javascript src="source/courseOverview.js" defer="defer" />
    </head>
    <body>
        <div id="tabs-1">
            <div id="edit-imod" class="content scaffold-edit" role="main">
                <g:if test="${flash.message}">
                    <div class="message" role="status">
                        ${flash.message}
                    </div>
                </g:if>
                <g:hasErrors bean="${currentImod}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${currentImod}" var="error">
                            <li
                                <g:if test="${error in org.springframework.validation.FieldError}">
                                    data-field-id="${error.field}"
                                </g:if>
                            >
                                <g:message error="${error}"/>
                            </li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <g:form url="[action:'update',controller:'imod']" method="post" class="courseoverview">
                    <g:hiddenField name="id" value="${currentImod?.id}"/>
                    <g:hiddenField name="version" value="${currentImod?.version}"/>
                    <fieldset id="courseoverview-form" class="form">
                        <table class="inner-table">
                            <tr height="50px">
                                <td>
                                </td>
                                <td>

                                    <fieldset class="buttons">
                                        <span class="topicButtonGradient">
                                    	<a href="../syllabus/${currentImod?.id}" target="_blank">
                                            Show Syllabus
                                        </a>
                                        </span>
                                        <span class="topicButtonGradient">
                                        <g:actionSubmit class="save overview-save show-hover-new" action="update" title="${ message( code:'imod.courseOverview.save' ) }" value="${message(code: 'Save', default: 'Save')}"/>
                                        </span>
                                        <span class="topicButtonGradient">
                                        <g:actionSubmit class="delete show-hover-new" action="delete" title="${ message( code:'imod.courseOverview.delete' ) }" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                                        </span>
                                    </fieldset>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="course-overview-form">
                                        <div class="form-title">
                                            <span class="title-text">
                                                Course Details
                                            </span>
                                        </div>
                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'name', 'error')}">
                                            <label for="name">
                                                <g:message code="imod.name.label" default="Course Title" />
                                                <span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                            <g:textField name="name" id = "courseName" placeholder ="New Imods" required="" value="${currentImod?.name}" title="${ message( code:'imod.courseOverview.courseTitle' ) }"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'imodNumber', 'error')} ">
                                            <label for="imodNumber">
                                                <g:message code="imod.imodNumber.label" default="Course Number" />
                                                <span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                            <g:textField name="imodNumber" placeholder ="CST 100" value="${currentImod?.imodNumber}" required="" id="imod-number" title="${ message( code:'imod.courseOverview.courseNumber' ) }"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'url', 'error')} ">
                                            <label for="url">
                                                <g:message code="imod.url.label" default="Course URL" />

                                            </label>
                                            <g:textField name="url" id = "courseUrl" placeholder ="example.com" value="${currentImod?.url}" title="${ message( code:'imod.courseOverview.courseURL' ) }"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'courseLocation', 'error')} ">
                                            <label for="courseLocation">
                                                <g:message code="imod.courseLocation.label" default="Classroom Location" />
                                            </label>
                                            <g:textField name="courseLocation" value="${currentImod?.courseLocation}" id="course-location" title="${ message( code:'imod.courseOverview.classLocation' ) }"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'courseSemester', 'error')} ">
                                            <label for="courseSemester">
                                                <g:message code="imod.courseSemester.label" default="Semester" />
                                            </label>
                                            <g:textField name="courseSemester" value="${currentImod?.courseSemester}" id="course-semester" title="${ message( code:'imod.courseOverview.courseSemester' ) }"/>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="course-overview-form">
                                        <div class="form-title">
                                            <span class="title-text">
                                                Schedule
                                            </span>
                                        </div>
                                        <div class="dateFields fieldcontain ${hasErrors(bean: currentImod, field: 'startDate', 'error')} required">
                                            <label for="startDate">
                                                <g:message code="imod.schedule.startDate.label" default="Start Date" />
                                                <span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                            <g:datePicker name="schedule.startDate" id="schedule-start-date" default="none" noSelection="['':'']" precision="day"  value="${currentImod?.schedule?.startDate}" years="${2014..2100}" class="show-hover-new"  title="${Help.toolTip("OVERVIEW", "Schedule start Date")}" />
                                        </div>

                                        <div class="dateFields fieldcontain ${hasErrors(bean: currentImod, field: 'endDate', 'error')} required">
                                            <label for="endDate">
                                                <g:message code="imod.schedule.endDate.label" default="End Date" />
                                                <span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                            <g:datePicker name="schedule.endDate" id="schedule-end-date" default="none" noSelection="['':'']"precision="day" value="${currentImod?.schedule?.endDate}" years="${2014..2100}" title="${Help.toolTip("OVERVIEW", "Schedule end Date")}" class="show-hover-new" />
                                        </div>

                                        <div class="timeFields fieldcontain ${hasErrors(bean: currentImod, field: 'startTime', 'error')} ">
                                            <label for="startTime">
                                                <g:message code="imod.schedule.startTime.label" default="Start Time" />
												<span class="required-indicator">
                                                    *
                                                </span>
                                            </label>

                                        <joda:timePicker name="schedule.startTime" id="schedule-start-time" default="none" noSelection="['':'']" value="${currentImod?.schedule?.startTime}"  />
                                        </div>
                                        <div class="timeFields fieldcontain ${hasErrors(bean: currentImod, field: 'endTime', 'error')} ">
                                            <label for="endTime">
                                                <g:message code="imod.schedule.endTime.label" default="End Time" />
												<span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                        <joda:timePicker name="schedule.endTime" id="schedule-end-time" default="none" noSelection="['':'']" value="${currentImod?.schedule?.endTime}" />
                                        </div>
                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'repeats', 'error')} ">
                                            <label for="repeats">
                                                <g:message code="imod.repeats.label" default="Repeats" />
                                            </label>
                                            <!-- FIXME all model queries should be run in controller -->
                                            <g:select id="repeats" name="schedule.repeats.id" from="${imod.ScheduleRepeats.list()}" optionKey="id" value="${currentImod?.schedule?.repeats?.id}" class="custom-dropdown many-to-one" title="${ message( code:'imod.courseOverview.scheduleRepeats' ) }" />
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'repeatsEvery', 'error')} ">
                                            <label for="repeatsEvery">
                                                <g:message code="imod.repeatsEvery.label" default="Repeats Every" />
                                            </label>
                                            <!-- FIXME all model queries should be run in controller -->
                                            <g:select id="repeats-every" name="schedule.repeatsEvery.id" from="${imod.ScheduleRepeatsEvery.list()}" optionKey="id" value="${currentImod?.schedule?.repeatsEvery?.id}" noSelection="${['null':'Nothing Selected']}" class="custom-dropdown many-to-one" title="${ message( code:'imod.courseOverview.scheduleRepeatsEvery' ) }"/>
                                             <span id= "duration"></span>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'schedule.scheduleWeekDays', 'error')} style="padding:0" ">
                                        <label for="scheduleWeekDays">
                                                <g:message code="imod.scheduleWeekDays.label" default="Repeats On" />
                                            </label>
                                        <g:each in="${imod.ScheduleWeekDays.list()}" var="scheduleWeekDays" status="i">
                                            <!-- FIXME move complex logic to controller -->
                                            <!-- FIXME all model queries should be run in controller -->
                                            <g:checkBox name="scheduleWeekDays_${scheduleWeekDays.id}" id="schedule-week-days-${scheduleWeekDays.id}" value="${scheduleWeekDays.description == currentImod?.schedule?.scheduleWeekDays?.find{p -> p.id == scheduleWeekDays?.id}.toString()}" title="${ message( code:'imod.courseOverview.scheduleRepeatsOn' ) }"/>
                                            <label for="weekdays">
                                                ${scheduleWeekDays.description}
                                            </label>
                                        </g:each>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="course-overview-form">
                                        <div class="form-title">
                                            <span class="title-text">
                                                Course Description
                                            </span>
                                        </div>
                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'overview', 'error')} ">
                                            <label for="overview">
                                                <g:message code="imod.overview.label" default="Overview" />
                                            </label>
                                            <g:textArea name="overview" value="${currentImod?.overview}" rows="4" cols="40" title="${ message( code:'imod.courseOverview.overview')}"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'subjectArea', 'error')} ">
                                            <label for="subject-area">
                                                <g:message code="imod.subjectArea.label" default="Subject Area" />
                                                <span class="required-indicator">
                                                    *
                                                </span>
                                            </label>
                                            <g:textField name="subjectArea" id="subject-area" placeholder="sample" required="" value="${currentImod?.subjectArea}" title="${ message( code:'imod.courseOverview.subjectArea')}"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'audience', 'error')} ">
                                            <label for="audience">
                                                <g:message code="imod.audience.label" default="Audience" />
                                            </label>
                                            <g:select name="audience" from="${imod.Audience.list()}" noSelection="${['none':'No Audience'] }" multiple="multiple" optionKey="id" size="5" value="${currentImod?.audience*.id}" class="many-to-many" />
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'creditHours', 'error')} ">
                                            <label for="credit-hours">
                                                <g:message code="imod.creditHours.label" default="Credit Hours" />
                                            </label>
                                            <g:field name="creditHours" id="credit-hours" type="number" min="0" max="7" value="${currentImod.creditHours}" title="${ message( code:'imod.courseOverview.creditHours')}"/>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'timeRatio', 'error')} ">
                                            <label for="time-ratio">
                                                <g:message code="imod.timeRatio.label" default="Time Ratio" />
                                            </label>
                                            <g:textField name="timeRatio" id="time-ratio" value="${currentImod?.timeRatio}" title="${ message( code:'imod.courseOverview.timeRatio')}"/>
                                            <span class="help-block">
                                                "Time spent in and out of class. eg: 1:2"
                                            </span>
                                        </div>

                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'numberOfSeats', 'error')} ">
                                            <label for="number-of-seats">
                                                <g:message code="imod.numberOfSeats.label" default="Number Of Seats" />
                                            </label>
                                            <g:field name="numberOfSeats" id="number-of-seats" type="number" min="0" max="1000" value="${currentImod.numberOfSeats}" title="${ message( code:'imod.courseOverview.numberOfSeats')}"/>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="policy-field course-overview-form" id="policy">
                                        <div class="form-title">
                                            <span class="title-text">
                                                Course Policy
                                            </span>
                                        </div>
                                        <div id="accordion">
                                            <h3>
                                                Grading Procedure
                                            </h3>
                                            <div>
                                                <div>
                                                    <g:radio name="gradingProcedureRadio" class="grading-radio" value="Competency-Based" checked="${currentImod?.gradingProcedure == 'Competency-Based' ? true : false}" title="${ message( code:'imod.courseOverview.gradingProcedure')}"/>
                                                    Competency-Based
                                                </div>

                                                <div>
                                                    <g:radio name="gradingProcedureRadio" class="grading-radio" value="Standardized Grades"  checked="${currentImod?.gradingProcedure != 'Custom' && currentImod?.gradingProcedure != 'Competency Based' ?true:false}" title="${ message( code:'imod.courseOverview.gradingProcedure')}"/>
                                                    Standardized
                                                </div>

                                                <div>
                                                    <g:radio name="gradingProcedureRadio"  class="grading-radio" value="Custom" checked="${currentImod?.gradingProcedure == 'Custom'?true:false}" title="${ message( code:'imod.courseOverview.gradingProcedure')}"/>
                                                    Custom
                                                    <br>
                                                    <g:textArea name="gradingProcedure" id="grading-procedure-text" value="${currentImod?.gradingProcedure != 'Standardized Grades' && currentImod?.gradingProcedure != 'Competency Based' ? currentImod?.gradingProcedure:''}" rows="3" cols="30" title="${ message( code:'imod.courseOverview.gradingProcedure')}"/>
                                                </div>
                                            </div>
                                            <h3>
                                                Attendance and Tardiness
                                            </h3>
                                            <div>
												<input type="button" class="restore-default" name="attendanceCheck" id="attendance-box" class="attendance-check" value="Restore Default"/>
												<g:textArea name="attendance" value="${currentImod?.attendance}" rows="3" cols="30" title="${ message( code:'imod.courseOverview.attendance')}" />
                                            </div>
                                            <h3>
                                                Class Participation
                                            </h3>
                                            <div>
											<input type="button" class="restore-default" name="participationCheck" id="participation-box" class="attendance-check" value="Restore Default"/>

												<g:textArea name="classParticipation" id="class-participation" value="${currentImod?.classParticipation}" rows="2" cols="30" title="${ message( code:'imod.courseOverview.classParticipation')}" />
                                            </div>
                                            <h3>
                                                Professional Conduct
                                            </h3>
                                            <div>
												<input type="button" class="restore-default" name="professionalConductCheck" id="professional-conduct-box" class="attendance-check" value="Restore Default"/>
                                                <g:textArea name="professionalConduct" id="professional-conduct" value="${currentImod?.professionalConduct}" rows="2" cols="30" title="${ message( code:'imod.courseOverview.professionalConduct')}" />
                                            </div>
                                            <h3>
                                                Missed Exams/Make-up Exams
                                            </h3>
                                            <div>
												<input type="button" class="restore-default" name="missedExamsCheck" id="missed-exams-box" class="attendance-check" value="Restore Default"/>
                                                <g:textArea name="missedExams" id="missed-exams" value="${currentImod?.missedExams}" rows="2" cols="30" title="${ message( code:'imod.courseOverview.missedExams')}" />
                                            </div>
                                            <h3>
                                                Missed Assignments
                                            </h3>
                                            <div>
												<input type="button" class="restore-default" name="missedAssignmentsCheck" id="missed-assignments-box" class="attendance-check" value="Restore Default"/>
                                                <g:textArea name="missedAssignments" id="missed-assignments" value="${currentImod?.missedAssignments}" rows="2" cols="30" title="${ message( code:'imod.courseOverview.missedAssignments')}" />
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </g:form>

                <g:form controller="imod" method="post" class="instructor-form">
                    <g:hiddenField name="id" value="${currentImod?.id}"/>
                    <g:hiddenField name="version" value="${currentImod?.version}"/>
                    <fieldset class="form">
                        <table class="inner-table">
                            <tr>
                                <td colspan="2">
                                    <div class="course-overview-form">
                                        <div class="form-title">
                                            <span class="title-text">
                                                Instructors
                                            </span>
                                        </div>
                                        <div class="fieldcontain ${hasErrors(bean: currentImod, field: 'instructors', 'error')} " style="padding:0 15px;">
                                            <div id="topicDialog" class="custom-instructor">
                                                <input type="hidden" name="imodID" value="221" id="imodID">
                                                <fieldset class="buttons topicButtonField">
                                                    <span class="topicButtonGradient">
                                                        <input type="submit" name="_action_add" value="Add Instructor" class="add show-hover-new topicButton" title="${ message( code:'imod.courseOverview.addInstructors')}">
                                                    </span>
                                                    <g:if test="${currentImod?.instructors != null && !currentImod?.instructors.isEmpty()}">
                                                        <span class="remove-instructor topicButtonGradient">
                                                            <input type="submit" name="_action_remove" value="Remove Instructor" class="remove show-hover-new topicButton delete-instructor" id="removeTopic" title="${ message( code:'imod.courseOverview.removeInstructors')}">
                                                        </span>
                                                    </g:if>
                                                    <span id="errorMessage"></span>
                                                </fieldset>
                                                <table id="topicList" class="instructor-list">
                                                    <thead>
                                                        <tr>
                                                            <td class="saveIcon saveIcon-parent">
                                                                <i class="fa fa-square-o"></i>
                                                            </td>
                                                            <g:sortableColumn property="firstName" title="${message(code: 'imod.instructor.firstName.label', default: 'First Name')}" class="show-hover-new"/>
                                                            <g:sortableColumn property="lastName" title="${message(code: 'imod.instructor.lastName.label', default: 'Last Name')}" class="show-hover-new"/>
                                                            <g:sortableColumn property="email" title="${message(code: 'imod.instructor.email.label', default: 'Email')}" class="show-hover-new" />
                                                            <g:sortableColumn property="officeHours" title="${message(code: 'imod.instructor.officeHours.label', default: 'Office Hours')}" class="show-hover-new" />
                                                            <g:sortableColumn property="webPage" title="${message(code: 'imod.instructor.webPage.label', default: 'Web Page')}" class="show-hover-new" />
                                                            <g:sortableColumn property="Role" title="${message(code: 'imod.instructor.Role.label', default: 'Role')}" class="show-hover-new" />
                                                            <g:sortableColumn property="location" title="${message(code: 'imod.instructor.location.label', default: 'Office Location')}" class="show-hover-new" />
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <g:each in="${currentImod?.instructors?}" var="instructor">
                                                            <tr id="instructor${instructor.id}" data-id="${instructor.id}" class="topicListRow">
                                                                <td class="saveIcon">
                                                                    <i class=" fa fa-square-o"></i>
                                                                </td>
                                                                <td>
                                                                    <g:textField name="firstName[]" value="${instructor.firstName}" class="first_name" />
                                                                </td>
                                                                <td>
                                                                    <g:textField name="lastName[]" value="${instructor.lastName}" class="last_name"/>
                                                                </td>
                                                                <td>
                                                                    <g:textField name="email[]" value="${instructor.email}" class="email"/>
                                                                </td>
                                                                <td>
                                                                    <g:textField name="officeHours[]" value="${instructor.officeHours}" class="office_hours"/>
                                                                </td>
                                                                <td>
                                                                    <g:textField name="webPage[]" value="${instructor.webPage}" class="web_page"/>
                                                                </td>
                                                                <td>
                                                                    <select name="role[]" id="role" class="custom-dropdown role">
                                                                        <option value="">Select Role</option>

                                                                        <g:if test="${instructor.role == 'Assistant Professor'}">
                                                                            <option selected>Assistant Professor</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Assistant Professor</option>
                                                                        </g:else>

                                                                        <g:if test="${instructor.role == 'Associate Professor'}">
                                                                            <option selected>Associate Professor</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Associate Professor</option>
                                                                        </g:else>

                                                                        <g:if test="${instructor.role == 'Professor'}">
                                                                            <option selected>Professor</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Professor</option>
                                                                        </g:else>

                                                                        <g:if test="${instructor.role == 'Teaching Assistant'}">
                                                                            <option selected>Teaching Assistant</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Teaching Assistant</option>
                                                                        </g:else>

                                                                        <g:if test="${instructor.role == 'Course Assistant'}">
                                                                            <option selected>Course Assistant</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Course Assistant</option>
                                                                        </g:else>

                                                                        <g:if test="${instructor.role == 'Grader'}">
                                                                            <option selected>Grader</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option>Grader</option>
                                                                        </g:else>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <g:textField name="location[]" value="${instructor.location}" class="location"/>
                                                                </td>
                                                            </tr>
                                                        </g:each>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </g:form>
            </div>
        </div>

        <div id="please-select-instructor" title="Please Select Instructor">
            <p>
                Please select an instructor to delete.
            </p>
        </div>

        <div id="confirm-instructor-remove" title="Confirm">
            <p>
                Are you sure you want to delete instructors?
            </p>
        </div>

        <div id="instructor-removed-success" title="Success">
            <p>
                Instructors removed successfully.
            </p>
        </div>
    </body>
</html>
