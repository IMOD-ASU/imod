<%@ page import="imodv6.Content" %>



<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'chapter', 'error')} required">
	<label for="chapter">
		<g:message code="content.chapter.label" default="Chapter" />
		<span class="required-indicator">*</span>
	</label>
	%{--<g:select id="chapter" name="chapter.id" from="${imodv6.ContentChapter.list()}" optionKey="id" required="" value="${contentInstance?.chapter?.id}" class="many-to-one"/>--}%
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'knowledgeDomainCode', 'error')} required">
	<label for="learningObjectiveType">
		<g:message code="content.learningObjectiveType.label" default="Learning Objective Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="learningObjectiveType" from="${imodv6.ContentKnowledgeDomainCode?.values()}" keys="${imodv6.ContentKnowledgeDomainCode.values()*.name()}" required="" value="${contentInstance?.knowledgeDomainCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'priorityCode', 'error')} required">
	<label for="priorityCodeType">
		<g:message code="content.priorityCodeType.label" default="Priority Code Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="priorityCodeType" from="${imodv6.ContentPriorityCode?.values()}" required="" value="${contentInstance?.priorityCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'resources', 'error')} ">
	<label for="resources">
		<g:message code="content.resources.label" default="Resources" />
		
	</label>
	<g:select name="resources" from="${imodv6.ContentResource.list()}" multiple="multiple" optionKey="id" size="5" value="${contentInstance?.resources*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'topicTitle', 'error')} ">
	<label for="topicTitle">
		<g:message code="content.topicTitle.label" default="Topic Title" />
		
	</label>
	<g:textField name="topicTitle" value="${contentInstance?.topicTitle}"/>
</div>

