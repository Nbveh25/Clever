<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 09.10.2024
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выбор квизов</title>
    <link rel="stylesheet" href="../css/choose_quiz.css">
    <link rel="shortcut icon" href="../img/logo_without_name_1.png">
    <script src="../js/choose_quiz.js"></script>
    <script src="../js/search_quiz.js"></script>
    <script src="../js/filter_quiz.js"></script>
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
            <!-- Quizzes will be loaded here by JavaScript -->
        </div>
    </main>

    <footer>
        <p>&copy; 2024 Все права защищены.</p>
    </footer>
</div>
</body>
</html>
