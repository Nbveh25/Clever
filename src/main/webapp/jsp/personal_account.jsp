<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="<c:url value='/css/personal_account.css'/>">
    <link rel="shortcut icon" href="<c:url value='../img/logo_without_name_1.png'/>">
</head>
<body>
<header class="header">
    <img class="logo" src="<c:url value='../img/logo_brain_1.png'/>" alt="Логотип">
    <div class="header_link_container">
        <a class="header_link" href="<c:url value='/logout-servlet'/>">Выйти</a>
    </div>
</header>

<div id="user_container" class="user_container">
    <h2>Данные пользователя</h2>
    <div class="user_meta">
        <div class="img_and_upload">
            <img class="icon_of_user" alt="icon_of_user" src="<c:url value='../img/ana_de_armas.jpg'/>">
            <input type="file" accept="image/*" class="upload_btn">
        </div>
        <div>
            <div class="user_info">
                <p id="name_of_user" class="name_of_user"><%= session.getAttribute("login") %>
                </p>
            </div>
            <br>
            <div class="user_info">
                <h2 class="email_user"><%= session.getAttribute("email") %>
                </h2>
            </div>
        </div>
    </div>
</div>

<div class="setting_container">
    <h2>Настройки</h2>
    <a class="link_btn" href="<c:url value='/upgrade-permission-jsp'/>">Права модератора</a>
    <a class="link_btn" href="#">Изменить логин</a>
    <a class="link_btn" href="#">Удалить профиль</a>
</div>

</body>
</html>