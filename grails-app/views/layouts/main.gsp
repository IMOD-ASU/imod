<%--
	this is the basic layout with only the IMOD header and footer
--%>

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

		<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.0/themes/ui-lightness/jquery-ui.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

		<script src="https://code.jquery.com/jquery-2.1.1.min.js" defer></script>
		<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.min.js" defer></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" defer></script>
		<script src="${resource(dir: 'js', file: 'application.js')}" defer></script>

		<gvisualization:apiImport/>
		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#imod-navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${createLink(uri: '/')}">
						IMODS
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling on mobile screens -->
				<div class="collapse navbar-collapse" id="imod-navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<sec:ifLoggedIn>
							<li>
								<a href="#coming-soon">
									<span class="glyphicon glyphicon-user"></span>
									<sec:username/>
								</a>
							</li>
							<li>
								<a href='${createLink(uri: '/logout')}'>
									<span class="fa fa-unlock"></span>
									Logout
								</a>
							</li>
						</sec:ifLoggedIn>

						<sec:ifNotLoggedIn>
							<li>
								<a href='${createLink(uri: '/login')}'>
									<span class="fa fa-lock"></span>
									Login
								</a>
							</li>
						</sec:ifNotLoggedIn>

						<sec:ifSwitched>
							<li>
								<a href='${request.contextPath}/j_spring_security_exit_user'>
									Resume as
								</a>
								<sec:switchedUserOriginalUsername/>
							</li>
						</sec:ifSwitched>
					</ul>
				</div>
			</div>
		</nav>

		<g:layoutBody/>

		<div class="container footer">
			Copyright &copy; 2014 IMOD&trade;
		</div>
	</body>
</html>
