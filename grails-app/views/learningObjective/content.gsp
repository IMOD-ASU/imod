<html>
	<head>
		<title>
			Learning Objective - Content
		</title>
		<g:external dir="css/source" file="learningObjective.css" />

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
		<g:external dir="bower_components/jquery-sortable/source/js" file="jquery-sortable-min.js" defer="defer" />
		<g:javascript src="source/learningObjectiveContent.js" defer="defer" />

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective.id}" />
		<g:render template="/content/topicForm"/>

		<fieldset class="learning-objective-button">
			<input type="submit" id="save-content" value="Save" name="save_content" class="save show-hover-new">
		</fieldset>
		<fieldset>
			<span class="topicModalButtonGradient">

				<button id="addTopicModal" class="topicModalButton" title="${ message( code:'imod.learningObjective.addTopic' ) }">
					<i class="fa fa-plus"></i>
					Add Topics
				</button>
			</span>
		</fieldset>


			${contentList2}

		

		<g:if test="${contentList.toString() != '[]'}">
			<g:hiddenField name="treeData" value='${contentList}' />
		</g:if>
		<g:else>
			Please add a topic.
		</g:else>
		%{-- <div id="contentTree"></div> --}%
	</body>
</html>
