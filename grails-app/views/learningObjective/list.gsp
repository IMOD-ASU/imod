
<%@ page import="imodv6.LearningObjective" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'learningObjective.label', default: 'LearningObjective')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-learningObjective" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-learningObjective" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="learningObjective.actionWord.label" default="Action Word" /></th>
					
						<g:sortableColumn property="completeLearningObjective" title="${message(code: 'learningObjective.completeLearningObjective.label', default: 'Complete Learning Objective')}" />
					
						<g:sortableColumn property="condition" title="${message(code: 'learningObjective.condition.label', default: 'Condition')}" />
					
						<th><g:message code="learningObjective.content.label" default="Content" /></th>
					
						<g:sortableColumn property="criteria" title="${message(code: 'learningObjective.criteria.label', default: 'Criteria')}" />
					
						<th><g:message code="learningObjective.criteriaType.label" default="Criteria Type" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${learningObjectiveInstanceList}" status="i" var="learningObjectiveInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${learningObjectiveInstance.id}">${fieldValue(bean: learningObjectiveInstance, field: "actionWord")}</g:link></td>
					
						<td>${fieldValue(bean: learningObjectiveInstance, field: "completeLearningObjective")}</td>
					
						<td>${fieldValue(bean: learningObjectiveInstance, field: "condition")}</td>
					
						<td>${fieldValue(bean: learningObjectiveInstance, field: "content")}</td>
					
						<td>${fieldValue(bean: learningObjectiveInstance, field: "criteria")}</td>
					
						<td>${fieldValue(bean: learningObjectiveInstance, field: "criteriaType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${learningObjectiveInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
