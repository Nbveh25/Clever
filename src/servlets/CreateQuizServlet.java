package servlets;

import com.cloudinary.Cloudinary;
import dto.QuizDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.QuizService;
import services.impl.QuizServiceImpl;
import utils.CloudinaryUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.UploadUtil.getFile;

@WebServlet(urlPatterns = "/create-quiz")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CreateQuizServlet extends HttpServlet {

    private QuizService quizService;
    private Cloudinary cloudinary;

    @Override
    public void init() throws ServletException {
        quizService = (QuizServiceImpl) getServletContext().getAttribute("quizService");
        cloudinary = CloudinaryUtil.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_quiz = req.getParameter("name_quiz");
        String description_quiz = req.getParameter("description_quiz");
        String quiz_type = req.getParameter("quiz_type");

        Part part = req.getPart("quiz_icon");
        File file = getFile(part);

        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("folder", "quiz_icons");

        Map uploadResult = cloudinary.uploader().upload(file, uploadParams);

        String quizIconPath = (String) uploadResult.get("secure_url");

        QuizDTO quizDTO = new QuizDTO(name_quiz, description_quiz, quiz_type, quizIconPath);
        int quiz_id = quizService.addQuiz(quizDTO);

        req.getSession().setAttribute("quiz_id", quiz_id);

        req.getRequestDispatcher("/create-question-jsp").forward(req, resp);

        file.delete();
    }
}