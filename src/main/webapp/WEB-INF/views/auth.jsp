<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Аутентификация</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>" />
    <link rel="stylesheet" href="<c:url value='../../css/auth.css'/>" />
    <script src="<c:url value='../../js/auth_validator.js'/>"></script>
</head>

<body>
    <form action="<c:url value="/auth-servlet"/>" method="post" class="auth_form">
        <h1 class="auth_form_h">Ещё щуть-щуть...</h1>
        <p>Мы отправили письмо с кодом на указанную почту</p>
        <input type="text" name="input_code" placeholder="Введите код" class="input_code" required>
        <button class="send_btn" type="submit">Отправить</button>
        <p>
            <div class="error_message" style="display: none; color: red;"></div>
        </p>
    </form>
</body>

</html>