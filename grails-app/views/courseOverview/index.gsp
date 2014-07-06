<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			Course Overview
		</title>
		<meta name="layout" content="imod">
	</head>
	<body>
		<div id="tabs-1">
			<div id="edit-imod" class="content scaffold-edit" role="main">
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${imodInstance}">
					<ul class="errors" role="alert">
						<g:eachError bean="${imodInstance}" var="error">
							<li
								<g:if test="${error in org.springframework.validation.FieldError}">
									data-field-id="${error.field}"
								</g:if>
							>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</g:hasErrors>
				<g:form method="post">
					<g:hiddenField name="id" value="${imodInstance?.id}"/>
					<g:hiddenField name="version" value="${imodInstance?.version}"/>
					<fieldset class="form">
						<table class="inner_table">
							<tr height="50px">
								<td>
								</td>
								<td>
									<fieldset class="buttons">
										<g:actionSubmit class="save showHoverNew" action="update" title="${Help.toolTip("OVERVIEW", "Save Course Overview")}" value="${message(code: 'Save', default: 'Save')}"/>
										<g:actionSubmit class="delete showHoverNew" action="delete" title="${Help.toolTip("OVERVIEW", "Delete Course Overview")}" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<div class="course_overview_form">
										<div class="form_title">
											<span class="title_text">
												Course Details
											</span>
										</div>
										<g:render template="form_course_details"/>
									</div>
								</td>
								<td>
									<div class="course_overview_form">
										<div class="form_title">
											<span class="title_text">
												Schedule
											</span>
										</div>
										<g:render template="form_course_schedule"/>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="course_overview_form">
										<div class="form_title">
											<span class="title_text">
												Instructors
											</span>
										</div>
										<g:render template="form_course_instructors"/>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="course_overview_form">
										<div class="form_title">
											<span class="title_text">
												Course Description
											</span>
										</div>
										<g:render template="form_course_description"/>
									</div>
								</td>
								<td>
									<div class="course_overview_form" id="policy">
										<div class="form_title">
											<span class="title_text">
												Course Policy
											</span>
										</div>
										<div id="accordion">
											<h3>
												Grading procedure
											</h3>
											<div>
												<textarea name="gradingProcedure" rows="3" cols="30">
												</textarea>
											</div>
											<h3>
												Attendance and tardiness
											</h3>
											<div>
												<textarea name="attendance" rows="3" cols="30">
												</textarea>
											</div>
											<h3>
												Class participation
											</h3>
											<div>
												<textarea rows="2" cols="30">
												</textarea>
											</div>
											<h3>
												Classroom decorum
											</h3>
											<div>
												<textarea rows="2" cols="30">
												</textarea>
											</div>
											<h3>
												Missed exams/Make-up exams
											</h3>
											<div>
												<textarea rows="2" cols="30">
												</textarea>
											</div>
											<h3>
												Missed Assignments
											</h3>
											<div>
												<textarea rows="2" cols="30">
												</textarea>
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
	</body>
</html>

