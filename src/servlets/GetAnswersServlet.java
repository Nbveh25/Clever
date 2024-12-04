package servlets;

import com.google.gson.Gson;
import dto.AnswerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AnswerService;
import utils.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "GetAnswersServlet", urlPatterns = "/get-answers-servlet")
public class GetAnswersServlet extends HttpServlet {
    private AnswerService answerService;

    @Override
    public void init() throws ServletException {
        super.init();
        answerService = (AnswerService) getServletContext().getAttribute("answerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        List<AnswerDTO> wrongAnswers = answerService.getAnswers(questionId, Constants.WRONG_ANSWERS);
        List<AnswerDTO> rightAnswers = answerService.getAnswers(questionId, Constants.RIGHT_ANSWERS);
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
