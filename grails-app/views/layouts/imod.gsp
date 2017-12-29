<%--
	this is the layout for all imod tabs, it has the navigation between:
	- Course Overview
	- Learning Objectives
	- Content
	- Assessments
	- Pedagogy
--%>
<%@ page import="imod.Help" %>
<%@ page import="imod.Imod" %>
<%@ page import="imod.ImodUser" %>


<g:applyLayout name="main">
	<html>
		<head>
			<title>
				<g:layoutTitle default="IMOD" />
			</title>
			<meta name="imod-is-saved" value="${currentImod?.saved}" />

			<g:layoutHead/>
		</head>

		<body>
			<div id="help-placeholder">
				<img id="resize-quicktip" src="${resource(dir: 'images', file: 'i.png')}" style="background:lightyellow" alt=""/>
				<span id="qtip-heading" style="color:white">
					Information
				</span>
				%{--<a id="open-button">--}%
					%{--<img id="active-quicktip" src="${resource(dir: 'images', file: 'tip_active.png')}" alt=""/>--}%
				%{--</a>--}%
				<div id="qtip-place" style="overflow-y:auto; ">

					<g:if test="${controllerName == 'courseOverview'}"><span>The user can enter information such as course title, number, URL, location, semester, schedule, prerequisites, policies, and instructors. </span></g:if>
					<g:if test="${controllerName == 'learningObjective'}"><span>This allows users to create new learning objectives, view or edit existing objectives.<br><br> This tab has four sub-tabs:
					<ol><li><b>Performance sub tab</b>: User enters the learning domain, domain category, action word category and action word of the learning objective</li><li><b>Condition tab</b>: provides a text area for user to enter the condition for a
					learning objective or can choose one of the generic conditions provided</li><li><b>Content sub tab</b>: user can select an existing content topic or add new topics if it does not exist and then link to the learning objective. This tab allows arranging the content topics in a hierarchical ordering of topics and subtopics. </li><li><b>Criteria sub tab</b>: allows the user to select a criteria and criteria type (such as
					accuracy, speed, quality or quantity). </li></ol><br>
						Example:
						Given a Software Requirements Specification (SRS) document of a Capstone project, students should be able to analyze the quality of the Software requirements in the
						document with 95% accuracy.</span></g:if>
					<g:if test="${controllerName == 'content'}">
					<span style="">Content topics and subtopics for the course can be added through this tab. For each topic the knowledge dimension and topic priority is defined. This information is used to find assessments and instructional activities
					that best fit for delivering a topic.<br><br>
						The revised Bloom’s taxonomy introduced an additional dimension called the knowledge dimension that was categorized as follows:
						<ol><li>Factual</li><li>Conceptual</li><li>Procedural</li><li>Metacognitive</li></ol><br>
					The IMODS framework uses a prioritization framework that classifies topics and subtopics of a particular course as one of the following:
					<ol><li>Critical</li><li>Important</li><li>Good to know</li></ol><br>
						Achieving the right mix of the three levels of learning (priorities) is essential to planning a good course.</span>
					</g:if>
					<g:if test="${controllerName == 'assessment'}">Content area for information on assessment technique</g:if>
					<g:if test="${controllerName == 'pedagogy'}">Content area for information on pedagogy technique</g:if>

					</div>

			</div>
			%{--<div id="help-placeholder-2">--}%
				%{--<img id="resize-quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/>--}%
				%{--<a id="open-button">--}%
					%{--<img id="active-quicktip" src="${resource(dir: 'images', file: 'tip_inactive.png')}" alt=""/>--}%
				%{--</a>--}%
			%{--</div>--}%

		<sec:ifLoggedIn>
			<div id="progressbar"><div class="progress-label">Loading...</div></div>
		</sec:ifLoggedIn>
			<div class="message banner">

				<g:if test="${id != "new"}">
					Please enter course overview data followed by learning objectives and content.
				</g:if>
				<g:else>
					Please enter course overview data and save the IMOD to get access to other learning objectives and content.
				</g:else>
				<g:hiddenField name="checkId" value="${id}"></g:hiddenField>
			</div>
			<div id="tabs-container" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul id="main-menu" class="ui-tabs-nav ui-widget-header ui-corner-all">
					<li id="a" class="ui-state-default ui-corner-top ${ currentPage.find(/course overview/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="courseOverview" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'learning_context_icon.png')}" alt=""/>
							<span id="overview-tab-title">
								Course Overview
							</span>
						</g:link>
					</li>
					<g:if test="${id != "new"}">
					<li id="b" class="ui-state-default ui-corner-top ${ currentPage.find(/learning objective/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="learningObjective" action="performance" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'LO_icon.png')}" alt=""/>
							<span id="lo-tab-title">
								Learning Objectives
							</span>
						</g:link>
					</li>
					<li  id="c" class="ui-state-default ui-corner-top ${ currentPage.find(/^content/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="content" action="index" id="${currentImod?.id}" params=" [ ${objectiveId: params.objectiveId} ] " class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'content_icon.png')}" alt=""/>
							<span id="content-tab-title">
								Content
							</span>
						</g:link>
					</li>
					<li id="d" class="ui-state-default ui-corner-top ${ currentPage.find(/assessment/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="assessment" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/>
							<span id="assess-tab-title">
								Assessment
							</span>
						</g:link>
					</li>
					<li  id="e" class="ui-state-default ui-corner-top ${ currentPage.find(/pedagogy/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="pedagogy" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'pedagogy_icon.png')}" alt=""/>
							<span id="pedagogy-tab-title">
								Pedagogy
							</span>
						</g:link>
					</li>

					<li  id="f" class="ui-state-default ui-corner-top ${ currentPage.find(/schedule/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="schedule" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'schedule_icon.png')}" alt=""/>
							<span id="schedule-tab-title">
								Schedule
							</span>
						</g:link>
					</li>

					</g:if>
				</ul>

				<g:layoutBody/>
			</div>
		</body>
	</html>
</g:applyLayout>
