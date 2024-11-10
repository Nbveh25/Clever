package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.Quiz;
import dao.QuizDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/choose-quiz-servlet")
public class ChooseQuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizDAO quizDAO = new QuizDAO();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            List<Quiz> quizzes = quizDAO.getAllQuiz();
            Gson gson = new Gson();
            String json = gson.toJson(quizzes);
            out.print(json);
            out.flush();
        }
    }
}
