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
			<fieldset class="learning-objective-button topicButtonGradient">
				<button type="submit" class="save show-hover-new" id="performance-save" name="update" value="Save" title="Click on save button to save data entered on the current sub-tab">
					<i class="fa fa-save green"></i>
					Save
				</button>
			</fieldset>

			<label for="learning-domain-list" class="learning-domain-list title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Domain')}">
				Learning Domain
			</label>
			&nbsp;<g:select name="LDL" id="learning-domain-list" class="custom-dropdown" from="${domainList.name}" noSelection="${['null':'-- Select --']}" value="${selectedDomain?.name?:""}" title="${ message( code:'imod.learningObjective.learningDomain' ) }" />
			<br/>
			<div style="height:5px"></div>
			<label for="domain-category-list" class="domain-category-list" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Learning Category')}">
				Domain Category
			</label>
			<g:select name="DCL" id="domain-category-list" class="custom-dropdown" from="${categoriesList.name}" noSelection="${['null':'Nothing Selected']}" value="${selectedDomainCategory?.name?:"" }" title="${ message( code:'imod.learningObjective.domainCategory' ) }" />
			<br/>

			<g:hiddenField name="selectedActionWordCategory" value="${selectedActionWordCategory}" id="selected-action-word-category" />

			<label for="action-word-category" class="action-word-category">
				Action Word Category
			</label>

			<br>
			<div class="icons assign">
				<g:each var="actionWordCategory" in="${actionWordCategoryList.actionWordCategory}" status="i">
					<input type="radio" id="radio${i}" name="actionWordCategory" value="${actionWordCategory}"/><label for="radio${i}">
						${actionWordCategory}
					</label>
				</g:each>
			</div>
			<label for="action-words" class="action-words">
			Action Word
			</label>
			<select name="actionWord" id="action-words" title="${ message( code:'imod.learningObjective.actionWord' ) }" class="custom-dropdown" style="margin-bottom:5px;width:180px">
				<option>
					${actionWord}
				</option>
			</select>
			<br/>
			<input name="customActionWord" type="text" id="custom-action-words" value="Enter the details here" style="margin-bottom:5px;width:180px">
		</g:form>

		<div id="please-select-action-word" title="Please Select Action Word">
            <p>
                Please select an action word or select --Other-- field at the end of list
            </p>
        </div>
	</body>
</html>
