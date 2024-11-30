package servlets;

import com.google.gson.Gson;
import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.QuizService;
import services.impl.QuizServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChooseQuizServlet", urlPatterns = "/choose-quiz-servlet")
public class ChooseQuizServlet extends HttpServlet {
    private final QuizService quizService = new QuizServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            List<QuizDTO> quizzes = quizService.getQuizzes();
            Gson gson = new Gson();
            String json = gson.toJson(quizzes);
            out.print(json);
            out.flush();
        }
    }
}
