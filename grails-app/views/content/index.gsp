<%@ page import="imod.Content" %>
<%@ page import="imod.Help" %>
<%@ page import="imod.KnowledgeDimensionEnum" %>
<html>
<head>
	<title>
		Content
	</title>


	<g:external dir="css/source" file="learningObjective.css"/>
	<g:external dir="css/source" file="content.css"/>

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

	<g:javascript src="source/topicDialog.js" defer="defer"/>
	<g:external dir="bower_components/jquery.piegraph" file="jquery.piegraph.js"/>
	<g:external dir="bower_components/canvas.js" file="index.js"/>

	<meta name="layout" content="imod">
</head>

<body>
<g:hiddenField name="imodID" value="${currentImod.id}"/>
<fieldset class="buttons content-buttons topicButtonField">
	<span class="topicButtonGradient saveBG">
		<button
			class="save showHoverNew topicButton saveTopic"
			action="save"
			title="${Help.toolTip("OVERVIEW", "Save Selected Topics and Save")}">
			<i class="fa fa-save green"></i>
			${message(code: 'Save Topic', default: ' Save')}
		</button>
	</span>
	<span class="topicButtonGradient cancelBG">
		<button
			class="showHoverNew topicButton cancelTopic"
			action="cancel"
			title="${Help.toolTip("OVERVIEW", "Leave Add Topics without saving")}">
			<i class="fa fa-times red"></i>
			${message(code: 'Cancel Topics', default: ' Cancel')}
		</button>
	</span>
</fieldset>

<div id="legend">
	<fieldset class="button topicButtonField legendFS">
		<span id="legendTitle">Legend :</span>
	</fieldset>

	<table id="legendTable">
		<thead></thead>
		<tbody>
		<tr><td id="legendC"><g:img
			dir="images/content"
			file="knowDimC.png"/>Conceptual</td></tr>
		<tr><td id="legendF"><g:img
			dir="images/content"
			file="knowDimF.png"/>Factual</td></tr>
		<tr><td id="legendM"><g:img
			dir="images/content"
			file="knowDimM.png"/>Metacognitive</td></tr>
		<tr><td id="legendP"><g:img
			dir="images/content"
			file="knowDimP.png"/>Procedural</td></tr>

		</tbody>
	</table>

</div>

<div id="contentTable">

	<fieldset class="buttons topicButtonField">
		<span class="topicButtonGradient">
			<button
				class="add  topicButton" action="add" id="addTopic"
				title="${message(code: 'imod.content.add')}"
				value="${message(code: 'Add Topic', default: 'Add Topic')}">
				<i class="fa fa-plus green"></i>
				${message(code: 'Add Topic', default: 'Add Topic')}
			</button>
		</span>
		<span class="topicButtonGradient">
			<button
				class="remove  topicButton" action="remove"
				id="removeTopic"
				title="${message(code: 'imod.content.remove')}"
				value="${message(code: 'Remove Topic', default: 'Remove Topic')}"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
				<i class="fa fa-minus-circle red"></i>
				${message(code: 'Remove Topic', default: 'Remove Topic')}
			</button>
		</span>
		<span id="errorMessage" class="globalErrorMessage"></span>
	</fieldset>

	<table id="topicList">
		<thead>
		<tr>
			<td class="saveIcon saveIcon-parent">
				<i class="fa fa-square-o"></i>
			</td>
			<td>Topic</td>
			<td>Knowledge Dimension</td>
			<td>Priority</td>
			<td>Resources</td>
			<td>Pre-Req</td>
		</tr>
		</thead>
		<tbody>
		<!-- ${currentImod.contents} -->
		<g:each var="contentItem" in="${currentImod.contents.sort { a, b -> a.topicTitle.compareTo(b.topicTitle) }}">
			<input type="hidden" id="contentID" value="${contentItem.id}"/>
			<tr id="${contentItem.id}" class="topicItem">
				<td class="saveIcon">
					<i class="fa fa-square-o"></i>
				</td>
				<td class="topicTitle">
					<g:textField
						name="topicTitle${contentItem.id}"
						value="${contentItem.topicTitle}"/>
					<input
						type="hidden"
						id="topicTitleSaved${contentItem.id}"
						value="${contentItem.topicTitle}"/>
				</td>
				<td class="show-hover-new topicDimensions">
					<span>

						<g:img
							dir="images/content"
							file="knowDim${contentItem.dimensions.sort() { it.value }.collect() {
								it.toString().charAt(0)
							}.join() ?: 'None'}.png"
							title="${contentItem.dimensions.join(',  ')}"/>
						<button
							class="knowledgeDimensionButton "

							value="${contentItem.dimensions.join(',')}"
							type="button"
							id="knowDimensionList${contentItem.id}"
							title="${message(code: 'imod.content.knowledgeDimension')}">
							Knowledge Dimensions
						</button>
						<input type="hidden"
							   id="knowDimensionListSaved${contentItem.id}"
							   value="${contentItem.dimensions.join(',')}"/>
					</span>
				</td>
				<td class="topicPriority">
					<g:select size="1"
							  name="topicPriority${contentItem.id}"
							  from="${Content.priorities()}"
							  value="${contentItem.priority}"
							  class="custom-dropdown  priority custom-dropdown"
							  title="${message(code: 'imod.content.priority')}"/>
					<input
						type="hidden"
						id="topicPrioritySaved${contentItem.id}"
						value="${contentItem.priority}"/>
				</td>
				<td class="topicResources">
					<button class="ResourceButton"
							title="${message(code: 'imod.content.Resources')}"
							id="topicResources${contentItem.id}"
							type="button">
						Resources
					</button>
				</td>
				<td class="topicPreReq">
					<g:checkBox
						name="topicPreReq${contentItem.id}"
						value="${contentItem.preReq}"
						class="show-hover-new"
						title="${message(code: 'imod.content.PreReq')}"/>
					<input
						type="hidden"
						id="topicPreReqSaved${contentItem.id}"
						value="${contentItem.preReq}"/>
				</td>
			</tr>
		</g:each>

		<g:if test="${contentList.getClass() != String}">
			<g:each var="contentItem" in="${contentList}">
				<tr id="${contentItem.id}" class="topicItem">
					<td class="saveIcon">
						<i class="fa fa-square-o"></i>
					</td>
					<td class="topicTitle">
						<g:textField
							name="topicTitle${contentItem.id}"
							value="${contentItem.topicTitle}"/>
						<input
							type="hidden"
							id="topicTitleSaved${contentItem.id}"
							value="${contentItem.topicTitle}"/>
					</td>
					<td class="topicDimensions">
						<span>
							<g:img
								dir="images/content"
								file="knowDim${contentItem.dimensions.sort() { it.value }.collect() {
									it.toString().charAt(0)
								}.join() ?: 'None'}.png"
								title="${contentItem.dimensions.join(',')}"/>
							<button
								class="knowledgeDimensionButton"
								value="${contentItem.dimensions.join(',')}"
								type="button"
								id="knowDimensionList${contentItem.id}"
								title="${message(code: 'imod.content.knowledgeDimension')}">
								Knowledge Dimensionsaa
							</button>
							<input type="hidden"
								   id="knowDimensionListSaved${contentItem.id}"
								   value="${contentItem.dimensions.join(',')}"/>
						</span>
					</td>
					<td class="topicPriority">
						<g:select size="1"
								  name="topicPriority${contentItem.id}"
								  from="${Content.priorities()}"
								  value="${contentItem.priority}"
								  class="custom-dropdown"/>
						<input
							type="hidden"
							id="topicPrioritySaved${contentItem.id}"
							value="${contentItem.priority}"/>
					</td>
					<td class="topicResources">
						<button class="ResourceButton" id="topicResources${contentItem.id}"
								type="button">Resources</button>
					</td>
					<td class="topicPreReq">
						<g:checkBox
							name="topicPreReq${contentItem.id}"
							value="${contentItem.preReq}"/>
						<input
							type="hidden"
							id="topicPreReqSaved${contentItem.id}"
							value="${contentItem.preReq}"/>
					</td>
				</tr>
			</g:each>
		</g:if>
		</tbody>
	</table>

</div>


<fieldset class="buttons content-buttons topicButtonField">
	<span class="topicButtonGradient saveBG">
		<button
			class="save showHoverNew topicButton saveTopic"
			action="save"
			title="${Help.toolTip("OVERVIEW", "Save Selected Topics and Save")}">
			<i class="fa fa-save green"></i>
			${message(code: 'Save Topic', default: ' Save')}
		</button>
	</span>
	<span class="topicButtonGradient cancelBG">
		<button
			class="showHoverNew topicButton cancelTopic"
			action="cancel"
			title="${Help.toolTip("OVERVIEW", "Leave Add Topics without saving")}">
			<i class="fa fa-times red"></i>
			${message(code: 'Cancel Topics', default: ' Cancel')}
		</button>
	</span>
</fieldset>

<div id="selectKnowledgeDimensionBackground" class="modalBackground">
</div>

<div id="selectKnowledgeDimensions" class="draggable">
	<div class="draggable-handle">
		<input type="hidden" id="topicID"/>
		<span>
			<ul>
				<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">
					<li>
						<input
							type="checkbox"
							id="${dimension}"
							value="${dimension}"/>
						<label class="knowledge-dim-label" title="${dimension.getInfo()}" for="${dimension}">
							${dimension}
						</label>
					</li>
				</g:each>
			</ul>
			<g:img
				dir="images/content"
				file="knowDimNone.png"
				id="dimImage"
				width="71"
				height="71"/>
		</span>
		<button
			class="save showHoverNew resourceButton topicButtonGradient knowledgedimBtn"
			id="knowDimFinished"
			title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}">
			<i class="fa fa-save green"></i>
			${message(code: 'Save Resource', default: ' Continue')}
		</button>
		<button
			class="cancel showHoverNew resourceButton topicButtonGradient knowledgedimBtn"
			id="closeKnowDim"
			title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}">
			<i class="fa fa-times red"></i>
			${message(code: 'Cancel Resource', default: ' Cancel')}
		</button>
	</div>
</div>

<div id="selectResourceBackground" class="modalBackground">
</div>

<div id="selectResource" class="draggable">
	<fieldset class="buttons resourceButtonField draggable-handle">
		<span class="topicButtonGradient">
			<button
				class="add showHoverNew resourceButton" action="add" id="addResource"
				title="${Help.toolTip("OVERVIEW", "Add New Resource")}"
				value="${message(code: 'Add Resource', default: 'Add Resource')}">
				<i class="fa fa-plus green"></i>
				${message(code: 'Add Resource', default: 'Add Resource')}
			</button>
		</span>
		<span class="topicButtonGradient">
			<button
				class="remove showHoverNew resourceButton" action="remove"
				id="removeResource"
				title="${Help.toolTip("OVERVIEW", "Delete Selected Resource")}"
				value="${message(code: 'Remove Resource', default: 'Remove Resource')}">
				<i class="fa fa-minus-circle red"></i>
				${message(code: 'Remove Resource', default: 'Remove Resource')}
			</button>
		</span>
		<span id="errorMessageResources" class="globalErrorMessage"></span>
	</fieldset>
	<table id="resourceList">
		<thead>
		<tr>
			<td></td>
			<td>Title</td>
			<td>Description</td>
			<td>Resource Type</td>
			<td></td>
		</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<fieldset class="buttons resourceButtonField">
		<span class="topicButtonGradient saveBG">
			<button
				class="save showHoverNew resourceButton"
				action="save"
				id="saveResource"
				title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}">
				<i class="fa fa-save green"></i>
				${message(code: 'Save Resource', default: ' Continue')}
			</button>
		</span>
		<span class="topicButtonGradient cancelBG">
			<button
				class="showHoverNew resourceButton"
				action="cancel"
				id="cancelResource"
				title="${Help.toolTip("OVERVIEW", "Leave Add Resources without saving")}">
				<i class="fa fa-times red"></i>
				${message(code: 'Cancel Resources', default: ' Cancel')}
			</button>
		</span>

	</fieldset>
</div>

<div id="chart" align="center">

</div>
<input type="hidden" id="treeData" value="${contentList}">
</body>
</html>
