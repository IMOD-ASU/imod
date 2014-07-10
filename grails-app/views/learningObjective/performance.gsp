<%@ page import="imodv6.Help; imodv6.Imod; imodv6.ImodUser;" %>
<html>
	<head>
		<title>
			Learning Objective - Performance
		</title>

		<meta name="layout" content="learningObjective">
	</head>
	<body>
		<label for="learning-domain-list" title="${imodv6.Help.toolTip('LEARNINGOBJECTIVE', 'Learning Domain')}">
			Learning Domain
		</label>
		<g:select name="LDL" id="learning-domain-list" from="${domainList.name}" />
		<br />

		<label for="domain-category-list" title="${imodv6.Help.toolTip('LEARNINGOBJECTIVE', 'Learning Category')}">
			Domain Category
		</label>
		<g:select name="DCL" id="domain-category-list" from="${domainList[0].domainCategories}"  />
		<br />

		<ol id="action-words">
			<g:each var="actionWord" in="${['Differentiating', 'Organizing', 'Attributing']}">
				<li class="action-word ui-state-default">
					${actionWord}
				</li>
			</g:each>
		</ol>
	</body>
</html>
