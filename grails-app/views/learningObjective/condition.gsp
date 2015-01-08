<%@ page import="imodv6.LearningObjective" %>
<%@ page import="imodv6.Help" %>
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
				<g:hiddenField name="learningObjectiveID" value="${learningObjective.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="condition" id="page-type" />
			</fieldset>
			<div class="learning-objective condition radio-form">
				<g:radio name="conditionType" checked="${!isCustom}" value="Generic" id="LO-condition-rd-generic" class="LO-condition-radio" />
				<label for="LO_condition_rd_generic" >
					Generic
				</label>
				<span>
					&nbsp;
				</span>
				<g:radio name="conditionType" checked="${isCustom}" value="Custom" id="LO-condition-rd-custom" class="LO-condition-radio" />
				<label for="LO-condition-rd-custom" >
					Custom
				</label>
			</div>
			<br />
			<g:textArea name="customCondition" value="${currentCondition}" id="LO-condition-custom" class="LO-condition-data" rows="8" />
			<div id="LO-condition-generic">
				<g:select size="5" name="genericCondition" class="LO-condition-data" from="${LearningObjective.genericConditions}" value="${currentCondition}" />
			</div>
			<g:checkBox name="hideCondition" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Hide from Objective')}" checked="${hideCondition}" />
			Hide from Objective
		</g:form>
	</body>
</html>
