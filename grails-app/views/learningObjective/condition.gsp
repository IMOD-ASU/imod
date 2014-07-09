<html>
	<head>
		<title>
			Learning Objective - Condition
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<div id="LO_condition_radio_form">
			<input id="LO_condition_rd_generic" class="LO_condition_radio" type="radio" name="LO_condition_type" checked="checked" value="Generic">
			<label for="LO_condition_rd_generic" >
				Generic
			</label>
			<span>
				&nbsp;
			</span>
			<input id="LO_condition_rd_custom" class="LO_condition_radio" type="radio" name="LO_condition_type" value="Custom">
			<label for="LO_condition_rd_custom" >
				Custom
			</label>
		</div>
		<br>
		<textarea id="LO_condition_custom" class="LO_condition_data" rows="8" >
		</textarea>
		<div id="LO_condition_generic">
			<g:select size="5" name="LO_generic" class="LO_condition_data" from="${['Condition1','Condition2','Condition3','Condition4','Condition5'] }" />
		</div>
		<g:checkBox name="LO_hide_from_Objective" title="${imodv6.Help.toolTip('LEARNINGOBJECTIVE', 'Hide from Objective')}"/> Hide from Objective
	</body>
</html>
