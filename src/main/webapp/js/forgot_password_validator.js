document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('.forgot_pass_form');
    const emailInput = document.querySelector('input[name="email"]');
    const passwordInput = document.querySelector('input[name="password"]');
    const repasswordInput = document.querySelector('input[name="repassword"]');
    const errorMessageDiv = document.querySelector('.error_message');

    form.addEventListener('submit', function (event) {
        let isValid = true;
        let errorMessage = '';
        const minLength = 6; 

        errorMessageDiv.style.display = 'none';
        errorMessageDiv.textContent = '';

        // Проверка почты
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInput.value)) {
            isValid = false;
            errorMessage += 'Введите корректный email.<br>';
        }

        // ПРоверка пароля
        const password = passwordInput.value;
        if (password.length < minLength) {
            isValid = false;
            errorMessage += 'Пароль должен содержать минимум 6 символов.<br>';
        }
        if (!/[A-Z]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну заглавную букву.<br>';
        }
        if (!/[a-z]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну строчную букву.<br>';
        }
        if (!/[0-9]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы одну цифру.<br>';
        }
        if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
            isValid = false;
            errorMessage += 'Пароль должен содержать хотя бы один специальный символ.<br>';
        }

        // Совпадение паролей
        if (password !== repasswordInput.value) {
            isValid = false;
            errorMessage += 'Пароли не совпадают. Пожалуйста, попробуйте снова.<br>';
        }

        if (!isValid) {
            event.preventDefault(); // Prevent form submission
            errorMessageDiv.innerHTML = errorMessage; // Set error messages
            errorMessageDiv.style.display = 'block'; // Show error message div
        }
    });
});