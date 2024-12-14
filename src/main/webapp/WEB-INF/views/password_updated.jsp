<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Успех!</title>
    <link rel="shortcut icon" href="<c:url value='/main/webapp/img/logo_without_name_1.png'/>"/>
    <link rel="stylesheet" href="<c:url value='../../css/congrat.css'/>"/>
</head>

<body>
<div class="congrat">
    <h1>Пароль изменен!</h1>
    <h3>Для входа на сайт используйте логин и новый пароль.</h3>
    <a href="<c:url value="/login"/>">Перейти на страницу входа</a>
</div>
<img src="<c:url value="/main/webapp/img/congrats_img.jpg"/>"/>


</body>
</html>