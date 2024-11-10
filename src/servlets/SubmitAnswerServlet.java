package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.AnswerDAO;
import dao.PlayerDAO;

import java.io.IOException;

@WebServlet("/submit-answer-servlet")
public class SubmitAnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        int game_id = (int) session.getAttribute("game_id");
        String answerId = req.getParameter("answerId");
        String questionId = req.getParameter("questionId");
        String answerText = req.getParameter("answerText");

        AnswerDAO answerDAO = new AnswerDAO();
        String answerFromDB =  answerDAO.getAnswerById(Integer.parseInt(answerId), "right_answers");

        if (answerFromDB.equals(answerText)) {
            PlayerDAO playerDAO = new PlayerDAO();
            int currentScore = playerDAO.getTotalScore(user_id, game_id); // Получаем текущий счет
            int newScore = currentScore + 1;
            playerDAO.updateTotalScore(user_id, game_id, newScore);
        }
    }
}