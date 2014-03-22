<div class="fieldcontain ${hasErrors(bean: imodInstance, field: 'contents', 'error')} "
     style="margin-left:-20px;">
    <label for="contents" style="width: 63%;">
        <g:message code="imod.contents.label"/>
    </label>
    <g:select name="contents" from="${currentChapterContentList}" multiple="multiple" optionKey="id" size="5"
              value="" class="many-to-many"
              style="width:150px;margin-left:-110px;"/>
    <div>
    <g:submitButton name="deleteTopic" value="Remove" onclick="removeTopic()"/>
    <g:actionSubmit value="Cancel" onclick="closeRemoveTopicPopup()"/>
    </div>
</div>