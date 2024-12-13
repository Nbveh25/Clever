<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание квиза</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="../../css/create_quiz.css">
    <script src="../../js/create_quiz_validator.js"></script>
</head>

<body>

<div class="create-container">
    <form action="<c:url value='/create-quiz-servlet'/>" method="post" enctype="multipart/form-data"
          id="create-quiz-form">

        <h2 class="header-create">Создание квиза</h2>

        <p>
            <input id="name_quiz" name="name_quiz" class="input-text" type="text" placeholder="Название квиза"
                   maxlength="50" required>
        </p>

        <p>
            <textarea id="description_quiz" name="description_quiz" class="textarea" placeholder="Описание квиза"
                      maxlength="50"></textarea>
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
            <label for="quizIcon" class="label_icon">Иконка квиза:</label>
            <input class="media-file" type="file" id="quizIcon" name="quiz_icon" accept="image/*">
        </p>

        <p>
            <button class="create-quiz-btn" type="submit">Создать квиз</button>
        </p>
        <p>
        <div class="error_message" style="display: none; color: red;"></div>
        </p>
    </form>

</div>

</body>
</html>