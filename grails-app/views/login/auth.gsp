<html>

<head>
<title><g:message code='spring.security.ui.login.title'/></title>
<meta name='layout' content='register'/>
<style>
	.message {
		color: red;
	}
</style>
</head>

<body>

<p/>

<div class="login s2ui_center ui-corner-all" style='text-align:center;width:320px;'>
	<div class="login-inner">
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
	<div class="sign-in">

	<h1><g:message code='spring.security.ui.login.signin'/></h1>

	<table>
		<tr>
			<td><label for="username"><g:message code='spring.security.ui.login.username'/></label></td>
			<td><input name="j_username" id="username" size="20" /></td>
		</tr>
		<tr>
			<td><label for="password"><g:message code='spring.security.ui.login.password'/></label></td>
			<td><input type="password" name="j_password" id="password" size="20" /></td>
		</tr>
		<tr>
			<td colspan='2'>
				<input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
				<label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label> |
				<span class="forgot-link">
					<g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<s2ui:linkButton  elementId='register' controller='register' messageCode='spring.security.ui.login.register'/>
				<s2ui:submitButton elementId='loginButton' form='loginForm' messageCode='spring.security.ui.login.login'/>
			</td>
		</tr>
	</table>

	<g:if test="${flash.message == 'Sorry, we were not able to find a user with that username and password.'}">
		<div class="message" role="status">
			Invalid credentials. We were not able to find a user with that username and password.
		</div>
	</g:if>

	<g:if test="${flash.message == 'Sorry, your account is locked.'}">
		<div class="message" role="status">
			Please verify your registration by clicking on the link sent to your email address.
		</div>
	</g:if>

	</div>
	</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('body').css("font-family","Ubuntu");
	$('body').css("font-weight","boldest");
	$('h1').css("font-size","20px");
	$('#username').focus();
	$('#loginButton').css("background","#0a1629");
	$('#loginButton').css("color","white");
	$('#loginButton').css("font-family","Ubuntu");
	$('#loginButton').css("font-weight","bolder");
	$('#register').css("background","#0a1629");
	$('#register').css("color","white");
	$('#register').css("font-family","Ubuntu");
	$('#register').css("font-weight","bolder");
	$('h1').css("color","#0a1629");
	$('td').css("font-size","15px");

});

<s2ui:initCheckboxes/>

</script>

</body>
</html>
