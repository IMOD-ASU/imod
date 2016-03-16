<%@ page import="imod.Help" %>
<%@ page import="imod.KnowledgeDimensionEnum"%>

<html>
	<head>
		<title>
			Assessment
		</title>

		<meta name="layout" content="imod">
		<!-- FIXME These should be loaded when needed, not prefetched -->
		<link id="imgNone" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimNone.png')}">
		<link id="imgC" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimC.png')}">
		<link id="imgF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimF.png')}">
		<link id="imgM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimM.png')}">
		<link id="imgP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimP.png')}">
		<link id="imgCF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCF.png')}">
		<link id="imgCM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCM.png')}">
		<link id="imgCP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCP.png')}">
		<link id="imgFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFM.png')}">
		<link id="imgFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFP.png')}">
		<link id="imgMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimMP.png')}">
		<link id="imgCFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFM.png')}">
		<link id="imgCFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFP.png')}">
		<link id="imgCMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCMP.png')}">
		<link id="imgFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFMP.png')}">
		<link id="imgCFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFMP.png')}">

		<g:external dir="css/source" file="assessment.css" />
		<g:external dir="css/source" file="learningObjective.css" />
		<g:external dir="css/source" file="iconModule.css" />
		<g:javascript src="source/assessment.js" defer="defer" />
	</head>

	<body>
		<div class="modalBackgroundFavorites"></div>
		<div class="favorites-modal draggable">
			<fieldset class="titleField draggable-handle">
				<div id="editTitle">
					<b> Favorite Assessment Techniques</b>
				</div>
				<span id="closeFavoritesModal" class="topicButtonGradient">
					<button id="closeFavoritesModalButton"><i class="fa fa-times white"></i></button>
				</span>
			</fieldset>
			<div class="favorites-inner">
			</div>
		</div>
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
									<li class="learning-objective list-item ${(learningObjective.id == currentLearningObjective?.id) ? 'active' : ''  }">
										<g:link action="index" id="${currentImod.id}" params="[learningObjectiveID: learningObjective.id]" class="learning-objective list-link">
											${ learningObjective.definition }
										</g:link>
									</li>
								</g:each>
							</g:if>
							<g:else>
								<div class="no-objective-defined">
									<div style="opacity: 0.5;height: 50px;font-size: 20px">There are no objectives defined.</div>
									<div>
									<span class="topicButtonGradient" >
										<a href="../../learningObjective/performance/${currentImod?.id}">
											Define Objectives
										</a>
									</span>
									</div>
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
						<g:checkBox name="myCheckbox" class="select-all" id="selectAllkd" />
						<label>Select All</label>

						<hr style="margin-top: 6px; margin-bottom: 6px; width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
							<ul style="list-style-type:none">
								<g:each var="knowledgeDimension" in="${knowledgeDimensions}" status="index">
									<li>
										<g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}" checked = "${dimension.find { it.toString() == knowledgeDimension.description.toString() }}" />
										<label for="knowledge-dimension-${index}">
											${knowledgeDimension.description}
										</label>

									</li>
								</g:each>
							</ul>
						</div>
						<h3>
							Learning Domains
						</h3>
						<div>
						<g:checkBox name="myCheckbox" class="select-all" id="selectAlllD" />
						<label>Select All</label>
						<hr style="margin-top: 6px; margin-bottom: 6px;  width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
							<ul style="list-style-type:none">
								<g:each var="learningDomain" in="${learningDomains}" status="index">
									<li>
										<g:checkBox  name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" checked="${learningDomain.name == selectedDomain.toString()}" />
										<label for="learning-domain-${index}">
											${learningDomain.name}
										</label>

									</li>
								</g:each>
							</ul>
						</div>
						<h3>
							Domain Categories
						</h3>
						<div>
						<g:checkBox name="myCheckbox" class="select-all" id="selectAlldC" />
						<label>Select All</label>

						<hr style="margin-top: 6px; margin-bottom: 6px; width: 90%;border: 0;height: 2px;background: #000;opacity: 0.2;">
							<ul style="list-style-type:none">
								<g:each var="domainCategory" in="${domainCategories}" status="index">
									<li>
										<g:checkBox  name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" checked="${domainCategory.name == selectedDomainCategory.toString()}"/>
										<label for="domain-category-${index}">
											${domainCategory.name}
										</label>

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
											<i class="fa fa-plus green"></i>
											Add New Technique
										</button>
									</span>

									<span id="favorites-button" class="topicButtonGradient">
										<button>
											<i class="fa fa-star yellow"></i>
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
											Assessment Plan
										</button>
									</span>
								</div>
							</div>
							<br>
							<div class="pedagogy-nav-bar learning-objective creator">
								<span>
									<g:img class="pedagogy type-icon" id="performance-tab" dir="images" file="LO_assessment.png" alt="Performance"/>
								</span>
								<span>
									<g:img id="performance-tab" dir="images" file="assessment-bar.png" alt="Content"/>
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

						<div>
							<%-- Buttons for Add New Technique, Favorites and Assessment Plan--%>
							<span id="new-technique-button" class="topicButtonGradient">
								<button>
									<i class="fa fa-plus green"></i>
									Add New Technique
								</button>
							</span>

							<span id="favorites-button" class="topicButtonGradient">
								<button>
									<i class="fa fa-star yellow"></i>
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
									Assessment Plan
								</button>
							</span>
						</div>
						<br>
						</div>
						<div class="video-control" style="">
							<video class="video-center" controls src="http://media.w3.org/2010/05/bunny/movie.ogv" width="500">
								Your user agent does not support the HTML5 Video element.
							</video>
						</div>
						<div class="no-objective-defined">
							<div style="opacity: 0.5;height: 50px;font-size: 20px">There are no objectives defined.</div>
							<div>
							<span class="topicButtonGradient" >
								<a href="../../learningObjective/performance/${currentImod?.id}">
									Define Objectives
								</a>
							</span>
							</div>
						</div>
					</g:else>

					<%--Dialog box for Add New Technique --%>
					<div id="add-new-technique" class="draggable" title="Add New Technique">
						<%--To render the add new Technique dialog box--%>
						<fieldset class="titleField draggable-handle">
							<div id="editTitle" class="assessment-title">
							<b> Add Assessment Technique</b>
							</div>
							<span id="errorMessage" style="color: red"></span>
						</fieldset>

						<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" class="add-new-technique-form no-warn-form" params="[learningObjectiveID: currentLearningObjective?.id]">
							<g:hiddenField name="techniqueId" />
							<g:hiddenField name="learningObjective" id="learningObjectiveID" value="${currentLearningObjective?.id}"/>
							<table id="techniqueList">
								<tr>
									<td class="td-label" width="40%">Title</td>
									<td width="60%"> <g:textField name="title" /></td>
									<input type="hidden" name="titlecheck" id="titlecheck" >
								</tr>
								<tr>
									<td class="td-label" width="40%">Learning Domain	</td>
									<td width="60%">
										<g:select id="learningDomain" name="learningDomain" multiple="multiple"from="${learningDomains}" noSelection="${['null':'-- Select one or more --']}"  optionKey="name" />
									<td>
									<input type="hidden" name="domainSelected" id="domainSelected" >
									<input type="hidden" name="domainCategorySelected" id="domainCategorySelected" >
								</tr>
								<tr>
									<td class="td-label" width="40%">Domain Category</td>
									<td width="60%">
										<g:select  id="domainCategory" name="domainCategory" multiple="multiple" from="${domainCategories}" noSelection="${['null':'-- Select one or more --']}" optionKey="name" />
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Description of Activity</td>
									<td width="60%">
										<g:textArea name="activityDescription" rows="5" cols="30" />
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Procedure</td>
									<td width="60%">
										<g:textArea id="procedure" name="assessmentProcedure" rows="5" cols="30" />
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Knowledge Dimension</td>
									<td width="60%" class="show-hover-new">
										<span>
											<g:img
												dir="images/content"
												file="knowDimNone.png"
												id="dimImageModal"
												width="71"
												height="71"
												title=""
											/>
											<button id="k1" class="knowledgeDimensionButton" title="${message(code: 'imod.content.knowledgeDimension')}"> Knowledge Dimensions</button>
										</span>
										<input type="hidden" name="knowledgeDimension" id="knowledgeDimension" value="">
										<input type="hidden" name="cloneDetect" id="cloneDetect" >
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Duration</td>
									<td width="60%"><g:textField name="duration" /></td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Difficulty</td>
									<td width="60%">
										<select class="custom-dropdown" id="assessmentDifficulty" name="assessmentDifficulty" from="${assessmentDifficulty}" optionKey="difficulty">
											<option value="Low">Low</option>
											<option value="Medium">Medium</option>
											<option value="High">High</option>
										</select>
									</td>
								</tr>
								<tr>

									<td class="td-label" width="40%">Type</td>
									<td width="60%">
										<select class="custom-dropdown" id="assessmentType" name="assessmentType" from="${assessmentType}" optionKey="difficulty">
											<option value="Summative">Summative</option>
											<option value="Formative">Formative</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">When To Carry Out</td>
									<td width="60%">
										<select class="custom-dropdown" id="assessmentTime"  name="assessmentTime" from="${assessmentTime}" optionKey="assessmentTime" >
											<option value="Pre">Pre</option>
											<option value="Mid">Mid</option>
											<option value="Post">Post</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-label" width="40%">Where To Carry Out</td>
									<td width="60%">
										<select class="custom-dropdown" id="assessmentPlace"  name="assessmentPlace" from="${assessmentPlace}" optionKey="assessmentPlace" >
											<option value="In-Class">In-Class</option>
											<option value="Out-of-Class">Out-of- Class</option>
											<option value="Hybrid">Hybrid</option>
										</select>
									</td>
								</tr>
								<tr>
								<td class="td-label" width="40%">Feedback Mechanism</td>
									<td width="60%">
										<select class="custom-dropdown" name="assessmentFeedback" from="${assessmentFeedback}" optionKey="name">
											<g:each var="feedback" in="${assessmentFeedback}">
												<option id="feedback-${feedback.id}" value="${feedback.name}">${feedback.name}</option>
											</g:each>
										</select>
									</td>
								</tr>
								<tr>
								<td class="td-label" width="40%">References</td>
									<td width="60%"><g:textArea name="references" rows="5" cols="30"/> </td>
								</tr>
							</table>
							<br>


							<div id="modalButtons">
								<button type="submit" name="_action_save" value="Save" id="saveButton" class="new-technique-popup-button">
									<i class="fa fa-save green"></i>
									Save
								</button>
								<button type="submit" name="_action_cancel" value="Cancel" class="new-technique-popup-button">
									<i class="fa fa-times red"></i>
									Cancel
								</button>
							</div><br>
						</g:form>
					</div>

					<%--Dialog box for Displaying in edit and View mode Technique --%>
					<div id="display-new-technique" title="Display Technique">
						<%--To render the add new Technique dialog box--%>
						<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" class="no-warn-form" params="[learningObjectiveID: currentLearningObjective?.id]">
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
					<div class="draggable" id="assessment-plan">

						<fieldset class="titleField draggable-handle">
							<div id="editTitle">
							 <span><b> Assessment Plan</b></span>
							 <div style="float:right">
								<span id="printAssessmentPlan" class="topicButtonGradient" >
									<a href="../assessmentPlan/${currentImod?.id}" target="_blank">
									<i class="fa fa-print white"></i>
										Print
									</a>
								</span>
								<span id="closeAssessmentPlan" class="topicButtonGradient" >
									<button id="closeAssessmentPlanButton">
										<i class="fa fa-times white"></i>
									</button>
								</span>
							</div>
							</div>
						</fieldset>
						<div id="assessment-plan-accordion">
							<g:if test="${learningObjectives != null }">
								<g:each var="learningObjective" in="${learningObjectives}">
									<g:if test="${learningObjective.definition != null && !learningObjective.definition.trim().isEmpty()}">
										<h3 class="assessment-plan-LO" id="${learningObjective.id}">${ learningObjective.definition }</h3>
										<div class="assignedTechniques" id="assignedTechniques-${ learningObjective.id }">

											<g:if test="${learningObjective.assessmentTechniques.size()}">
												<ul>
												<g:each var="technique" in="${learningObjective.assessmentTechniques}">

													<g:if test="${technique != null && !technique.title.isEmpty()}">
														<li>${technique.title}</li>
													</g:if>

												</g:each>
												</ul>
											</g:if>
											<g:else>
												No techniques are assigned to this Learning Objective
											</g:else>

										</div>
									</g:if>
								</g:each>
							</g:if>
							<g:else>
								<div class="no-objective-defined">
									There are no objectives defined
								</div>
							</g:else>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div id="selectKnowledgeDimensionBackground" class="modalBackground2"></div>
		<div id="selectKnowledgeDimensions" class="draggable modelBackground2Target">
			<div class="draggable-handle">
				<input type="hidden" id="topicID" />
				<span>
					<ul>
						<g:each var="dimension" in="${KnowledgeDimensionEnum.values()}">
							<li>
								<input
									type="checkbox"
									id="${dimension}"
									value="${dimension}"
								/>
								<label class="knowledge-dim-label" title="${dimension.getInfo()}" for="${dimension}">
									${dimension}
								</label>
							</li>
						</g:each>
					</ul>
					<g:img
						dir="images/content"
						file="knowDimNone.png"
						id="dimImage"
						width="71"
						height="71"
					/>
				</span>
				<button
				  class="save showHoverNew resourceButton topicButtonGradient knowledgedimBtn"
							id="knowDimFinished"
							title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}"
				>
							<i class="fa fa-save green"></i>
							${message(code: 'Save Resource', default: 'Continue')}
				</button>
				<button
				  class="cancel showHoverNew resourceButton topicButtonGradient"
							id="closeKnowDim"
							title="${Help.toolTip("OVERVIEW", "Save Selected Resources and Save")}"
				>
							<i class="fa fa-times red"></i>
							${message(code: 'Cancel Resource', default: ' Cancel')}
				</button>

			</div>
		</div>


	</body>
</html>
