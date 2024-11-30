function togglePasswordVisibility(inputId) {
    const passwordInput = document.getElementById(inputId);
    const currentType = passwordInput.getAttribute('type');

    if (currentType === 'password') {
        passwordInput.setAttribute('type', 'text');
    } else {
        passwordInput.setAttribute('type', 'password');
    }
}