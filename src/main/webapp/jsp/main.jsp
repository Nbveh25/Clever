<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clever</title>
    <link rel="shortcut icon" href="../img/logo_without_name_1.png">
    <link rel="stylesheet" href="../css/main.css">
</head>

<header class="header">
    <img class="logo" src="../img/logo_brain_1.png">
    <div class="header_link_container">
        <a class="header_link" href="<c:url value="/create-quiz-jsp"/>">Создать квиз</a>
        <a class="header_link" href="<c:url value="/personal-account-jsp"/>">Профиль</a>
        <a class="header_link" href="<c:url value="/logout-servlet"/>">Выйти</a>
    </div>
</header>

<body>

<div class="container">
    <h1>Провести квиз</h1>
    <img class="main_img" src="../img/create_quiz.png">
    <div class="button-container">
        <a href="<c:url value="/jsp/choose_quiz.jsp"/>" class="button">Создать игру</a>
    </div>
</div>

<div class="container">
    <h1>Играть с друзьями</h1>
    <img class="main_img" src="../img/connect_to_quiz.png"><br>
    <form action="<c:url value="/main-servlet"/>" method="POST">
        <input name="input_code" class="input_code" type="text" placeholder="Введите код">
        <div class="button-container">
            <button type="submit" class="button">Подключиться к игре</button>
        </div>
    </form>
</div>

</body>

</html>
<!--
Illustration by <a href="https://icons8.com/illustrations/author/lZpGtGw5182N">Elisabet Guba</a> from <a href="https://icons8.com/illustrations">Ouch!</a>-->
