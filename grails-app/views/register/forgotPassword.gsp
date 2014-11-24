<html>

<head>
<title><g:message code='spring.security.ui.forgotPassword.title'/></title>
<meta name='layout' content='register'/>
</head>

<body>

<p/>

<s2ui:form width='400' height='220' elementId='forgotPasswordFormContainer'
           titleCode='spring.security.ui.forgotPassword.header' center='true'>

	<g:form action='forgotPassword' name="forgotPasswordForm" autocomplete='off'>

	<g:if test='${emailSent}'>
	<br/>
	<g:message code='spring.security.ui.forgotPassword.sent'/>
	<br><br><a id="loginButton" href="auth" class="login-back ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Back to log in</span></a>
	</g:if>


	<g:else>

	<br/>
	<h4><g:message code='spring.security.ui.forgotPassword.description'/></h4>

	<table>
		<tr>
			<td><label for="username"><g:message code='spring.security.ui.forgotPassword.username'/></label></td>
			<td><g:textField name="username" size="25" /></td>
		</tr>
	</table>

	<s2ui:submitButton elementId='reset' form='forgotPasswordForm' messageCode='spring.security.ui.forgotPassword.submit'/>
	<a id="loginButton" href="auth" class="login-back ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Back to log in</span></a>

	</g:else>

	</g:form>

</s2ui:form>

<script>
$(document).ready(function() {
	$('#username').focus();
	$('.login-back').prop('href',window.location.pathname.match(/\/[^\/]+\//)[0]+'login/auth');
});
</script>

</body>
</html>
