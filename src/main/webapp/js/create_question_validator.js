document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    const questionTypeSelect = document.querySelector("select[name='question_type']");
    const mediaFileInput = document.querySelector("input[name='mediaFile']");

    form.addEventListener("submit", function(event) {
        const questionType = questionTypeSelect.value;

        if (questionType !== "text_question" && !mediaFileInput.files.length) {
            event.preventDefault();
            alert("Пожалуйста, загрузите файл для вопроса, который не является текстовым.");
        }
    });
});
