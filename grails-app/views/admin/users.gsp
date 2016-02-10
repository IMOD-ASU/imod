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
	</head>
	<body>

		<table class="admin-table users-table users">
			<thead>
				<th>ID</th>
				<th class="email">Email</th>
			    <th class="username">Username</th>
			    <th class="enabled">enabled</th>
			    <th class="expired">accountExpired</th>
			    <th class="locked">accountLocked</th>
			    <th class="expired">passwordExpired</th>
			</thead>
			<tbody>
			<g:each var="user" in="${users}">
				<tr>
					<td class="user-id">${user.id}</td>
					<td class="email">${user.email}</td>
				    <td class="username">${user.username}</td>
				    <td class="enabled">${user.enabled}</td>
				    <td class="expired">${user.accountExpired}</td>
				    <td class="locked">${user.accountLocked}</td>
				    <td class="expired">${user.passwordExpired}</td>
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
