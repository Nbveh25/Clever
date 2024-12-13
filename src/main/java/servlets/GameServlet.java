package servlets;

import dto.GameDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.GameService;
import websockets.QuizStartWebSocket;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "GameServlet", urlPatterns = "/game-servlet")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        gameService = (GameService) getServletContext().getAttribute("gameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String quiz_id = req.getParameter("id");
        String code = String.format("%06d", new Random().nextInt(1000000));

        req.setAttribute("code", code);

        GameDTO gameDTO = new GameDTO(Integer.parseInt(quiz_id), code);
        int game_id = gameService.addGame(gameDTO);

        session.setAttribute("game_id", game_id);

        getServletContext().getRequestDispatcher("/WEB-INF/views/page_with_code.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizStartWebSocket.notifyQuizStarted();
        resp.sendRedirect("/result-of-quiz-servlet");
    }
}
