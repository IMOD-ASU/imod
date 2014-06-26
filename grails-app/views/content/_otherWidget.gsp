<%@ page import="imodv6.Help" %>
<table>
    <tbody>
    <tr style="display: block;">
        <td><div class="topic_addition_widget" style="width: 728px;min-height: 655px;">
            <div class="form_title removeBorder">
                <span style="margin-left: 5px;"><button id="topicSchedule" class="showHover"
                                                        title="${Help.toolTip("CONTENT", "Topic Schedule")}"
                                                        onclick="showTopicSchedule()">Topic Schedule</button>
                </span></div>
            <table style="width: 100%;">
                <tr style="border-bottom: 1px solid gray;">
                    <td>
                        <div style="margin-top: 10px;margin-bottom: 10px;">
                        </div>
                    </td>
                </tr>
                <tr style="border-bottom: 1px solid gray;">
                    <td>
                        <div style="margin-top: 10px;margin-bottom: 10px;">
                            <g:render template="/imod/form_chapter_topic"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="margin-top: 10px;margin-bottom: 10px;" class=""
                             title="${Help.toolTip("CONTENT", "Topic Map")}">
                            <div class="form_title removeBorder">
                                <span style="font-size: 15px;font-weight: bold;">Topic Map</span>
                                <input type="button" name="distributionWidget" value="Topic Distributions"
                                       class="add showHover" onclick="openTopicDistributionPopup()"
                                       title="${imodv6.Help.toolTip("CONTENT", "Topic Distributions")}"></div>

                            <p>Coming soon...</p>
                        </div>
                    </td>
                </tr>
            </table>
        </div></td>

    </tr>
    </tbody>
</table>

<div id="showTopicScheduleTable" title="${Help.toolTip("CONTENT", "Add topic schedule")}">
    <g:form controller="content" action="saveTopicSchedule" name="topicScheduleForm">
        <g:hiddenField name="id" value="${params.id ?: imod?.id}"/>
        <table>
            <tbody>
            <tr>
                <td><input type="button" name="addTopicScheduleButton" onclick="addTopicScheduleAfter()" class="add"
                           value="Add Topic Schedule" style="padding: 0;width:200px;"/>
                </td>
                <td><input type="button" name="removeTopicScheduleButton" onclick="removeTopicScheduleBefore()"
                           style="padding: 0;width:250px;"
                           class="remove" value="Remove Topic Schedule"/></td>
                <td><input type="button" name="saveTopicScheduleButton"
                           onclick="jQuery('form#topicScheduleForm').submit()"
                           class="add" value="Save" style="padding: 0;width:88px;"></td>
            </tr>
            </tbody>
        </table>
        <table id="topicScheduleMainTable"  style="width: 400px;">
            <thead>
            <tr>
                <th>Topic</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody id="showTopicScheduleBody">
            <g:if test="${topicDateForCurrentLearningObjectiveList}">
                <g:each in="${topicDateForCurrentLearningObjectiveList}"
                        var="topicDateForCurrentLearningObjective">
                    <tr style="border:1px solid #000" class='contentTopicSchedule' id="${topicDateForCurrentLearningObjective?.id}" dir="${topicDateForCurrentLearningObjective?.id}">
                        <td>
                            <input type="hidden" name="topicScheduleIds" value="${topicDateForCurrentLearningObjective?.id}"/>
                            <g:select name="scheduleTopicList" class="scheduleTopicList" from="${contentList}"
                                      optionKey="id" value="${topicDateForCurrentLearningObjective?.content?.id}" style="padding: 0;"/>
                        </td>
                        <td>
                            <g:datePicker name="scheduleDate" class="scheduleDate"
                                          precision="day"
                                          value="${topicDateForCurrentLearningObjective?.startDate}"/>
                        </td>
                    </tr>
                </g:each>
            </g:if>
            </tbody>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Update" name="topicScheduleSubmit" style="padding: 0"/>
                    <input type="button" value="Cancel" name="topicScheduleCancel" style="padding: 0"
                           onclick="closeTopicScheduleTable()"/>
                </td>
            </tr>
        </table>
    </g:form>
</div>

<div id="templateForTopicSchedule" style="display: none;">
    <table border="1">
        <tr class='contentResources' dir="0" style="border: 1px solid #000;">
            <td>
                <input type="hidden" name="topicScheduleIds" value="0"/>
                <g:select name="scheduleTopicList" class="scheduleTopicList" from="${contentList}" optionKey="id"
                          value="" style="padding: 0;"/>
            </td>
            <td>
                <g:datePicker name="scheduleDate" class="scheduleDate" precision="day"
                              value=""/>
            </td>
        </tr>
    </table>
</div>
<g:render template="/content/topicInformationGraphWidget"/>

