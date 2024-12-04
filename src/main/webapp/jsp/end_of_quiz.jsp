<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Аутентификация</title>
    <link rel="shortcut icon" href="<c:url value='../img/logo_without_name_1.png'/>" />
    <link rel="stylesheet" href="<c:url value='/css/end_of_quiz.css'/>">
</head>

<body>
<div class="container">
    <h1>Квиз завершен</h1>
    <p>Спасибо за участие! Вы можете вернуться на главную страницу.</p>
    <a href="<c:url value='/main-jsp'/>">Вернуться на главную страницу</a>
</div>
</body>

</html>