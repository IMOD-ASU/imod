<g:if test="${ it.isEmpty() }">
	Empty Learning Objective
</g:if>

<!-- Definition of the conditions for completing a learning objective -->
<g:if test="${ !it.hideFromLearningObjectiveCondition }">
	<span class="learning-objective-condition">
		<g:if test="${ it.condition != null }">
			${ it.condition }
		</g:if>
	</span>
</g:if>

<!-- Definition of the perfomance evaulations for a learning objective -->
<span class="learning-objective-performance">
	<g:if test="${ it.performance != null }">
		${ it.actionWord }
	</g:if>
</span>

<!-- Definition of the criteria for understanding a learning objective -->

<span class="learning-objective-criteria">
	<g:if test="${ it.criteriaAccuracy != null && it.criteriaAccuracyHidden == false }">
		${ it.criteriaAccuracy }
	</g:if>

	<g:if test="${ it.criteriaQuality != null && it.criteriaQualityHidden == false }">
		${ it.criteriaQuality }
	</g:if>

	<g:if test="${ it.criteriaQuantity != null && it.criteriaQuantityHidden == false }">
		${ it.criteriaQuantity }
	</g:if>

	<g:if test="${ it.criteriaSpeed != null && it.criteriaSpeedHidden == false }">
		${ it.criteriaSpeed }
	</g:if>
</span>

<!-- Definition of the indicators used to measure progress through learning objective -->
<span class="learning-objective-indicator">
	<g:if test="${ it.indicator != null }">
		${ it.indicator }
	</g:if>
</span>
