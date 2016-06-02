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
		<g:external dir="bower_components/tooltipster/css" file="tooltipster.css" />
		<g:external dir="bower_components/tooltipster/css/themes" file="tooltipster-noir.css" />

		<!-- internal styling -->
		<g:external dir="css" file="main.css" />

		<!-- core javascript libraries -->
		<!-- <g:external dir="bower_components/jquery/dist" file="jquery.min.js" /> @Wesley.Coomber@asu.edu change for temporary working on my branch. Calender plugin doesn work with jquery 3.0!-->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.js"></script>
		<g:external dir="bower_components/jquery.ui/ui" file="core.js" />
		<g:external dir="bower_components/jquery.ui/ui" file="widget.js" />

		<!-- ui widgets -->
		<g:external dir="bower_components/jquery.ui/ui" file="accordion.js" />
		<g:external dir="bower_components/jquery.ui/ui" file="button.js" />
		<g:external dir="bower_components/jquery.ui/ui" file="dialog.js" />
		<g:external dir="bower_components/jquery.ui/ui" file="progressbar.js" />

		<!-- plugins to javascript libraries -->
		<g:external dir="bower_components/qtip2" file="jquery.qtip.min.js" />
		<g:external dir="bower_components/chart.js" file="Chart.js" />
		<g:external dir="bower_components/jstree/dist" file="jstree.min.js" />
		<g:external dir="bower_components/jquery.validate/dist" file="jquery.validate.min.js" />
		<g:external dir="bower_components/jquery.maskedinput/dist" file="jquery.maskedinput.min.js" />
		<g:external dir="js/plugins" file="jquery.custom.draggable.js"/>
		<g:external dir="bower_components/tooltipster/js" file="jquery.tooltipster.min.js" />

		<!-- internal javascript -->
		<g:javascript src="source/application.js"/>

		<g:layoutHead/>
	</head>

	<body>

		<div id="navbar">
			<div id="imodlogo" role="banner">
				<a href="${createLink(uri: '/')}">
					<g:img dir="images" file="imods_white_condensed.png" id="imod-logo" alt="imod"/>
				</a>
			</div>

			<div id="nav-links">
				<ul>
					<g:if test="${session.isAdmin}">
					<li>
						<a class="admin-link" href="${createLink(uri: '/admin/assessment')}">
							<i class="fa fa-lock"></i>
							Admin Area
						</a>
					</li>
					</g:if>
					<li><a class="banner-imod" href="${createLink(uri: '/imod')}">
						<i class="fa fa-list-alt"></i>
						<g:message code="My i-mods"/>
					</a></li>
					<li><a class="banner-home" href="${createLink(uri: '/')}">
						<i class="fa fa-home"></i>
						<g:message code="default.home.label"/>
					</a></li>
					<sec:ifLoggedIn>
						<li>
							<a href='${createLink(uri: '/settings')}' id='logout-link' class='banner-link'>
								<i class="fa fa-user"></i>
								Profile
							</a>
						</li>
						<li>
						<a href='${createLink(uri: '/logout')}' id='logout-link' class='banner-link'>
							<i class="fa fa-sign-out"></i>
							Logout
						</a></li>
					</sec:ifLoggedIn>

					<sec:ifNotLoggedIn>
						<li><a href="${createLink(uri: '/login')}" id="loginLink" class="banner-link">
							<i class="fa fa-sign-in"></i>
							Login
						</a></li>
					</sec:ifNotLoggedIn>

					<sec:ifSwitched>
						<li><a href="${request.contextPath}/j_spring_security_exit_user">
					  		Resume as
					  	</a></li>
						<sec:switchedUserOriginalUsername/>
				  	</sec:ifSwitched>
				</ul>
			</div>
		</div>
%{--<sec:ifLoggedIn>--}%
	%{--<div id="progressbar"><div class="progress-label">Loading...</div></div>--}%
%{--</sec:ifLoggedIn>--}%
		<g:layoutBody/>

		<div class="footer" role="contentinfo">
			Copyright &copy; 2015 IMODS&trade;
		</div>

		<div id="save-before-leaving" title="Please Save">
			<p>
				Please save before proceeding to next page.
			</p>
		</div>
	</body>
</html>
