<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>Адаптивная форма регистрации и авторизации</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js" defer></script>
</head>

<body>
    <article class="container">
        <div class="block">

            <section class="block_item">
                <h2 class="block_item_title">У вас уже есть аккаунт?</h2>
                <button class="block_item_btn signin_btn" >Войти</button>
            </section>

            <section class="block_item">
                <h2 class="block_item_title">У вас нет аккаунта?</h2>
                <button class="block_item_btn signup_btn">Зарегистрироваться?</button>
            </section>
        </div>

        <div class="form_box">
            <form action="#" class="form form_signin">
                <h3 class="form_title">Вход</h3>
                <p>
                    <input type="text" placeholder="Логин" class="form_input">
                </p>
                <p>
                    <input type="password" placeholder="Пароль" class="form_input">
                </p>
                <p>
                    <button class="form_btn">Войти</button>
                </p>
                <p>
                    <a href="#" class="form_forgot">Забыли пароль?</a>
                </p>
            </form>

            <form action="#" class="form form_signup">
                <h3 class="form_title">Регистрация</h3>
                <p>
                    <input type="text" placeholder="Логин" class="form_input">
                </p>
                <p>
                    <input type="email" placeholder="Email" class="form_input">
                </p>
                <p>
                    <input type="password" placeholder="Пароль" class="form_input">
                </p>
                <p>
                    <input type="password" placeholder="Повторите пароль" class="form_input">
                </p>
                <p>
                    <button class="form_btn form_btn_signup">Зарегистрироваться</button>
                </p>
            </form>

        </div>

    </article>

</body>

</html>