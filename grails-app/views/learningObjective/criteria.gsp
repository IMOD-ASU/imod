<html>
	<head>
		<title>
			Learning Objective - Criteria
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<form>
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
			</section>
			<section>
				<input id="enable-accuracy" type="checkbox" name="enableAccuracy" value="enableAccuracy">
				<label for="accuracy-text">
					Accuracy
				</label>
				<input id="accuracy-text" type="text">
				<input id="accuracy-hide" type="checkbox" name="hideAccuracy" value="hideAccuracy">
				<label for="accuracy-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-quality" type="checkbox" name="enableQuality" value="enableQuality">
				<label for="quality-text">
					Quality
				</label>
				<input id="quality-text" type="text">
				<input id="quality-hide" type="checkbox" name="hideQuality" value="hideQuality">
				<label for="quality-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-quantity" type="checkbox" name="enableQuantity" value="enableQuantity">
				<label for="quantity-text">
					Quantity
				</label>
				<input id="quantity-text" type="text">
				<input id="quantity-hide" type="checkbox" name="hideQuantity" value="hideQuantity">
				<label for="quantity-hide">
					hide
			 	</label>
		 	</section>

		 	<section>
				<input id="enable-speed" type="checkbox" name="enableSpeed" value="enableSpeed">
				<label for="quantity-text">
					Speed
				</label>
				<input id="speed-text" type="text">
				<input id="speed-hide" type="checkbox" name="hideSpeed" value="hideSpeed">
				<label for="speed-hide">
					hide
			 	</label>
		 	</section>
		</form>
	</body>
</html>
