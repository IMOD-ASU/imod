import imodv6.ContentKnowledgeDomainCode
import imodv6.ContentPriorityCode
import imodv6.ContentResourceType
import imodv6.DomainCategory
import imodv6.LearningDomain
import imodv6.PedagogyActivity
import imodv6.PedagogyActivityDuration
import imodv6.PedagogyActivityFocus
import imodv6.PedagogyMode
import imodv6.PedagogyReference
import imodv6.PedagogyReferenceType
import imodv6.PedagogyTechnique


class BootStrap {

    def init = { servletContext ->
        if (imodv6.Role.list().size() == 0) {
            //user roles can be changed here, or in the ui
            new imodv6.Role(authority: "ROLE_ANONYMOUS").save();
            new imodv6.Role(authority: "ROLE_ADMIN").save();
            new imodv6.Role(authority: "ROLE_USER").save();
        }

        if (imodv6.Help.count() == 0) {
            //Tab 3 Content tab
            new imodv6.Help(tabFieldUiId: "Add Learning Objective", tabId: "CONTENT", tabFieldId: 1, tabFieldName: "Add Learning Objective", text: "Click on this button to add a Learning Objective").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Remove Learning Objective", tabId: "CONTENT", tabFieldId: 2, tabFieldName: "Remove Learning Objective", text: "Click on this button to remove a Learning Objective").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add Objective", tabId: "CONTENT", tabFieldId: 3, tabFieldName: "Add Objective", text: "Add Objective").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Topic Schedule", tabId: "CONTENT", tabFieldId: 4, tabFieldName: "Topic Schedule", text: "Click on this button to add a Topic Schedule").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Topic Map", tabId: "CONTENT", tabFieldId: 5, tabFieldName: "Topic Map", text: "Topic Map").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add topic schedule", tabId: "CONTENT", tabFieldId: 6, tabFieldName: "Add topic schedule", text: "Add topic schedule").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add Topic Form", tabId: "CONTENT", tabFieldId: 7, tabFieldName: "Add Topic", text: "Add Topic").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Remove Topic Widget", tabId: "CONTENT", tabFieldId: 8, tabFieldName: "Remove Topic Widget", text: "Remove Topic").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add Topic", tabId: "CONTENT", tabFieldId: 9, tabFieldName: "Add Topic", text: "Click on this button to add a Topic").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Remove Topic", tabId: "CONTENT", tabFieldId: 10, tabFieldName: "Remove Topic", text: "Click on this button to remove a Topic").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add Resource", tabId: "CONTENT", tabFieldId: 11, tabFieldName: "Add Resource", text: "Add Resource").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Topic Distributions", tabId: "CONTENT", tabFieldId: 12, tabFieldName: "Topic Distributions", text: "Click on this button to display topic distributions").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Topic Label", tabId: "CONTENT", tabFieldId: 13, tabFieldName: "Topic", text: "Topic").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Knowledge Dimension Label", tabId: "CONTENT", tabFieldId: 14, tabFieldName: "Knowledge Dimension", text: "Knowledge Dimension").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Priority Label", tabId: "CONTENT", tabFieldId: 15, tabFieldName: "Priority Label", text: "Priority").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Resources Label", tabId: "CONTENT", tabFieldId: 16, tabFieldName: "Resources Label", text: "Resources").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Pre-Req Label", tabId: "CONTENT", tabFieldId: 17, tabFieldName: "Pre-Req Label", text: "Pre-Req").save(flush: true)

            //Tab 1 Course overview
            new imodv6.Help(tabFieldUiId: "Save Course Overview", tabId: "OVERVIEW", tabFieldId: 1, tabFieldName: "Save Course Overview", text: "Click on this button to save a Course overview").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Delete Course Overview", tabId: "OVERVIEW", tabFieldId: 2, tabFieldName: "Delete Course Overview", text: "Click on this button to remove a Course overview").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Schedule start Date", tabId: "OVERVIEW", tabFieldId: 3, tabFieldName: "Schedule start Date", text: "Click on this button to select a Schedule start date").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Schedule end Date", tabId: "OVERVIEW", tabFieldId: 4, tabFieldName: "Schedule end Date", text: "Click on this button to select a Schedule end date").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Add instructor", tabId: "OVERVIEW", tabFieldId: 5, tabFieldName: "Add instructor", text: "Click on this button to add a Instructor").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Last Name Label", tabId: "OVERVIEW", tabFieldId: 6, tabFieldName: "Last Name", text: "Last Name").save(flush: true)
            new imodv6.Help(tabFieldUiId: "First Name Label", tabId: "OVERVIEW", tabFieldId: 7, tabFieldName: "First Name", text: "First Name").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Email Label", tabId: "OVERVIEW", tabFieldId: 8, tabFieldName: "Email", text: "Email").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Office Hours Label", tabId: "OVERVIEW", tabFieldId: 9, tabFieldName: "Office Hours", text: "Office Hours").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Web Page Label", tabId: "OVERVIEW", tabFieldId: 10, tabFieldName: "Web Page", text: "Web Page").save(flush: true)
        
			//Tab 5 Pedagogy
			new imodv6.Help(tabFieldUiId: "Add New Technique", tabId: "PEDAGOGY", tabFieldId: 1, tabFieldName: "Add New Technique", text: "Click on this button to add a new technique").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Clone technique", tabId: "PEDAGOGY", tabFieldId: 2, tabFieldName: "Clone technique", text: "Click on this button to clone a technique").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Favorites", tabId: "PEDAGOGY", tabFieldId: 3, tabFieldName: "Favorites", text: "Favorites displays the techniques that have be made the user's favorites").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Instructional Plan", tabId: "PEDAGOGY", tabFieldId: 4, tabFieldName: "Instructional Plan", text: "Yet to be implemented").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Ideal Match", tabId: "PEDAGOGY", tabFieldId: 5, tabFieldName: "Ideal Match", text: "Ideal Match displays the techniques that exactly match the Domain, Domain Category and Knowledge Dimension of the objective selected.").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Extended Match", tabId: "PEDAGOGY", tabFieldId: 6, tabFieldName: "Extended Match", text: "Extended Match displays the techniques that match the Domain, Domain Category and Knowledge Dimension of the selections made in the Filter Options").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Domain", tabId: "PEDAGOGY", tabFieldId: 7, tabFieldName: "Domain", text: "Bloom's Taxonomy definition the domains of educational activities or learning (Cognitive, Affective and Psychomotive)").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Domain Category", tabId: "PEDAGOGY", tabFieldId: 8, tabFieldName: "Domain Category", text: "The categories were expressed as verbs rather than nouns as Remembering, Understanding, Applying, Analyzing, Evaluating, and Creating").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Knowledge Dimension", tabId: "PEDAGOGY", tabFieldId: 9, tabFieldName: "Knowledge Dimension", text: "The Knowledge Dimension embodied both noun and verb aspects and categorized as Factual, Conceptual, Procedural and Metacognitive Knowledge ").save(flush: true)
            new imodv6.Help(tabFieldUiId: "Refresh", tabId: "Refresh", tabFieldId: 10, tabFieldName: "Web Page", text: "Clicking on Refresh displays the techniques that belongs the selections on the Domain, Domain Category and Knowledge Dimension in the Extended match.").save(flush: true)
		}

		if (ContentPriorityCode.count() == 0) {
            new ContentPriorityCode(description: "Critical").save(flush: true)
            new ContentPriorityCode(description: "Very important").save(flush: true)
            new ContentPriorityCode(description: "Good to know").save(flush: true)
        }

        if (ContentPriorityCode.count() == 0) {
            new ContentPriorityCode(description: "Critical").save(flush: true)
            new ContentPriorityCode(description: "Very important").save(flush: true)
            new ContentPriorityCode(description: "Good to know").save(flush: true)
        }
		
		if (LearningDomain.count() == 0) {
			new LearningDomain(name: "Cognitive").save(flush: true)
			new LearningDomain(name: "Affective").save(flush: true)
			new LearningDomain(name: "Psychomotor").save(flush: true)
		}

		if (DomainCategory.count() == 0) {
			new DomainCategory(domain_id: 1, name: "Remembering").save(flush: true)
			new DomainCategory(domain_id: 1, name: "Understanding").save(flush: true)
			new DomainCategory(domain_id: 1, name: "Applying").save(flush: true)
			new DomainCategory(domain_id: 1, name: "Analyzing").save(flush: true)
			new DomainCategory(domain_id: 1, name: "Evaluating").save(flush: true)
			new DomainCategory(domain_id: 1, name: "Creating").save(flush: true)
			new DomainCategory(domain_id: 2, name: "Receiving Phenomena").save(flush: true)
			new DomainCategory(domain_id: 2, name: "Responding").save(flush: true)
			new DomainCategory(domain_id: 2, name: "Valuing").save(flush: true)
			new DomainCategory(domain_id: 2, name: "Organisation").save(flush: true)
			new DomainCategory(domain_id: 2, name: "Internalising Values").save(flush: true)
		}
	
		
        if (ContentKnowledgeDomainCode.count() == 0) {
            new ContentKnowledgeDomainCode(description: "Factual").save(flush: true)
            new ContentKnowledgeDomainCode(description: "Conceptual").save(flush: true)
            new ContentKnowledgeDomainCode(description: "Procedural").save(flush: true)
            new ContentKnowledgeDomainCode(description: "Metacognitive").save(flush: true)
        }

        if (ContentResourceType.count() == 0) {
            new ContentResourceType(description: "Document").save(flush: true)
            new ContentResourceType(description: "Book").save(flush: true)
            new ContentResourceType(description: "Chapter").save(flush: true)
            new ContentResourceType(description: "URL").save(flush: true)
        }
		
		if(PedagogyMode.count() == 0){
			new PedagogyMode(name:"online").save(flush:true)
			new PedagogyMode(name:"in-person").save(flush:true)
			new PedagogyMode(name:"hybrid").save(flush:true)
		}
		
		if(PedagogyReferenceType.count() == 0){
			new PedagogyReferenceType(description:"Book").save(flush:true)
			new PedagogyReferenceType(description:"Web").save(flush:true)
		}
		
		if(PedagogyActivityDuration.count() == 0){
			new PedagogyActivityDuration(duration:"Single Session").save(flush:true)
			new PedagogyActivityDuration(duration:"Multiple Session").save(flush:true)
			new PedagogyActivityDuration(duration:"Both Session").save(flush:true)
		}
		if(PedagogyActivityFocus.count() == 0){
			new PedagogyActivityFocus(focus:"Reading").save(flush:true)
			new PedagogyActivityFocus(focus:"Writing").save(flush:true)
			new PedagogyActivityFocus(focus:"Discussing").save(flush:true)
			new PedagogyActivityFocus(focus:"Presenting").save(flush:true)
		}
		
		if(PedagogyTechnique.count() == 0){
			/*Pedagogy Technique*/
			def pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Jigsaw",
				pedagogyDescription: "As in a jigsaw puzzle, each piece representing each student's part is essential for the completion and full understanding of the final product. ",
				domain:LearningDomain.findAllByNameInList(['Cognitive']),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList(['Remembering']),
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
				pedagogyMode: PedagogyMode.findByName("in-person")).save(flush:true)
			new PedagogyActivity(title:"jigsaw",
				description:"Small tasks are split among each student in each group. Eventually each student will come back to her or his jigsaw group and will try to present a well-organized report to the group. As a team the students organize their work and present it as a whole.",
				example:"Research on different computing languages was to be done. The students were split into five teams of four members each and each team was assigned a language. The students were asked to divide the the tasks to be researched based on the history, implementation, application of the language and comparison with other languages. Students worked individually and eventually put the wok together, then gave a presentation on each area of research.",
				material:"clicker, paper",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			new PedagogyReference(title:"Student_Engagement_Techniques",
				author:"Elizabeth F. Barkley",
				referenceLinkISBN:"978-0-470-28191-8",
				referenceType:PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			
			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Structured Problem Solving",
				pedagogyDescription: "Provides students with a process for solving complex, content based problems within a specified amount of time. It breaks the problem into specific steps and thus students learn to identify, analyze, and solve problems in an organized way",
				domain:LearningDomain.findAllByNameInList(['Cognitive']),
				category:DomainCategory.findAllByNameInList(["Understanding","Applying","Analyzing","Evaluating"]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList(["Metacognitive"]),
				focus: PedagogyActivityFocus.findAllByFocusInList(["Discussing","Presenting"]),
				pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
			/*Pedagogy Activity*/
			new PedagogyActivity(title:"Step-2",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example:"The Dewey Six-Step Problem Solving Technique",
				material:"",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			new PedagogyActivity(title:"Step-3",
				description: "ask students to solve the problem using specific steps you have identified as a problem solving technique",
				example:"The Dewey Six-Step Problem Solving Technique",
				material:"",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			new PedagogyActivity(title:"Step-1",
				description:"Organize students into teams and assign them a complex problem to solve",
				example:"Problem could be like evaluate the effectiveness of the antacids",
				material:"",
				pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			/*Pedagogy Reference*/
			new PedagogyReference(title:"Student_Engagement_Techniques",
				author:"Elizabeth F. Barkley",
				referenceLinkISBN:"978-0-470-28191-8",
				referenceType:PedagogyReferenceType.findByDescription("Book"),
				pedagogyTechnique:pedagogyTech).save(flush:true)
			
			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Face-to Face/ Video / Audio  Lectures ",
				pedagogyDescription: "Lecture",
				domain:LearningDomain.findAllByNameInList(['Cognitive']),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList(['Remembering',"Understanding"]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Factual",
					"Conceptual"
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
			
			/*Pedagogy Technique*/
			pedagogyTech = new PedagogyTechnique(
				pedagogyTitle:"Partially Guided Programming Exercise",
				pedagogyDescription:"Partially Guided Programming Exercise ",
				domain:LearningDomain.findAllByNameInList(['Cognitive']),//'Cognitive','Affective', 'Psychomotor'
				category:DomainCategory.findAllByNameInList(["Understanding", "Applying", "Analyzing", "Evaluating"]),
				knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList([
					"Conceptual",
					"Procedural"					
				]),
				focus: PedagogyActivityFocus.findAllByFocusInList([
					"Reading",
					"Writing",
					"Discussing",
					"Presenting"
				]),
				pedagogyMode: PedagogyMode.findByName("in-person")).save(flush:true)
				
				/*Pedagogy Technique*/
				pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Think Aloud Pair Problem Solving",
					pedagogyDescription: "Student pairs receive a series of problems as well as specific roles - problem solver and listener - then switch with each problem. The problem solver thinks aloud, talking through the steps of solving the problem, while the partner listens, following the steps, attempting to understand the reasoning behind the steps, and offering suggestions if there is a misstep.",
					domain:LearningDomain.findAllByNameInList(['Cognitive']),
					category:DomainCategory.findAllByNameInList(["Understanding", "Applying", "Analyzing", "Evaluating"]),
					knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList(["Factual", "Conceptual", "Procedural", "Metacognitive"]),
					focus: PedagogyActivityFocus.findAllByFocusInList(["Discussing","Presenting"]),
					pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
				/*Pedagogy Activity*/
				new PedagogyActivity(title:"Step-1",
					description: "Spend time developing an appropriate set of field related problems to solve within a limited time frame",
					example:"",
					material:"",
					pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
					pedagogyTechnique:pedagogyTech).save(flush:true)
				new PedagogyActivity(title:"Step-2",
					description: "Ask students to form pairs",
					example:"",
					material:"",
					pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
					pedagogyTechnique:pedagogyTech).save(flush:true)
				new PedagogyActivity(title:"Step-3",
					description: "Ask students to solve problems alternating the roles with each new problem",
					example:"",
					material:"",
					pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
					pedagogyTechnique:pedagogyTech).save(flush:true)
				new PedagogyActivity(title:"Step-4",
					description: "Call completion when all problems have been solved",
					example:"",
					material:"",
					pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
					pedagogyTechnique:pedagogyTech).save(flush:true)
				/*Pedagogy Reference*/
				new PedagogyReference(title:"Student_Engagement_Techniques",
					author:"Elizabeth F. Barkley",
					referenceLinkISBN:"978-0-470-28191-8",
					referenceType:PedagogyReferenceType.findByDescription("Book"),
					pedagogyTechnique:pedagogyTech).save(flush:true)
					
					/*Pedagogy Technique*/
					pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Frames",
						pedagogyDescription: "Instructors give students a template of sentence stem that provides the shape of a short essay but not the content. Students complete the sentence, expressing their own ideas in their own words within a clear and organized framework.",
						domain:LearningDomain.findAllByNameInList(['Cognitive']),
						category:DomainCategory.findAllByNameInList(["Analyzing","Evaluating"]),
						knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList(["Factual", "Conceptual", "Procedural", "Metacognitive"]),
						focus: PedagogyActivityFocus.findAllByFocusInList(["Reading", "Writing"]),
						pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
					/*Pedagogy Activity*/
					new PedagogyActivity(title:"Step-1",
						description: "Choose a topic and write a brief essay that you have a skeleton of the esay",
						example:"Theory X exposes that ________ and is very useful because it offers insight into __________. ",
						material:"",
						pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
						pedagogyTechnique:pedagogyTech).save(flush:true)
					new PedagogyActivity(title:"Step-2",
						description: "Make copies of the frames along with the directions to use as handouts and distribute it to each student.",
						example:"",
						material:"",
						pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
						pedagogyTechnique:pedagogyTech).save(flush:true)
					new PedagogyActivity(title:"Step-3",
						description: "Students write the essay using the framework as a guide",
						example:"",
						material:"",
						pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
						pedagogyTechnique:pedagogyTech).save(flush:true)
					new PedagogyActivity(title:"Step-4",
						description: "Assess the student essay based on the original essay ",
						example:"",
						material:"",
						pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
						pedagogyTechnique:pedagogyTech).save(flush:true)
					/*Pedagogy Reference*/
					new PedagogyReference(title:"Student_Engagement_Techniques",
						author:"Elizabeth F. Barkley",
						referenceLinkISBN:"978-0-470-28191-8",
						referenceType:PedagogyReferenceType.findByDescription("Book"),
						pedagogyTechnique:pedagogyTech).save(flush:true)
						/*Pedagogy Technique*/
						pedagogyTech = new PedagogyTechnique(pedagogyTitle:"In-Class Portfolio",
							pedagogyDescription: "Students collect and organize lecture notes, essay responses to prompts presented in class, summaries of discussions, personal reflections into a portfolio and submit for evaluation two to three times during the academic year.",
							domain:LearningDomain.findAllByNameInList(['Cognitive']),
							category:DomainCategory.findAllByNameInList(["Applying","Analyzing"]),
							knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList(["Factual", "Conceptual", "Procedural", "Metacognitive"]),
							focus: PedagogyActivityFocus.findAllByFocusInList(["Writing", "Discussing"]),
							pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
						/*Pedagogy Activity*/
						new PedagogyActivity(title:"Step-1",
							description: "Organise class sessions so that in addition to listening, students are actively integrating and applying what they learnt by writing, discussing and problem solving ",
							example:"",
							material:"",
							pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
							pedagogyTechnique:pedagogyTech).save(flush:true)
						new PedagogyActivity(title:"Step-2",
							description: "Determine portfolio parameters",
							example:"",
							material:"",
							pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
							pedagogyTechnique:pedagogyTech).save(flush:true)
						new PedagogyActivity(title:"Step-3",
							description: "Decide how portfolio will be evaluated and determine the grading rubrics. Explain the process and expectations to students",
							example:"",
							material:"",
							pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
							pedagogyTechnique:pedagogyTech).save(flush:true)
						new PedagogyActivity(title:"Step-4",
							description: "Assess the student essay based on the original essay ",
							example:"",
							material:"",
							pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
							pedagogyTechnique:pedagogyTech).save(flush:true)
						/*Pedagogy Reference*/
						new PedagogyReference(title:"Student_Engagement_Techniques",
							author:"Elizabeth F. Barkley",
							referenceLinkISBN:"978-0-470-28191-8",
							referenceType:PedagogyReferenceType.findByDescription("Book"),
							pedagogyTechnique:pedagogyTech).save(flush:true)
					
							/*Pedagogy Technique*/410
							pedagogyTech = new PedagogyTechnique(pedagogyTitle:"Seminar",
								pedagogyDescription: "Students make formal presentations of an original paper to a small group of peers.",
								domain:LearningDomain.findAllByNameInList(['Cognitive']),
								category:DomainCategory.findAllByNameInList(["Understanding","Remembering"]),
								knowledge:ContentKnowledgeDomainCode.findAllByDescriptionInList(["Factual", "Conceptual"]),
								focus: PedagogyActivityFocus.findAllByFocusInList(["Reading", "Writing", "Discussing","Presenting"]),
								pedagogyMode: PedagogyMode.findByName("hybrid")).save(flush:true)
							/*Pedagogy Activity*/
							new PedagogyActivity(title:"Step-1",
								description: "Assign students research papers",
								example:"",
								material:"",
								pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
								pedagogyTechnique:pedagogyTech).save(flush:true)
							new PedagogyActivity(title:"Step-2",
								description: "Explain the time frame and tasks, and give time to discuss the paper with peers",
								example:"",
								material:"",
								pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
								pedagogyTechnique:pedagogyTech).save(flush:true)
							new PedagogyActivity(title:"Step-3",
								description: "Students will present the paper in the class and discuss the questions raised by other students.",
								example:"",
								material:"",
								pedagogyActivityDuration:PedagogyActivityDuration.findByDuration("Single Session"),
								pedagogyTechnique:pedagogyTech).save(flush:true)
							/*Pedagogy Reference*/
							new PedagogyReference(title:"Student_Engagement_Techniques",
								author:"Elizabeth F. Barkley",
								referenceLinkISBN:"978-0-470-28191-8",
								referenceType:PedagogyReferenceType.findByDescription("Book"),
								pedagogyTechnique:pedagogyTech).save(flush:true)
		}
    }
    def destroy = {
    }
}