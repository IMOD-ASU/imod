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
        <g:external dir="css/source" file="syllabus.css" media="screen, print"/>
    </head>
    <body>

        <input type="hidden" class="imod_id" value="${currentImod.id}">

        <div class="wrapper">
            <div class="course-details">
                <div class="form-title">
                    <h3>Course Details</h3>

                    <div class="options">

                    	<g:if env="development">
		                    <a href="#" class="edit-pref">Edit Preferences</a>
		                </g:if>

                        <a href="javascript:window.print()">Print</a>
                        <a href="../syllabuspdf/${currentImod?.id}" class="download-pdf">Download</a>

                    </div>

                </div>
                <div class="fieldcontain">
                    <label for="name">
                        <g:message code="imod.name.label" default="Course Title" />:
                    </label>
                    <strong>
                        ${currentImod?.name.encodeAsCustomEscape()}
                    </strong>
                </div>
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
                        ${currentImod?.courseLocation.encodeAsCustomEscape()}
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label for="courseSemester">
                        <g:message code="imod.courseSemester.label" default="Semester" />:
                    </label>
                    <strong>
                        ${currentImod?.courseSemester.encodeAsCustomEscape()}
                    </strong>
                </div>
            </div>%{-- course details --}%

            <div class="schedule">
                <div class="form-title">
                    <h3>Schedule</h3>
                </div>
                <div class="fieldcontain">
                    <label>Start Date:</label>
                    <strong>
                        <g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.startDate}"/>
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label>End Date:</label>
                    <strong>
                        <g:formatDate format="dd-MMM-yyyy" date="${currentImod?.schedule?.endDate}"/>
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label>Start Time:</label>
                    <strong>
                        <joda:format pattern="HH:mm" value="${currentImod?.schedule?.startTime}"/>
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label>End Time:</label>
                    <strong>
                        <joda:format pattern="HH:mm" value="${currentImod?.schedule?.endTime}"/>
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label for="repeats">
                        Repeats
                    </label>
                    <strong>
                        ${currentImod?.schedule?.repeats?.description.encodeAsCustomEscape()}
                    </strong>
                </div>

                <g:if test="${currentImod?.schedule?.repeats?.description == "Weekly" || currentImod?.schedule?.repeats?.description == "Daily"}">
                    <div class="fieldcontain">
                        <label>
                            Repeats Every
                        </label>
                        <strong>
                            ${currentImod?.schedule?.repeatsEvery?.description.encodeAsCustomEscape()} days
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
            </div>%{-- schedule --}%

            <div class="course-description">
                <div class="form-title">
                    <h3>Course Description</h3>
                </div>
                <div class="fieldcontain">
                    <label>Overview:</label>
                    <strong>
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
                        ${currentImod?.creditHours.encodeAsCustomEscape()}
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label>Time Ratio:</label>
                    <strong>
                        ${currentImod?.timeRatio.encodeAsCustomEscape()}
                    </strong>
                </div>
                <div class="fieldcontain">
                    <label>Number of Seats:</label>
                    <strong>
                        ${currentImod?.numberOfSeats.encodeAsCustomEscape()}
                    </strong>
                </div>
            </div>%{-- course description --}%

            <div class="course-policy">
                <div class="form-title">
                    <h3>Course Policy</h3>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Grading procedure:</strong>
                    </h4>
                    <p>
                        ${currentImod?.gradingProcedure.encodeAsCustomEscape()}
                    </p>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Attendance and tardiness:</strong>
                    </h4>
                    <p>
                        ${currentImod?.attendance.encodeAsCustomEscape()}
                    </p>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Class participation:</strong>
                    </h4>
                    <p>
                        ${currentImod?.classParticipation.encodeAsCustomEscape()}
                    </p>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Professional Conduct:</strong>
                    </h4>
                    <p>
                        ${currentImod?.professionalConduct.encodeAsCustomEscape()}
                    </p>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Missed exams/Make-up exams:</strong>
                    </h4>
                    <p>
                        ${currentImod?.missedExams.encodeAsCustomEscape()}
                    </p>
                </div>
                <div class="fieldcontain">
                    <h4>
                        <strong>Missed Assignments:</strong>
                    </h4>
                    <p>
                        ${currentImod?.missedAssignments.encodeAsCustomEscape()}
                    </p>
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

            </div>%{-- instructors --}%
            </g:if>


            <g:if test="${learningObjectives != null && learningObjectives.isEmpty()}">
            <div class="learning-objectives">
                <div class="form-title">
                    <h3>Learning Objectives</h3>
                </div>
                <ul class="learning-objective list-wrapper">
                    <g:each var="learningObjective" in="${learningObjectives}">
                        <g:if test="${learningObjective.definition != null && learningObjective.definition.trim() != "" }">
                            <li class="learning-objective list-item">
                                ${ learningObjective.definition.encodeAsCustomEscape() }
                            </li>
                        </g:if>
                    </g:each>
                </ul>
            </div>
            </g:if>

            <g:if test="${contentList.trim() != '<ul></ul>'}">
            <div class="Content">
                <div class="form-title">
                    <h3>Content</h3>
                </div>
                %{-- comes straight from the controller as text --}%
                ${contentList.encodeAsCustomEscape()}
            </div>
            </g:if>
        </div>

        <g:if env="development">
        <div class="pref-modal-wrap">
            <div class="pref-modal">

                <h3>Edit Syllabus Preferences</h3>

                <ol class="pref-sortable">


                    <g:each in="${settings}" var="setting">

                        <li data-id="${setting.id}">
                            <label>
                                <g:checkBox name="setting_checkbox" value="${setting.prefs.selected[0]}" />
                                <span>${setting.description}</span>
                            </label>
                        </li>

                    </g:each>

                </ol>

                <button class="submit">Submit</button>

            </div>
        </div>
        </g:if>

        <g:external dir="bower_components/jquery/dist" file="jquery.min.js"/>
        <g:external dir="bower_components/jquery-sortable/source/js" file="jquery-sortable-min.js"/>
        <script type="text/javascript">

            $(function() {

                // $("ol.pref-sortable").sortable();

                // syllabus preferences hide/show
                $('.pref-modal-wrap').click(function() {
                	$(this).hide();
                	return false;
                });

                $('.edit-pref').click(function() {
                	$('.pref-modal-wrap').show();
                	return false;
                });

                $('.pref-modal').click(function(event) {

                	event.stopPropagation();

                });

                $('.pref-modal .submit').click(function() {

                    var settings = [];

                    $('.pref-sortable li').each(function(index) {

                        var setting = {}

                        setting["description"] = $(this)
                            .find('span')
                            .text();
                        setting["selected"] = $(this)
                            .find('input[type="checkbox"]')
                            .is(":checked");
                        setting["sort_number"] = index;
                        setting["id"] = $(this).data('id');

                        settings.push(setting);

                    });

                    $.ajax({
                        url: '../syllabusUpdate',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            imod: $('.imod_id').val(),
                            settings: JSON.stringify(settings)
                        },
                        success:
                            function () {
                                console.log("done");
                            },
                        error:
                            function (xhr) {
                                // When something goes wrong log to the browser console
                                console.log(xhr.responseText);
                            }
                    });

                });

            });

        </script>

    </body>
</html>
