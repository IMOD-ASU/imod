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

		<!-- style libraries -->
		<g:external dir="bower_components/jquery.ui/themes/base" file="all.css" />
		<g:external dir="bower_components/qtip2" file="jquery.qtip.min.css" />
		<g:external dir="bower_components/jstree/dist/themes/default" file="style.min.css" />
		<g:external dir="bower_components/fontawesome/css" file="font-awesome.min.css" />
		<g:external dir="bower_components/ubuntu-fontface" file="ubuntu.css" />

		<!-- internal styling -->
		<g:external dir="css" file="main.css" />

		<!-- core javascript libraries -->
		<g:external dir="bower_components/jquery/dist" file="jquery.min.js" defer="defer" />
		<g:external dir="bower_components/jquery.ui/ui" file="core.js" defer="defer" />
		<g:external dir="bower_components/jquery.ui/ui" file="widget.js" defer="defer" />

		<!-- ui widgets -->
		<g:external dir="bower_components/jquery.ui/ui" file="accordion.js" defer="defer" />
		<g:external dir="bower_components/jquery.ui/ui" file="button.js" defer="defer" />
		<g:external dir="bower_components/jquery.ui/ui" file="dialog.js" defer="defer" />

		<!-- plugins to javascript libraries -->
		<g:external dir="bower_components/qtip2" file="jquery.qtip.min.js" defer="defer" />
		<g:external dir="bower_components/jstree/dist" file="jstree.min.js" defer="defer" />
		<g:external dir="bower_components/jquery.validate/dist" file="jquery.validate.min.js" defer="defer" />
		<g:external dir="bower_components/jquery.maskedinput/dist" file="jquery.maskedinput.min.js" defer="defer" />

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
			<g:external dir="bower_components/log" file="log.min.js" defer="defer" />
			<g:external dir="bower_components/htmlhint/lib" file="htmlhint.js" defer="defer" />
			<g:javascript src="source/lintHTML.js" defer="defer" />
		</g:if>

	</body>
</html>
