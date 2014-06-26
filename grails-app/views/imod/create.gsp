<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<script src="${resource(dir: 'js', file: 'jquery.qtip-1.0.0-rc3.min.js')}"></script>
		<script src="${resource(dir: 'js', file: 'jquery.treeview.js')}"></script>
		<script src="${resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js')}"></script>
		<script src="${resource(dir: 'js/source', file: 'imod.create.gsp.js')}"></script>
		<link rel="stylesheet" href="${resource(dir: 'css/library', file: 'jquery.treeview.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'imod.create.gsp.css')}" type="text/css">
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body onLoad="myFunc()">
		<a href="#create-imod" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="help_placeholder"  >
			<img id="resize_quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/><span>Quick tip</Span> <a id="closebutton" href="#" onclick="toggleHelp()">_</a>

			<div id="qtipPlace" style="
			float: right;
			text-align: right;
			position: absolute;
			right: 0;"></div>
		</div>
		<div id="open_help" >
			<Span>Quick Tip</Span> 	<a id="closebutton" href="#" onclick="toggleHelp()">_</a>
		</div>
		<div id="tabs-container">
			<div id="tabs">
			  <ul>
				<li><a href="#tabs-1"><img class="tab_icon" src="${resource(dir: 'images', file: 'learning_context_icon.png')}" alt=""/><span id="overview_tab_title">Course Overview</span></a></li>
				<li><a href="#tabs-2"><img class="tab_icon" src="${resource(dir: 'images', file: 'LO_icon.png')}" alt=""/><span id="lo_tab_title">Learning Objectives</span></a></li>
				<li><a href="#tabs-3"><img class="tab_icon" src="${resource(dir: 'images', file: 'content_icon.png')}" alt=""/><span id="content_tab_title">Content</span></a></li>
				<li><a href="#tabs-4"><img class="tab_icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/><span id="assess_tab_title">Assessment</span></a></li>
				<li><a href="#tabs-5"><img class="tab_icon" src="${resource(dir: 'images', file: 'pedagogy_icon.png')}" alt=""/><span id="pedagogy_tab_title">Pedagogy</span></a></li>
			  </ul>
				<div id="tabs-1">
					<div id="create-imod" class="content scaffold-create" role="main">
						<g:if test="${flash.message}">
							<div class="message" role="status">${flash.message}</div>
						</g:if>
						<g:hasErrors bean="${imodInstance}">
							<ul class="errors" role="alert">
								<g:eachError bean="${imodInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
								</g:eachError>
							</ul>
						</g:hasErrors>
						<g:form action="save" >
							<fieldset class="form">
								 <table class="inner_table">
										<tr height="50px">
											<td />
											<td>
												<fieldset class="buttons">
													<g:submitButton name="create"
													class="save showHoverNew"
													title="${Help.toolTip("OVERVIEW", "Save Course Overview")}"
													value="${message(code: 'default.button.create.label', default: 'Create Course Overview')}" />
												</fieldset>
											</td>
										</tr>
										<tr>
											<td>
												<div class="course_overview_form">
													<div class="form_title"><span class="title_text">Course Details</span></div>
													<g:render template="form_course_details"/>
												</div>
											</td>
											<td>
												<div class="course_overview_form">
													<div class="form_title"><span class="title_text">Schedule</span></div>
													<g:render template="form_course_schedule" />
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="course_overview_form">
													<div class="form_title"><span class="title_text">Instructors</span></div>
													<g:render template="form_course_instructors" />
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="course_overview_form">
													<div class="form_title"><span class="title_text">Course Description</span></div>
													<g:render template="form_course_description"/>
												</div>
											</td>
											<td>
												<div class="course_overview_form" id="policy">
													<div class="form_title"><span class="title_text">Course Policy</span></div>
													<div id="accordion">
													<h3>Grading procedure</h3>
													<div><g:textArea name="gradingProcedure" rows="3" cols="30" />
													</div>
													<h3>Attendance and tardiness</h3>
													<div><g:textArea name = "attendance" rows="3" cols="30" />
													</div>
													<h3>Class participation</h3>
													<div><textarea rows="2" cols="30"></textarea>
													</div>
													<h3>Classroom decorum</h3>
													<div><textarea rows="2" cols="30"></textarea>
													</div>
													<h3>Missed exams/Make-up exams</h3>
													<div><textarea rows="2" cols="30"></textarea>
													</div>
													<h3>Missed Assignments</h3>
													<div><textarea rows="2" cols="30"></textarea>
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
				<div id="tabs-2">
					<g:render template="/learningObjective/main" />
				</div>
				<div id="tabs-3">
				</div>
				<div id="tabs-4">
					<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
				</div>
				<div id="tabs-5">
					<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
				</div>
			</div>
		</div>
	</body>
</html>
