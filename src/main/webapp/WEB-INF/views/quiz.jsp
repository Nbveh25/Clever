<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квиз</title>
    <link rel="stylesheet" href="<c:url value='../../css/quiz.css'/>">
    <link rel="shortcut icon" href="<c:url value='/main/webapp/img/logo_without_name_1.png'/>"/>
    <script src="<c:url value='../../js/quiz.js'/>"></script>
</head>


<body>
<div class="question_container" id="question_container">
    <%-- Здесь будет генериться вопрос --%>
</div>
<div class="timer">15</div>

</body>

</html>