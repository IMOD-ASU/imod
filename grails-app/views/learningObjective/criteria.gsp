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
			<fieldset class="learning-objective-button">
				<g:actionSubmit action="save" type="criteria" value="${message(code: 'Save', default: 'Save')}" id="${currentImod.id}" learningObjectiveID="${currentLearningObjective.id }" class="save show-hover-new" title="${ message( code:'imod.learningObjective.save' ) }" />
				<g:hiddenField name="id" value="${currentImod.id}" id="current-imod" />
				<g:hiddenField name="learningObjectiveID" value="${currentLearningObjective.id}" id="learning-objective-id" />
				<g:hiddenField name="pageType" value="criteria" id="page-type" />
			</fieldset>

			<!--<section>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span >Hide from Students</span>
			</section>-->

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaAccuracyEnabled}" id="enable-accuracy" name="enableAccuracy" />
				<label for="accuracy-text" class="criteria-label-equal-width">
					Accuracy
				</label>
				<g:textField value="${currentLearningObjective.criteriaAccuracy}" disabled="${!currentLearningObjective.criteriaAccuracyEnabled}" id="accuracy-text" name="accuracy" title="${ message( code:'imod.learningObjective.accuracy' ) }"/>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective.criteriaAccuracyHidden}" disabled="${!currentLearningObjective.criteriaAccuracyEnabled}" id="accuracy-hide" name="hideAccuracy" title="${ message( code:'imod.learningObjective.hideAccuracyFromObjective' ) }"  />
				<label for="accuracy-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaQualityEnabled}" id="enable-quality" name="enableQuality" />
				<label for="quality-text" class="criteria-label-equal-width">
					Quality
				</label>
				<g:textField value="${currentLearningObjective.criteriaQuality}" disabled="${!currentLearningObjective.criteriaQualityEnabled}" id="quality-text" name="quality" title="${ message( code:'imod.learningObjective.quality' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox id="quality-hide" name="hideQuality" value="${currentLearningObjective.criteriaQualityHidden}" disabled="${!currentLearningObjective.criteriaQualityEnabled}" title="${ message( code:'imod.learningObjective.hideQualityFromObjective' ) }"/>
				<label for="quality-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaQuantityEnabled}" id="enable-quantity" name="enableQuantity"	/>
				<label for="quantity-text" class="criteria-label-equal-width">
					Quantity
				</label>
				<g:textField value="${currentLearningObjective.criteriaQuantity}" disabled="${!currentLearningObjective.criteriaQuantityEnabled}" id="quantity-text" name="quantity" title="${ message( code:'imod.learningObjective.quantity' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective.criteriaQuantityHidden}" disabled="${!currentLearningObjective.criteriaQuantityEnabled}" id="quantity-hide" name="hideQuantity" title="${ message( code:'imod.learningObjective.hideQuantityFromObjective' ) }"/>
				<label for="quantity-hide">
					Hide from Students
				</label>
			</section>

			<section>
				<g:checkBox value="${currentLearningObjective.criteriaSpeedEnabled}" id="enable-speed" name="enableSpeed" />
				<label for="quantity-text" class="criteria-label-equal-width">
					Speed
				</label>
				<g:textField value="${currentLearningObjective.criteriaSpeed}" disabled="${!currentLearningObjective.criteriaSpeedEnabled}" id="speed-text" name="speed" title="${ message( code:'imod.learningObjective.speed' ) }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<g:checkBox value="${currentLearningObjective.criteriaSpeedHidden}" disabled="${!currentLearningObjective.criteriaSpeedEnabled}" id="speed-hide" name="hideSpeed" title="${ message( code:'imod.learningObjective.hideSpeedFromObjective' ) }"/>
				<label for="speed-hide">
					Hide from Students
				</label>
			</section>
		</g:form>
	</body>
</html>
