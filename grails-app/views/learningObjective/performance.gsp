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
			<g:submitButton class="learning-objective-button save showHoverNew" name="update" value="Save" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />

			<label for="learning-domain-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Domain')}">
				Learning Domain
			</label>
			<g:select
				name="LDL"
				id="learning-domain-list"
				from="${domainList.name}"
				noSelection="${['null':'Nothing Selected']}"
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
				noSelection="${['null':'Nothing Selected']}"
				value="${selectedDomainCategory?.name?:"" }"
			/>
			<br />

		
			<div id="action-word-categories">
				<g:each var="actionWordCategory" in="${actionWordCategoryList.actionWordCategory}">

					${selectedActionWordCategory} == ${actionWordCategory}

					<g:if test="${actionWordCategory == selectedActionWordCategory}">
						<input type="radio" id="radio1" name="actionWordCategory"><label for="radio1" checked>${actionWordCategory}</label>
					</g:if>
					<g:else>
						<input type="radio" id="radio1" name="actionWordCategory"><label for="radio1">${actionWordCategory}</label>
					</g:else>					
				</g:each>
			</div>

			<select id="action-words">
				<option>
					null
				</option>
			</select>

		</g:form>
	</body>
</html>
