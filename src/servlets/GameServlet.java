package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import dao.GameDAO;
import utils.Utils;
import websockets.QuizStartWebSocket;

import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = "/game-servlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String quiz_id = req.getParameter("id");
        String code = Utils.generateCode();

        req.setAttribute("code", code);

        GameDAO gameDAO = new GameDAO();
        int game_id = gameDAO.addGame(new Game(Integer.parseInt(quiz_id), code));
        session.setAttribute("game_id", game_id);

        req.getRequestDispatcher("/page-with-code-jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizStartWebSocket.notifyQuizStarted();
        resp.sendRedirect("/result-of-quiz-jsp");
    }
}
