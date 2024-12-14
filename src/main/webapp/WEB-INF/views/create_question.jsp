<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание вопросов</title>
    <link rel="shortcut icon" href="<c:url value='../../img/logo_without_name_1.png'/>">
    <link rel="stylesheet" href="<c:url value='../../css/create_question.css'/>">
    <script src="<c:url value='../../js/create_question_validator.js'/>"></script>
</head>

<body>

<div class="create-container">
    <form action="<c:url value='/create-question'/>" method="post" enctype="multipart/form-data">
        <h2 class="header-create">Создание вопросов</h2>

        <h1 class="header-create">Вопрос ${number}</h1>

        <p>
            <textarea name="question_quiz" class="textarea" placeholder="Введите вопрос" maxlength="250"></textarea>
        </p>

        <p>
            <select name="question_type" class="question-category">
                <option value="text_question" class="option-category">Текстовый вопрос</option>
                <option value="music_question" class="option-category">Музыкальный вопрос</option>
                <option value="image_question" class="option-category">Вопрос с картинкой</option>
                <option value="video_question" class="option-category">Вопрос с видео</option>
            </select>
        </p>

        <p>
            <input class="media-file" type="file"
                   id="mediaFile" name="mediaFile"
                   accept="image/*,video/*,audio/*">
        </p>

        <p>
            <textarea name="right_answer" class="textarea" placeholder="Правильный ответ" maxlength="250"
                      required></textarea>
            <textarea name="wrong_answer1" class="textarea" placeholder="Неправильный ответ 1"
                      maxlength="250" required></textarea>
            <textarea name="wrong_answer2" class="textarea" placeholder="Неправильный ответ 2"
                      maxlength="250" required></textarea>
            <textarea name="wrong_answer3" class="textarea" placeholder="Неправильный ответ 3"
                      maxlength="250" required></textarea>
        </p>

        <p>
            <button name="add_question" class="create-quiz-btn" type="submit">Добавить вопрос</button>
            <button name="save_quiz" class="create-quiz-btn" type="submit">Сохранить квиз</button>
        </p>

    </form>
</div>

</body>
</html>