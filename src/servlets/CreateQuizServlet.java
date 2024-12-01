package servlets;

import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.QuizService;
import services.impl.QuizServiceImpl;
import utils.UploadFilesUtil;

import java.io.IOException;

@WebServlet(name = "CreateQuizServlet", urlPatterns = "/create-quiz-servlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreateQuizServlet extends HttpServlet {
    private final QuizService quizService = new QuizServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_quiz = req.getParameter("name_quiz");
        String description_quiz = req.getParameter("description_quiz");
        String quiz_type = req.getParameter("quiz_type");

        HttpSession session = req.getSession();
        UploadFilesUtil uploadFilesService = new UploadFilesUtil();

        Part part = req.getPart("quiz_icon");
        String filename = part.getSubmittedFileName();

        String uploadDir = getServletContext().getRealPath("/source/quiz_icons");
        String uploadPath = uploadDir + "/" + filename;

        String contextPath = req.getContextPath();
        String quizIconPath = contextPath + "/source/quiz_icons/" + filename;

        uploadFilesService.uploadMediaFile(part.getInputStream(), uploadPath);

        QuizDTO quizDTO = new QuizDTO(name_quiz, description_quiz, quiz_type, quizIconPath);

        int quiz_id = quizService.addQuiz(quizDTO);
        session.setAttribute("quiz_id", quiz_id);

        req.getRequestDispatcher("/create-question-jsp").forward(req, resp);
    }
}