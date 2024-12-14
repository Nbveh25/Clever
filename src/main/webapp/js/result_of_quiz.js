document.addEventListener("DOMContentLoaded", function () {

    updateScores();

    setInterval(updateScores, 5000);
})

function updateScores() {
    fetch("/get-players")
        .then(response => response.json())
        .then(data => {
            const resultList = document.querySelector('.result_container');
            resultList.innerHTML = ''

            data.forEach(result => {
                const resultItem = document.createElement('div');
                resultItem.className = 'result_item';
                resultItem.innerHTML = `
                        <img src="${result.icon_url}" alt="icon" class="icon_of_user">
                        <h2 class="nickname">${result.login}</h2>
                        <h2 class="score">${result.total_score}</h2>
                    `;
                resultList.appendChild(resultItem);
            })
        })
        .catch(error => {
            console.error("Ошибка при получении данных: ", error);
        });
}
