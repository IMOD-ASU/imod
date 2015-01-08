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
				<g:actionSubmit action="save" type="criteria" value="${message(code: 'Save', default: 'Save')}" id="${currentImod.id }" learningObjectiveID="${learningObjective.id }" class="save show-hover-new" title="${Help.toolTip("OVERVIEW", "Save Learning Objective")}" />
				<!-- FIXME replace with g:hiddenField -->
				<input type="hidden" name="id" value="${currentImod.id}">
				<!-- FIXME replace with g:hiddenField -->
				<input type="hidden" name="learningObjectiveID" value="${learningObjective.id}">
				<!-- FIXME replace with g:hiddenField -->
				<input type="hidden" name="pageType" value="criteria">
			</section>

			<section>
				<g:checkBox value="${learningObjective.criteriaAccuracyEnabled}" id="enable-accuracy" name="enableAccuracy" />
				<label for="accuracy-text" class="criteria-label-equal-width">
					Accuracy
				</label>
				<g:textField value="${learningObjective.criteriaAccuracy}" disabled="${!learningObjective.criteriaAccuracyEnabled}" id="accuracy-text" name="accuracy" />
				<g:checkBox value="${learningObjective.criteriaAccuracyHidden}" disabled="${!learningObjective.criteriaAccuracyEnabled}" id="accuracy-hide" name="hideAccuracy" />
				<label for="accuracy-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${learningObjective.criteriaQualityEnabled}" id="enable-quality" name="enableQuality" />
				<label for="quality-text" class="criteria-label-equal-width">
					Quality
				</label>
				<g:textField value="${learningObjective.criteriaQuality}" disabled="${!learningObjective.criteriaQualityEnabled}" id="quality-text" name="quality" />
				<g:checkBox id="quality-hide" name="hideQuality" value="${learningObjective.criteriaQualityHidden}" disabled="${!learningObjective.criteriaQualityEnabled}" />
				<label for="quality-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${learningObjective.criteriaQuantityEnabled}" id="enable-quantity" name="enableQuantity"	/>
				<label for="quantity-text" class="criteria-label-equal-width">
					Quantity
				</label>
				<g:textField value="${learningObjective.criteriaQuantity}" disabled="${!learningObjective.criteriaQuantityEnabled}" id="quantity-text" name="quantity" />
				<g:checkBox value="${learningObjective.criteriaQuantityHidden}" disabled="${!learningObjective.criteriaQuantityEnabled}" id="quantity-hide" name="hideQuantity" />
				<label for="quantity-hide">
					hide
				</label>
			</section>

			<section>
				<g:checkBox value="${learningObjective.criteriaSpeedEnabled}" id="enable-speed" name="enableSpeed" />
				<label for="quantity-text" class="criteria-label-equal-width">
					Speed
				</label>
				<g:textField value="${learningObjective.criteriaSpeed}" disabled="${!learningObjective.criteriaSpeedEnabled}" id="speed-text" name="speed" />
				<g:checkBox value="${learningObjective.criteriaSpeedHidden}" disabled="${!learningObjective.criteriaSpeedEnabled}" id="speed-hide" name="hideSpeed" />
				<label for="speed-hide">
					hide
				</label>
			</section>
		</g:form>
	</body>
</html>
