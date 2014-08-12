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

			<link rel="stylesheet" href="${resource(dir: 'css/source', file: 'learningObjective.css')}" type="text/css">

			<script src="${resource(dir: 'js/source', file: 'learningObjective.js')}" defer></script>

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
			<fieldset class="form">
				 <table  class="LO-inner-table">
					<tr>
						<td id="LO-list-pane" rowspan="2">
							<div class="learning-objectives-list">
								<div class="form-title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
									<span class="title-text">
										Learning Objectives
										<a href="/imodv6/learningObjective/create/${imodInstance.id}">
											<button>
												<span class="fa fa-plus">
												</span>
												Add
											</button>
										</a>
									</span>
								</div>
								<ul class="learning-objective-list-wrapper">
									<g:each in="${learningObjectivesList}" status="i" var="learningObjectiveItem">
										<li class="learning-objective-list-item">
											<g:link action="edit" id="${learningObjectiveItem.id}" class="learning-objective-list-link">
												<!-- Definition of the conditions for completing a learning objective -->
												<span class="learning-objective-condition">
													<g:if test="${ learningObjectiveItem.condition != null }">
														${ learningObjectiveItem.condition }
													</g:if>
												</span>

												<!-- Definition of the perfomance evaulations for a learning objective -->
												<span class="learning-objective-performance">
													<g:if test="${ learningObjectiveItem.performance != null }">
														${ learningObjectiveItem.performance }
													</g:if>
												</span>

												<!-- Definition of the criteria for understanding a learning objective -->

												<span class="learning-objective-criteria">
													<g:if test="${ learningObjectiveItem.criteriaAccuracy != null && learningObjectiveItem.criteriaAccuracyHidden == false }">
														${ learningObjectiveItem.criteriaAccuracy }
													</g:if>

													<g:if test="${ learningObjectiveItem.criteriaQuality != null && learningObjectiveItem.criteriaQualityHidden == false }">
														${ learningObjectiveItem.criteriaQuality }
													</g:if>

													<g:if test="${ learningObjectiveItem.criteriaQuantity != null && learningObjectiveItem.criteriaQuantityHidden == false }">
														${ learningObjectiveItem.criteriaQuantity }
													</g:if>

													<g:if test="${ learningObjectiveItem.criteriaSpeed != null && learningObjectiveItem.criteriaSpeedHidden == false }">
														${ learningObjectiveItem.criteriaSpeed }
													</g:if>
												</span>

												<!-- Definition of the indicators used to measure progress through learning objective -->
												<span class="learning-objective-indicator">
													<g:if test="${ learningObjectiveItem.indicator != null }">
														${ learningObjectiveItem.indicator }
													</g:if>
												</span>
											</g:link>
										</li>
									</g:each>
								</ul>
							</div>
						</td>
						<td id="LO-definition-cell">
							<div id="learning-objectives-definition" class="learning-objective-current">
								<!-- Definition of the conditions for completing a learning objective -->
								<span class="learning-objective-condition">
									<g:if test="${ learningObjective.condition != null }">
										${ learningObjective.condition }
									</g:if>
								</span>

								<!-- Definition of the perfomance evaulations for a learning objective -->
								<span class="learning-objective-performance">
									<g:if test="${ learningObjective.performance != null }">
										${ learningObjective.performance }
									</g:if>
								</span>

								<!-- Definition of the criteria for understanding a learning objective -->

								<span class="learning-objective-criteria">
									<g:if test="${ learningObjective.criteriaAccuracy != null && learningObjective.criteriaAccuracyHidden == false }">
										${ learningObjective.criteriaAccuracy }
									</g:if>

									<g:if test="${ learningObjective.criteriaQuality != null && learningObjective.criteriaQualityHidden == false }">
										${ learningObjective.criteriaQuality }
									</g:if>

									<g:if test="${ learningObjective.criteriaQuantity != null && learningObjective.criteriaQuantityHidden == false }">
										${ learningObjective.criteriaQuantity }
									</g:if>

									<g:if test="${ learningObjective.criteriaSpeed != null && learningObjective.criteriaSpeedHidden == false }">
										${ learningObjective.criteriaSpeed }
									</g:if>
								</span>

								<!-- Definition of the indicators used to measure progress through learning objective -->
								<span class="learning-objective-indicator">
									<g:if test="${ learningObjective.indicator != null }">
										${ learningObjective.indicator }
									</g:if>
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td id="LO-creator">
							<div class="learning-objective-creator">
								<img id="LO-type-icon" src="${resource(dir: 'images/learningObjectives', file: 'LO-'+currentPage+'.png')}" alt=""/>

								<div id="tabs-LO-subhead">
									<ul class="ui-tabs-nav">
										<li <g:if test="${currentPage=='performance'}">class="active" </g:if>>
											<g:link
												action="performance"
												learningObjectiveID="${learningObjective.id}"
												id="${imodInstance?.id}"
												class="performance"
											>
												Performance
											</g:link>
										</li>
										<li <g:if test="${currentPage=='content'}">class="active" </g:if>>
											<g:link
												action="content"
												learningObjectiveID="${learningObjective.id}"
												id="${imodInstance?.id}"
												class="content"
											>
												Content
											</g:link>
										</li>
										<li <g:if test="${currentPage=='condition'}">class="active" </g:if>>
											<g:link
												action="condition"
												learningObjectiveID="${learningObjective.id}"
												id="${imodInstance?.id}"
												class="condition"
											>
												Condition
											</g:link>
										</li>
										<li <g:if test="${currentPage=='criteria'}">class="active" </g:if>>
											<g:link
												action="criteria"
												learningObjectiveID="${learningObjective.id}"
												id="${imodInstance?.id}"
												class="criteria"
											>
												Criteria
											</g:link>
										</li>
									</ul>
								</div>
								<div class="tabs-background">
								</div>
								<hr />
								<br>
								<div>
									<g:layoutBody/>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
		</body>
	</html>
</g:applyLayout>
