//const socket = new WebSocket('ws://localhost:8080/quiz-start-websocket');
const url = new URL(window.location.href)
const socket = new WebSocket('ws://'+url.host+'/quiz-start-websocket');


socket.onmessage = function (event) {
    const data = JSON.parse(event.data);
    if (data.quizStarted) {
        window.location.href = "/quiz-show"
    }
}