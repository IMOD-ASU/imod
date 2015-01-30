<!DOCTYPE html>
<%--
this is the basic layout with only the IMOD header and footer
--%>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
	<head>
		<title>
			<g:layoutTitle default="IMOD"/>
		</title>

		<meta charset="utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}" type="image/x-icon" />
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}" />
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}" sizes="114x114" />

		<!-- style libraries provided by content delivery network -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/ui-lightness/jquery-ui.css" />
		<link rel="stylesheet" href="//cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.css">
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/themes/default/style.min.css" />
		<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Ubuntu:regular,bold&amp;subset=Latin" />
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />

		<!-- internal styling -->
		<g:external dir="css" file="main.css" />

		<!-- javascript libraries provided by content delivery network -->
		<script src="//code.jquery.com/jquery-2.1.1.min.js" defer></script>
		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.min.js" defer></script>
		<script src="//cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.js" defer></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/jstree.min.js" defer></script>

		<!-- internal javascript -->
		<g:javascript src="source/application.js" defer="defer" />

		<g:layoutHead/>
	</head>

	<body>
		<div id="imodlogo" role="banner" style="background-color:#0C1625;">
			<a href="${createLink(uri: '/')}">
				<g:img dir="images" file="colorsignature_sml.png" id="imod-logo" alt="imod"/>
			</a>
			<span>
				<a class="banner-imod" href="${createLink(uri: '/imod')}">
					<g:message code="My i-mods"/>
				</a>
				<a class="banner-home" href="${createLink(uri: '/')}">
					<g:img dir="images" file="home_sm.png" class="resize-home"/>
					<g:message code="default.home.label"/>
				</a>
				<nobr>
					<div id="login-link-container" class="banner-link">
						<sec:ifLoggedIn>
						<g:img dir="images" file="home_sm.png" class="resize-home"/>
							<g:img dir="images" file="user_sm.png" class="resize-home"/>
							<sec:username/>
							<a href='${createLink(uri: '/logout')}' id='logout-link' class='banner-link'>
								Logout
							</a>
						</sec:ifLoggedIn>

						<sec:ifNotLoggedIn>
							<a href="${createLink(uri: '/login')}" id="loginLink" class="banner-link">
								Login
							</a>
						</sec:ifNotLoggedIn>

						<sec:ifSwitched>
							<a href="${request.contextPath}/j_spring_security_exit_user">
						  		Resume as
						  	</a>
							<sec:switchedUserOriginalUsername/>
					  	</sec:ifSwitched>
					</div>
				</nobr>
			</span>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo">
			Copyright &copy; 2015 IMOD&trade;
		</div>

		<g:if env="development">
			<!-- automated html style checking -->
			<script src="https://rawgit.com/yaniswang/HTMLHint/master/lib/htmlhint.js"></script>
			<g:javascript src="source/lintHTML.js" />
		</g:if>
	</body>
</html>
