<li style="width: 288px;" class=" ${(params.objectiveId && (params.long('objectiveId') == chapter?.id)) || (!params.objectiveId && (count == 0)) ? 'firstChapter' : ''} chapterLi">
	<g:hiddenField name="objectiveId-${chapter?.id}" value="${chapter?.id}"/>
	<g:link controller="content" action="reloadContentTab" params="[objectiveId: chapter?.id]" id="${imod?.id}" style="text-decoration: none;">
		<span class="showHover" title="${chapter?.name}">${(chapter?.name?.length() > grailsApplication.config.objective.length) ? chapter?.name?.substring(0, grailsApplication.config.objective.length) + '...' : chapter?.name}</span> <img
			title="${chapter?.name}"     src="${resource(plugin: 'famfamfam', dir: 'images/icons', file: 'information.png')}" class="informationIcon"
			style="float: right;"/>
	</g:link>
</li>
