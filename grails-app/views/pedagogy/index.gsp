<%@ page import="imodv6.Help" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.PedagogyActivity" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>
<%@ page import="imodv6.PedagogyActivityFocus" %>
<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyReference" %>

<html>
    <head>
        <title>
            Pedagogy
        </title>

        <meta name="layout" content="imod">

        <g:external dir="css/source" file="pedagogy.css" />
        <g:javascript src="source/pedagogy.js" defer="defer" />
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
                    <td>
                        <!-- left panel for the page -->
                        <div class="learning-objective list">
                            <div class="form-title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
                                <span class="title-text">
                                    Learning Objectives
                                </span>
                            </div>
                            <ul class="learning-objective list-wrapper">
                                <g:each var="learningObjective" in="${learningObjectives}">
                                    <li class="learning-objective list-item ${(learningObjective.id == currentLearningObjective.id) ? 'active' : ''  }">
                                        <g:link action="index" id="${currentImod.id}" params="[learningObjectiveID: learningObjective.id]" class="learning-objective list-link">
                                            <g:render template="/learningObjective/definition" bean="${learningObjective}" />
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </div>

                        <div id="filter-pedagogy-techniques">
                            <h3>
                                Knowledge Dimesions
                            </h3>
                            <div>
                                <ul>
                                    <g:each var="knowledgeDimension" in="${knowledgeDimensions}" status="index">
                                        <li>
                                            <label for="knowledge-dimension-${index}">
                                                ${knowledgeDimension.description}
                                            </label>
                                            <g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}" />
                                        </li>
                                    </g:each>
                                </ul>
                            </div>
                            <h3>
                                Learning Domains
                            </h3>
                            <div>
                                <ul>
                                    <g:each var="learningDomain" in="${learningDomains}" status="index">
                                        <li>
                                            <label for="learning-domain-${index}">
                                                ${learningDomain.name}
                                            </label>
                                            <g:checkBox name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" />
                                        </li>
                                    </g:each>
                                </ul>
                            </div>
                            <h3>
                                Domain Categories
                            </h3>
                            <div>
                                <ul>
                                    <g:each var="domainCategory" in="${domainCategories}" status="index">
                                        <li>
                                            <label for="domain-category-${index}">
                                                ${domainCategory.name}
                                            </label>
                                            <g:checkBox name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" />
                                        </li>
                                    </g:each>
                                </ul>
                            </div>
                        </div>
                    </td>

                    <td>
                        <!-- body of the page -->
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="topic_addition_widget">
                                            <div class="form_title removeBorder">
                                                <span>

                                                    <%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
                                                    <g:form controller="PedagogyTechnique" method="post" id="${currentImod.id}" params="learningObjectiveID=${currentLearningObjective.id}">
                                                            <g:actionSubmit value="Add New Technique" action="create" />
                                                    </g:form>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Favorites')}">
                                                        Favorites
                                                    </button>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Instructional Plan')}">
                                                        Instructional Plan
                                                    </button>
                                                </span>
                                            </div>

                                            <table>
                                                <tr>
                                                    <td>
                                                        <div id="pc3_img">
                                                            <!-- FIXME replace with g:img -->
                                                            <img src="${resource(dir: 'images', file: 'logo_orange.png')}" alt="OrangeImodLogo"/>

                                                            <%-- PC3 Functionality --%>

                                                            <!-- FIXME replace with g:img -->
                                                            <img id="performance-tab" src="${resource(dir: 'images', file: 'Performance.png')}" alt="Performance" title="${chapter?.performance}"/>

                                                            <!-- FIXME replace with g:img -->
                                                            <img id="content-tab" src="${resource(dir: 'images', file: 'Content.png')}" alt="Content" title="${contentTitle?.join(", ")}"/>

                                                            <!-- FIXME replace with g:img -->
                                                            <img id="condition-tab" src="${resource(dir: 'images', file: 'Condition.png')}" alt="Condition" title="${chapter?.condition}"/>

                                                            <!-- FIXME replace with g:img -->
                                                            <img id="criteria-tab" src="${resource(dir: 'images', file: 'Criteria.png')}" alt="Criteria" title="${chapter?.criteriaAccuracy}"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div id="selectedFilter">
                                                ${selectionLine}
                                            </div>
                                            <%-- Ideal match accordion --%>
                                            <div id="ped1_accordion">
                                                <h3 title="${Help.toolTip('PEDAGOGY', 'Ideal Match')}" class="showHover">
                                                    Ideal Match
                                                </h3>

                                                <div id="ideal-matches">
                                                </div>

                                                <%--Favorite Accordion --%>
                                                <h3 title="${Help.toolTip('PEDAGOGY', 'Favorites')}" class="showHover">
                                                    Favorite
                                                </h3>
                                                <div>
                                                    <g:each in="${favPedaTechList}" var="favPedaTech" status="i">
                                                        <div title="${favPedaTech.pedagogyTechnique.pedagogyDescription}" class="imgblock showHover">
                                                            <!-- FIXME Grails should only query models from controller -->
                                                            <g:set var="fav" value="${ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(userId,favPedaTech.pedagogyTechnique)}" />
                                                            <!-- FIXME Grails should only query models from controller -->
                                                            <g:set var="assign" value="${ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(chapter,favPedaTech.pedagogyTechnique)}" />

                                                            <!-- FIXME long IF ELSE chain logic should be in controller or model -->
                                                            <g:if test="${fav && assign}">
                                                                <!-- FIXME replace with g:img -->
                                                                <img src="${resource(dir: 'images', file: 'fav-assign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechnique.id}" />
                                                            </g:if>
                                                            <g:elseif test="${fav}">
                                                                <!-- FIXME replace with g:img -->
                                                                <img src="${resource(dir: 'images', file: 'fav-unassign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechnique.id}"/>
                                                            </g:elseif>
                                                            <g:elseif test="${assign}">
                                                                <!-- FIXME replace with g:img -->
                                                                <img src="${resource(dir: 'images', file: 'unfav-assign.png')}" alt="Criteria" usemap="#${favPedaTech.pedagogyTechniqueh.id}" />
                                                            </g:elseif>
                                                            <g:else>
                                                                <!-- FIXME replace with g:img -->
                                                                <img src="${resource(dir: 'images', file: 'unfav-unassign.png')}" alt="Criteria" usemap="#$favPedaTech.pedagogyTechnique.id}" />
                                                            </g:else>
                                                            <div class="smallblackarea">
                                                                <map name="${favPedaTech.pedagogyTechnique.id}">
                                                                    <area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
                                                                    <area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
                                                                    <area shape="rect" coords="90,90,200,200" title="Clone" alt="Clone" />
                                                                </map>
                                                                ${favPedaTech.pedagogyTechnique.title}
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
                        <div id="add-new-technique" title="Add New Technique">
                            <%--To render the add new Technique dialog box--%>
                            <!-- FIXME rename validation function to be more descriptive -->
                            <g:form controller="PedagogyTechnique" method="post" id="${currentImod.id}" params="learningObjectiveID=${currentLearningObjective.id}">
                                <g:render template="pedagogyTechniqueForm" />
                                <div align="center">
                                    <g:actionSubmit value="Save" action="create" />
                                    <g:actionSubmit value="Cancel" action="cancel" />
                                </div>
                            </g:form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
