
<%@ page import="imodv6.LearningObjective" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'learningObjective.label', default: 'LearningObjective')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<a href="#show-learningObjective" class="skip" tabindex="-1">
			<g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
		</a>
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label"/>
					</a>
				</li>
				<li>
					<g:link class="list" action="list">
						<g:message code="default.list.label" args="[entityName]" />
					</g:link>
				</li>
				<li>
					<g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link>
				</li>
			</ul>
		</div>
		<div id="show-learningObjective" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<ol class="property-list learningObjective">
				<g:if test="${learningObjectiveInstance?.actionWord}">
					<li class="fieldcontain">
						<span id="actionWord-label" class="property-label">
							<g:message code="learningObjective.actionWord.label" default="Action Word" />
						</span>
						<span class="property-value" aria-labelledby="actionWord-label">
							<g:link controller="actionWord" action="show" id="${learningObjectiveInstance?.actionWord?.id}">
								${learningObjectiveInstance?.actionWord?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.completeLearningObjective}">
					<li class="fieldcontain">
						<span id="completeLearningObjective-label" class="property-label">
							<g:message code="learningObjective.completeLearningObjective.label" default="Complete Learning Objective" />
						</span>
						<span class="property-value" aria-labelledby="completeLearningObjective-label">
							<g:fieldValue bean="${learningObjectiveInstance}" field="completeLearningObjective"/>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.condition}">
					<li class="fieldcontain">
						<span id="condition-label" class="property-label">
							<g:message code="learningObjective.condition.label" default="Condition" />
						</span>
						<span class="property-value" aria-labelledby="condition-label">
							<g:fieldValue bean="${learningObjectiveInstance}" field="condition"/>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.content}">
					<li class="fieldcontain">
						<span id="content-label" class="property-label">
							<g:message code="learningObjective.content.label" default="Content" />
						</span>
						<span class="property-value" aria-labelledby="content-label">
							<g:link controller="content" action="show" id="${learningObjectiveInstance?.content?.id}">
								${learningObjectiveInstance?.content?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.criteria}">
					<li class="fieldcontain">
						<span id="criteria-label" class="property-label">
							<g:message code="learningObjective.criteria.label" default="Criteria" />
						</span>
						<span class="property-value" aria-labelledby="criteria-label">
							<g:fieldValue bean="${learningObjectiveInstance}" field="criteria"/>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.criteriaType}">
					<li class="fieldcontain">
						<span id="criteriaType-label" class="property-label">
							<g:message code="learningObjective.criteriaType.label" default="Criteria Type" />
						</span>
						<span class="property-value" aria-labelledby="criteriaType-label">
							<g:link controller="learningObjectiveCriteriaType" action="show" id="${learningObjectiveInstance?.criteriaType?.id}">
								${learningObjectiveInstance?.criteriaType?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.domainCategory}">
					<li class="fieldcontain">
						<span id="domainCategory-label" class="property-label">
							<g:message code="learningObjective.domainCategory.label" default="Domain Category" />
						</span>
						<span class="property-value" aria-labelledby="domainCategory-label">
							<g:link controller="domainCategory" action="show" id="${learningObjectiveInstance?.domainCategory?.id}">
								${learningObjectiveInstance?.domainCategory?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.imod}">
					<li class="fieldcontain">
						<span id="imod-label" class="property-label">
							<g:message code="learningObjective.imod.label" default="Imod" />
						</span>
						<span class="property-value" aria-labelledby="imod-label">
							<g:link controller="imod" action="show" id="${learningObjectiveInstance?.imod?.id}">
								${learningObjectiveInstance?.imod?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.indicator}">
					<li class="fieldcontain">
						<span id="indicator-label" class="property-label">
							<g:message code="learningObjective.indicator.label" default="Indicator" />
						</span>
						<span class="property-value" aria-labelledby="indicator-label">
							<g:fieldValue bean="${learningObjectiveInstance}" field="indicator"/>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.learningDomain}">
					<li class="fieldcontain">
						<span id="learningDomain-label" class="property-label">
							<g:message code="learningObjective.learningDomain.label" default="Learning Domain" />
						</span>
						<span class="property-value" aria-labelledby="learningDomain-label">
							<g:link controller="learningDomain" action="show" id="${learningObjectiveInstance?.learningDomain?.id}">
								${learningObjectiveInstance?.learningDomain?.encodeAsHTML()}
							</g:link>
						</span>
					</li>
				</g:if>

				<g:if test="${learningObjectiveInstance?.performance}">
					<li class="fieldcontain">
						<span id="performance-label" class="property-label">
							<g:message code="learningObjective.performance.label" default="Performance" />
						</span>
						<span class="property-value" aria-labelledby="performance-label">
							<g:fieldValue bean="${learningObjectiveInstance}" field="performance"/>
						</span>
					</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${learningObjectiveInstance?.id}" />
					<g:link class="edit" action="edit" id="${learningObjectiveInstance?.id}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
