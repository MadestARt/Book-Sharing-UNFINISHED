<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2> ${requestScope.helloMessage}</h2>
    <h1> Список Авторов : </h1>
    <ul> <c:forEach var = "author" items="${requestScope.authors}">
        <li>
            ${author}<a href="/books?authorId=${author.id}">${author.name}</a>
        </li>
    </c:forEach>
    </ul>



</body>
</html>
