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

		<div class="password-form">

			<g:form class="form-settings update-settings" name="updateSettings" url="[action:'updateSettings',controller:'settings']">

				<g:if test="${flash.error}">
				  <div class="alert alert-error">${flash.error}</div>
				</g:if>
				<g:if test="${flash.success}">
				  <div class="alert alert-success">${flash.success}</div>
				</g:if>

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

				<hr>

			</g:form>

		</div>

		<div class="details-form">
			<g:form class="form-settings details-settings" name="updateDetails" url="[action:'updateDetails',controller:'settings']">

				<g:if test="${flash.detailsError}">
				  <div class="alert alert-error">${flash.detailsError}</div>
				</g:if>
				<g:if test="${flash.detailsSuccess}">
				  <div class="alert alert-success">${flash.detailsSuccess}</div>
				</g:if>

				<h3>Change Personal Information:</h3>
				<div>
					<label>Email:</label>
					<span>${currentUser.email}</span>
				</div>

				<div>
					<label>Username:</label>
					<span>${currentUser.username}</span>
				</div>

				<div>
					<label>First Name:</label>
					<g:textField name="firstName" id="firstName" value="${currentUser.firstName}" />
				</div>

				<div>
					<label>Last Name:</label>
					<g:textField name="lastName" id="lastName" value="${currentUser.lastName}" />
				</div>

				<div>
					<label>Location:</label>
					<g:textField name="location" id="location" value="${currentUser.location}" />
				</div>

				<div>
					<label>Office Hours:</label>
					<g:textField name="officeHours" id="officeHours" value="${currentUser.officeHours}" />
				</div>

				<div>
					<label>Web Page:</label>
					<g:textField name="webPage" id="webPage" value="${currentUser.webPage}" />
				</div>

				<div>
					<label>Phone Number:</label>
					<g:textField name="phoneNumber" id="phoneNumber" value="${currentUser.phoneNumber}" />
				</div>

				<div>
					<label>Role:</label>

					<select name="role" id="role" class="custom-dropdown role">
                        <option value="">Select Role</option>

                        <g:each var="role" in="${roles}">
						    <option ${currentUser.role == role ? 'selected' : ''}>${role}</option>
						</g:each>

                    </select>
				</div>


				<span class="topicButtonGradient">
					<button type="submit">
						<i class="fa fa-save blue"></i> Submit
					</button>
				</span>

				<hr>

			</g:form>
		</div>


	</body>
</html>
