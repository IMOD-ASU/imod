<%@ page import="imod.Help" %>
<html>
	<head>
		<title>
			Learning Objective - Criteria
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<g:form action="save">
			<fieldset class="learning-objective-button topicButtonGradient">
				<button type="submit" name="_action_save" value="Save" type="criteria" id="${currentImod.id}" learningObjectiveID="${currentLearningObjective?.id }" class="save show-hover-new" title="Click on save button to save data entered on the current sub-tab" ${(learningObjectives) ? '' : 'disabled'}>
					<i class="fa fa-save green"></i>
					Save
				</button>
				<g:hiddenField name="id" value="${currentImod.id}" id="current-imod" />
				<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective?.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="criteria" id="page-type" />
			</fieldset>

			<section>
				<g:checkBox value="${currentLearningObjective?.criteriaAccuracyEnabled}" id="enable-accuracy" name="enableAccuracy" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="accuracy-text" class="criteria-label-equal-width">
					Accuracy
				</label>
				<g:textField value="${currentLearningObjective?.criteriaAccuracy}" disabled="${!currentLearningObjective?.criteriaAccuracyEnabled}" id="accuracy-text" name="accuracy" title="${ message( code:'imod.learningObjective.accuracy' ) }"/>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective?.criteriaAccuracyHidden}" disabled="${!currentLearningObjective?.criteriaAccuracyEnabled}" id="accuracy-hide" name="hideAccuracy" title="${ message( code:'imod.learningObjective.hideAccuracyFromObjective' ) }"  disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="accuracy-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective?.criteriaQualityEnabled}" id="enable-quality" name="enableQuality" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="quality-text" class="criteria-label-equal-width">
					Quality
				</label>
				<g:textField value="${currentLearningObjective?.criteriaQuality}" disabled="${!currentLearningObjective?.criteriaQualityEnabled}" id="quality-text" name="quality" title="${ message( code:'imod.learningObjective.quality' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox id="quality-hide" name="hideQuality" value="${currentLearningObjective?.criteriaQualityHidden}" disabled="${!currentLearningObjective?.criteriaQualityEnabled}" title="${ message( code:'imod.learningObjective.hideQualityFromObjective' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="quality-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective?.criteriaQuantityEnabled}" id="enable-quantity" name="enableQuantity"	disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="quantity-text" class="criteria-label-equal-width">
					Quantity
				</label>
				<g:textField value="${currentLearningObjective?.criteriaQuantity}" disabled="${!currentLearningObjective?.criteriaQuantityEnabled}" id="quantity-text" name="quantity" title="${ message( code:'imod.learningObjective.quantity' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective?.criteriaQuantityHidden}" disabled="${!currentLearningObjective?.criteriaQuantityEnabled}" id="quantity-hide" name="hideQuantity" title="${ message( code:'imod.learningObjective.hideQuantityFromObjective' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="quantity-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective?.criteriaSpeedEnabled}" id="enable-speed" name="enableSpeed" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="quantity-text" class="criteria-label-equal-width">
					Speed
				</label>
				<g:textField value="${currentLearningObjective?.criteriaSpeed}" disabled="${!currentLearningObjective?.criteriaSpeedEnabled}" id="speed-text" name="speed" title="${ message( code:'imod.learningObjective.speed' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective?.criteriaSpeedHidden}" disabled="${!currentLearningObjective?.criteriaSpeedEnabled}" id="speed-hide" name="hideSpeed" title="${ message( code:'imod.learningObjective.hideSpeedFromObjective' ) }" disabled="${(learningObjectives) ? 'false' : 'true'}"/>
				<label for="speed-hide">
					Hide from Students
				</label>
			</section>
		</g:form>
	</body>
</html>
