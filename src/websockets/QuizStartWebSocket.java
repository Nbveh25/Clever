package websockets;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/quiz-start-websocket")
public class QuizStartWebSocket {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void notifyQuizStarted() {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText("{\"quizStarted\": true}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
