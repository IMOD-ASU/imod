<%@ page import="imodv6.Help" %>
<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>

<html>
    <head>
        <title>
            Learning Objective - Performance
        </title>

        <meta name="layout" content="imod">
        <link rel="stylesheet" href="${resource(dir: 'css/source', file: 'pedagogy.pedagogyBodyTemplate.gsp.css')}">
        <script src="${resource(dir: 'js/source', file: 'pedagogy.pedagogyObjectiveTemplate.gsp.js')}" defer></script>
        <script src="${resource(dir: 'js/source', file: 'pedagogy.pedagogyBodyTemplate.gsp.js')}" defer></script>
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

                        <!-- left panel for the page -->
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
                        <!-- body of the page -->
                        <table>
                            <tbody>
                                <tr style="display: block;">
                                    <td>
                                        <div class="topic_addition_widget" style="width: 705px;min-height: 655px;">
                                            <div class="form_title removeBorder">
                                                <span style="margin-left: 5px; ">

                                                    <%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Add New Technique')}" onclick="addNewTechnique()">
                                                        Add New Technique
                                                    </button>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Favorites')}" onclick="expandFavorite();">
                                                        Favorites
                                                    </button>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Instructional Plan')}" onclick="addNewTechnique()">
                                                        Instructional Plan
                                                    </button>
                                                </span>
                                            </div>

                                            <table style="width: 100%;">
                                                <tr style="border-bottom: 1px solid;  color:#CACCCE;">
                                                    <td>
                                                        <div style="margin-top: 5px;margin-bottom: 5px;"  id="pc3_img">

                                                            <img style="padding-left: 50px;" src="${resource(dir: 'images', file: 'logo_orange.png')}" alt="OrangeImodLogo"/>

                                                            <%-- PC3 Functionality --%>

                                                            <img id="performance-tab" style="padding-left: 15px; height: 40px;" src="${resource(dir: 'images', file: 'Performance.png')}" onmouseover="this.src='${resource(dir: 'images', file: 'OrangePerformance.png')}'" onmouseout="this.src='${resource(dir: 'images', file: 'Performance.png')}'" alt="Performance" title="${chapter?.performance}"/>

                                                            <img id="content-tab" style="height: 37px;" src="${resource(dir: 'images', file: 'Content.png')}" onmouseover="this.src='${resource(dir: 'images', file: 'OrangeContent.png')}'" onmouseout="this.src='${resource(dir: 'images', file: 'Content.png')}'" alt="Content" title="${contentTitle?.join(", ")}"/>

                                                            <img id="condition-tab" style="height: 37px;" src="${resource(dir: 'images', file: 'Condition.png')}" onmouseover="this.src='${resource(dir: 'images', file: 'OrangeCondition.png')}'" onmouseout="this.src='${resource(dir: 'images', file: 'Condition.png')}'"alt="Condition" title="${chapter?.condition}"/>

                                                            <img id="criteria-tab" style="height: 38px;" src="${resource(dir: 'images', file: 'Criteria.png')}" onmouseover="this.src='${resource(dir: 'images', file: 'OrangeCriteria.png')}'" onmouseout="this.src='${resource(dir: 'images', file: 'Criteria.png')}'"alt="Criteria" title="${chapter?.criteriaAccuracy}"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div style="font-weight:bold;padding-left:20px;" id="selectedFilter">
                                                ${selectionLine}
                                            </div>
                                            <%-- Ideal match accordion --%>
                                            <div id="ped1_accordion">
                                                <h3 title="${Help.toolTip('PEDAGOGY', 'Ideal Match')}" class="showHover">
                                                    Ideal Match
                                                </h3>
                                                <div id="idealMatchDiv">
                                                    <g:each in="${pedaTechList}" var="pedaTech" status="i">
                                                        <div title="${pedaTech.pedagogyDescription}" class="imgblock showHover">
                                                            <g:set var="fav" value="${ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(userId,pedaTech)}"/>
                                                            <g:set var="assign" value="${ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(chapter,pedaTech)}"/>
                                                            <g:if test="${fav && assign}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-assign.png')}" alt="Criteria" usemap="#${pedaTech.id}" />
                                                            </g:if>
                                                            <g:elseif test="${fav}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-unassign.png')}" alt="Criteria" usemap="#${pedaTech.id}" />
                                                            </g:elseif>
                                                            <g:elseif test="${assign}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-assign.png')}" alt="Criteria" usemap="#${pedaTech.id}" />
                                                            </g:elseif>
                                                            <g:else>
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-unassign.png')}" alt="Criteria" usemap="#${pedaTech.id}" />
                                                            </g:else>
                                                            <div class="smallblackarea">
                                                                <map name="${pedaTech.id}">
                                                                    <area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: pedaTech.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
                                                                    <area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: pedaTech.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
                                                                    <area shape="rect" coords="90,90,200,200" href="javascript:clonePedagogyTech(${pedaTech.id});" title="Clone" alt="Clone" />
                                                                </map>
                                                                ${pedaTech.pedagogyTitle}
                                                            </div>
                                                        </div>
                                                    </g:each>
                                                </div>

                                                <%--Extended Match Accordion --%>
                                                <h3 title="${Help.toolTip('PEDAGOGY', 'Extended Match')}" class="showHover">
                                                    Extended Match
                                                </h3>
                                                <div id="extendedMatchDiv">
                                                    <%-- <g:render template="pedagogyExtendedMatch" /> --%>
                                                </div>

                                                <%--Favorite Accordion --%>
                                                <h3 title="${Help.toolTip('PEDAGOGY', 'Favorites')}" class="showHover">
                                                    Favorite
                                                </h3>
                                                <div>
                                                    <g:each in="${favPedaTechList}" var="favPedaTech" status="i">
                                                        <div title="${favPedaTech.pedagogyTechnique.pedagogyDescription}" class="imgblock showHover">
                                                            <g:set var="fav" value="${ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(userId,favPedaTech.pedagogyTechnique)}"/>
                                                            <g:set var="assign" value="${ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(chapter,favPedaTech.pedagogyTechnique)}"/>
                                                            <g:if test="${fav && assign}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-assign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechnique.id}" />
                                                            </g:if>
                                                            <g:elseif test="${fav}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-unassign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechnique.id}"/>
                                                            </g:elseif>
                                                            <g:elseif test="${assign}">
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-assign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechniqueh.id}" />
                                                            </g:elseif>
                                                            <g:else>
                                                                <img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-unassign.png')}" alt="Criteria" usemap="#$favPedaTech.pedagogyTechnique.id}" />
                                                            </g:else>
                                                            <div class="smallblackarea">
                                                                <map name="${favPedaTech.pedagogyTechnique.id}">
                                                                    <area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
                                                                    <area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
                                                                    <area shape="rect" coords="90,90,200,200" href="javascript:clonePedagogyTech(${favPedaTech.pedagogyTechnique.id});" title="Clone" alt="Clone" />
                                                                </map>
                                                                ${favPedaTech.pedagogyTechnique.pedagogyTitle}
                                                            </div>
                                                        </div>
                                                    </g:each>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <%--Dialog box for Add New Technique --%>
                        <div id="showAddNewTechnique" title="Add New Technique">
                            <g:render template="pedagogyAddTechnique"/>
                        </div>

                        <%--Dialog box for Clone Technique --%>
                        <div id="cloneTechnique" title="Clone Technique">
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
