<g:form name="newTechnique" id="new-technique">
	<label>Title</label>
	<g:textField name="title" />

	<label>Learning Domain</label>
	<g:select
		name="learningDomain"
		from="${learningDomains}"
		optionKey="name" />
	<br />

	<label>Domain Category</label>
	<g:select
		name="domainCategory"
		from="${domainCategories}"
		optionKey="name" />
	<br />

	<label>Knowledge Dimension</label>
	<g:select
		name="knowledgeDimension"
		from="${knowledgeDimensions}"
		optionKey="description" />
	<br />

	<label>Delivery Mode</label>
	<g:select
		name="pedagogyMode"
		from="${pedagogyModes}"
		optionKey="name" />
	<br />

	<label>Location</label>
	<g:textField name="location" />

	<label>Focus</label>
	<g:select
		name="pedagogyMode"
		from="${pedagogyFocuses}"
		optionKey="focus" />
	<br />

	<label>Direction</label>
	<g:textField name="direction" />

	<label>Materials Required</label>
	<g:textField name="materials" />

	<label>Reference</label>
	<g:textField name="reference" />

	<label>Description of Strategy</label>
	<g:textField name="strategyDescription" />

	<label>Description of Activity</label>
	<g:textField name="activityDescription" />
</g:form>
