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

<span class="learning-objective-content">
	<g:if test="${ it.contents != null }">
		<%-- One item in list --%>
		<g:if test="${ it.contents.size() < 2 }">
			<g:set var="contentItem" value="${it.contents[0]}" />
			<g:if test="${ contentItem.topicTitle != null }">
				${ contentItem.topicTitle }
			</g:if>
		</g:if>
		<%-- Two items in list --%>
		<g:elseif test="${ it.contents.size() == 2 }">
			<g:set var="contentItem" value="${it.contents[0]}" />
			<g:if test="${ contentItem.topicTitle != null }">
				${ contentItem.topicTitle }
			</g:if>
			and
			<g:set var="contentItem" value="${it.contents[1]}" />
			<g:if test="${ contentItem.topicTitle != null }">
				${ contentItem.topicTitle }
			</g:if>
		</g:elseif>
		<%-- Many items in list --%>
		<g:else>
			<g:each in="${ it.contents }" var="contentItem" status="contentIndex">
				<g:if test="${ contentItem.topicTitle != null }">
					<g:if test="${ contentIndex > 0 }">
						,&nbsp;
					</g:if>
					<g:if test="${ contentIndex == it.contents.size() - 1 }">
						and&nbsp;
					</g:if>
					${ contentItem.topicTitle }
				</g:if>
			</g:each>
		</g:else>
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
