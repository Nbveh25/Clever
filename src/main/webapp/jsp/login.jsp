<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="shortcut icon" href="../img/logo_without_name_1.png" />
    <link rel="stylesheet" href="../css/login.css">
</head>

<body>
    <article class="container_login">

        <div class="block_login">

            <div class="block_item_login">
                <h2 class="block_item_title_login">У вас нет аккаунта?</h2>
                <form action="/register-jsp">
                    <button class="block_item_btn_login">Зарегистрироваться?</button>
                </form>
            </div>

        </div>

        <div class="form_box_login">

            <form action="/login-servlet" method="post" class="form_login form_signin_login">
                <h3 class="form_title_login">Вход</h3>
                <p>
                    <input name="login" type="text" placeholder="Логин" class="form_input_login" required>
                </p>
                <p>
                    <input name="password" type="password" placeholder="Пароль" class="form_input_login" required>
                </p>
                <p>
                    <button type="submit" class="form_btn_login">Войти</button>
                </p>
                <p>
                    <a href="/forgot-pass-jsp" class="form_forgot_login">Забыли пароль?</a>
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