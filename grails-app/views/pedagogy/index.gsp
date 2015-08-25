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
		<div id="topicDialogBackground" class="modalBackground"></div>
		<!-- Adding the  table layout as in Learning objective page -->
		<table class="learning-objective inner-table">
			<tr>
				<td>
					<!-- left panel for the page -->
					<div class="learning-objective list">
						<div class="form-title" style="border-top-left-radius:3px 3px; border-top-right-radius:3px 3px">
							<span class="title-text">
								Learning Objectives
							</span>
						</div>
						<ul class="learning-objective list-wrapper">
							<g:if test="${learningObjectives}">
								<g:each var="learningObjective" in="${learningObjectives}">
									<li class="learning-objective list-item ${(learningObjective.id == currentLearningObjective.id) ? 'active' : ''  }">
										<g:link action="index" id="${currentImod.id}" params="[learningObjectiveID: learningObjective.id]" class="learning-objective list-link">
											${ learningObjective.definition }
										</g:link>
									</li>
								</g:each>
							</g:if>
							<g:else>
								<div class="no-objective-defined">
									There are no objectives defined
									<g:render template="emptyStateTemplate" />
								</div>
							</g:else>
						</ul>
					</div>
					<div class="form-title" style=" border-top-left-radius: 3px; border-top-right-radius: 3px; text-align:center;">
						Filter Options
					</div>
					<div id="filter-pedagogy-techniques" >
						<h3>
							Knowledge Dimensions
						</h3>
						<div >
							<ul>
								<g:each var="knowledgeDimension" in="${knowledgeDimensions}" status="index">
									<li>
										<label for="knowledge-dimension-${index}">
											${knowledgeDimension.description}
										</label>
										<g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}"
										            checked = "${dimension.find { it.toString() == knowledgeDimension.description.toString() }}"/>
									</li>
								</g:each>
							</ul>
						</div>
						<h3>
							Learning Domains
						</h3>
						<div>
							<ul>
								<g:each var="learningDomain" in="${learningDomains}" status="index">
									<li>
										<label for="learning-domain-${index}">
											${learningDomain.name}
										</label>
										<g:checkBox  name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}"
										             checked="${learningDomain.name == selectedDomain.toString()}"/>
									</li>
								</g:each>
							</ul>
						</div>
						<h3>
							Domain Categories
						</h3>
						<div>
							<ul>
								<g:each var="domainCategory" in="${domainCategories}" status="index">
									<li>
										<label for="domain-category-${index}">
											${domainCategory.name}
										</label>
										<g:checkBox  name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}"
										             checked="${domainCategory.name == selectedDomainCategory.toString()}"/>
									</li>
								</g:each>
							</ul>
						</div>
					</div>
				</td>

				<td class="learning-objective definition-cell">
					<g:if test="${learningObjectives}">
						<div class="topic_addition_widget">
							<div class="pedagogy-page-buttons">
								<br>
								<span>
									<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
									<button id="add-new-technique-button">
									<i class="fa fa-plus"></i>
										Add New Technique
									</button>

									<button id="favorites-button">
									<i class="fa fa-star"></i>
										Favorites
									</button>

									<button id="unfavorites">UnFavorites
									</button>

									<button id="instruction-plan-button">Instructional Plan
									</button>
								</span>
							</div>
							<br>
							<div class="learning-objective creator">
							<span style="float:center;"><g:img class="pedagogy type-icon" id="performance-tab" dir="images/learningObjectives" file="LO-content.png" alt="Content"/></span>
							<span><g:img id="performance-tab" dir="images" file="content.png" alt="Content"/></span>
							</div>

							<div>
							<span> <b> ${selectedDomain} </b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;
							<span id="domainCategoryspan"> <b>${selectedDomainCategory}</b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;
							<g:if test="${dimension == null}">

							</g:if>
							<g:else>
								<g:each var="kd" in="${dimension}" status="i">
							       <span> <b>${kd}</b></span>
								   <g:if test="${dimensionSize != i}" >
										<span ><b> or</b> <span>
									</g:if>
								 </g:each>

							</g:else>

							</div>

							<div id="selectedFilter">
								${selectionLine}
							</div>

							<br>
							<div id="ideal-matches-toggle">
							<h3 title="${Help.toolTip('PEDAGOGY', 'Ideal Match')}" class="showHover">
								<b>Ideal Matches</b>
							</h3>

							<div id="ideal-matches" class="icons favorite" style="height:300px"></div>

							<h3 title="${Help.toolTip('PEDAGOGY', 'Extended Match')}" class="showHover">
								<b>Extended Matches</b>
							</h3>

							<div id="extended-matches" class="icons favorite"></div>
							</div>
						</div>
					</g:if>
					<g:else>
						<div class="pedagogy-page-buttons-disabled">
							<span>
								<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
								<button id="add-new-technique-button">
									<i class="fa fa-plus"></i>
									Add New Technique
								</button>

								<button id="favorites-button">
									<i class="fa fa-star"></i>
									Favorites
								</button>

								<button id="instruction-plan-button">Instructional Plan
								</button>
							</span>
							<br/>
						</div>
						<div class="video-control" style="">
							<video class="video-center" controls src="http://media.w3.org/2010/05/bunny/movie.ogv" width="500">
								Your user agent does not support the HTML5 Video element.
							</video>
						</div>
						<div class="no-objective-defined">
							There are no objectives defined
							<g:render template="emptyStateTemplate" />
						</div>
					</g:else>

					<%--Dialog box for Add New Technique --%>
					<div class="draggable" id="add-new-technique">
						<%--To render the add new Technique dialog box--%>
						<fieldset class="titleField draggable-handle">
							<b>Add Pedagogy Technique</b>
						</fieldset>
						<g:form controller="pedagogyTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
							<g:hiddenField name="techniqueId" />
							<table id="techniqueList">
							<tr>
							<td width="40%">Title</td>
							<td width="60%"> <g:textField name="title" /></td>
							</tr>
							<!--<tr>
							<td width="40%"> Assign to Current Learning Objective </td>
							<td width="60%"> <g:checkBox name="assignedToLearningObjective" /></td>
							</tr>
							<tr>
							<td width="40%">Favorite Technique </td>
							<td width="60%"><g:checkBox name="favoriteTechnique" /></td>
							</tr>-->
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

							<div id="modalButtons" style="align:left">
								<g:actionSubmit value="Save" action="save" class="new-technique-popup-button"/>
								<g:actionSubmit value="Cancel" action="cancel" class="new-technique-popup-button" />
							</div>
						</g:form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
