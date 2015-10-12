<%@ page import="imod.Help" %>

<html>
	<head>
		<title>
			Assessment
		</title>

		<meta name="layout" content="imod">

		<g:external dir="css/source" file="assessment.css" />
		<g:external dir="css/source" file="learningObjective.css" />
		<g:external dir="css/source" file="iconModule.css" />
		<g:javascript src="source/assessment.js" defer="defer" />
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
					<div id="filter-assessment-techniques" >
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
										<g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}" checked = "${dimension.find { it.toString() == knowledgeDimension.description.toString() }}" />
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
										<g:checkBox  name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" checked="${learningDomain.name == selectedDomain.toString()}" />
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
										<g:checkBox  name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" checked="${domainCategory.name == selectedDomainCategory.toString()}"/>
									</li>
								</g:each>
							</ul>
						</div>
					</div>
				</td>

				<td class="learning-objective definition-cell">
					<g:if test="${learningObjectives}">
						<div class="topic_addition_widget">
							<div class="assessment-page-buttons">
								<br>
								<div>
									<%-- Buttons for Add New Technique, Favorites and Assessment Plan--%>
									<span id="new-technique-button" class="topicButtonGradient">
										<button>
											<i class="fa fa-plus"></i>
											Add New Technique
										</button>
									</span>

									<span id="favorites-button" class="topicButtonGradient">
										<button>
											<i class="fa fa-star"></i>
											Favorites
										</button>
									</span>

									<span  id="unfavorites" class="topicButtonGradient">
										<button>
											UnFavorites
										</button>
									</span>

									<span id="assessment-plan-button" class="topicButtonGradient">
										<button>
											<i class="fa fa-graduation-cap"></i>
											Instructional Plan
										</button>
									</span>
								</div>
							</div>
							<br>
							<div class="pedagogy-nav-bar learning-objective creator">
								<span>
									<g:img class="pedagogy type-icon" id="performance-tab" dir="images/learningObjectives" file="LO-content.png" alt="Content"/>
								</span>
								<span>
									<g:img id="performance-tab" dir="images" file="content.png" alt="Content"/>
								</span>
							</div>

							<div>
							<span id="learningDomainspan"> <b> ${selectedDomain} </b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;
							<span id="domainCategoryspan"> <b>${selectedDomainCategory}</b>&nbsp;&nbsp;<i class="fa fa-caret-right"></i></span>&nbsp;&nbsp;
							<g:if test="${dimension == null}">

							</g:if>
							<g:else>
								<span id="knowledgeDimensionspan">
								<g:each var="kd" in="${dimension}" status="i">
							        <b>${kd}</b>
								   <g:if test="${dimensionSize != i}" >
										<b> or</b>
									</g:if>
								 </g:each>
								 </span>
							</g:else>

							</div>

							<div id="selectedFilter">
								${selectionLine}
							</div>
							<br>
							<div id="ideal-matches-toggle">
							<h3 title="${Help.toolTip('ASSESSMENT', 'Ideal Match')}" class="showHover">
								<b>Ideal Matches</b>
							</h3>

							<div id="ideal-matches1" class="icons favorite AllMatches"  style="height:300px"></div>


							<h3 title="${Help.toolTip('PEDAGOGY', 'Extended Match')}" class="showHover">
								<b>Extended Matches</b>
							</h3>

							<div id="extended-matches" class="icons favorite AllMatches"></div>
							</div>


							</div>

							<div class="favDiv">
								<h3 id="assessmentFavorites" title="" class="showHover">
									Favorites
								</h3>

								<div id="assessmentFavoritesDiv" class="AllMatches"  >
								</div>

								<div id="assessmentFavoritesDiv1" class="AllMatches"  >
								</div>
							</div>
						</div>
					</g:if>
					<g:else>
						<div class="assessment-page-buttons-disabled">
							<span>
								<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>
								<button id="new-technique-button">Add New Technique
								</button>

								<button id="favorites">Favorites
								</button>

								<button id="assessment-plan-button">Assessment Plan
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
					<div id="new-technique" title="Add New Technique">
						<%--To render the add new Technique dialog box--%>

						<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
							<g:hiddenField name="techniqueId" />
							<span>
								<label >
									Assign
								</label>
								<g:checkBox class="assignBtn" name="assignedToLearningObjective" />

								<label >
									Favorite
								</label>
								<g:checkBox class="favBtn" name="favoriteTechnique" />
							</span>
							<br/>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Title
								</label>
								<g:textField name="title" class="form-inputs"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Description
								</label>
								<g:textArea name="description" class="form-inputs"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Procedure
								</label>
								<g:textArea name="procedure" class="form-inputs"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Duration
								</label>
								<g:field type="number" name="duration" min="01" max="60" class="allInputs"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Feedback Mechanism
								</label>
								<g:select class="custom-dropdown form-inputs" name="assessmentFeedback" from="${assessmentFeedback}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Learning Domain
								</label>
								<g:select class="custom-dropdown form-inputs" name="learningDomain" from="${learningDomains}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Domain Category
								</label>
								<g:select class="custom-dropdown form-inputs" name="domainCategory" from="${domainCategories}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Knowledge Dimension
								</label>
								<g:select class="custom-dropdown form-inputs" name="knowledgeDimension" from="${knowledgeDimensions}" optionKey="description" />
							</p>
							<br/>

							<div id="modalButtons">
								<g:actionSubmit value="Save" action="save" />
								<g:actionSubmit value="Cancel" action="cancel" />
							</div>
						</g:form>
					</div>

					<%--Dialog box for Displaying in edit and View mode Technique --%>
					<div id="display-new-technique" title="Display Technique">
						<%--To render the add new Technique dialog box--%>
						<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
							<span class="editviewButtons">
								<label >
									Assign
								</label>
								<g:checkBox class="assignBtn" name="assignedToLearningObjective" />

								<label >
									Favorite
								</label>
								<g:checkBox class="favBtn" name="favoriteTechnique" />

								<input type="button" value="Edit" id="editModal" />
								<input type="button" value="View" id="viewModal" />

							</span>
							<br/>

							<g:hiddenField name="techniqueId1" />
							<p class="form-align">
								<label class="form-labels">
									Title
								</label>
								<g:textField name="title1"  class="allInputs"/>
								<g:textField name="title2"  class="allInputs1"/>
							</p>
							<br/>
							<p class="form-align">
								<label class="form-labels">
									Description
								</label>
								<g:textField name="description1"  class="allInputs"/>
								<g:textField name="description2"  class="allInputs1"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Procedure
								</label>
								<g:textArea name="procedure1"  class="allInputs"/>
								<g:textArea name="procedure2"  class="allInputs1"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Duration
								</label>
								<g:field type="number" name="duration1" min="01" max="60" class="allInputs"/>
								<g:field type="number" name="duration2" min="01" max="60" class="allInputs1"/>
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Feedback Mechanism
								</label>
								<g:select class="custom-dropdown" name="assessmentFeedback1" from="${assessmentFeedback}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Learning Domain
								</label>
								<g:select class="custom-dropdown" name="learningDomain" from="${learningDomains}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Domain Category
								</label>
								<g:select class="custom-dropdown" name="domainCategory" from="${domainCategories}" optionKey="name" />
							</p>
							<br/>

							<p class="form-align">
								<label class="form-labels">
									Knowledge Dimension
								</label>
								<g:select class="custom-dropdown" name="knowledgeDimension" from="${knowledgeDimensions}" optionKey="description" />
							</p>
							<br/>

							<div id="modalButtons">
								<g:actionSubmit value="Save" action="save1" />
								<g:actionSubmit value="Cancel" action="cancel" />
							</div>
						</g:form>
					</div>

					<%--Dialog box for Assessement Plans--%>
					<div id="assessment-plan" >
						<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
							<label>
								<h2>
									Learning Objective
								</h2>
							</label>
							<br/>

							<span>
								<g:if test="${learningObjectives.definition !=null}">
									<g:each var="learningObjective" in="${learningObjectives}">
										<div class="assessment-plan learning-objective">
											${learningObjective.id} : ${ learningObjective.definition }
										</div>
									</g:each>
								</g:if>
							</span>

							<div id="assessmentData">
							</div>
							<br/>

							<div id='individualAssessments'>
								<div id='assignTitle'>
								</div>
								<div id='assignTitle1'>
								</div>
							</div>
						</g:form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
