package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.PlayerDAO;
import services.AnswerService;
import services.impl.AnswerServiceImpl;
import utils.Constants;

import java.io.IOException;

@WebServlet(name = "SubmitAnswerServlet", urlPatterns = "/submit-answer-servlet")
public class SubmitAnswerServlet extends HttpServlet {
    private final AnswerService answerService = new AnswerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        int game_id = (int) session.getAttribute("game_id");
        String answerId = req.getParameter("answerId");
        String questionId = req.getParameter("questionId");
        String answerText = req.getParameter("answerText");

        String answerFromDB =  answerService.getAnswerById(Integer.parseInt(answerId), Constants.RIGHT_ANSWERS);

        if (answerFromDB.equals(answerText)) {
            PlayerDAO playerDAO = new PlayerDAO();
            int current_score = playerDAO.getTotalScore(user_id, game_id); // Получаем текущий счет
            int new_score = current_score + 1;
            playerDAO.updateTotalScore(user_id, game_id, new_score);
        }
    }
}