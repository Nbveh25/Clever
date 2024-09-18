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
    <article class="container">

        <div class="block">

            <div class="block_item">
                <h2 class="block_item_title">У вас нет аккаунта?</h2>
                <form action="register.jsp">
                    <button class="block_item_btn">Зарегистрироваться?</button>
                </form>
            </div>

        </div>

        <div class="form_box">

            <form action="#" class="form form_signin">
                <h3 class="form_title">Вход</h3>
                <p>
                    <input type="text" placeholder="Логин" class="form_input" required>
                </p>
                <p>
                    <input type="password" placeholder="Пароль" class="form_input" required>
                </p>
                <p>
                    <button class="form_btn">Войти</button>
                </p>
                <p>
                    <a href="#" class="form_forgot">Забыли пароль?</a>
                </p>
            </form>

        </div>

    </article>

</body>

</html>