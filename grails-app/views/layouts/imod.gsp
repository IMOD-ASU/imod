<%--
	this is the layout for all imod tabs, it has the navigation between:
	- Course Overview
	- Learning Objectives
	- Content
	- Assessments
	- Pedagogy
--%>

<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}"/>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.qtip-1.0.0-rc3.min.js')}" defer></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.treeview.js')}" defer></script>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.treeview.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'imod.edit.gsp.css')}">
		<script src="${resource(dir: 'js/source', file: 'imod.edit.gsp.js')}"></script>

		<script src="${resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js')}" defer></script>

		<title>
			<g:layoutTitle default="IMOD"/>
		</title>
	</head>

	<body>
		<div id="help_placeholder"  >
			<img id="resize_quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/>
			<span>
				Quick tip
			</Span>
			<a id="closebutton" href="#" onclick="toggleHelp()">
				_
			</a>

			<div id="qtipPlace" style="float: right; text-align: right; position: absolute; right: 0;">
			</div>
		</div>
		<div id="open_help" >
			<Span>
				Quick Tip
			</Span>
			<a id="closebutton" href="#" onclick="toggleHelp()">
				_
			</a>
		</div>
		<div id="tabs-container">
			<g:layoutBody/>
		</div>
	</body>
</html>
