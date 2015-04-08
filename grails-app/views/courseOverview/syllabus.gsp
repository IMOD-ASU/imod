<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@ page import="imod.Content"%>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            Syllabus
        </title>
    </head>
    <body>

        <div class="course-details">
            <div class="form-title">
                <h3>Course Details</h3>
            </div>
            <div class="fieldcontain">
                <label for="name">
                    <g:message code="imod.name.label" default="Course Title" />: 
                </label>
                <strong>${currentImod?.name}</strong>
            </div>
            <div class="fieldcontain">
                <label for="imodNumber">
                    <g:message code="imod.imodNumber.label" default="Course Number" />: 
                </label>
                <strong>${currentImod?.imodNumber}</strong>
            </div>
            <div class="fieldcontain">
                <label for="url">
                    <g:message code="imod.url.label" default="Course URL" />: 
                </label>
                <strong>${currentImod?.url}</strong>
            </div>
            <div class="fieldcontain">
                <label for="courseLocation">
                    <g:message code="imod.courseLocation.label" default="Classroom Location" />: 
                </label>
                <strong>${currentImod?.courseLocation}</strong>
            </div>
            <div class="fieldcontain">
                <label for="courseSemester">
                    <g:message code="imod.courseSemester.label" default="Semester" />: 
                </label>
                <strong>${currentImod?.courseSemester}</strong>
            </div>
        </div>%{-- course details --}%

        <div class="schedule">
            <div class="form-title">
                <h3>Schedule</h3>
            </div>
            <div class="fieldcontain">
                <label>Start Date:</label>
                <strong><g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.startDate}"/></strong>
            </div>
            <div class="fieldcontain">
                <label>End Date:</label>
                <strong><g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.endDate}"/></strong>
            </div>
            <div class="fieldcontain">
                <label>Start Time:</label>
                <strong><joda:format pattern="HH:mm" value="${currentImod?.schedule?.startTime}"/></strong>
            </div>
            <div class="fieldcontain">
                <label>End Time:</label>
                <strong><joda:format pattern="HH:mm" value="${currentImod?.schedule?.endTime}"/></strong>
            </div>
            <div class="fieldcontain">
                <label for="repeats">
                    Repeats
                </label>
                <strong>${currentImod?.schedule?.repeats?.description}</strong>
            </div>

            <g:if test="${currentImod?.schedule?.repeats?.description == "Weekly" || currentImod?.schedule?.repeats?.description == "Daily"}">
     
                <div class="fieldcontain">
                    <label>
                        Repeats Every
                    </label>
                    <strong>${currentImod?.schedule?.repeatsEvery?.description} days</strong>
                </div>

            </g:if>

            <g:if test="${currentImod?.schedule?.repeats?.description == "Weekly" || currentImod?.schedule?.repeats?.description == "Daily"}">
                
                <div class="fieldcontain">
                    <label for="scheduleWeekDays">
                        Repeats On
                    </label>
                    <g:each in="${imod.ScheduleWeekDays.list()}" var="scheduleWeekDays" status="i">
                        
                        <g:checkBox name="scheduleWeekDays_${scheduleWeekDays.id}" id="schedule-week-days-${scheduleWeekDays.id}" value="${scheduleWeekDays.description == currentImod?.schedule?.scheduleWeekDays?.find{p -> p.id == scheduleWeekDays?.id}.toString()}" />
                        <label for="weekdays">
                            ${scheduleWeekDays.description}
                        </label>
                    </g:each>
                </div>
            </g:if>
        </div>%{-- schedule --}%

        <div class="course-description">
            <div class="form-title">
                <h3>Course Description</h3>
            </div>
            <div class="fieldcontain">
                <label>Overview:</label>
                <strong>${currentImod?.overview}</strong>
            </div>
            <div class="fieldcontain">
                <label>Subject Area:</label>
                <strong>${currentImod?.subjectArea}</strong>
            </div>
            <div class="fieldcontain">
                <label>Audience:</label>
                <strong>
                    <g:each in="${currentImod?.audience*.description}" var="it" status="i">
                        ${it.encodeAsHTML()}

                        <g:if test="${i != currentImod?.audience*.description.size() - 1}">
                            ,
                        </g:if>
                    </g:each>
                    
                </strong>
            </div>
            <div class="fieldcontain">
                <label>Credit Hours:</label>
                <strong>${currentImod?.creditHours}</strong>
            </div>
            <div class="fieldcontain">
                <label>Time Ratio:</label>
                <strong>${currentImod?.timeRatio}</strong>
            </div>
            <div class="fieldcontain">
                <label>Number of Seats:</label>
                <strong>${currentImod?.numberOfSeats}</strong>
            </div>
        </div>%{-- course description --}%

        <div class="course-policy">
            <div class="form-title">
                <h3>Course Policy</h3>
            </div>
            <div class="fieldcontain">
                <label><strong>Grading procedure:</strong></label>
                <p>${currentImod?.gradingProcedure}</p>
            </div>
            <div class="fieldcontain">
                <label><strong>Attendance and tardiness:</strong></label>
                <p>${currentImod?.attendance}</p>
            </div>
            <div class="fieldcontain">
                <label><strong>Class participation:</strong></label>
                <p>${currentImod?.classParticipation}</p>
            </div>
            <div class="fieldcontain">
                <label><strong>Professional Conduct:</strong></label>
                <p>${currentImod?.professionalConduct}</p>
            </div>
            <div class="fieldcontain">
                <label><strong>Missed exams/Make-up exams:</strong></label>
                <p>${currentImod?.missedExams}</p>
            </div>
            <div class="fieldcontain">
                <label><strong>Missed Assignments:</strong></label>
                <p>${currentImod?.missedAssignments}</p>
            </div>
        </div>%{-- course policy --}%

        <div class="instructors">

            <div class="form-title">
                <h3>Instructor</h3>
            </div>

            <ul>
            <g:each in="${currentImod?.instructors}" var="instructor">
                
                <li>
                    <strong>${instructor.firstName} ${instructor.lastName}</strong><br>
                    ${instructor.role}<br>
                    ${instructor.email}<br>                    
                    <g:if test="${instructor.officeHours != null && !instructor.officeHours.isEmpty()}">
                        Office Hours: ${instructor.officeHours}<br>
                    </g:if>
                    <g:if test="${instructor.webPage != null && !instructor.webPage.isEmpty()}">
                        Website: ${instructor.webPage}<br>
                    </g:if>
                    <g:if test="${instructor.location != null && !instructor.location.isEmpty()}">
                        Location: ${instructor.location}<br>
                    </g:if>
                </li>

            </g:each>
            </ul>

        </div>%{-- instructors --}%


        <div class="learning-objectives">

            <div class="form-title">
                <h3>Learning Objectives</h3>
            </div>

            <ul class="learning-objective list-wrapper">
                <g:each var="learningObjective" in="${learningObjectives}">
                    <li class="learning-objective list-item">
                        ${ learningObjective.definition }
                    </li>
                </g:each>
            </ul>

        </div>

        <div class="Content">

            <div class="form-title">
                <h3>Content</h3>
            </div>

            <table>
            <g:if test="${contentList.getClass()!=String}">
                <g:each var="contentItem" in="${contentList}">
                    <tr id="${contentItem.id}" class="topicItem">
                        <td class="topicTitle">
                            ${contentItem.topicTitle}
                        </td>
                        <td class="topicDimensions">
                            <span>
                                <!-- FIXME move complex logic to controller -->
                                <g:img width="30" dir="images/content" file="knowDim${contentItem.dimensions.sort(){it.value}.collect(){it.toString().charAt(0)}.join()?:'None'}.png" />

                                ${contentItem.dimensions.join(',')}
                            </span>
                        </td>
                        <td class="topicPriority">
                            ${contentItem.priority}
                        </td>
                        <td class="topicPreReq">
                            <g:if test="${contentItem.preReq }">
                                Pre-Req
                            </g:if>
                        </td>
                    </tr>
                </g:each>
            </g:if>
            </table>

        </div>

    </body>
</html>
