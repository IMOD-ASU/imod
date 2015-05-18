<div id="filter-pedagogy-techniques">
	<h3>
		Knowledge Dimensions
	</h3>
	<div>
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
					<g:checkBox name="learningDomain" value="${learningDomain.id}" id="learning-domain-${index}" />
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
					<g:checkBox name="domainCategory" value="${domainCategory.id}" id="domain-category-${index}" />
				</li>
			</g:each>
		</ul>
	</div>
</div>
