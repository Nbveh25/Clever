package servlets;

import dto.PlayerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.GameService;
import services.PlayerService;

import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main-servlet")
public class MainServlet extends HttpServlet {
    private GameService gameService;
    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        gameService = (GameService) getServletContext().getAttribute("gameService");
        playerService = (PlayerService) getServletContext().getAttribute("playerService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String code = req.getParameter("input_code");
        int user_id = (int) session.getAttribute("user_id");
        String login = (String) session.getAttribute("login");
        String icon_url = (String) session.getAttribute("icon_url");
        int game_id = gameService.getGameIdByCode(code);

        // Ищем игру по коду в БД
        if (game_id != -1) {
            int quiz_id = gameService.getQuizIdByGameId(game_id);

            PlayerDTO playerDTO = new PlayerDTO(user_id, game_id, login,0, icon_url);
            playerService.addPlayer(playerDTO);

            session.setAttribute("quiz_id", quiz_id);
            session.setAttribute("game_id", game_id);
            req.getRequestDispatcher("html/waiting_of_quiz.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/main-jsp").forward(req, resp);
        }
    }
}