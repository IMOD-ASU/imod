<%@ page import="imod.Help" %>

<html>
    <head>
        <title>
            Pedagogy
        </title>

        <meta name="layout" content="imod">

        <g:external dir="css/source" file="pedagogy.css" />
        <g:external dir="css/source" file="learningObjective.css" />
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
                                Knowledge Dimensions
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
                                                    <button id="add-new-technique-button">
                                                        Add New Technique
                                                    </button>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Favorites')}">
                                                        Favorites
                                                    </button>

                                                    <button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Instructional Plan')}">
                                                        Instructional Plan
                                                    </button>
                                                </span>
                                            </div>

                                            <div id="pc3_img">
                                                <g:img dir="images" file="logo_orange.png" alt="OrangeImodLogo"/>

                                                <%-- PC3 Functionality --%>
                                                <g:img id="performance-tab" dir="images/learningObjectives" file="LO-performance.png" alt="Performance"/>
                                                <g:img id="content-tab" dir="images/learningObjectives" file="LO-content.png" alt="Content"/>
                                                <g:img id="condition-tab" dir="images/learningObjectives" file="LO-condition.png" alt="Condition"/>
                                                <g:img id="criteria-tab" dir="images/learningObjectives" file="LO-criteria.png" alt="Criteria"/>
                                            </div>

                                            <div id="selectedFilter">
                                                ${selectionLine}
                                            </div>

                                            <h3 title="${Help.toolTip('PEDAGOGY', 'Ideal Match')}" class="showHover">
                                                Ideal Match
                                            </h3>

                                            <div id="ideal-matches"></div>

                                            <h3 title="${Help.toolTip('PEDAGOGY', 'Extended Match')}" class="showHover">
                                                Extended Match
                                            </h3>

                                            <div id="extended-matches"></div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <%--Dialog box for Add New Technique --%>
                        <div id="add-new-technique" title="Add New Technique">
                            <%--To render the add new Technique dialog box--%>
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
