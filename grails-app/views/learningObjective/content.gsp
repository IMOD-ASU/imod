<html>
	<head>
		<title>
			Learning Objective - Content
		</title>
		<g:external dir="css/source" file="learningObjective.css" />
		<g:external dir="css/source" file="content.css" />

		<!-- FIXME These should be loaded when needed, not prefetched -->
		<link id="imgNone" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimNone.png')}">
		<link id="imgC" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimC.png')}">
		<link id="imgF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimF.png')}">
		<link id="imgM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimM.png')}">
		<link id="imgP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimP.png')}">
		<link id="imgCF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCF.png')}">
		<link id="imgCM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCM.png')}">
		<link id="imgCP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCP.png')}">
		<link id="imgFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFM.png')}">
		<link id="imgFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFP.png')}">
		<link id="imgMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimMP.png')}">
		<link id="imgCFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFM.png')}">
		<link id="imgCFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFP.png')}">
		<link id="imgCMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCMP.png')}">
		<link id="imgFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFMP.png')}">
		<link id="imgCFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFMP.png')}">

		<g:javascript src="source/topicDialog.js" defer="defer" />
		<g:javascript src="source/learningObjectiveContent.js" defer="defer" />

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<!-- FIXME replace with g:hiddenField -->
		<input type="hidden" id="learningObjectiveID" value="${learningObjective.id}" />
		<g:render template="/content/topicForm"/>
		<fieldset>
			<span class="topicModalButtonGradient">
				<button id="addTopicModal" class="topicModalButton">
					<i class="fa fa-plus"></i>
					Add Topics
				</button>
			</span>
		</fieldset>
		<!-- FIXME replace with g:hiddenField -->
		<input type="hidden" id="treeData" value="${contentList}">
		<div id="contentTree"></div>
	</body>
</html>
