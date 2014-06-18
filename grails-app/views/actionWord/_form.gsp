<%@ page import="imodv6.ActionWord" %>



<div class="fieldcontain ${hasErrors(bean: actionWordInstance, field: 'actionWord', 'error')} ">
	<label for="actionWord">
		<g:message code="actionWord.actionWord.label" default="Action Word" />
		
	</label>
	<g:textField name="actionWord" value="${actionWordInstance?.actionWord}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actionWordInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="actionWord.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${imodv6.DomainCategory.list()}" optionKey="id" required="" value="${actionWordInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actionWordInstance, field: 'domain', 'error')} required">
	<label for="domain">
		<g:message code="actionWord.domain.label" default="Domain" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="domain" name="domain.id" from="${imodv6.LearningDomain.list()}" optionKey="id" required="" value="${actionWordInstance?.domain?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actionWordInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="actionWord.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${imodv6.ImodUser.list()}" optionKey="id" required="" value="${actionWordInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actionWordInstance, field: 'visibility', 'error')} ">
	<label for="visibility">
		<g:message code="actionWord.visibility.label" default="Visibility" />
		
	</label>
	<g:textField name="visibility" value="${actionWordInstance?.visibility}"/>
</div>

