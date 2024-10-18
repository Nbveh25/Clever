const search = () => {
    const searchbox = document.getElementById("search-item").value.toUpperCase();
    const quiz = document.querySelectorAll(".quiz-tag");

    quiz.forEach(quizItem => {
        const match = quizItem.querySelector('h2#title_of_quiz');

        if (match) {
            let textvalue = match.textContent || match.innerHTML;

            if (textvalue.toUpperCase().indexOf(searchbox) > -1) {
                quizItem.style.display = "";
            } else {
                quizItem.style.display = "none";
            }
        }
    });
}
