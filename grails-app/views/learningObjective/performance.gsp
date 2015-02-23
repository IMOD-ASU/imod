<%@ page import="imod.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Performance
		</title>

		<meta name="layout" content="learningObjective">

		<g:external dir="css/source" file="iconModule.css" />
	</head>
	<body>
		<g:form name="performance" action="save" id="${currentImod.id}">
			<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective.id}" id="learning-objective-id" />
			<g:hiddenField name="pageType" value="performance" id="page-type" />
			<fieldset class="learning-objective-button">
			<g:submitButton class="save show-hover-new" name="update" value="Save" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
			</fieldset>
			<label for="learning-domain-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Domain')}">
				Learning Domain
			</label>
			<g:select name="LDL" id="learning-domain-list" from="${domainList.name}" noSelection="${['null':'Nothing Selected']}" value="${selectedDomain?.name?:""}" />
			<br />

			<label for="domain-category-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Category')}">
				Domain Category
			</label>
			<g:select name="DCL" id="domain-category-list" from="${categoriesList.name}" noSelection="${['null':'Nothing Selected']}" value="${selectedDomainCategory?.name?:"" }" />
			<br />

			<g:hiddenField name="selectedActionWordCategory" value="${selectedActionWordCategory}" id="selected-action-word-category" />

			<div class="action-word-categories">
				<g:each var="actionWordCategory" in="${actionWordCategoryList.actionWordCategory}" status="i">
					<input type="radio" id="radio${i}" name="actionWordCategory" value="${actionWordCategory}"><label for="radio${i}">
						${actionWordCategory}
					</label>
				</g:each>
			</div>

			<select name="actionWord" id="action-words">
				<option>
					${actionWord}
				</option>
			</select>

		</g:form>
	</body>
</html>
