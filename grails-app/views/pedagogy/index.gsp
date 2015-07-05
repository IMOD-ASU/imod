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
		</div>

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
				<td></td>
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
										<br>
										<g:img class="pedagogy type-icon" id="performance-tab" dir="images/learningObjectives" file="LO-performance.png" alt="Performance"/>

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
										<br>
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
							<table>
							<tr>
							<td width="40%"> Title </td>
							<td width="60%"> <g:textField name="title" /></td>
							</tr>
							<tr>
							<td width="40%"> Assign to Current Learning Objective </td>
							<td width="60%"> <g:checkBox name="assignedToLearningObjective" /></td>
							</tr>
							<tr>
							<td width="40%">Favorite Technique </td>
							<td width="60%"><g:checkBox name="favoriteTechnique" /></td>
							</tr>
							<tr>
							<td width="40%">Learning Domain	</td>
							<td width="60%"><g:select name="learningDomain" from="${learningDomains}" optionKey="name" /><td>
							</tr>
							<tr>
							<td width="40%">Domain Category</td>
							<td width="60%"><g:select name="domainCategory" from="${domainCategories}" optionKey="name" /></td>
							</tr>
							<tr>
							<td width="40%">Knowledge Dimension</td>
							<td width="60%"><g:select name="knowledgeDimension" from="${knowledgeDimensions}" optionKey="description" /></td>
							</tr>
							<tr>
							<td width="40%">Delivery Mode</td>
							<td width="60%"><g:select name="pedagogyMode" from="${pedagogyModes}" optionKey="name" /></td>
							</tr>
							<tr>
							<td width="40%">Location</td>
							<td width="60%"><g:textField name="location" /> </td>
							</tr>
							<tr>
							<td width="40%">Focus</td>
							<td width="60%"><g:select name="pedagogyFocus" from="${pedagogyFocuses}" optionKey="focus" /></td>
							</tr>
							<tr>
							<td width="40%">Direction</td>
							<td width="60%"><g:textField name="direction" /></td>
							</tr>
							<tr>
							<td width="40%">Materials Required</td>
							<td width="60%"><g:textField name="materials" /> </td>
							</tr>
							<tr>
							<td width="40%">Reference</td>
							<td width="60%"><g:textField name="reference" /></td>
							</tr>
							<tr>
							<td width="40%">Description of Strategy</td>
							<td width="60%">
							<g:textField name="strategyDescription" /></td>
							</tr>
							<tr>
							<td width="40%">Description of Activity</td>
							<td width="60%"><g:textField name="activityDescription" /></td>
							</tr>
							</table>
							<g:actionSubmit value="Save" action="save" />
							<g:actionSubmit value="Cancel" action="cancel" />
						</g:form>

					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
