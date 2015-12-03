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
			<fieldset class="learning-objective-button topicButtonGradient">
				<button type="submit" name="_action_save" value="Save" class="save show-hover-new" title="Click on save button to save data entered on the current sub-tab" ${(learningObjectives) ? '' : 'disabled'}>
					<i class="fa fa-save green"></i> Save
				</button>
				<g:hiddenField name="id" value="${currentImod.id}" id="current-imod-d" />
				<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective?.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="condition" id="page-type" />
			</fieldset>
			<div class="learning-objective condition radio-form">
				<g:radio name="conditionType" checked="${isCustom}" value="Generic" id="condition-generic" class="learning-objective condition radio" title="${ message( code:'imod.learningObjective.genericCondition' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="condition-generic">
					Generic
				</label>
				<span>
					&nbsp;
				</span>
				<g:radio name="conditionType" checked="${!isCustom}" value="Custom" id="condition-custom" class="learning-objective condition radio" title="${ message( code:'imod.learningObjective.customCondition' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="condition-custom">
					Custom
				</label>
			</div>
			<br />
			<g:textArea name="customCondition" value="${currentCustomCondition}" id="custom-condition-text" class="learning-objective condition custom-text" rows="8" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
			<div class="learning-objective condition generic">
				<g:select size="5" name="genericCondition" from="${LearningObjective.genericConditions}" value="${currentCondition}" class="learning-objective condition generic text" id="generic-condition-text" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
			</div>
			<g:checkBox name="hideCondition" checked="${hideCondition}" id="hide-condition" title="${ message( code:'imod.learningObjective.hideFromObjective' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
			Hide from Students
		</g:form>
	</body>
</html>
