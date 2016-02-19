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
import imod.AssessmentTechnique
import imod.Role
import imod.ScheduleRepeats
import imod.ScheduleRepeatsEvery
import imod.ScheduleWeekDays
import imod.Audience
import imod.AssessmentFeedback


class BootStrap {

	def init = { servletContext ->
		def tempRole
		def adminRole

		/**
		 * Generates the user security roles
		 */
		if (Role.list().size() == 0) {
			new Role(
				authority: 'ROLE_ANONYMOUS'
			).save()

			adminRole = new Role(
				authority: 'ROLE_ADMIN'
			).save()

			tempRole = new Role(
				authority: 'ROLE_USER'
			).save()

			def developer = new ImodUser(
				username: 'postgres',
				password: 'postgres',
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

			// Create Admin user
			def admin = new ImodUser(
				username: 'imodadmin',
				password: 'imodadmin',
				enabled: true,
				accountExpired: false,
				accountLocked: false,
				passwordExpired: false,
				email: 'contact@rahulparekh.in'
			).save()

			// Attach role to the user
			new ImodUserRole(
				imodUser: admin,
				role: adminRole
			).save()

			/*
				Help Data
			*/
			def help = [

				// These are the help information for the Course Overview tab
				// Course Overview is the 1st tab

				[
					tabFieldUiId: 'Save Course Overview',
					tabId: 'OVERVIEW',
					tabFieldId: 1,
					tabFieldName: 'Save Course Overview',
					text: 'Click on this button to save a Course overview'
				], [
					tabFieldUiId: 'Delete Course Overview',
					tabId: 'OVERVIEW',
					tabFieldId: 2,
					tabFieldName: 'Delete Course Overview',
					text: 'Click on this button to remove a Course overview'
				],[
	                tabFieldUiId: 'Schedule start Date',
	                tabId: 'OVERVIEW',
	                tabFieldId: 3,
	                tabFieldName: 'Schedule start Date',
	                text: 'Click on this button to select a Schedule start date'
	            ], [
	                tabFieldUiId: 'Schedule end Date',
	                tabId: 'OVERVIEW',
	                tabFieldId: 4,
	                tabFieldName: 'Schedule end Date',
	                text: 'Click on this button to select a Schedule end date'
	            ], [
	                tabFieldUiId: 'Add instructor',
	                tabId: 'OVERVIEW',
	                tabFieldId: 5,
	                tabFieldName: 'Add instructor',
	                text: 'Click on this button to add a Instructor'
	            ], [
	                tabFieldUiId: 'Last Name Label',
	                tabId: 'OVERVIEW',
	                tabFieldId: 6,
	                tabFieldName: 'Last Name',
	                text: 'Last Name'
	            ], [
	                tabFieldUiId: 'First Name Label',
	                tabId: 'OVERVIEW',
	                tabFieldId: 7,
	                tabFieldName: 'First Name',
	                text: 'First Name'
	            ], [
	                tabFieldUiId: 'Email Label',
	                tabId: 'OVERVIEW',
	                tabFieldId: 8,
	                tabFieldName: 'Email',
	                text: 'Email'
	            ], [
	                tabFieldUiId: 'Office Hours Label',
	                tabId: 'OVERVIEW',
	                tabFieldId: 9,
	                tabFieldName: 'Office Hours',
	                text: 'Office Hours'
	            ], [
	                tabFieldUiId: 'Web Page Label',
	                tabId: 'OVERVIEW',
	                tabFieldId: 10,
	                tabFieldName: 'Web Page',
	                text: 'Web Page'
	            ],

	            // These are the help information for the Learning Objective tab
				// Learning Objective is the 2st tab
				[
	                tabFieldUiId: 'Add Learning Objective',
	                tabId: 'LEARNINGOBJECTIVE',
	                tabFieldId: 2,
	                tabFieldName: 'Add Learning Objective',
	                text: 'Click on this button to add a Learning Objective'
	            ], [
	                tabFieldUiId: 'Learning Domain',
	                tabId: 'LEARNINGOBJECTIVE',
	                tabFieldId: 2,
	                tabFieldName: 'Learning Domain',
	                text: 'Select a Domain for student to Learn'
	            ], [
	                tabFieldUiId: 'Learning Category',
	                tabId: 'LEARNINGOBJECTIVE',
	                tabFieldId: 2,
	                tabFieldName: 'Learning Category',
	                text: 'Select a Category for student to Learn'
	            ], [
	                tabFieldUiId: 'Hide from Objective',
	                tabId: 'LEARNINGOBJECTIVE',
	                tabFieldId: 2,
	                tabFieldName: 'Hide from Objective',
	                text: 'Hides this custom condition from the Learning Objective'
	            ],

	        	// These are the help tips for the Content Tab
	            // Content is the 3rd Tab

	            [
	                tabFieldUiId: 'Add Learning Objective',
	                tabId: 'CONTENT',
	                tabFieldId: 1,
	                tabFieldName: 'Add Learning Objective',
	                text: 'Click on this button to add a Learning Objective'
	            ], [
	                tabFieldUiId: 'Remove Learning Objective',
	                tabId: 'CONTENT',
	                tabFieldId: 2,
	                tabFieldName: 'Remove Learning Objective',
	                text: 'Click on this button to remove a Learning Objective'
	            ], [
	                tabFieldUiId: 'Add Objective',
	                tabId: 'CONTENT',
	                tabFieldId: 3,
	                tabFieldName: 'Add Objective',
	                text: 'Add Objective'
	            ], [
	                tabFieldUiId: 'Topic Schedule',
	                tabId: 'CONTENT',
	                tabFieldId: 4,
	                tabFieldName: 'Topic Schedule',
	                text: 'Click on this button to add a Topic Schedule'
	            ], [
	                tabFieldUiId: 'Topic Map',
	                tabId: 'CONTENT',
	                tabFieldId: 5,
	                tabFieldName: 'Topic Map',
	                text: 'Topic Map'
	            ], [
	                tabFieldUiId: 'Add topic schedule',
	                tabId: 'CONTENT',
	                tabFieldId: 6,
	                tabFieldName: 'Add topic schedule',
	                text: 'Add topic schedule'
	            ], [
	                tabFieldUiId: 'Add Topic Form',
	                tabId: 'CONTENT',
	                tabFieldId: 7,
	                tabFieldName: 'Add Topic',
	                text: 'Add Topic'
	            ], [
	                tabFieldUiId: 'Remove Topic Widget',
	                tabId: 'CONTENT',
	                tabFieldId: 8,
	                tabFieldName: 'Remove Topic Widget',
	                text: 'Remove Topic'
	            ], [
	                tabFieldUiId: 'Add Topic',
	                tabId: 'CONTENT',
	                tabFieldId: 9,
	                tabFieldName: 'Add Topic',
	                text: 'Click on this button to add a Topic'
	            ], [
	                tabFieldUiId: 'Remove Topic',
	                tabId: 'CONTENT',
	                tabFieldId: 10,
	                tabFieldName: 'Remove Topic',
	                text: 'Click on this button to remove a Topic'
	            ], [
	                tabFieldUiId: 'Add Resource',
	                tabId: 'CONTENT',
	                tabFieldId: 11,
	                tabFieldName: 'Add Resource',
	                text: 'Add Resource'
	            ], [
	                tabFieldUiId: 'Topic Distributions',
	                tabId: 'CONTENT',
	                tabFieldId: 12,
	                tabFieldName: 'Topic Distributions',
	                text: 'Click on this button to display topic distributions'
	            ], [
	                tabFieldUiId: 'Topic Label',
	                tabId: 'CONTENT',
	                tabFieldId: 13,
	                tabFieldName: 'Topic',
	                text: 'Topic'
	            ], [
	                tabFieldUiId: 'Knowledge Dimension Label',
	                tabId: 'CONTENT',
	                tabFieldId: 14,
	                tabFieldName: 'Knowledge Dimension',
	                text: 'Knowledge Dimension'
	            ], [
	                tabFieldUiId: 'Priority Label',
	                tabId: 'CONTENT',
	                tabFieldId: 15,
	                tabFieldName: 'Priority Label',
	                text: 'Priority'
	            ], [
	                tabFieldUiId: 'Rereference Label',
	                tabId: 'CONTENT',
	                tabFieldId: 16,
	                tabFieldName: 'Rereference Label',
	                text: 'Rereference'
	            ], [
	                tabFieldUiId: 'Pre-Req Label',
	                tabId: 'CONTENT',
	                tabFieldId: 17,
	                tabFieldName: 'Pre-Req Label',
	                text: 'Pre-Req'
	            ],


	            // Help tool tips for the Pedagogy Tab
	            // Padagogy is the 5th tab
	            [
	                tabFieldUiId: 'Add New Technique',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 1,
	                tabFieldName: 'Add New Technique',
	                text: 'Click on this button to add a new technique'
	            ], [
	                tabFieldUiId: 'Clone technique',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 2,
	                tabFieldName: 'Clone technique',
	                text: 'Click on this button to clone a technique'
	            ], [
	                tabFieldUiId: 'Favorites',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 3,
	                tabFieldName: 'Favorites',
	                text: 'Favorites displays the techniques that have be made the user\'s favorites'
	            ], [
	                tabFieldUiId: 'Instructional Plan',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 4,
	                tabFieldName: 'Instructional Plan',
	                text: 'Yet to be implemented'
	            ], [
	                tabFieldUiId: 'Ideal Match',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 5,
	                tabFieldName: 'Ideal Match',
	                text: 'Ideal Match displays the techniques that exactly match the Domain, Domain Category and Knowledge Dimension of the objective selected.'
	            ], [
	                tabFieldUiId: 'Extended Match',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 6,
	                tabFieldName: 'Extended Match',
	                text: 'Extended Match displays the techniques that match the Domain, Domain Category and Knowledge Dimension of the selections made in the Filter Options'
	            ], [
	                tabFieldUiId: 'Domain',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 7,
	                tabFieldName: 'Domain',
	                text: 'Bloom\'s Taxonomy definition the domains of educational activities or learning (Cognitive, Affective and Psychomotive)'
	            ], [
	                tabFieldUiId: 'Domain Category',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 8,
	                tabFieldName: 'Domain Category',
	                text: 'The categories were expressed as verbs rather than nouns as Remembering, Understanding, Applying, Analyzing, Evaluating, and Creating'
	            ], [
	                tabFieldUiId: 'Knowledge Dimension',
	                tabId: 'PEDAGOGY',
	                tabFieldId: 9,
	                tabFieldName: 'Knowledge Dimension',
	                text: 'The Knowledge Dimension embodied both noun and verb aspects and categorized as Factual, Conceptual, Procedural and Metacognitive Knowledge '
	            ], [
	                tabFieldUiId: 'Refresh',
	                tabId: 'Refresh',
	                tabFieldId: 10,
	                tabFieldName: 'Web Page',
	                text: 'Clicking on Refresh displays the techniques that belongs the selections on the Domain, Domain Category and Knowledge Dimension in the Extended match.'
	            ]

			]

			// iterate the help list
			// and save to DB
			help.each {
				new Help(it).save()
			}

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

			def scheduleRepeats = [
				'Daily',
				'Every Weekday (Monday to Friday)',
				'Every Monday and Wednesday',
				'Every Monday, Wednesday and Friday',
				'Every Tuesday and Thursday',
				'Weekly'
			]

			scheduleRepeats.each {
				new ScheduleRepeats(
					description: it
				).save()
			}

			for (int i =1; i <= 30; i++) {
				def ScheduleRepeatsE = new ScheduleRepeatsEvery(
					description: i
				)
				ScheduleRepeatsE.save()
			}

			def scheduleDays = ['S', 'M', 'T', 'W', 'T', 'F', 'S']

			scheduleDays.each {
				new ScheduleWeekDays(
					description: it
				).save()
			}

			/**
			 * Generate Learning Domains, Domain Categories and Action Words
			 */
			def learningDomainCognitive = new LearningDomain(
				name: 'Cognitive'
			).save()

			def learningDomainAffective = new LearningDomain(
				name: 'Affective'
			).save()

			def learningDomainPsychomotor = new LearningDomain(
				name: 'Psychomotor'
			).save()

			/**
			 * This creates the domain Category instances,
			 * these are used to link Learning Domains to Action Words
			 */

			/**
			 * These are the Categories for the Cognitive Domain
			 */
			def domainCategoryRemembering = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Remember',
				priority: 1
			)

			def domainCategoryUnderstanding = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Understand',
				priority: 2
			)

			def domainCategoryApplying = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Apply',
				priority: 3
			)

			def domainCategoryAnalyzing = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Analyze',
				priority: 4
			)

			def domainCategoryEvaluating = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Evaluate',
				priority: 5

			)

			def domainCategoryCreating = new DomainCategory(
				domain: learningDomainCognitive,
				name: 'Create',
				priority: 6
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
				name: 'Receiving Phenomena',
				priority: 1
			)

			def domainCategoryResponding = new DomainCategory(
				domain: learningDomainAffective,
				name: 'Respond',
				priority: 2
			)

			def domainCategoryValuing = new DomainCategory(
				domain: learningDomainAffective,
				name: 'Value',
				priority: 3
			)

			def domainCategoryOrganisation = new DomainCategory(
				domain: learningDomainAffective,
				name: 'Organization',
				priority: 4
			)

			def domainCategoryInternalisingValues = new DomainCategory(
				domain: learningDomainAffective,
				name: 'Internalizing Values',
				priority: 5
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
				name: 'Perception',
				priority: 1
			)

			def domainCategorySet = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Set',
				priority: 2
			)

			def domainCategoryGuidedResponse = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Guided Response',
				priority: 3
			)

			def domainCategoryMechanism = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Mechanism',
				priority: 4
			)

			def domainCategoryComplexOvertResponse = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Complex Overt Response',
				priority: 5
			)

			def domainCategoryAdaptation = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Adaptation',
				priority: 6
			)

			def domainCategoryOrigination = new DomainCategory(
				domain: learningDomainPsychomotor,
				name: 'Origination',
				priority: 7
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
				actionWordCategory: 'Recognize'
			)

			def actionWordCategoryRecalling = new ActionWordCategory(
				domainCategory: domainCategoryRemembering,
				actionWordCategory: 'Recall'
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
				actionWordCategory: 'Interpret'
			)

			def actionWordCategoryExemplifying = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Exemplify'
			)

			def actionWordCategoryClassifying = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Classify'
			)

			def actionWordCategorySummarizing = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Summarize'
			)

			def actionWordCategoryInferring = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Infer'
			)

			def actionWordCategoryComparing = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Compare'
			)

			def actionWordCategoryExplaining = new ActionWordCategory(
				domainCategory: domainCategoryUnderstanding,
				actionWordCategory: 'Explain'
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
				actionWordCategory: 'Execute'
			)

			def actionWordCategoryImplementing = new ActionWordCategory(
				domainCategory: domainCategoryApplying,
				actionWordCategory: 'Implement'
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
				actionWordCategory: 'Differentiate'
			)

			def actionWordCategoryOrganizing = new ActionWordCategory(
				domainCategory: domainCategoryAnalyzing,
				actionWordCategory: 'Organize'
			)

			def actionWordCategoryAttributing = new ActionWordCategory(
				domainCategory: domainCategoryAnalyzing,
				actionWordCategory: 'Attribute'
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
				actionWordCategory: 'Check'
			)

			def actionWordCategoryCritiquing = new ActionWordCategory(
				domainCategory: domainCategoryEvaluating,
				actionWordCategory: 'Critique'
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
				actionWordCategory: 'Generate'
			)

			def actionWordCategoryPlanning = new ActionWordCategory(
				domainCategory: domainCategoryCreating,
				actionWordCategory: 'Plan'
			)

			def actionWordCategoryProducing = new ActionWordCategory(
				domainCategory: domainCategoryCreating,
				actionWordCategory: 'Produce'
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
				actionWordCategory: 'Listens'
			)

			def actionWordCategoryRemembers = new ActionWordCategory(
				domainCategory: domainCategoryReceivingPhenomena,
				actionWordCategory: 'Remembers'
			)

			def actionWordCategoryNames = new ActionWordCategory(
				domainCategory: domainCategoryReceivingPhenomena,
				actionWordCategory: 'Names'
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
				actionWordCategory: 'Participates'
			)

			def actionWordCategoryDiscusses = new ActionWordCategory(
				domainCategory: domainCategoryResponding,
				actionWordCategory: 'Discusses'
			)

			def actionWordCategoryPractices = new ActionWordCategory(
				domainCategory: domainCategoryResponding,
				actionWordCategory: 'Practices'
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
				actionWordCategory: 'Demonstrates'
			)

			def actionWordCategoryFollows = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: 'Follows'
			)

			def actionWordCategoryProposes = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: 'Proposes'
			)
			def actionWordCategoryInforms = new ActionWordCategory(
				domainCategory: domainCategoryValuing,
				actionWordCategory: 'Informs'
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
				actionWordCategory: 'Recognizes'
			)

			def actionWordCategoryExplains = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: 'Explains'
			)

			def actionWordCategoryCreates = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: 'Creates'
			)
			def actionWordCategoryAlters = new ActionWordCategory(
				domainCategory: domainCategoryOrganisation,
				actionWordCategory: 'Alters'
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
				actionWordCategory: 'Cooperates'
			)

			def actionWordCategoryDisplays = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: 'Displays'
			)

			def actionWordCategoryRevises = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: 'Revises'
			)
			def actionWordCategoryUses = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: 'Uses'
			)
			def actionWordCategoryValues = new ActionWordCategory(
				domainCategory: domainCategoryInternalisingValues,
				actionWordCategory: 'Values'
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
				actionWordCategory: 'Detects'
			)

			def actionWordCategoryAdjusts = new ActionWordCategory(
				domainCategory: domainCategoryPerception,
				actionWordCategory: 'Adjusts'
			)

			def actionWordCategoryEstimates = new ActionWordCategory(
				domainCategory: domainCategoryPerception,
				actionWordCategory: 'Estimates'
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
				actionWordCategory: 'Knows'
			)

			def actionWordCategoryRecognises = new ActionWordCategory(
				domainCategory: domainCategorySet,
				actionWordCategory: 'Recognises'
			)

			def actionWordCategoryShows = new ActionWordCategory(
				domainCategory: domainCategorySet,
				actionWordCategory: 'Shows'
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
				actionWordCategory: 'Performs'
			)

			def actionWordCategoryFollowing = new ActionWordCategory(
				domainCategory: domainCategoryGuidedResponse,
				actionWordCategory: 'Following'
			)

			def actionWordCategoryResponds = new ActionWordCategory(
				domainCategory: domainCategoryGuidedResponse,
				actionWordCategory: 'Responds'
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
				actionWordCategory: 'Using'
			)

			def actionWordCategoryRepairs = new ActionWordCategory(
				domainCategory: domainCategoryMechanism,
				actionWordCategory: 'Repairs'
			)

			def actionWordCategoryDrives = new ActionWordCategory(
				domainCategory: domainCategoryMechanism,
				actionWordCategory: 'Drives'
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
				actionWordCategory: 'Maneuvers'
			)

			def actionWordCategoryOperates = new ActionWordCategory(
				domainCategory: domainCategoryComplexOvertResponse,
				actionWordCategory: 'Operates'
			)

			def actionWordCategoryDisplaying = new ActionWordCategory(
				domainCategory: domainCategoryComplexOvertResponse,
				actionWordCategory: 'Displays'
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
				actionWordCategory: 'Response'
			)

			def actionWordCategoryPerforming = new ActionWordCategory(
				domainCategory: domainCategoryAdaptation,
				actionWordCategory: 'Perform'
			)

			def actionWordCategoryModifies = new ActionWordCategory(
				domainCategory: domainCategoryAdaptation,
				actionWordCategory: 'Modifies'
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
				actionWordCategory: 'Constructs'
			)

			def actionWordCategoryDevelops = new ActionWordCategory(
				domainCategory: domainCategoryOrigination,
				actionWordCategory: 'Develops'
			)

			def actionWordCategoryCreating = new ActionWordCategory(
				domainCategory: domainCategoryOrigination,
				actionWordCategory: 'Creating'
			)

			/**
			 * relating the words to the category
			 */
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryConstructs)
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryDevelops)
			domainCategoryOrigination.addToActionWordCategories(actionWordCategoryCreating)

			domainCategoryOrigination.save()

			def knowledgeDimensions = [
				[
	                description: 'Factual',
	                info: 'The knowledge of terminology, details, or elements.'
	            ], [
	                description: 'Conceptual',
	                info: 'The knowledge classifications, generalizations, and theories.'
	            ], [
	                description: 'Procedural',
	                info: 'The knowledge of subject specific skills and techniques;the knowledge of criteria for when to use appropriate procedures'
	            ], [
	                description: 'Metacognitive',
	                info: 'The knowledge about cognitive tasks; strategic knowledge and self-knowledge.'
	            ]
			]

			knowledgeDimensions.each {
				new KnowledgeDimension(it).save()
			}

			new ResourceType(
				description: 'Document'
			).save()

			new ResourceType(
				description: 'Book'
			).save()

			new ResourceType(
				description: 'Chapter'
			).save()

			new ResourceType(
				description: 'URL'
			).save()

			new AssessmentFeedback(
				name: 'In Person'
			).save()

			new AssessmentFeedback(
				name: 'Online'
			).save()

			new AssessmentFeedback(
				name: 'Both'
			).save()

			new PedagogyMode(
				name: 'online'
			).save()

			new PedagogyMode(
				name: 'in-person'
			).save()

			new PedagogyMode(
				name: 'hybrid'
			).save()

			new PedagogyReferenceType(
				description: 'Book'
			).save()

			new PedagogyReferenceType(
				description: 'Web'
			).save()

			new PedagogyActivityDuration(
				duration: 'Single Session'
			).save()

			new PedagogyActivityDuration(
				duration: 'Multiple Session'
			).save()

			new PedagogyActivityDuration(
				duration: 'Both Session'
			).save()

			new PedagogyActivityFocus(
				focus: 'Reading'
			).save()

			new PedagogyActivityFocus(
				focus: 'Writing'
			).save()

			new PedagogyActivityFocus(
				focus: 'Discussing'
			).save()

			new PedagogyActivityFocus(
				focus: 'Presenting'
			).save()

			/*Pedagogy Technique*/
			def pedagogyTech = new PedagogyTechnique(
				title: 'Jigsaw',
				description: 'As in a jigsaw puzzle, each piece representing each student\'s part is essential for the completion and full understanding of the final product.',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList([
					'Remember'
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
					'Factual',
					'Conceptual',
					'Procedural',
					'Metacognitive'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Reading',
					'Writing',
					'Discussing',
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('in-person'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()


			new PedagogyActivity(
				title: 'jigsaw',
				description: 'Small tasks are split among each student in each group. Eventually each student will come back to her or his jigsaw group and will try to present a well-organized report to the group. As a team the students organize their work and present it as a whole.',
				example: 'Research on different computing languages was to be done. The students were split into five teams of four members each and each team was assigned a language. The students were asked to divide the the tasks to be researched based on the history, implementation, application of the language and comparison with other languages. Students worked individually and eventually put the wok together, then gave a presentation on each area of research.',
				material: 'clicker, paper',
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique:pedagogyTech
			).save()

			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType:PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique:pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Structured Problem Solving',
				description: 'Provides students with a process for solving complex, content based problems within a specified amount of time. It breaks the problem into specific steps and thus students learn to identify, analyze, and solve problems in an organized way',
				domain:LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					'Understanding',
					'Applying',
					'Analyzing',
					'Evaluating'
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
					'Metacognitive'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Discussing',
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: 'Step-2',
				description: 'ask students to solve the problem using specific steps you have identified as a problem solving technique',
				example: 'The Dewey Six-Step Problem Solving Technique',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-3',
				description: 'ask students to solve the problem using specific steps you have identified as a problem solving technique',
				example: 'The Dewey Six-Step Problem Solving Technique',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-1',
				description: 'Organize students into teams and assign them a complex problem to solve',
				example: 'Problem could be like evaluate the effectiveness of the antacids',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType: PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Face-to Face/ Video / Audio  Lectures ',
				description: 'Lecture',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					'Remember',
					'Understanding'
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
					'Factual',
					'Conceptual'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Partially Guided Programming Exercise',
				description: 'Partially Guided Programming Exercise ',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),//'Cognitive','Affective', 'Psychomotor'
				category: DomainCategory.findAllByNameInList([
					'Understanding',
					'Applying',
					'Analyzing',
					'Evaluating'
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
					'Conceptual',
					'Procedural'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Reading',
					'Writing',
					'Discussing',
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('in-person'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Think Aloud Pair Problem Solving',
				description: 'Student pairs receive a series of problems as well as specific roles - problem solver and listener - then switch with each problem. The problem solver thinks aloud, talking through the steps of solving the problem, while the partner listens, following the steps, attempting to understand the reasoning behind the steps, and offering suggestions if there is a misstep.',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					'Understanding',
					'Applying',
					'Analyzing',
					'Evaluating'
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
					'Factual',
					'Conceptual',
					'Procedural',
					'Metacognitive'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Discussing',
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: 'Step-1',
				description: 'Spend time developing an appropriate set of field related problems to solve within a limited time frame',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-2',
				description: 'Ask students to form pairs',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-3',
				description: 'Ask students to solve problems alternating the roles with each new problem',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-4',
				description: 'Call completion when all problems have been solved',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType: PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Frames',
				description: 'Instructors give students a template of sentence stem that provides the shape of a short essay but not the content. Students complete the sentence, expressing their own ideas in their own words within a clear and organized framework.',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category: DomainCategory.findAllByNameInList([
					'Analyzing',
					'Evaluating'
				]),
				knowledge: KnowledgeDimension.findAllByDescriptionInList([
					'Factual',
					'Conceptual',
					'Procedural',
					'Metacognitive'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Reading',
					'Writing'
				]),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: 'Step-1',
				description: 'Choose a topic and write a brief essay that you have a skeleton of the esay',
				example: 'Theory X exposes that ________ and is very useful because it offers insight into __________. ',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-2',
				description: 'Make copies of the frames along with the directions to use as handouts and distribute it to each student.',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-3',
				description: 'Students write the essay using the framework as a guide',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-4',
				description: 'Assess the student essay based on the original essay ',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()


			// All the assessment techniques
			def assessments = [
				[
	                title: 'Minute Papers',
	                learningDomain: LearningDomain.findAllByNameInList([
	                    'Cognitive'
	                ]),//'Cognitive','Affective', 'Psychomotor'
	                domainCategory: DomainCategory.findAllByNameInList([
	                    'Remember'
	                ]),
	                knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList([
	                    'Factual',
	                    'Conceptual',
	                    'Procedural',
	                    'Metacognitive'
	                ]),
	                assessmentFeedback: AssessmentFeedback.findByName('Online'),
	                isAdmin: true
	            ],
	            [
				    title: 'Background Knowledge Probe',
				    description: 'This activity goes beyond the common practice of asking students what courses they have already taken in the field. Using a survey, the instructor elicits information that can be used to focus instruction on appropriate content and level of difficulty.',
				    procedure: '',
				    duration: '15',
				    difficulty: 'Low',
				    whenToCarryOut: 'Pre',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList([['Cognitive']]),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList([['Conceptual', 'Factual']]),
				    domainCategory: DomainCategory.findAllByNameInList([['Remember', 'Understand']]),
				    isAdmin: true
				],


				[
				    title: 'Student-generated Test questions',
				    description: 'By having students write test questions and compose answers, faculty discover what students identify as key content, what they consider reasonable test questions and how well they can answer the questions they create. Instructors pre-determine the types of questions (essay, multiple choice, shortanswer, etc.) and the topics to be addressed. The questions can then be compiled for a study guide or, as an added incentive, chosen (if suitable) to appear on the actual test.',
				    procedure: '',
				    duration: '15',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList([['Cognitive']]),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList([['Conceptual', 'Factual']]),
				    domainCategory: DomainCategory.findAllByNameInList([['Remember', 'Understand', 'Apply']]),
				    isAdmin: true
				],

				[
				    title: 'Minute paper',
				    description: 'This technique helps ascertain what students felt was the most important information they learned during a particular class meeting and if they have any lingering questions about the content. Answers to these questions help faculty focus instruction, make mid-course corrections or identify areas that need more emphasis.',
				    procedure: '',
				    duration: '1',
				    difficulty: 'Low',
				    whenToCarryOut: 'Pre',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual','Factual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Remember', 'Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Muddiest point',
				    description: 'This very simple technique identifies areas of confusion from a lecture, discussion, homework or other activity. When students write out the answer to the question,  What was the muddiest point in _______? Students not only must reflect on the content material but also articulate their thoughts. This CAT works well when large amounts of information has been presented.',
				    procedure: '',
				    duration: '5',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive', 'Conceptual', 'Factual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Remember', 'Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Capstone project',
				    description: 'This is a simple technique in which students apply the concepts they have learnt on a project.',
				    procedure: '',
				    duration: '',
				    difficulty: 'High',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Practicuum or internship',
				    description: 'The purpose of the practicum or internship is to provide students with supervised experience in an actual work setting similar to one in which the student might end up after graduating. Assessment generally involves analysis of work performance by the student’s supervisor, grading of a formal report by the faculty member, and a self-analysis written by the student.',
				    procedure: '',
				    duration: '',
				    difficulty: 'High',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Midterm test',
				    description: 'The purpose of the Midterm test is to assess how much the students have learnt and are able to apply in answering questions related to the coursework.',
				    procedure: '',
				    duration: '120 min',
				    difficulty: 'High',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply', 'Remember']),
				    isAdmin: true
				],

				[
				    title: 'Final Exam',
				    description: 'The purpose of the Final Examis to assess how much the students have learnt and are able to apply in answering questions related to the coursework.',
				    procedure: '',
				    duration: '120 min',
				    difficulty: 'High',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply', 'Remember']),
				    isAdmin: true
				],

				[
				    title: 'Final Project',
				    description: 'This is a simple technique in which students apply the concepts they have learnt on a project.',
				    procedure: '',
				    duration: '',
				    difficulty: 'High',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Focused Listing',
				    description: 'This technique focuses on the students attention on a single important term, name or  concept from a particular lesson or class session and directs them to list several ideas that are closely related to that focus point. It can help the faculty assess how well the students can describe or define a central point in the lesson.',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Memory Matrix',
				    description: 'In a memory matrix the row and column headings are given but the cells the boces within are left empty.When the students fill in these boxes the feedback can be easily scanned and analysed.This technique assesses students recall of important course content and their skill at organizing that information into categories provided by the instructor',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Content,Form,and Function Outlines',
				    description: 'This assessment technique is called the" what how and why "outlines.The student writes brief notes answering the "what ,how and why " question in an outline format that can be quickly read and assessed.This technique is used to assess the students ability at separating and analyzing informational content.',
				    procedure: '',
				    duration: '20 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual', 'Procedural', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],


				[
				    title: 'Misconception/Preconception Check',
				    description: 'This technique assesses the students prior knowledge by focusing on uncovering prior knowledge that may hinder or block further learning.This technique is designed to uncover specific instances of incorrect or incomplete knowledge,attitudes, values that represent lilely barriers to new learning.',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Perception']),
				    isAdmin: true
				],

				[
				    title: 'Empty Outlines',
				    description: 'The instructor provides students with an empty or partially completed outline of an in class presentation or homework assignment and gives them limited amount of time to fill in the blank spaces.It helps faculty fi nd out how well the students have caught the important points of a lecture.',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Factual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Perception', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Categorizing Grid',
				    description: 'This technique  is the paper amnd pencil equivalent of sorting objects in a warehouse and putting the like ones together in a bin.This feedback allows the teacher to determine quickly whether ,how and how well students understand "what goes with what".  ',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive', 'Affective']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Mechanism', 'Adaptation', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Defining Features Matrix',
				    description: 'The defining matrix requires student to categorize concpts according to the presence (+) or absence (-) of important defining features thereby providing data on their analtyic and thinking abilities. ',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Analytic Memos',
				    description: 'This technique requires the student to write a one to two page analysis of a specific problem or issue.It helps to assess the students ability to communicate the analysis in a clear and concise manner.',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Summative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Word Journal',
				    description: 'The word journal prompts a two part response .First the student summarises a short text in a single word. Second, the student writes a paragraph explaining why he or she chose that particular word to summarize the text.It assesses the students ability to read carefully and creaticely summarize it in a single word',
				    procedure: '',
				    duration: '10 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),

				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Factual']),

				    domainCategory: DomainCategory.findAllByNameInList(['Understand']),

				    domainCategory: DomainCategory.findAllByNameInList(['Apply']),
				    isAdmin: true
				],

				[
				    title: 'Concept Maps',
				    description: 'Concept maps are drawings or diagrams showing the mental connections that students make between a major concept the instructor focuses on and other concepts they have learned.This technique assesses the patterns of associations they make in relation to a focal concept',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Problem Recognition Tasks',
				    description: 'Problem Recognition Tasks present students with a few examples of common problem types. The students task is to recognize and identify particular type of problem each  example represents.This practice helps students develop a diagnostic skill.',
				    procedure: '',
				    duration: '10 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],


				[
				    title: 'Human Tableau or Class Modeling',
				    description: 'Group of students create "living" scenes or model processes to show what they know. Just as some students learen more effectively by listening,others learn more effectively by movement.This technique works well for kinesthetic learners. ',
				    procedure: '',
				    duration: '15 minutes',
				    difficulty: 'Medium',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive', 'Affective', 'Psychomotor']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Classroom opinion polls',
				    description: 'Many faculty already use de facto opinion polling in their classes when they ask students to raise their hands to indicate agreement  or disagreement with a particular statement.',
				    procedure: '',
				    duration: '10 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Interest/Knowledge/Skills Checklist',
				    description: 'Teachers create checklists of topics covered in their courses and skills  strengthened  by or required for succeeding in those courses.Students rate their interest in the various topics and assess their levels of skill or knowledge in those topics, by indicating the appropriate responses on the checklist. ',
				    procedure: '',
				    duration: '10 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Goal Ranking and Matching',
				    description: 'Goal Ranking and Matching is a simple procedure that many faculty have adapted to use in first or second day of class. It takes only a few minutes for students to list a few learning they hope to achieve through the course and to rank the relative importance of those goals.',
				    procedure: '',
				    duration: '30 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Pre',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Punctuated Lectures',
				    description: 'This technique requires students and teachers to go through five steps: listen, stop, reflect, write, and give feedback.After a part of the lecture or demonstration the students reflect on what they were doing the presentation and how it aided or hindered their understanding of the information',
				    procedure: '',
				    duration: '5 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Process Analysis',
				    description: 'Process analysis Requires that students keep records of actual steps taken while carrying out a specific assignment and conclusions they draw about their approaches to assignment',
				    procedure: '',
				    duration: '10 minutes',
				    difficulty: 'Low',
				    whenToCarryOut: 'Post',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				],

				[
				    title: 'Diagnostic Learning Logs',
				    description: 'In Diagnostic learning logs are essentially limited, tightly  focused versions of the academic journals many teachers already use.In these logs, students keep records of each class or assignment.',
				    procedure: '',
				    duration: '',
				    difficulty: 'Low',
				    whenToCarryOut: 'Mid',
				    reference: 'Thomas A. Angelo and K.Patricia Cross. “Classroom Assessment Techniques“, 2nd ed. James Rhem. California: Jossey-Bass.',
				    type: 'Formative',
				    assessmentFeedback: AssessmentFeedback.findByName('In Person'),
				    learningDomain: LearningDomain.findAllByNameInList(['Cognitive']),
				    knowledgeDimension: KnowledgeDimension.findAllByDescriptionInList(['Conceptual', 'Metacognitive']),
				    domainCategory: DomainCategory.findAllByNameInList(['Understand', 'Apply']),
				    isAdmin: true
				]

			]

			// looping through assessments and save them one by one
			def techAssess
			assessments.each {
				techAssess = new AssessmentTechnique()
				techAssess.title = it.title
				techAssess.description = it.description
				techAssess.procedure = it.procedure
				techAssess.duration= it.duration
				techAssess.difficulty = it.low
				techAssess.whenToCarryOut = it.whenToCarryOut
				techAssess.reference = it.reference
				techAssess.type = it.type
				techAssess.assessmentFeedback = it.assessmentFeedback
				it.domainCategory.each { dc ->
					techAssess.addToDomainCategory(
						dc
					)
				}
				it.learningDomain.each { ld ->
					techAssess.addToLearningDomain(
						ld
					)
				}
				it.knowledgeDimension.each { kd ->
					techAssess.addToKnowledgeDimension(
						kd
					)
				}
				techAssess.save()
				admin.addToAssessmentTechnique(techAssess).save()
			}


			/*Pedagogy Reference*/
			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType: PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique: pedagogyTech
			).save()
			def newTechnique
			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Talking Chips'

			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 117-121.'

			newTechnique.activityDescription = 'Is a tehnnique in which students participate in a group discussion and surrender a token each time they speak.It is particularly usful for ensuring equitable participation.'

			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Critical Debate'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'materials'
			newTechnique.activityDescription = 'Is a technique in which students assume and argue the side of an issue that is in opposition to their pesonal views. It is particularly useful for developing critical thinking skills and encouraging students to challenge their existing assumptions.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Discussing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Round table'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 241-246.'
			newTechnique.activityDescription = 'Is a technique in which students genetate ideas and speak in order moving from one student to the next. It is particularly useful for structuring brainstroming sessions and ensuring that all students participate.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Discussing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Digital 3D Scanning'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Nitish Vasudevan and Conrad S. Tucker, "Digital representation of physical artifacts: The effect of low cost, high accuracy 3D scanning technologies on engineering education, student learning and design evaluation," in ASME 2013 International Design Engineering Technical Conferences and Computers and Information in Engineering Conference. Volume 1: 15th International Conference on Advanced Vehicle Technologies; 10th International Conference on Design Education; 7th International Conference on Micro- and Nanosystems, Portland, Oregon, USA, 2013, pp. V001T04A013. DOI: 10.1115/DETC2013-12651'
			newTechnique.activityDescription = 'Is a technique in which students study the output of 3D scanners to bridge their gap in understanding the trannsformation of real world models to digital models.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Designettes Technique'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Cassandra Telenko, Bradley Camburn, Katja Hölttä-Otto, Kristin Wood and Kevin Otto, "Designettes: New approaches to multidisciplinary engineering design education," in ASME 2014 International Design Engineering Technical Conferences and Computers and Information in Engineering Conference. Volume 3: 16th International Conference on Advanced Vehicle Technologies; 11th International Conference on Design Education; 7th Frontiers in Biomedical Devices, Buffalo, New York, USA, 2014, pp. V003T04A003. DOI: 10.1115/DETC2014-35137'
			newTechnique.activityDescription = 'Is a technique in which instructors use brief evocative descriptions, accounts or episodes to teach engineering design thinking based on short term experiene.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Game Learning Techniques'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'O. Cicekci, M. Turkeri and O. Pekcan, "Development of soil profile visualization software using game engines," in Geo-Congress 2014 Technical Papers: Geo-Characterization and Modeling for Sustainability February 23-26, 2014, Atlanta, Georgia, 2003, pp. 3364-3372. DOI: 10.1061/9780784413272.327'
			newTechnique.activityDescription = 'Is a technique in which students are asked to play a game.Playing the game helps them learn some of the concepts illustrated for a particular topic.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Psychomotor'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Mobile based Game LEarning Technique'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'O. Cicekci, M. Turkeri and O. Pekcan, "Development of soil profile visualization software using game engines," in Geo-Congress 2014 Technical Papers: Geo-Characterization and Modeling for Sustainability February 23-26, 2014, Atlanta, Georgia, 2003, pp. 3364-3372. DOI: 10.1061/9780784413272.327'
			newTechnique.activityDescription = 'Is a technique in which students are asked to play a game on their mobile phones.Playing the game helps them learn some of the concepts illustrated for a particular topic.The portability of the mobile makes the learning software available all throughout'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Psychomotor'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Asynchronous Online Discussion forums'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Project-based learning and student knowledge construction during asynchronous Joyce Hwee Ling Koh a, Susan C. Herring b, Khe Foon Hew a'
			newTechnique.activityDescription = 'Is a technique in which students are asked to be a part of online discussion forum to enhance each others understanding of the concepts through one another'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Text Mining on Student Diaries'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Qualitative Text Mining in Student’s Service Learning Diary Hsu Chia-Ling,Chang ya-Fung'
			newTechnique.activityDescription = 'Is a technique in which students are asked to be a part of online discussion forum to enhance each others understanding of the concepts through one another'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Project Based Mobile Learning'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'CONCEPTIONS OF PROJECT-BASED MOBILE LEARNING AMONG COLLEGE STUDENTS Chiung-Sui Chang and Ya-Ping Huang'
			newTechnique.activityDescription = 'Is a technique in which students complete a part or the entire project using handheld portable mobile devices.Mobile devices could also be used to keep track of progress in the project'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Flow chart based Concept learning'
			newTechnique.materials = 'materials'
			newTechnique.reference = ''
			newTechnique.activityDescription = 'Is a technique in which flow charts can be used by an instructor to elaborate and teach a particular concept in which the flow/logic is central to the concept'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Step Based Learning Software'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'CONCEPTIONS OF PROJECT-BASED MOBILE LEARNING AMONG COLLEGE STUDENTS Chiung-Sui Chang and Ya-Ping Huang'
			newTechnique.activityDescription = 'Is a technique in which a step based process can be taught to students by making them solve problems step by step using software.The regular use of software helps them understand and be confident of the process of problem solving'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Hybrid Learning Technique'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'R. Grace, "Hybrid Course Linking Natural Hazards with Probability and Statistics," J. Prof. Issues Eng. Educ. Pract., vol. 132, pp. 217-223, 07/01; 2015/08, 2006. DOI: 10.1061/(ASCE)1052-3928(2006)132:3(217)'
			newTechnique.activityDescription = 'Two different topics can be combined and taught as one so as to understand the relationship between the topics and the concepts associated with them'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Classroom Demo Technique'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'R. Vander Schaaf and J. Klosky, "Classroom Demonstrations in Introductory Mechanics," J. Prof. Issues Eng. Educ. Pract., vol. 131, pp. 83-89, 04/01; 2015/08, 2005. DOI: 10.1061/(ASCE)1052-3928(2005)131:2(83)'
			newTechnique.activityDescription = 'Is a technique in which a classroom demo using equipment or objects related to the topic helps students understand the concept '
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Lecture'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'reference'
			newTechnique.activityDescription = 'Is a technique in which the instructor explains, illustrates and gives examples so that students better understand a concept or topic'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Flipped Charts'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Neal H. Kasamoto, "Using Workshop-Training Techniques in the Engineering Classroom," J. Prof. Issues Eng. Educ. Pract., vol. 127, pp. 41-44, 04/01; 2015/08, 2001. DOI: 10.1061/(ASCE)1052-3928(2001)127:2(41)'
			newTechnique.activityDescription = 'Flip charts can highlight key points, equations, or concepts more effectively than computer-generated text slides, chalkboards,white boards, and view graphs. Once a chalkboard is erased or slides are changed the concept persists in the chart'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'After Action Reviews'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Neal H. Kasamoto, "Using Workshop-Training Techniques in the Engineering Classroom," J. Prof. Issues Eng. Educ. Pract., vol. 127, pp. 41-44, 04/01; 2015/08, 2001. DOI: 10.1061/(ASCE)1052-3928(2001)127:2(41)'
			newTechnique.activityDescription = 'Is a technique in which after the topic is taught students can give feedback to the instructor so as to improve the entire process'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Seating Arrangements'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Neal H. Kasamoto, "Using Workshop-Training Techniques in the Engineering Classroom," J. Prof. Issues Eng. Educ. Pract., vol. 127, pp. 41-44, 04/01; 2015/08, 2001. DOI: 10.1061/(ASCE)1052-3928(2001)127:2(41)'
			newTechnique.activityDescription = 'Is a technique which seating arrangement improves the learnability of a particular topic'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Case Study'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 182-188.'
			newTechnique.activityDescription = 'Is a technique in which students review a written study of a real-world  scenario and develop a solution to the dilemma pesented in the case.  It is particulaly useful for presenting abstract principles and theories in ways that students find relevent.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Partially guided programming exercise'
			newTechnique.materials = 'materials'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Mutiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Think aloud pair problem solving'
			newTechnique.materials = 'materials'
			newTechnique.activityDescription = 'Is a Technique in which students solve problems aloud to try out their reasoning on a listening peer.  It is particularly useful for emphasizing the problem-solving'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Classify'
			newTechnique.materials = 'materials'
			newTechnique.activityDescription = 'Is a technique in which students are given artifacts of sub categories in a classification system and they have to sort the items or group similar artifacts'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Jigsaw'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. Collaborative Learning Techniques, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a technique in which students develop knowledge about a given topic and then teach it to others.It  useful for motivating students to process information and  teach it to their peers.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Structured Problem Solving'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. "Collaborative Learning Techniques", 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 188-193.'
			newTechnique.activityDescription = 'Is a technique in which students follow a structured format to solve problems. Dividing problem- solving processes into manageable steps so that students dont feel overwhelmed'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Frames'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. "Collaborative Learning Techniques", 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 188-193.'
			newTechnique.activityDescription = 'Is a technique in which students are given a template using which they complete the sentences using their own ideas'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()






			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Three step interview'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 121-126'
			newTechnique.activityDescription = 'Is a technique in which students interview each other and report what they learn to another pair.  It is particularly useful for helping students network and improve communication skills.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Multiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()



			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Analytic Teams'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 193-199'
			newTechnique.activityDescription = 'Is a technique in which students assume roles and specific tasks to perform when critically reading an assignment, listening to a lecture or watching a video.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()



			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Send a Problem'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 177-182.'
			newTechnique.activityDescription = 'Is a technique in which students try to solve a problem as a group, and then pass the problem and solution to a nearby group who does the same; the final group evaluates the solutions.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Multiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()



			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Collaborative Writing'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 236-241'
			newTechnique.activityDescription = 'Is a technique in whih students reord their thoughts in a jounal that they exhange with peers for comments and questions. It is partiularly useful for connecting coursework  to students personal lives '
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()





			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Fishbowl'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 117-121.'
			newTechnique.activityDescription = 'Is a technique in which students form concentric circles with the smaller,inside group of students disussing and the larger,outside group listening and observing.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Artifacts'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. Collaborative Learning Techniques, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a technique in which instructors provide groups of students with photos,charts,drawings or objects which represent key ideas about a topic'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Stations'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. Collaborative Learning Techniques, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a technique in which students move around the classroom and interact with learning material and examine,question and exchange ideas with peers to learn more.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Insights-Resources-Application'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. Collaborative Learning Techniques, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a technique in which students in conjunction with a reading ,complete a written assignment which includes insights, resources and applications related to the Reading Assignment'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'WebQuests'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. Collaborative Learning Techniques, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a technique in which students investigate an open ended question using specific websites provided by the instructor over the internet'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()



			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Role Play'

			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 156-163.'
			newTechnique.activityDescription = 'Is a tehnique in which students assume a different identity and act out a scenario. It is particularly useful for engaging students in a creative activity that helps them "learn by doing".'
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Psychomotor'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Affective'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Test Taking Teams'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 163-169.'
			newTechnique.activityDescription = 'Is a technique in which students prepare or a test in working groups, take the test individually, and then retake the tests in their groups. It is particularly useful for helping students assess and improve their undestanding of subject matter'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Buzz Groups'

			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 112-117.'
			newTechnique.activityDescription = 'Is a technique in which students discuss course-related questions informally in small groups of peers.It is particularly useful for generating lots of information and ideas in a short period of time to prepare for and improve whole-class discussions.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Multiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Learning cell'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 140-145.'
			newTechnique.activityDescription = 'Is a tehnique in which students quiz each other using questions they have developed individually about a reading assignment or other learning activity. It is  useful for engaging students actively in thinking about content'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Multiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Group Investigation'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 199-205.'
			newTechnique.activityDescription = 'Is a technique in which students plan,conduct, and report on in-depth research projects. It is particularly useful for teaching students researh procedures and helping them to gain in-depth knowledge about a specifi area.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Affinity Grouping'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 207-211.'
			newTechnique.activityDescription = 'Is a technique in which students  generate ideas, identify common themes, and then sort and organize the ideas accordingly. It is partiularly useful for helping students "unpack" a complicated topic and identify and classify its constituent parts.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Multiple Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))

			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Group Grid'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 211-216.'
			newTechnique.activityDescription = 'Is a technique in which students are given pieces of information and asked to place them in the blank cells of a grid according to category rubrics.  It is particularly useful for clarifying conceptual categories and developing sorting skills.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Team Matrix'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 216-221.'
			newTechnique.activityDescription = 'Is a technique in which students  discriminate between similar concepts by noticing and marking on a chart the presence or absence of important defining features.It is particularly useful for distinguishing among closely related concepts.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Sequence Chains'

			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 221-226.'
			newTechnique.activityDescription = 'Is a technque in which students analyze and depict graphically a series of events,actions ,roles. It is useful for understanding the processes,cause and effect,and chronologial series, and organizing information in an orderly,coherent progression.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))
			newTechnique.addToLearningDomain(LearningDomain.findByName('Cognitive'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'World Webs'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 226-233.'
			newTechnique.activityDescription = 'Is a technique in which students generate a list of related ideas and then organize them ina graphic, identifying relationships by drawing lines or arrows to represent the connections.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Dyadic Essays'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 246-251.'
			newTechnique.activityDescription = 'Is a technique in which students write  essay questions and model answers for each other, exchange questions, and after responding compare their answers to the model answers.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()

			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Peer Editing'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 251-256.'
			newTechnique.activityDescription = 'Is a tecgnique in which students critially review and provide  feedback on a peers essay, report,argument,research paper,or other writing assignment. '
			newTechnique.pedagogyMode = PedagogyMode.findByName('hybrid')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Both Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))

			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Discussing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Team Anthologies'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 262-267.'
			newTechnique.activityDescription = 'Is a technique in which students develop a compilation of course-related readings with student reactions to the material.It is particularly useful for experiencing the research process without writing a formal research paper.'
			newTechnique.pedagogyMode = PedagogyMode.findByName('online')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))

			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Reading'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Writing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			newTechnique = new PedagogyTechnique()
			newTechnique.title = 'Paper Seminar'
			newTechnique.materials = 'materials'
			newTechnique.reference = 'Elizabeth F. Barkley, K.Patricia Cross and Claire Howell Major. “Collaborative Learning Techniques“, 1st ed. James Rhem. California: Jossey-Bass, 2005, pp 267-273.'
			newTechnique.activityDescription = 'Is a technique in which students write and then present a paper, receive  feedback from  peers, and engage in a general discussion of the issues in the paper with the entire group. '
			newTechnique.pedagogyMode = PedagogyMode.findByName('in-person')
			newTechnique.pedagogyDuration= PedagogyActivityDuration.findByDuration('Single Session')
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Conceptual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Factual'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Procedural'))
			newTechnique.addToKnowledgeDimension(KnowledgeDimension.findByDescription('Metacognitive'))

			newTechnique.addToDomainCategory(DomainCategory.findByName('Understand'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Apply'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Evaluate'))
			newTechnique.addToDomainCategory(DomainCategory.findByName('Analyze'))

			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Presenting'))
			newTechnique.addToActivityFocus(PedagogyActivityFocus.findByFocus('Discussing'))
			newTechnique.isAdmin = true
			newTechnique.save()
			admin.addToPedagogyTechnique(newTechnique)
			admin.save()


			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'In-Class Portfolio',
				description: 'Students collect and organize lecture notes, essay responses to prompts presented in class, summaries of discussions, personal reflections into a portfolio and submit for evaluation two to three times during the academic year.',
				domain: LearningDomain.findAllByNameInList(['Cognitive']),
				category: DomainCategory.findAllByNameInList(['Applying','Analyzing']),
				knowledge: KnowledgeDimension.findAllByDescriptionInList(['Factual', 'Conceptual', 'Procedural', 'Metacognitive']),
				focus: PedagogyActivityFocus.findAllByFocusInList(['Writing', 'Discussing']),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: 'Step-1',
				description: 'Organise class sessions so that in addition to listening, students are actively integrating and applying what they learnt by writing, discussing and problem solving ',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-2',
				description: 'Determine portfolio parameters',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-3',
				description: 'Decide how portfolio will be evaluated and determine the grading rubrics. Explain the process and expectations to students',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-4',
				description: 'Assess the student essay based on the original essay ',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType: PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				title: 'Seminar',
				description: 'Students make formal presentations of an original paper to a small group of peers.',
				domain: LearningDomain.findAllByNameInList([
					'Cognitive'
				]),
				category:DomainCategory.findAllByNameInList([
					'Understanding',
					'Remember'
				]),
				knowledge:KnowledgeDimension.findAllByDescriptionInList([
					'Factual',
					'Conceptual'
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					'Reading',
					'Writing',
					'Discussing',
					'Presenting'
				]),
				pedagogyMode: PedagogyMode.findByName('hybrid'),
				isAdmin: true
			).save()
			admin.addToPedagogyTechnique(pedagogyTech)
			admin.save()

			/*Pedagogy Activity*/
			new PedagogyActivity(
				title: 'Step-1',
				description: 'Assign students research papers',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-2',
				description: 'Explain the time frame and tasks, and give time to discuss the paper with peers',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			new PedagogyActivity(
				title: 'Step-3',
				description: 'Students will present the paper in the class and discuss the questions raised by other students.',
				example: '',
				material: '',
				pedagogyActivityDuration: PedagogyActivityDuration.findByDuration('Single Session'),
				pedagogyTechnique: pedagogyTech
			).save()

			/*Pedagogy Reference*/
			new PedagogyReference(
				title: 'Student_Engagement_Techniques',
				author: 'Elizabeth F. Barkley',
				referenceLinkISBN: '978-0-470-28191-8',
				referenceType: PedagogyReferenceType.findByDescription('Book'),
				pedagogyTechnique: pedagogyTech
			).save()
		}
	}
	def destroy = {
	}
}
