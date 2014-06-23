<%@ page import="imodv6.Help" %>
<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
</g:if>
<g:hasErrors bean="${imodInstance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${imodInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
</g:hasErrors>
<g:form action="save" >
	<fieldset class="form">
		 <table  class="LO_inner_table">
				<tr>
					<td class="LO_list_pane" rowspan="2">
						<div class="learning_objectives_list">
							<g:render template="/learningObjective/list_pane"/>
						</div>
					</td>
					<td class="LO_definition_cell">
						<div class="learning_objectives_definition">
							<g:render template="/learningObjective/definition" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="LO_creator">
						<div class="learning_objective_creator">
							<g:render template="/learningObjective/creator/header"/>
							<g:render template="/learningObjective/creator/content"/>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
</g:form>

