<%@ page import="imod.LearningObjective" %>
<%@ page import="imod.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Condition
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:form action="save" >
			<fieldset class="learning-objective-button">
				<g:actionSubmit action="save" value="${message(code: 'Save', default: 'Save')}" class="save show-hover-new" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
				<g:hiddenField name="id" value="${currentImod.id}" id="current-imod-d" />
				<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="condition" id="page-type" />
			</fieldset>
			<div class="learning-objective condition radio-form">
				<g:radio name="conditionType" checked="${!isCustom}" value="Generic" id="condition-generic" class="learning-objective condition radio" />
				<label for="condition-generic" >
					Generic
				</label>
				<span>
					&nbsp;
				</span>
				<g:radio name="conditionType" checked="${isCustom}" value="Custom" id="condition-custom" class="learning-objective condition radio" />
				<label for="condition-custom" >
					Custom
				</label>
			</div>
			<br />
			<g:textArea name="customCondition" value="${currentCondition}" id="custom-condition-text" class="learning-objective condition custom-text" rows="8" />
			<div class="learning-objective condition generic">
				<g:select size="5" name="genericCondition" from="${LearningObjective.genericConditions}" value="${currentCondition}" class="learning-objective condition generic text" id="generic-condition-text" />
			</div>
			<g:checkBox name="hideCondition" checked="${hideCondition}" id="hide-condition" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Hide from Objective')}" />
			Hide from Objective
		</g:form>
	</body>
</html>
