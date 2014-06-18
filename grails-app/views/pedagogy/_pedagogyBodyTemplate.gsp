<%@ page import="imodv6.Help" %>
<%@ page import="imodv6.ImodUserPedagogyFavorite" %>
<%@ page import="imodv6.ImodPedagogyAssign" %>
<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>

<style type="text/css" media="screen">
	select[disabled][disabled]{
		-moz-user-input: disabled;
	    -moz-user-focus: ignore;
	    color: GrayText;
	    background-color: ThreeDFace;
	    cursor: inherit;
	}
	.smallblackarea {
        top: 50px;
        display: inline-block;
        margin-right: 0;
        margin-top: 0;
        position: absolute;
        left: 25px;
        font-family: arial;
        font-size: 12px;
        font-weight: bold;
        padding: 5px;
        width:100px;
    }

    .imgblock {
    display: block;
    position: relative;
    width: 140px;
    height: 140px;
    padding: 10px;
    float:left;
    }

	  .ui-tooltip, .arrow:after {
	    background: white;
	    border: 0.5px solid black;
	  }
	  .ui-tooltip {
	    padding: 5px 10px;
	    //color: white;
	    //border-radius: 20px;
	    font: bold 12px "Helvetica Neue", Sans-Serif;
	    //text-transform: uppercase;
	    //box-shadow: 0 0 7px black;
	  }
	  .arrow {
	    width: 70px;
	    height: 16px;
	    overflow: hidden;
	    position: absolute;
	    left: 50%;
	    margin-left: -35px;
	    bottom: -16px;
	  }
	  .arrow.top {
	    top: -16px;
	    bottom: auto;
	  }
	  .arrow.left {
	    left: 20%;
	  }
	  .arrow:after {
	    content: "";
	    position: absolute;
	    left: 20px;
	    top: -20px;
	    width: 25px;
	    height: 25px;
	    box-shadow: 6px 5px 9px -9px black;
	    -webkit-transform: rotate(45deg);
	    -moz-transform: rotate(45deg);
	    -ms-transform: rotate(45deg);
	    -o-transform: rotate(45deg);
	    tranform: rotate(45deg);
	  }
	  .arrow.top:after {
	    bottom: -20px;
	    top: auto;
	  }
	
</style>  
  
<script>
var activity = 2;
var reference = 2;

// To open Add New Technique Dialog box
function addNewTechnique() {
	jQuery("#showAddNewTechnique").dialog("open");
    activity = 2;
	reference = 2;
    return false;
}

// To open Clone Technique Dialog box
function cloneTechnique() {
	jQuery("#cloneTechnique").dialog("open");
    return false;
}

// To close Add New Technique Dialog box
function closeNewTechniqueTable() {
    jQuery("#showAddNewTechnique").dialog("close");
    return false;
}

// To close Clone Technique Dialog box
function closeCloneTechniqueTable(data) {
	if(data=="done"){
		jQuery("#cloneTechnique").dialog("close");
		var test = 4;
		location.href = "44?loadPedagogyTab=true&objectiveId=4"
	    return false;
	}
}

// To Validate while creating a new technique
function onSubmitValidate(){
	if($("#pedagogyTitle_dialog").val()==''){
		alert("Please provide title");
		return false;
	}	
	if($("#pedagogyModeId_dialog option:selected").val()==''){
		alert("Please select Mode");
		return false;
	}
	if($("#domain_dialog").val()==null){
		alert("Please select Domain");
		return false;
	}
	if($("#category_dialog").val()==null){
		alert("Please select Category");
		return false;
	}
	if($("#knowledge_dialog").val()==null){
		alert("Please select Knowledge Dimension");
		return false;
	}
	return true;
}


var referenceTypeOptions="";
var durationOptions="";

//Initializing the jquery ui - accordions, dialogs and tool tip 
$(function() {
		jQuery("#showAddNewTechnique").dialog({autoOpen: false, height: 500, width: 750, show: "fadein", hide: "fadeout", modal: true});
		jQuery("#cloneTechnique").dialog({autoOpen: false, height: 500, width: 750, show: "fadein", hide: "fadeout", modal: true});		
		$( "#ped1_accordion" ).accordion({
			heightStyle: "content"
		});
		$("#ped_technique").accordion({
			heightStyle: "content",
			collapsible: true,
			active: false
		});
		$("#performance-tab").tooltip({
	      position: {
	          my: "center bottom-20",
	          at: "center top",
	          using: function( position, feedback ) {
	            $( this ).css( position );
	            $( "<div>" )
	              .addClass( "arrow" )
	              .addClass( feedback.vertical )
	              .addClass( feedback.horizontal )
	              .appendTo( this );
	          }
	        }
	      });
		$("#content-tab").tooltip({
	      position: {
	          my: "center bottom-20",
	          at: "center top",
	          using: function( position, feedback ) {
	            $( this ).css( position );
	            $( "<div>" )
	              .addClass( "arrow" )
	              .addClass( feedback.vertical )
	              .addClass( feedback.horizontal )
	              .appendTo( this );
	          }
	        }
	      });
		$("#condition-tab").tooltip({
	      position: {
	          my: "center bottom-20",
	          at: "center top",
	          using: function( position, feedback ) {
	            $( this ).css( position );
	            $( "<div>" )
	              .addClass( "arrow" )
	              .addClass( feedback.vertical )
	              .addClass( feedback.horizontal )
	              .appendTo( this );
	          }
	        }
	      });
		$("#criteria-tab").tooltip({
	      position: {
	          my: "center bottom-20",
	          at: "center top",
	          using: function( position, feedback ) {
	            $( this ).css( position );
	            $( "<div>" )
	              .addClass( "arrow" )
	              .addClass( feedback.vertical )
	              .addClass( feedback.horizontal )
	              .appendTo( this );
	          }
	        }
	      });
		  referenceTypeOptions = "<option value=''>Select one</option>"
	      <g:each var="referenceType" in="${referenceTypeList}">
	      	referenceTypeOptions += "<option value='${referenceType.id}'>${referenceType.description}</option>"
	      </g:each>
	      durationOptions = "<option value=''>Select one</option>"
	      <g:each var="duration" in="${PedagogyActivityDuration.list()}">
	      	durationOptions += "<option value='${duration.id}'>${duration.duration}</option>"
	      </g:each>	        
	});

// Expand the favorite accordion
	function expandFavorite(){
		$("#ped1_accordion").accordion({active:2});
	}

// Expand the extended match accordion
	function expandExtended(){
		$("#ped1_accordion").accordion({active:1});
	}

// To add activity row dynamically at new and clone technique
	function addRowActivity(flag){
		var newRow = "<tr> <td><input type='text' name='pedagogyActivity"+activity+".title' value=''/></td> <td><input type='text' name='pedagogyActivity"+activity+".description' value=''/></td> <td><select name='pedagogyActivity"+activity+".duration'>"+durationOptions+"</select></td> <td><input type='text' name='pedagogyActivity"+activity+".example' value=''/></td> <td><input type='text' name='pedagogyActivity"+activity+".material' value=''/></td><td><a href='javascript:' onclick='removeRow(this);' style='text-decoration:none;font-weight:bold;'>x</a></td></tr>";
		if(flag==true){			
			$("form#cloneTechniqueForm").find("#techniqueTableData").append(newRow);
		}else{
			$("#techniqueTableData").append(newRow);
		}		
		activity++;
	}

// To remove activity/reference row dynamically at new and clone technique	
	function removeRow(ref,flag){
		var rowCount = ref.parentNode.parentNode.parentNode.rows.length;
		if(rowCount > 2){
			var row = ref.parentNode.parentNode;
			row.parentNode.removeChild(row);
			activity--;
		}
	}

// To add reference row dynamically at new and clone technique
	function addRowReference(flag){
		var newRow = "<tr> <td><input type='text' name='pedagogyReference"+reference+".title' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".author' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".description' value=''/></td> <td><input type='text' name='pedagogyReference"+reference+".referenceLinkISBN' value=''/></td> <td><select name='pedagogyReference"+reference+".refeType'>"+referenceTypeOptions+"</select></td><td><a href='javascript:' onclick='removeRow(this);' style='text-decoration:none;font-weight:bold;'>x</a></td></tr>";
		if(flag==true){
			$("form#cloneTechniqueForm").find("#referenceTableData").append(newRow);
		}else{
			$("#referenceTableData").append(newRow);
		}
		reference++;
	}

// Ajax call to clone activity
	function clonePedagogyTech(id){
		${remoteFunction(controller: 'pedagogy', action: 'clonePedagogyTech', update:'cloneTechnique', params:'\'techId=\'+id', onSuccess:'cloneTechnique();')};
	}
</script>

 
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
