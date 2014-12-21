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

			<g:external dir="css/source" file="learningObjective.css" />

			<g:javascript src="source/learningObjective.js" defer="defer" />

			<g:layoutHead/>
		</head>
		<body>

			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${currentImod}">
				<ul class="errors" role="alert">
					<g:eachError bean="${currentImod}" var="error">
						<li data-field-id="${ error in org.springframework.validation.FieldError ? error.field : '' }">
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
										<g:link action="create" id="${currentImod.id}">
											<button>
												<span class="fa fa-plus"></span>
												Add
											</button>
										</g:link>
									</span>
								</div>
								<ul class="learning-objective-list-wrapper">
									<g:each in="${learningObjectivesList}" status="i" var="learningObjectiveItem">
										<li class="learning-objective-list-item ${(learningObjectiveItem.id == learningObjective.id) ? 'active' : ''  }">
											<g:link
												action="performance"
												id="${currentImod.id}"
												params="[learningObjectiveID: learningObjectiveItem.id]"
												class="learning-objective-list-link"
											>
												<g:render template="/learningObjective/definition" bean="${learningObjectiveItem}" />
											</g:link>
										</li>
									</g:each>
								</ul>
							</div>
						</td>
						<td id="LO-definition-cell">
							<div id="learning-objectives-definition" class="learning-objective-current">
								<g:render template="/learningObjective/definition" bean="${learningObjective}" />
							</div>
						</td>
					</tr>
					<tr>
						<td id="LO-creator">
							<div class="learning-objective-creator">
								<img id="LO-type-icon" src="${resource(dir: 'images/learningObjectives', file: 'LO-' + currentPage.find(/\w+$/) + '.png')}" alt=""/>

								<ul class="learning-objectives sub-nav">
									<li class="${ currentPage.find(/learning objective performance/) == null ? '' : 'active' }">
										<g:link
											action="performance"
											params="[learningObjectiveID: learningObjective.id]"
											id="${currentImod?.id}"
											class="performance"
										>
											Performance
										</g:link>
									</li>
									<li class="${ currentPage.find(/learning objective content/) == null ? '' : 'active' }">
										<g:link
											action="content"
											params="[learningObjectiveID: learningObjective.id]"
											id="${currentImod?.id}"
											class="content"
										>
											Content
										</g:link>
									</li>
									<li class="${ currentPage.find(/learning objective condition/) == null ? '' : 'active' }">
										<g:link
											action="condition"
											params="[learningObjectiveID: learningObjective.id]"
											id="${currentImod?.id}"
											class="condition"
										>
											Condition
										</g:link>
									</li>
									<li class="${ currentPage.find(/learning objective criteria/) == null ? '' : 'active' }">
										<g:link
											action="criteria"
											params="[learningObjectiveID: learningObjective.id]"
											id="${currentImod?.id}"
											class="criteria"
										>
											Criteria
										</g:link>
									</li>
								</ul>
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
