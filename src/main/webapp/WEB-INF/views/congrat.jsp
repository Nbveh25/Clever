<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Успех!</title>
    <link rel="shortcut icon" href="<c:url value="/main/webapp/img/logo_without_name_1.png"/>"/>
    <link rel="stylesheet" href="../../css/congrat.css"/>
</head>
<body>
<div class="congrat">
    <h1>Регистрация завершена!</h1>
    <h3>Ваш адрес электронной почты подтвержден.</h3>
    <h3>Для входа на сайт используйте логин и пароль указанные при регистрации.</h3>
    <a href="<c:url value="/login-servlet"/>">Перейти на страницу входа</a>
</div>
<img src="<c:url value="/main/webapp/img/congrats_img.jpg"/>"/>
</body>
</html>