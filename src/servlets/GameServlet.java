package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Game;
import model.GameDAO;
import model.Utils;

import java.io.IOException;

@WebServlet("/game-servlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String quiz_id = req.getParameter("id");
        String code = Utils.generateCode();

        req.setAttribute("code", code);

        GameDAO gameDAO = new GameDAO();
        gameDAO.addGame(new Game(Integer.parseInt(quiz_id), code));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/game-jsp");
        dispatcher.forward(req, resp);
    }

    // Запускает игру
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizStatusServlet.startQuiz();
    }
}
