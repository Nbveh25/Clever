package servlets;

import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.QuizDAO;
import utils.UploadFilesUtil;

import java.io.IOException;

@WebServlet(name = "CreateQuizServlet", urlPatterns = "/create-quiz-servlet")
@MultipartConfig(
        location="C:\\Users\\timur\\IdeaProjects\\Clever\\src\\main\\webapp\\source\\quiz_icons",
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*1024,
        maxRequestSize=1024*1024*5*5
)
public class CreateQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_quiz = req.getParameter("name_quiz");
        String description_quiz = req.getParameter("description_quiz");
        String quiz_type = req.getParameter("quiz_type");

        HttpSession session = req.getSession();
        UploadFilesUtil uploadFilesService = new UploadFilesUtil();

        Part part = req.getPart("quiz_icon");
        String filename = part.getSubmittedFileName();

        String uploadPath = "C:\\Users\\timur\\IdeaProjects\\Clever\\src\\main\\webapp\\source\\quiz_icons\\" + filename;

        String quizIconPath = "http://localhost:8080/source/quiz_icons/" + filename;

        uploadFilesService.uploadMediaFile(part.getInputStream(), uploadPath);

        QuizDTO quizDTO = new QuizDTO(name_quiz, description_quiz, quiz_type, quizIconPath);
        QuizDAO quizDAO = new QuizDAO();

        int quiz_id = quizDAO.addQuiz(quizDTO);
        session.setAttribute("quiz_id", quiz_id);

        req.getRequestDispatcher("/create-question-jsp").forward(req, resp);

        // TODO (Подумать про дубликаты квизов и всяческие проверки)
    }
}
