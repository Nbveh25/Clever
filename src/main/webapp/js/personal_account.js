function upgradePermission() {
    fetch('/user-servlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(response => {
        if (response.ok) {
            Swal.fire('Права успешно обновлены!');
            console.log('Успех!');
        } else {
            console.error('Ошибка при обновлении прав:', response.status, response.statusText);
            return response.text().then(text => {
                Swal.fire('Ошибка при обновлении прав: ' + text);
            });
        }
    }).catch(error => {
        console.log('Ошибка:', error);
        Swal.fire('Произошла ошибка при обновлении прав.');
    });
}

function updateLogin() {
    Swal.fire({
        title: 'Введите новый логин',
        input: 'text',
        inputPlaceholder: 'Ваш новый логин',
        showCancelButton: true,
        confirmButtonText: 'Сохранить',
        cancelButtonText: 'Отмена',
    }).then((result) => {
        if (result.isConfirmed) {
            const newLogin = result.value;
            console.log('Полученный логин:', newLogin);

            if (newLogin !== null && newLogin.trim() !== '') {
                fetch('/user-servlet', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ newLogin: newLogin })
                })
                    .then(response => response.text())
                    .then(data => {
                        location.reload();
                        console.log('Ответ от сервлета:', data);
                    })
                    .catch(error => {
                        console.error('Ошибка:', error);
                    });
            }
        }
    });
}

function deleteUser() {
    Swal.fire({
        title: 'Подтверждение удаления',
        text: "Вы уверены, что хотите удалить свой профиль?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Да, удалить!',
        cancelButtonText: 'Отмена'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch('/user-servlet', {
                method: 'DELETE'
            }).then(response => response.text()) // Исправлено: добавлен return
                .then(text => { // Добавлен новый then для обработки текста ответа
                    // Здесь можно добавить логику после успешного удаления, например:
                    // window.location.href = '/login-jsp';
                }).catch(error => {
                console.error('Ошибка', error);
                //Swal.fire('Произошла ошибка. Попробуйте еще раз.');
            });
        }
    });
}


document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('upgrade-permission').addEventListener("click", upgradePermission)
    document.getElementById('delete-profile').addEventListener("click", deleteUser);
    document.getElementById('change-login').addEventListener("click", updateLogin);
});