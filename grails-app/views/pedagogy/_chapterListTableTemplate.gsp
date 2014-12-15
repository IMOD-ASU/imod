<%-- To display Learning Objectives--%>
<li style="width: 288px;padding:3px;">
    <g:hiddenField name="objectiveId-${chapter?.id}" value="${chapter?.id}"/>
    <g:link controller="pedagogy" action="reloadPedagogyTab" params="[objectiveId: chapter?.id]" id="${currentImod?.id}" style="text-decoration: none;">
        <span class="informationIcon" title="${chapter?.name}">
            ${chapter?.name}
        </span>
    </g:link>
</li>
