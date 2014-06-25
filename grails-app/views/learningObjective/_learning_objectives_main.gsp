<%@ page import="imodv6.Help" %>
<div id="create-imod" class="content scaffold-create" role="main">
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

	<table style="border:1px solid black; border-collapse:collapse; left:-5%;height:500px; width:110%;" class="inner_table">
		<tr >
			<td style="border:1px solid black; border-collapse:collapse; width:30%" rowspan="2">
				<div class="learning_objectives_list">
					<div class="form_title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
						<span class="title_text">
							Learning Objectives
							<a href="/imodv6/learningObjective/create">
								<button><span class="fa fa-plus"></span> Add</button>
							</a>

						</span>
					</div>
					<g:render template="/learningObjective/learning_objectives_list"/>
				</div>
			</td>
			<td style="border:1px solid black; border-collapse:collapse; width:70%">
				<div class="learning_objectives_definition">
					<g:render template="/learningObjective/learning_objectives_definition" />
				</div>
			</td>
		</tr>
		<tr style="border:1px solid black; border-collapse:collapse">
			<td>
				<div class="learning_objective_creator">
					<g:render template="/learningObjective/learning_objectives_creator"/>
				</div>
			</td>
		</tr>
	</table>
</div>
