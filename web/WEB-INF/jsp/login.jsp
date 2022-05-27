<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 27.05.2022
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post" enctype="application/x-www-form-urlencoded">
    <label for="emailId"> Почта:
    <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId"> Пароль:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <button type="submit">
        Войти в аккаунт
    </button>

</form>
</body>
</html>
