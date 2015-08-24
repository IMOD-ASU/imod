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
			<div class="message banner">
				Please enter course overview data followed by learning objectives and content.
			</div>
			<div id="tabs-container" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul class="ui-tabs-nav ui-widget-header ui-corner-all">
					<li id="a" class="ui-state-default ui-corner-top ${ currentPage.find(/course overview/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="courseOverview" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'learning_context_icon.png')}" alt=""/>
							<span id="overview-tab-title">
								Course Overview
							</span>
						</g:link>
					</li>
					<li id="b" class="ui-state-default ui-corner-top ${ currentPage.find(/learning objective/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="learningObjective" action="performance" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'LO_icon.png')}" alt=""/>
							<span id="lo-tab-title">
								Learning Objectives
							</span>
						</g:link>
					</li>
					<li  id="c" class="ui-state-default ui-corner-top ${ currentPage.find(/^content/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="content" action="index" id="${currentImod?.id}" params=" [ ${objectiveId: params.objectiveId} ] " class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'content_icon.png')}" alt=""/>
							<span id="content-tab-title">
								Content
							</span>
						</g:link>
					</li>
					<li id="d" class="ui-state-default ui-corner-top ${ currentPage.find(/assessment/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="assessment" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
							<img class="tab-icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/>
							<span id="assess-tab-title">
								Assessment
							</span>
						</g:link>
					</li>
					<li  id="e" class="ui-state-default ui-corner-top ${ currentPage.find(/pedagogy/) == null ? '' : 'ui-tabs-active ui-state-active'}">
						<g:link controller="pedagogy" action="index" id="${currentImod?.id}" class="ui-tabs-anchor">
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
