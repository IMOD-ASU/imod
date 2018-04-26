<%@ page import="imod.Help" %>
<%@ page import="imod.KnowledgeDimensionEnum"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>
		Schedule
	</title>

	<meta name="layout" content="imod">
	<!-- FIXME These should be loaded when needed, not prefetched -->
	<link id="imgNone" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimNone.png')}">
	<link id="imgC" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimC.png')}">
	<link id="imgF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimF.png')}">
	<link id="imgM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimM.png')}">
	<link id="imgP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimP.png')}">
	<link id="imgCF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCF.png')}">
	<link id="imgCM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCM.png')}">
	<link id="imgCP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCP.png')}">
	<link id="imgFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFM.png')}">
	<link id="imgFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFP.png')}">
	<link id="imgMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimMP.png')}">
	<link id="imgCFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFM.png')}">
	<link id="imgCFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFP.png')}">
	<link id="imgCMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCMP.png')}">
	<link id="imgFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFMP.png')}">
	<link id="imgCFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFMP.png')}">

	<g:external dir="css/source" file="learningObjective.css" />
	<g:external dir="css/source" file="iconModule.css" />
	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.css" />
	<g:external dir="bower_components/moment/min" file="moment.min.js" />
	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.js" />
	<g:external dir="bower_components/fontawesome/css" file="font-awesome.min.css" />

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.min.css" type="text/css" rel="stylesheet" />

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>

	<g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.js" />
	<g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.css" />
	<g:external dir="css/source" file="schedule.css" />

</head>

<body >

<div class="modalBackgroundFavorites"></div>

<div id="topicDialogBackground" class="modalBackground"></div>


<!-- Adding the  table layout as in Learning objective page -->
<table class="learning-objective inner-table schedule-table">
	<tr>
		<td>
			<!-- left panel for the page -->
			<div class="learning-objective list">
				<div class="form-title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
					<span class="title-text">
						Learning Objectives
					</span>
				</div>
				<ul class="learning-objective list-wrapper">
					<g:if test="${learningObjectives}">
						<g:each var="learningObjective" in="${learningObjectives}">
							<li class="learning-objective list-item ${(learningObjective.id == currentLearningObjective?.id) ? 'active' : ''  }">
								<g:link action="index" id="${currentImod.id}" params="[learningObjectiveID: learningObjective.id]" class="learning-objective list-link">
									${ learningObjective.definition }
								</g:link>
							</li>
						</g:each>
					</g:if>
					<g:else>
						<div class="no-objective-defined">
							<div style="opacity: 0.5;height: 50px;font-size: 20px">There are no objectives defined.</div>
							<div>
								<span class="topicButtonGradient" >
									<a href="../../learningObjective/performance/${currentImod?.id}">
										Define Objectives
									</a>
								</span>
							</div>
						</div>
					</g:else>
				</ul>
			</div>
			<div class="form-title" style=" border-top-left-radius: 3px; border-top-right-radius: 3px; text-align:center;">
				Filter Options
			</div>
			<div id="filter-assessment-techniques" >
				<h3>
					Knowledge Dimensions
				</h3>
				<div >
					<g:checkBox name="myCheckbox" class="select-all" id="selectAllkd" />
					<label>Select All</label>

					<hr style=" width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
					<ul>
						<g:each var="knowledgeDimension" in="${knowledgeDimensions}" status="index">
							<li>
								<g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}" checked = "${dimension.find { it.toString() == knowledgeDimension.description.toString() }}" />
								<label for="knowledge-dimension-${index}">
									${knowledgeDimension.description}
								</label>

							</li>
						</g:each>
					</ul>
				</div>
				<h3>
					Type of Activity
				</h3>
				<div>
					<g:checkBox name="myCheckbox" class="select-all" id="selectAlllD" />
					<label>Select All</label>
					<hr style=" width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
					<ul>
						<g:each var="activityType" in="${activityTypes}" status="index">

							<li>
								<g:checkBox  name="activityType" />
								${activityType}
							</li>
						</g:each>
					</ul>
				</div>
				<h3>
					Task Environment
				</h3>
				<div>
					<g:checkBox name="myCheckbox" class="select-all" id="selectAlldC" />
					<label>Select All</label>

					<hr style=" width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
					<ul>
						<g:each var="taskEnv" in="${taskEnvironment}" status="index">
							<li>
								<g:checkBox  name="taskEnv" />
								${taskEnv}
							</li>
						</g:each>
					</ul>
				</div>
			</div>
		</td>

		<td class="learning-objective definition-cell">

			<div class="container"  >
				<div class="row">
					<div class="col-xs-12"></div>
					<h1></h1>
					<br />
					<div id="scheduleCalendar"></div>

				</div>
			</div>


			<g:if test="${learningObjectives}">
				<!-- <div class="topic_addition_widget" align="left"> -->
				<div class="container" align="left">
					<div>
						<br>
						<div>
							<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
							<span id="add-new-technique-button" class="topicButtonGradient">
								<button>
									<i class="fa fa-plus yellow"></i>
									Add New Activity
								</button>
							</span>

						</div>
					</div>
				</div>

				<g:javascript> var imodStartDateYear = (${imodStartDate.split('-')[0]}) </g:javascript>
				<g:javascript> var imodStartDateMonth = (${imodStartDate.split('-')[1]}) </g:javascript>
				<g:javascript> var imodStartDateDay = (${imodStartDate.split('-')[2]}) </g:javascript>
				<g:javascript> var imodEndDateYear = (${imodEndDate.split('-')[0]}) </g:javascript>
				<g:javascript> var imodEndDateMonth = (${imodEndDate.split('-')[1]}) </g:javascript>
				<g:javascript> var imodEndDateDay = (${imodEndDate.split('-')[2]}) </g:javascript>

				<!--Dialog box for Add New Task to Calendar -->

				<div class="draggable" id="add-new-technique" >
					<g:form controller="schedule" method="post" action="addEvent" class="no-warn-form">
						<table class="inner-table">
							<tr>
								<td>
									<div>
										<div class ="add_activity_text"  >
											<b> Activity Title</b>
										</div>
										<div>
											<g:textField name="title" class="add_activity_text" id = "taskTitle"/>
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Learning Domain</b>
										</div>
										<div >
											<g:select name="learnO"  id="taskLearningDomain" class="add_activity_dropdown custom-dropdown" from="${learningDomains}" />
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Knowledge Dimension</b>
										</div>
										<div>
											<g:select name="knowD"  id="taskKnowledgeDimension" class="add_activity_dropdown custom-dropdown" from="${knowledgeDimensions}" />
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Type Of Activity</b>
										</div>
										<div >
											<g:select name="typeOfActivityField"  id="taskTypeOfActivity" class="add_activity_dropdown custom-dropdown" from="${activityTypes}" />
										</div>
										<br>
									</div>
								</td>
								<td class="schedule-tab course-overview-form-td">
									<div>
										<div class ="add_activity_text" id="assignedDate" >
											<b> Assigned Date</b>
										</div>
										<div class ="add_activity_field"  >
											<g:datePicker name="startDate" id="taskStartDate" default="none" noSelection="['':'']" precision="day"  value="${currentImod?.schedule?.startDate}" years="${2014..2100}" class="show-hover-new taskStartDate"  title="${Help.toolTip("OVERVIEW", "Schedule start Date")}" />
											<span id="startDateError"></span>
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Due Date</b>
										</div>
										<div class ="add_activity_field" >
											<g:datePicker name="endDate" id="taskEndDate" default="none" noSelection="['':'']" precision="day" value="${currentImod?.schedule?.endDate}" years="${2014..2100}" title="${Help.toolTip("OVERVIEW", "Schedule end Date")}" class="show-hover-new" />
											<span id="endDateError"></span>
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Task Environment</b>
										</div>
										<div>
											<g:select name="enviro" id="taskEnvironment" class="add_activity_dropdown custom-dropdown" from="${taskEnvironment}" />
										</div>
										<br>
										<div class ="add_activity_text" >
											<b> Expected Working Hours</b>
										</div>
										<div>
											<g:textField name="workTime" class="add_activity_text" id ="taskWorkTime"/>
											<span><b>Hours</b></span>
										</div>
									</div>
								</td>
							</tr>
							<br>
						</table>

						<g:hiddenField name="imodId" value="${currentImod.id}" />
						<g:hiddenField name="lo" value="${currentLearningObjective.id}"/>
						<g:hiddenField name="id" id="taskID2" value="${taskID}" />

						<fieldset id="courseoverview-form"></fieldset>

						<div align="left" class="add_activity_text pedagogy-title" >
							<b>  Notes</b>
						</div>
						<div align="left" class="add_activity_text">
							<g:textArea rows="7" cols="82"  name="notes" id="taskInfo"/>
						</div>

						<br>
						<g:hiddenField name="edit" id="editCheck" value="yes" />

						<!-- <div id="modalButtons" style="align:left"> -->
						<div id="modalButtons" style="align:right">
							<button type="submit"  id="saveButton" class="new-technique-popup-button">
								<!--<button type="submit" name="_action_save" value="Save" id="saveButton" class="new-technique-popup-button"onclick="addTask()">-->
								<i class="fa fa-save green"></i>
								Save Activity
							</button>

							<!-- <button type="submit"  id="editButton" class="new-technique-popup-button"> -->
								<!--<button type="submit" name="_action_save" value="Save" id="saveButton" class="new-technique-popup-button"onclick="addTask()">-->
								<!--<i class="fa fa-save green"></i>
								Save Changes
							</button> -->

							<button type="submit"  id="cancelButton" class="new-technique-popup-button" name = "cancelButton">
								<!--<button type="submit" name="_action_cancel" value="Cancel" class="new-technique-popup-button">-->
								<i class="fa fa-times red"></i>
								Cancel
							</button>

						</div>
						<br>
					</g:form>

					<g:form controller="schedule" method="post" action="deleteEvent" class="no-warn-form">
						<g:hiddenField name="id" id="taskID" value="${taskID}" />
						<g:hiddenField name="imodId" value="${currentImod.id}" />
						<g:hiddenField name="lo" value="${currentLearningObjective.id}"/>
						<button type="submit" class="new-technique-popup-button" id="deleteButton">
							<i class="fa fa-times red"></i>
							Delete Current Event
						</button>

					</g:form>
        		</div>


    <%--Dialog box for Displaying in edit and View mode Technique --%>
				<div id="display-new-technique" title="Display Technique">
					<%--To render the add new Technique dialog box--%>
				</div>
			</g:if>

			<!-- The chart goes here <div id="chartContainer" type="hidden" style="height: 300px; width: 100%;display: none;"></div> -->
			<div id="chartContainer"  style="height: 300px; width: 100%; display:block;"></div>
			<!--
	List out the imods course info dates (demo getting data from the controller file)
	-->
			<br>
			<div style="display: none;">
				<p id="startDate">Start Date Here:${startDate1}</p>
				<p id="endDate">End Date Here:${endDate1}</p>
				<p id="creditHours">Credit Hours Here:${creditHours1}</p>
				<p id="timeRatio">Credit Hours/Time Ratio Here:${timeRatio1}</p>

				<p id="creditHoursH" >${creditHours1}</p>
				<p id="timeRatioH" >${timeRatio1}</p>
			</div>

			<div style="display: none;">
				<p  >
				<form id="startDate" >
					Task Start Date -->
					<label>Choose Year:</label>
					<select id='chooseYear' name="years1">
					</select>

					<label>Choose Month:</label>
					<select id='chooseMonth' name="months1">
					</select>

					<label>Choose Day:</label>
					<select id='chooseDay' name="days1">
					</select>

					<label>Choose Hour:</label>
					<select id='chooseHour' name="hours1">
					</select>

					<br/>
				</form>
			</p>


				<p >
				<form id="endDate" >
					Task End Date -->
					<label>Choose Year:</label>
					<select id='chooseYear2' name="years1">
					</select>

					<label>Choose Month:</label>
					<select id='chooseMonth2' name="months1">
					</select>

					<label>Choose Day:</label>
					<select id='chooseDay2' name="days1">
					</select>

					<label>Choose Hour:</label>
					<select id='chooseHour2' name="hours1">
					</select>

					<br/>
				</form>
			</p>

				<form id="addTaskBut" >
					<input  type="button" value="Add Task" id = "addT" />
				</form>
			</div>



		</td>
	</tr>
</table>
<div id="selectKnowledgeDimensionBackground" class="modalBackground2"></div>
<div id="selectKnowledgeDimensions" class="draggable modelBackground2Target">
	<div class="draggable-handle">
		<input type="hidden" id="topicID" />
		<span>
			<ul>
				<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">
					<li>
						<input
							type="checkbox"
							id="${dimension}"
							value="${dimension}"
						/>
						<label class="knowledge-dim-label" title="${dimension.getInfo()}" for="${dimension}">
							${dimension}
						</label>
					</li>
				</g:each>
			</ul>
			<g:img
				dir="images/content"
				file="knowDimNone.png"
				id="dimImage"
				width="71"
				height="71"
			/>
		</span>
		<button
			class="save showHoverNew resourceButton topicButtonGradient knowledgedimBtn"
			id="knowDimFinished"
			title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}"
		>
			<i class="fa fa-save green"></i>
			${message(code: 'Save Resource', default: 'Continue')}
		</button>
		<button
			class="cancel showHoverNew resourceButton topicButtonGradient"
			id="closeKnowDim"
			title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}"
		>
			<i class="fa fa-times red"></i>
			${message(code: 'Cancel Resource', default: ' Cancel')}
		</button>

	</div>

</div>

<g:javascript src="source/schedule.js" defer="defer" />
</body>
</html>
