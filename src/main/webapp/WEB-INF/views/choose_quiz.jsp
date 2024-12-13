<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выбор квизов</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="<c:url value='../../css/choose_quiz.css'/>">
    <script src="<c:url value='../../js/choose_quiz.js'/>"></script>
    <script src="<c:url value='../../js/search_quiz.js'/>"></script>
    <script src="<c:url value='../../js/filter_quiz.js'/>"></script>
</head>
<body>
<div class="container">
    <header>
        <h1>Выберите квиз</h1>
    </header>

    <main>
        <div class="filter-container">
            <input type="text" name="" id="search-item" placeholder="Найти квиз" class="seach-item" onkeyup="search()">
            <select name="" class="filter-selector" id="filter-selector" onchange="filter()">
                <option value="" class="">Тип квиза</option>
                <option value="Наука" class="">Наука</option>
                <option value="Развлечение" class="">Развлечение</option>
                <option value="Тестирование" class="">Тестирование</option>
                <option value="Искусство" class="">Искусство</option>
            </select>
        </div>

        <div class="quiz-list" id="quiz-list">
            <!-- Здесь будут прогружаться квизы инде -->
        </div>
    </main>

    <footer>
        <p>&copy; 2024 Все права защищены.</p>
    </footer>
</div>
</body>
</html>