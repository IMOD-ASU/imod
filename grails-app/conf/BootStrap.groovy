import imodv6.ActionWord
import imodv6.ContentKnowledgeDomainCode
import imodv6.ContentPriorityCode
import imodv6.ContentResourceType
import imodv6.DomainCategory
import imodv6.Help
import imodv6.Imod
import imodv6.ImodUser
import imodv6.ImodUserRole
import imodv6.LearningDomain
import imodv6.PedagogyActivity
import imodv6.PedagogyActivityDuration
import imodv6.PedagogyActivityFocus
import imodv6.PedagogyMode
import imodv6.PedagogyReference
import imodv6.PedagogyReferenceType
import imodv6.PedagogyTechnique
import imodv6.Role


class BootStrap {

	def init = { servletContext ->
		def tempRole

		/**
		 * Generates the user security roles
		 */
		if (Role.list().size() == 0) {
			new Role(
				authority: "ROLE_ANONYMOUS"
			).save();

			new Role(
				authority: "ROLE_ADMIN"
			).save();

			tempRole = new Role(
				authority: "ROLE_USER"
			).save();
		}

		/**
		 * Generates a sample IMOD user
		 * with project default username and password
		 * As well as a sample imod for that user
		 */
		if (ImodUser.count() < 1){
			def developer = new ImodUser(
				username: "postgres",
				password: "postgres",
				enabled: true,
				accountExpired: false,
				accountLocked: false,
				passwordExpired: false
			).save(flush: true)

			new ImodUserRole(
				imodUser: developer,
				role: tempRole
			).save(flush: true)

			def defaultImod = new Imod(
				owner: developer,
				name: "sample",
				url: "sample.com",
				imodNumber: "1",
				subjectArea: "sample"
			)
			developer.addToImods(defaultImod)
			developer.save(flush: true)
		}


		/**
		 * This is filling the information for the help box
		 * @param tabFieldUiId [description]
		 * @param tabId [description]
		 * @param tabFieldId [description]
		 * @param tabFieldName [description]
		 * @param text [description]
		 */
		if (Help.count() == 0) {
			/**
			 *
			 * These are the help information for the Course Overview tab
			 * Course Overview is the 1st tab
			 *
			 */
			new Help(
				tabFieldUiId: "Save Course Overview",
				tabId: "OVERVIEW",
				tabFieldId: 1,
				tabFieldName: "Save Course Overview",
				text: "Click on this button to save a Course overview"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Delete Course Overview",
				tabId: "OVERVIEW",
				tabFieldId: 2,
				tabFieldName: "Delete Course Overview",
				text: "Click on this button to remove a Course overview"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Schedule start Date",
				tabId: "OVERVIEW",
				tabFieldId: 3,
				tabFieldName: "Schedule start Date",
				text: "Click on this button to select a Schedule start date"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Schedule end Date",
				tabId: "OVERVIEW",
				tabFieldId: 4,
				tabFieldName: "Schedule end Date",
				text: "Click on this button to select a Schedule end date"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add instructor",
				tabId: "OVERVIEW",
				tabFieldId: 5,
				tabFieldName: "Add instructor",
				text: "Click on this button to add a Instructor"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Last Name Label",
				tabId: "OVERVIEW",
				tabFieldId: 6,
				tabFieldName: "Last Name",
				text: "Last Name"
			).save(flush: true)

			new Help(
				tabFieldUiId: "First Name Label",
				tabId: "OVERVIEW",
				tabFieldId: 7,
				tabFieldName: "First Name",
				text: "First Name"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Email Label",
				tabId: "OVERVIEW",
				tabFieldId: 8,
				tabFieldName: "Email",
				text: "Email"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Office Hours Label",
				tabId: "OVERVIEW",
				tabFieldId: 9,
				tabFieldName: "Office Hours",
				text: "Office Hours"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Web Page Label",
				tabId: "OVERVIEW",
				tabFieldId: 10,
				tabFieldName: "Web Page",
				text: "Web Page"
			).save(flush: true)


			/**
			 *
			 * These are the help information for the Learning Objective tab
			 * Learning Objective is the 2st tab
			 *
			 */
			new Help(
				tabFieldUiId: "Add Learning Objective",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Add Learning Objective",
				text: "Click on this button to add a Learning Objective"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Learning Domain",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Learning Domain",
				text: "Select a Domain for student to Learn"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Learning Category",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Learning Category",
				text: "Select a Category for student to Learn"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Hide from Objective",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Hide from Objective",
				text: "Hides this custom condition from the Learning Objective"
			).save(flush: true)

			/**
			 *
			 * These are the help tips for the Content Tab
			 * Content is the 3rd Tab
			 *
			 */
			new Help(
				tabFieldUiId: "Add Learning Objective",
				tabId: "CONTENT",
				tabFieldId: 1,
				tabFieldName: "Add Learning Objective",
				text: "Click on this button to add a Learning Objective"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Remove Learning Objective",
				tabId: "CONTENT",
				tabFieldId: 2,
				tabFieldName: "Remove Learning Objective",
				text: "Click on this button to remove a Learning Objective"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add Objective",
				tabId: "CONTENT",
				tabFieldId: 3,
				tabFieldName: "Add Objective",
				text: "Add Objective"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Topic Schedule",
				tabId: "CONTENT",
				tabFieldId: 4,
				tabFieldName: "Topic Schedule",
				text: "Click on this button to add a Topic Schedule"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Topic Map",
				tabId: "CONTENT",
				tabFieldId: 5,
				tabFieldName: "Topic Map",
				text: "Topic Map"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add topic schedule",
				tabId: "CONTENT",
				tabFieldId: 6,
				tabFieldName: "Add topic schedule",
				text: "Add topic schedule"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add Topic Form",
				tabId: "CONTENT",
				tabFieldId: 7,
				tabFieldName: "Add Topic",
				text: "Add Topic"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Remove Topic Widget",
				tabId: "CONTENT",
				tabFieldId: 8,
				tabFieldName: "Remove Topic Widget",
				text: "Remove Topic"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add Topic",
				tabId: "CONTENT",
				tabFieldId: 9,
				tabFieldName: "Add Topic",
				text: "Click on this button to add a Topic"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Remove Topic",
				tabId: "CONTENT",
				tabFieldId: 10,
				tabFieldName: "Remove Topic",
				text: "Click on this button to remove a Topic"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Add Resource",
				tabId: "CONTENT",
				tabFieldId: 11,
				tabFieldName: "Add Resource",
				text: "Add Resource"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Topic Distributions",
				tabId: "CONTENT",
				tabFieldId: 12,
				tabFieldName: "Topic Distributions",
				text: "Click on this button to display topic distributions"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Topic Label",
				tabId: "CONTENT",
				tabFieldId: 13,
				tabFieldName: "Topic",
				text: "Topic"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Knowledge Dimension Label",
				tabId: "CONTENT",
				tabFieldId: 14,
				tabFieldName: "Knowledge Dimension",
				text: "Knowledge Dimension"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Priority Label",
				tabId: "CONTENT",
				tabFieldId: 15,
				tabFieldName: "Priority Label",
				text: "Priority"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Resources Label",
				tabId: "CONTENT",
				tabFieldId: 16,
				tabFieldName: "Resources Label",
				text: "Resources"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Pre-Req Label",
				tabId: "CONTENT",
				tabFieldId: 17,
				tabFieldName: "Pre-Req Label",
				text: "Pre-Req"
			).save(flush: true)

			/**
			 *
			 * Help tool tips for the Pedagogy Tab
			 * Padagogy is the 5th tab
			 *
			 */
			new Help(
				tabFieldUiId: "Add New Technique",
				tabId: "PEDAGOGY",
				tabFieldId: 1,
				tabFieldName: "Add New Technique",
				text: "Click on this button to add a new technique"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Clone technique",
				tabId: "PEDAGOGY",
				tabFieldId: 2,
				tabFieldName: "Clone technique",
				text: "Click on this button to clone a technique"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Favorites",
				tabId: "PEDAGOGY",
				tabFieldId: 3,
				tabFieldName: "Favorites",
				text: "Favorites displays the techniques that have be made the user's favorites"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Instructional Plan",
				tabId: "PEDAGOGY",
				tabFieldId: 4,
				tabFieldName: "Instructional Plan",
				text: "Yet to be implemented"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Ideal Match",
				tabId: "PEDAGOGY",
				tabFieldId: 5,
				tabFieldName: "Ideal Match",
				text: "Ideal Match displays the techniques that exactly match the Domain, Domain Category and Knowledge Dimension of the objective selected."
			).save(flush: true)

			new Help(
				tabFieldUiId: "Extended Match",
				tabId: "PEDAGOGY",
				tabFieldId: 6,
				tabFieldName: "Extended Match",
				text: "Extended Match displays the techniques that match the Domain, Domain Category and Knowledge Dimension of the selections made in the Filter Options"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Domain",
				tabId: "PEDAGOGY",
				tabFieldId: 7,
				tabFieldName: "Domain",
				text: "Bloom's Taxonomy definition the domains of educational activities or learning (Cognitive, Affective and Psychomotive)"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Domain Category",
				tabId: "PEDAGOGY",
				tabFieldId: 8,
				tabFieldName: "Domain Category",
				text: "The categories were expressed as verbs rather than nouns as Remembering, Understanding, Applying, Analyzing, Evaluating, and Creating"
			).save(flush: true)

			new Help(
				tabFieldUiId: "Knowledge Dimension",
				tabId: "PEDAGOGY",
				tabFieldId: 9,
				tabFieldName: "Knowledge Dimension",
				text: "The Knowledge Dimension embodied both noun and verb aspects and categorized as Factual, Conceptual, Procedural and Metacognitive Knowledge "
			).save(flush: true)

			new Help(
				tabFieldUiId: "Refresh",
				tabId: "Refresh",
				tabFieldId: 10,
				tabFieldName: "Web Page",
				text: "Clicking on Refresh displays the techniques that belongs the selections on the Domain, Domain Category and Knowledge Dimension in the Extended match."
			).save(flush: true)
		}


		/**
		 * Content Priority
		 * TODO: What is this???
		 */
		if (ContentPriorityCode.count() == 0) {
			new ContentPriorityCode(
				description: "Critical"
			).save(flush: true)

			new ContentPriorityCode(
				description: "Very important"
			).save(flush: true)

			new ContentPriorityCode(
				description: "Good to know"
			).save(flush: true)
		}


		if (LearningDomain.count() < 1) {
			/**
			 * Generate Learning Domains, Domain Categories and Action Words
			 */
			def learningDomainCognitive = new LearningDomain(
				name: "Cognitive"
			)
			learningDomainCognitive.save(flush: true)

			def learningDomainAffective = new LearningDomain(
				name: "Affective"
			)
			learningDomainAffective.save(flush: true)

			def learningDomainPsychomotor = new LearningDomain(
				name: "Psychomotor"
			)
			learningDomainPsychomotor.save(flush: true)

			/**
			 * This creates the domain Category instances,
			 * these are used to link Learning Domains to Action Words
			 */

			/**
			 * These are the Categories for the Cognitive Domain
			 */
			def domainCategoryRemembering = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Remembering"
			)

			def domainCategoryUnderstanding = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Understanding"
			)

			def domainCategoryApplying = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Applying"
			)

			def domainCategoryAnalyzing = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Analyzing"
			)

			def domainCategoryEvaluating = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Evaluating"
			)

			def domainCategoryCreating = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Creating"
			)

			/**
			 * This Links each Domain Category to its Learning Domain
			 */
			learningDomainCognitive.addToDomainCategories(domainCategoryRemembering)
			learningDomainCognitive.addToDomainCategories(domainCategoryUnderstanding)
			learningDomainCognitive.addToDomainCategories(domainCategoryApplying)
			learningDomainCognitive.addToDomainCategories(domainCategoryAnalyzing)
			learningDomainCognitive.addToDomainCategories(domainCategoryEvaluating)
			learningDomainCognitive.addToDomainCategories(domainCategoryCreating)

			/**
			 * These are the Categories for the Affective Domain
			 */
			def domainCategoryReceivingPhenomena = new DomainCategory(
				domain: learningDomainAffective,
				name: "Receiving Phenomena"
			)

			def domainCategoryResponding = new DomainCategory(
				domain: learningDomainAffective,
				name: "Responding"
			)

			def domainCategoryValuing = new DomainCategory(
				domain: learningDomainAffective,
				name: "Valuing"
			)

			def domainCategoryOrganisation = new DomainCategory(
				domain: learningDomainAffective,
				name: "Organisation"
			)

			def domainCategoryInternalisingValues = new DomainCategory(
				domain: learningDomainAffective,
				name: "Internalising Values"
			)

			/**
			 * This Links each Domain Category to its Learning Domain
			 */
			learningDomainAffective.addToDomainCategories(domainCategoryReceivingPhenomena)
			learningDomainAffective.addToDomainCategories(domainCategoryResponding)
			learningDomainAffective.addToDomainCategories(domainCategoryValuing)
			learningDomainAffective.addToDomainCategories(domainCategoryOrganisation)
			learningDomainAffective.addToDomainCategories(domainCategoryInternalisingValues)

			/**
			 * creating action words for remembering category
			 */
			def actionWordRemembering = new ActionWord(
				category: domainCategoryRemembering,
				actionWord: "Recognizing"
			)

			def actionWordRecalling = new ActionWord(
				category: domainCategoryRemembering,
				actionWord: "Recalling"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryRemembering.addToActionWords(actionWordRemembering)
			domainCategoryRemembering.addToActionWords(actionWordRecalling)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryRemembering.save(flush: true)

			/**
			 * creating action words for understanding category
			 */
			def actionWordInterpreting = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Interpreting"
			)

			def actionWordExemplifying = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Exemplifying"
			)

			def actionWordClassifying = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Classifying"
			)

			def actionWordSummarizing = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Summarizing"
			)

			def actionWordInferring = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Inferring"
			)

			def actionWordComparing = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Comparing"
			)

			def actionWordExplaining = new ActionWord(
				category: domainCategoryUnderstanding,
				actionWord: "Explaining"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryUnderstanding.addToActionWords(actionWordInterpreting)
			domainCategoryUnderstanding.addToActionWords(actionWordExemplifying)
			domainCategoryUnderstanding.addToActionWords(actionWordClassifying)
			domainCategoryUnderstanding.addToActionWords(actionWordSummarizing)
			domainCategoryUnderstanding.addToActionWords(actionWordInferring)
			domainCategoryUnderstanding.addToActionWords(actionWordComparing)
			domainCategoryUnderstanding.addToActionWords(actionWordExplaining)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryUnderstanding.save(flush: true)

			/**
			 * creating action words for applying category
			 */
			def actionWordExecuting = new ActionWord(
				category: domainCategoryApplying,
				actionWord: "Executing"
			)

			def actionWordImplementing = new ActionWord(
				category: domainCategoryApplying,
				actionWord: "Implementing"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryApplying.addToActionWords(actionWordExecuting)
			domainCategoryApplying.addToActionWords(actionWordImplementing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryApplying.save(flush: true)

			/**
			 * creating action words for analyzing category
			 */
			def actionWordDifferentiating = new ActionWord(
				category: domainCategoryAnalyzing,
				actionWord: "Differentiating"
			)

			def actionWordOrganizing = new ActionWord(
				category: domainCategoryAnalyzing,
				actionWord: "Organizing"
			)

			def actionWordAttributing = new ActionWord(
				category: domainCategoryAnalyzing,
				actionWord: "Attributing"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryAnalyzing.addToActionWords(actionWordDifferentiating)
			domainCategoryAnalyzing.addToActionWords(actionWordOrganizing)
			domainCategoryAnalyzing.addToActionWords(actionWordAttributing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryAnalyzing.save(flush: true)

			/**
			 * creating action words for evaluating category
			 */
			def actionWordChecking = new ActionWord(
				category: domainCategoryEvaluating,
				actionWord: "Checking"
			)

			def actionWordCritiquing = new ActionWord(
				category: domainCategoryEvaluating,
				actionWord: "Critiquing"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryEvaluating.addToActionWords(actionWordChecking)
			domainCategoryEvaluating.addToActionWords(actionWordCritiquing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryEvaluating.save(flush: true)

			/**
			 * creating action words for evaluating category
			 */
			def actionWordGenerating = new ActionWord(
				category: domainCategoryCreating,
				actionWord: "Generating"
			)

			def actionWordPlanning = new ActionWord(
				category: domainCategoryCreating,
				actionWord: "Planning"
			)

			def actionWordProducing = new ActionWord(
				category: domainCategoryCreating,
				actionWord: "Producing"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryCreating.addToActionWords(actionWordGenerating)
			domainCategoryCreating.addToActionWords(actionWordPlanning)
			domainCategoryCreating.addToActionWords(actionWordProducing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryCreating.save(flush: true)
		}

		if (ContentKnowledgeDomainCode.count() < 1) {
			new ContentKnowledgeDomainCode(
				description: "Factual"
			).save(flush: true)

			new ContentKnowledgeDomainCode(
				description: "Conceptual"
			).save(flush: true)

			new ContentKnowledgeDomainCode(
				description: "Procedural"
			).save(flush: true)

			new ContentKnowledgeDomainCode(
				description: "Metacognitive"
			).save(flush: true)
		}

		if (ContentResourceType.count() == 0) {
			new ContentResourceType(
				description: "Document"
			).save(flush: true)

			new ContentResourceType(
				description: "Book"
			).save(flush: true)

			new ContentResourceType(
				description: "Chapter"
			).save(flush: true)

			new ContentResourceType(
				description: "URL"
			).save(flush: true)
		}

		if(PedagogyMode.count() == 0){
			new PedagogyMode(
				name:"online"
			).save(flush:true)

			new PedagogyMode(
				name:"in-person"
			).save(flush:true)

			new PedagogyMode(
				name:"hybrid"
			).save(flush:true)
		}

		if(PedagogyReferenceType.count() == 0){
			new PedagogyReferenceType(
				description:"Book"
			).save(flush:true)

			new PedagogyReferenceType(
				description:"Web"
			).save(flush:true)
		}

		if(PedagogyActivityDuration.count() == 0){
			new PedagogyActivityDuration(
				duration:"Single Session"
			).save(flush:true)

			new PedagogyActivityDuration(
				duration:"Multiple Session"
			).save(flush:true)

			new PedagogyActivityDuration(
				duration:"Both Session"
			).save(flush:true)
		}
		if(PedagogyActivityFocus.count() == 0){
			new PedagogyActivityFocus(
				focus:"Reading"
			).save(flush:true)

			new PedagogyActivityFocus(
				focus:"Writing"
			).save(flush:true)

			new PedagogyActivityFocus(
				focus:"Discussing"
			).save(flush:true)

			new PedagogyActivityFocus(
				focus:"Presenting"
			).save(flush:true)
		}

		if(PedagogyTechnique.count() == 0){
			/*Pedagogy Technique*/
			def pedagogyTech = new PedagogyTechnique(
				pedagogyTitle:"Jigsaw",
				pedagogyDescription: "As in a jigsaw puzzle, each piece representing each student's part is essential for the completion and full understanding of the final product. ",
				domain:LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList([
					'Remembering'
				]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual",
					"Procedural",
					"Metacognitive"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Reading",
					"Writing",
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("in-person")
			).save(flush:true)

			new PedagogyActivity(
				title:"jigsaw",
				description:"Small tasks are split among each student in each group. Eventually each student will come back to her or his jigsaw group and will try to present a well-organized report to the group. As a team the students organize their work and present it as a whole.",
				example:"Research on different computing languages was to be done. The students were split into five teams of four members each and each team was assigned a language. The students were asked to divide the the tasks to be researched based on the history, implementation, application of the language and comparison with other languages. Students worked individually and eventually put the wok together, then gave a presentation on each area of research.",
				material:"clicker, paper",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech
			).save(flush:true)

			new PedagogyReference(
				title:"Student_Engagement_Techniques",
				author:"Elizabeth F. Barkley",
				referenceLinkISBN:"978-0-470-28191-8",
				referenceType:PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique:pedagogyTech
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle:"Structured Problem Solving",
				pedagogyDescription: "Provides students with a process for solving complex, content based problems within a specified amount of time. It breaks the problem into specific steps and thus students learn to identify, analyze, and solve problems in an organized way",
				domain:LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Metacognitive"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title:"Step-2",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example: "The Dewey Six-Step Problem Solving Technique",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-3",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example: "The Dewey Six-Step Problem Solving Technique",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-1",
				description: "Organize students into teams and assign them a complex problem to solve",
				example: "Problem could be like evaluate the effectiveness of the antacids",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "Face-to Face/ Video / Audio  Lectures ",
				pedagogyDescription: "Lecture",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					'Remembering',
					"Understanding"
				]),
				knowledge: ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "Partially Guided Programming Exercise",
				pedagogyDescription: "Partially Guided Programming Exercise ",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge: ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Conceptual",
					"Procedural"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Reading",
					"Writing",
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("in-person")
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "Think Aloud Pair Problem Solving",
				pedagogyDescription: "Student pairs receive a series of problems as well as specific roles - problem solver and listener - then switch with each problem. The problem solver thinks aloud, talking through the steps of solving the problem, while the partner listens, following the steps, attempting to understand the reasoning behind the steps, and offering suggestions if there is a misstep.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge: ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual",
					"Procedural",
					"Metacognitive"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Spend time developing an appropriate set of field related problems to solve within a limited time frame",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-2",
				description: "Ask students to form pairs",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-3",
				description: "Ask students to solve problems alternating the roles with each new problem",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-4",
				description: "Call completion when all problems have been solved",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "Frames",
				pedagogyDescription: "Instructors give students a template of sentence stem that provides the shape of a short essay but not the content. Students complete the sentence, expressing their own ideas in their own words within a clear and organized framework.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					"Analyzing",
					"Evaluating"
				]),
				knowledge: ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual",
					"Procedural",
					"Metacognitive"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Reading",
					"Writing"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Choose a topic and write a brief essay that you have a skeleton of the esay",
				example: "Theory X exposes that ________ and is very useful because it offers insight into __________. ",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-2",
				description: "Make copies of the frames along with the directions to use as handouts and distribute it to each student.",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-3",
				description: "Students write the essay using the framework as a guide",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-4",
				description: "Assess the student essay based on the original essay ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "In-Class Portfolio",
				pedagogyDescription: "Students collect and organize lecture notes, essay responses to prompts presented in class, summaries of discussions, personal reflections into a portfolio and submit for evaluation two to three times during the academic year.",
				domain: LearningDomain.findAllByNameInList(['Cognitive']),
				category: DomainCategory.findAllByNameInList(["Applying","Analyzing"]),
				knowledge: ContentKnowledgeDomainCode.findAllByDescriptionInList(["Factual", "Conceptual", "Procedural", "Metacognitive"]),
				focus: PedagogyActivityFocus.findAllByFocusInList(["Writing", "Discussing"]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Organise class sessions so that in addition to listening, students are actively integrating and applying what they learnt by writing, discussing and problem solving ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-2",
				description: "Determine portfolio parameters",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-3",
				description: "Decide how portfolio will be evaluated and determine the grading rubrics. Explain the process and expectations to students",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-4",
				description: "Assess the student essay based on the original essay ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle: "Seminar",
				pedagogyDescription: "Students make formal presentations of an original paper to a small group of peers.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					"Understanding",
					"Remembering"
				]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Reading",
					"Writing",
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save(flush:true)

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Assign students research papers",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-2",
				description: "Explain the time frame and tasks, and give time to discuss the paper with peers",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			new PedagogyActivity(
				title: "Step-3",
				description: "Students will present the paper in the class and discuss the questions raised by other students.",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save(flush:true)
		}
	}
	def destroy = {
	}
}
