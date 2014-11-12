<html>
	<head>
		<meta name='layout' content='register'/>
		<title>
			<g:message code='spring.security.ui.register.title'/>
		</title>
	</head>
	<body>
		<s2ui:form width='650' height='300' elementId='loginFormContainer'
		titleCode='spring.security.ui.register.description' center='true'>
			<g:form action='register' name='registerForm'>
				<g:if test='${emailSent}'>
					<br/>
					<g:message code='spring.security.ui.register.sent'/>
					<br><br><a id="loginButton" href="/imod/auth" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Back to log in</span></a>
				</g:if>
				<g:else>
					<br/>
					<table>
						<tbody>
							<s2ui:textFieldRow name='username' labelCode='user.username.label' bean="${command}"
							size='40' labelCodeDefault='Username' value="${command.username}"/>
							<s2ui:textFieldRow name='email' bean="${command}" value="${command.email}"
							size='40' labelCode='user.email.label' labelCodeDefault='E-mail'/>
							<s2ui:passwordFieldRow name='password' labelCode='user.password.label' bean="${command}"
							size='40' labelCodeDefault='Password' value="${command.password}"/>
							<s2ui:passwordFieldRow name='password2' labelCode='user.password2.label' bean="${command}"
							size='40' labelCodeDefault='Password (again)' value="${command.password2}"/>
						</tbody>
					</table>
					<p>Password must be at least eight characters long, and have a letter, a number and a special character  !@#$%^&</p>
					<s2ui:submitButton elementId='create' form='registerForm' messageCode='spring.security.ui.register.submit'/>
					<a id="loginButton" href="/imod/auth" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Back to log in</span></a>
				</g:else>
			</g:form>
		</s2ui:form>
		<script>
			$(document).ready(function() {
				$('#username').focus();
			});
		</script>
	</body>
</html>
