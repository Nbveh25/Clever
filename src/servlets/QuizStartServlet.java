package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dto.Question;
import dao.QuestionDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/quiz-start-servlet")
public class QuizStartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        QuestionDAO questionDAO = new QuestionDAO();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int quiz_id = (int) session.getAttribute("quiz_id");

        try (PrintWriter out = resp.getWriter()) {
            List<Question> questions = questionDAO.getQuizQuestions(quiz_id);
            Gson gson = new Gson();
            String json = gson.toJson(questions);
            out.print(json);
            out.flush();
        }
    }
}
