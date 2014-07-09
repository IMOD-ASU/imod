<%--
	this is the layout for the learning objective tab, it manages switching between the:
	- Performance
	- Content
	- Condition
	- Criteria
	learning objective sections
--%>

<%@ page import="imodv6.Help" %>

<g:applyLayout name="imod">
	<html>
		<head>
			<title>
				<g:layoutTitle default="Learning Objective"/>
			</title>

			<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'learningObjective.main.gsp.css')}" type="text/css">

			<script src="${resource(dir: 'js/source', file: 'learningObjective.main.gsp.js')}" defer></script>

			<g:layoutHead/>
		</head>
		<body>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${imodInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${imodInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">
								data-field-id="${error.field}"
							</g:if>
						>
							<g:message error="${error}"/>
						</li>
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
											<g:link action="create" id="${imodInstance.id}" title="${imodv6.Help.toolTip('LEARNINGOBJECTIVE', 'Add Learning Objective')}">
												<button>
													<span class="fa fa-plus">
													</span>
													Add
												</button>
											</g:link>
										</span>
									</div>
									<ul>
										<g:each in="${learningObjectivesList}" status="i" var="learningObjectiveInstance">
											<li>
												<g:link action="edit" id="${learningObjectiveInstance.id}" class="square">
													${fieldValue(bean: learningObjectiveInstance, field: "id")}
												</g:link>
											</li>
										</g:each>
									</ul>
								</div>
							</td>
							<td id="LO_definition_cell">
								<div class="learning_objectives_definition">
									<%--
									<!-- Definition of the conditions for completing a learning objective -->
									<span class="learning-objective-condition">
										${fieldValue(bean: learningObjective, field: "condition")}
									</span>

									<!-- Definition of the perfomance evaulations for a learning objective -->
									<span class="learning-objective-performance">
										${fieldValue(bean: learningObjective, field: "performace")}
									</span>

									<!-- Definition of the criteria for understanding a learning objective -->
									<span class="learning-objective-criteria">
										${fieldValue(bean: learningObjective, field: "criteria")}
									</span>

									<!-- Definition of the indicators used to measure progress through learning objective -->
									<span class="learning-objective-indicator">
										${fieldValue(bean: learningObjective, field: "indicator")}
									</span>
									--%>
								</div>
							</td>
						</tr>
						<tr>
							<td id="LO_creator">
								<div class="learning_objective_creator">
									<img id="LO-type-icon" src="${resource(dir: 'images/learningObjectives', file: 'LO-performance.png')}" alt=""/>

									<div id="tabs-LO-subhead">
										<ul class="ui-tabs-nav">
											<li>
												<g:link action="performance" id="${imodInstance.id}" class="performance" file="${resource(dir: 'images/learningObjectives', file: 'LO-performance.png')}">
													Performance
												</g:link>
											</li>
											<li>
												<g:link action="content" id="${imodInstance.id}" class="content" file="${resource(dir: 'images/learningObjectives', file: 'LO-content.png')}">
													Content
												</g:link>
											</li>
											<li>
												<g:link action="condition" id="${imodInstance.id}" class="condition" file="${resource(dir: 'images/learningObjectives', file: 'LO-condition.png')}">
													Condition
												</g:link>
											</li>
											<li>
												<g:link action="criteria" id="${imodInstance.id}" class="criteria" file="${resource(dir: 'images/learningObjectives', file: 'LO-criteria.png')}">
													Criteria
												</g:link>
											</li>
										</ul>
										<div class="tabs-background">
										</div>
									</div>
									<br />
									<br />
									<br />
									<hr />
									<div>
										<g:layoutBody/>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
			</g:form>
		</body>
	</html>
</g:applyLayout>
