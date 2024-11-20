package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Answer;
import dao.AnswerDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet("/get-answers-servlet")
public class GetAnswersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        AnswerDAO answerDAO = new AnswerDAO();

        List<Answer> wrongAnswers = answerDAO.getAnswers(questionId, "wrong_answers");
        List<Answer> rightAnswers = answerDAO.getAnswers(questionId, "right_answers");
        wrongAnswers.addAll(rightAnswers);

        Collections.shuffle(wrongAnswers);
        try (PrintWriter out = resp.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(wrongAnswers);
            out.println(json);
            out.flush();
        }
    }
}
