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
				<g:actionSubmit action="save" value="${message(code: 'Save', default: 'Save')}" class="save showHoverNew" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
				<g:hiddenField name="id" value="${currentImod.id}" />
				<g:hiddenField name="learningObjectiveID" value="${learningObjective.id}" />
				<g:hiddenField name="pageType" value="condition" />
			</fieldset>
			<div id="LO-condition-radio-form">
				<!-- FIXME replace with g:radio -->
				<input id="LO-condition-rd-generic" class="LO-condition-radio" type="radio" name="LO_condition_type" <g:if test="${!isCustom}">checked="checked"</g:if>	value="Generic" />
				<label for="LO_condition_rd_generic" >
					Generic
				</label>
				<span>
					&nbsp;
				</span>
				<!-- FIXME replace with g:radio -->
				<input id="LO-condition-rd-custom" class="LO-condition-radio" type="radio" name="LO_condition_type" <g:if test="${isCustom}">checked="checked"</g:if> value="Custom" />
				<label for="LO-condition-rd-custom" >
					Custom
				</label>
			</div>
			<br />
			<textarea name="LO_custom" id="LO-condition-custom" class="LO-condition-data" rows="8" />
			<div id="LO-condition-generic">
				<g:select size="5" name="LO_generic" class="LO-condition-data" from="${LearningObjective.genericConditions }" value="${currentCondition}" />
			</div>
			<g:checkBox name="LO_hide_from_Objective" title="${Help.toolTip('LEARNINGOBJECTIVE', 'Hide from Objective')}" checked="${hideCondition}" />
			Hide from Objective
		</g:form>
	</body>
</html>
