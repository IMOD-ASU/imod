<html>
    <head>
        <title>
            <g:message code='spring.security.ui.resetPassword.title' />
        </title>
        <meta name='layout' content='register' />
    </head>

    <body>
        <s2ui:form width='475' height='250' elementId='resetPasswordFormContainer' titleCode='spring.security.ui.resetPassword.header' center='true'>
        	<g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>
            	<g:hiddenField name='t' value='${token}' />
            	<div class="sign-in">
                	<br />
                	<h4>
                        <g:message code='spring.security.ui.resetPassword.description' />
                    </h4>
                	<table>
                		<s2ui:passwordFieldRow name='password' labelCode='resetPasswordCommand.password.label' bean="${command}" labelCodeDefault='Password' value="${command?.password}" />
                		<s2ui:passwordFieldRow name='password2' labelCode='resetPasswordCommand.password2.label' bean="${command}" labelCodeDefault='Password (again)' value="${command?.password2}" />
                	</table>

                	<s2ui:submitButton elementId='reset' form='resetPasswordForm' messageCode='spring.security.ui.resetPassword.submit' />
                	<a id="loginButton" href="auth" class="login-back ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
                        <span class="ui-button-text">
                            Back to log in
                        </span>
                    </a>
            	</div>
            </g:form>
        </s2ui:form>
        <script>
            $(document).ready(function() {
            	$('#password').focus();
            	$('.login-back').prop('href',window.location.pathname.match(/\/[^\/]+\//)[0]+'login/auth');
            });
        </script>
    </body>
</html>
