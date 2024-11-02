
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

            questionHtml += `
                <div class="button_container">
                    <button class="button_answer red" data-answer="1">Ответ 1</button>
                    <button class="button_answer purple" data-answer="2">Ответ 2</button>
                    <button class="button_answer orange" data-answer="3">Ответ 3</button>
                    <button class="button_answer green" data-answer="4">Ответ 4</button>
                </div>
                </div>
            `;

            container.insertAdjacentHTML('beforeend', questionHtml);

            document.querySelectorAll('.button_answer').forEach(button => {
                button.addEventListener('click', function() {
                    let answer = this.getAttribute('data-answer');
                    submitAnswer(answer);
                });
            });
        }
    }

    function submitAnswer(answer) {
        fetch('/submit-answer-servlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `answer=${encodeURIComponent(answer)}`
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
        timeLeft = 15; // Reset timer
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
                }
            }
        }, 1000);
    }

    function updateTimerDisplay() {
        document.querySelector('.timer').textContent = timeLeft;
    }

    // Load questions when the page loads
    loadQuestions();
});