<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Manage Assessment Techniques
		</title>
		<meta name="layout" content="imodAdmin" />
		<g:external dir="css/source" file="adminTechniques.css" />
	</head>
	<body>

		<table class="technique-table">
			<thead>
				<th>Order</th>
				<th class="title">Title</th>
			    <th class="description">Description</th>
			    <th class="procedure">Procedure</th>
			    <th class="duration">Duration</th>
			    <th class="difficulty">Difficulty</th>
			    <th class="type">Type</th>
			    <th class="whenToCarryOut">When</th>
			    <th class="reference">Reference</th>
			    <th class="isAdmin">isAdmin</th>
			    <th class="addedBy">Owned By</th>
			</thead>
			<tbody>
			<g:each var="technique" in="${techniques}">
				<tr>
					<td>${technique.id}</td>
					<td class="title">${technique.title}</td>
					<td class="description">${technique.description}</td>
					<td class="procedure">${technique.procedure}</td>
					<td class="duration">${technique.duration}</td>
					<td class="difficulty">${technique.difficulty}</td>
					<td class="type">${technique.type}</td>
					<td class="whenToCarryOut">${technique.whenToCarryOut}</td>
					<td class="reference">${technique.reference}</td>
					<td class="isAdmin">
						<form>
							<select>
								<option value="true" ${technique.isAdmin ? 'selected' : ''}>True</option>
								<option value="false" ${technique.isAdmin ? '' : 'selected'}>False</option>
							</select>
						</form>
					</td>
					<td class="addedBy">
						<g:each var="user" in="${technique.users}">
							E: ${user.email} <br>
							U: ${user.username}
						</g:each>
					</td>
				</tr>
			</g:each>
			</tbody>
		</table>

		<ul class="technique-pagination">
			<g:each in="${1..pages}" var="page" >
				<li class="${currentPageNumber == page ? 'active' : ''}"><a href="?page=${page}">${page}</a></li>
			</g:each>
		</ul>

	</body>
</html>
