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

			<g:layoutHead/>
		</head>

		<body>

			<div class="row">
				<div class="col-md-offset-1 col-md-8">
					<ul class="nav nav-tabs nav-justified" role="tablist">
						<li class="active">
							<g:link controller="courseOverview" action="index" id="${imodInstance.id}">
								<span class="fa fa-book">
								</span>
								<span id="overview_tab_title">
									Course Overview
								</span>
							</g:link>
						</li>
						<li>
							<g:link controller="learningObjective" action="performance" id="${imodInstance.id}">
								<span class="fa fa-list">
								</span>
								<span id="lo_tab_title">
									Learning Objectives
								</span>
							</g:link>
						</li>
						<li>
							<g:link controller="content" action="contentTab" id="${imodInstance.id}" params=" [ ${objectiveId: params.objectiveId} ] ">
								<span class="fa fa-pencil-square-o">
								</span>
								<span id="content_tab_title">
									Content
								</span>
							</g:link>
						</li>
						<li>
							<g:link controller="assessment" action="index" id="${imodInstance.id}">
								<span class="fa fa-tasks">
								</span>
								<span id="assess_tab_title">
									Assessment
								</span>
							</g:link>
						</li>
						<li>
							<g:link controller="pedagogy" action="pedagogyTab">
								<span class="fa fa-graduation-cap"></span>
								<span id="pedagogy_tab_title">
									Pedagogy
								</span>
							</g:link>
						</li>
					</ul>

					<g:layoutBody/>
				</div>

				<div class="col-md-2 well well-default">
					<div>
						<span class="fa fa-lightbulb-o">
						</span>
						<span>
							Quick tip
						</Span>
						<a id="closebutton" href="#">
							_
						</a>

						<div id="qtipPlace">
						</div>
					</div>
					<div>
						<Span>
							Quick Tip
						</Span>
						<a id="closebutton" href="#">
							_
						</a>
					</div>
				</div>
			</div>
		</body>
	</html>
</g:applyLayout>
