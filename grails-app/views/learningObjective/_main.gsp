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
					<td id="LO_list_pane" rowspan="2">
						<div class="learning_objectives_list">
							<div class="form_title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
								<span class="title_text">
									Learning Objectives
									<a href="/imodv6/learningObjective/create">
										<button>
											<span class="fa fa-plus">
											</span>
											Add
										</button>
									</a>

								</span>
							</div>
							<ul>
								<li>
									<g:each in="${learningObjectiveInstanceList}" status="i" var="learningObjective">
										<g:link action="edit" id="${learningObjective.id}" class="square">
											${fieldValue(bean: learningObjective, field: "name")}
										</g:link>
									</g:each>
								</li>
							</ul>
						</div>
					</td>
					<td id="LO_definition_cell">
						<div class="learning_objectives_definition">
							<g:render template="/learningObjective/definition" />
						</div>
					</td>
				</tr>
				<tr>
					<td id="LO_creator">
						<div class="learning_objective_creator">
							<%@ page import="imodv6.Help" %>
							<img id="LO-type-icon" src="${resource(dir: 'images/learningObjectives', file: 'LO-performance.png')}" alt=""/>

							<div id="tabs-LO-subhead">
								<ul class="ui-tabs-nav">
									<li>
										<A file="${resource(dir: 'images/learningObjectives', file: 'LO-performance.png')}" class="performance" href="#LO-performance">
											Performance
										</A>
									</li>
									<li>
										<A file="${resource(dir: 'images/learningObjectives', file: 'LO-content.png')}" class="content" href="#LO-content">
											Content
										</A>
									</li>
									<li>
										<A file="${resource(dir: 'images/learningObjectives', file: 'LO-condition.png')}" class="condition" href="#LO-condition">
											Condition
										</A>
									</li>
									<li>
										<A file="${resource(dir: 'images/learningObjectives', file: 'LO-criteria.png')}" class="criteria" href="#LO-criteria">
											Criteria
										</A>
									</li>
								</ul>
								<div class="tabs-background">
								</div>
							</div>
							<hr />
							<div>
								<div id="LO-performance">
									Performance
								</div>
								<div id="LO-content">
									Content
								</div>
								<div id="LO-condition">
									<g:render template="/learningObjective/creator/LO_condition"/>
								</div>
								<div id="LO-criteria">
									Criteria
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
</g:form>

