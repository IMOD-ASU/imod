<%@ page import="imodv6.DomainCategory" %>



<div class="fieldcontain ${hasErrors(bean: domainCategoryInstance, field: 'domain', 'error')} required">
	<label for="domain">
		<g:message code="domainCategory.domain.label" default="Domain" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="domain" name="domain.id" from="${imodv6.LearningDomain.list()}" optionKey="id" required="" value="${domainCategoryInstance?.domain?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: domainCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="domainCategory.name.label" default="Name" />

	</label>
	<g:textField name="name" value="${domainCategoryInstance?.name}"/>
</div>

