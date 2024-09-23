<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="shortcut icon" href="../img/logo_without_name_2.png" />
    <link rel="stylesheet" href="../css/register.css">
</head>

<body>

<article class="container">

    <div class="block">
        <div class="block_item">
            <h2 class="block_item_title">У вас уже есть аккаунт?</h2>
            <form action="/login-jsp">
                <button class="block_item_btn">Войти</button>
            </form>
        </div>
    </div>

    <div class="form_box">
        <form action="/register-servlet" method="post" class="form">
            <h3 class="form_title">Регистрация</h3>
            <p>
                <input type="text" name="login" placeholder="Логин" class="form_input" required>
            </p>
            <p>
                <input type="email" name="email" placeholder="Email" class="form_input" required>
            </p>
            <p>
                <input type="password" name="password" placeholder="Пароль" class="form_input" required>
            </p>
            <p>
                <input type="password" name="repassword" placeholder="Повторите пароль" class="form_input" required>
            </p>
            <p>
                <button type="submit" class="form_btn">Зарегистрироваться</button>
            </p>
            <p>
                <c:if test="${not empty errorMessage}">
                    <div class="error_message">
                        <c:out value="${errorMessage}"/>
                    </div>
                </c:if>
            </p>
        </form>

    </div>

</article>

</body>

</html>