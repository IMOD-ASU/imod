<%-- <g:render template="/content/contentTabScript"/> --%>
<div id="edit-imod" class="content scaffold-edit" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${imodInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${imodInstance}" var="error">
                <li
                    <g:if test="${error in org.springframework.validation.FieldError}">
                        data-field-id="${error.field}"
                    </g:if>
                >
                    <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <table>

        <tr>
            <td style="vertical-align: top;padding:-50;">
                <%--<g:render template="/pedagogy/pedagogyLearningObjectiveTemplate"/>--%>
            </td>

            <td style="vertical-align: top;padding:-50;">
                <%--<g:render template="/pedagogy/pedagogyBodyTemplate"/>--%>
            </td>
        </tr>
    </table>
</div>
