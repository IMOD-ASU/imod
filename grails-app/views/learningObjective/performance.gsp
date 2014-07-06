<html>
	<head>
		<title>
			Course Overview
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<label for="learning-domain-list">
			Learning Domain
		</label>
		<g:select name="LDL" id="learning-domain-list" from="${['Cognitive','Domain2','Domain3','Domain4'] }" />
		<br />

		<label for="domain-category-list">
			Domain Category
		</label>
		<g:select name="DCL" id="domain-category-list" from="${['Analyze','Category2','Category3','Category4'] }" />
		<br />

		<ol id="action-words">
			<g:each var="actionWord" in="${['Differntiating', 'Organizing', 'Attributing']}">
				<li class="action-word ui-state-default">
					${actionWord}
				</li>
			</g:each>
		</ol>
	</body>
</html>
