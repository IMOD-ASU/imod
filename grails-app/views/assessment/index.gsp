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
							<g:checkBox name="knowledgeDimension" value="${knowledgeDimension.id}" id="knowledge-dimension-${index}" />
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
							<g:checkBox  name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" />
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
							<g:checkBox  name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" />
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
						<span>

							<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>

							<button id="new-technique">Add New Technique
							</button>

							<button id="favorites">Favorites
							</button>

							<button id="instructional-plan">Instructional Plan
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

					<h3 title="" class="showHover">
						Ideal Match
					</h3>

					<div id="ideal-matches" class="icons favorite"> </div>

					<h3 id="assessmentFavorites" title="" class="showHover">
						Favorites
					</h3>

					<div id="assessmentFavoritesDiv" class="icons favorite"></div>


					<h3 title="" class="showHover">
						Extended Match
					</h3>

					<div id="extended-matches" class="icons favorite"></div>
				</div>
				</g:if>
				<g:else>

				<div class="assessment-page-buttons-disabled">
					<span>

						<%-- Buttons for Add New Technique, Favorites and Instructional Plan--%>

						<button id="new-technique">Add New Technique
						</button>

						<button id="favorites">Favorites
						</button>

						<button id="instructional-plan">Instructional Plan
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
				<div id="add-new-technique" title="Add New Technique">
					<%--To render the add new Technique dialog box--%>
					<g:form controller="assessmentTechnique" method="post" id="${currentImod.id}" params="[learningObjectiveID: currentLearningObjective.id]">
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



						<g:actionSubmit value="Save" action="create" />
						<button id="create-assessment-cancel">
							Cancel
						</button>
					</g:form>
				</div>




		</td>
	</tr>



</table>




	</body>
</html>
