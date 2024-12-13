<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Личный кабинет</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="../../css/personal_account.css">
    <script src="../../js/personal_account.js" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<header class="header">
    <img class="logo" src="../../img/logo_brain_1.png" alt="Логотип">
    <div class="header_link_container">
        <a class="header_link" href="<c:url value='/logout-servlet'/>">Выйти</a>
    </div>
</header>

<div id="user_container" class="user_container">
    <h2>Данные пользователя</h2>
    <div class="user_meta">
        <div class="img_and_upload">
            <img class="icon_of_user" alt="icon_of_user" src='<%= session.getAttribute("icon_url").toString() %>'>
            <!--<img class="icon_of_user" alt="icon_of_user" src="../img/ic_profile.png">-->
            <form action="<c:url value='/upload-icon-servlet'/>" method="post" enctype="multipart/form-data">
                <input type="file" accept="image/*" class="upload_btn" name="profilePic">
                <button type="submit" class="link_btn">Загрузить фото</button>
            </form>
        </div>
        <div>
            <div class="user_info">
                <h2 id="name_of_user" class="name_of_user"><%=session.getAttribute("login")%>
                </h2>
                <br>
                <h2 class="email_user"><%=session.getAttribute("email")%>
                </h2>
            </div>
        </div>
    </div>
</div>

<div class="setting_container">
    <h2>Настройки</h2>
    <button id="upgrade-permission" class="link_btn">Права PRO</button>
    <button id="change-login" class="link_btn">Изменить логин</button>
    <button id="delete-profile" class="link_btn">Удалить профиль</button>
</div>

</body>
</html>
