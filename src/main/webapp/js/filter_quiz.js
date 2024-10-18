const filter = () => {
    const selectedCategory = document.getElementById('filter-selector').value;
    const quizTags = document.querySelectorAll(".quiz-tag"); // Получаем все элементы квизов

    quizTags.forEach(quiz => {
        const quizTypeElement = quiz.querySelector('h3'); // Получаем элемент h3 внутри текущего квиза
        let quizType = quizTypeElement ? quizTypeElement.textContent : ""; // Получаем текст типа квиза

        // Проверяем, соответствует ли тип квиза выбранной категории
        if (selectedCategory === "" || quizType === selectedCategory) {
            quiz.style.display = ""; // Показываем элемент
        } else {
            quiz.style.display = "none"; // Скрываем элемент
        }
    });
}