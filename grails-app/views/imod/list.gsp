
<%@ page import="imodv6.Imod" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
		</title>
		<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'imod.list.gsp.css')}">
	</head>
	<body>
		<div class="list-imod-body">
			<div id="list-imod" class="content scaffold-list" role="main">
				<g:if test="${flash.message}">
					<div class="message" role="status">
						${flash.message}
					</div>
				</g:if>
				<g:link class="dashed-square" action="create" >
					<br/>
					<br/>
					New i-mod
				</g:link>
				<g:each in="${imodInstanceList}" status="i" var="imodInstance">
					<g:link action="edit" id="${imodInstance.id}" class="square">
						<br/>
						<br/>
						${fieldValue(bean: imodInstance, field: "name")}
					</g:link>
				</g:each>
			</div>
		</div>
	</body>
</html>
