<%@ page import="imodv6.Content; imodv6.Help; imodv6.KnowledgeDimensionEnum"%>
<div id="topicDialogBackground" class="modalBackground"></div>
<div id="topicDialog">
	<input type="hidden" id="imodID" value="${imodInstance.id}">
	<fieldset class="buttons topicButtonField">
		<span class="topicButtonGradient">
			<g:actionSubmit
				class="add showHoverNew topicButton" action="add" id="addTopic"
				title="${Help.toolTip("OVERVIEW", "Add New Topic")}"
				value="${message(code: 'Add Topic', default: 'Add Topic')}"
			/>
		</span>
		<span class="topicButtonGradient">
			<g:actionSubmit
				class="remove showHoverNew topicButton" action="remove"
				id="removeTopic"
				title="${Help.toolTip("OVERVIEW", "Delete Selected Topic")}"
				value="${message(code: 'Remove Topic', default: 'Remove Topic')}"
			/>
		</span>
		<span id="errorMessage"></span>
	</fieldset>
	<table id="topicList">
		<thead>
			<tr>
				<td></td>
				<td>Topic</td>
				<td>Knowledge Dimension</td>
				<td>Priority</td>
				<td>Resources</td>
				<td>Pre-Req</td>
			</tr>
		</thead>
		<tbody>
			<g:if test="${contentList.getClass()!=String}">
				<g:each var="contentItem" in="${contentList}">
					<tr id="${contentItem.id}" class="topicItem">
						<td class="saveIcon">
							<i class="hidden fa fa-eraser"></i>
						</td>
						<td class="topicTitle">
							<g:textField
								name="topicTitle${contentItem.id}"
								value="${contentItem.topicTitle}"
							/>
							<input
								type="hidden"
								id="topicTitleSaved${contentItem.id}"
								value="${contentItem.topicTitle}"
							/>
						</td>
						<td class="topicDimensions">
							<span>
								<g:img
									dir="images/content"
									file="knowDim${contentItem.dimensions.sort(){it.value}.collect(){it.toString().charAt(0)}.join()?:'None'}.png"
								/>
								<button
									class="knowledgeDimensionButton"
									value="${contentItem.dimensions.join(',')}"
									type="button"
									id="knowDimensionList${contentItem.id }"
								>
									Knowledge Dimensions
								</button>
								<input type="hidden"
									id="knowDimensionListSaved${contentItem.id }"
									value="${contentItem.dimensions.join(',')}"
								/>
							</span>
						</td>
						<td class="topicPriority">
							<g:select size="1"
								name="topicPriority${contentItem.id}"
								from="${Content.priorities()}"
								value="${contentItem.priority}"
							/>
							<input
								type="hidden"
								id="topicPrioritySaved${contentItem.id}"
								value="${contentItem.priority}"
							/>
						</td>
						<td class="topicResources">
							<button id="topicResources${contentItem.id}" type="button">Resources</button>
						</td>
						<td class="topicPreReq">
							<g:checkBox
								name="topicPreReq${contentItem.id}"
								value="${contentItem.preReq }"
							/>
							<input
								type="hidden"
								id="topicPreReqSaved${contentItem.id}"
								value="${contentItem.preReq}"
							/>
						</td>
					</tr>
				</g:each>
			</g:if>
		</tbody>
	</table>
	<fieldset id="topicModalClose" class="buttons topicButtonField">
		<span class="topicButtonGradient saveBG">
			<button
				class="save showHoverNew topicButton"
				action="save"
				id="saveTopic"
				title="${Help.toolTip("OVERVIEW", "Save Selected Topics and Finish")}"
			>
				${message(code: 'Save Topic', default: ' Finish')}
			</button>
		</span>
		<span class="topicButtonGradient cancelBG">
			<button
				class="showHoverNew topicButton"
				action="cancel"
				id="cancelTopic"
				title="${Help.toolTip("OVERVIEW", "Leave Add Topics without saving")}"
			>
				<i class="fa fa-times"></i>
				${message(code: 'Cancel Topics', default: ' Cancel')}
			</button>
		</span>
	</fieldset>
</div>

<div id="selectKnowledgeDimensionBackground" class="modalBackground">
</div>
<div id="selectKnowledgeDimensions">
	<input type="hidden" id="topicID" />
	<span>
		<ul>
			<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">
				<li>
					<input
						type="checkbox"
						id="${dimension}"
						value="${dimension}"
					/>
					<label for="${dimension}">
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
			height="71"
		/>
	</span>
	<button id="knowDimFinished" type="button">Save</button>
</div>
