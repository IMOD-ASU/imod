<%--
	this is the layout for all imod tabs, it has the navigation between:
	- Course Overview
	- Learning Objectives
	- Content
	- Assessments
	- Pedagogy
--%>
<g:applyLayout name="main">
	<html>
		<head>
			<title>
				<g:layoutTitle default="IMOD"/>
			</title>

			<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
			<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.qtip-1.0.0-rc3.min.js')}" defer></script>
			<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.treeview.js')}" defer></script>
			<script src="${resource(dir: 'js/source', file: 'imod.edit.gsp.js')}"></script>
			<script src="${resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js')}" defer></script>

			<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.treeview.css')}">
			<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'imod.edit.gsp.css')}">

			<g:layoutHead/>
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
				<ul>
					<li>
						<a href="#tabs-1">
							<img class="tab_icon" src="${resource(dir: 'images', file: 'learning_context_icon.png')}" alt=""/>
							<span id="overview_tab_title">
								Course Overview
							</span>
						</a>
					</li>
					<li>
						<a href="#tabs-2">
							<img class="tab_icon" src="${resource(dir: 'images', file: 'LO_icon.png')}" alt=""/>
							<span id="lo_tab_title">
								Learning Objectives
							</span>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'content', action: 'contentTab', id: imodInstance?.id, params: [objectiveId: params.objectiveId])}">
							<img class="tab_icon" src="${resource(dir: 'images', file: 'content_icon.png')}" alt=""/>
							<span id="content_tab_title">
								Content
							</span>
						</a>
					</li>
					<li>
						<a href="#tabs-4">
							<img class="tab_icon" src="${resource(dir: 'images', file: 'assess_icon.png')}" alt=""/>
							<span id="assess_tab_title">
								Assessment
							</span>
						</a>
					</li>
					<li>
						<a href="#tabs-5">
							<img class="tab_icon" src="${resource(dir: 'images', file: 'pedagogy_icon.png')}" alt=""/>
							<span id="pedagogy_tab_title">
								Pedagogy
							</span>
						</a>
					</li>
				</ul>

				<g:layoutBody/>
			</div>
		</body>
	</html>
</g:applyLayout>
