<ul>
	<li>
		<g:each in="${learningObjectiveInstanceList}" status="i" var="learningObjective">
			<g:link action="edit" id="${learningObjective.id}" class="square">
				${fieldValue(bean: learningObjective, field: "name")}
			</g:link>
		</g:each>
	</li>
</ul>
