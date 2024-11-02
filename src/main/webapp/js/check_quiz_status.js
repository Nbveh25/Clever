function checkQuizStatus() {
    fetch('/quiz-status-servlet')
        .then(response => response.text())
        .then(status => {
            if (status === "started") {
                alert("Квиз начался!");
                // Здесь можно добавить логику для отображения первого вопроса
                // Переход на страницу с вопросами
                window.location.href = "/quiz-start-jsp";
            } else {
                setTimeout(checkQuizStatus, 10000); // Проверяем статус каждые 10 секунд
            }
        });
}
window.onload = checkQuizStatus;