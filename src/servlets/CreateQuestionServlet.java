package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Answer;
import model.AnswerDAO;
import model.Question;
import model.QuestionDAO;
import services.UploadFilesService;

import java.io.*;

@WebServlet("/create-question-servlet")
@MultipartConfig(
        location="C:\\Users\\timur\\IdeaProjects\\Clever\\src\\main\\webapp\\media",
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*1024, maxRequestSize=1024*1024*5*5
)
public class CreateQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UploadFilesService uploadFilesService = new UploadFilesService();
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();

        String question_quiz = req.getParameter("question_quiz");
        String quiz_type = req.getParameter("quiz_type");
        Integer quiz_id = (Integer) session.getAttribute("quiz_id");

        String right_answer = req.getParameter("right_answer");
        String wrong_answer1 = req.getParameter("wrong_answer1");
        String wrong_answer2 = req.getParameter("wrong_answer2");
        String wrong_answer3 = req.getParameter("wrong_answer3");

        Part part = req.getPart("mediaFile");
        String fileName = part.getSubmittedFileName();
        String path = uploadFilesService.getPathForUpload(fileName, quiz_type);
        String mediaPath = uploadFilesService.getPathForMedia(fileName, quiz_type);

        uploadFilesService.uploadMediaFile(part.getInputStream(), path);

        Question question = new Question(quiz_id, question_quiz, quiz_type, mediaPath);

        if (req.getParameter("add_question") != null) {

            int question_id = questionDAO.addQuestion(question);

            answerDAO.addAnswer(new Answer(question_id, right_answer), "right_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer1), "wrong_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer2), "wrong_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer3), "wrong_answers");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/create-question-jsp");
            dispatcher.forward(req, resp);
        } else if (req.getParameter("save_quiz") != null) {
            // Сохраняем вопрос в БД, переходим на страницу со списком квизов
            int question_id = questionDAO.addQuestion(question);

            answerDAO.addAnswer(new Answer(question_id, right_answer), "right_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer1), "wrong_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer2), "wrong_answers");
            answerDAO.addAnswer(new Answer(question_id, wrong_answer3), "wrong_answers");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/choose-quiz-jsp");
            dispatcher.forward(req, resp);
        }
    }
}