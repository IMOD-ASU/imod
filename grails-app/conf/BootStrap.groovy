import imodv6.ContentKnowledgeDomainCode
import imodv6.ContentPriorityCode
import imodv6.ContentResourceType

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
    }
    def destroy = {
    }
}
