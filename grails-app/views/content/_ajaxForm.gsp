<%@ page import="imodv6.Content; imodv6.Help; imodv6.KnowledgeDimensionEnum" %>
<div id="contentDialog">
	<input type="hidden" id="imodID" value="${imodInstance.id}">
	<fieldset class="buttons contentButtonField">
		<span class="contentButtonGradient">
			<g:actionSubmit 
				class="add showHoverNew contentButton" 
				action="add" 
				id="addTopic"
				title="${Help.toolTip("OVERVIEW", "Add New Topic")}"  
				value="${message(code: 'Add Topic', default: 'Add Topic')}"
			/>
		</span>
		<span class=contentButtonGradient>
			<g:actionSubmit 
				class="remove showHoverNew contentButton" 
				action="remove"
				id="removeTopic" 
				title="${Help.toolTip("OVERVIEW", "Delete Selected Topic")}"  
				value="${message(code: 'Remove Topic', default: 'Remove Topic')}"
			/>
		</span>
		<span class="contentButtonGradient saveBG">
			<g:actionSubmit 
				class="save showHoverNew contentButton" 
				action="save"
				id="saveTopic"
				title="${Help.toolTip("OVERVIEW", "Save Selected Topic")}"  
				value="${message(code: 'Save Topic', default: 'Save Topic')}"
			/>
		</span>

	</fieldset>
	<table id="contentList">
		<thead>
			<tr>
			</tr>
		</thead>
		<tbody>
			<g:each  var="contentItem" in="${contentList}">
				<tr id="${contentItem.id}" class="contentItem">
					<td class="saveIcon" >
						<i class="hidden fa fa-check"></i>
					</td>
					<td class="contentTitle">
						<g:textField name="contentTitle${contentItem.id}" value="${contentItem.topicTitle}"/>
					</td>
					<td class="contentDimensions">
						<span>
							<g:img 
								dir="images/content" 
								file="knowDim${contentItem.dimensions.sort(){it.value}.collect(){it.toString().charAt(0)}.join()?:'None'}.png" 
								width="20" 
								height="20"
							/>
							<button class="knowledgeDimensionButton" type="button">Knowledge Dimensions</button>
							<input type="hidden" id="knowDimensionList${contentItem.id }" value="${contentItem.dimensions.join(',')}">
						</span>
					</td>
					<td class="contentPriority">
						<g:select
							size="1"
							name="contentPriority${contentItem.id}"
							from="${Content.priorities()}"
							value="${contentItem.priority}"
						/>
					</td>
					<td class="contentResources">
						<button id="contentResources${contentItem.id}" type="button">Resources</button>
					</td>
					<td class="contentPreReq">
						<g:checkBox name="contentPreReq${contentItem.id}" value="${contentItem.preReq }" />
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</div>


<div id="selectKnowledgeDimensions">
	<input type="hidden" id="contentID">
	<span>	
		<ul>
			<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">
				<li>
					<input type="checkbox" id="${dimension}" value="${dimension}">
					<label for="${dimension}">${dimension}</label>
				</li>
			</g:each>
		</ul>
		<g:img dir="images/content" file="knowDimNone.png" id="dimImage" width="71" height="71"/>
	</span>
	<button id="knowDimFinished" type="button">Save</button>
</div>