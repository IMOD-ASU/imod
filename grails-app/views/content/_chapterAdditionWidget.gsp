<%@ page import="imodv6.Help" %>
<table>
    <tbody>
    <tr>
        <td style="padding-right: 0;">
            <div class="chapter_addition_widget" style="width: 310px; min-height: 656px;margin-right:10px;">
                <div class="form_title removeBorder"><span class="title_text"><g:message
                        code="imod.objectives.widget.title2"/></span>
                    <span style="float: right">%{--<button id="createChapter" class="add showHover"
                                                       title="${Help.toolTip("CONTENT", "Add Learning Objective")}"
                                                       onclick="showObjective()"><g:message
                                code="chapter.addition.widget.add.button"/></button>
                        <button id="deleteChapter" class="remove showHover" onclick="removeAllObjective()"
                                title="${Help.toolTip("CONTENT", "Remove Learning Objective")}">
                            <g:message code="learning.objectives..widget.remove.button"/></button>--}%
                    </span></div>

                <div>
                    <div style="margin-top: 20px;" id="chapterListTable">
                        <ul style="margin-left: 10px;" id="chapterListUL">
                            <g:if test="${objectiveList}">
                                <g:each in="${objectiveList}"
                                        var="chapter"
                                        status="count">
                                    <g:render template="/content/chapterListTableTemplate"
                                              model="[chapter: chapter, count: count]"/>
                                </g:each>
                            </g:if>
                            <g:else>
                                <div class="graphErrorMessage">No learning objective available</div>
                            </g:else>
                            <div id="objectiveQtipPlace" style="margin-left: 120px;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<table id="addLearningObjectiveDiv" title="${Help.toolTip("CONTENT", "Add Objective")}">
    <tr>
        <td></td>
        <td><g:textField name="addObjectiveName" value="" placeholder="Objective name"
                         style="height: 28px;margin: 10px;"/></td>
    </tr>
    <tr>
        <td colspan="2"><input type="button" name="addObjective" value="Add" onclick="saveLearningObjective()"
                               style="height: 34px;margin-left: 5px;">
            <input type="button" name="addObjective" value="Cancel" onclick="closeLearningObjective()"
                   style="height: 34px;">
        </td>
    </tr>
</table>
