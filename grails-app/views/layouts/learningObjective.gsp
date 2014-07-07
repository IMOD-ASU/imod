<%--
	this is the layout for the learning objective tab, it manages switching between the:
	- Performance
	- Content
	- Condition
	- Criteria
	learning objective sections
--%>

<%@ page import="imodv6.Help" %>

<g:applyLayout name="main">
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
									<!-- Definition of the conditions for completing a learning objective -->
									<span>
										${fieldValue(bean: learningObjective, field: "preCondition")}
									</span>
									<span class="learning-objective-condition">
										${fieldValue(bean: learningObjective, field: "condition")}
									</span>
									<span>
										${fieldValue(bean: learningObjective, field: "postCondition")}
									</span>

									<!-- Definition of the perfomance evaulations for a learning objective -->
									<span>
										${fieldValue(bean: learningObjective, field: "prePerformance")}
									</span>
									<span class="learning-objective-performance">
										${fieldValue(bean: learningObjective, field: "performace")}
									</span>
									<span>
										${fieldValue(bean: learningObjective, field: "postPerformance")}
									</span>

									<!-- Definition of the criteria for understanding a learning objective -->
									<span>
										${fieldValue(bean: learningObjective, field: "preCriteria")}
									</span>
									<span class="learning-objective-criteria">
										${fieldValue(bean: learningObjective, field: "criteria")}
									</span>
									<span>
										${fieldValue(bean: learningObjective, field: "postCriteria")}
									</span>

									<!-- Definition of the indicators used to measure progress through learning objective -->
									<span>
										${fieldValue(bean: learningObjective, field: "preIndicator")}
									</span>
									<span class="learning-objective-indicator">
										${fieldValue(bean: learningObjective, field: "indicator")}
									</span>
									<span>
										${fieldValue(bean: learningObjective, field: "postIndicator")}
									</span>
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
												<a file="${resource(dir: 'images/learningObjectives', file: 'LO-performance.png')}" class="performance" href="#LO-performance">
													Performance
												</a>
											</li>
											<li>
												<a file="${resource(dir: 'images/learningObjectives', file: 'LO-content.png')}" class="content" href="#LO-content">
													Content
												</a>
											</li>
											<li>
												<a file="${resource(dir: 'images/learningObjectives', file: 'LO-condition.png')}" class="condition" href="#LO-condition">
													Condition
												</a>
											</li>
											<li>
												<a file="${resource(dir: 'images/learningObjectives', file: 'LO-criteria.png')}" class="criteria" href="#LO-criteria">
													Criteria
												</a>
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
