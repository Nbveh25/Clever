document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('.form_signin_login');
    const loginInput = document.getElementById('login');
    const passwordInput = document.getElementById('password');
    const errorMessageContainer = document.querySelector('.error_message');

    form.addEventListener('submit', function(event) {
        let isValid = true;
        let errorMessage = '';
        const password = passwordInput.value;
        const minLength = 6;

        errorMessageContainer.innerHTML = '';

        // Проверка логина
        if (loginInput.value.trim() === '') {
            isValid = false;
            errorMessage += 'Логин не может быть пустым.<br>';
        } else if (loginInput.value.length < 3) {
            isValid = false;
            errorMessage += 'Логин должен содержать не менее 3 символов.<br>';
        }

        // Проверка пароля
        if (password.length < minLength) {
            isValid = false;
            errorMessage += 'Пароль должен содержать минимум 6 символов.<br>';
        } else if (!/[A-Z]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну заглавную букву.<br>';
        } else if (!/[a-z]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну строчную букву.<br>';
        } else if (!/[0-9]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну цифру.<br>';
        } else if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы один специальный символ.<br>';
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