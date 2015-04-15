import imod.ActionWordCategory
import imod.KnowledgeDimension
import imod.ContentPriorityCode
import imod.ResourceType
import imod.DomainCategory
import imod.Help
import imod.Imod
import imod.ImodUser
import imod.ImodUserRole
import imod.LearningDomain
import imod.PedagogyActivity
import imod.PedagogyActivityDuration
import imod.PedagogyActivityFocus
import imod.PedagogyMode
import imod.PedagogyReference
import imod.PedagogyReferenceType
import imod.PedagogyTechnique
import imod.Role
import imod.ScheduleRepeats
import imod.ScheduleRepeatsEvery
import imod.ScheduleWeekDays
import imod.Audience


class BootStrap {

	def init = { servletContext ->
		def tempRole

		/**
		 * Generates the user security roles
		 */
		// FIXME there should be a single check to see if data exists that is shared by all domains
		if (Role.list().size() == 0) {
			new Role(
				authority: "ROLE_ANONYMOUS"
			).save()

			new Role(
				authority: "ROLE_ADMIN"
			).save()

			tempRole = new Role(
				authority: "ROLE_USER"
			).save()
		}

		/**
		 * Generates a sample IMOD user
		 * with project default username and password
		 */
		if (ImodUser.count() < 1){
			def developer = new ImodUser(
				username: "postgres",
				password: "postgres",
				enabled: true,
				accountExpired: false,
				accountLocked: false,
				passwordExpired: false,
				email: 'imod.grails@gmail.com'
			).save()

			new ImodUserRole(
				imodUser: developer,
				role: tempRole
			).save()
		}


		/**
		 * This is filling the information for the help box
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
			).save()

			new Help(
				tabFieldUiId: "Delete Course Overview",
				tabId: "OVERVIEW",
				tabFieldId: 2,
				tabFieldName: "Delete Course Overview",
				text: "Click on this button to remove a Course overview"
			).save()

			new Help(
				tabFieldUiId: "Schedule start Date",
				tabId: "OVERVIEW",
				tabFieldId: 3,
				tabFieldName: "Schedule start Date",
				text: "Click on this button to select a Schedule start date"
			).save()

			new Help(
				tabFieldUiId: "Schedule end Date",
				tabId: "OVERVIEW",
				tabFieldId: 4,
				tabFieldName: "Schedule end Date",
				text: "Click on this button to select a Schedule end date"
			).save()

			new Help(
				tabFieldUiId: "Add instructor",
				tabId: "OVERVIEW",
				tabFieldId: 5,
				tabFieldName: "Add instructor",
				text: "Click on this button to add a Instructor"
			).save()

			new Help(
				tabFieldUiId: "Last Name Label",
				tabId: "OVERVIEW",
				tabFieldId: 6,
				tabFieldName: "Last Name",
				text: "Last Name"
			).save()

			new Help(
				tabFieldUiId: "First Name Label",
				tabId: "OVERVIEW",
				tabFieldId: 7,
				tabFieldName: "First Name",
				text: "First Name"
			).save()

			new Help(
				tabFieldUiId: "Email Label",
				tabId: "OVERVIEW",
				tabFieldId: 8,
				tabFieldName: "Email",
				text: "Email"
			).save()

			new Help(
				tabFieldUiId: "Office Hours Label",
				tabId: "OVERVIEW",
				tabFieldId: 9,
				tabFieldName: "Office Hours",
				text: "Office Hours"
			).save()

			new Help(
				tabFieldUiId: "Web Page Label",
				tabId: "OVERVIEW",
				tabFieldId: 10,
				tabFieldName: "Web Page",
				text: "Web Page"
			).save()


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
			).save()

			new Help(
				tabFieldUiId: "Learning Domain",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Learning Domain",
				text: "Select a Domain for student to Learn"
			).save()

			new Help(
				tabFieldUiId: "Learning Category",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Learning Category",
				text: "Select a Category for student to Learn"
			).save()

			new Help(
				tabFieldUiId: "Hide from Objective",
				tabId: "LEARNINGOBJECTIVE",
				tabFieldId: 2,
				tabFieldName: "Hide from Objective",
				text: "Hides this custom condition from the Learning Objective"
			).save()

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
			).save()

			new Help(
				tabFieldUiId: "Remove Learning Objective",
				tabId: "CONTENT",
				tabFieldId: 2,
				tabFieldName: "Remove Learning Objective",
				text: "Click on this button to remove a Learning Objective"
			).save()

			new Help(
				tabFieldUiId: "Add Objective",
				tabId: "CONTENT",
				tabFieldId: 3,
				tabFieldName: "Add Objective",
				text: "Add Objective"
			).save()

			new Help(
				tabFieldUiId: "Topic Schedule",
				tabId: "CONTENT",
				tabFieldId: 4,
				tabFieldName: "Topic Schedule",
				text: "Click on this button to add a Topic Schedule"
			).save()

			new Help(
				tabFieldUiId: "Topic Map",
				tabId: "CONTENT",
				tabFieldId: 5,
				tabFieldName: "Topic Map",
				text: "Topic Map"
			).save()

			new Help(
				tabFieldUiId: "Add topic schedule",
				tabId: "CONTENT",
				tabFieldId: 6,
				tabFieldName: "Add topic schedule",
				text: "Add topic schedule"
			).save()

			new Help(
				tabFieldUiId: "Add Topic Form",
				tabId: "CONTENT",
				tabFieldId: 7,
				tabFieldName: "Add Topic",
				text: "Add Topic"
			).save()

			new Help(
				tabFieldUiId: "Remove Topic Widget",
				tabId: "CONTENT",
				tabFieldId: 8,
				tabFieldName: "Remove Topic Widget",
				text: "Remove Topic"
			).save()

			new Help(
				tabFieldUiId: "Add Topic",
				tabId: "CONTENT",
				tabFieldId: 9,
				tabFieldName: "Add Topic",
				text: "Click on this button to add a Topic"
			).save()

			new Help(
				tabFieldUiId: "Remove Topic",
				tabId: "CONTENT",
				tabFieldId: 10,
				tabFieldName: "Remove Topic",
				text: "Click on this button to remove a Topic"
			).save()

			new Help(
				tabFieldUiId: "Add Resource",
				tabId: "CONTENT",
				tabFieldId: 11,
				tabFieldName: "Add Resource",
				text: "Add Resource"
			).save()

			new Help(
				tabFieldUiId: "Topic Distributions",
				tabId: "CONTENT",
				tabFieldId: 12,
				tabFieldName: "Topic Distributions",
				text: "Click on this button to display topic distributions"
			).save()

			new Help(
				tabFieldUiId: "Topic Label",
				tabId: "CONTENT",
				tabFieldId: 13,
				tabFieldName: "Topic",
				text: "Topic"
			).save()

			new Help(
				tabFieldUiId: "Knowledge Dimension Label",
				tabId: "CONTENT",
				tabFieldId: 14,
				tabFieldName: "Knowledge Dimension",
				text: "Knowledge Dimension"
			).save()

			new Help(
				tabFieldUiId: "Priority Label",
				tabId: "CONTENT",
				tabFieldId: 15,
				tabFieldName: "Priority Label",
				text: "Priority"
			).save()

			new Help(
				tabFieldUiId: "Resources Label",
				tabId: "CONTENT",
				tabFieldId: 16,
				tabFieldName: "Resources Label",
				text: "Resources"
			).save()

			new Help(
				tabFieldUiId: "Pre-Req Label",
				tabId: "CONTENT",
				tabFieldId: 17,
				tabFieldName: "Pre-Req Label",
				text: "Pre-Req"
			).save()

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
			).save()

			new Help(
				tabFieldUiId: "Clone technique",
				tabId: "PEDAGOGY",
				tabFieldId: 2,
				tabFieldName: "Clone technique",
				text: "Click on this button to clone a technique"
			).save()

			new Help(
				tabFieldUiId: "Favorites",
				tabId: "PEDAGOGY",
				tabFieldId: 3,
				tabFieldName: "Favorites",
				text: "Favorites displays the techniques that have be made the user's favorites"
			).save()

			new Help(
				tabFieldUiId: "Instructional Plan",
				tabId: "PEDAGOGY",
				tabFieldId: 4,
				tabFieldName: "Instructional Plan",
				text: "Yet to be implemented"
			).save()

			new Help(
				tabFieldUiId: "Ideal Match",
				tabId: "PEDAGOGY",
				tabFieldId: 5,
				tabFieldName: "Ideal Match",
				text: "Ideal Match displays the techniques that exactly match the Domain, Domain Category and Knowledge Dimension of the objective selected."
			).save()

			new Help(
				tabFieldUiId: "Extended Match",
				tabId: "PEDAGOGY",
				tabFieldId: 6,
				tabFieldName: "Extended Match",
				text: "Extended Match displays the techniques that match the Domain, Domain Category and Knowledge Dimension of the selections made in the Filter Options"
			).save()

			new Help(
				tabFieldUiId: "Domain",
				tabId: "PEDAGOGY",
				tabFieldId: 7,
				tabFieldName: "Domain",
				text: "Bloom's Taxonomy definition the domains of educational activities or learning (Cognitive, Affective and Psychomotive)"
			).save()

			new Help(
				tabFieldUiId: "Domain Category",
				tabId: "PEDAGOGY",
				tabFieldId: 8,
				tabFieldName: "Domain Category",
				text: "The categories were expressed as verbs rather than nouns as Remembering, Understanding, Applying, Analyzing, Evaluating, and Creating"
			).save()

			new Help(
				tabFieldUiId: "Knowledge Dimension",
				tabId: "PEDAGOGY",
				tabFieldId: 9,
				tabFieldName: "Knowledge Dimension",
				text: "The Knowledge Dimension embodied both noun and verb aspects and categorized as Factual, Conceptual, Procedural and Metacognitive Knowledge "
			).save()

			new Help(
				tabFieldUiId: "Refresh",
				tabId: "Refresh",
				tabFieldId: 10,
				tabFieldName: "Web Page",
				text: "Clicking on Refresh displays the techniques that belongs the selections on the Domain, Domain Category and Knowledge Dimension in the Extended match."
			).save()
		}


		// Populate Audience types
		if (Audience.count() < 1) {
			def audience = new Audience(
				description: 'Lower Division'
			).save()

			def audience2 = new Audience(
				description: 'Upper Division'
			).save()

			def audience3 = new Audience(
				description: 'Undergraduate'
			).save()

			def audience4 = new Audience(
				description: 'Graduate'
			).save()
		}


		if(ScheduleRepeats.count() < 1)
		{
			def ScheduleRepeatsDaily = new ScheduleRepeats(
				description: "Daily"
				)
			ScheduleRepeatsDaily.save()

			def ScheduleRepeatsWeekDay = new ScheduleRepeats(
				description: "Every Weekday (Monday to Friday)"
				)
			ScheduleRepeatsWeekDay.save()

			def ScheduleRepeatsMW = new ScheduleRepeats(
				description: "Every Monday and Wednesday"
				)
			ScheduleRepeatsMW.save()

			def ScheduleRepeatsTTh = new ScheduleRepeats(
				description: "Every Tuesday and Thursday"
				)
			ScheduleRepeatsTTh.save()

			def ScheduleRepeatsWeekly = new ScheduleRepeats(
				description: "Weekly"
				)
			ScheduleRepeatsWeekly.save()
		}

		if(ScheduleRepeatsEvery.count() < 1)
		{
			for(int i =1; i <= 30; i++)
			{
				def ScheduleRepeatsE = new ScheduleRepeatsEvery(
					description: i)
				ScheduleRepeatsE.save()
			}
		}

		if(ScheduleWeekDays.count() < 1)
		{
			def ScheduleSunday = new ScheduleWeekDays(
				description:"Sunday"
				)
			ScheduleSunday.save()

			def ScheduleMonday = new ScheduleWeekDays(
				description:"Monday"
				)
			ScheduleMonday.save()

			def ScheduleTuesday = new ScheduleWeekDays(
				description:"Tuesday"
				)
			ScheduleTuesday.save()

			def ScheduleWednesday = new ScheduleWeekDays(
				description:"Wednesday"
				)
			ScheduleWednesday.save()

			def ScheduleThursday = new ScheduleWeekDays(
				description:"Thursday"
				)
			ScheduleThursday.save()

			def ScheduleFriday = new ScheduleWeekDays(
				description:"Friday"
				)
			ScheduleFriday.save()

			def ScheduleSaturday = new ScheduleWeekDays(
				description:"Saturday"
				)
			ScheduleSaturday.save()
		}


		if (LearningDomain.count() < 1) {
			/**
			 * Generate Learning Domains, Domain Categories and Action Words
			 */
			def learningDomainCognitive = new LearningDomain(
				name: "Cognitive"
			)
			learningDomainCognitive.save()

			def learningDomainAffective = new LearningDomain(
				name: "Affective"
			)
			learningDomainAffective.save()

			def learningDomainPsychomotor = new LearningDomain(
				name: "Psychomotor"
			)
			learningDomainPsychomotor.save()

			/**
			 * This creates the domain Category instances,
			 * these are used to link Learning Domains to Action Words
			 */

			/**
			 * These are the Categories for the Cognitive Domain
			 */
			def domainCategoryRemembering = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Remember"
			)

			def domainCategoryUnderstanding = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Understand"
			)

			def domainCategoryApplying = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Apply"
			)

			def domainCategoryAnalyzing = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Analyze"
			)

			def domainCategoryEvaluating = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Evaluate"
			)

			def domainCategoryCreating = new DomainCategory(
				domain: learningDomainCognitive,
				name: "Create"
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
				name: "Respond"
			)

			def domainCategoryValuing = new DomainCategory(
				domain: learningDomainAffective,
				name: "Value"
			)

			def domainCategoryOrganisation = new DomainCategory(
				domain: learningDomainAffective,
				name: "Organization"
			)

			def domainCategoryInternalisingValues = new DomainCategory(
				domain: learningDomainAffective,
				name: "Internalizing Values"
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
			 * These are the Categories for the Psychomotor Domain
			 */
			def domainCategoryPerception = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Perception"
			)

			def domainCategorySet = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Set"
			)

			def domainCategoryGuidedResponse = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Guided Response"
			)

			def domainCategoryMechanism = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Mechanism"
			)

			def domainCategoryComplexOvertResponse = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Complex Overt Response"
			)

			def domainCategoryAdaptation = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Adaptation"
			)

			def domainCategoryOrigination = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: "Origination"
			)

			/**
			 * This Links each Domain Category to its Learning Domain
			 */
			learningDomainPsychomotor.addToDomainCategories(domainCategoryPerception)
			learningDomainPsychomotor.addToDomainCategories(domainCategorySet)
			learningDomainPsychomotor.addToDomainCategories(domainCategoryGuidedResponse)
			learningDomainPsychomotor.addToDomainCategories(domainCategoryMechanism)
			learningDomainPsychomotor.addToDomainCategories(domainCategoryComplexOvertResponse)
			learningDomainPsychomotor.addToDomainCategories(domainCategoryAdaptation)
			learningDomainPsychomotor.addToDomainCategories(domainCategoryOrigination)


			/**
			 * creating action words for remembering category
			 */
			def actionWordCategoryRecognizing = new ActionWordCategory(
				domainCategory: domainCategoryRemembering,
				actionWordCategory: "Recognize"
			)

			def actionWordCategoryRecalling = new ActionWordCategory(
				domainCategory: domainCategoryRemembering,
				actionWordCategory: "Recall"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryRemembering.addToActionWordCategories(actionWordCategoryRecognizing)
			domainCategoryRemembering.addToActionWordCategories(actionWordCategoryRecalling)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryRemembering.save()

			/**
			 * creating action words for understanding category
			 */
			def actionWordCategoryInterpreting = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Interpret"
			)

			def actionWordCategoryExemplifying = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Exemplify"
			)

			def actionWordCategoryClassifying = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Classify"
			)

			def actionWordCategorySummarizing = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Summarize"
			)

			def actionWordCategoryInferring = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Infer"
			)

			def actionWordCategoryComparing = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Compare"
			)

			def actionWordCategoryExplaining = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: "Explain"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryInterpreting)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryExemplifying)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryClassifying)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategorySummarizing)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryInferring)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryComparing)
			domainCategoryUnderstanding.addToActionWordCategories(actionWordCategoryExplaining)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryUnderstanding.save()

			/**
			 * creating action words for applying category
			 */
			def actionWordCategoryExecuting = new ActionWordCategory(
				domainCategory: domainCategoryApplying,
				actionWordCategory: "Execute"
			)

			def actionWordCategoryImplementing = new ActionWordCategory(
				domainCategory: domainCategoryApplying,
				actionWordCategory: "Implement"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryApplying.addToActionWordCategories(actionWordCategoryExecuting)
			domainCategoryApplying.addToActionWordCategories(actionWordCategoryImplementing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryApplying.save()

			/**
			 * creating action words for analyzing category
			 */
			def actionWordCategoryDifferentiating = new ActionWordCategory(
				domainCategory: domainCategoryAnalyzing,
				actionWordCategory: "Differentiate"
			)

			def actionWordCategoryOrganizing = new ActionWordCategory(
				domainCategory: domainCategoryAnalyzing,
				actionWordCategory: "Organize"
			)

			def actionWordCategoryAttributing = new ActionWordCategory(
				domainCategory: domainCategoryAnalyzing,
				actionWordCategory: "Attribute"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryAnalyzing.addToActionWordCategories(actionWordCategoryDifferentiating)
			domainCategoryAnalyzing.addToActionWordCategories(actionWordCategoryOrganizing)
			domainCategoryAnalyzing.addToActionWordCategories(actionWordCategoryAttributing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryAnalyzing.save()

			/**
			 * creating action words for evaluating category
			 */
			def actionWordCategoryChecking = new ActionWordCategory(
				domainCategory: domainCategoryEvaluating,
				actionWordCategory: "Check"
			)

			def actionWordCategoryCritiquing = new ActionWordCategory(
				domainCategory: domainCategoryEvaluating,
				actionWordCategory: "Critique"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryEvaluating.addToActionWordCategories(actionWordCategoryChecking)
			domainCategoryEvaluating.addToActionWordCategories(actionWordCategoryCritiquing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryEvaluating.save()

			/**
			 * creating action words for evaluating category
			 */
			def actionWordCategoryGenerating = new ActionWordCategory(
				domainCategory: domainCategoryCreating,
				actionWordCategory: "Generate"
			)

			def actionWordCategoryPlanning = new ActionWordCategory(
				domainCategory: domainCategoryCreating,
				actionWordCategory: "Plan"
			)

			def actionWordCategoryProducing = new ActionWordCategory(
				domainCategory: domainCategoryCreating,
				actionWordCategory: "Produce"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryCreating.addToActionWordCategories(actionWordCategoryGenerating)
			domainCategoryCreating.addToActionWordCategories(actionWordCategoryPlanning)
			domainCategoryCreating.addToActionWordCategories(actionWordCategoryProducing)

			/**
			 * Saving the action words and the domain category
			 */
			domainCategoryCreating.save()

			/**
			 * creating action words for ReceivingPhenomena category
			 */
			def actionWordCategoryListens = new ActionWordCategory(
				domainCategory: domainCategoryReceivingPhenomena,
				actionWordCategory: "Listens"
			)

			def actionWordCategoryRemembers = new ActionWordCategory(
				domainCategory: domainCategoryReceivingPhenomena,
				actionWordCategory: "Remembers"
			)

			def actionWordCategoryNames = new ActionWordCategory(
				domainCategory: domainCategoryReceivingPhenomena,
				actionWordCategory: "Names"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryReceivingPhenomena.addToActionWordCategories(actionWordCategoryListens)
			domainCategoryReceivingPhenomena.addToActionWordCategories(actionWordCategoryRemembers)
			domainCategoryReceivingPhenomena.addToActionWordCategories(actionWordCategoryNames)

			domainCategoryReceivingPhenomena.save()

			/**
			 * creating action words for Responding category
			 */
			def actionWordCategoryParticipates = new ActionWordCategory(
				domainCategory: domainCategoryResponding,
				actionWordCategory: "Participates"
			)

			def actionWordCategoryDiscusses = new ActionWordCategory(
				domainCategory: domainCategoryResponding,
				actionWordCategory: "Discusses"
			)

			def actionWordCategoryPractices = new ActionWordCategory(
				domainCategory: domainCategoryResponding,
				actionWordCategory: "Practices"
			)
			/**
			 * relating the words to the category
			 */
			domainCategoryResponding.addToActionWordCategories(actionWordCategoryParticipates)
			domainCategoryResponding.addToActionWordCategories(actionWordCategoryDiscusses)
			domainCategoryResponding.addToActionWordCategories(actionWordCategoryPractices)

			domainCategoryResponding.save()

			/**
			 * creating action words for Valuing category
			 */
			def actionWordCategoryDemonstrates = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: "Demonstrates"
			)

			def actionWordCategoryFollows = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: "Follows"
			)

			def actionWordCategoryProposes = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: "Proposes"
			)
			def actionWordCategoryInforms = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: "Informs"
			)
			/**
			 * relating the words to the category
			 */
			domainCategoryValuing.addToActionWordCategories(actionWordCategoryDemonstrates)
			domainCategoryValuing.addToActionWordCategories(actionWordCategoryFollows)
			domainCategoryValuing.addToActionWordCategories(actionWordCategoryProposes)
			domainCategoryValuing.addToActionWordCategories(actionWordCategoryInforms)
			domainCategoryValuing.save()

			/**
			 * creating action words for Organisation category
			 */
			def actionWordCategoryRecognizes = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: "Recognizes"
			)

			def actionWordCategoryExplains = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: "Explains"
			)

			def actionWordCategoryCreates = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: "Creates"
			)
			def actionWordCategoryAlters = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: "Alters"
			)
			/**
			 * relating the words to the category
			 */
			domainCategoryOrganisation.addToActionWordCategories(actionWordCategoryRecognizes)
			domainCategoryOrganisation.addToActionWordCategories(actionWordCategoryExplains)
			domainCategoryOrganisation.addToActionWordCategories(actionWordCategoryCreates)
			domainCategoryOrganisation.addToActionWordCategories(actionWordCategoryAlters)
			domainCategoryOrganisation.save()

			/**
			 * creating action words for Internalising Values category
			 */
			def actionWordCategoryCooperates = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: "Cooperates"
			)

			def actionWordCategoryDisplays = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: "Displays"
			)

			def actionWordCategoryRevises = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: "Revises"
			)
			def actionWordCategoryUses = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: "Uses"
			)
			def actionWordCategoryValues = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: "Values"
			)
			/**
			 * relating the words to the category
			 */
			domainCategoryInternalisingValues.addToActionWordCategories(actionWordCategoryCooperates)
			domainCategoryInternalisingValues.addToActionWordCategories(actionWordCategoryDisplays)
			domainCategoryInternalisingValues.addToActionWordCategories(actionWordCategoryRevises)
			domainCategoryInternalisingValues.addToActionWordCategories(actionWordCategoryUses)
			domainCategoryInternalisingValues.addToActionWordCategories(actionWordCategoryValues)
			domainCategoryInternalisingValues.save()

			/**
			 * creating action words for Perception category
			 */
			def actionWordCategoryDetects = new ActionWordCategory(
				domainCategory: domainCategoryPerception,
				actionWordCategory: "Detects"
			)

			def actionWordCategoryAdjusts = new ActionWordCategory(
				domainCategory: domainCategoryPerception,
				actionWordCategory: "Adjusts"
			)

			def actionWordCategoryEstimates = new ActionWordCategory(
				domainCategory: domainCategoryPerception,
				actionWordCategory: "Estimates"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryPerception.addToActionWordCategories(actionWordCategoryDetects)
			domainCategoryPerception.addToActionWordCategories(actionWordCategoryAdjusts)
			domainCategoryPerception.addToActionWordCategories(actionWordCategoryEstimates)

			domainCategoryPerception.save()

			/**
			 * creating action words for Set category
			 */
			def actionWordCategoryKnows = new ActionWordCategory(
				domainCategory: domainCategorySet,
				actionWordCategory: "Knows"
			)

			def actionWordCategoryRecognises = new ActionWordCategory(
				domainCategory: domainCategorySet,
				actionWordCategory: "Recognises"
			)

			def actionWordCategoryShows = new ActionWordCategory(
				domainCategory: domainCategorySet,
				actionWordCategory: "Shows"
			)

			/**
			 * relating the words to the category
			 */
			domainCategorySet.addToActionWordCategories(actionWordCategoryKnows)
			domainCategorySet.addToActionWordCategories(actionWordCategoryRecognises)
			domainCategorySet.addToActionWordCategories(actionWordCategoryShows)

			domainCategorySet.save()

			/**
			 * creating action words for GuidedResponse category
			 */
			def actionWordCategoryPerforms = new ActionWordCategory(
				domainCategory: domainCategoryGuidedResponse,
				actionWordCategory: "Performs"
			)

			def actionWordCategoryFollowing = new ActionWordCategory(
				domainCategory: domainCategoryGuidedResponse,
				actionWordCategory: "Following"
			)

			def actionWordCategoryResponds = new ActionWordCategory(
				domainCategory: domainCategoryGuidedResponse,
				actionWordCategory: "Responds"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryGuidedResponse.addToActionWordCategories(actionWordCategoryPerforms)
			domainCategoryGuidedResponse.addToActionWordCategories(actionWordCategoryFollowing)
			domainCategoryGuidedResponse.addToActionWordCategories(actionWordCategoryResponds)

			domainCategoryGuidedResponse.save()

			/**
			 * creating action words for Mechanism category
			 */
			def actionWordCategoryUsing = new ActionWordCategory(
				domainCategory: domainCategoryMechanism,
				actionWordCategory: "Using"
			)

			def actionWordCategoryRepairs = new ActionWordCategory(
				domainCategory: domainCategoryMechanism,
				actionWordCategory: "Repairs"
			)

			def actionWordCategoryDrives = new ActionWordCategory(
				domainCategory: domainCategoryMechanism,
				actionWordCategory: "Drives"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryMechanism.addToActionWordCategories(actionWordCategoryUsing)
			domainCategoryMechanism.addToActionWordCategories(actionWordCategoryRepairs)
			domainCategoryMechanism.addToActionWordCategories(actionWordCategoryDrives)

			domainCategoryMechanism.save()


			/**
			 * creating action words for ComplexOvertResponse category
			 */
			def actionWordCategoryManeuvers = new ActionWordCategory(
				domainCategory: domainCategoryComplexOvertResponse,
				actionWordCategory: "Maneuvers"
			)

			def actionWordCategoryOperates = new ActionWordCategory(
				domainCategory: domainCategoryComplexOvertResponse,
				actionWordCategory: "Operates"
			)

			def actionWordCategoryDisplaying = new ActionWordCategory(
				domainCategory: domainCategoryComplexOvertResponse,
				actionWordCategory: "Displays"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryComplexOvertResponse.addToActionWordCategories(actionWordCategoryManeuvers)
			domainCategoryComplexOvertResponse.addToActionWordCategories(actionWordCategoryOperates)
			domainCategoryComplexOvertResponse.addToActionWordCategories(actionWordCategoryDisplaying)

			domainCategoryComplexOvertResponse.save()

			/**
			 * creating action words for Adaptation category
			 */
			def actionWordCategoryResponse = new ActionWordCategory(
				domainCategory: domainCategoryAdaptation,
				actionWordCategory: "Response"
			)

			def actionWordCategoryPerforming = new ActionWordCategory(
				domainCategory: domainCategoryAdaptation,
				actionWordCategory: "Perform"
			)

			def actionWordCategoryModifies = new ActionWordCategory(
				domainCategory: domainCategoryAdaptation,
				actionWordCategory: "Modifies"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryAdaptation.addToActionWordCategories(actionWordCategoryResponse)
			domainCategoryAdaptation.addToActionWordCategories(actionWordCategoryPerforming)
			domainCategoryAdaptation.addToActionWordCategories(actionWordCategoryModifies)

			domainCategoryAdaptation.save()

			/**
			 * creating action words for Origination category
			 */
			def actionWordCategoryConstructs = new ActionWordCategory(
				domainCategory: domainCategoryOrigination,
				actionWordCategory: "Constructs"
			)

			def actionWordCategoryDevelops = new ActionWordCategory(
				domainCategory: domainCategoryOrigination,
				actionWordCategory: "Develops"
			)

			def actionWordCategoryCreating = new ActionWordCategory(
				domainCategory: domainCategoryOrigination,
				actionWordCategory: "Creating"
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryConstructs)
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryDevelops)
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryCreating)

			domainCategoryOrigination.save()


		}



if (KnowledgeDimension.count() < 1) {
			new KnowledgeDimension(
				description: "Factual",
				info: "The knowledge of terminology, details, or elements."
			).save()

			new KnowledgeDimension(
				description: "Conceptual",
				info: "The knowledge classifications, generalizations, and theories."
			).save()

			new KnowledgeDimension(
				description: "Procedural",
				info: "The knowledge of subject specific skills and techniques; “the knowledge of criteria for when to use appropriate procedures."
			).save()

			new KnowledgeDimension(
				description: "Metacognitive",
				info: "The knowledge about cognitive tasks; strategic knowledge; self-knowledge."
			).save()
		}

		if (ResourceType.count() == 0) {
			new ResourceType(
				description: "Document"
			).save()

			new ResourceType(
				description: "Book"
			).save()

			new ResourceType(
				description: "Chapter"
			).save()

			new ResourceType(
				description: "URL"
			).save()
		}

		if(PedagogyMode.count() == 0){
			new PedagogyMode(
				name:"online"
			).save()

			new PedagogyMode(
				name:"in-person"
			).save()

			new PedagogyMode(
				name:"hybrid"
			).save()
		}

		if(PedagogyReferenceType.count() == 0){
			new PedagogyReferenceType(
				description:"Book"
			).save()

			new PedagogyReferenceType(
				description:"Web"
			).save()
		}

		if(PedagogyActivityDuration.count() == 0){
			new PedagogyActivityDuration(
				duration:"Single Session"
			).save()

			new PedagogyActivityDuration(
				duration:"Multiple Session"
			).save()

			new PedagogyActivityDuration(
				duration:"Both Session"
			).save()
		}
		if(PedagogyActivityFocus.count() == 0){
			new PedagogyActivityFocus(
				focus:"Reading"
			).save()

			new PedagogyActivityFocus(
				focus:"Writing"
			).save()

			new PedagogyActivityFocus(
				focus:"Discussing"
			).save()

			new PedagogyActivityFocus(
				focus:"Presenting"
			).save()
		}

		if(PedagogyTechnique.count() == 0){
			/*Pedagogy Technique*/
			def pedagogyTech = new PedagogyTechnique(
				title:"Jigsaw",
				description: "As in a jigsaw puzzle, each piece representing each student's part is essential for the completion and full understanding of the final product. ",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList([
					'Remembering'
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
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
			).save()

			new PedagogyActivity(
				title:"jigsaw",
				description:"Small tasks are split among each student in each group. Eventually each student will come back to her or his jigsaw group and will try to present a well-organized report to the group. As a team the students organize their work and present it as a whole.",
				example:"Research on different computing languages was to be done. The students were split into five teams of four members each and each team was assigned a language. The students were asked to divide the the tasks to be researched based on the history, implementation, application of the language and comparison with other languages. Students worked individually and eventually put the wok together, then gave a presentation on each area of research.",
				material:"clicker, paper",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech
			).save()

			new PedagogyReference(
				title:"Student_Engagement_Techniques",
				author:"Elizabeth F. Barkley",
				referenceLinkISBN:"978-0-470-28191-8",
				referenceType:PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique:pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title:"Structured Problem Solving",
				description: "Provides students with a process for solving complex, content based problems within a specified amount of time. It breaks the problem into specific steps and thus students learn to identify, analyze, and solve problems in an organized way",
				domain:LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
					"Metacognitive"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title:"Step-2",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example: "The Dewey Six-Step Problem Solving Technique",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-3",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example: "The Dewey Six-Step Problem Solving Technique",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-1",
				description: "Organize students into teams and assign them a complex problem to solve",
				example: "Problem could be like evaluate the effectiveness of the antacids",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "Face-to Face/ Video / Audio  Lectures ",
				description: "Lecture",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					'Remembering',
					"Understanding"
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
					"Factual",
					"Conceptual"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "Partially Guided Programming Exercise",
				description: "Partially Guided Programming Exercise ",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
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
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "Think Aloud Pair Problem Solving",
				description: "Student pairs receive a series of problems as well as specific roles - problem solver and listener - then switch with each problem. The problem solver thinks aloud, talking through the steps of solving the problem, while the partner listens, following the steps, attempting to understand the reasoning behind the steps, and offering suggestions if there is a misstep.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					"Understanding",
					"Applying",
					"Analyzing",
					"Evaluating"
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
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
			).save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Spend time developing an appropriate set of field related problems to solve within a limited time frame",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-2",
				description: "Ask students to form pairs",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-3",
				description: "Ask students to solve problems alternating the roles with each new problem",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-4",
				description: "Call completion when all problems have been solved",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "Frames",
				description: "Instructors give students a template of sentence stem that provides the shape of a short essay but not the content. Students complete the sentence, expressing their own ideas in their own words within a clear and organized framework.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					"Analyzing",
					"Evaluating"
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
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
			).save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Choose a topic and write a brief essay that you have a skeleton of the esay",
				example: "Theory X exposes that ________ and is very useful because it offers insight into __________. ",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-2",
				description: "Make copies of the frames along with the directions to use as handouts and distribute it to each student.",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-3",
				description: "Students write the essay using the framework as a guide",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-4",
				description: "Assess the student essay based on the original essay ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "In-Class Portfolio",
				description: "Students collect and organize lecture notes, essay responses to prompts presented in class, summaries of discussions, personal reflections into a portfolio and submit for evaluation two to three times during the academic year.",
				domain: LearningDomain.findAllByNameInList(['Cognitive']),
				category: DomainCategory.findAllByNameInList(["Applying","Analyzing"]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList(["Factual", "Conceptual", "Procedural", "Metacognitive"]),
				focus: PedagogyActivityFocus.findAllByFocusInList(["Writing", "Discussing"]),
				pedagogyMode: PedagogyMode.findByName("hybrid")
			).save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Organise class sessions so that in addition to listening, students are actively integrating and applying what they learnt by writing, discussing and problem solving ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-2",
				description: "Determine portfolio parameters",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-3",
				description: "Decide how portfolio will be evaluated and determine the grading rubrics. Explain the process and expectations to students",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-4",
				description: "Assess the student essay based on the original essay ",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: "Seminar",
				description: "Students make formal presentations of an original paper to a small group of peers.",
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					"Understanding",
					"Remembering"
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
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
			).save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: "Step-1",
				description: "Assign students research papers",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-2",
				description: "Explain the time frame and tasks, and give time to discuss the paper with peers",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: "Step-3",
				description: "Students will present the paper in the class and discuss the questions raised by other students.",
				example: "",
				material: "",
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: "Student_Engagement_Techniques",
				author: "Elizabeth F. Barkley",
				referenceLinkISBN: "978-0-470-28191-8",
				referenceType: PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique: pedagogyTech
			).save()
		}
	}
	def destroy = {
	}
}
