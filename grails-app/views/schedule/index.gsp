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

	<g:external dir="css/source" file="schedule.css" />
	<g:external dir="css/source" file="learningObjective.css" />
	<g:external dir="css/source" file="iconModule.css" />

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.css" />

	<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.min.css" type="text/css" rel="stylesheet" />

	<!--
		Scheduler css and js imports here
	-->

	<g:external dir="bower_components/moment/min" file="moment.min.js" />
	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.js" />

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

	<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>

    %{-- <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> --}%

	<g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.js" />
	<g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.css" />

    <script>
	    $(function() {
	        $( "#datetimepicker" ).datetimepicker();
	    });

	    $(function() {
	        $( "#datetimepicker2" ).datetimepicker();
	    });
    </script>

</head>

<body>

	<div class="modalBackgroundFavorites"></div>
	<div class="favorites-modal draggable">
		<fieldset class="titleField draggable-handle">
			<div id="editTitle">
				<b> Delete Events</b>
			</div>
			TO BE DONE.
		</fieldset>

	</div>
	<div id="edit-imod" class="content scaffold-edit" role="main">
		<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>

	<g:hasErrors bean="${currentImod}">
	<ul class="errors" role="alert">
		<g:eachError bean="${currentImod}" var="error">
		<li>
			<g:message error="${error}"/>
		</li>
	</g:eachError>
	</ul>
	</g:hasErrors>
	</div>

	<div id="topicDialogBackground" class="modalBackground"></div>


	<!-- Adding the  table layout as in Learning objective page -->
	<table class="learning-objective inner-table">
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
		Learning Domains
	</h3>
	<div>
		<g:checkBox name="myCheckbox" class="select-all" id="selectAlllD" />
		<label>Select All</label>
		<hr style=" width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
		<ul>
			<g:each var="learningDomain" in="${learningDomains}" status="index">
			<li>
				<g:checkBox  name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" checked="${learningDomain.name == selectedDomain.toString()}" />
				<label for="learning-domain-${index}">
					${learningDomain.name}
				</label>

			</li>
		</g:each>
	</ul>
	</div>
	<h3>
		Domain Categories
	</h3>
	<div>
		<g:checkBox name="myCheckbox" class="select-all" id="selectAlldC" />
		<label>Select All</label>

		<hr style=" width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
		<ul>
			<g:each var="domainCategory" in="${domainCategories}" status="index">
			<li>
				<g:checkBox  name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" checked="${domainCategory.name == selectedDomainCategory.toString()}"/>
				<label for="domain-category-${index}">
					${domainCategory.name}
				</label>

			</li>
		</g:each>
	</ul>
	</div>
	</div>
	</td>

	<td class="learning-objective definition-cell">

		<g:if test="${learningObjectives}">
		<div class="topic_addition_widget">
			<div class="pedagogy-page-buttons">
				<br>
				<div>
					<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
					<span id="add-new-technique-button" class="topicButtonGradient">
						<button>
							<i class="fa fa-plus green"></i>

							Add New Event
						</button>
					</span>

					<span id="favorites-button" class="topicButtonGradient">
						<button>
							<i class="fa fa-times"></i>
							Delete Event
						</button>
					</span>

					<span id="favorites-button" class="topicButtonGradient">
						<button>
							<i class="fa fa-pencil-square-o"></i>
							Edit Event
						</button>
					</span>


					<!--
					<span id="favorites-button" class="topicButtonGradient">
					<button>
					<i class="fa fa-star yellow"></i>
					Favorites
				</button>
			</span>
		-->
	</span>
	</div>
	</div>
	</div>


	<%--Dialog box for Add New Technique --%>
	<div class="draggable" id="add-new-technique">
		<%--To render the add new Technique dialog box--%>
		<fieldset class="titleField draggable-handle">
			<div id="editTitle" class="pedagogy-title">
				<b> Add New Event</b>
			</div>
			<span id="errorMessage" class="red"></span>
		</fieldset>
		<g:form controller="pedagogyTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective?.id]" class="no-warn-form">
		<g:hiddenField name="techniqueId" />
		<g:hiddenField name="learningObjective" id="learningObjectiveID" value="${currentLearningObjective?.id}"/>
		<table id="techniqueList">
			<tr>
				<td class="td-label" width="40%">Event Title</td>
				<td class="td-label" width="40%">Assigned</td>
			</tr>


			<tr>
				<td style="width:70%">
					<g:textField name="title" />
				</td>

				<td >
					    <p>Start Date:<input type="txt" id="datetimepicker"></p>

				</td>


				<input type="hidden" name="titlecheck" id="titlecheck" >
			</tr>

			<tr>
				<td class="td-label" width="40%">Learning Objective</td>
				<td class="td-label" width="40%">Due Date</td>
			</tr>


			<tr>
				<td width="60%">
					<g:select class="custom-dropdown" name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
				</td>

				<td style="width:100%">
					<p>End Date:<input type="txt" id="datetimepicker2"></p>
				</td>

				<input type="hidden" name="titlecheck" id="titlecheck" >
			</tr>


			<tr>
				<td class="td-label" width="40%">Knowledge Dimension</td>
				<td class="td-label" width="40%">Task Environment</td>
			</tr>



			<tr>
				<td width="60%">
					<g:select class="custom-dropdown" name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
				</td>

				<td width="60%">
					<g:select class="custom-dropdown" name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
				</td>
			</tr>

			<tr>
				<td class="td-label" width="40%">Type of Activity Dimension</td>
				<td class="td-label" width="40%">Expected Working Time</td>
			</tr>


			<tr>
				<td width="60%">
					<g:select class="custom-dropdown" name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
				</td>

				<td width="60%">
					<g:select class="custom-dropdown" name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
				</td>
			</tr>

			<!--

			<tr>
			<td class="td-label" width="40%">Learning Domain	</td>
			<td width="60%">

			<g:textArea name="reference" rows="5" cols="30"/>
		</td>
	</tr>
	-->

	<tr>
		<td class="td-label" width="40%">Notes
			<br>
			<g:textArea name="activityDescription" rows="7" cols="100" id = "notes2"/></td>

		</tr>
	</table>
	<br>

	<div id="modalButtons" style="align:left">
		<button type="submit"  id="saveButton" class="new-technique-popup-button">
		<!--<button type="submit" name="_action_save" value="Save" id="saveButton" class="new-technique-popup-button"onclick="addTask()">-->
			<i class="fa fa-save green"></i>
			Save
		</button>
		<button type="submit" name="_action_cancel" value="Cancel" class="new-technique-popup-button">
			<i class="fa fa-times red"></i>
			Cancel
		</button>

	</div>
	<br>
	</g:form>
	</div>
	<%--Dialog box for Displaying in edit and View mode Technique --%>
	<div id="display-new-technique" title="Display Technique">
		<%--To render the add new Technique dialog box--%>
		<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" class="no-warn-form" params="[learningObjectiveID: currentLearningObjective?.id]">
			<span class="editviewButtons">
				<label >
					Assign
				</label>
				<g:checkBox class="assignBtn" name="assignedToLearningObjective" />

				<label >
					Favorite
				</label>
				<g:checkBox class="favBtn" name="favoriteTechnique" />

				<input type="button" value="Edit" id="editModal" />
				<input type="button" value="View" id="viewModal" />

			</span>
			<br/>

			<g:hiddenField name="techniqueId1" />
			<p class="form-align">
				<label class="form-labels">
					Title
				</label>
				<g:textField name="title1"  class="allInputs"/>
				<g:textField name="title2"  class="allInputs1"/>
			</p>
			<br/>
			<p class="form-align">
				<label class="form-labels">
					Description
				</label>
				<g:textField name="description1"  class="allInputs"/>
				<g:textField name="description2"  class="allInputs1"/>
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Procedure
				</label>
				<g:textArea name="procedure1"  class="allInputs"/>
				<g:textArea name="procedure2"  class="allInputs1"/>
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Duration
				</label>
				<g:field type="number" name="duration1" min="01" max="60" class="allInputs"/>
				<g:field type="number" name="duration2" min="01" max="60" class="allInputs1"/>
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Feedback Mechanism
				</label>
				<g:select class="custom-dropdown" name="assessmentFeedback1" from="${assessmentFeedback}" optionKey="name" />
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Learning Domain
				</label>
				<g:select class="custom-dropdown" name="learningDomain" from="${learningDomains}" optionKey="name" />
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Domain Category
				</label>
				<g:select class="custom-dropdown" name="domainCategory" from="${domainCategories}" optionKey="name" />
			</p>
			<br/>

			<p class="form-align">
				<label class="form-labels">
					Knowledge Dimension
				</label>
				<g:select class="custom-dropdown" name="knowledgeDimension" from="${knowledgeDimensions}" optionKey="description" />
			</p>
			<br/>

			<div id="modalButtons">
				<g:actionSubmit value="Save" action="save1" />
				<g:actionSubmit value="Cancel" action="cancel" />
			</div>
		</g:form>
	</div>
	</g:if>


	<!--- Calendar starts after here
	<div id='calendar'>
	</div>
	-->


	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1></h1>
				<br />
				<div id="scheduleCalendar"></div>
			</div>
		</div>
	</div>

	<div id="taskContent" title="Task Details" style="display: none;">
		<div id="taskInfo"></div>
		<p>Start:</p>
		<div id="taskTime"></div>
		<p><strong><a id="taskLink" target="_blank">For more info.</a></strong></p>
	</div>



	<script>
	$("#datetimepicker").datetimepicker({
		onSelect: function(dateText, inst) {
			var dateAsString = dateText; //the first parameter of this function
			var dateAsObject = $(this).datetimepicker( 'getDate' ); //the getDate method
			console.log(dateAsString);
			console.log(dateAsObject);
		}
	});

	$("#datetimepicker2").datetimepicker({
		onSelect: function(dateText, inst) {
			var dateAsString = dateText; //the first parameter of this function
			var dateAsObject = $(this).datetimepicker( 'getDate' ); //the getDate method
			console.log(dateAsString);
			console.log(dateAsObject);
		}
	})
	</script>


	<!--- Calendar starts after here
	<div id='calendar'>
	</div>
	-->


	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1></h1>
				<br />
				<div id="scheduleCalendar"></div>
			</div>
		</div>
	</div>

	<div id="taskContent" title="Task Details" style="display: none;">
		<div id="taskInfo"></div>
		<p>Start:</p>
		<div id="taskTime"></div>
		<p><strong><a id="taskLink" target="_blank">For more info.</a></strong></p>
	</div>

	<!-- The chart goes here -->
	<div id="chartContainer" style="height: 300px; width: 97.5%;"></div>

	<!--
	List out the imods course info dates (demo getting data from the controller file)
	-->
	<br>
	<p id="startDate">Start Date Here:${startDate1}</p>
	<p id="endDate">End Date Here:${endDate1}</p>
	<p id="creditHours">Credit Hours Here:${creditHours1}</p>
	<p id="timeRatio">Credit Hours/Time Ratio Here:${timeRatio1}</p>
	<br>

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
