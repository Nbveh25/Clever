let questionCount = 1;

function addQuestion() {
    questionCount = parseInt(sessionStorage.getItem("number")) || 1;

    questionCount++;

    const questionNumberDisplay = document.getElementById("question-number");
    questionNumberDisplay.textContent = "Номер вопроса: " + questionCount;

    sessionStorage.setItem("number", questionCount);
}

window.onload = function() {
    questionCount = parseInt(sessionStorage.getItem("number")) || 1;
    const questionNumberDisplay = document.getElementById("question-number");
    questionNumberDisplay.textContent = "Вопрос №" + questionCount;

    const addButton = document.querySelector('button[name="add_question"]');
    addButton.addEventListener("click", function(event) {
        addQuestion();
    });

    const saveButton = document.querySelector('button[name="save_quiz"]');
    saveButton.addEventListener("click", function(event) {
        sessionStorage.setItem("number", 1);
    });
}