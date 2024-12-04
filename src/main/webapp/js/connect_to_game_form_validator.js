document.addEventListener("DOMContentLoaded", function (event) {
    const form = document.getElementById('connect-to-game-form')
    const codeInput = document.querySelector('input[name="input_code"]');
    const errorMessageContainer = document.querySelector('.error_message');

    form.addEventListener('submit', function (event) {
        let isValid = true;
        let errorMessage = ''
        const minLength = 6;

        errorMessageContainer.innerHTML = '';

        if (codeInput.value.trim() === '') {
            isValid = false
            errorMessage = 'Код не может быть пустым.'
        } else if (codeInput.value.length !== minLength) {
            isValid = false
            errorMessage = 'Код должен быть шестизначным.'
        }

        if (!isValid) {
            event.preventDefault();
            errorMessageContainer.innerHTML = errorMessage;
            errorMessageContainer.style.display = 'block';
        } else {
            errorMessageContainer.style.display = 'none';
        }
    })
})