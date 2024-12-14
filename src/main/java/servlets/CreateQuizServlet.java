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

@WebServlet(name = "CreateQuizServlet", urlPatterns = "/create-quiz")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CreateQuizServlet extends HttpServlet {

    private Cloudinary cloudinary;
    private QuizService quizService;

    private static final String QUIZ_ICON_FOLDER = "quiz_icons";
    private static final String DEFAULT_QUIZ_ICON = "https://res.cloudinary.com/dsrqq4er2/image/upload/v1733603414/logo_without_name_1_poumis.png";

    @Override
    public void init() throws ServletException {
        quizService = (QuizServiceImpl) getServletContext().getAttribute("quizService");
        cloudinary = CloudinaryUtil.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/create_quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_quiz = req.getParameter("name_quiz");
        String description_quiz = req.getParameter("description_quiz");
        String quiz_type = req.getParameter("quiz_type");

        String quizIconPath = DEFAULT_QUIZ_ICON;

        Part filePart = req.getPart("quiz_icon");
        if (filePart != null && filePart.getSize() > 0) {
            try {
                File file = getFile(filePart);

                Map<String, Object> uploadParams = new HashMap<>();
                uploadParams.put("folder", QUIZ_ICON_FOLDER);

                Map uploadResult = cloudinary.uploader().upload(file, uploadParams);
                quizIconPath = (String) uploadResult.get("secure_url");

                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        QuizDTO quizDTO = new QuizDTO(name_quiz, description_quiz, quiz_type, quizIconPath);
        int quiz_id = quizService.addQuiz(quizDTO);

        req.getSession().setAttribute("quiz_id", quiz_id);
        resp.sendRedirect(req.getContextPath() + "/create-question");
    }
}