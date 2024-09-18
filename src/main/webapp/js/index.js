const signInBtn = document.querySelector('.signin_btn');
const signUpBtn = document.querySelector('.signup_btn');
const formBox = document.querySelector('.form_box');
const body = document.body;

signUpBtn.addEventListener('click', function(){
    formBox.classList.add('active');
    body.classList.add('active');
})

signInBtn.addEventListener('click', function(){
    formBox.classList.remove('active');
    body.classList.remove('active');
})
