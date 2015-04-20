package imod

import org.springframework.dao.DataIntegrityViolationException

class ImodController {
	def learningObjectiveService

	static allowedMethods = [
		save: 'POST',
		update: 'POST',
		delete: 'POST'
	]

	def springSecurityService


	def index() {
		redirect(
			action: 'list',
			params: params
		)
	}

	def list() {
        // get current user object
        def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

        def interpret = ActionWordCategory.findByActionWordCategory("Interpret")
        def implement = ActionWordCategory.findByActionWordCategory("Implement")


        // create sample imod if it doesn't exist
        def sample = currentUser.imods.find{it.name == 'Object-Oriented Software Development - sample'}

        if(sample == null){

            def audience1 = Audience.findByDescription("Lower Division");

            // create a new imod
            def newImod = new Imod(
                owner: currentUser,
                name: 'Object-Oriented Software Development - sample',
                url: 'https://piazza.com/asu/spring2014/cst100/home',           
                subjectArea: 'Computer Science',
                imodNumber: 'CST100',
                courseLocation: 'Peralta 213',
                overview: 'Introduces problem solving with a state-of-the-art programming language. Expressions, statements, basic control flow and methods. Data, data aggregation and usage.',
                courseSemester: 'Fall 2015',
                gradingProcedure: 'Attainment of course outcomes will be assessed based on your performance in various course activities throughout the semester. Table 1 shows a breakdown of the percentage allocation for each type of course assessment activity. \n\n Table 1: Percentage Allocation for each Type of Course Assessment Activity \n ASSESSMENTS % OF FINAL GRADE \n Assignments 40 \n Team Project    20 \n Mid-term test   20 \n Final Exam  20 \n \n The standard ASU grading options will be used in this course: \n A+; A; A-; B+; B; B-; C+; C; C-; D; and E. See Table 2 for the percentage allocation for each grade level. \n Table 2: Percentage allocation for Each Grade Level \n A+  95 and above \n B   75 –79 \n C-  55 – 59 \n A   90 – 94 \n B-  70 – 74 \n D   50 – 54 \n A-  85 – 89 \n C+  65 – 69 \n E   49 and below \n B+  80 – 84 \n C   60 – 64',
                attendance: 'Regular on-time attendance in this course is expected. The presentations, activities and discussions that will occur during class sessions are designed to enhance your educational experience and help you achieve the identified course objectives. An attendance sheet will be circulated / Check for Understanding assignment will be administered at the beginning of each class session (Tuesday and Thursday sessions). You are responsible for ensuring that you sign the attendance sheet/ complete and submit your assignment. You may miss up to three class sessions without penalty. If you are absent, I assume you have a good reason and do not need to see any related documentation. Beginning with your fourth absence, each missed session will result in the loss of 2 percentage points from your final cumulative grade.',
                classParticipation: 'TBD',
                professionalConduct: '• You should keep a copy of all work submitted for grading (exception: quizzes) and any e-mails concerning submitted work that is sent to the instructor.  If an assignment “goes missing”, for whatever reason, the copy kept by you will be used for grading.  If no copy is available, a score of zero will be given. Submittals rarely are lost but if it happens this is the policy that will be followed. \n •   The classroom should be a lively, interactive, and comfortable place where information is shared, ideas tested and issues debated. You are expected to participate in class discussions and activities in a manner that is respectful and sensitive to the cultural, religious, sexual, and other individual differences in the ASU community. If you encounter a problem with civility and respect, please do not hesitate to come forward and discuss with me. We will find a means to address and rectify the situation. \n •   The following behaviors are considered unacceptable, and are unwelcomed during class sessions:\n(1) Wearing caps and other headgear (except those worn for medical or religious purposes) during class. \n (2) Sleeping or daydreaming during class. \n (3) Chronic tardiness; be here when class starts. \n (4) Reading or working on materials that are extraneous to the course. \n (5) Prematurely packing up your books and bags before class has ended. \n (6) Chatting with your classmates during instructor or student-led presentations. \n (7) Checking your cell phones or other electronic devices. \n •   I have no problem with eating and drinking during class, provided you don’t leave a mess or distract others. You must however honor the policies of the assigned room. \n \n ACADEMIC INTEGRITY & CONDUCT: \n Each student has an obligation to act with honesty and integrity, and to respect the rights of others in carrying out all academic assignments. You should be familiar with, and adhere to the student code of conduct, and the Academic Integrity Policy (see links to related documents below).  In the event of violations of academic integrity or conduct, the conditions, procedures, and sanctions provided by these policies will guide the process taken to address the issue. \n \n Student Code of Conduct: https://students.asu.edu/srr \n\n  Integrity Policy: \n https://provost.asu.edu/sites/default/files/AcademicIntegrityPolicyPDF.pdf',
                missedExams: 'TBD',
                missedAssignments: 'ASSIGNMENT SUBMISSION\nAssignment submission dates are generally firm, and in most cases submittals are due either before or at the beginning of class.  I appreciate, however, that there may be times during the semester, when work from various courses “pile up”.  If this happens you should not hesitate to request a deadline extension via email or in-person. Late assignments will not be accepted unless an extension was granted by the instructor. The Recitation Leaders will not approve deadline extensions. The latest time to request an extension is 24 hours before the assignment is due. Requests made after this time will be denied. If granted, you are responsible for ensuring that the late assignment is submitted according to all other specifications in the assignment instructions.\n\nFEEDBACK ON ASSIGNMENTS\nIn most cases, submitted assignments will be graded and returned within a week. For more elaborate submissions (e.g., Project), the turn-around time may be more.',
                creditHours: '3',
                timeRatio: '1:3',
                numberOfSeats: '30'
            )

			// update current user
            currentUser.addToImods(newImod)

            // save new imod and the updated user to database
            currentUser.save()

            newImod.addToAudience(audience1)

            def newInstructor = new Instructor(
                firstName: "Dr. Ajay",
                lastName: "Bansal",
                email: "ajay.bansal@asu.edu",
                role: "Professor",
                officeHours: "Tuesdays & Thursdays | 9:00am – 10:0am or by appointment",
                location: "Peralta 230V",
                createdBy: newImod.id
            )

            // save new instructor and the updated user to database
            newInstructor.save()

            newInstructor = new Instructor(
                firstName: "Rehman",
                lastName: "Chughtai",
                email: "Rehman.Chughtai@asu.edu",
                role: "Assistant Professor",
                officeHours: "by email appointment",
                location: "Peralta 235 (Bullpen)",
                createdBy: newImod.id
            )


            // save new instructor and the updated user to database
            newInstructor.save()

            def currentImod = newImod;

            // add learning objectives
            
            // LO(1)
            def learningObjectiveID = learningObjectiveService.create(newImod)
            def lo1 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo1.actionWordCategory = implement
            lo1.definition = "(LO1) Given a problem specification apply Iteration, Constructing Algorithms, Constructing Formal Code, Selection, Sequence, and Problem Solving 85%";
            lo1.actionWord = "apply"
            lo1.condition = "Given a problem specification"
            lo1.criteriaAccuracy = "85%"
            lo1.criteriaAccuracyEnabled = "TRUE"
            lo1.criteriaAccuracyHidden = "FALSE"
            lo1.criteriaQuality = ""
            lo1.criteriaQualityEnabled = "FALSE"
            lo1.criteriaQualityHidden = "FALSE"
            lo1.criteriaQuantity = ""
            lo1.criteriaQuantityEnabled = "FALSE"
            lo1.criteriaQuantityHidden = "FALSE"
            lo1.criteriaSpeed = ""
            lo1.criteriaSpeedEnabled = "FALSE"
            lo1.criteriaSpeedHidden = "FALSE"
            lo1.hideFromLearningObjectiveCondition = "FALSE"
            lo1.performance = "Apply"
			lo1.save();

			// LO(2)
            learningObjectiveID = learningObjectiveService.create(newImod)
            def lo2 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo2.actionWordCategory = implement
            lo2.definition = "(LO2) Given a problem specification use Strings, Tuples, Constructing Formal Code, Store and Manipulate Data, Lists, Variables, and Constructing Algorithms 85% determined per assessment";
            lo2.actionWord = "use"
            lo2.condition = "Given a problem specification"
            lo2.criteriaAccuracy = "85%"
            lo2.criteriaAccuracyEnabled = "TRUE"
            lo2.criteriaAccuracyHidden = "FALSE"
            lo2.criteriaQuality = ""
            lo2.criteriaQualityEnabled = "FALSE"
            lo2.criteriaQualityHidden = "FALSE"
            lo2.criteriaQuantity = ""
            lo2.criteriaQuantityEnabled = "FALSE"
            lo2.criteriaQuantityHidden = "FALSE"
            lo2.criteriaSpeed = "determined per assessment"
            lo2.criteriaSpeedEnabled = "TRUE"
            lo2.criteriaSpeedHidden = "FALSE"
            lo2.hideFromLearningObjectiveCondition = "FALSE"
            lo2.performance = "Apply"
			lo2.save();

			// LO(3)
            learningObjectiveID = learningObjectiveService.create(newImod)
            def lo3 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo3.actionWordCategory = implement
            lo3.definition = "(LO3) Given a problem specification use Constructing Algorithms, Constructing Formal Code, Object Types, Functions, Classes and Objects, and Modular Programming Techniques";
            lo3.actionWord = "use"
            lo3.condition = "Given a problem specification"
            lo3.criteriaAccuracy = ""
            lo3.criteriaAccuracyEnabled = "FALSE"
            lo3.criteriaAccuracyHidden = "FALSE"
            lo3.criteriaQuality = ""
            lo3.criteriaQualityEnabled = "FALSE"
            lo3.criteriaQualityHidden = "FALSE"
            lo3.criteriaQuantity = ""
            lo3.criteriaQuantityEnabled = "FALSE"
            lo3.criteriaQuantityHidden = "FALSE"
            lo3.criteriaSpeed = ""
            lo3.criteriaSpeedEnabled = "FALSE"
            lo3.criteriaSpeedHidden = "FALSE"
            lo3.hideFromLearningObjectiveCondition = "FALSE"
            lo3.performance = "Apply"
			lo3.save();

			// LO(4)
            learningObjectiveID = learningObjectiveService.create(newImod)
            def lo4 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo4.actionWordCategory = interpret
            lo4.definition = "(LO4) understand Object, Classes and Objects, and Types dependent per assessment";
            lo4.actionWord = "understand"
            lo4.condition = "Given a problem specification"
            lo4.criteriaAccuracy = "dependent per assessment"
            lo4.criteriaAccuracyEnabled = "TRUE"
            lo4.criteriaAccuracyHidden = "FALSE"
            lo4.criteriaQuality = ""
            lo4.criteriaQualityEnabled = "FALSE"
            lo4.criteriaQualityHidden = "FALSE"
            lo4.criteriaQuantity = ""
            lo4.criteriaQuantityEnabled = "FALSE"
            lo4.criteriaQuantityHidden = "FALSE"
            lo4.criteriaSpeed = ""
            lo4.criteriaSpeedEnabled = "FALSE"
            lo4.criteriaSpeedHidden = "FALSE"
            lo4.performance = "Understand"
			lo4.save();

			// LO(5)
            learningObjectiveID = learningObjectiveService.create(newImod)
            def lo5 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo5.actionWordCategory = implement
            lo5.definition = "(LO5) Given a problem specification apply Constructing Algorithms, Constructing Formal Code, and Problem Solving 85% determined per assessment";
            lo5.actionWord = "apply"
            lo5.condition = "Given a problem specification"
            lo5.criteriaAccuracy = "85%"
            lo5.criteriaAccuracyEnabled = "TRUE"
            lo5.criteriaAccuracyHidden = "FALSE"
            lo5.criteriaQuality = "determined per assessment"
            lo5.criteriaQualityEnabled = "TRUE"
            lo5.criteriaQualityHidden = "FALSE"
            lo5.criteriaQuantity = ""
            lo5.criteriaQuantityEnabled = "FALSE"
            lo5.criteriaQuantityHidden = "FALSE"
            lo5.criteriaSpeed = ""
            lo5.criteriaSpeedEnabled = "FALSE"
            lo5.criteriaSpeedHidden = "FALSE"
            lo5.hideFromLearningObjectiveCondition = "FALSE"
            lo5.performance = "Apply"
			lo5.save();

			// LO(6)
            learningObjectiveID = learningObjectiveService.create(newImod)
            def lo6 = learningObjectiveService.safeGet(currentImod, learningObjectiveID)
            lo6.actionWordCategory = implement
            lo6.definition = "(LO6) Given a problem specification configure Errors (syntax, semantic, runtime), Setup and Configuration, Executing Code, Constructing Formal Code, Writing Code, and Software Development Environment 85% determined per assessment";
            lo6.actionWord = "configure"
            lo6.condition = "Given a problem specification"
            lo6.criteriaAccuracy = "85%"
            lo6.criteriaAccuracyEnabled = "TRUE"
            lo6.criteriaAccuracyHidden = "FALSE"
            lo6.criteriaQuality = ""
            lo6.criteriaQualityEnabled = "FALSE"
            lo6.criteriaQualityHidden = "FALSE"
            lo6.criteriaQuantity = ""
            lo6.criteriaQuantityEnabled = "FALSE"
            lo6.criteriaQuantityHidden = "FALSE"
            lo6.criteriaSpeed = "determined per assessment"
            lo6.criteriaSpeedEnabled = "TRUE"
            lo6.criteriaSpeedHidden = "FALSE"
            lo6.hideFromLearningObjectiveCondition = "FALSE"
            lo6.performance = "Apply"
			lo6.save();


			// content
			def currentInstance = null
			def dimensions
			def dimensions2

			def contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Programming Fundamentals"
			dimensions = "Metacognitive, Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Problem Solving"
			dimensions = "Metacognitive, Procedural"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo1.addToContents(contentInstance)		
			lo5.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Constructing Algorithms"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()			
			lo1.addToContents(contentInstance)
			lo2.addToContents(contentInstance)
			lo3.addToContents(contentInstance)
			lo5.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Constructing Formal Code"
			dimensions = "Procedural, Factual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo1.addToContents(contentInstance)
			lo2.addToContents(contentInstance)
			lo3.addToContents(contentInstance)
			lo5.addToContents(contentInstance)
			lo6.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Variables"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo2.addToContents(contentInstance)



			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Software Development Environment"
			dimensions = "Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Setup and Configuration"
			dimensions = "Procedural"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo6.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Writing Code"
			dimensions = "Procedural, Factual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo6.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Executing Code"
			dimensions = "Procedural"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo6.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Errors (syntax, semantic, runtime)"
			dimensions = "Procedural, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo6.addToContents(contentInstance)



			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Program Structure and Flow"
			dimensions = "Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Sequence"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo1.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Selection"
			dimensions = "Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo1.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Iteration"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo1.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Critical"
			contentInstance.topicTitle = "Functions"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo3.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Modular Programming Techniques"
			dimensions = "Procedural, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo3.addToContents(contentInstance)




			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Composite Data Structures"
			dimensions = "Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Store and Manipulate Data"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			currentInstance = contentInstance
			lo2.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Lists"
			dimensions = "Procedural, Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo2.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Strings"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo2.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Tuples"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo2.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Good to Know"
			contentInstance.topicTitle = "Searching and Sorting"
			dimensions = "Procedural, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()



			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Good to Know"
			contentInstance.topicTitle = "Classes and Objects"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Good to Know"
			contentInstance.topicTitle = "Object"
			dimensions = "Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo3.addToContents(contentInstance)
			lo4.addToContents(contentInstance)

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Good to Know"
			contentInstance.topicTitle = "Types"
			dimensions = "Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()
			lo4.addToContents(contentInstance)




			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Application Domain - Problem Specification"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.save()
			currentInstance = contentInstance

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Introduction to Graphics and Drawing"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()

			contentInstance = new Content(imod: currentImod)
			contentInstance.preReq = "FALSE"
			contentInstance.priority = "Very Important"
			contentInstance.topicTitle = "Introduction to Animation"
			dimensions = "Factual, Conceptual"
			if (dimensions != ''){
				dimensions2 = dimensions.split(',').collect() {
					it.toUpperCase().trim() as KnowledgeDimensionEnum
				}
			}
			contentInstance.dimensions = dimensions2
			contentInstance.parentContent = currentInstance
			contentInstance.save()



        } // end sample


        // search for imods owned by current user
        def displayList = Imod.findAllWhere(owner: currentUser)
        [
            imodInstanceList: displayList,
            sort: 'name'
        ]
    }


	def create() {
		// get the current user
		def currentUser = ImodUser.findById(springSecurityService.currentUser.id)

		// create a new imod
		def newImod = new Imod(
			owner: currentUser,
			name: 'New Imod',
			url: 'example.com',
			subjectArea: 'sample',
			imodNumber: '1',
			//	gradingProcedure: '',
			attendance: 'Regular on-time attendance in this course is expected',
			classParticipation: 'Students are expected to participate in the educational process and not be a disruptive element with regard to the learning of others.',
			professionalConduct: 'All students should be familiar with the Student Code of Conduct, which can be found at http://www.asu.edu/studentlife/judicial/',
			missedExams: 'The only legitimate reasons for missing an exam are business or university related travel or illness for more than half the assignment period with appropriate documentation. Contact your instructor to make appropriate attangements',
			missedAssignments: 'Assignments should be turned by the specified deadline. Late assignments will not be accepted unless prior arrangements have been made with the instructor.'
		)

		// update current user
		currentUser.addToImods(newImod)

		// save new imod and the updated user to database
		currentUser.save()

		// redirect to editing new Imod
		redirect(
			controller: 'CourseOverview',
			action: 'index',
			id: newImod.id
		)
	}

	def save() {
		// get the correct owner id from Spring Security
		params.remove('owner')
		params.remove('owner.id')
		params.put(
			'owner.id',
			springSecurityService.currentUser.id
		)

		// FIXME unbundle variable assignment
		def currentImod = new Imod(params)
		if (!currentImod.save()) {
			render(
				view: 'create',
				model: [
					currentImod: currentImod
				]
			)
			return
		}

		flash.message = message(
			code: 'default.created.message',
			args: [
				message(
					code: 'imod.label',
					default: 'Imod'
				),
				currentImod
			]
		)
		redirect(
			action: 'list'
		)
	}

	def update(Long id, Long version) {
		def currentImod = Imod.get(id)
		if (!currentImod) {
			flash.message = message(
				code: 'default.not.found.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					currentImod
				]
			)
			redirect(
				action: 'list'
			)
			return
		}

		// FIXME handle a null imod seperate from handling versioning
		if (version != null) {
			if (currentImod.version > version) {
				currentImod.errors.rejectValue(
					'version',
					'default.optimistic.locking.failure',
					[
						message(
							code: 'imod.label',
							default: 'Imod'
						)
					] as Object[],
					'Another user has updated this Imod while you were editing'
				)
				render (
					view: 'edit',
					model: [
						currentImod: currentImod
					]
				)
				return
			}
		}

		// FIXME unbundle variable assignment
		currentImod.properties = params

		if (!currentImod.save()) {
			render(
				view: 'edit',
				model: [
					currentImod: currentImod
				]
			)
			return
		}

		flash.message = message (
			code: 'default.updated.message',
			args: [
				message (
					code: 'imod.label',
					default: 'Imod'
				),
				currentImod
			]
		)
		redirect(
			controller: 'courseOverview',
			action: 'index',
			id: currentImod.id
		)


		def schedule = Schedule.findById(currentImod.properties.get("scheduleId"))

		schedule.scheduleWeekDays = null

		params.each{

			if(it.key.contains("scheduleWeekDays_"))
			{
				if (it.value.contains("on")){
					if(schedule.scheduleWeekDays == null)
					{
						currentImod.schedule.addToScheduleWeekDays(ScheduleWeekDays.get((it.key - "scheduleWeekDays_") as Integer))
					}
					else
					{
						currentImod.schedule.scheduleWeekDays << ScheduleWeekDays.get((it.key - "scheduleWeekDays_") as Integer)

					}
					currentImod.schedule.save()
					currentImod.save()
				}
			}
		}
	}

	/*def sample(){
		


		// search for imods owned by current user
		def displayList = Imod.findAllWhere(owner: currentUser)
		[
			imodInstanceList: displayList,
			sort: 'name'
		]
	}*/

	def delete(Long id) {
		def currentImod = Imod.get(id)
		if (!currentImod) {
			flash.message = message(
				code: 'default.not.found.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					currentImod
				]
			)
			redirect(
				action: 'list'
			)
			return
		}

		try {
			// delete associated instructors
			def instructors = currentImod.instructors

			instructors.each{
				it.delete()
			}

			currentImod.delete()
			flash.message = message (
				code: 'default.deleted.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					currentImod
				]
			)
			redirect(
				action: 'list'
			)
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(
				code: 'default.not.deleted.message',
				args: [
					message(
						code: 'imod.label',
						default: 'Imod'
					),
					currentImod
				]
			)
			redirect(
				action: 'show',
				id: id
			)
		}
	}
}
