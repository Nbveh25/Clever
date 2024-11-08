package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GameDAO;
import model.Player;
import model.PlayerDAO;

import java.io.IOException;

@WebServlet("/main-servlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String code = req.getParameter("input_code");
        int user_id = (int) session.getAttribute("user_id");
        String login = (String) session.getAttribute("login");
        System.out.println("user_id: " + user_id);
        GameDAO gameDAO = new GameDAO();
        int game_id = gameDAO.getGameId(code);

        // Ищем игру по коду в БД
        if (game_id != -1) {
            int quiz_id = gameDAO.getQuizId(game_id);
            PlayerDAO playerDAO = new PlayerDAO();

            Player player = new Player(user_id, game_id, login,0);
            playerDAO.addPlayer(player);

            session.setAttribute("quiz_id", quiz_id);
            session.setAttribute("game_id", game_id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("html/waiting_of_quiz.html");
            dispatcher.forward(req, resp);
        }
    }
}
