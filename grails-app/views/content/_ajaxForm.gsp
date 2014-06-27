<%@ page import="imodv6.Content" %>
<table id="addTopicPopupTable">
	<tr>
		<td>
			<g:message code="imod.topic.title.label"/>
		</td>
		<td>
			<g:textField name="topicTitle" required=""/>
		</td>
	</tr>
</table>

<div>
	<input type="submit" value="Save" class="save" style="padding: 0;"/>
	<input type="button" value="Cancel" onclick="closeAddTopicPopup()" style="padding: 0;"/>
</div>
