<%@ page import="imodv6.CoursePolicyCategory" %>



<div class="fieldcontain ${hasErrors(bean: coursePolicyCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="coursePolicyCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${coursePolicyCategoryInstance?.name}"/>
</div>

