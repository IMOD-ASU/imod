<%@page import="imod.Help" %>
<%@page import="imod.Imod" %>
<%@page import="imod.ImodUser" %>
<%@page import="imod.ScheduleRepeatsEvery"%>
<%@page import="imod.ScheduleWeekDays"%>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Manage Pedagogy Techniques
		</title>
		<meta name="layout" content="imodAdmin" />
		<g:external dir="css/source" file="adminTechniques.css" />
		<g:javascript src="source/adminTechniques.js" defer="defer" />
	</head>
	<body>

		<table class="admin-table technique-table pedagogy">
			<thead>
				<th>ID</th>
				<th class="title">Title</th>
				<th class="learningdomain">Learning <br>Domain</th>
				<th class="domaincategories">Domain <br>Categories</th>
				<th class="kd">Knowledge <br>Dimensions</th>
				<th class="mode">Delivery <br>Mode</th>
				<th class="focus">Focus</th>
				<th class="duration">Duration</th>
			    <th class="description">Description</th>
			    <th class="materials">Materials</th>
			    <th class="reference">Reference</th>
			    <th class="isAdmin">Admin <br>Technique</th>
			    <th class="addedBy">Owned By</th>
			</thead>
			<tbody>
			<g:each var="technique" in="${techniques}">
				<tr>
					<td class="technique-id">${technique.id}</td>
					<td class="title">${technique.title}</td>
					<td class="learningdomain">${technique.learningDomain.join(', ')}</td>
					<td class="domaincategories">${technique.domainCategory.join(', ')}</td>
					<td class="kd">${technique.knowledgeDimension.join(', ')}</td>
					<td class="mode">${technique.pedagogyMode}</td>
					<td class="focus">${technique.activityFocus.join(', ')}</td>
					<td class="duration">${technique.pedagogyDuration}</td>
					<td class="description">${technique.activityDescription}</td>
					<td class="materials">${technique.materials}</td>
					<td class="reference">${technique.reference}</td>
					<td class="isAdmin">
						<form>
							<select data-id="${technique.id}" class="isAdminSelect">
								<option value="true" ${technique.isAdmin ? 'selected' : ''}>Yes</option>
								<option value="false" ${technique.isAdmin ? '' : 'selected'}>No</option>
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

		<ul class="admin-pagination">
			<g:each in="${1..pages}" var="page" >
				<li class="${currentPageNumber == page ? 'active' : ''}"><a href="?page=${page}">${page}</a></li>
			</g:each>
		</ul>

	</body>
</html>
