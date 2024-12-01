package servlets;

import com.google.gson.Gson;
import dto.QuestionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.GameService;
import services.QuestionService;
import services.impl.GameServiceImpl;
import services.impl.QuestionServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "QuizServlet", urlPatterns = "/quiz-servlet")
public class QuizServlet extends HttpServlet {
    private final QuestionService questionService = new QuestionServiceImpl();
    private final GameService gameService = new GameServiceImpl();

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int game_id = (int) req.getSession().getAttribute("game_id");

        gameService.deleteGame(game_id);

        req.getRequestDispatcher("/main-jsp").forward(req, resp);
    }
}
