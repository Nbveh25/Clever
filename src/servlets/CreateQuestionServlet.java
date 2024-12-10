package servlets;

import com.cloudinary.Cloudinary;
import dto.AnswerDTO;
import dto.QuestionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.AnswerService;
import services.QuestionService;
import utils.CloudinaryUtil;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.UploadUtil.*;

@WebServlet(name = "CreateQuestionServlet", urlPatterns = "/create-question-servlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 1024
)
public class CreateQuestionServlet extends HttpServlet {

    private QuestionService questionService;
    private AnswerService answerService;
    private Cloudinary cloudinary;

    @Override
    public void init() throws ServletException {
        questionService = (QuestionService) getServletContext().getAttribute("questionService");
        answerService = (AnswerService) getServletContext().getAttribute("answerService");
        cloudinary = CloudinaryUtil.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer questionNumber = (Integer) session.getAttribute("questionNumber");
        if (questionNumber == null) {
            questionNumber = 2;
            session.setAttribute("questionNumber", questionNumber);
        }

        req.setAttribute("number", questionNumber);
        req.getRequestDispatcher(getServletContext().getContextPath() + "/create-question-jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer questionNumber = (Integer) session.getAttribute("questionNumber");
        if (questionNumber == null) {
            questionNumber = 2;
        } else {
            questionNumber++;
        }
        session.setAttribute("questionNumber", questionNumber);

        String question_quiz = req.getParameter("question_quiz");
        String question_type = req.getParameter("question_type");
        Integer quiz_id = (Integer) session.getAttribute("quiz_id");

        String right_answer = req.getParameter("right_answer");
        String wrong_answer1 = req.getParameter("wrong_answer1");
        String wrong_answer2 = req.getParameter("wrong_answer2");
        String wrong_answer3 = req.getParameter("wrong_answer3");

        String mediaPath = null;

        if (!"text_question".equals(question_type)) {
            Part part = req.getPart("mediaFile");
            if (part == null || part.getSize() == 0) {
                throw new ServletException("File is required for non-text questions");
            } else {

                File file = getFile(part);
                String resourceType = getResourceType(part.getContentType());

                Map<String, Object> uploadParams = new HashMap<>();
                uploadParams.put("folder", getFolderUpload(question_type));
                uploadParams.put("resource_type", resourceType);

                Map uploadResult = cloudinary.uploader().upload(file, uploadParams);
                mediaPath = (String) uploadResult.get("secure_url");

            }
        }

        QuestionDTO questionDTO = new QuestionDTO(quiz_id, question_quiz, question_type, mediaPath);

        if (req.getParameter("add_question") != null) {
            saveQuestion(right_answer, wrong_answer1, wrong_answer2, wrong_answer3, questionDTO);
            req.setAttribute("number", questionNumber);
            req.getRequestDispatcher(getServletContext().getContextPath() + "/create-question-jsp").forward(req, resp);
        } else if (req.getParameter("save_quiz") != null) {
            saveQuestion(right_answer, wrong_answer1, wrong_answer2, wrong_answer3, questionDTO);
            req.getRequestDispatcher(getServletContext().getContextPath() + "/choose-quiz-jsp").forward(req, resp);
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