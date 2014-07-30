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
				<input id="enable-accuracy" name="enableAccuracy" type="checkbox" value="enableAccuracy">
				<label for="accuracy-text">
					Accuracy
				</label>
				<input id="accuracy-text" name="accuracy" type="text">
				<input id="accuracy-hide" name="hideAccuracy" type="checkbox" value="hideAccuracy">
				<label for="accuracy-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-quality" name="enableQuality"  type="checkbox" value="enableQuality">
				<label for="quality-text">
					Quality
				</label>
				<input id="quality-text" name="quality" type="text">
				<input id="quality-hide" name="hideQuality" type="checkbox"  value="hideQuality">
				<label for="quality-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-quantity" name="enableQuantity" type="checkbox" value="enableQuantity">
				<label for="quantity-text">
					Quantity
				</label>
				<input id="quantity-text" name="quantity" type="text">
				<input id="quantity-hide" name="hideQuantity" type="checkbox" value="hideQuantity">
				<label for="quantity-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-speed" name="enableSpeed" type="checkbox" value="enableSpeed">
				<label for="quantity-text">
					Speed
				</label>
				<input id="speed-text" name="speed" type="text">
				<input id="speed-hide" name="hideSpeed" type="checkbox" value="hideSpeed">
				<label for="speed-hide">
					hide
			 	</label>
		 	</section>
		</g:form>
	</body>
</html>
