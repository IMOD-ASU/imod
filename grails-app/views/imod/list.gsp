
<%@ page import="imodv6.Imod" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imod.label', default: 'Imod')}" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="container">
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<div class="row">
				<div class="col-md-3">
					<g:link class="dashed-square" action="create" >
						<div class="alert alert-success text-center" role="alert">
							New i-mod
						</div>
					</g:link>
				</div>
				<div class="col-md-3">
					<g:each in="${imodInstanceList}" status="i" var="imodInstance">
						<g:link controller="courseOverview" action="index" id="${imodInstance.id}" class="square">
							<div class="panel panel-default">
								<div class="panel-body text-center">
									${fieldValue(bean: imodInstance, field: "name")}
								</div>
							</div>
						</g:link>
					</g:each>
				</div>
			</div>
		</div>
	</body>
</html>
