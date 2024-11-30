package servlets;

import com.google.gson.Gson;
import dto.QuestionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.QuestionService;
import services.impl.QuestionServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "QuizStartServlet", urlPatterns = "/quiz-start-servlet")
public class QuizStartServlet extends HttpServlet {
    private final QuestionService questionService = new QuestionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int quiz_id = (int) session.getAttribute("quiz_id");

        try (PrintWriter out = resp.getWriter()) {
            List<QuestionDTO> questions = questionService.getQuestions(quiz_id);
            Gson gson = new Gson();
            String json = gson.toJson(questions);
            out.print(json);
            out.flush();
        }
    }
}
