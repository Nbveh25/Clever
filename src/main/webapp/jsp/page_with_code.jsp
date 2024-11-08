<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Код игры</title>
    <link rel="shortcut icon" href="../img/logo_without_name_1.png" />
    <link rel="stylesheet" href="../css/page_with_code.css">
</head>
<body>
    <div class="code_container">
        <h2 class="title">Код игры</h2>
        <h3 class="code"> <%= request.getAttribute("code")%> </h3>
        <form action="#" method="post">
            <button type="submit" class="start_btn">Старт</button>
        </form>
    </div>

    <!--Этот список нужно генерить-->
    <div class="list_of_users">
        <!--<div class="user">
            <img class="icon_of_user" src="../img/ana_de_armas.jpg" alt="icon">
            <h2 class="nickname">Nickname</h2>
        </div>-->
    </div>

</body>
</html>
