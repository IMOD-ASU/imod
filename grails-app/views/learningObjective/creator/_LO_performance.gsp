<script src="${resource(dir: 'js/source', file: 'action.words.js')}" defer></script>
<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'action.words.css')}" type="text/css">

<label for="learning-domain-list">Learning Domain</label>
<g:select name="LDL" id="learning-domain-list" from="${['Cognitive','Domain2','Domain3','Domain4'] }" />
<hr />

<label for="domain-category-list">Domain Category</label>
<g:select name="DCL" id="domain-category-list" from="${['Analyze','Category2','Category3','Category4'] }" />
<hr />

<div id="action-words" class="selectable">
	<g:each var="actionWord" in="${['Differntiating', 'Organizing', 'Attributing']}">
		<div class="ui-state-default">
			${actionWord}
		</div>
	</g:each>
</div>
