<%--
	this is the layout for all imod tabs, it has the navigation between:
	- Course Overview
	- Learning Objectives
	- Content
	- Assessments
	- Pedagogy
--%>
<%@ page import="imod.Help" %>
<%@ page import="imod.Imod" %>
<%@ page import="imod.ImodUser" %>

<g:applyLayout name="main">
	<html>
		<head>
			<title>
				<g:layoutTitle default="IMOD" />
			</title>
			<meta name="imod-is-saved" value="${currentImod?.saved}" />

			<g:layoutHead/>
		</head>

		<body>
			<div id="help-placeholder">
				<img id="resize-quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/>
				<span id="qtip-heading">
					Quick tip
				</Span>
				<a id="open-button">
					<img id="active-quicktip" src="${resource(dir: 'images', file: 'tip_active.png')}" alt=""/>
				</a>
				<div id="qtip-place" style="">
				</div>

			</div>
			<div id="help-placeholder-2">
				<img id="resize-quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/>
				<a id="open-button">
					<img id="active-quicktip" src="${resource(dir: 'images', file: 'tip_inactive.png')}" alt=""/>
				</a>
			</div>
			<div id="tabs-container" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<g:layoutBody/>
			</div>
		</body>
	</html>
</g:applyLayout>
