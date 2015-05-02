<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@ page import="imod.Content"%>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>
            Syllabus
        </title>

        <g:external dir="css/source" file="syllabus.css" media="screen, print"/>
        

    </head>
    <body>

        <div class="wrapper">

            <div class="course-details">
                <div class="form-title">
                    <h3>Course Details</h3>
                    
                </div>
                <div class="fieldcontain">
                    <label for="name">
                        <g:message code="imod.name.label" default="Course Title" />: 
                    </label>
                    <strong>${currentImod?.name.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label for="imodNumber">
                        <g:message code="imod.imodNumber.label" default="Course Number" />: 
                    </label>
                    <strong>${currentImod?.imodNumber.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label for="url">
                        <g:message code="imod.url.label" default="Course URL" />: 
                    </label>
                    <strong>${currentImod?.url.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label for="courseLocation">
                        <g:message code="imod.courseLocation.label" default="Classroom Location" />: 
                    </label>
                    <strong>${currentImod?.courseLocation.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label for="courseSemester">
                        <g:message code="imod.courseSemester.label" default="Semester" />: 
                    </label>
                    <strong>${currentImod?.courseSemester.encodeAsHTML()}</strong>
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

                <g:if test="${currentImod?.schedule?.repeats?.description == "Weekly" }">
                    
                    <div class="fieldcontain">
                        <label for="scheduleWeekDays">
                            Repeats On
                        </label>
                        <g:each in="${imod.ScheduleWeekDays.list()}" var="scheduleWeekDays" status="i">
                            
                            <g:if test="${scheduleWeekDays.description == currentImod?.schedule?.scheduleWeekDays?.find{p -> p.id == scheduleWeekDays?.id}.toString()}">                           
                                <label for="weekdays">
                                    <strong>${scheduleWeekDays.description}</strong>
                                </label>
                            </g:if>
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
                    <strong>${currentImod?.overview.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label>Subject Area:</label>
                    <strong>${currentImod?.subjectArea.encodeAsHTML()}</strong>
                </div>
                <div class="fieldcontain">
                    <label>Audience:</label>
                    <strong>
                        <g:each in="${currentImod?.audience*.description}" var="it" status="i">
                            ${it.encodeAsHTML().encodeAsHTML()}

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
                    <strong>${currentImod?.timeRatio.encodeAsHTML()}</strong>
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
                    <h4><strong>Grading procedure:</strong></h4>
                    <p>${currentImod?.gradingProcedure.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
                <div class="fieldcontain">
                    <h4><strong>Attendance and tardiness:</strong></h4>
                    <p>${currentImod?.attendance.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
                <div class="fieldcontain">
                    <h4><strong>Class participation:</strong></h4>
                    <p>${currentImod?.classParticipation.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
                <div class="fieldcontain">
                    <h4><strong>Professional Conduct:</strong></h4>
                    <p>${currentImod?.professionalConduct.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
                <div class="fieldcontain">
                    <h4><strong>Missed exams/Make-up exams:</strong></h4>
                    <p>${currentImod?.missedExams.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
                <div class="fieldcontain">
                    <h4><strong>Missed Assignments:</strong></h4>
                    <p>${currentImod?.missedAssignments.trim().encodeAsHTML().replaceAll("\n","<br/>")}</p>
                </div>
            </div>%{-- course policy --}%

            <g:if test="${currentImod?.instructors != null && !currentImod?.instructors.isEmpty()}">
            <div class="instructors">

                <div class="form-title">
                    <h3>Instructor</h3>
                </div>

                <ul>
                <g:each in="${currentImod?.instructors}" var="instructor">
                    
                    <li>
                        <strong>${instructor.firstName.encodeAsHTML().replaceAll("\n","<br/>")} ${instructor.lastName.encodeAsHTML().replaceAll("\n","<br/>")}</strong><br/>
                        ${instructor.role.encodeAsHTML().replaceAll("\n","<br/>")}<br/>
                        ${instructor.email.encodeAsHTML().replaceAll("\n","<br/>")}<br/>                    
                        <g:if test="${instructor.officeHours != null && !instructor.officeHours.isEmpty()}">
                            Office Hours: ${instructor.officeHours.encodeAsHTML().replaceAll("\n","<br/>")}<br/>
                        </g:if>
                        <g:if test="${instructor.webPage != null && !instructor.webPage.isEmpty()}">
                            Website: ${instructor.webPage.encodeAsHTML().replaceAll("\n","<br/>")}<br/>
                        </g:if>
                        <g:if test="${instructor.location != null && !instructor.location.isEmpty()}">
                            Location: ${instructor.location.encodeAsHTML().replaceAll("\n","<br/>")}<br/>
                        </g:if>
                    </li>

                </g:each>
                </ul>

            </div>%{-- instructors --}%
            </g:if>

            <div class="learning-objectives">

                <div class="form-title">
                    <h3>Learning Objectives</h3>
                </div>

                <ul class="learning-objective list-wrapper">
                    <g:each var="learningObjective" in="${learningObjectives}">                    
                        <g:if test="${learningObjective.definition != null && learningObjective.definition.trim() != "" }">
                            <li class="learning-objective list-item">
                                ${ learningObjective.definition.encodeAsHTML() }

                            </li>
                        </g:if>
                    </g:each>
                </ul>

            </div>

            <div class="Content">

                <div class="form-title">
                    <h3>Content</h3>
                </div>

                %{-- comes straight from the controller as text --}%
                ${contentList.replaceAll("\n","<br/>").replaceAll("&(?!amp;)", "&amp;")}

            </div>

        </div>
    </body>
</html>
