<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Manage Users
		</title>
		<meta name="layout" content="imodAdmin" />
		<g:external dir="css/source" file="adminTechniques.css" />
		<g:javascript src="source/adminTechniques.js" defer="defer" />
	</head>
	<body>

		<table class="admin-table users-table users">
			<thead>
				<th>ID</th>
				<th class="email">Email</th>
			    <th class="username">Username</th>
			    <th class="firstName">First Name</th>
                <th class="lastName">Last Name</th>
                <th class="location">Location</th>
                <th class="officeHours">Office Hours</th>
                <th class="webPage">Web Page</th>
                <th class="phoneNumber">Phone Number</th>
                <th class="role">Role</th>
			    <th class="enabled">Enabled</th>
			    <th class="accountExpired">Account Expired</th>
			    <th class="accountLocked">Account Locked</th>
			    <th class="passwordExpired">Password Expired</th>
			    <th>Save Changes</th>
			</thead>
			<tbody>
			<g:each var="user" in="${users}">
				<tr class="user-row">
					<td class="user-id">${user.id}
						<input type="hidden" name="id" value="${user.id}"></input>
					</td>
					<td class="email">${user.email}</td>
				    <td class="username">${user.username}</td>
				    <td class="firstName">${user.firstName}</td>
                    <td class="lastName">${user.lastName}</td>
                    <td class="location">${user.location}</td>
                    <td class="officeHours">${user.officeHours}</td>
                    <td class="webPage">${user.webPage}</td>
                    <td class="phoneNumber">${user.phoneNumber}</td>
                    <td class="role">${user.role}</td>
				    <td class="enabled">
				    	<select name="enabled" class="enabled">
				    		<option ${user.enabled ? 'selected class="last_selected"' : ''}>true</option>
				    		<option ${user.enabled ? '' : 'selected class="last_selected"' }>false</option>
				    	</select>
				    </td>
				    <td class="accountExpired">
				    	<select name="accountExpired" class="accountExpired">
				    		<option ${user.accountExpired ? 'selected class="last_selected"' : ''}>true</option>
				    		<option ${user.accountExpired ? '' : 'selected class="last_selected"' }>false</option>
				    	</select>
				    </td>
				    <td class="accountLocked">
				    	<select name="accountLocked" class="accountLocked">
				    		<option ${user.accountLocked ? 'selected class="last_selected"' : ''}>true</option>
				    		<option ${user.accountLocked ? '' : 'selected class="last_selected"' }>false</option>
				    	</select>
				    </td>
				    <td class="passwordExpired">
				    	<select name="passwordExpired" class="passwordExpired">
				    		<option ${user.passwordExpired ? 'selected class="last_selected"' : ''}>true</option>
				    		<option ${user.passwordExpired ? '' : 'selected class="last_selected"' }>false</option>
				    	</select>
				    </td>
				    <td class="save">
				    	<a href="#" class="save-btn topicButtonGradient"><i class="fa fa-save green"></i> Save Changes</a>
				    </td>
				</tr>
			</g:each>
			</tbody>
		</table>

		<ul class="admin-pagination user-pagination">
			<g:each in="${1..pages}" var="page" >
				<li class="${currentPageNumber == page ? 'active' : ''}"><a href="?page=${page}">${page}</a></li>
			</g:each>
		</ul>

	</body>
</html>
