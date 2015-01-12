<html>
	<head>
		<title>
			Add Instructor
		</title>
		<meta name="layout" content="main">
	</head>
	<body>
		<formset>
			<g:form action="create">
				<label for="firstName">
					First Name
				</label>
				<g:textField name="firstName" />
				<br />
				<br />

				<label for="lastName">
					Last Name
				</label>
				<g:textField name="lastName" />
				<br />
				<br />

				<label for="email">
					Email
				</label>
				<g:textField name="email" />
				<br />
				<br />

				<label for="officeHours">
					OfficeHours
				</label>
				<g:textField name="officeHours" />
				<br />
				<br />

				<label for="webPage">
					webPage
				</label>
				<g:textField name="webPage" />
				<br />
				<br />

				<label for="role">
					Role
				</label>
				<g:textField name="role" />
				<br />
				<br />

				<label for="location">
					Location
				</label>
				<g:textField name="location" />
				<br />
				<br />

				<g:submitButton name="add_instructor" value="Add Instructor" />
			</g:form>
		</formset>
	</body>
</html>
