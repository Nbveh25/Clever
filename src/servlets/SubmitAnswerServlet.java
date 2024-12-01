package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.PlayerDAO;
import services.AnswerService;
import services.PlayerService;
import services.impl.AnswerServiceImpl;
import services.impl.PlayerServiceImpl;
import utils.Constants;

import java.io.IOException;

@WebServlet(name = "SubmitAnswerServlet", urlPatterns = "/submit-answer-servlet")
public class SubmitAnswerServlet extends HttpServlet {
    private final AnswerService answerService = new AnswerServiceImpl();
    private final PlayerService playerService = new PlayerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        int game_id = (int) session.getAttribute("game_id");
        String answerId = req.getParameter("answerId");
        String answerText = req.getParameter("answerText");

        String answerFromDB =  answerService.getAnswerById(Integer.parseInt(answerId), Constants.RIGHT_ANSWERS);

        if (answerFromDB.equals(answerText)) {
            int current_score = playerService.getTotalScore(user_id, game_id);
            int new_score = current_score + 1;
            playerService.updateTotalScore(user_id, game_id, new_score);
        }
    }
}