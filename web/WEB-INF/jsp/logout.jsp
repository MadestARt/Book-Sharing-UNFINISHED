
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <form action="/logout" method="post">
        <button type="submit">
            Выйти из аккаунта
        </button>
    </form>
</c:if>
</body>
</html>
