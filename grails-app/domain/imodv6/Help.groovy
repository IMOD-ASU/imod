package imodv6

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
