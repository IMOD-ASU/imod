<g:if test="${currentChapterContentList}">
    <div id="treecontrol" style="display: block;">
        <a title="Collapse the entire tree below" href="#"><img
                src="${resource(dir: 'images', file: 'minus.gif')}"> <g:message
                code="imod.topic.tree.hierarchy.collapse.all.link.label"/></a>
        <a title="Expand the entire tree below" href="#"><img
                src="${resource(dir: 'images', file: 'plus.gif')}"> <g:message
                code="imod.topic.tree.hierarchy.expand.all.link.label"/></a>
    </div>
    <ul id="topicTreeView" class="filetree treeview" style="max-height: 176px;min-height: 176px;overflow-y: scroll;">
        <li class="${!(chapterCount) ? 'last' : ''}"><span
                class="folder">${chapter?.name}</span>
            <ul style="display: none">
                <g:each in="${currentChapterContentList}" var="content" status="contentCount">
                    <li class="${(contentCount == (currentChapterContentList ? currentChapterContentList?.size() - 1 : '')) ? 'last' : ''}"><span
                            class="folder">${content?.topicTitle}</span></li>
                </g:each>
            </ul>
        </li>
    </ul>
</g:if>
<g:else>
    <div class="treeEmptyMessage"><g:message code="imod.topicTreeHierarchy.empty.message" /></div>
</g:else>