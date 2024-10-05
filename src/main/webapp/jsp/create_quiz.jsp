<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание квиза</title>
    <link rel="stylesheet" href="../css/create_quiz.css">
    <link rel="shortcut icon" href="../img/logo_without_name_1.png">
</head>

<body>

<div class="create-container">
    <form action="/create-quiz-servlet" method="post">
        <p>
            <h2 class="header-create">Создание квиза</h2>
        </p>
        <p>
            <input name="name_quiz" class="input-text" type="text" placeholder="Название квиза" required>
        </p>
        <p>
            <textarea name="description_quiz" class="textarea" placeholder="Описание квиза"></textarea>
        </p>
        <p>
            <select name="quiz_type" class="quiz-category">
                <option class="option-category">Наука</option>
                <option class="option-category">Развлечение</option>
                <option class="option-category">Тестирование</option>
                <option class="option-category">Искусство</option>
            </select>
        </p>
        <p>
            <button class="create-quiz-btn" type="submit">Создать квиз</button>
        </p>
    </form>

</div>

</body>
</html>