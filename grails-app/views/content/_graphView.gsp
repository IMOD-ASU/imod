<div id="piechart" style=""></div>
<g:if test="${contentPriorityMap}">
    <gvisualization:pieCoreChart elementId="piechart" title="" width="${540}" height="${300}" dynamicLoading="${true}"
                                 columns="${topicInformationColumns}" data="${contentPriorityMap}"
                                 sliceVisibilityThreshold="${0}"/>
</g:if>
<g:else>
    <div class="graphErrorMessage"><g:message code="imod.topicInformationGraph.widget.message"/></div>
</g:else>