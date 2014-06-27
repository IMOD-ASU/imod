<%@ page import="imodv6.CoursePolicy" %>



<div class="fieldcontain ${hasErrors(bean: coursePolicyInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="coursePolicy.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${imodv6.CoursePolicyCategory.list()}" optionKey="id" required="" value="${coursePolicyInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: coursePolicyInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="coursePolicy.description.label" default="Description" />
	</label>
	<g:textField name="description" value="${coursePolicyInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: coursePolicyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="coursePolicy.name.label" default="Name" />
	</label>
	<g:textField name="name" value="${coursePolicyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: coursePolicyInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="coursePolicy.user.label" default="User" />
		<span class="required-indicator">
			*
		</span>
	</label>
	<g:select id="user" name="user.id" from="${imodv6.ImodUser.list()}" optionKey="id" required="" value="${coursePolicyInstance?.user?.id}" class="many-to-one"/>
</div>

