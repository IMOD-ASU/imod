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
			<div id="tabs-container" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul class="ui-tabs-nav ui-widget-header ui-corner-all">
					<li id="d" class="ui-state-default ui-corner-top ${ currentPage.find(/assessment/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="admin" action="assessment" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/>
							<span id="assess-tab-title">
								Assessment
							</span>
						</g:link>
					</li>
					<li  id="e" class="ui-state-default ui-corner-top ${ currentPage.find(/pedagogy/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="admin" action="pedagogy" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'pedagogy_icon.png')}" alt=""/>
							<span id="pedagogy-tab-title">
								Pedagogy
							</span>
						</g:link>
					</li>
				</ul>

				<g:layoutBody/>
			</div>
		</body>
	</html>
</g:applyLayout>
