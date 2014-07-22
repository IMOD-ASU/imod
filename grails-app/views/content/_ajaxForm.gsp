<%@ page import="imodv6.Content; imodv6.Help; imodv6.KnowledgeDimensionEnum" %>
<div id="contentDialog">
	<fieldset class="buttons contentButtonField">
		<span class="contentButtonGradient">
			<g:actionSubmit 
				class="add showHoverNew contentButton" 
				action="add" 
				title="${Help.toolTip("OVERVIEW", "Add New Topic")}"  
				value="${message(code: 'Add Topic', default: 'Add Topic')}"
			/>
		</span>
		<span class=contentButtonGradient>
			<g:actionSubmit 
				class="remove showHoverNew contentButton" 
				action="remove" 
				title="${Help.toolTip("OVERVIEW", "Delete Selected Topic")}"  
				value="${message(code: 'Remove Topic', default: 'Remove Topic')}"
			/>
		</span>

	</fieldset>
	<div id=contentList>
		<g:each  var="contentItem" in="${contentList}">
			<div id="${contentItem.id}">
				<g:textField name="contentTitle${contentItem.id}" value="${contentItem.topicTitle}"/>
				<g:select
					rows="1"
					multiple="true"
					name="contentDimensions${contentItem.id}"
					from="${KnowledgeDimensionEnum.values()}"
					value="${contentItem.dimensions}"
				/>
				<g:select
					name="contentPriority${contentItem.id}"
					from="${Content.priorities()}"
					value="${contentItem.priority}"
				/>
				<button id="contentResources${contentItem.id}" type="button">Resources</button>
				<g:checkBox name="contentPreReq${contentItem.id}" value="${contentItem.preReq }" />
			</div>
		</g:each>
	</div>
</div>
