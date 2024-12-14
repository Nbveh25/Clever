package servlets;

import com.google.gson.Gson;
import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.QuizService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChooseQuizServlet", urlPatterns = "/choose-quiz")
public class ChooseQuizServlet extends HttpServlet {
    private QuizService quizService;

    @Override
    public void init() throws ServletException {
        quizService = (QuizService) getServletContext().getAttribute("quizService");
    }

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
