<%@ page import="imodv6.Help" %>
<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>

<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'pedagogy.pedagogyBodyTemplate.gsp.css')}">

<script src="${resource(dir: 'js/source', file: 'pedagogy.pedagogyBodyTemplate.gsp.js')}" defer></script>


<table>
    <tbody>
    <tr style="display: block; ">
        <td><div class="topic_addition_widget" style="width: 705px;min-height: 655px;">
            <div class="form_title removeBorder">
                <span style="margin-left: 5px; ">

<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>

                	<button id="newTechnique" class="showHover"
                           title="${Help.toolTip("PEDAGOGY", "Add New Technique")}"
                           onclick="addNewTechnique()">Add New Technique</button>

                    <button id="newTechnique" class="showHover"
                           title="${Help.toolTip("PEDAGOGY", "Favorites")}"
                           onclick="expandFavorite();">Favorites</button>

                    <button id="newTechnique" class="showHover"
                           title="${Help.toolTip("PEDAGOGY", "Instructional Plan")}"
                           onclick="addNewTechnique()">Instructional Plan</button>

                </span></div>

            <table style="width: 100%;">
                <tr style="border-bottom: 1px solid;  color:#CACCCE;">
                    <td>
                        <div style="margin-top: 5px;margin-bottom: 5px;"  id="pc3_img">

                        <img style="padding-left: 50px;" src="${resource(dir: 'images', file: 'logo_orange.png')}" alt="OrangeImodLogo"/>

<%-- PC3 Functionality               --%>

                        <img id="performance-tab" style="padding-left: 15px; height: 40px;"
                        			src="${resource(dir: 'images', file: 'Performance.png')}"
									onmouseover="this.src='${resource(dir: 'images', file: 'OrangePerformance.png')}'"
									onmouseout="this.src='${resource(dir: 'images', file: 'Performance.png')}'" alt="Performance" title="${chapter?.performance}"/>

                        <img id="content-tab" style="height: 37px;"
                        			src="${resource(dir: 'images', file: 'Content.png')}"
									onmouseover="this.src='${resource(dir: 'images', file: 'OrangeContent.png')}'"
									onmouseout="this.src='${resource(dir: 'images', file: 'Content.png')}'" alt="Content" title="${contentTitle?.join(", ")}"/>

                        <img id="condition-tab" style="height: 37px;"
                        			src="${resource(dir: 'images', file: 'Condition.png')}"
                        			onmouseover="this.src='${resource(dir: 'images', file: 'OrangeCondition.png')}'"
									onmouseout="this.src='${resource(dir: 'images', file: 'Condition.png')}'"alt="Condition" title="${chapter?.condition}"/>

                        <img id="criteria-tab" style="height: 38px;"
                        			src="${resource(dir: 'images', file: 'Criteria.png')}"
                        			onmouseover="this.src='${resource(dir: 'images', file: 'OrangeCriteria.png')}'"
									onmouseout="this.src='${resource(dir: 'images', file: 'Criteria.png')}'"alt="Criteria" title="${chapter?.criteria}"/>
                        </div>
                    </td>
                </tr>
              </table>
            <div style="font-weight:bold;padding-left:20px;" id="selectedFilter">${selectionLine}</div>
<%-- Ideal match accordion --%>
    		<div id="ped1_accordion">
                 		<h3 title="${Help.toolTip("PEDAGOGY", "Ideal Match")}" class="showHover">Ideal Match</h3>
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
							           		<area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: params.objectiveId, pedtecID: pedaTech.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
							           		<area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: params.objectiveId, pedtecID: pedaTech.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
							           		<area shape="rect" coords="90,90,200,200" href="javascript:clonePedagogyTech(${pedaTech.id});" title="Clone" alt="Clone" />
					           			</map>
					      	  			${pedaTech.pedagogyTitle}
					      	  		</div>
								</div>
							</g:each>
						</div>

<%--Extended Match Accordion --%>
						<h3 title="${Help.toolTip("PEDAGOGY", "Extended Match")}" class="showHover">Extended Match</h3>
						<div id="extendedMatchDiv">
							<g:render template="pedagogyExtendedMatch" />
						</div>
<%--Favorite Accordion --%>
						<h3 title="${Help.toolTip("PEDAGOGY", "Favorites")}" class="showHover">Favorite</h3>
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
							           		<area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
							           		<area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: params.objectiveId, pedtecID: favPedaTech.pedagogyTechnique.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
							           		<area shape="rect" coords="90,90,200,200" href="javascript:clonePedagogyTech(${favPedaTech.pedagogyTechnique.id});" title="Clone" alt="Clone" />
					           			</map>
					      	  			${favPedaTech.pedagogyTechnique.pedagogyTitle}
					      	  		</div>
								</div>
							</g:each>
						</div>
					</div>
        </div></td>

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
