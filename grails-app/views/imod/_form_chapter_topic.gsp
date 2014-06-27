<%@ page import="imodv6.ContentKnowledgeDomainCode; imodv6.Help; imodv6.ContentPriorityCode; imodv6.Imod" %>
<g:form name="addTopicForm" action="saveTopic" controller="content" title="${Help.toolTip("CONTENT", "Add Topic Form")}">
	<g:hiddenField name="id" value="${params.id ?: imod?.id}"/>
	<g:hiddenField name="objectiveId" value="${chapter?.id}"/>
	<g:render template="/content/ajaxForm"/>
</g:form>
<g:form name="removeTopicForm" action="#" title="${Help.toolTip("CONTENT", "Remove Topic Widget")}" onsubmit="return false;">
	<g:render template="/content/deleteTopicAjaxForm"/>
</g:form>
<div class="form_title removeBorder">
	<input type="button" name="_action_update" value="Add Topic" class="add showHover" onclick="openAddTopicPopup()" title="${Help.toolTip("CONTENT", "Add Topic")}"/>
	<input type="button" name="_action_delete" value="Remove Topic" class="remove showHover" title="${Help.toolTip("CONTENT", "Remove Topic")}" onclick="openRemoveTopicPopup()">
</div>

<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'contents', 'error')} ">
	<div id="clickthis">
	</div>
	<div id="custom_topic" style="max-height: 138px;min-height: 138px;overflow: scroll;width: 695px;">
		<table id="topic_table" border="1" style="width: 100%">
			<thead>
				<tr>
					<th class="showHover" title="${Help.toolTip("CONTENT", "Topic Label")}">
						Topic
					</th>
					<th class="showHover" title="${Help.toolTip("CONTENT", "Knowledge Dimension Label")}">
						Knowledge Dimension
					</th>
					<th class="showHover" title="${Help.toolTip("CONTENT", "Priority Label")}">
						Priority
					</th>
					<th class="showHover" title="${Help.toolTip("CONTENT", "Resources Label")}">
						Resources
					</th>
					<th class="showHover" title="${Help.toolTip("CONTENT", "Pre-Req Label")}">
						Pre-Req
					</th>
				</tr>
			</thead>
			<tbody>
				<g:if test="${contentList}">
					<g:each in="${contentList}" var="content">
						<tr class="showHover" id="${content?.id}" title="Topic: ${content?.topicTitle} | Knowledge Dimension: ${content?.knowledgeDomainCode} | Priority: ${content?.priorityCode}">
							<td>
								<g:textField name="topicTitle" value="${content?.topicTitle}"/>
								<g:hiddenField name="contentId-${content?.id}" value="${content?.id}"/>
							</td>
							<td>
								<g:select name="learningObjectiveType" noSelection="['': 'Select one']" from="${ContentKnowledgeDomainCode.values()?.collect { it.description }}"  value="${content?.knowledgeDomainCode?.description}"/>
							</td>
							<td>
								<g:select name="priorityCodeType" from="${ContentPriorityCode.values()*.description}" class="${content?.id}" noSelection="['': 'Select one']" value="${content?.priorityCode?.description}" onchange="updateCurrentTopic(jQuery(this).attr('class'))"/></td>
							<td>
								<input type="button" name="showResources" style="padding: 0" value="Resources" onclick="showResourcesPopup('${content?.id}')"/>
							</td>
							<td>
								<g:checkBox name="preReq${content?.id}" value="${content?.preReq}" onchange="changePreReq('${content?.id}')"/>
							</td>
						</tr>
					</g:each>
				</g:if>
				<g:else>
					<tr>
						<td colspan="5">
							No data found
						</td>
					</tr>
				</g:else>
			</tbody>
		</table>
	</div>
</div>

<div id="showResourcesDiv" title="${Help.toolTip("CONTENT", "Add Resource")}">
	<div id="showResourcesInnerDiv">
		<g:uploadForm controller="content" action="saveResource" name="resourceAdditionForm">
			<table>
				<tbody>
					<tr>
						<td>
							<input type="button" name="addResourcesButton" onclick="addResourceBefore()" class="add" value="Add Resource" style="padding: 0;;width:170px;"/>
						</td>
						<td>
							<input type="button" name="removeResourcesButton" onclick="removeResourceBefore()" style="padding: 0;width:200px;" class="remove" value="Remove Resource"/>
						</td>
						<td>
							<input type="button" name="saveResourcesButton" onclick="jQuery('form#resourceAdditionForm').submit()" class="add" value="Save" style="padding: 0;width:88px;">
						</td>
					</tr>
				</tbody>
			</table>
			<g:hiddenField name="id" value="${params.id ?: chapter?.id}"/>
			<g:hiddenField name="currentContentIdForResource" value=""/>
			<table style="width: 100%" id="showResourcesTable" border="1">
				<thead>
					<tr>
						<th>
							Resource Name
						</th>
						<th>
							File Upload
						</th>
						<th>
							Resource Type
						</th>
					</tr>
				</thead>
			<tbody>
			</tbody>
			</table>
		</div>
		<input type="submit" value="Update" name="saveResource" style="height: 27px;"/>
		<input type="button" value="Cancel" name="cancel" onclick="closeResourcesPopup()" style="height: 27px;"/>
	</g:uploadForm>
</div>
