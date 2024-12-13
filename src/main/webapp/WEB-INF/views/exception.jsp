<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
    <link rel="shortcut icon" href="<c:url value='/main/webapp/img/logo_without_name_1.png'/>"/>
    <link rel="stylesheet" href="../../css/exception.css"/>
</head>

<body>
<h1 class="title_1">ОШИБКА</h1>
<strong>Request URI: ${requestScope.uri}</strong><br>
<strong>Status code: ${requestScope.statusCode}</strong><br>
<c:if test="${not empty requestScope.message}">
    <strong>${requestScope.message}</strong>
</c:if>
<div class="gifs">
    <img class="bully" src="https://res.cloudinary.com/dsrqq4er2/image/upload/v1733831258/bully2_jvf3lf.gif"
         alt="gif_1"/>
    <img class="travolta" src="https://res.cloudinary.com/dsrqq4er2/image/upload/v1733831343/travolta_fwufkd.gif"
         alt="gif_2"/>
    <img class="timur" src="https://res.cloudinary.com/dsrqq4er2/image/upload/v1733831369/timur_vca2hp.gif"
         alt="gif_3"/>
</div>
</body>
</html>