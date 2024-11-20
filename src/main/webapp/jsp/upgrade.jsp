<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Улучшить до версии ПРО</title>
    <link rel="stylesheet" href="../css/upgrade.css">
    <link rel="shortcut icon" href="../img/logo_without_name_1.png">
</head>
<body>
<header class="header">
    <img class="logo" src="../img/logo_brain_1.png" alt="Логотип">
</header>

<div class="container">
    <h1>Улучшить до версии ПРО</h1>
    <p class="description">Станьте пользователем версии ПРО и получите доступ к эксклюзивным функциям, включая создание собственных квизов!</p>

    <div class="upgrade_info">
        <h2>Преимущества версии ПРО:</h2>
        <ul>
            <li>Создание неограниченного количества квизов</li>
            <li>Доступ к расширенной аналитике</li>
            <li>Персонализированные темы и стили</li>
            <li>Поддержка при создании квизов</li>
        </ul>
    </div>

    <form method="post" action="<c:url value="/upgrade-servlet"/>">
        <div class="upgrade_button_container">
            <button class="upgrade_btn">Улучшить до версии ПРО</button>
        </div>
    </form>

</div>

</body>
</html>