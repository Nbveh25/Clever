<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="shortcut icon" href="<c:url value='../img/logo_without_name_1.png'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/login.css'/>">
    <script src="<c:url value='/js/login_validator.js'/>"></script>
    <script src="<c:url value='/js/eye_pass.js'/>"></script>
</head>

<body>

<%
    String login = "";
    String pass = "";

    // Получаем все cookies
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            // Извлекаем логин и пароль из cookies
            if ("login".equals(cookie.getName())) {
                login = cookie.getValue();
            }
            if ("password".equals(cookie.getName())) { // Исправлено: имя cookie должно быть "password"
                pass = cookie.getValue();
            }
        }
    }
%>

<article class="container_login">

    <div class="block_login">
        <div class="block_item_login">
            <h2 class="block_item_title_login">У вас нет аккаунта?</h2>
            <form action="<c:url value='/register-jsp'/>">
                <button class="block_item_btn_login">Зарегистрироваться?</button>
            </form>
        </div>
    </div>

    <div class="form_box_login">
        <form action="<c:url value='/login-servlet'/>" method="post" class="form_login form_signin_login">
            <h3 class="form_title_login">Вход</h3>
            <p>
                <input id="login" name="login" type="text" placeholder="Логин" class="form_input_login"
                       value="<%= login %>" required>
            </p>
            <p class="password-container">
                <input id="password" name="password" type="password" placeholder="Пароль" class="form_input_login"
                       value="<%= pass %>" required>
                <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">👁️</button>
            </p>
            <p>
                <input type="checkbox" id="rememberMe" name="rememberMe">
                <label for="rememberMe">Запомнить меня</label>
            </p>
            <p>
                <button type="submit" class="form_btn_login">Войти</button>
            </p>
            <p>
                <a href="<c:url value='/forgot-pass-jsp'/>" class="form_forgot_login">Забыли пароль?</a>
            </p>
            <p>
            <div class="error_message" style="display: none; color: red;"></div>
            </p>
        </form>
    </div>

</article>

</body>

</html>