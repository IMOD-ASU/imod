<%--
	this is the layout for all imod tabs, it has the navigation between:
	- Course Overview
	- Learning Objectives
	- Content
	- Assessments
	- Pedagogy
--%>
<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser" %>
<g:applyLayout name="main">
	<html>
		<head>
			<title>
				<g:layoutTitle default="IMOD"/>
			</title>

			<g:layoutHead/>

			<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'layout.imod.css')}">
			<link rel="stylesheet" href="//cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.css">
			<script src="//cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.js" defer></script>
			<script src="${resource(dir: 'js/source', file: 'layout.imod.js')}" defer></script>
		</head>

		<body>
			<div id="help-placeholder">
				<img id="resize-quicktip" src="${resource(dir: 'images', file: 'quick_tip.png')}" alt=""/>
				<span>
					Quick tip
				</Span>
				<a id="closebutton" href="#" onclick="toggleHelp()">
					_
				</a>

				<div id="qtipPlace" style="float: right; text-align: right; position: absolute; right: 0;">
				</div>
			</div>
			<div id="open-help" >
				<Span>
					Quick Tip
				</Span>
				<a id="closebutton" href="#" onclick="toggleHelp()">
					_
				</a>
			</div>
			<div id="tabs-container" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
					<li class="ui-state-default ui-corner-top ${ currentPage.find(/course overview/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="courseOverview" action="index" id="${imodInstance?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'learning_context_icon.png')}" alt=""/>
							<span id="overview-tab-title">
								Course Overview
							</span>
						</g:link>
					</li>
					<li class="ui-state-default ui-corner-top ${ currentPage.find(/learning objective/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="learningObjective" action="performance" id="${imodInstance?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'LO_icon.png')}" alt=""/>
							<span id="lo-tab-title">
								Learning Objectives
							</span>
						</g:link>
					</li>
					<li class="ui-state-default ui-corner-top ${ currentPage.find(/^content/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="content" action="index" id="${imodInstance?.id}" params=" [ ${objectiveId: params.objectiveId} ] " class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'content_icon.png')}" alt=""/>
							<span id="content-tab-title">
								Content
							</span>
						</g:link>
					</li>
					<li class="ui-state-default ui-corner-top ${ currentPage.find(/assessment/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="assessment" action="index" id="${imodInstance?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/>
							<span id="assess-tab-title">
								Assessment
							</span>
						</g:link>
					</li>
					<li class="ui-state-default ui-corner-top ${ currentPage.find(/pedagogy/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="pedagogy" action="index" id="${imodInstance?.id}" class="ui-tabs-anchor">
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
