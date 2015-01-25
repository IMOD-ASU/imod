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
				<g:actionSubmit action="save" type="criteria" value="${message(code: 'Save', default: 'Save')}" id="${currentImod.id}" learningObjectiveID="${currentLearningObjective.id }" class="save show-hover-new" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
				<g:hiddenField name="id" value="${currentImod.id}" id="current-imod-d" />
				<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="criteria" id="page-type" />
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaAccuracyEnabled}" id="enable-accuracy" name="enableAccuracy" />
				<label for="accuracy-text" class="criteria-label-equal-width">
					Accuracy
				</label>
				<g:textField value="${currentLearningObjective.criteriaAccuracy}" disabled="${!currentLearningObjective.criteriaAccuracyEnabled}" id="accuracy-text" name="accuracy" />
				<g:checkBox value="${currentLearningObjective.criteriaAccuracyHidden}" disabled="${!currentLearningObjective.criteriaAccuracyEnabled}" id="accuracy-hide" name="hideAccuracy" />
				<label for="accuracy-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaQualityEnabled}" id="enable-quality" name="enableQuality" />
				<label for="quality-text" class="criteria-label-equal-width">
					Quality
				</label>
				<g:textField value="${currentLearningObjective.criteriaQuality}" disabled="${!currentLearningObjective.criteriaQualityEnabled}" id="quality-text" name="quality" />
				<g:checkBox id="quality-hide" name="hideQuality" value="${currentLearningObjective.criteriaQualityHidden}" disabled="${!currentLearningObjective.criteriaQualityEnabled}" />
				<label for="quality-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaQuantityEnabled}" id="enable-quantity" name="enableQuantity"	/>
				<label for="quantity-text" class="criteria-label-equal-width">
					Quantity
				</label>
				<g:textField value="${currentLearningObjective.criteriaQuantity}" disabled="${!currentLearningObjective.criteriaQuantityEnabled}" id="quantity-text" name="quantity" />
				<g:checkBox value="${currentLearningObjective.criteriaQuantityHidden}" disabled="${!currentLearningObjective.criteriaQuantityEnabled}" id="quantity-hide" name="hideQuantity" />
				<label for="quantity-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaSpeedEnabled}" id="enable-speed" name="enableSpeed" />
				<label for="quantity-text" class="criteria-label-equal-width">
					Speed
				</label>
				<g:textField value="${currentLearningObjective.criteriaSpeed}" disabled="${!currentLearningObjective.criteriaSpeedEnabled}" id="speed-text" name="speed" />
				<g:checkBox value="${currentLearningObjective.criteriaSpeedHidden}" disabled="${!currentLearningObjective.criteriaSpeedEnabled}" id="speed-hide" name="hideSpeed" />
				<label for="speed-hide">
					hide
				</label>
			</section>
		</g:form>
	</body>
</html>
