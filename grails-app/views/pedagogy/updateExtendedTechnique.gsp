<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<!-- FIXME remove all inline CSS from file -->
<!-- FIXME re organize template as a grails server page, instead of a fragment -->

<!-- Extended Match Results on click of Refresh  -->
<g:each in="${pedaTechList}" var="p">
	<div title="${p.description}" class="imgblock showHover">
		<!-- FIXME Grails should only query models from controller -->
		<g:set var="fav" value="${ImodUserPedagogyFavorite.findByImodUserAndPedagogyTechnique(userId,p)}" />
		<!-- FIXME Grails should only query models from controller -->
	    <g:set var="assign" value="${ImodPedagogyAssign.findByLearningObjectiveAndPedagogyTechnique(chapter,p)}" />

		<!-- FIXME long IF ELSE chain logic should be in controller or model -->
		<g:if test="${fav && assign}">
			<!-- FIXME replace with g:img -->
	       <img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-assign.png')}" alt="Criteria" usemap="#${p.id}" />
	    </g:if>
	    <g:elseif test="${fav}">
			<!-- FIXME replace with g:img -->
	   		<img style="height: 130px;"  src="${resource(dir: 'images', file: 'fav-unassign.png')}" alt="Criteria" usemap="#${p.id}" />
	    </g:elseif>
	    <g:elseif test="${assign}">
			<!-- FIXME replace with g:img -->
	    	<img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-assign.png')}" alt="Criteria" usemap="#${p.id}" />
	    </g:elseif>
	    <g:else>
			<!-- FIXME replace with g:img -->
	    	<img style="height: 130px;"  src="${resource(dir: 'images', file: 'unfav-unassign.png')}" alt="Criteria" usemap="#${p.id}" />
	    </g:else>

		<map name="${p.id}">
			<area shape="rect" coords="0,0,18,18" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: objectiveId, pedtecID: p.id, fav:'true', assign:'false'])}" title="Favorite" alt="Favorite" />
			<area shape="rect" coords="90,0,126,24" href="${createLink(controller: 'pedagogy', action: 'reloadPedagogyTab', id: currentImod?.id, params: [objectiveId: objectiveId, pedtecID: p.id, fav:'false', assign:'true'])}" title="Assign" alt="Assign" />
			<area shape="rect" coords="90,90,200,200" onclick="clonePedagogyTech(${p?.id})" title="Clone" alt="Clone" />
		</map>

		<div class="smallblackarea">
			${p.title}
		</div>
	</div>
</g:each>
