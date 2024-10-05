package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz;
import model.QuizDAO;

import java.io.IOException;

@WebServlet("/create-quiz-servlet")
public class CreateQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_quiz = req.getParameter("name_quiz");
        String description_quiz = req.getParameter("description_quiz");
        String quiz_type = req.getParameter("quiz_type");

        HttpSession session = req.getSession();

        Quiz quiz = new Quiz(name_quiz, description_quiz, quiz_type);
        QuizDAO quizDAO = new QuizDAO();

        int quizId = quizDAO.addQuiz(quiz);
        session.setAttribute("quiz_id", quizId);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/create-question-jsp");
        dispatcher.forward(req, resp);

        // TODO (Подумать про дубликаты квизов и всяческие проверки)
    }
}
