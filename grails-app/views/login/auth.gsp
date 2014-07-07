<html>
	<head>
		<meta name='layout' content='main'/>
		<title>
			<g:message code="springSecurity.login.title"/>
		</title>
	</head>

	<body>
		<div class="container">
			<div class='page-header'>
				<h1>
					<g:message code="springSecurity.login.header"/>
				</h1>
			</div>

			<g:if test='${flash.message}'>
				<div class='login_message'>
					${flash.message}
				</div>
			</g:if>

			<form action='${postUrl}' method='POST' autocomplete='off' role="form">
				<div class="form-group">
					<label for='username'>
						<g:message code="springSecurity.login.username.label"/>:
					</label>
					<input class="form-control" type='text' name='j_username' id='username'/>
				</div>

				<div class="form-group">
					<label for='password'>
						<g:message code="springSecurity.login.password.label"/>:
					</label>
					<input class="form-control" type='password' name='j_password' id='password'/>
				</div>

				<div class="form-group">
					<div class="checkbox">
						<label>
							<input type="checkbox" name="${rememberMeParameter}" <g:if test='${hasCookie}'>checked="checked"</g:if>>
							<g:message code="springSecurity.login.remember.me.label"/>
						</label>
					</div>
				</div>

				<div class="form-group">
					<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
					<a id = 'register' href="${createLink(uri: '/register')}">
						Register
					</a>
				</div>
			</form>
		</div>
	</body>
</html>
