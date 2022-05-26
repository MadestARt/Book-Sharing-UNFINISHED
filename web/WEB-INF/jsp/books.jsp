<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 21.05.2022
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>
        <c:choose>
            <c:when test="${not empty requestScope.booksByAuthor}">
                <ul>
                    <c:forEach var = "bookDto" items="${requestScope.booksByAuthor}" >
                        <li>
                                ${bookDto.name} Дата выхода :${bookDto.yearOfPublish} Число страниц : ${bookDto.pageCount}
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${empty requestScope.booksByAuthor}">
                Книг данного автора нет :(
            </c:when>
        </c:choose>

    </h1>
</body>
</html>
