<!--  To display Learning Objectives and the Filter Options -->
<table style="padding-top: 0;">
    <tbody>
        <tr>
            <td style="padding-right: 0;">
                <div class="chapter_addition_widget" style="width: 310px; min-height: 654px;margin-right:10px;">
                    <div class="form_title removeBorder" >
                        <span class="title_text">
                            <g:message code="imod.objectives.widget.title2"/>
                        </span>
                        <span style="float: right">
                            %{--
                                <button id="createChapter" class="add showHover" title="${Help.toolTip("CONTENT", "Add Learning Objective")}" onclick="showObjective()">
                                    <g:message code="chapter.addition.widget.add.button"/>
                                </button>
                                <button id="deleteChapter" class="remove showHover" onclick="removeAllObjective()" title="${Help.toolTip("CONTENT", "Remove Learning Objective")}">
                                    <g:message code="learning.objectives..widget.remove.button"/>
                                </button>
                            --}%
                        </span>
                    </div>

                    <div>
                        <div style="margin-top: 20px; height: 320px; overflow:scroll; overflow-x: hidden;" id="chapterListTable">
                            <p>
                                <ul style="margin-left: 10px;" id="chapterListUL">
                                    <g:if test="${objectiveList}">
                                        <g:each in="${objectiveList}" var="chapter" status="count">
                                            <g:render template="/pedagogy/chapterListTableTemplate" model="[chapter: chapter, count: count]"/>
                                        </g:each>
                                    </g:if>
                                    <g:else>
                                        <div class="graphErrorMessage">
                                            No learning objective available
                                        </div>
                                    </g:else>
                                    <div id="objectiveQtipPlace" style="margin-left: 120px;"></div>
                                </ul>
                            </p>
                        </div>

                        <div style="width: 250px;">
                            <div class="form_title removeBorder">
                                <span class="title_text">
                                    <g:message code="Filter Options"/>
                                </span>
                                <span style="float: right;padding-right: 7px;" class="title_text">
                                    <a href="javascript:" style="text-decoration:none;color:#FFFFFF" onclick="submitRefresh();" title="${Help.toolTip("PEDAGOGY", "Refresh")}" class="showHover">
                                        Refresh
                                    </a>
                                    %{--
                                        <button id="createChapter" class="add showHover" title="${Help.toolTip("CONTENT", "Add Filter options")}" onclick="showObjective()">
                                            <g:message code="chapter.addition.widget.add.button"/>
                                        </button>
                                        <button id="deleteChapter" class="remove showHover" onclick="removeAllObjective()" title="${Help.toolTip("CONTENT", "Remove Filter options")}">
                                            <g:message code="learning.objectives..widget.remove.button"/>
                                        </button>
                                    --}%
                                </span>
                            </div>

                            <g:formRemote name="pedAccordion" id="pedAccordion" url="[action:'updateExtendedTechnique',controller:'pedagogy']" update="extendedMatchDiv">
                                <div id="ped_accordion">
                                    <h3 title="${Help.toolTip("PEDAGOGY", "Domain")}" class="showHover">
                                        Domain
                                    </h3>
                                    <div>
                                        <p>
                                            <g:each in="${lrnDomainlist}" var="domain">
                                                <g:if test="${domain.toString().equals(dmn.toString())}">
                                                    <input type="checkbox" name="domain" value="${domain.toString()}" checked="checked">
                                                </g:if>
                                                <g:else>
                                                    <input type="checkbox" name="domain" value="${domain.toString()}">
                                                </g:else>
                                                <label>
                                                    ${domain.toString()}
                                                </label>
                                                <br>
                                            </g:each>
                                        </p>
                                    </div>
                                    <h3 title="${Help.toolTip("PEDAGOGY", "Domain Category")}" class="showHover">
                                        Domain Category
                                    </h3>
                                    <div>
                                        <p>
                                            <g:each in="${domainList}" var="domain">
                                                <g:if test="${domain.toString().equals(dc.toString())}">
                                                    <input type="checkbox" name="domainCategory" value="${domain.toString()}" checked="checked">
                                                </g:if>
                                                <g:else>
                                                    <input type="checkbox" name="domainCategory" value="${domain.toString()}">
                                                </g:else>
                                                <label>
                                                    ${domain.toString()}
                                                </label>
                                                <br>
                                            </g:each>
                                        </p>
                                    </div>

                                    <h3 title="${Help.toolTip("PEDAGOGY", "Knowledge Dimension")}" class="showHover">
                                        Knowledge Dimension
                                    </h3>
                                    <div>
                                        <p>
                                            <g:each in="${mapkdList.entrySet()}" var="kdomain">
                                                <input type="checkbox" name="kdomain" value="${kdomain.key.toString()}" ${kdomain.value.toString().equals('true')?'checked':''}>
                                                <label>
                                                    ${kdomain.key.toString()}
                                                </label>
                                                <br>
                                            </g:each>
                                        </p>
                                    </div>
                                </div>
                            </g:formRemote>
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
        <td>
            <g:textField name="addObjectiveName" value="" placeholder="Objective name" style="height: 28px;margin: 10px;"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" name="addObjective" value="Add" onclick="saveLearningObjective()" style="height: 34px;margin-left: 5px;">
            <input type="button" name="addObjective" value="Cancel" onclick="closeLearningObjective()" style="height: 34px;">
        </td>
    </tr>
</table>
