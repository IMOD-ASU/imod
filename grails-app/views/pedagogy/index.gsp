<%@ page import="imodv6.Help" %>
<html>
    <head>
        <title>
            Learning Objective - Performance
        </title>

        <meta name="layout" content="imod">
        <script src="${resource(dir: 'js/source', file: 'pedagogy.pedagogyObjectiveTemplate.gsp.js')}" defer></script>
    </head>
    <body>
        <div id="edit-imod" class="content scaffold-edit" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">
                    ${flash.message}
                </div>
            </g:if>

            <g:hasErrors bean="${currentImod}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${currentImod}" var="error">
                        <li>
                            <g:message error="${error}"/>
                        </li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <table>
                <tr>
                    <td style="vertical-align: top;padding:-50;">
                        <g:render template="/pedagogy/pedagogyLearningObjectiveTemplate"/>
                    </td>

                    <td style="vertical-align: top;padding:-50;">
                        <%-- <g:render template="/pedagogy/pedagogyBodyTemplate"/> --%>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
