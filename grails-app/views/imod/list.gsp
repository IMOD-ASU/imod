
<%@ page import="imodv6.Imod" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<style type="text/css" media="screen">
			.list-imod-body{
				height:400px;
				background-color:#ffffff;
        		margin-left:2.5%;
        		margin-right:17.5%;
        		margin-top:5%;
				margin-bottom:4.7%;
				overflow-y:scroll;
				padding-left:3.5%;
				padding-top:2%;
				-moz-box-shadow: 5px 5px 5px #888;
				-webkit-box-shadow: 5px 5px 5px #888;
				box-shadow: 5px 5px 5px #888;
			}
			#list-imod{
			}
		</style>	
			
	</head>
	<body>
			
		<div class="list-imod-body">
			<div id="list-imod" class="content scaffold-list" role="main">
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:link class="dashed-square" action="create" ><br/><br/>New i-mod</g:link>
				<g:each in="${imodInstanceList}" status="i" var="imodInstance">
					<g:link action="edit" id="${imodInstance.id}" class="square"><br/><br/>${fieldValue(bean: imodInstance, field: "name")}</g:link>
				</g:each>
			</div>
		</div>
	</body>
</html>
