<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ожидание начала квиза</title>
    <link rel="shortcut icon" href="<c:url value='/main/webapp/img/logo_without_name_1.png'/>"/>
    <link rel="stylesheet" href="<c:url value='../../css/waiting_of_quiz.css'/>"/>
    <script src="<c:url value='../../js/quiz_websocket_listener.js'/>"></script>
</head>

<body>
<div class="container">
    <h1>Ожидание начала квиза</h1>
    <img src="<c:url value='../../gif/emoji-thinking.gif'/>" alt="Thinking Emoji">
</div>

</body>
</html>
