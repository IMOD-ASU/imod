<html>
	<head>
		<title>
			<g:layoutTitle default='User Registration' />
		</title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />

		<g:external dir="bower_components/jquery/dist" file="jquery.min.js" />
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'reset.css',plugin:'spring-security-ui')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'spring-security-ui.css',plugin:'spring-security-ui')}"/>
		<jqui:resources />
		<link rel="stylesheet" media="screen" href="${resource(dir:'css/smoothness',file:'jquery-ui-1.10.3.custom.css',plugin:'spring-security-ui')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.jgrowl.css',plugin:'spring-security-ui')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.safari-checkbox.css',plugin:'spring-security-ui')}"/>
		<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'auth.css',plugin:'spring-security-ui')}"/>

		<g:layoutHead />
	</head>

	<body>
		<g:layoutBody />

		<g:javascript src='jquery/jquery.jgrowl.js' plugin='spring-security-ui'/>
		<g:javascript src='jquery/jquery.checkbox.js' plugin='spring-security-ui'/>
		<g:javascript src='plugins/spring-security-ui.js'/>

		<s2ui:showFlash/>
	</body>
</html>
