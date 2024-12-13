<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_2.png'/>"/>
    <link rel="stylesheet" href="<c:url value='../../css/register.css'/>">
    <script src="<c:url value='../../js/register_validator.js'/>"></script>
    <script src="<c:url value='/main/webapp/js/eye_pass.js'/>"></script>
</head>


<body>

<article class="container">

    <div class="block">
        <div class="block_item">
            <h2 class="block_item_title">У вас уже есть аккаунт?</h2>
            <form action="<c:url value='/login-servlet'/>">
                <button class="block_item_btn">Войти</button>
            </form>
        </div>
    </div>

    <div class="form_box">
        <form action="<c:url value='/register-servlet'/>" method="post" class="form">
            <h3 class="form_title">Регистрация</h3>
            <p>
                <input id="login" type="text" name="login" placeholder="Логин" class="form_input" required>
            </p>
            <p>
                <input id="email" type="email" name="email" placeholder="Email" class="form_input" required>
            </p>
            <p class="password-container">
                <input id="password" type="password" name="password" placeholder="Пароль" class="form_input" required>
                <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">👁️</button>
            </p>
            <p class="password-container">
                <input id="repassword" type="password" name="repassword" placeholder="Повторите пароль"
                       class="form_input" required>
                <button type="button" class="toggle-password" onclick="togglePasswordVisibility('repassword')">👁️
                </button>
            </p>
            <p>
                <button type="submit" class="form_btn">Зарегистрироваться</button>
            </p>
            <p>
            <div class="error_message" style="display: none; color: red;"></div>
            </p>
        </form>

    </div>

</article>

</body>

</html>