<%@ page import="imod.Help" %>

<html>
	<head>
		<title>
			Pedagogy
		</title>

		<meta name="layout" content="imod">

		<g:external dir="css/source" file="pedagogy.css" />
		<g:external dir="css/source" file="learningObjective.css" />
		<g:external dir="css/source" file="iconModule.css" />
		<g:javascript src="source/pedagogy.js" defer="defer" />
	</head>
	<body>
		<div id="edit-imod" class="content scaffold-edit" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>

			<g:hasErrors bean="${currentImod}">
				<ul class="errors" role="alert">
					<g:eachError bean="${currentImod}" var="error">
						<li>
							<g:message error="${error}"/>
						</li>
					</g:eachError>
				</ul>
			</g:hasErrors>

			<table>
				<tr>
					<td class="learning-objective list-panel">
						<!-- left panel for the page -->
						<div>
							<div class="form-title">
								<span class="title-text">
									Learning Objectives
								</span>
							</div>
							<ul class="learning-objective list-wrapper">
								<g:each var="learningObjective" in="${learningObjectives}">
									<li class="learning-objective list-item ${(learningObjective.id == currentLearningObjective.id) ? 'active' : ''  }">
										<g:link action="index" id="${currentImod.id}" params="[learningObjectiveID: learningObjective.id]" class="learning-objective list-link">
											${ learningObjective.definition }
										</g:link>
									</li>
								</g:each>
							</ul>
						</div>

						<g:render template="/pedagogy/filter" />
					</td>

					<td>
						<!-- body of the page -->
						<table>
							<tbody>
								<tr>
									<td>
										<div class="topic_addition_widget">
											<div class="form_title removeBorder">
												<span>

													<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
													<button id="add-new-technique-button">
														Add New Technique
													</button>

													<button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Favorites')}">
														Favorites
													</button>

													<button id="newTechnique" class="showHover" title="${Help.toolTip('PEDAGOGY', 'Instructional Plan')}">
														Instructional Plan
													</button>
												</span>
											</div>

											<g:img id="performance-tab" dir="images/learningObjectives" file="LO-performance.png" alt="Performance"/>

											<ul class="learning-objective sub-nav">
												<li class="performance active">
													Performance
												</li>
												<li class="content">
													Content
												</li>
												<li class="condition">
													Condition
												</li>
												<li class="criteria active">
													Criteria
												</li>
											</ul>

											<div id="selectedFilter">
												${selectionLine}
											</div>

											<h3 title="${Help.toolTip('PEDAGOGY', 'Ideal Match')}" class="showHover">
												Ideal Match
											</h3>

											<div id="ideal-matches" class="icons favorite"></div>

											<h3 title="${Help.toolTip('PEDAGOGY', 'Extended Match')}" class="showHover">
												Extended Match
											</h3>

											<div id="extended-matches" class="icons favorite"></div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>

						<%--Dialog box for Add New Technique --%>
						<div id="add-new-technique" title="Add New Technique">
							<%--To render the add new Technique dialog box--%>
							<g:form controller="pedagogyTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
								<g:hiddenField name="techniqueId" />
								<label>
									Title
								</label>
								<g:textField name="title" />
								<br />

								<label>
									Assign to Current Learning Objective
								</label>
								<g:checkBox name="assignedToLearningObjective" />
								<br />

								<label>
									Favorite Technique
								</label>
								<g:checkBox name="favoriteTechnique" />
								<br />

								<label>
									Learning Domain
								</label>
								<g:select name="learningDomain" from="${learningDomains}" optionKey="name" />
								<br />

								<label>
									Domain Category
								</label>
								<g:select name="domainCategory" from="${domainCategories}" optionKey="name" />
								<br />

								<label>
									Knowledge Dimension
								</label>
								<g:select name="knowledgeDimension" from="${knowledgeDimensions}" optionKey="description" />
								<br />

								<label>
									Delivery Mode
								</label>
								<g:select name="pedagogyMode" from="${pedagogyModes}" optionKey="name" />
								<br />

								<label>
									Location
								</label>
								<g:textField name="location" />
								<br />

								<label>
									Focus
								</label>
								<g:select name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" />
								<br />

								<label>
									Direction
								</label>
								<g:textField name="direction" />
								<br />

								<label>
									Materials Required
								</label>
								<g:textField name="materials" />
								<br />

								<label>
									Reference
								</label>
								<g:textField name="reference" />
								<br />

								<label>
									Description of Strategy
								</label>
								<g:textField name="strategyDescription" />
								<br />

								<label>
									Description of Activity
								</label>
								<g:textField name="activityDescription" />
								<br />

								<g:actionSubmit value="Save" action="save" />
								<g:actionSubmit value="Cancel" action="cancel" />
							</g:form>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
