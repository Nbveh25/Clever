package servlets;

import dto.AnswerDTO;
import dto.QuestionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Answer;
import services.AnswerService;
import services.QuestionService;
import services.impl.AnswerServiceImpl;
import services.impl.QuestionServiceImpl;
import utils.Constants;
import utils.UploadFilesUtil;

import java.io.*;

@WebServlet(name = "CreateQuestionServlet", urlPatterns = "/create-question-servlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreateQuestionServlet extends HttpServlet {
    private final QuestionService questionService = new QuestionServiceImpl();
    private final AnswerService answerService = new AnswerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UploadFilesUtil uploadFilesUtil = new UploadFilesUtil();

        String question_quiz = req.getParameter("question_quiz");
        String quiz_type = req.getParameter("quiz_type");
        Integer quiz_id = (Integer) session.getAttribute("quiz_id");

        String right_answer = req.getParameter("right_answer");
        String wrong_answer1 = req.getParameter("wrong_answer1");
        String wrong_answer2 = req.getParameter("wrong_answer2");
        String wrong_answer3 = req.getParameter("wrong_answer3");

        Part part = req.getPart("mediaFile");
        String fileName = part.getSubmittedFileName();
        String path = uploadFilesUtil.getPathForUpload(fileName, quiz_type);
        String mediaPath = uploadFilesUtil.getPathForMedia(fileName, quiz_type);

        uploadFilesUtil.uploadMediaFile(part.getInputStream(), path);

        QuestionDTO questionDTO = new QuestionDTO(quiz_id, question_quiz, quiz_type, mediaPath);

        if (req.getParameter("add_question") != null) {

            saveQuestion(right_answer, wrong_answer1, wrong_answer2, wrong_answer3, questionDTO);

            req.getRequestDispatcher("/create-question-jsp").forward(req, resp);
        } else if (req.getParameter("save_quiz") != null) {

            saveQuestion(right_answer, wrong_answer1, wrong_answer2, wrong_answer3, questionDTO);

            req.getRequestDispatcher("/choose-quiz-jsp").forward(req, resp);
        }
    }

    private void saveQuestion(String right_answer, String wrong_answer1, String wrong_answer2, String wrong_answer3, QuestionDTO questionDTO) {
        int question_id = questionService.addQuestion(questionDTO);

        answerService.addAnswer(new AnswerDTO(question_id, right_answer), Constants.RIGHT_ANSWERS);
        answerService.addAnswer(new AnswerDTO(question_id, wrong_answer1), Constants.WRONG_ANSWERS);
        answerService.addAnswer(new AnswerDTO(question_id, wrong_answer2), Constants.WRONG_ANSWERS);
        answerService.addAnswer(new AnswerDTO(question_id, wrong_answer3), Constants.WRONG_ANSWERS);
    }


}