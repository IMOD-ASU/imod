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

			<form class="update-settings" action="update-settings">

				<h3>Personal Information:</h3>
				<div>
					<label>Email:</label>
					<span>${currentUser.email}</span>
				</div>

				<hr>

				<h3>Change Password:</h3>
				<div>
					<label>Current Password:</label>
					<g:textField name="currentPassword" id="currentPassword"/>
				</div>

				<div>
					<label>New Password:</label>
					<g:textField name="newPassword" id="newPassword"/>
				</div>

				<div>
					<label>Re-enter Password:</label>
					<g:textField name="reenterPassword" id="reenterPassword"/>
				</div>

				<span class="topicButtonGradient">
					<button type="submit">
						<i class="fa fa-save blue"></i> Submit
					</button>
				</span>

			</form>

		</div>


	</body>
</html>
