<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<script type="text/javascript" src="${resource(dir: 'js/library', file: 'jquery.qtip-1.0.0-rc3.min.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/library', file: 'jquery.treeview.js')}"></script>
		<link rel="stylesheet" href="${resource(dir: 'css/library', file: 'jquery.treeview.css')}" type="text/css">
		<style type="text/css" media="screen">
			.chapterLi {
				list-style-type: none;
			}

			.chapterLi:hover {
				background-color: #d3d3d3;
			}

			#learningObjectiveType, #contentSelect {
				padding: 0;
			}

			#priorityCodeType {
				padding: 0;
				width: 215px;
			}

			#tabs-container {
				margin-left:2.5%;
				margin-right:17.5%;
				margin-top:5%;
				margin-bottom:10.5%;
				font-size:12px;
			}

			.ui-tabs-anchor {
				font-family: "Ubuntu";
			}

			.buttons {
				width:20%;
				position:absolute;
				right:0;
			}

			.ui-widget-content {
				font-family:"Ubuntu";
			}

			#tabs-2{
				margin-bottom:23.5%;
			}

			#tabs-3{
				margin-bottom:18.74%;
			}

			#tabs-4{
				margin-bottom:23.5%;
			}

			#tabs-5{
				margin-bottom:25.1%;
			}

			table.inner_table{
				position:relative;
				left:-62.5px;
				width:113%;
				height:100%;
			}

			td{
			}

			.buttons{
				width:auto;
				top:0px;
				right:25px;
			}

			.course_overview_form,.topic_hierarchy_widget,.topic_information_widget{
				padding: 0px 0px 20px 0px;
				border: solid 1px #C0C0C0;
				border-radius: 15px;
				height:100%;
			}

			.form_title{
				background: #0A1629;
				top:0;
				color: #ffffff;
				line-height:250%;
				background: -webkit-gradient(linear, left top, left bottom, from(#1B4176), to(#172751));
				border-top-left-radius: 14px 14px;
				border-top-right-radius: 14px 14px;
			}

			.fieldcontain label, .fieldcontain .property-label {
				text-align: left;
				margin-left: 40px;
			}

			.fieldcontain{
				padding:0px 0px 0px 15px;
				margin-right:-5em;
			}

			.title_text{
				padding-left:10px;
			}

			img.tab_icon{
					width:20%;
					height:auto;
			}

			a.ui-tabs-anchor{
				width:181.953px;
			}

			#overview_tab_title{
				position:absolute;
				top:10px;
			}

			#lo_tab_title{
				position:absolute;
				top:10px;
			}

			#content_tab_title{
				position:absolute;
				top:10px;
				left:80px;
			}

			#assess_tab_title{
				position:absolute;
				top:10px;
				left:80px;
			}

			#pedagogy_tab_title{
				position:absolute;
				top:10px;
				left:70px;
			}

			.ui-tabs-nav li{
				height:43px;
				border-top-left-radius:5px 5px;
				border-top-right-radius:5px;
			}

			#help_placeholder {
				width:12em;
				height: 9em;
				position: absolute;
				right: 15px;
				top: 144px;
				border-width: 1px;
				padding-right: 1em;
				padding-bottom: 3em;
				padding-left: 5px;
				padding-top: 5px;
				background-color: #FFFF5A;
				z-index:10;
			}

			#open_help {
				width:12em;
				height: 1em;
				position: absolute;
				right: 15px;
				top: 144px;
				border-width: 1px;
				padding-right: 1em;
				padding-bottom: 10px;
				padding-left: 5px;
				padding-top: 5px;
				background-color: #FFFF5A;
				z-index: 10;
				display: none;
			}

			#closebutton{
				float: right;
			}

			#resize_quicktip{
				width:20px;
				height:auto;
				position:relative;
				top:5px;
			}

			.fieldcontain label, .fieldcontain .property-label{
				text-align:left;
				margin-left:40px;
			}

			.fieldcontain{
				margin-right:0em;
			}

			#custom_instructor{
				height:127px;
				overflow-y:scroll;
			}

			#instructor_table{
				width:858px;
			}

			#audience {
				min-width: 153px;
			}
		</style>
		<r:require module="jqueryui" />
		<script type="text/javascript">
			jQuery(document).ready(function () {
				jQuery.browser = {};
				(function () {
					jQuery.browser.msie = false;
					jQuery.browser.version = 0;
					if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
						jQuery.browser.msie = true;
						jQuery.browser.version = RegExp.$1;
					}
				})();
				jQuery('.showHoverNew').qtip({
					style: {background: 'transparent', border: 'none', color: 'black'},
					show: 'mouseover',
					hide: 'mouseout',
					position: {
						type: 'static',
						container: $('div#qtipPlace')
					}
				});
				var showHelp="${ImodUser.get(currentUser).preferences.showHelp}";
				if (showHelp!="true"){
					$("#help_placeholder").css("display","none")
				}
			});
		</script>
		<script type="text/javascript">
			function updateDatePicker () {
				$("input[value='date.struct']:hidden").each(function() {
					var dateFormat = "${ImodUser.get(currentUser).preferences.dateRepresentation}";
					var name = $(this).attr('name');
					var id = name.replace(".", "_").replace("[", "_").replace("]", "_") + "_input"; // Create JQuery Friendly ID

					if ($('#'+id).length == 0) {

						// Find the Select Elements
						var selectDay= $(this).nextAll("select:eq(0)").hide();
						var selectMonth = $(this).nextAll("select:eq(1)").hide();
						var selectYear = $(this).nextAll("select:eq(2)").hide();

						// Get the Values
						var dateDay= $(selectDay).val();
						var dateMonth = $(selectMonth).val();
						var dateYear = $(selectYear).val();

						// Calculate the Current Input Value
						var val = "";
						if (dateDay != "" && dateYear != "" && dateMonth != "") { // If there is a date in the Selects then use it otherwise it's empty
							var date = new Date (dateYear, dateMonth-1, dateDay);
							val = $.datepicker.formatDate(dateFormat, date);
						}

						// Create element
						var template = "<input type='text' name='"+ id +"' id='"+ id +"' value='"+ val +"'/>";

						if ($(this).parent(".datePickerCalenderView").size()) {
							template = "<div id='"+ id +"'/>";
						}


						$(this).before(template);
						var displayWidget = $('#' + id );

						displayWidget.blur(function() {
							var date = $.datepicker.parseDate(dateFormat, $(this).val());

							if (date == null) {
								$(selectDay).val("");
								$(selectMonth).val("");
								$(selectYear).val("");
							}
							else {
								$(selectDay).val(date.getDate());
								$(selectMonth).val(date.getMonth()+1);
								$(selectYear).val(date.getFullYear());
							}
						}).keydown(function(event) {
							// Show popup on Down Arrow
							if (event.keyCode == 40) {
								displayWidget.datepicker("show");
							}
						});

						displayWidget.datepicker({
							changeMonth: true,
							changeYear: true,
							dateFormat: dateFormat,
							constrainInput: true,
							showButtonPanel: true,
							showWeeks: true,
							showOn: 'button',
							onSelect: function(dateText, inst) {
								if (inst == null) {
									$(selectDay).val("");
									$(selectMonth).val("");
									$(selectYear).val("");
								}
								else {
									$(selectDay).val(inst.selectedDay);
									$(selectMonth).val(inst.selectedMonth+1);
									$(selectYear).val(inst.selectedYear);
								}
							}
						});
					}
				});
			}

			$(document).ready(function() {
				document.getElementById("repeats").removeAttribute("multiple")
				document.getElementById("repeatsEvery").removeAttribute("multiple")
				$("#tabs").tabs({
					ajaxOptions: {
						error: function (xhr, status, index, anchor) {
							$(anchor.hash).html('errorLoadingTabMessage');
						}
					}
				});
				$("#accordion").accordion();
				updateDatePicker();
				$("#spinner").ajaxComplete (function(event, request, settings){
					updateDatePicker();
				});

			});

		</script>
		<script type="text/javascript">
			$(function() {
				$( "#help_placeholder" ).draggable();
				$("#open_help").draggable();
			});
		</script>
		<script type="text/javascript">
			function toggleHelp(){
				var helpbox=$("#help_placeholder")
				var openbox=$("#open_help")
				if (helpbox.css("display")=="block"){
					boxtop=helpbox.css("top")
					boxleft=helpbox.css("left")
					helpbox.css("display","none")
					openbox.css("display","block")
					openbox.css("top",boxtop)
					openbox.css("left",boxleft)
				}
				else {
					boxtop=openbox.css("top")
					boxleft=openbox.css("left")
					helpbox.css("display","block")
					openbox.css("display","none")
					helpbox.css("top",boxtop)
					helpbox.css("left",boxleft)
				}

			}
		</script>
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
					<g:render template="/learningObjective/learning_objectives_main" />
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
