<%@ page import="imodv6.LearningObjective" %>



<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'actionWord', 'error')} required">
	<label for="actionWord">
		<g:message code="learningObjective.actionWord.label" default="Action Word" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="actionWord" name="actionWord.id" from="${imodv6.ActionWord.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.actionWord?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'completeLearningObjective', 'error')} ">
	<label for="completeLearningObjective">
		<g:message code="learningObjective.completeLearningObjective.label" default="Complete Learning Objective" />

	</label>
	<g:textField name="completeLearningObjective" value="${learningObjectiveInstance?.completeLearningObjective}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'condition', 'error')} ">
	<label for="condition">
		<g:message code="learningObjective.condition.label" default="Condition" />

	</label>
	<g:textField name="condition" value="${learningObjectiveInstance?.condition}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="learningObjective.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="content" name="content.id" from="${imodv6.Content.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.content?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'criteria', 'error')} ">
	<label for="criteria">
		<g:message code="learningObjective.criteria.label" default="Criteria" />

	</label>
	<g:textField name="criteria" value="${learningObjectiveInstance?.criteria}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'criteriaType', 'error')} required">
	<label for="criteriaType">
		<g:message code="learningObjective.criteriaType.label" default="Criteria Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="criteriaType" name="criteriaType.id" from="${imodv6.LearningObjectiveCriteriaType.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.criteriaType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'domainCategory', 'error')} required">
	<label for="domainCategory">
		<g:message code="learningObjective.domainCategory.label" default="Domain Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="domainCategory" name="domainCategory.id" from="${imodv6.DomainCategory.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.domainCategory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'imod', 'error')} required">
	<label for="imod">
		<g:message code="learningObjective.imod.label" default="Imod" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="imod" name="imod.id" from="${imodv6.Imod.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.imod?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'indicator', 'error')} ">
	<label for="indicator">
		<g:message code="learningObjective.indicator.label" default="Indicator" />

	</label>
	<g:textField name="indicator" value="${learningObjectiveInstance?.indicator}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'learningDomain', 'error')} required">
	<label for="learningDomain">
		<g:message code="learningObjective.learningDomain.label" default="Learning Domain" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="learningDomain" name="learningDomain.id" from="${imodv6.LearningDomain.list()}" optionKey="id" required="" value="${learningObjectiveInstance?.learningDomain?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: learningObjectiveInstance, field: 'performance', 'error')} ">
	<label for="performance">
		<g:message code="learningObjective.performance.label" default="Performance" />

	</label>
	<g:textField name="performance" value="${learningObjectiveInstance?.performance}"/>
</div>

