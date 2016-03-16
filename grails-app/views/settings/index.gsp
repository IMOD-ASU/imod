<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Welcome <sec:username/> - Manage User Settings
		</title>
		<meta name="layout" content="imod-notabs" />
        <g:external dir="css/source" file="settings.css" />
        <g:javascript src="source/settings.js" defer="defer" />
	</head>
	<body>

		<div class="settings-form">

			<g:form class="update-settings" name="updateSettings" url="[action:'updateSettings',controller:'settings']">

				<g:if test="${flash.error}">
				  <div class="alert alert-error">${flash.error}</div>
				</g:if>
				<g:if test="${flash.success}">
				  <div class="alert alert-success">${flash.success}</div>
				</g:if>

				<h3>Personal Information:</h3>
				<div>
					<label>Email:</label>
					<span>${currentUser.email}</span>
				</div>

				<div>
					<label>Username:</label>
					<span>${currentUser.username}</span>
				</div>

				<hr>

				<h3>Change Password:</h3>
				<div>
					<label>Current Password:</label>
					<g:passwordField name="currentPassword" id="currentPassword"/>
				</div>

				<div>
					<label>New Password:</label>
					<g:passwordField name="newPassword" id="newPassword"/>
				</div>

				<div>
					<label>Re-enter Password:</label>
					<g:passwordField name="reenterPassword" id="reenterPassword"/>
				</div>

				<span class="topicButtonGradient">
					<button type="submit">
						<i class="fa fa-save blue"></i> Submit
					</button>
				</span>

			</g:form>

		</div>


	</body>
</html>
