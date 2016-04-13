<%@ page import="imod.Content"%>
<%@ page import="imod.Help"%>
<%@ page import="imod.KnowledgeDimensionEnum"%>

<div id="topicDialogBackground" class="modalBackground"></div>
<div id="topicDialog" class="draggable">
	<g:hiddenField name="imodID" value="${currentImod.id}" />
	<fieldset class="buttons topicButtonField draggable-handle">
		<span class="topicButtonGradient">
			<button type="submit" name="_action_add" value="Add Topic" class="add show-hover-new topicButton tooltipster" id="addTopic" title="${message(code: 'imod.content.add')}">
				<i class="fa fa-plus green"></i>
				Add Topic
			</button>
		</span>
		<span class="topicButtonGradient">
			<button type="submit" name="_action_remove" value="Remove Topic" class="remove show-hover-new topicButton tooltipster" id="removeTopic" title="${message(code: 'imod.content.remove')}">
				<i class="fa fa-minus-circle red"></i>
				Remove Topic
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
				<td>
					Topic
				</td>
				<td>
					Knowledge Dimension
				</td>
				<td>
					Priority
				</td>
				<td>
					Resources
				</td>
				<td>
					Pre-Req
				</td>
			</tr>
		</thead>
		<tbody>
			<!-- TODO is this test necessary? -->
			<g:if test="${contentList.getClass()!=String}">
				<g:each var="contentItem" in="${contentList}">
					<tr id="${contentItem.id}" class="topicItem">
						<td class="saveIcon">
							<i class="fa fa-square-o"></i>
						</td>
						<td class="topicTitle">
							<g:textField name="topicTitle${contentItem.id}" value="${contentItem.topicTitle}" />
							<!-- FIXME replace with g:hiddenField -->
							<input type="hidden" id="topicTitleSaved${contentItem.id}" value="${contentItem.topicTitle}" />
						</td>
						<td class="topicDimensions">
							<span>
								<!-- FIXME move complex logic to controller -->
								<g:img dir="images/content" file="knowDim${contentItem.dimensions.sort(){it.value}.collect(){it.toString().charAt(0)}.join()?:'None'}.png" title="${contentItem.dimensions.join(',  ')}"/>
								<button class="knowledgeDimensionButtons tooltipster" value="${contentItem.dimensions.join(',')}" type="button" id="knowDimensionList${contentItem.id}" title="${message(code: 'imod.content.knowledgeDimension')}>
									Knowledge Dimensions
								</button>
								<!-- FIXME replace with g:hiddenField -->
								<input type="hidden" id="knowDimensionListSaved${contentItem.id }" value="${contentItem.dimensions.join(',')}" />
							</span>
						</td>
						<td class="topicPriority">
							<g:select class="custom-dropdown" size="1" name="topicPriority${contentItem.id}" from="${Content.priorities()}" value="${contentItem.priority}" />
							<!-- FIXME replace with g:hiddenField -->
							<input type="hidden" id="topicPrioritySaved${contentItem.id}" value="${contentItem.priority}" />
						</td>
						<td class="topicResources">
							<button id="topicResources${contentItem.id}" type="button">
								Resources
							</button>
							<button class="ResourceButton" id="topicResources${contentItem.id}" type="button">Resources</button>
						</td>
						<td class="topicPreReq">
							<g:checkBox name="topicPreReq${contentItem.id}" value="${contentItem.preReq }" />
							<!-- FIXME replace with g:hiddenField -->
							<input type="hidden" id="topicPreReqSaved${contentItem.id}" value="${contentItem.preReq}" />
						</td>
					</tr>
				</g:each>
			</g:if>
		</tbody>
	</table>
	<fieldset id="topicModalClose" class="buttons topicButtonField">
		<span class="topicButtonGradient saveBG">
			<button class="save show-hover-new topicButton saveTopic tooltipster" action="save" id="saveTopic" title="${message(code: 'imod.content.save')}">
				<i class="fa fa-save green"></i>
				${message(code: 'Save Topic', default: ' Save')}
			</button>
		</span>
		<span class="topicButtonGradient cancelBG">
			<button class="show-hover-new topicButton cancelTopic tooltipster" action="cancel" id="cancelTopic" title="${message(code: 'imod.content.cancel')}">
				<i class="fa fa-times red"></i>
				${message(code: 'Cancel Topics', default: ' Cancel')}
			</button>
		</span>
	</fieldset>
</div>

%{--<div id="selectKnowledgeDimensionBackground" class="modalBackground"></div>--}%

%{--<div id="selectKnowledgeDimensions" class="draggable">--}%
	%{--<div class="draggable-handle">--}%
		%{--<!-- FIXME replace with g:hiddenField, this also may not be necessary -->--}%
		%{--<input type="hidden" id="topicID" />--}%
		%{--<span>--}%
			%{--<ul>--}%
				%{--<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">--}%
					%{--<li>--}%
						%{--<!-- FIXME replace with g:checkbox tag -->--}%
						%{--<input type="checkbox" id="${dimension}" value="${dimension}" />--}%
						%{--<label class="knowledge-dim-label" title="${dimension.getInfo()}" for="${dimension}">--}%
							%{--${dimension}--}%
						%{--</label>--}%
						%{--<input--}%
							%{--type="checkbox"--}%
							%{--id="${dimension}"--}%
							%{--value="${dimension}"/>--}%
						%{--<label class="knowledge-dim-label" title="${dimension.getInfo()}" for="${dimension}">--}%
							%{--${dimension}--}%
						%{--</label>--}%
					%{--</li>--}%
				%{--</g:each>--}%
			%{--</ul>--}%
			%{--<g:img dir="images/content" file="knowDimNone.png" id="dimImage" width="71" height="71" />--}%
		%{--</span>--}%
	%{--</div>--}%
	%{--<button id="knowDimFinished" class="topicButtonGradient knowledgedimBtn" type="button">--}%
		%{--<i class="fa fa-save green"></i>--}%
		%{--Continue--}%
	%{--</button>--}%
	%{--<button id="closeKnowDim" class="topicButtonGradient knowledgedimBtn" type="button">--}%
		%{--<i class="fa fa-times red"></i>--}%
		%{--Cancel--}%
	%{--</button>--}%
%{--</div>--}%

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
			class="save showHoverNew resourceButton topicButtonGradient knowledgedimBtn tooltipster"
			id="knowDimFinished"
			title="${message(code: 'imod.pedagogy.technique.KD.continue')}">
			<i class="fa fa-save green"></i>
			${message(code: 'Save Topic', default: ' Continue')}
		</button>
		<button
			class="cancel showHoverNew resourceButton topicButtonGradient knowledgedimBtn tooltipster"
			id="closeKnowDim"
			title="${message(code: 'imod.pedagogy.technique.KD.cancel')}">
			<i class="fa fa-times red"></i>
			${message(code: 'Cancel Topic', default: ' Cancel')}
		</button>
	</div>
</div>
<div id="selectResourceBackground" class="modalBackground">
</div>
<div id="selectResource" class="draggable">
	<fieldset class="buttons resourceButtonField draggable-handle">
		<span class="topicButtonGradient">
			<button
				class="add showHoverNew resourceButton tooltipster" action="add" id="addResource"
				title="${message(code: 'imod.pedagogy.technique.addResource')}"
				value="${message(code: 'Add Resource', default: 'Add Resource')}"
			>
				<i class="fa fa-plus green"></i>
				${message(code: 'Add Resource', default: 'Add Resource')}
			</button>
		</span>
		<span class="topicButtonGradient">
			<button
				class="remove showHoverNew resourceButton tooltipster" action="remove"
				id="removeResource"
				title="${message(code: 'imod.pedagogy.technique.removeResource')}"
				value="${message(code: 'Remove Resource', default: 'Remove Resource')}"
			>
				<i class="fa fa-minus-circle red"></i>
				${message(code: 'Remove Resource', default: 'Remove Resource')}
			</button>
		</span>
		<span id="errorMessage"></span>
	</fieldset>
	<table id="resourceList">
		<thead>
			<tr>
				<td></td>
				<td>Title</td>
				<td>Description</td>
				<td>Resource Type</td>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
		<fieldset class="buttons resourceButtonField">
		<span class="topicButtonGradient saveBG">
			<button
				class="save showHoverNew resourceButton tooltipster"
				action="save"
				id="saveResource"
				title="${message(code: 'imod.pedagogy.technique.saveResource')}"
			>
				<i class="fa fa-save green"></i>
				${message(code: 'Save Resource', default: ' Save')}
			</button>
		</span>
		<span class="topicButtonGradient cancelBG">
			<button
				class="showHoverNew resourceButton tooltipster"
				action="cancel"
				id="cancelResource"
				title="${message(code: 'imod.pedagogy.technique.KD.cancel')}"
			>
				<i class="fa fa-times red"></i>
				${message(code: 'Cancel Resources', default: ' Cancel')}
			</button>
		</span>


</fieldset>
</div>
