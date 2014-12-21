<%@ page import="imodv6.Help" %>
<html>
    <head>
        <title>
            Learning Objective - Performance
        </title>

        <meta name="layout" content="imod">
        <script src="${resource(dir: 'js/source', file: 'pedagogy.pedagogyObjectiveTemplate.gsp.js')}" defer></script>
    </head>
    <body>
        <div id="edit-imod" class="content scaffold-edit" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">
                    ${flash.message}
                </div>
            </g:if>

            <g:hasErrors bean="${currentImod}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${currentImod}" var="error">
                        <li>
                            <g:message error="${error}"/>
                        </li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <table>
                <tr>
                    <td style="vertical-align: top;padding:-50;">
                        <table style="padding-top: 0;">
                            <tbody>
                                <tr>
                                    <td style="padding-right: 0;">
                                        <div class="chapter_addition_widget" style="width: 310px; min-height: 654px;margin-right:10px;">
                                            <div class="form_title removeBorder" >
                                                <span class="title_text">
                                                    <g:message code="currentImod.objectives.widget.title2"/>
                                                </span>
                                            </div>

                                            <div>
                                                <div style="margin-top: 20px; height: 320px; overflow:scroll; overflow-x: hidden;" id="chapterListTable">
                                                    <p>
                                                        <ul style="margin-left: 10px;" id="chapterListUL">
                                                            <g:if test="${objectiveList}">
                                                                <g:each in="${objectiveList}" var="learningObjectiveItem" status="count">
                                                                    <g:render template="/learningObjective/definition" bean="${learningObjectiveItem}"/>
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
                                                            <a href="javascript:" style="text-decoration:none;color:#FFFFFF" onclick="submitRefresh();" title="${Help.toolTip('PEDAGOGY', 'Refresh')}" class="showHover">
                                                                Refresh
                                                            </a>
                                                        </span>
                                                    </div>

                                                    <g:formRemote name="pedAccordion" id="pedAccordion" url="[action:'updateExtendedTechnique',controller:'pedagogy']" update="extendedMatchDiv">
                                                        <div id="ped_accordion">
                                                            <h3 title="${Help.toolTip('PEDAGOGY', 'Domain')}" class="showHover">
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
                                                            <h3 title="${Help.toolTip('PEDAGOGY', 'Domain Category')}" class="showHover">
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

                                                            <h3 title="${Help.toolTip('PEDAGOGY', 'Knowledge Dimension')}" class="showHover">
                                                                Knowledge Dimension
                                                            </h3>
                                                            <div>
                                                                <p>
                                                                    <g:each in="${mapkdList}" var="kdomain">
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
                        <table id="addLearningObjectiveDiv" title="${Help.toolTip('CONTENT', 'Add Objective')}">
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

                    </td>

                    <td style="vertical-align: top;padding:-50;">
                        <g:render template="/pedagogy/pedagogyBodyTemplate"/>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
