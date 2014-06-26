<script src="${resource(dir: 'js/source', file: 'content.contentTab.gsp.js')}" defer></script>
<link href="${resource(dir: 'css/source', file: 'content.contentTab.gsp.css')}" rel="stylesheet">
<div id="edit-imod" class="content scaffold-edit" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${imodInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${imodInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <table>
        <tr>
            <td></td>
            <td>
                <div></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="vertical-align: top;padding: 0;">
                <g:render template="/content/chapterAdditionWidget"/>
            </td>

            <td style="vertical-align: top;padding: 0;">
                <g:render template="/content/otherWidget"/>
            </td>
        </tr>
    </table>
</div>
