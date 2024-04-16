<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>

    <head>
        <title>Brackets</title>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script>
            $(document).on("click", "#addStringButton", function () {
                var insertedString = document.getElementById('newStringInput').value;
                $("#stringsTable").find('tbody')
                    .append($('<tr>')
                        .append($('<td name="originalString">')
                            .append($('<p>')
                                .text(insertedString)
                            )
                        )
                        .append($(' <input path="stringsPair[${status.index}].originalString" type="hidden" name="stringsList"  value=\'' + insertedString + '\' > '))
                        .append($('<td>'))
                    );

            });


            $(document).on("click", "#clearTableButton", function () {
                $("#stringsTable tr").remove();
            });
        </script>
    </head>

    <body>
        <h1 class="page-title">Brackets cleaner</h1>
        <div class="input-bar">
            <label> Add a new string </label>
            <input id="newStringInput" name="newStringInput" type="text" />
            <button class="button-enter" id="addStringButton">+</button>
        </div>
        <form id="tableFormId" method="POST" modelAttribute="stringsList"
            action="${pageContext.request.contextPath}/brackets">
            <table id="stringsTable">
                <thead>
                    <tr>
                        <th>Original Strings</th>
                        <th>Clean Strings</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stringsList.stringsPairs}" var="stringsPair" varStatus="status">
                        <tr>
                            <td name="originalString">${stringsPair.originalString}</td>
                            <input path="stringsPair[${status.index}].originalString" type="hidden" name="stringsList"
                                value="${stringsPair.originalString}" />
                            <td>${stringsPair.cleanString}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="btn-group">
                <button id="cleanButton" class="button-submit" type="submit" class="btn btn-primary" name="clean"
                    value="Clean Button">Clean
                    Strings</button>
                <button id="downloadButton" class="button-submit" type="submit" class="btn btn-primary" name="download"
                    value="Clean Button">Download Pdf</button>
            </div>

            <!--    <button id="clearTableButton">Clear</button>-->

        </form>
        <c:if test="${downloadLink!=null}">
            <div class="input-bar">
                <p> Pdf output will be available at link <a href=${downloadLink}>Download link</a> </p>
            </div>
        </c:if>

    </body>