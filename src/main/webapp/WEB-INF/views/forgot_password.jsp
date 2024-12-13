<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Восстановление доступа</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="../../css/forgot_password.css">
    <script src="../../js/forgot_password_validator.js"></script>
</head>

<body>
<form action="<c:url value='/forgot-pass-servlet'/>" method="post" class="forgot_pass_form">
    <h1 class="forgot_pass_form_h">Восстановление доступа</h1>
    <input type="email" name="email" placeholder="Введите email" class="input_code" required>
    <input type="password" name="password" placeholder="Новый пароль" class="input_code" required>
    <input type="password" name="repassword" placeholder="Повторите пароль" class="input_code" required>
    <button class="send_btn" type="submit">Отправить</button>
    <p>
    <div class="error_message" style="display: none; color: red;"></div>
    </p>
</form>
</body>

</html>