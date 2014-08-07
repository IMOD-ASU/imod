<%@ page import="imodv6.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Criteria
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:form action="save">
			<section>
				<g:actionSubmit
					class="save showHoverNew"
					action="save"
					title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}"
					value="${message(code: 'Save', default: 'Save')}"
					id="${imodInstance.id }"
					learningObjectiveID="${learningObjective.id }"
					type="criteria"
				/>
				<input type="hidden" name="id" value="${imodInstance.id}">
				<input type="hidden" name="learningObjectiveID" value="${learningObjective.id}">
				<input type="hidden" name="pageType" value="criteria">
			</section>
			<section>
				<g:checkBox
					id="enable-accuracy"
					name="enableAccuracy"
					value="${learningObjective.criteriaAccuracyEnabled}"
				/>
				<label for="accuracy-text">
					Accuracy
				</label>
				<g:textField
					id="accuracy-text"
					name="accuracy"
					value="${learningObjective.criteriaAccuracy}"
					disabled="${!learningObjective.criteriaAccuracyEnabled}"
				/>
				<g:checkBox
					id="accuracy-hide"
					name="hideAccuracy"
					value="${learningObjective.criteriaAccuracyHidden}"
					disabled="${!learningObjective.criteriaAccuracyEnabled}"
				/>
				<label for="accuracy-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
		 		<g:checkBox
		 			id="enable-quality"
		 			name="enableQuality"
		 			value="${learningObjective.criteriaQualityEnabled}"
		 		/>
				<label for="quality-text">
					Quality
				</label>
				<g:textField
					id="quality-text"
					name="quality"
					value="${learningObjective.criteriaQuality}"
					disabled="${!learningObjective.criteriaQualityEnabled}"
				/>
				<g:checkBox
					id="quality-hide"
					name="hideQuality"
					value="${learningObjective.criteriaQualityHidden}"
					disabled="${!learningObjective.criteriaQualityEnabled}"
				/>
				<label for="quality-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
		 		<g:checkBox
					id="enable-quantity"
					name="enableQuantity"
					value="${learningObjective.criteriaQuantityEnabled}"
				/>
				<label for="quantity-text">
					Quantity
				</label>
				<g:textField
					id="quantity-text"
					name="quantity"
					value="${learningObjective.criteriaQuantity}"
					disabled="${!learningObjective.criteriaQuantityEnabled}"
				/>
				<g:checkBox
					id="quantity-hide"
					name="hideQuantity"
					value="${learningObjective.criteriaQuantityHidden}"
					disabled="${!learningObjective.criteriaQuantityEnabled}"
				/>
				<label for="quantity-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
		 		<g:checkBox
					id="enable-speed"
					name="enableSpeed"
					value="${learningObjective.criteriaSpeedEnabled}"
				/>
				<label for="quantity-text">
					Speed
				</label>
				<g:textField
					id="speed-text"
					name="speed"
					value="${learningObjective.criteriaSpeed}"
					disabled="${!learningObjective.criteriaSpeedEnabled}"
				/>
				<g:checkBox
					id="speed-hide"
					name="hideSpeed"
					value="${learningObjective.criteriaSpeedHidden}"
					disabled="${!learningObjective.criteriaSpeedEnabled}"
				/>
				<label for="speed-hide">
					hide
			 	</label>
		 	</section>
		</g:form>
	</body>
</html>
