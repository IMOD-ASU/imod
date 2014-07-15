<%@ page import="imodv6.LearningObjective; imodv6.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Condition
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:form action="save" >
			<fieldset class="LO_buttons">
				<g:actionSubmit 
					class="save showHoverNew" 
					action="save" 
					title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}"  
					value="${message(code: 'Save', default: 'Save')}"
				/>
				<input type="hidden" name="id" value="${imodInstance.id}">
				<input type="hidden" name="learningObjectiveID" value="${learningObjective.id}">
				<input type="hidden" name="pageType" value="condition">
			</fieldset>
			<div id="LO_condition_radio_form">
				<input 
					id="LO_condition_rd_generic" 
					class="LO_condition_radio" 
					type="radio" 
					name="LO_condition_type" 
					<g:if test="${!isCustom}">checked="checked"</g:if> 
					value="Generic"
				>
				<label for="LO_condition_rd_generic" >
					Generic
				</label>
				<span>
					&nbsp;
				</span>
				<input 
					id="LO_condition_rd_custom" 
					class="LO_condition_radio" 
					type="radio" 
					name="LO_condition_type"
					<g:if test="${isCustom}">checked="checked"</g:if> 
					value="Custom"
				>
				<label for="LO_condition_rd_custom" >
					Custom
				</label>
			</div>
			<br>
			<textarea name="LO_custom" id="LO_condition_custom" class="LO_condition_data" rows="8" ><g:if test="${isCustom}">${currentCondition}</g:if></textarea>
			<div id="LO_condition_generic">
				<g:select 
					size="5" 
					name="LO_generic" 
					class="LO_condition_data" 
					from="${LearningObjective.genericConditions }"
					value="${currentCondition}" 
				/>
			</div>
			<g:checkBox 
				name="LO_hide_from_Objective" 
				title="${imodv6.Help.toolTip('LEARNINGOBJECTIVE', 'Hide from Objective')}"
				checked="${hideCondition}"
			/> Hide from Objective
		</g:form>
	</body>
</html>
