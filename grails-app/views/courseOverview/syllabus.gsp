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
            Generate Syllabus
        </title>
        <g:external dir="bower_components/fontawesome/css" file="font-awesome.min.css" />
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
	            	<a href="#" class="topicButtonGradient generate-syllabus">Save &amp; Generate Syllabus</a>
	            </div>
            </div>

            <h2><center>${currentImod?.name.encodeAsCustomEscape()}</center></h2>

            <div id="syllabus-content">
	            <div class="course-details" >
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-course-') ? '' : 'checked'} data-id="course"  />
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

	            <div class="schedule">
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-schedule-') ? '' : 'checked'} data-id="schedule"/>
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

	            <div class="course-description">
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-description-') ? '' : 'checked'} data-id="description"/>
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
	                        ${currentImod?.creditHours}
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
	                        ${currentImod?.numberOfSeats}
	                    </strong>
	                </div>
	             </div>
	            </div>%{-- course description --}%

	            <div class="course-policy">
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-policy-') ? '' : 'checked'} data-id="policy"/>
	                    Course Policy
	                    </h3>
	                </div>
	                <div id="policy">
	                <div class="fieldcontain" >
	                    <h4>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-grading-procedure-') ? '' : 'checked'} data-id="grading-procedure"/>
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
	                <div class="fieldcontain" >
	                    <h4>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-attendance-') ? '' : 'checked'} data-id="attendance"/>
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
	                <div class="fieldcontain">
	                    <h4>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-participation-') ? '' : 'checked'} data-id="participation"/>
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
	                <div class="fieldcontain" >
	                    <h4>
	                        <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-conduct-') ? '' : 'checked'} data-id="conduct"/>
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
	                <div class="fieldcontain" >
	                    <h4>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-exams-') ? '' : 'checked'} data-id="exams"/>
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
	                <div class="fieldcontain" >
	                    <h4>
	                        <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-missed-exams-') ? '' : 'checked'} data-id="missed-exams"/>
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
	             </div>
	            </div>%{-- course policy --}%

	            <div class="instructors">

	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-instructor-') ? '' : 'checked'} data-id="instructor"/>
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

	            <div class="learning-objectives">
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-learning-objectives-list-') ? '' : 'checked'} data-id="learning-objectives-list"/>
	                    Learning Objectives <span class="small-toggles"><input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-assessments-') ? '' : 'checked'} data-class="assessments"  /> Assessments <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-instructions-') ? '' : 'checked'} data-class="instructions"  /> Instructional Techniques</span>
	                    </h3>
	                </div>

                	<g:if test="${learningObjectives != null && !learningObjectives.isEmpty()}">
	                <ul id="learning-objectives-list" class="learning-objective list-wrapper">
	                    <g:each var="learningObjective" in="${learningObjectives}">
	                        <g:if test="${learningObjective.definition != null && learningObjective.definition.trim() != "" }">
	                            <li class="learning-objective list-item">
	                                ${ learningObjective.definition.encodeAsCustomEscape() }
	                                <g:if test="${learningObjective.assessmentTechniques.size()}">
	                                	<ul class="assessments">
	                                		<li>
						                    	<strong>Assessments</strong>
						                    	<ul>
												<g:each var="technique" in="${learningObjective.assessmentTechniques}">

													<g:if test="${technique != null && !technique.title.isEmpty()}">
														<li>${technique.title}</li>
													</g:if>

												</g:each>
												</ul>
											</li>
										</ul>
									</g:if>
	                                <g:if test="${learningObjective.pedagogyTechniques.size()}">
					                    <ul>
					                    <li class="instructions">
					                    	<strong>Instructional Techniques</strong>
					                    	<ul>
					                    		<g:each var="technique" in="${learningObjective.pedagogyTechniques}">

							                        <g:if test="${technique != null && !technique.title.isEmpty()}">
							                            <li>${technique.title}</li>
							                        </g:if>

							                    </g:each>
					                    	</ul>
				                    	</li>
					                    </ul>
					                </g:if>
	                            </li>
	                        </g:if>
	                    </g:each>
	                </ul>
		            </g:if>
		            <g:else>
		            	<div id="learning-objectives-list" class="learning-objective list-wrapper">
			            	N/A
			            </div>
			        </g:else>
	            </div>


	            <div class="Content">
	                <div class="form-title">
	                    <h3>
	                    <input type="checkbox" class="display-toggle" ${hideSectionsList.contains('-content-list-') ? '' : 'checked'} data-id="content-list"/>
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

            </div>
            <input type="hidden" name="imodID" id="imodID" value="${currentImod.id}">
            <input type="hidden" id="sortIdList" value="${sortIdList}">
            <div class="right">
            	<a href="#" class="topicButtonGradient generate-syllabus">Save &amp; Generate Syllabus</a>
            </div>
        </div>
    </body>
</html>
