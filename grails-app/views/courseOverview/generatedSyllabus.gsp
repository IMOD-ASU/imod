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
            Print Syllabus
        </title>
        <g:external dir="css/source" file="syllabus.css" media="screen, print"/>
        <g:external dir="bower_components/jquery/dist" file="jquery.min.js" />
        <g:external dir="bower_components/jquery.ui/ui" file="core.js" />
        <g:external dir="bower_components/jquery.ui/ui" file="widget.js" />

        <!-- ui widgets -->
        <g:external dir="bower_components/jquery.ui/ui" file="accordion.js" />
        <g:external dir="bower_components/jquery.ui/ui" file="button.js" />
        <g:external dir="bower_components/jquery.ui/ui" file="dialog.js" />

        <!-- plugins to javascript libraries -->
        <g:external dir="bower_components/qtip2" file="jquery.qtip.min.js" />
        <g:external dir="bower_components/jstree/dist" file="jstree.min.js" />
        <g:external dir="bower_components/jquery.validate/dist" file="jquery.validate.min.js" />
        <g:external dir="bower_components/jquery.maskedinput/dist" file="jquery.maskedinput.min.js" />
        <g:external dir="js/plugins" file="jquery.custom.draggable.js"/>
        <g:javascript src="source/syllabus.js" defer="defer" />

    </head>
    <body>
        <div class="wrapper">

        	<div class="float-wrapper">
	        	<div class="right">
	            	<button class="topicButtonGradient printpdf">Print Page</button>
	            </div>
            </div>

            <h2><center>${currentImod?.name.encodeAsCustomEscape()}</center></h2>

        	<div id="syllabus-content">
	        	<g:if test="${!hideSectionsList.contains('-course-')}">
		            <div class="course-details" >
		                <div class="form-title">
		                    <h3>
		                    Course Details
		                    </h3>
		                </div>
		                <div id="course">
		                <div class="fieldcontain">
		                    <label for="imodNumber">
		                        <g:message code="imod.imodNumber.label" default="Course Number" />:
		                    </label>
		                    <strong>${currentImod?.imodNumber.encodeAsCustomEscape()}</strong>
		                </div>
		                <div class="fieldcontain">
		                    <label for="url">
		                        <g:message code="imod.url.label" default="Course URL" />:
		                    </label>
		                    <strong>
		                        ${currentImod?.url.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label for="courseLocation">
		                        <g:message code="imod.courseLocation.label" default="Classroom Location" />:
		                    </label>
		                    <strong>
		                    	<g:if test="${currentImod?.courseLocation == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.courseLocation.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label for="courseSemester">
		                        <g:message code="imod.courseSemester.label" default="Semester" />:
		                    </label>
		                    <strong>
		                    	<g:if test="${currentImod?.courseSemester == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.courseSemester.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                </div>
		            </div>%{-- course details --}%
		        </g:if>

		        <g:if test="${!hideSectionsList.contains('-schedule-')}">
		            <div class="schedule">
		                <div class="form-title">
		                    <h3>
		                    Schedule
		                    </h3>
		                </div>
		                <div id="schedule">
		                <div class="fieldcontain">
		                    <label>Start Date:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.schedule?.startDate == null}">
		                    		N/A
		                    	</g:if>
		                        <g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.startDate}"/>
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>End Date:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.schedule?.endDate == null}">
		                    		N/A
		                    	</g:if>
		                        <g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.endDate}"/>
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>Start Time:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.schedule?.startTime == null}">
		                    		N/A
		                    	</g:if>
		                        <joda:format pattern="HH:mm" value="${currentImod?.schedule?.startTime}"/>
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>End Time:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.schedule?.endTime == null}">
		                    		N/A
		                    	</g:if>
		                        <joda:format pattern="HH:mm" value="${currentImod?.schedule?.endTime}"/>
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label for="repeats">
		                        Repeats
		                    </label>
		                    <strong>
		                    	<g:if test="${currentImod?.schedule?.repeats == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.schedule?.repeats?.description.encodeAsCustomEscape()}
		                    </strong>
		                </div>

		                <g:if test="${(currentImod?.schedule?.repeats?.description == "Weekly" || currentImod?.schedule?.repeats?.description == "Daily") && currentImod?.schedule?.repeatsEvery?.description != null}">
		                    <div class="fieldcontain">
		                        <label>
		                            Repeats Every
		                        </label>
		                        <strong>
		                        	<g:if test="${currentImod?.schedule?.repeatsEvery == null}">
			                    		N/A
			                    	</g:if>
			                    	<g:else>
		                          	  ${currentImod?.schedule?.repeatsEvery?.description.encodeAsCustomEscape()} days
		                        	</g:else>
		                        </strong>
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
		                                    <strong>${scheduleWeekDays.description.encodeAsCustomEscape()}</strong>
		                                </label>
		                            </g:if>
		                        </g:each>

		                    </div>
		                </g:if>
		                </div>
		            </div>%{-- schedule --}%
		        </g:if>

		        <g:if test="${!hideSectionsList.contains('-description-')}">
		            <div class="course-description">
		                <div class="form-title">
		                    <h3>
		                    Course Description
		                    </h3>
		                </div>
		                <div id="description">
		                <div class="fieldcontain">
		                    <label>Overview:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.overview == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.overview.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>Subject Area:</label>
		                    <strong>
		                        ${currentImod?.subjectArea.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>Audience:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.audience == null || currentImod?.audience.isEmpty()}">
		                    		N/A
		                    	</g:if>
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
		                    <strong>
		                    	<g:if test="${currentImod?.creditHours == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.creditHours.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>Time Ratio:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.timeRatio == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.timeRatio.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		                <div class="fieldcontain">
		                    <label>Number of Seats:</label>
		                    <strong>
		                    	<g:if test="${currentImod?.numberOfSeats == null}">
		                    		N/A
		                    	</g:if>
		                        ${currentImod?.numberOfSeats.encodeAsCustomEscape()}
		                    </strong>
		                </div>
		             </div>
		            </div>%{-- course description --}%
		        </g:if>

		        <g:if test="${!hideSectionsList.contains('-policy-')}">
		            <div class="course-policy">
		                <div class="form-title">
		                    <h3>
		                    Course Policy
		                    </h3>
		                </div>
		                <div id="policy">
	                	<g:if test="${!hideSectionsList.contains('-grading-procedure-')}">
			                <div class="fieldcontain" >
			                    <h4>
			                        <strong>Grading procedure:</strong>
			                    </h4>
			                    <div id="grading-procedure">
			                    <p>
			                    	<g:if test="${currentImod?.gradingProcedure == null || currentImod?.gradingProcedure.isEmpty()}">
			                    		N/A
			                    	</g:if>

			                        ${currentImod?.gradingProcedure.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

			            <g:if test="${!hideSectionsList.contains('-attendance-')}">
			                <div class="fieldcontain" >
			                    <h4>
			                        <strong>Attendance and tardiness:</strong>
			                    </h4>
			                    <div id="attendance">
			                    <p>
			                    	<g:if test="${currentImod?.attendance == null || currentImod?.attendance.isEmpty()}">
			                    		N/A
			                    	</g:if>
			                        ${currentImod?.attendance.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

			            <g:if test="${!hideSectionsList.contains('-participation-')}">
			                <div class="fieldcontain">
			                    <h4>
			                        <strong>Class participation:</strong>
			                    </h4>
			                    <div  id ="participation">
			                    <p>
			                    	<g:if test="${currentImod?.classParticipation == null || currentImod?.classParticipation.isEmpty()}">
			                    		N/A
			                    	</g:if>
			                        ${currentImod?.classParticipation.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

			            <g:if test="${!hideSectionsList.contains('-conduct-')}">
			                <div class="fieldcontain" >
			                    <h4>
			                        <strong>Professional Conduct:</strong>
			                    </h4>
			                    <div id="conduct">
			                    <p>
			                    	<g:if test="${currentImod?.professionalConduct == null || currentImod?.professionalConduct.isEmpty()}">
			                    		N/A
			                    	</g:if>
			                        ${currentImod?.professionalConduct.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

			            <g:if test="${!hideSectionsList.contains('-exams-')}">
			                <div class="fieldcontain" >
			                    <h4>
			                        <strong>Missed exams/Make-up exams:</strong>
			                    </h4>
			                    <div id="exams">
			                    <p>
			                    	<g:if test="${currentImod?.missedExams == null || currentImod?.missedExams.isEmpty()}">
			                    		N/A
			                    	</g:if>
			                        ${currentImod?.missedExams.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

			            <g:if test="${!hideSectionsList.contains('-missed-exams-')}">
			                <div class="fieldcontain" >
			                    <h4>
			                        <strong>Missed Assignments:</strong>
			                    </h4>
			                    <div id="missed-exams">
			                    <p>
			                    	<g:if test="${currentImod?.missedAssignments == null || currentImod?.missedAssignments.isEmpty()}">
			                    		N/A
			                    	</g:if>
			                        ${currentImod?.missedAssignments.encodeAsCustomEscape()}
			                    </p>
			                    </div>
			                </div>
			            </g:if>

		             </div>
		            </div>%{-- course policy --}%
		        </g:if>

		        <g:if test="${!hideSectionsList.contains('-instructor-')}">

		            <div class="instructors">

		                <div class="form-title">
		                    <h3>
		                    Instructor
		                    </h3>
		                </div>
		                <div id="instructor">
		                	<g:if test="${currentImod?.instructors != null && !currentImod?.instructors.isEmpty()}">
				                <ul>
				                <g:each in="${currentImod?.instructors}" var="instructor">

				                    <li>
				                        <strong>
				                            ${instructor.firstName.encodeAsCustomEscape()} ${instructor.lastName.encodeAsCustomEscape()}
				                        </strong>
				                        <br>
				                        ${instructor.role.encodeAsCustomEscape()}
				                        <br>
				                        ${instructor.email.encodeAsCustomEscape()}
				                        <br>
				                        <g:if test="${instructor.officeHours != null && !instructor.officeHours.isEmpty()}">
				                            Office Hours: ${instructor.officeHours.encodeAsCustomEscape()}
				                            <br>
				                        </g:if>
				                        <g:if test="${instructor.webPage != null && !instructor.webPage.isEmpty()}">
				                            Website: ${instructor.webPage.encodeAsCustomEscape()}
				                            <br>
				                        </g:if>
				                        <g:if test="${instructor.location != null && !instructor.location.isEmpty()}">
				                            Location: ${instructor.location.encodeAsCustomEscape()}
				                            <br>
				                        </g:if>
				                    </li>

				                </g:each>
				                </ul>
				            </g:if>
		                	<g:else>
			                	N/A
			                </g:else>
			            </div>
		            </div>%{-- instructors --}%
	            </g:if>

	            <g:if test="${!hideSectionsList.contains('-learning-objectives-list-')}">
		            <div class="learning-objectives">
		                <div class="form-title">
		                    <h3>
		                    Learning Objectives
		                    </h3>
		                </div>
		                <g:if test="${learningObjectives != null && !learningObjectives.isEmpty()}">
		                <ul id="learning-objectives-list" class="learning-objective list-wrapper">
		                    <g:each var="learningObjective" in="${learningObjectives}">
		                        <g:if test="${learningObjective.definition != null && learningObjective.definition.trim() != "" }">
		                            <li class="learning-objective list-item">
		                                ${ learningObjective.definition.encodeAsCustomEscape() }
		                            </li>
		                        </g:if>
		                    </g:each>
		                </ul>
			            </g:if>
                        <g:else>
			            	N/A
				        </g:else>
		            </div>
		        </g:if>

		        <g:if test="${!hideSectionsList.contains('-content-list-')}">
		            <div class="Content">
		                <div class="form-title">
		                    <h3>
		                    Content
		                    </h3>
		                </div>
		                <div id="content-list">
		                	<g:if test="${contentList == null || contentList.isEmpty()}">
		                		N/A
			                </g:if>
		                    %{-- comes straight from the controller as text --}%
		                    ${contentList.encodeAsCustomEscape()}
		                </div>
		            </div>
	            </g:if>
	        </div>

	        <div class="right">
            	<button class="topicButtonGradient printpdf">Print Page</button>
            </div>

            <input type="hidden" id="sortIdList" value="${sortIdList}">

        </div>
    </body>
</html>
