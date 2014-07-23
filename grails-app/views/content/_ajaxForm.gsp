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
				<tr id="${contentItem.id}">
					<td>
						<g:textField name="contentTitle${contentItem.id}" value="${contentItem.topicTitle}"/>
					</td>
					<td>
						<g:select
							rows="1"
							multiple="true"
							name="contentDimensions${contentItem.id}"
							from="${KnowledgeDimensionEnum.values()}"
							value="${contentItem.dimensions}"
						/>
					</td>
					<td>
						<g:select
							size="3"
							name="contentPriority${contentItem.id}"
							from="${Content.priorities()}"
							value="${contentItem.priority}"
						/>
					</td>
					<td>
						<button id="contentResources${contentItem.id}" type="button">Resources</button>
					</td>
					<td>
						<g:checkBox name="contentPreReq${contentItem.id}" value="${contentItem.preReq }" />
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</div>
