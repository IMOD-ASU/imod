<!DOCTYPE html>
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

		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="pragma" content="no-cache"/>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">

	  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}" type="image/x-icon">
	  <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}">
	  <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'colorsymbol_sml.png')}">
	  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	  <link rel="stylesheet" href="${resource(dir: 'css', file: 'imod.css')}" type="text/css">
	  <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
	  <link rel="stylesheet" href="${resource(dir: 'css/flick', file: 'jquery-ui-1.10.3.custom.css')}" type="text/css">
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

		<script src="${resource(dir: 'js', file: 'application.js')}" defer></script>

	  <gvisualization:apiImport/>
	  <g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>
		<div id="imodlogo" role="banner" style="background-color:#0C1625;">
		<a href="${createLink(uri: '/')}">
			<img id="imod_logo" src="${resource(dir: 'images', file: 'colorsignature_sml.png')}" alt="imod"/>
		</a>
		  <span id='s2ui_login_link_container'>
		    <a class="banner-imod" href="${createLink(uri: '/imod')}">
					<g:message code="My i-mods"/>
				</a>
		    <a class="banner-home" href="${createLink(uri: '/')}">
					<img class="resize_home" src="${resource(dir: 'images', file: 'home_sm.png')}" alt=""/>
					<g:message code="default.home.label"/>
				</a>
	      <nobr>
	        <div id='loginLinkContainer' class='banner-link'>
	          <sec:ifLoggedIn>
							<img class="resize_home" src="${resource(dir: 'images', file: 'user_sm.png')}" alt=""/>
	            <sec:username/>
							<a href='${createLink(uri: '/logout')}' id='logoutLink' class='banner-link'>
								Logout
							</a>
	          </sec:ifLoggedIn>

	          <sec:ifNotLoggedIn>
	            <a href='${createLink(uri: '/login')}' id='loginLink' class='banner-link'>
								Login
							</a>
	          </sec:ifNotLoggedIn>

	          <sec:ifSwitched>
	            <a href='${request.contextPath}/j_spring_security_exit_user'>
	              Resume as
								<sec:switchedUserOriginalUsername/>
	            </a>
	          </sec:ifSwitched>
	        </div>
	      </nobr>
		  </span>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo">
			Copyright © 2014 IMOD™
		</div>

		<div id="spinner" class="spinner" style="display:none;">
			<g:message code="spinner.alt" default="Loading&hellip;"/>
		</div>
	</body>
</html>
