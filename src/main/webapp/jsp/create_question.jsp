<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание вопросов</title>
    <link rel="stylesheet" href="../css/create_question.css">
    <link rel="shortcut icon" href="../img/logo_without_name_1.png">
    <script src="../js/number_of_question.js"></script>
</head>

<body>

<div class="create-container">
    <form action="/create-question-servlet" method="post" enctype="multipart/form-data">
        <h2 class="header-create">Создание вопросов</h2>

        <p id="question-number" class="number-of-question">Номер вопроса: 0</p>

        <p>
            <textarea name="question_quiz" class="textarea" placeholder="Введите вопрос"></textarea>
        </p>

        <p>
            <select name="quiz_type" class="question-category">
                <option value="text_question" class="option-category">Текстовый вопрос</option>
                <option value="music_question" class="option-category">Музыкальный вопрос</option>
                <option value="image_question" class="option-category">Вопрос с картинкой</option>
                <option value="video_question" class="option-category">Вопрос с видео</option>
            </select>
        </p>

        <p>
            <input class="media-file" type="file" id="mediaFile" name="mediaFile" accept="image/*,video/*,audio/*">
        </p>

        <p>
            <textarea name="right_answer" class="textarea" placeholder="Правильный ответ"></textarea>
            <textarea name="wrong_answer1" class="textarea" placeholder="Неправильный ответ 1"></textarea>
            <textarea name="wrong_answer2" class="textarea" placeholder="Неправильный ответ 2"></textarea>
            <textarea name="wrong_answer3" class="textarea" placeholder="Неправильный ответ 3"></textarea>
        </p>

        <p>
            <button name="add_question" class="create-quiz-btn" type="submit">Добавить вопрос</button>
            <button name="save_quiz" class="create-quiz-btn" type="submit">Сохранить квиз</button>
        </p>

    </form>
</div>

</body>
</html>
