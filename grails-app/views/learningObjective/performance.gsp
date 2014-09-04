<%@ page import="imodv6.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Performance
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:form name="performance" action="save" id="${imodInstance.id}">
			<g:hiddenField name="learningObjectiveID" value="${learningObjective.id}" />
			<g:hiddenField name="pageType" value="performance" />
			<g:hiddenField name="performance" value="" />
			<g:submitButton class="learning-objective-button save showHoverNew" name="update" value="Save" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
		</g:form>

		<label for="learning-domain-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Domain')}">
			Learning Domain
		</label>
		<g:select
			name="LDL"
			id="learning-domain-list"
			from="${domainList.name}"
			value="${selectedDomain?.name?:""}"
		/>
		<br />

		<label for="domain-category-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Category')}">
			Domain Category
		</label>
		<g:select
			name="DCL"
			id="domain-category-list"
			from="${categoriesList.name}"
			value="${selectedDomainCategory?.name?:"" }"
		/>
		<br />

		<ol id="action-word-categories">
			<g:each var="actionWordCategory" in="${actionWordCategoryList.actionWordCategory}">
				<li class="action-word-category ui-state-default">
					${actionWordCategory}
				</li>
			</g:each>
		</ol>

		<select id="action-words">
			<option>
				null
			</option>
		</select>
	</body>
</html>
