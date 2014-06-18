<g:if test="${contentScheduleList}">

    <div class="fieldcontain">
        <div id="clickthis"><g:link controller="contentSchedule" action="create"
                                    params="['imod.id': imodInstance?.id]"><g:message
                    code="add.schedule.label"/></g:link>
        </div>

        <div id="custom_topic" style="min-height: 255px;max-height: 255px;overflow-y: scroll;">
            <table id="topic_table">
                <thead>
                <tr>
                    <g:sortableColumn property="date"
                                      title="${message(code: 'imod.topicSchedule.date.column.label', default: 'Date')}"/>
                    <g:sortableColumn property="content"
                                      title="${message(code: 'imod.topicSchedule.topicName.column.label', default: 'Topic Name')}"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${contentScheduleList}" var="contentSchedule">
                    <tr>
                        <td><g:textField name="startDate" value="${contentSchedule?.startDate}"/></td>
                        <td><g:select name="contentSelect" from="${currentImodContentList}"
                                      value="${contentSchedule?.content}" style="width: 101px;"/></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</g:if>
<g:else>
    <div class="topicScheduleEmptyMessage"><g:message code="imod.schedule.empty.widget.message" /></div>
</g:else>