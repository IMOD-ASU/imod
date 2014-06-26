<%-- To display Learning Objectives--%>
<li style="width: 288px;padding:3px;"
    class=" ${(params.objectiveId && (params.long('objectiveId') == chapter?.id)) || (!params.objectiveId && (count == 0)) ? 'firstChapter' : ''} chapterLi">
    <g:hiddenField name="objectiveId-${chapter?.id}" value="${chapter?.id}"/>
    <g:link controller="pedagogy" action="reloadPedagogyTab" params="[objectiveId: chapter?.id]" id="${imod?.id}"
            style="text-decoration: none;">
        <span class="informationIcon" title="${chapter?.name}">${(chapter?.name?.length() > grailsApplication.config.objective.length) ? chapter?.name?.substring(0, grailsApplication.config.objective.length) + '...' : chapter?.name} </span>

    </g:link>
</li>
