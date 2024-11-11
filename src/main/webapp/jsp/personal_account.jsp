<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Личный кабинет</title>
  <link rel="stylesheet" href="../css/personal_account.css">
  <link rel="shortcut icon" href="../img/logo_without_name_1.png">
</head>
<body>
<header class="header">
  <img class="logo" src="../img/logo_brain_1.png">
  <div class="header_link_container">
    <a class="header_link" href="" >Выйти</a>
  </div>
</header>

<div id="user_container" class="user_container">

  <h2>Данные пользователя</h2>

  <div class="user_meta">
    <div class="img_and_upload">
      <img class="icon_of_user" alt="icon_of_user" src="../img/ana_de_armas.jpg">
      <input type="file" accept="image/*">
    </div>

    <div>
      <div class="user_info">
        <p id="name_of_user" class="name_of_user">Ana de Armas</p>
      </div>
      <div class="user_info">
        <h2 class="email_user">anadearmas@internet.ru</h2>
      </div>
    </div>
  </div>

</div>

<div class="setting_container">
  <h2>Настройки</h2>

  <a class="link_btn" href="#">Права модератора</a>
  <a class="link_btn" href="#">Изменить логин</a>
  <a class="link_btn" href="#">Изменить пароль</a>
  <a class="link_btn" href="#">Удалить профиль</a>

</div>

</body>
</html>