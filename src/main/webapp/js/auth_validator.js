document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('.auth_form');
    const codeInput = document.querySelector('input[name="input_code"]');
    const errorMessageContainer = document.querySelector('.error_message');

    form.addEventListener('submit', function(event) {
        let isValid = true;
        let errorMessage = '';

        errorMessageContainer.innerHTML = '';

        // Проверка кода
        if (codeInput.value.trim() === '') {
            isValid = false;
            errorMessage += 'Код не может быть пустым.<br>';
        } else if (codeInput.value.length !== 6) {
            isValid = false;
            errorMessage += 'Код должен содержать 6 символов.<br>';
        }

        // Если есть ошибки, отменяем отправку формы и выводим сообщение
        if (!isValid) {
            event.preventDefault();
            errorMessageContainer.innerHTML = errorMessage;
            errorMessageContainer.style.display = 'block';
        } else {
            errorMessageContainer.style.display = 'none';
        }
    });
});