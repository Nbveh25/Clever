document.addEventListener("DOMContentLoaded", function() {
    fetch('/choose-quiz-servlet')
        .then(response => response.json())
        .then(data => {
            const quizList = document.querySelector('.quiz-list');
            quizList.innerHTML = '';
            data.forEach(quiz => {
                const quizTag = document.createElement('div');
                quizTag.className = 'quiz-tag';
                quizTag.innerHTML = `
              <img src="${quiz.quizIconPath}" alt="${quiz.quizName}" class="quiz-icon">
              <div class="quiz-content"> 
                <h2 id="title_of_quiz">${quiz.quizName}</h2>
                <p>${quiz.quizDescription}</p>
                <h3 id="quiz_type">${quiz.quizType}</h3>
                <a href="/game-servlet?id=${quiz.id}" class="quiz-button">Начать квиз</a>
              </div>
            `;
                quizList.appendChild(quizTag);
            });
        })
        .catch(error => console.error('Error fetching quizzes:', error));
});


