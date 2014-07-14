package imodv6

/**
 * Help allows properly structured title attributes in the views to enter content into the Help Tooltip
 * @param tabId the name of the tab that this tip will be found on
 * @param tabFieldId identifier that will link this tip directly to the html element being hovered over
 * @param text description of what the element being hovered over does
 * @param tabFieldUiId TODO what is this?
 */
class Help {
    String tabId
    Integer tabFieldId
    String tabFieldName
    String text
    String tabFieldUiId

    static constraints = {
    }

    static mapping = {
        version false
    }

    static String toolTip(String tabId, String tabFieldUiId) {
		return Help.findByTabIdAndTabFieldUiId(tabId, tabFieldUiId)?.text
    }

    def String toString() {
        return text //TBD
    }
}
