<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<script>
$(function() {
	$("#selectedFilter").html("${selectionLine}");
});
</script>

<!-- Extended Match Results on click of Refresh  -->
<g:each in="${pedaTechList}" var="p">
	<div title="${p.pedagogyDescription}" class="imgblock showHover">
	<g:set var="fav" value="${ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(userId,p)}"/>
    <g:set var="assign" value="${ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(chapter,p)}"/>
    <g:if test="${fav && assign}">
       <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-assign.png')}" alt="Criteria" usemap="#${p.id}" />							           
    </g:if>
    <g:elseif test="${fav}">
   		<img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-unassign.png')}" alt="Criteria" usemap="#${p.id}" />
    </g:elseif>
    <g:elseif test="${assign}">
    	<img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-assign.png')}" alt="Criteria" usemap="#${p.id}" />
    </g:elseif>
    <g:else>
    	<img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-unassign.png')}" alt="Criteria" usemap="#${p.id}" />
    </g:else>
		<map name="${p.id}">
			<area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: objectiveId, pedtecID: p.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
			<area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: imod?.id, params: [objectiveId: objectiveId, pedtecID: p.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
			<area shape="rect" coords="90,90,200,200" href="javascript:clonePedagogyTech(${p?.id});" title="Clone" alt="Clone" />

		</map>
		<div class="smallblackarea">
			${p.pedagogyTitle}
		</div>
	</div>
</g:each>
