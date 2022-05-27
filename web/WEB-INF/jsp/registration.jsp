<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="/registration" method="post" enctype="multipart/form-data">
        <label for="loginId"> Имя:
            <input type="text" name="name" id="loginId">
        </label><br>
        <label for="emailId"> Электронная почта:
            <input type="text" name="email" id="emailId">
        </label><br>
        <label for="passwordId"> Пароль:
            <input type="password" name="password" id="passwordId">
        </label><br>
        <label for="fileId"> Аватар:
            <input type="file" name="avatar" id="fileId">
        </label><br>
        <label for="birthdayId"> Дата рождения:
            <input type="date" name="birthday" id="birthdayId">
        </label><br>
        <input type="radio" name="gender" value="MALE"> Male
        <br>
        <input type="radio" name="gender" value="FEMALE"> Female
        <br>
        <button type="submit">
            Зарегистрироваться
        </button>
        <c:if test="${not empty requestScope.errors}">
            <div>
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>
                        ${error}
                    </span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </form>

</body>
</html>
