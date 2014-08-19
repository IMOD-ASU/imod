package imodv6

/**
 * Help allows properly structured title attributes in the views to enter content into the Help Tooltip
 */
class Help {
	/**
	 * description of what the element being hovered over does
	 */
	String text

	/**
	 * the name of the tab that this tip will be found on
	 * TODO: Do there really need to be this many identifying features?
	 */
	String tabId

	/**
	 * identifier that will link this tip directly to the html element being hovered over
	 * TODO: Do there really need to be this many identifying features?
	 */
	Integer tabFieldId

	/**
	 * identifier for the field that the Tool Tip will appear over
	 * TODO: Do there really need to be this many identifying features?
	 */
	String tabFieldUiId

	/**
	 * TODO: What is this?
	 * TODO: Do there really need to be this many identifying features?
	 */
	String tabFieldName

	/**
	 * Help is not versioned
	 */
	static mapping = {
		version false
	}

	/**
	 * This takes in information about a tool tip and give the help text
	 * @param  tabId        Which tab is requesting tool tip
	 * @param  tabFieldUiId Which field is being hovered over
	 * @return              Tool Tip Text
	 */
	static String toolTip(String tabId, String tabFieldUiId) {
		return Help.findByTabIdAndTabFieldUiId(tabId, tabFieldUiId)?.text
	}

	/**
	 * printing the Help will give the tool tip text
	 * @return tool tip text
	 */
	def String toString() {
		return text
	}
}
