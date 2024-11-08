document.addEventListener("DOMContentLoaded", function() {
    let currentQuestionIndex = 0;
    let questions = [];
    let timeLeft = 15;
    let timerInterval;

    function loadQuestions() {
        fetch('/quiz-start-servlet')
            .then(response => response.json())
            .then(data => {
                questions = data;
                displayQuestion(currentQuestionIndex);
                startTimer();
            })
            .catch(error => {
                console.error("Ошибка при получении вопросов: ", error);
            });
    }

    function displayQuestion(index) {
        const container = document.getElementById('question_container');
        container.innerHTML = '';

        if (index < questions.length) {
            const question = questions[index];
            let questionHtml = `
                <div class="question_container">
                    <h2 class="number_of_question">Вопрос №${index + 1}</h2>
                    <h3>${question.question}</h3>
            `;

            // Добавление медиа-контента
            if (question.typeQuestion === 'music_question') {
                questionHtml += `
                    <audio controls class="music_bar" autoplay="autoplay">
                        <source src="${question.mediaPath}" type="audio/mpeg">
                        Ваш браузер не поддерживает элемент audio.
                    </audio>
                `;
            } else if (question.typeQuestion === 'image_question') {
                questionHtml += `
                    <img src="${question.mediaPath}" alt="question_image" class="question_image">
                `;
            } else if (question.typeQuestion === 'video_question') {
                questionHtml += `
                    <video width="320" height="240" controls class="question_video" autoplay="autoplay">
                        <source src="${question.mediaPath}" type="video/mp4">
                        Ваш браузер не поддерживает элемент video.
                    </video>
                `;
            }

            questionHtml += `<div class="button_container">`;
            container.insertAdjacentHTML('beforeend', questionHtml);

            // Получение ответов для текущего вопроса
            fetch(`/get-answers-servlet?questionId=${question.id}`)
                .then(response => response.json())
                .then(answers => {
                    const buttonContainer = document.querySelector('.button_container'); // Получаем контейнер кнопок
                    answers.forEach(answer => {
                        const answerButton = document.createElement('button');
                        answerButton.className = 'button_answer';
                        answerButton.setAttribute('data-answer', answer.id);
                        answerButton.setAttribute('data-question', question.id);
                        answerButton.textContent = answer.answer;

                        answerButton.addEventListener('click', function() {
                            // Отключаем все кнопки после нажатия
                            const allButtons = buttonContainer.querySelectorAll('button');
                            allButtons.forEach(btn => btn.disabled = true); // Блокируем все кнопки
                            allButtons.forEach(btn => btn.hidden = true); // Делаем невидимыми все кнопки

                            // Отправляем ответ
                            submitAnswer(answer.id, question.id, answer.answer);
                        });

                        buttonContainer.appendChild(answerButton); // Добавляем кнопку в контейнер
                    });
                })
                .catch(error => {
                    console.error("Ошибка при получении ответов: ", error);
                });

        }
    }

    function submitAnswer(answerId, questionId, answerText) {
        const data = new URLSearchParams();
        data.append('answerId', answerId);
        data.append('questionId', questionId);
        data.append('answerText', answerText);

        fetch(`/submit-answer-servlet`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data.toString()

        })
            .then(response => response.json())
            .then(data => {
                console.log("Ответ успешно отправлен");
            })
            .catch(error => {
                console.error("Ошибка при отправке ответа: ", error);
            });
    }

    function startTimer() {
        timeLeft = 15;
        updateTimerDisplay();

        clearInterval(timerInterval);
        timerInterval = setInterval(() => {
            timeLeft--;
            updateTimerDisplay();

            if (timeLeft <= 0) {
                clearInterval(timerInterval);
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    displayQuestion(currentQuestionIndex);
                    startTimer();
                } else {
                    console.log("Все вопросы были показаны.");
                    // переход на страницу с результатами
                    window.location.href = "/main-jsp";
                }
            }
        }, 1000);
    }

    function updateTimerDisplay() {
        document.querySelector('.timer').textContent = timeLeft;
    }

    loadQuestions();
});