document.addEventListener('DOMContentLoaded', function (event) {
    const form = document.getElementById('connect-to-game-form')
    const quizName = document.getElementById('name_quiz')
    const quizDescription = document.getElementById('description_quiz')
    const errorMessageContainer = document.querySelector('.error_message')

    form.addEventListener('submit', function () {
        let isValid = true
        let errorMessage = ''
        const minLengthQuizName = 40

        errorMessageContainer.innerHTML = '';

        //Проверка названия квиза
       /* if (quizName.length > minLengthQuizName) {
            isValid = false
            errorMessage = 'Название квиза должно быть меньше 40 символов'
        }

        //Проверка описания квиза
*/

    })
})