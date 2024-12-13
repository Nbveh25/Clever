<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clever</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="../../js/connect_to_game_form_validator.js"></script>
</head>

<header class="header">
    <img class="logo" src="<c:url value='../../img/logo_brain_1.png'/>" alt="logo">
    <div class="header_link_container">
        <a class="header_link" href="<c:url value='/create-quiz-servlet'/>">Создать квиз</a>
        <a class="header_link" href="<c:url value='/user-servlet'/>">Профиль</a>
        <a class="header_link" href="<c:url value='/logout-servlet'/>">Выйти</a>
    </div>
</header>

<body>

<div class="container">
    <h1>Провести квиз</h1>
    <img class="main_img" src="<c:url value='../../img/flying_man_quiz.png'/>" alt="flying_man">
    <div class="button-container">
        <a href="<c:url value="/start-game-servlet"/>" class="button">Создать игру</a>
    </div>
</div>

<div class="container">
    <h1>Играть с друзьями</h1>
    <img class="main_img" src="<c:url value='../../img/connect_to_quiz.png'/>" alt="conversation_mens"><br>
    <form action="<c:url value='/main-servlet'/>" method="POST" id="connect-to-game-form">
        <input name="input_code" class="input_code" type="number" placeholder="Введите код">
        <div class="button-container">
            <button type="submit" class="button">Подключиться к игре</button>
        </div>
        <p>
        <div class="error_message" style="display: none; color: red;"></div>
        </p>
    </form>
</div>

</body>

</html>
<!--
Illustration by <a href="https://icons8.com/illustrations/author/lZpGtGw5182N">Elisabet Guba</a> from <a href="https://icons8.com/illustrations">Ouch!</a>-->