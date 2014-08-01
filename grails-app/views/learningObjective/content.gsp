<html>
	<head>
		<title>
			Learning Objective - Content
		</title>
		<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'topicDialog.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'contentSubtab.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jstree', file: 'style.min.css')}" type="text/css">
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
		<script src="${resource(dir: 'js/source', file: 'topicDialog.js')}" defer></script>
		<script src="${resource(dir: 'js/source', file: 'learningObjectiveContent.js')}" defer></script>
		<script src="${resource(dir: 'js/jstree', file: 'jstree.js')}" defer></script>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:render template="../content/topicForm"/>
		<fieldset>
			<span class="topicModalButtonGradient">
				<button id="addTopicModal" class="topicModalButton"><i class="fa fa-plus"></i>Add Topics</button>
			</span>
		</fieldset>
		<input type="hidden" id="treeData" value="${contentList}">
		<div id="contentTree">
		</div>
	</body>
</html>
