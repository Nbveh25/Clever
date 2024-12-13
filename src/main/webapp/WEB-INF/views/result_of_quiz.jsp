<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты квиза</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="<c:url value='../../css/result_of_quiz.css'/>">
    <script src="<c:url value='../../js/result_of_quiz.js'/>"></script>
</head>


<body>
<h2>Результаты квиза</h2>

<%-- Этот список нужно генерить --%>
<div class="result_container">
    <%--<div class="result_item">
        <img class="icon_of_user" src="../img/ana_de_armas.jpg" alt="icon">
        <h2 class="nickname">Nickname</h2>
        <h2 class="score">400</h2>
    </div>--%>
</div>

<form action="<c:url value='/quiz-servlet'/>" method="post">
    <div class="button-container">
        <button type="submit" class="button">Завершить квиз</button>
    </div>
</form>
</body>

</html>